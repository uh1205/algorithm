select substring(PRODUCT_CODE, 1, 2) CATEGORY, count(*) PRODUCTS
from PRODUCT
group by 1
order by 1