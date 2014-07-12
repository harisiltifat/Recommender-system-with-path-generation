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

	<script src="https://maps.googleapis.com/maps/api/js?v=3.exp"></script>
	<script>
		function initialize() {
		  var rows = document.getElementById("tblPlaces").rows.length;

		   var lng=document.getElementById(0+"lng").value
		   var lat=document.getElementById(0+"lat").value
		   var myLatlng = new google.maps.LatLng(lat,lng);
		   var pathCoordinates= [];
		   var mapOptions = {
					    zoom: 15,
					    center: myLatlng,
					  } ;

		  var map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);

		  for(i=0;i<rows;i++){ 
			   lng=document.getElementById(i+"lng").value
			  lat=document.getElementById(i+"lat").value
			  myLatlng = new google.maps.LatLng(lat,lng);

			   var marker = new google.maps.Marker({
			      position: myLatlng,
			      map: map,
			      title: document.getElementById(i+"name").value
			  });  
			  pathCoordinates.push(myLatlng);


		  }
		  var path = new google.maps.Polyline({
		        path: pathCoordinates,
		        geodesic: true,
		        strokeColor: '#FF0000',
		        strokeOpacity: 1.0,
		        strokeWeight: 2
		  });

		  path.setMap(map); 


		}
		google.maps.event.addDomListener(window, 'load', initialize);
    </script>
	<title>Places</title>
	<style>
      html, body, #map-canvas {
        height: 100%;
        margin: 100px;
        padding: 15px
      }
    </style>
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

		<!-- <div id="lstPlaces">
    	<h2>Categorised Places with Final Popularity Score </h2>
    		<c:set var="count" scope="page" value="0" />
    		<table id="tblPlaces">
    		<c:forEach var="places" items="${lstplaces}">
    		<tr>
    			<td><b>${places.name}</b></td>
    			<td>${places.types}</td>
    			<td>${places.rating}</td>
    			<c:set var="idlng" scope="page" value="${count}lng" />
    			<c:set var="idlat" scope="page" value="${count}lat" />
    			<c:set var="idname" scope="page" value="${count}name" />
    			<td><input type="hidden" id="${idlng}" value="${places.longitude}" /></td>
    			<td><input type="hidden" id="${idlat}" value="${places.latitude}" /></td>
    			<td><input type="hidden" id="${idname}" value="${places.name}" /></td>
    			<c:set var="count" scope="page" value="${count+1}" />
    		</tr>
			</c:forEach>	
			</table>	
		</div> -->

		<div id="content">
    	<h2>Categorised Places with Final Popularity Score </h2>
    		<c:forEach var="outermap" items="${finalScoredPlacesWithCat}">
    			<b>${outermap.key}</b><br>
    			<c:forEach var="innermap" items="${outermap.value}" >
    				<b>Name:</b> ${innermap.key.name}    				
    				<b>Score:</b> ${innermap.value}<br/>
    			</c:forEach>		
			</c:forEach>		
		</div>
	</div>	
	<div id="map-canvas"></div>

</body>

</html>
