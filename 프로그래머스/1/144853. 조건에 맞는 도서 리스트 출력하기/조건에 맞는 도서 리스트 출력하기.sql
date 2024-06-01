select BOOK_ID, date_format(PUBLISHED_DATE, '%Y-%m-%d')
from BOOK
where PUBLISHED_DATE like '2021%' and CATEGORY like '인문'
order by 2