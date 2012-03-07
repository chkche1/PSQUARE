(: XQuery main module :)
declare namespace tns = "http://www.example.org/pennphoto";

<DB>
<ENTRIES>
{
for $x in doc('pennphoto-12.xml')/tns:photodb/*
return 
    <USER>
        <ID>{data($x/tns:accountID)}</ID>
        <FNAME>{data($x/tns:fname)}</FNAME>
        <LNAME>{data($x/tns:lname)}</LNAME>
        <BDAY>{data($x/tns:birthDate)}</BDAY>
        <EMAIL>{data($x/tns:email)}</EMAIL>
        <GENDER>{data($x/tns:gender)}</GENDER>
        <ADDRESS>{data($x/tns:address)}</ADDRESS>
        <PW>{data($x/tns:password)}</PW>
    </USER>
}

{
for $x in doc('pennphoto-12.xml')/tns:photodb/tns:professor
return 
    <PROFESSOR>
        <ID>{data($x/tns:accountID)}</ID>
        <RESEARCH></RESEARCH>
        <ROOM></ROOM>
    </PROFESSOR>
}

{
for $x in doc('pennphoto-12.xml')/tns:photodb/tns:student
return 
    <STUDENT>
        <ID>{data($x/tns:accountID)}</ID>
        <ADVISOR>{data($x/tns:advisor)}</ADVISOR>
        <YEAR></YEAR>
    </STUDENT>
}

{
for $x in doc('pennphoto-12.xml')/tns:photodb/*
return
    { for $y in $x/tns:circle
       return
        <CIRCLE>
            <CID>{data($y/tns:circleID)}</CID>
            <OWNERID>{data($x/tns:accountID)}</OWNERID>
            <CNAME>{data($y/tns:name)}</CNAME>
        </CIRCLE>
    }
}

{
for $x in doc('pennphoto-12.xml')/tns:photodb/*
return
    { for $y in $x/tns:circle
       return
       for $z in $y/tns:friend
       return
       {
        <FRIEND>
            <FRIENDID>{data($z)}</FRIENDID>
            <CID>{data($y/tns:circleID)}</CID>
        </FRIEND>
        }
    }
}

{
for $x in doc('pennphoto-12.xml')/tns:photodb/*
return
        {
        for $y in $x/tns:interest
        return
        <INTEREST>
            <ID>{data($x/tns:accountID)}</ID>
            <INAME>{data($y)}</INAME>
        </INTEREST>
        }
}

{
for $x in doc('pennphoto-12.xml')/tns:photodb/*
return
        {
        for $y in $x/tns:schoolsAttended
        return
        <SCHOOL>
            <ID>{data($x/tns:accountID)}</ID>
            <SCHOOLNAME>{data($y/tns:name)}</SCHOOLNAME>
        </SCHOOL>
        }
}

{
for $x in doc('pennphoto-12.xml')/tns:photodb/*
return
    {
        for $y in $x/tns:photo
        return
        <PHOTO>
            <PID>{data($y/tns:photoID)}</PID>
            <USERID>{data($x/tns:accountID)}</USERID>
            <URL>{data($y/tns:url)}</URL>
            <PRIVACY>{if (data($y/tns:visibility) = 0) then "ALL" else "CUSTOM"}</PRIVACY>
       </PHOTO>
    }
}

{
for $x in doc('pennphoto-12.xml')/tns:photodb/*
return
    {
        for $y in $x/tns:photo
        return
        {
           for $z in $y/tns:rating
           return
           <RATING>
                <PID>{data($y/tns:photoID)}</PID>
                <RATERID>{data($z/tns:raterID)}</RATERID>
                <SCORE>{data($z/tns:score)}</SCORE>
            </RATING>
       }
    }
}

{
for $x in doc('pennphoto-12.xml')/tns:photodb/*
return
    {
        for $y in $x/tns:photo
        return
        {
           for $z in $y/tns:tag
           return
           <TAG>
                <PID>{data($y/tns:photoID)}</PID>
                <TAGGERID>{1}</TAGGERID>
                <COMMENT>{data($z/tns:text)}</COMMENT>
            </TAG>
       }
    }
}
(: TODO, fake grabbing privacies that don't exist' :)
  
</ENTRIES>
</DB>