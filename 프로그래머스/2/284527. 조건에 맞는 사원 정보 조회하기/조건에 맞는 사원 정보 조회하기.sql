SELECT
    SCORE,
    EMP_NO,
    EMP_NAME,
    POSITION,
    EMAIL
FROM (
    SELECT
        RANK() OVER (ORDER BY SUM(G.SCORE) DESC) AS RK,
        SUM(G.SCORE) AS SCORE,
        E.EMP_NO,
        E.EMP_NAME,
        E.POSITION,
        E.EMAIL
    FROM
        HR_EMPLOYEES E
    JOIN
        HR_GRADE G ON E.EMP_NO = G.EMP_NO
    WHERE
        G.YEAR = 2022
    GROUP BY
        E.EMP_NO
) A
WHERE RK = 1