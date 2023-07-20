SELECT b.name AS Department, a.name AS Employee, a.salary AS Salary
FROM (
    SELECT *, DENSE_RANK() OVER (PARTITION BY departmentId ORDER BY salary DESC) AS num
    FROM Employee
) a
JOIN Department b ON a.departmentId = b.id
WHERE a.num <= 3
