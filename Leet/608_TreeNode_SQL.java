select id,
case
   when tree.p_id is null
       then 'Root’
   when tree.id  in (
       select distinct  p_id from tree)
       then 'Inner’
   else 'Leaf’
end as Type
from tree
