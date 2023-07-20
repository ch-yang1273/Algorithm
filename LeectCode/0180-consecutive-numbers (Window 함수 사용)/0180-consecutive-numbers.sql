SELECT DISTINCT b.num as ConsecutiveNums
FROM (
    SELECT *, LEAD(a.num) OVER (ORDER BY a.id) as num2, LEAD(a.num, 2) OVER (ORDER BY a.id) as num3
    FROM Logs a
) b
WHERE b.num = b.num2 AND b.num = b.num3