select CATEGORY, sum(SALES) as TOTAL_SALES
from BOOK b inner join BOOK_SALES s on b.BOOK_ID = s.BOOK_ID
where SALES_DATE like '2022-01%'
group by 1
order by 1