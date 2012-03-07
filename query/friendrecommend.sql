select R.userid, R.score 
from Rating R, 
(
	select pid as same_photo
	from Rating
	where userid = -- userid given in gwt
) Common
where R.pid=Common.same_photo
