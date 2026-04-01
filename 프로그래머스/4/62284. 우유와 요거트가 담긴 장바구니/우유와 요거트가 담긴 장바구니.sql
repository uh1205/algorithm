select cart_id
from (
    select distinct cart_id, name
    from cart_products
    where name in ('Milk', 'Yogurt')
) as a
group by cart_id
having count(*) >= 2
order by cart_id