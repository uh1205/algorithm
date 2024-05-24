select PRODUCT_CODE, sum(PRICE * SALES_AMOUNT) as SALES
from OFFLINE_SALE o join PRODUCT p on o.PRODUCT_ID = p.PRODUCT_ID
group by 1
order by 2 desc, 1