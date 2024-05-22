select NAME, DATETIME
from ANIMAL_INS i
where i.ANIMAL_ID not in (select ANIMAL_ID from ANIMAL_OUTS)
order by 2
limit 3