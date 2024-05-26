select o.ANIMAL_ID, o.NAME
from ANIMAL_INS i join ANIMAL_OUTS o on i.ANIMAL_ID = o.ANIMAL_ID
order by datediff(o.DATETIME, i.DATETIME) desc
limit 2