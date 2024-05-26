select o.ANIMAL_ID, o.NAME
from ANIMAL_INS i join ANIMAL_OUTS o on i.ANIMAL_ID = o.ANIMAL_ID
order by timestampdiff(second, i.DATETIME, o.DATETIME) desc
limit 2