-- 내 풀이
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

-- 다른 풀이 1 (CASE)
CREATE FUNCTION getNthHighestSalary(N INT)
RETURNS INT
BEGIN
  RETURN (
    SELECT CASE WHEN COUNT(sub.salary) < N THEN NULL
                ELSE MIN(sub.salary)
           END
    FROM (
        SELECT DISTINCT salary
        FROM Employee
        ORDER BY salary DESC
        LIMIT N
    ) sub
  );
END

-- 다른 풀이 2 (IF)
CREATE FUNCTION getNthHighestSalary(N INT)
RETURNS INT
BEGIN
  RETURN (
    SELECT IF(COUNT(sub.salary) < N, NULL, MIN(sub.salary))
    FROM (
        SELECT DISTINCT salary
        FROM Employee
        ORDER BY salary DESC
        LIMIT N
    ) sub
  );
END

-- 다른 풀이 3 (서브쿼리 없이)
CREATE FUNCTION getNthHighestSalary(N INT)
RETURNS INT
BEGIN
  DECLARE A INT;
  SET A = N - 1;
  RETURN (
    SELECT DISTINCT salary
    FROM Employee
    ORDER BY salary DESC
    -- LIMIT N-1, 1 (N을 바로 수정할 수는 없다)
    -- LIMIT A, 1
    LIMIT 1 OFFSET A
  );
END