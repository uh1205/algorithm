select
    i.REST_ID,
    i.REST_NAME,
    i.FOOD_TYPE,
    i.FAVORITES,
    i.ADDRESS,
    s.SCORE
from REST_INFO i
join (
    select i.REST_ID, round(avg(r.REVIEW_SCORE), 2) as SCORE
    from REST_INFO i
    join REST_REVIEW r on i.REST_ID = r.REST_ID
    group by i.REST_ID
) s on i.REST_ID = s.REST_ID
where i.ADDRESS like '서울%'
order by s.SCORE desc, i.FAVORITES desc