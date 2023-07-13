-- 내풀이
select sub.hacker_id, h.name, sub.challenges_cnt
from (
    select hacker_id, count(*) as challenges_cnt
    from challenges 
    group by hacker_id
) sub
join hackers h on h.hacker_id = sub.hacker_id
where sub.challenges_cnt not in (
    select challenges_cnt
    from (
        select challenges_cnt, count(*) as challenges_cnt_cnt
        from (
            select hacker_id, count(*) as challenges_cnt
            from challenges 
            group by hacker_id
        ) as cs
        group by challenges_cnt
        having challenges_cnt_cnt != 1
        and challenges_cnt != (
            select max(challenges_cnt)
            from (
                select hacker_id, count(*) as challenges_cnt
                from challenges 
                group by hacker_id
            ) as h_cs
        )
    ) as temp
)
order by sub.challenges_cnt desc, sub.hacker_id asc;

-- 풀이 1-1
-- group의 challenges_created 에 관한 것이므로 having 절에 조건을 추가하겠다.
select hackers.hacker_id
     , hackers.name
	 , count(*) challenges_created
from challenges
	inner join hackers on challenges.hacker_id = hackers.hacker_id
group by hackers.hacker_id, hackers.name
order by challenges_created desc, hacker_id

-- 풀이 1-2
-- 중복된 연산이 3번이나 들어간다.
select hackers.hacker_id
     , hackers.name
     , count(*) challenges_created
from challenges
join hackers on challenges.hacker_id = hackers.hacker_id
group by hackers.hacker_id, hackers.name
having challenges_created = (
    select max(challenges_created)
    from (
        select hacker_id, count(*) as challenges_created
        from challenges 
        group by hacker_id
    ) sub)
    or challenges_created in (
    select challenges_created
    from (
        select hacker_id, count(*) as challenges_created
        from challenges 
        group by hacker_id
    ) sub
    group by challenges_created
    having count(*) = 1)
order by challenges_created desc, hacker_id

-- 풀이 2 (with 사용)
-- 같은 연산이 여러번 반복될 때 사용하기 좋다.
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