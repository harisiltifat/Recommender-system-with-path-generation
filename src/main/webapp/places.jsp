<%@ page language="java" contentType="text/html ; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<link rel='stylesheet' type='text/css' href='/ThesisProject/resources/css/style.css'>
	<link href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:400,700" rel="stylesheet" type="text/css"/>
	<script language="javascript" type="text/javascript" src="/ThesisProject/resources/js/jquery.js"></script>
	<title>Places</title>
	
</head>

<body>
	<div id="wrapper">
    	<!--  <div id="content">
    	<h2>Recommended Places By Max Likes </h2>
			<c:forEach var="outermap" items="${scoredPlacesByMaxLikes}">
    			<b>${outermap.key}</b><br>
    			<c:forEach var="innermap" items="${outermap.value}" >
    				<b>Name:</b> ${innermap.key.name}
    				<b>Likes:</b> ${innermap.key.likes}
    				<b>Score:</b> ${innermap.value}<br/>
    			</c:forEach>		
			</c:forEach>
		</div>-->
			<!--<div id="content">
    	<h2>Recommended Places By Max Likes </h2>
			<c:forEach var="outermap" items="${scoredPlacesByMaxLikes}">
    			<b>${outermap.key}</b><br>
    			<c:forEach var="innermap" items="${outermap.value}" >
    				<b>Name:</b> ${innermap.key.name}
    				<b>Likes:</b> ${innermap.key.likes}
    				<b>Score:</b> ${innermap.value}<br/>
    			</c:forEach>		
			</c:forEach>
		</div>-->
	  <!--  <div id="content">
    	<h2>Recommended Places By Opening Times </h2>
			<c:forEach var="outermap" items="${scoredPlacesByOpeningTime}">
    			<b>${outermap.key}</b><br>
    			<c:forEach var="innermap" items="${outermap.value}" >
    				<b>Name:</b> ${innermap.key.name}
    				<b>Opening Status:</b> ${innermap.key.openNow}
    				<b>Score:</b> ${innermap.value}<br/>
    			</c:forEach>		
			</c:forEach>
		</div>-->
		
		<div id="lstPlaces">
    	<h2>Categorised Places with Final Popularity Score </h2>
    		<c:forEach var="places" items="${lstplaces}">
    		<table><tr>
    			<td><b>${places.name}</b></td>
    			<td>${places.types}</td>
    			<td>${places.rating}</td>
    			</tr>
    		</table>
			</c:forEach>		
		</div>
		
		<%-- <div id="content">
    	<h2>Categorised Places with Final Popularity Score </h2>
    		<c:forEach var="outermap" items="${finalScoredPlacesWithCat}">
    			<b>${outermap.key}</b><br>
    			<c:forEach var="innermap" items="${outermap.value}" >
    				<b>Name:</b> ${innermap.key.name}    				
    				<b>Score:</b> ${innermap.value}<br/>
    			</c:forEach>		
			</c:forEach>		
		</div>  --%>
	</div>	
</body>

</html>
