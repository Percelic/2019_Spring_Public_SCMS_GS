<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
    <center>
        <form action="trans.do">
            <tr>
                <td><input type="text" name="text"> 
                <select name="source" size="1">
                <option value="ko" >한국어</option>
                <option value="en" seleted>영어</option>
            </select> <br><br> <input
                    type="submit" value="번역"></td>
            </tr>
 
 
 
 
            <br> <br> <br> <br> <br> 번역결과 
            <select name="target" size="1">
                <option value="ko" seleted>한국어</option>
                <option value="en" >영어</option>
            </select> <br>
            <textarea rows="2" cols="20">${result }</textarea>
 
 
 
        </form>
    </center>
</body>
</html>


