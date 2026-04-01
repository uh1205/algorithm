select id, name, host_id
from (
    select *, count(*) over (partition by host_id) as count
    from places
) a
where count >= 2
order by id