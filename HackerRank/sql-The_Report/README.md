[The Report](https://www.hackerrank.com/challenges/the-report/problem)

- CASE 절 : CASE WHEN ~ THEN ~ ELSE ~ END
- 특이한 JOIN 문

Name, Grade and Mark.

Grade 8 미만의 학생은 null 표시
등급 별 내림차순, 학생 이름순
이름이 null 인 경우 성적 오름차순

```sql
SELECT CASE WHEN G.GRADE < 8 THEN NULL ELSE S.NAME END
     , G.GRADE
     , S.MARKS
FROM STUDENTS S
   JOIN GRADES G ON S.MARKS BETWEEN G.MIN_MARK AND G.MAX_MARK
ORDER BY G.GRADE DESC, S.NAME, S.MARKS
```