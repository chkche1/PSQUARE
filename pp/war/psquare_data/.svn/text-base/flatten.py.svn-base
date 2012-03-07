#!/usr/bin/python

from xml.dom.minidom import parse, parseString

USER_PREFIX = "insert into Users(userid, first_name, last_name, dob, email, gender, address, password) values "

STUD_PREFIX = "insert into Student(userid, advisorid, year) values "

PROF_PREFIX = "insert into Professor(userid, research_area, room_id) values "

CIRCLE_PREFIX = "insert into Circle(cid, userid, name) values "

FRIEND_PREFIX = "insert into FriendRelation(friendid, cid) values "

INTEREST_PREFIX = "insert into Interest(userid, interest) values "

SCHOOL_PREFIX = "insert into AcademicInstitution(userid, institution) values "

def gen_prefix(table, fields):
    ret = []
    ret.append("insert into ")
    ret.append(table)
    ret.append('(')
    ret.append(','.join(fields))
    ret.append(') values ')
    return '' .join(ret)

def writeAll(dom, prefix, tag, f):
    f.write("\n#%s\n" % tag)

    for node in dom:
        f.write("%s (" %prefix)
        f.write(flattenxml(node))
        f.write("); \n")

def flattenxml(dom):    
    rc = []
    for node in dom.childNodes:
        if(node.nodeType != node.TEXT_NODE):
            try:
                child = node.childNodes[0]
                if node.tagName == 'BDAY': #convert formats
                    splits = child.data.split('/')
                    rc.append("'" + splits[2] + '-' + splits[0] + '-'+ splits[1] + "'")
                else:
                    rc.append("'" + child.data + "'")
            except IndexError:
                #print rc
                #print node
                rc.append('null')
    return ','.join(rc)

def fUsers(dom, f):
    writeAll(dom, USER_PREFIX, "USERS", f)

def fStuds(dom, f):
    writeAll(dom, STUD_PREFIX, "STUDENTS", f)

def fProfs(dom, f):
    writeAll(dom, PROF_PREFIX, "PROFESSORS", f)

def fCircles(dom, f):
    writeAll(dom, CIRCLE_PREFIX, "CIRCLES", f)

def fFriends(dom, f):
    writeAll(dom, FRIEND_PREFIX, "FRIENDS", f)


def fInterests(dom, f):
    writeAll(dom, INTEREST_PREFIX, "INTERESTS", f)

def fSchools(dom, f):
    prefix = gen_prefix("AcademicInstitution", ('userid', 'institution'))
    writeAll(dom, prefix, "SCHOOLS", f)

def fPhotos(dom, f):
    prefix = gen_prefix("Photo", ('pid', 'userid', 'url', 'privacy'))
    writeAll(dom, prefix, "PHOTOS", f)

def fRatings(dom, f):
    prefix = gen_prefix("Rating", ('pid', 'userid', 'score'))
    writeAll(dom, prefix, "RATING", f)

def fTags(dom, f):
    prefix = gen_prefix("Tag", ('pid', 'userid', 'comments'))
    writeAll(dom, prefix, "TAG", f)

def fCPrivs(dom, f):
    prefix = gen_prefix("CirclePrivacy", ('pid', 'cid'))
    writeAll(dom, prefix, "CIRCLEPRIVACY", f)

def fUPrivs(dom, f):
    prefix = gen_prefix("UserPrivacy", ('pid', 'userid'))
    writeAll(dom, prefix, "USERPRIVACY", f)

def flatten(in_name, out_name):
    f = open(out_name, 'w')
    dom = parse(in_name) # parse an XML file by name

    fUsers(dom.getElementsByTagName("USER"), f)    

    fProfs(dom.getElementsByTagName("PROFESSOR"), f)    

    fStuds(dom.getElementsByTagName("STUDENT"), f)    

    fCircles(dom.getElementsByTagName("CIRCLE"), f)    

    fFriends(dom.getElementsByTagName("FRIEND"), f)    

    fInterests(dom.getElementsByTagName("INTEREST"), f)    

    fSchools(dom.getElementsByTagName("SCHOOL"), f)    

    fPhotos(dom.getElementsByTagName("PHOTO"), f)    

    fRatings(dom.getElementsByTagName("RATING"), f)    

    fTags(dom.getElementsByTagName("TAG"), f)    

    fCPrivs(dom.getElementsByTagName("CIRCLEPRIV"), f)    

    fUPrivs(dom.getElementsByTagName("USERPRIV"), f)    

if __name__ == "__main__":
    import sys
    assert(len(sys.argv) == 3)
    flatten(sys.argv[1], sys.argv[2])
