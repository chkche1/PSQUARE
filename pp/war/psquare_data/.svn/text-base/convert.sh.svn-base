ZORBA_ARGS='--indent -f -q'
G12C='g12clean.xml'
G18C='g18clean.xml'
G22C='g22clean.xml'

MYG12='g12.sql'
MYG18='g18.sql'
MYG22='g22.sql'

MY_SERVER='fling.seas.upenn.edu'
MY_USER='sshannin'
MY_PW='lmaonaise'
MY_DB='sshannin'

MY_ARGS="--show-warnings -f -h $MY_SERVER -u $MY_USER --password=$MY_PW $MY_DB"

echo "Converting to common xml format"
echo "g12"
zorba $ZORBA_ARGS group12.xq -o $G12C

echo "g18"
zorba $ZORBA_ARGS group18.xq -o $G18C

echo "g22"
zorba $ZORBA_ARGS group22.xq -o $G22C

echo "flattening into tuples"

echo "g12"
./flatten.py $G12C $MYG12

echo "g18"
./flatten.py $G18C $MYG18

echo "g22"
./flatten.py $G22C $MYG22

echo "Inserting into db"
echo "g12"
mysql $MY_ARGS < $MYG12

echo "g18"
mysql $MY_ARGS < $MYG18

echo "g22"
mysql $MY_ARGS < $MYG22