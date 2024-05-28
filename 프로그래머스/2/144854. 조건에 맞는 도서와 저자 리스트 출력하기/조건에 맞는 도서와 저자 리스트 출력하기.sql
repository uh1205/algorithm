select BOOK_ID, AUTHOR_NAME,
    date_format(PUBLISHED_DATE,'%Y-%m-%d') as PUBLISHED_DATE
from BOOK b join AUTHOR a on b.AUTHOR_ID = a.AUTHOR_ID
where b.CATEGORY like '경제'
order by 3