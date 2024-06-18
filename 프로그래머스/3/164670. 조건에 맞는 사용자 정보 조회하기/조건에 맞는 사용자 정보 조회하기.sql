SELECT 
    USER_ID,
    NICKNAME,
    CONCAT_WS(' ', CITY, STREET_ADDRESS1, IFNULL(STREET_ADDRESS2, '')) AS 전체주소,
	CONCAT_WS('-', LEFT(TLNO, 3), SUBSTRING(TLNO, 4, 4), RIGHT(TLNO, 4)) AS 전화번호
FROM USED_GOODS_USER U
JOIN 
(
    SELECT WRITER_ID
    FROM USED_GOODS_BOARD
    GROUP BY WRITER_ID
    HAVING COUNT(*) >= 3
) B
ON U.USER_ID = B.WRITER_ID
ORDER BY USER_ID DESC