SELECT
    G.GRADE,
    D.ID,
    D.EMAIL
FROM
    DEVELOPERS D
JOIN (
    SELECT
        CASE
            WHEN SKILL_CODE & (
                SELECT CODE FROM SKILLCODES WHERE NAME = 'Python'
            ) = (SELECT CODE FROM SKILLCODES WHERE NAME = 'Python')
            AND SKILL_CODE & (
                SELECT BIT_OR(CODE) FROM SKILLCODES WHERE CATEGORY = 'Front End'
            ) > 0 THEN 'A'

            WHEN SKILL_CODE & (
                SELECT CODE FROM SKILLCODES WHERE NAME = 'C#'
            ) = (SELECT CODE FROM SKILLCODES WHERE NAME = 'C#') THEN 'B'

            WHEN SKILL_CODE & (
                SELECT BIT_OR(CODE) FROM SKILLCODES WHERE CATEGORY = 'Front End'
            ) > 0 THEN 'C'
        END AS GRADE,
        ID
    FROM
        DEVELOPERS
) G ON D.ID = G.ID
WHERE
    G.GRADE IS NOT NULL
ORDER BY
    G.GRADE, D.ID