CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
BEGIN
  RETURN (
      # Write your MySQL query statement below.
      SELECT r.salary
      FROM (
          SELECT DISTINCT salary, DENSE_RANK() OVER (ORDER BY salary DESC) AS salary_rank
          FROM Employee
      ) r
      WHERE r.salary_rank = N
  );
END