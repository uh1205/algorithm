select 
    p.PRODUCT_ID, 
    PRODUCT_NAME, 
    (PRICE * AMOUNT) as TOTAL_SALES
from
    FOOD_PRODUCT p 
    join (
        select PRODUCT_ID, sum(AMOUNT) as AMOUNT
        from FOOD_ORDER
        where PRODUCE_DATE like '2022-05%'
        group by PRODUCT_ID
    ) o 
    on p.PRODUCT_ID = o.PRODUCT_ID
order by 3 desc, p.PRODUCT_ID