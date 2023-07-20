SELECT b.name AS Department, a.name AS Employee, a.salary AS Salary
FROM (
    SELECT *, MAX(salary) OVER (PARTITION BY departmentId) AS max_salary
    FROM Employee
) a
JOIN Department b ON a.departmentId = b.id
WHERE a.salary = max_salary