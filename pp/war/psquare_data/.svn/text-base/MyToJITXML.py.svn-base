#!/usr/bin/python
import MySQLdb as M

MYHOST='fling.seas.upenn.edu'
MYUSER='sshannin'
MYPW='lmaonaise'
MYDB='sshannin'

DB = 'db'
USER = 'user'
ID = 'id'
CID = 'cid'
CIRCLE = 'circle'
FID = 'fid'
FNAME = 'fname'
NAME = 'name'
FRIEND = 'friend'
ADVISOR = 'advisor'
ADVISES = 'advises'

def gen(depth, x, flag = False):
    ret =  depth * '\t'
    ret += '<' + x + '>'
    if flag:
        ret += '\n'
    return ret

def cl(depth, x, flag = False):
    ret = depth * '\t'
    ret +=  '</' + x + '>'
    if flag:
        ret += '\n'
    return ret

def doit(out):
    f = out
    
    f.write(gen(0, DB, True))
    
    conn = M.connect( host = MYHOST,
                      user = MYUSER,
                      passwd = MYPW,
                      db = MYDB)
   
    c = conn.cursor()

    c.execute("SELECT userid, first_name, last_name FROM Users;")

    users = c.fetchall()

    uid_name_map = {}
    
    for user in users:
        uid = user[0]
        fname = user[1]
        lname = user[2]
        
        if fname is None:
            fname = ""

        if lname is None:
            lname = ""
        uid_name_map[uid] = fname + ' ' + lname

    c.execute("SELECT userid, advisorid FROM Student;")

    studs = c.fetchall()

    uid_advisor_map = {}

    for stud in studs:
        studid = stud[0]
        advisorid = stud[1]
        if advisorid is not None:
            #sys.stderr.write("Inserting %d %d into map" % (studid, advisorid))
            uid_advisor_map[studid] = advisorid

    print "Got users and advisor relationships, now building output per user."

    for user in users:
        uid = user[0]
        f.write(gen(1, USER, True))
        f.write(gen(2,ID) + str(uid) + cl(0,ID, True))
        f.write(gen(2,NAME) + uid_name_map[uid] + cl(0, NAME, True))

        for k,v in uid_advisor_map.iteritems():
            if v == uid:
                f.write(gen(2, ADVISES) + str(k) + cl(0, ADVISES, True))

            if k == uid:
                f.write(gen(2,ADVISOR) + str(v) + cl(0, ADVISOR, True))

        c.execute("SELECT cid from Circle where userid=%s;" %uid)
        
        circles = c.fetchall()
        
        for circle in circles:
            f.write(gen(2, CIRCLE, True))
            cid = circle[0]
            f.write(gen(3, CID) + str(cid) + cl(0, CID, True))

            c.execute("SELECT friendid from FriendRelation where cid=%s;" %cid)
            
            friends = c.fetchall()
            
            for friend in friends:
                fid = friend[0]
                
                f.write(gen(3,FRIEND, True))
                f.write(gen(4, FID) + str(fid) + cl(0, FID, True))
                f.write(gen(4, FNAME) + uid_name_map[fid] + cl(0, FNAME, True))
                f.write(cl(3, FRIEND, True))
            f.write(cl(2, CIRCLE, True))

        f.write(cl(1,USER,True))
    f.write(cl(0,DB, True))

if __name__ == '__main__':
    import sys

    if(len(sys.argv) != 2):
        exit(doit(sys.stdout))
    else:
        import tempfile, os
        handle = tempfile.NamedTemporaryFile(mode = 'w', delete = False)
        name = handle.name
        doit(handle)
        exit(os.rename(name, sys.argv[1]))
