select CATEGORY, PRICE as MAX_PRICE, PRODUCT_NAME
from FOOD_PRODUCT
where (CATEGORY, PRICE) in 
(
    select CATEGORY, max(PRICE)
    from FOOD_PRODUCT
    where CATEGORY in ('과자', '국', '김치', '식용유')
    group by 1
)
order by 2 desc