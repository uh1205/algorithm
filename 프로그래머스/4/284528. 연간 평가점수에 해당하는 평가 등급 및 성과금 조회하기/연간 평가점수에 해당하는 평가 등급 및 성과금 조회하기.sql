SELECT
    E.EMP_NO,
    E.EMP_NAME,
    G.GRADE,
    CASE
        WHEN G.GRADE = 'S' THEN E.SAL * 0.2
        WHEN G.GRADE = 'A' THEN E.SAL * 0.15
        WHEN G.GRADE = 'B' THEN E.SAL * 0.1
        ELSE 0
    END AS BONUS
FROM (
    SELECT
        EMP_NO,
        CASE
            WHEN AVG(SCORE) >= 96 THEN 'S'
            WHEN AVG(SCORE) >= 90 THEN 'A'
            WHEN AVG(SCORE) >= 80 THEN 'B'
            ELSE 'C'
        END AS GRADE
    FROM HR_GRADE
    GROUP BY EMP_NO
) AS G
JOIN
    HR_EMPLOYEES E ON G.EMP_NO = E.EMP_NO
ORDER BY
    E.EMP_NO