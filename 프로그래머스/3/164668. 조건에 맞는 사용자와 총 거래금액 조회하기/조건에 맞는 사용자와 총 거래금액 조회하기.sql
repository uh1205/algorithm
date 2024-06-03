select *
from
(
select USER_ID, NICKNAME, sum(PRICE) as TOTAL_SALES
from USED_GOODS_BOARD b join USED_GOODS_USER u on b.WRITER_ID = u.USER_ID
where STATUS = 'DONE'
group by 1
) a
where TOTAL_SALES >= 700000
order by 3