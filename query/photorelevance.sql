select AVG(score) as relevance
from Rating
group by pid
order by relevance desc
