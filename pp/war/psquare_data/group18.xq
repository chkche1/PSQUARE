(: XQuery main module :)
declare namespace tns = "http://www.example.org/pennphoto";
declare namespace functx = "http://www.functx.com";
    
import module namespace hash = "http://www.zorba-xquery.com/modules/cryptography/hash";


declare function local:hashcircle($id as element(tns:id), $c as element(tns:circle))
    as xs:string {
    (: xs:string(data($id)) + :) 
    let $joined := concat(xs:string(data($id)), hash:md5(data($c/tns:name)))
    let $sanitized := replace($joined, 'a|b|c|d|e|f', '')
    return fn:substring($sanitized, 0, 10)  
   };

<DB>
<ENTRIES>
{
for $x in doc('pennphoto-18.xml')/tns:photodb/*
return 
    <USER>
        <ID>{data($x/tns:id)}</ID>
        <FNAME>{data($x/tns:first_name)}</FNAME>
        <LNAME>{data($x/tns:last_name)}</LNAME>
        <BDAY>{year-from-date(current-date()) - data($x/tns:age)}</BDAY>
        <EMAIL>{data($x/tns:email)}</EMAIL>
        <GENDER>{data($x/tns:gender)}</GENDER>
        <ADDRESS></ADDRESS>
        <PW>{data($x/tns:password)}</PW>
    </USER>
}

{
for $x in doc('pennphoto-18.xml')/tns:photodb/tns:professor
return 
    <PROFESSOR>
        <ID>{data($x/tns:id)}</ID>
        <RESEARCH>{data($x/tns:researchArea)}</RESEARCH>
        <ROOM>{data($x/tns:roomNumber)}</ROOM>
    </PROFESSOR>
}

{
for $x in doc('pennphoto-18.xml')/tns:photodb/tns:student
return 
    <STUDENT>
        <ID>{data($x/tns:id)}</ID>
        <ADVISOR>{data($x/tns:advisor)}</ADVISOR>
        <YEAR></YEAR>
    </STUDENT>
}

{
for $x in doc('pennphoto-18.xml')/tns:photodb/*
return
    { for $y in $x/tns:circle
       return
        <CIRCLE>
            <CID>{local:hashcircle($x/tns:id, $y)}</CID>
            <OWNERID>{data($x/tns:id)}</OWNERID>
            <CNAME>{data($y/tns:name)}</CNAME>
        </CIRCLE>
    }
}

{
for $x in doc('pennphoto-18.xml')/tns:photodb/*
return
    { for $y in $x/tns:circle
       return
       {
           for $z in $y/tns:containsFriend
           return
           {
            <FRIEND>
                <FRIENDID>{data($z/tns:friend_id)}</FRIENDID>
                <CID>{local:hashcircle($x/tns:id, $y)}</CID>
            </FRIEND>
           }
       }
    }
}

{
for $x in doc('pennphoto-18.xml')/tns:photodb/tns:student
return
        {
        for $y in $x/tns:academic_institution
        return
        <SCHOOL>
            <ID>{data($x/tns:id)}</ID>
            <SCHOOLNAME>{data($y)}</SCHOOLNAME>
        </SCHOOL>
        }
}

{
for $x in doc('pennphoto-18.xml')/tns:photodb/*
return
    {
        for $y in $x/tns:photo
        return
        <PHOTO>
            <PID>{data($y/tns:photoID)}</PID>
            <USERID>{data($x/tns:id)}</USERID>
            <URL>{data($y/tns:url)}</URL>
            <PRIVACY>CUSTOM</PRIVACY>
       </PHOTO>
    }
}

{
for $x in doc('pennphoto-18.xml')/tns:photodb/*
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
for $x in doc('pennphoto-18.xml')/tns:photodb/*
return
    {
        for $y in $x/tns:photo
        return
        {
           for $z in $y/tns:tagging
           return
           <TAG>
                <PID>{data($y/tns:photoID)}</PID>
                <TAGGERID>{data($z/tns:tagger)}</TAGGERID>
                <COMMENT>{data($z/tns:tag)}</COMMENT>
            </TAG>
       }
    }
}

{
for $x in doc('pennphoto-18.xml')/tns:photodb/*
return
    {
        for $y in $x/tns:viewablePhoto
        return
           <USERPRIV>
                <PID>{data($y/tns:photo_id)}</PID>
                <ID>{data($x/tns:id)}</ID>
            </USERPRIV>
    }
}

</ENTRIES>
</DB>