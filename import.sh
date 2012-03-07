MY_SERVER='fling.seas.upenn.edu'
MY_USER='sshannin'
MY_PW='lmaonaise'
MY_DB='sshannin'

MY_ARGS="--show-warnings -h $MY_SERVER -u $MY_USER --password=$MY_PW $MY_DB"

echo "Clearing old schema"
echo "Adding tables"
mysql $MY_ARGS < m1.sql

echo "Addiing our data"
mysql $MY_ARGS < data.sql

cd pp/war/psquare_data && ./convert.sh