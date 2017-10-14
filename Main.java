import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

/**
 * @author denmkr
 */


class DBConnection {
    public static Connection connection;

    public static Statement getStatement() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/systems","postgres", "postgres");

        return connection.createStatement();
    }
}


public class Main {

    private static String readFile(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));

        String line;
        StringBuilder stringBuilder = new StringBuilder();

        /* Обычное чтение файла построчно */
        try {
            while ((line = reader.readLine()) != null) stringBuilder.append(line);
            return stringBuilder.toString();
        } finally { reader.close(); }
    }

    private static void AddValuesToDB(int documentId, String genome, Statement statement, int kShingle) throws SQLException {
        Set<String> resultSet = new HashSet<>();
        String forResult;

        int num = kShingle - 1;

        for (int i = 0; i < genome.length() - num; i++) {
            forResult = genome.substring(i, i + kShingle);
            resultSet.add(forResult);
        }

        for (String string : resultSet) {
            statement.executeUpdate("INSERT INTO genom.elements(document_id, letters, result) VALUES ('" + documentId + "', '" + string +"', " + kShingle + ")");
        }
    }

    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
        DBConnection.getStatement();
        Statement statement = DBConnection.getStatement();

        String genome1 = readFile("Genome_1.txt");
        String genome2 = readFile("Genome_2.txt");

        /* По 2 символа */
        AddValuesToDB(1, genome1, statement,2);
        AddValuesToDB(2, genome2, statement,2);

        /* По 5 символов */
        AddValuesToDB(1, genome1, statement,5);
        AddValuesToDB(2, genome2, statement,5);

        /* По 9 символов */
        AddValuesToDB(1, genome1, statement,9);
        AddValuesToDB(2, genome2, statement,9);

        DBConnection.connection.close();
    }
}
