

select P.pid, P.url
from Rating R, Photo P, UserPrivacy U
where R.comments REGEXP '*keyword*' and R.pid = P.pid and R.pid= U.pid and U.userid = -- this userid is the user who searches this photo

-- we should use pid to retrieve all/average scores and all comments

select R.comments 
from Rating R
where R.pid= --pid from last query

select AVG(score)
from Rating
where pid= -- pid from last query
group by pid
