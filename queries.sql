SELECT ((SELECT count(*) FROM (
 (SELECT letters FROM genom.elements WHERE document_id = 1 and result = 2)
  INTERSECT
 (SELECT letters FROM genom.elements WHERE document_id = 2 and result = 2)
) query1)::float
/
(SELECT count(*) FROM (
 (SELECT letters FROM genom.elements WHERE document_id = 1 and result = 2)
  UNION
 (SELECT letters FROM genom.elements WHERE document_id = 2 and result = 2)
) query2)::float
) as result;


SELECT ((SELECT count(*) FROM (
 (SELECT letters FROM genom.elements WHERE document_id = 1 and result = 5)
  INTERSECT ALL
 (SELECT letters FROM genom.elements WHERE document_id = 2 and result = 5)
) query1)::float
/
(
(SELECT count(*) FROM (
 (SELECT letters FROM genom.elements WHERE document_id = 1 and result = 5)
  UNION ALL
 (SELECT letters FROM genom.elements WHERE document_id = 2 and result = 5)
) query2)::float
-
(SELECT count(*) FROM (
 (SELECT letters FROM genom.elements WHERE document_id = 1 and result = 5)
  UNION
 (SELECT letters FROM genom.elements WHERE document_id = 2 and result = 5)
) query3)::float)
) as result


SELECT ((SELECT count(*) FROM (
 (SELECT letters FROM genom.elements WHERE document_id = 1 and result = 9)
  INTERSECT ALL
 (SELECT letters FROM genom.elements WHERE document_id = 2 and result = 9)
) query1)::float
/
(
(SELECT count(*) FROM (
 (SELECT letters FROM genom.elements WHERE document_id = 1 and result = 9)
  UNION ALL
 (SELECT letters FROM genom.elements WHERE document_id = 2 and result = 9)
) query2)::float
-
(SELECT count(*) FROM (
 (SELECT letters FROM genom.elements WHERE document_id = 1 and result = 9)
  UNION
 (SELECT letters FROM genom.elements WHERE document_id = 2 and result = 9)
) query3)::float)
) as result
