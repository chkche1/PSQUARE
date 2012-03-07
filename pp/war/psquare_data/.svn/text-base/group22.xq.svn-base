(: XQuery main module :)

declare namespace tns = "http://www.example.org/NewXMLSchema";

<DB>
<ENTRIES>
{
for $x in doc('pennphoto-22.xml')/tns:photodb/*
return 
    <USER>
        <ID>{data($x/tns:userID)}</ID>
        <FNAME>{data($x/tns:first_name)}</FNAME>
        <LNAME>{data($x/tns:last_name)}</LNAME>
        <BDAY>{data($x/tns:birthdate)}</BDAY>
        <EMAIL>{data($x/tns:email)}</EMAIL>
        <GENDER>{data($x/tns:gender)}</GENDER>
        <ADDRESS>{data($x/tns:address)}</ADDRESS>
        <PW>{data($x/tns:login/tns:password)}</PW>
    </USER>
}


{
for $x in doc('pennphoto-22.xml')/tns:photodb/*
where exists($x/tns:professor)
return 
    <PROFESSOR>
        <ID>{data($x/tns:userID)}</ID>
        <RESEARCH>{data($x/tns:professor/tns:research)}</RESEARCH>
        <ROOM>{data($x/tns:professor/tns:room_no)}</ROOM>
    </PROFESSOR>
}

{
for $x in doc('pennphoto-22.xml')/tns:photodb/*
where exists($x/tns:student)
return 
    <STUDENT>
        <ID>{data($x/tns:userID)}</ID>
        <ADVISOR>{data($x/tns:student/tns:advisor_id)}</ADVISOR>
        <YEAR>{data($x/tns:student/tns:current_year)}</YEAR>
    </STUDENT>
}

{
for $x in doc('pennphoto-22.xml')/tns:photodb/*
return
    { for $y in $x/tns:circle
       return
        <CIRCLE>
            <CID>{data($y/tns:cid)}</CID>
            <OWNERID>{data($x/tns:userID)}</OWNERID>
            <CNAME>{data($y/tns:cname)}</CNAME>
        </CIRCLE>
    }
}

{
for $x in doc('pennphoto-22.xml')/tns:photodb/*
return
    { for $y in $x/tns:circle
       return
       for $z in $y/tns:containsFriend
       return
       {
        <FRIEND>
            <FRIENDID>{data($z)}</FRIENDID>
            <CID>{data($y/tns:cid)}</CID>
        </FRIEND>
        }
    }
}

{
for $x in doc('pennphoto-22.xml')/tns:photodb/*
return
        {
        for $y in $x/tns:interest
        return
        <INTEREST>
            <ID>{data($x/tns:userID)}</ID>
            <INAME>{data($y)}</INAME>
        </INTEREST>
        }
}

{
for $x in doc('pennphoto-22.xml')/tns:photodb/*
return
        {
        for $y in $x/tns:institution
        return
        <SCHOOL>
            <ID>{data($x/tns:userID)}</ID>
            <SCHOOLNAME>{data($y/tns:name)}</SCHOOLNAME>
        </SCHOOL>
        }
}

{
for $x in doc('pennphoto-22.xml')/tns:photodb/*
return
    {
        for $y in $x/tns:photo
        return
        <PHOTO>
            <PID>{data($y/tns:photoID)}</PID>
            <USERID>{data($x/tns:userID)}</USERID>
            <URL>{data($y/tns:url)}</URL>
            <PRIVACY>CUSTOM</PRIVACY>
       </PHOTO>
    }
}

{
for $x in doc('pennphoto-22.xml')/tns:photodb/*
return
    {   
        for $y in $x/tns:photo
        return
        {
           for $z in $y/tns:rating
           return
           <RATING>
                <PID>{data($y/tns:photoID)}</PID>
                <RATERID>{data($z/tns:rater)}</RATERID>
                <SCORE>{data($z/tns:score)}</SCORE>
            </RATING>
       }
    }
}

{
for $x in doc('pennphoto-22.xml')/tns:photodb/*
return
    {
        for $y in $x/tns:photo
        return
        {
           for $z in $y/tns:tag
           return
           <TAG>
                <PID>{data($y/tns:photoID)}</PID>
                <TAGGERID>{data($z/tns:tagger)}</TAGGERID>
                <COMMENT>{data($z/tns:tag_description)}</COMMENT>
            </TAG>
       }
    }
}

{
for $x in doc('pennphoto-22.xml')/tns:photodb/*
return
    {
        for $y in $x/tns:photo
        return
        {
           for $z in $y/tns:visibility/tns:circleID
           return
           <CIRCLEPRIV>
                <PID>{data($y/tns:photoID)}</PID>
                <CID>{data($z)}</CID>
            </CIRCLEPRIV>
       }
    }
}

{
for $x in doc('pennphoto-22.xml')/tns:photodb/*
return
    {
        for $y in $x/tns:photo
        return
        {
           for $z in $y/tns:visibility/tns:userID
           return
           <USERPRIV>
                <PID>{data($y/tns:photoID)}</PID>
                <ID>{data($z)}</ID>
            </USERPRIV>
       }
    }
}

  
</ENTRIES>
</DB>