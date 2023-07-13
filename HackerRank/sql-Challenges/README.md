[Challenges](https://www.hackerrank.com/challenges/challenges/problem?h_r=profile)

- 서브쿼리가 많이 들어가는 문제였다.
- WITH 절을 사용하면 쿼리 내에서 여러 번 참조될 수 있는 연산의 결과를 한 번만 정의할 수 있다.
- WITH 절을 사용하면, 쿼리로 만든 임시적인 데이터 세트인 CTE(Common Table Expressions)를 만들 수 있다.

```sql
WITH counter AS (
	SELECT Hackers.hacker_id
	     , Hackers.name
	     , COUNT(*) AS challenges_created
	FROM challenges
		INNER JOIN Hackers ON Challenges.hacker_id = Hackers.hacker_id
	GROUP BY Hackers.hacker_id, Hackers.name
)

SELECT counter.hacker_id
     , counter.name
	 , counter.challenges_created
FROM counter
WHERE challenges_created = (SELECT MAX(challenges_created) FROM counter)
OR challenges_created IN (SELECT challenges_created
                          FROM counter
						  GROUP BY challenges_created
						  HAVING COUNT(*) = 1)
ORDER BY counter.challenges_created DESC, counter.hacker_id
```