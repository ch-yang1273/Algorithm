SELECT d.name AS Department, e.name AS Employee, m.max AS Salary
FROM (SELECT departmentId, MAX(salary) AS max
    FROM Employee
    GROUP BY departmentId) m
RIGHT JOIN Employee e ON m.departmentId = e.departmentId
JOIN Department d on m.departmentId = d.id
WHERE e.salary = m.max;

