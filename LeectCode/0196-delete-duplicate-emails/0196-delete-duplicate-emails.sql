# Please write a DELETE statement and DO NOT write a SELECT statement.
# Write your MySQL query statement below
delete from Person
where id not in (
  select temp.id
  from (
    select email, MIN(id) as id
    from Person
    group by email
  ) as temp
)
;