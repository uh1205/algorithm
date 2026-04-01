select f.id, n.fish_name, f.length
from (
    select
        id,
        fish_type,
        length,
        rank() over(partition by fish_type order by length desc) as rk
    from fish_info
) as f
join fish_name_info n on f.fish_type = n.fish_type
where rk = 1
order by 1