> [https://sqlzoo.net/](https://sqlzoo.net/)

# [SELECT_basics](https://sqlzoo.net/wiki/SELECT_basics)

## 1
```sql
SELECT population FROM world
  WHERE name = 'Germany'
```

## 2
```sql
SELECT name, population FROM world
  WHERE name IN ('Sweden', 'Norway', 'Denmark');
```

## 3
```sql
SELECT name, area FROM world
  WHERE area BETWEEN 200000 AND 250000
```

# [SELECT_names](https://sqlzoo.net/wiki/SELECT_names)

## 1
```sql
SELECT name FROM world
  WHERE name LIKE 'Y%'
```

## 2
```sql
SELECT name FROM world
  WHERE name LIKE '%Y'
```

## 3
```sql
SELECT name FROM world
  WHERE name LIKE '%x%'
```

## 4
```sql
SELECT name FROM world
  WHERE name LIKE '%land'
```

## 5
```sql
SELECT name FROM world
  WHERE name LIKE 'C%ia'
```

## 6
```sql
SELECT name FROM world
  WHERE name LIKE '%oo%'
```

## 7
```sql
SELECT name FROM world
  WHERE name LIKE '%a%a%a%'
```

## 8
```sql
SELECT name FROM world
 WHERE name LIKE '_t%'
ORDER BY name
```

## 9
```sql
SELECT name FROM world
 WHERE name LIKE '%o__o%'
```

## 10
```sql
SELECT name FROM world
 WHERE name LIKE '%o__o%'
```

## 11
```sql
SELECT name
  FROM world
 WHERE name = capital
```

## 12
```sql
SELECT name
  FROM world
 WHERE capital LIKE concat(name, ' City')
```

## 13
```sql
SELECT capital, name
  FROM world
WHERE capital LIKE concat('%', name, '%')
```

## 14
```sql
SELECT capital, name
  FROM world
WHERE capital LIKE concat(name, '_%')
```

oder

```sql
SELECT capital, name
  FROM world
WHERE length(capital) > length(name)
  AND capital LIKE concat(name, '%')
```

## 15
```sql
SELECT name, replace(capital, name, '')
  FROM world
WHERE capital LIKE concat(name, '_%')
```

# [SELECT_from_WORLD_Tutorial](https://sqlzoo.net/wiki/SELECT_from_WORLD_Tutorial)

## 1
```sql
SELECT name, continent, population FROM world
```

## 2
```sql
SELECT name FROM world
  WHERE population > 200000000
```

## 3
```sql
SELECT name, GDP/population
 FROM world
WHERE population > 200000000
```

## 4
```sql
SELECT name, population/1000000 
  FROM world
WHERE continent = 'South America'
```

## 5
```sql
SELECT name, GDP/population
 FROM world
WHERE population > 200000000
```

## 6
```sql
SELECT name, population
  FROM world
WHERE name IN ('France', 'Germany', 'Italy')
```

## 7
```sql
SELECT name, population, area
  FROM world
WHERE area > 3000000 OR population > 250000000
```

## 8
```sql
SELECT name, population, area
  FROM world
WHERE area > 3000000 XOR population > 250000000
```

## 9
```sql
SELECT name, round(population / 1000000, 2) , round(GDP / 1000000000, 2)
  FROM world
WHERE continent = 'South America'
```

## 10
```sql
SELECT name, round(GDP/population, -3)
  FROM world
WHERE GDP > 1000000000000
```

## 11
```sql
SELECT name, capital
  FROM world
 WHERE length(name) = length(capital)
```

## 12
```sql
SELECT name, capital
  FROM world
WHERE LEFT(name, 1) = LEFT(capital, 1) AND name != capital
```

## 13
```sql
SELECT name
  FROM world
WHERE name LIKE '%a%'
  AND name LIKE '%e%'
  AND name LIKE '%i%'
  AND name LIKE '%o%'
  AND name LIKE '%u%'
  AND name NOT LIKE '% %'
```

# [SELECT_from_Nobel_Tutorial](https://sqlzoo.net/wiki/SELECT_from_Nobel_Tutorial)

## 1
```sql
SELECT yr, subject, winner
  FROM nobel
WHERE yr = 1950
```

## 2
```sql
SELECT winner
  FROM nobel
WHERE yr = 1962
  AND subject = 'Literature'
```

## 3
```sql
SELECT yr, subject
  FROM nobel
WHERE winner = 'Albert Einstein'
```

## 4
```sql
SELECT winner
  FROM nobel
WHERE yr >= 2000
  AND subject = 'Peace'
```

## 5
```sql
SELECT yr, subject, winner
  FROM nobel
WHERE subject = 'Literature'
  AND yr >= 1980
  AND yr <= 1989
```

## 6
```sql
SELECT * FROM nobel
WHERE winner IN (
  'Theodore Roosevelt',
  'Woodrow Wilson',
  'Barack Obama',
  'Jimmy Carter'
)
```

## 7
```sql
SELECT winner
  FROM nobel
WHERE winner LIKE 'John %'
```

## 8
```sql
SELECT yr, subject, winner
  FROM nobel
WHERE (
  yr = 1980
  AND subject = 'Physics'
) OR (
  yr = 1984
  AND subject = 'Chemistry'
)
```

## 9
```sql
SELECT yr, subject, winner
  FROM nobel
WHERE yr = 1980
  AND subject NOT IN ('Chemistry', 'Medicine')
```

## 10
```sql
SELECT yr, subject, winner
  FROM nobel
WHERE (
  yr < 1910
  AND subject = 'Medicine'
) OR (
  yr >= 2004
  AND subject = 'Literature'
)
```

## 11
```sql
SELECT * FROM nobel
WHERE winner = 'Peter Grünberg'
```

## 12
```sql
SELECT * FROM nobel
WHERE winner = 'Eugene O\'Neill'
```

## 13
```sql
SELECT winner, yr, subject
  FROM nobel
WHERE winner LIKE 'Sir %'
ORDER BY yr DESC, subject
```

## 14
```sql
SELECT winner, subject
  FROM nobel
 WHERE yr=1984
 ORDER BY subject IN ('Physics','Chemistry'), subject, winner
```

# [SELECT_within_SELECT_Tutorial](https://sqlzoo.net/wiki/SELECT_within_SELECT_Tutorial)

## 1
```sql
SELECT name FROM world
  WHERE population >
     (SELECT population FROM world
      WHERE name='Russia')
```

## 2
```sql
SELECT name FROM world
WHERE GDP/population > (
  SELECT GDP/population FROM world
    WHERE name = 'United Kingdom'
)
  AND continent = 'Europe'
```

## 3
```sql
SELECT name, continent FROM world
WHERE continent IN (
  SELECT continent FROM world
  WHERE name IN ('Argentina', 'Australia')
)
ORDER BY name
```

## 4
```sql
SELECT name, population FROM world
WHERE  population > (SELECT population FROM world WHERE name = 'Canada')
  AND population < (SELECT population FROM world WHERE name = 'Poland')
```

## 5
```sql
SELECT
  name,
  CONCAT(
    ROUND(population / (SELECT population FROM world WHERE name = 'Germany') * 100
    ),
    '%'
   )
FROM world
WHERE continent = 'Europe'
```

## 6
```sql
SELECT name FROM world
WHERE gdp > ALL (
  SELECT gdp FROM world
  WHERE continent = 'Europe'
  AND gdp > 0
)
```

## 7
```sql
SELECT continent, name, area FROM world x
  WHERE area >= ALL
    (SELECT area FROM world y
        WHERE y.continent = x.continent
          AND population > 0)
```

## 8
```sql
SELECT continent, name FROM world a
  WHERE name <= ALL (
    SELECT name FROM world b
    WHERE a.continent = b.continent
    ORDER BY name
  )
```

## 9
```sql
SELECT name, continent, population FROM world a
WHERE  (
  SELECT COUNT(name) FROM world b
  WHERE a.continent = b.continent
) = (
  SELECT COUNT(name) FROM world b
  WHERE a.continent = b.continent
  AND b.population <= 25000000
)
```

## 10
```sql
SELECT name, continent FROM world a
WHERE population > ALL (
  SELECT population * 3 FROM world b
  WHERE a.continent = b.continent
  AND population > 0
  AND a.name != b.name
)
```

# [SUM_and_COUNT](https://sqlzoo.net/wiki/SUM_and_COUNT)

## 1
```sql
SELECT SUM(population)
FROM world
```

## 2
```sql
SELECT DISTINCT continent FROM world
```

## 3
```sql
SELECT SUM(gdp) FROM world
WHERE continent = 'Africa'
```

## 4
```sql
SELECT COUNT(name) FROM world
WHERE area >= 1000000
```

## 5
```sql
SELECT SUM(population) FROM world
WHERE name IN ('Estonia', 'Latvia', 'Lithuania')
```

## 6
```sql
SELECT continent, COUNT(name) FROM world
GROUP BY continent
```

## 7
```sql
SELECT continent, COUNT(name) FROM world
WHERE population > 10000000
GROUP BY continent
```

## 8
```sql
SELECT DISTINCT continent FROM world a
WHERE  100000000 < (
  SELECT SUM(population) FROM world b
  WHERE a.continent = b.continent
)
```

# [The_JOIN_operation](https://sqlzoo.net/wiki/The_JOIN_operation)

## 1
```sql
SELECT matchid, player FROM goal 
  WHERE teamid = 'GER'
```

## 2
```sql
SELECT id,stadium,team1,team2
  FROM game
WHERE id = 1012
```

## 3
```sql
SELECT player, teamid, stadium, mdate FROM game
JOIN goal ON (game.id = goal.matchid)
WHERE goal.teamid = 'GER'
```

## 4
```sql
SELECT team1, team2, player FROM game
JOIN goal ON (goal.matchid = game.id)
WHERE player LIKE 'Mario %'
```

## 5
```sql
SELECT player, teamid, coach, gtime
  FROM goal 
JOIN eteam ON (goal.teamid = eteam.id)
WHERE gtime<=10
```

## 6
```sql
SELECT mdate, teamname FROM game
JOIN eteam ON (game.team1 = eteam.id)
WHERE eteam.coach = 'Fernando Santos'
```

## 7
```sql
SELECT player FROM goal
JOIN game ON (goal.matchid = game.id)
WHERE game.stadium = 'National Stadium, Warsaw'
```

## 8
```sql
SELECT DISTINCT player FROM game
JOIN goal ON goal.matchid = game.id
WHERE (game.team1='GER' OR game.team2='GER')
AND goal.teamid != 'GER'
```

## 9
```sql
SELECT teamname, COUNT(teamname)
  FROM eteam JOIN goal ON id=teamid
 GROUP BY teamname
```

## 10
```sql
SELECT stadium, COUNT(stadium) FROM game
JOIN goal ON goal.matchid = game.id
GROUP BY game.stadium
```

## 11
```sql
SELECT matchid, mdate, COUNT(matchid) FROM goal
JOIN game ON goal.matchid = game.id
WHERE (team1 = 'POL' OR team2 = 'POL')
GROUP BY matchid, mdate
```

## 12
```sql
SELECT matchid, mdate, COUNT(matchid) FROM goal
JOIN game ON game.id = goal.matchid
WHERE goal.teamid = 'GER'
GROUP BY matchid, mdate
```

## 13
```sql
SELECT mdate,
  team1,
  SUM(CASE WHEN team1 = teamid THEN 1 ELSE 0 END) as score1,
  team2,
  SUM(CASE WHEN team2 = teamid THEN 1 ELSE 0 END) as score2
FROM game
LEFT JOIN goal ON game.id = goal.matchid
GROUP BY  mdate, team1, team2
```
> Korrekt, weiß aber nicht wieso... Left Join?

# [More_JOIN_operations](https://sqlzoo.net/wiki/More_JOIN_operations)

## 1
```sql
SELECT id, title
FROM movie
WHERE yr=1962
```

## 2
```sql
SELECT yr FROM movie
WHERE title = 'Citizen Kane'
```

## 3
```sql
SELECT id, title, yr FROM movie
WHERE title LIKE '%Star Trek%'
ORDER BY yr
```

## 4
```sql
SELECT id FROM actor
WHERE name = 'Glenn Close'
```

## 5
```sql
SELECT id FROM movie
WHERE title = 'Casablanca'
```

## 6
```sql
SELECT name FROM casting
JOIN actor ON casting.actorid = actor.id
WHERE movieid = 11768
```

## 7
```sql
SELECT name FROM movie
JOIN casting ON casting.movieid = movie.id 
JOIN actor ON casting.actorid = actor.id
WHERE movie.title = 'Alien'
```

## 8
```sql
SELECT DISTINCT title FROM movie
JOIN casting ON casting.movieid = movie.id 
JOIN actor ON casting.actorid = actor.id
WHERE actor.name = 'Harrison Ford'
```

## 9
```sql
SELECT DISTINCT title FROM movie
JOIN casting ON casting.movieid = movie.id 
JOIN actor ON casting.actorid = actor.id
WHERE actor.name = 'Harrison Ford'
AND casting.ord != 1
```

## 10
```sql
SELECT DISTINCT title, name FROM movie
JOIN casting ON casting.movieid = movie.id 
JOIN actor ON casting.actorid = actor.id
WHERE movie.yr = 1962
AND casting.ord = 1
```

## 11
```sql
SELECT yr,COUNT(title) FROM movie
JOIN casting ON movie.id=movieid
JOIN actor   ON actorid=actor.id
WHERE name='John Travolta'
GROUP BY yr
HAVING COUNT(title)=(
  SELECT MAX(c) FROM (
    SELECT yr, COUNT(title) AS c FROM movie
    JOIN casting ON movie.id=movieid
    JOIN actor   ON actorid=actor.id
    WHERE name='John Travolta'
     GROUP BY yr
  ) AS t
)
```

## 12
```sql
SELECT title, name FROM movie
JOIN casting ON casting.movieid = movie.id
JOIN actor ON casting.actorid = actor.id
WHERE casting.ord = 1
AND casting.movieid IN (
  SELECT movie.id FROM movie
  JOIN casting ON casting.movieid = movie.id
  JOIN actor ON casting.actorid = actor.id
  WHERE actor.name = 'Julie Andrews'
)
```

## 13
```sql
SELECT name FROM actor
WHERE 30 <= (
  SELECT COUNT(*) FROM casting
  WHERE actor.id = casting.actorid
  AND casting.ord = 1
)
ORDER BY name
```

## 14
```sql
SELECT title, (
  SELECT COUNT(*) FROM casting
  WHERE casting.movieid = movie.id
) AS count FROM movie
WHERE yr = '1978'
ORDER BY count DESC, title
```

## 15
```sql
SELECT DISTINCT name FROM actor
JOIN casting a ON a.actorid = actor.id
WHERE 0 < (
  SELECT COUNT(*) FROM casting b
  WHERE a.movieid =  b.movieid
  AND b.movieid IN (
    SELECT DISTINCT movie.id FROM movie
    JOIN casting ON casting.movieid = movie.id
    JOIN actor ON casting.actorid = actor.id
    WHERE actor.name = 'Art Garfunkel'
  )
)
AND actor.name != 'Art Garfunkel'
```

# [Using_Null](https://sqlzoo.net/wiki/Using_Null)

## 1
```sql
SELECT name FROM teacher
WHERE dept IS NULL
```

## 2
```sql
SELECT teacher.name, dept.name 
  FROM teacher
INNER JOIN dept ON (teacher.dept=dept.id)
```

## 3
```sql
SELECT teacher.name, dept.name FROM teacher
LEFT JOIN dept ON teacher.dept = dept.id
```

## 4
```sql
SELECT teacher.name, dept.name FROM teacher
RIGHT JOIN dept ON teacher.dept = dept.id
```

## 5
```sql
SELECT name, COALESCE(mobile, '07986 444 2266') FROM teacher
```

## 6
```sql
SELECT teacher.name, COALESCE(dept.name, 'None') FROM teacher
LEFT JOIN dept ON dept.id = teacher.dept
```

## 7
```sql
SELECT COUNT(name), COUNT(DISTINCT mobile) FROM teacher
```

## 8
```sql
SELECT dept.name, COUNT(teacher.id) FROM teacher
RIGHT JOIN dept ON dept.id = teacher.dept
GROUP BY dept.name
```

## 9
```sql
SELECT 
  teacher.name,
  CASE WHEN dept.id IN (1, 2) THEN 'Sci' ELSE 'Art' END
FROM teacher
LEFT JOIN dept ON teacher.dept = dept.id
```
> Aufgabenstellung verlangt eine Zeile (name + "Sci" | "Art"), Lösung sind zwei Zeilen

## 10
```sql
SELECT 
  teacher.name,
  CASE WHEN dept.id IN (1, 2) THEN 'Sci'
  ELSE CASE WHEN dept.id = 3 THEN 'Art' ELSE 'None' END
  END
FROM teacher
LEFT JOIN dept ON teacher.dept = dept.id
```

> Siehe 9.

# [Self_join](https://sqlzoo.net/wiki/Self_join)

## 1
```sql
SELECT COUNT(*) FROM stops
```

## 2
```sql
SELECT id FROM stops
WHERE name = 'Craiglockhart'
```

## 3
```sql
SELECT id, name FROM stops
JOIN route ON stops.id = route.stop
WHERE route.num = 4
AND route.company = 'LRT'
```

## 4
```sql
SELECT company, num, COUNT(*)
FROM route WHERE stop=149 OR stop=53
GROUP BY company, num
HAVING COUNT(*) = 2
```

## 5
```sql
SELECT a.company, a.num, a.stop, b.stop FROM route a
JOIN route b ON (a.company=b.company AND a.num=b.num)
WHERE a.stop=53 AND b.stop=(SELECT id FROM stops WHERE name = 'London Road')
```

## 6
```sql
SELECT a.company, a.num, stopa.name, stopb.name
FROM route a JOIN route b ON
  (a.company=b.company AND a.num=b.num)
  JOIN stops stopa ON (a.stop=stopa.id)
  JOIN stops stopb ON (b.stop=stopb.id)
WHERE stopa.name='Craiglockhart'
AND stopb.name = 'London Road'
```

## 7
```sql
SELECT DISTINCT a.company, a.num FROM route a
JOIN route b ON  (a.company=b.company AND a.num=b.num)
JOIN stops stopa ON (a.stop=stopa.id)
JOIN stops stopb ON (b.stop=stopb.id)
WHERE stopa.id = 115
AND  stopb.id = 137
```

## 8
```sql
SELECT a.company, a.num FROM route a
JOIN route b ON a.company = b.company AND a.num = b.num
JOIN stops stopa ON a.stop = stopa.id
JOIN stops stopb ON b.stop = stopb.id
WHERE stopa.name = 'Craiglockhart'
AND stopb.name = 'Tollcross'
```

## 9
```sql
SELECT stopa.name, a.company, a.num
FROM route a
  JOIN route b ON (a.num=b.num AND a.company=b.company)
  JOIN stops stopa ON (a.stop=stopa.id)
  JOIN stops stopb ON (b.stop=stopb.id)
WHERE stopb.name = 'Craiglockhart' AND a.company = 'LRT'
```

## 10
```sql
SELECT DISTINCT a.num, a.company, stopb.name ,  c.num,  c.company
FROM route a JOIN route b
ON (a.company = b.company AND a.num = b.num)
JOIN ( route c JOIN route d ON (c.company = d.company AND c.num= d.num))
JOIN stops stopa ON (a.stop = stopa.id)
JOIN stops stopb ON (b.stop = stopb.id)
JOIN stops stopc ON (c.stop = stopc.id)
JOIN stops stopd ON (d.stop = stopd.id)
WHERE  stopa.name = 'Craiglockhart' AND stopd.name = 'Sighthill'
            AND  stopb.name = stopc.name
ORDER BY LENGTH(a.num), b.num, stopb.id, LENGTH(c.num), d.num
```