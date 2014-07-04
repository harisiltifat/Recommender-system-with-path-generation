<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>SocialAuth Demo</title>
</head>
<body>

<h2 align="center">Authentication has been successful.</h2>
<br/>
<div align="center"><a href="index.jsp">Back</a></div>
<br />
<h3 align="center">Profile Information</h3>
<table cellspacing="1" cellspacing="4" border="0" bgcolor="e5e5e5" width="60%" align="center">
        <tr class="sectiontableheader">
                <th>Profile Field</th>
                <th>Value</th>
        </tr>
        <tr class="sectiontableentry1">
                <td>Id:</td>
                <td><c:out value="${profile.validatedId}"/></td>
        </tr>
        <tr class="sectiontableentry1">
                <td>Email:</td>
                <td><c:out value="${profile.email}"/></td>
        </tr>
        <tr class="sectiontableentry2">
                <td>First Name:</td>
                <td><c:out value="${profile.firstName}"/></td>
        </tr>
        <tr class="sectiontableentry1">
                <td>Last Name:</td>
                <td><c:out value="${profile.lastName}"/></td>
        </tr>
        <tr class="sectiontableentry2">
                <td>Country:</td>
                <td><c:out value="${profile.country}"/></td>
        </tr>
        <tr class="sectiontableentry1">
                <td>Language:</td>
                <td><c:out value="${profile.language}"/></td>
        </tr>
        <tr class="sectiontableentry2">
                <td>Full Name:</td>
                <td><c:out value="${profile.fullName}"/></td>
        </tr>
        <tr class="sectiontableentry1">
                <td>Display Name:</td>
                <td><c:out value="${profile.displayName}"/></td>
        </tr>
        <tr class="sectiontableentry2">
                <td>DOB:</td>
                <td><c:out value="${profile.dob}"/></td>
        </tr>
        <tr class="sectiontableentry1">
                <td>Gender:</td>
                <td><c:out value="${profile.gender}"/></td>
        </tr>
        <tr class="sectiontableentry2">
                <td>Location:</td>
                <td><c:out value="${profile.location}"/></td>
        </tr>
</table>
<!--  <h3 align="center">Contact Details</h3>
<table cellspacing="1" cellspacing="4" border="0" bgcolor="e5e5e5" align="center" width="60%">
        <tr class="sectiontableheader">
                <th width="15%">Name</th>
                <th>Email</th>
                <th>Profile URL</th>
        </tr>
        <c:forEach var="contact" items="${contacts}" varStatus="index">
        	<tr>
            	<td><c:out value="${contact.firstName}"/> <c:out value="${contact.lastName}"/></td>
                <td><c:out value="${contact.email}"/></td>
                <td><a href='<c:out value="${contact.profileUrl}"/>' target="_new"><c:out value="${contact.profileUrl}"/></a></td>
            </tr>
        </c:forEach>
</table>-->
</body>
</html>