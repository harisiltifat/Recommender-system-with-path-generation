<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel='stylesheet' type='text/css'
	href='/ThesisProject/resources/css/style.css'>
<link
	href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:400,700"
	rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript"
	src="/ThesisProject/resources/js/jquery.js"></script>
<script
	src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&libraries=places"></script>
<title>Location</title>
<script>
		var source, destination;
		function initialize() {
			source = new google.maps.places.Autocomplete(
			/** @type {HTMLInputElement} */(document.getElementById('source')),
			{ types: ['geocode'] });
			destination  = new google.maps.places.Autocomplete(
			/** @type {HTMLInputElement} */(document.getElementById('destination')),
			{ types: ['geocode'] });
		}
		
		function geolocate() {
			if (navigator.geolocation) {
				navigator.geolocation.getCurrentPosition(function(position) {
					var geolocation = new google.maps.LatLng(
					position.coords.latitude, position.coords.longitude);
					source.setBounds(new google.maps.LatLngBounds(geolocation,geolocation));
					destination.setBounds(new google.maps.LatLngBounds(geolocation,geolocation));
				});
			}
		}
		
    </script>
	<script type="text/javascript">
	$( document ).ready(function() {
		var geocoder = new google.maps.Geocoder();
		var address ;
		var sourceLat = 0;
		var sourceLng = 0;
		var destinationLat = 0;
		var destinationLng = 0;
		var time=0;
		var budget=0;
		var isTimeEnable=0;
		   
		$('#isTimeEnable').change(function(){
			   $("#time").prop("disabled", !$(this).is(':checked'));
			   $("#budget").prop("disabled", !$(this).is(':checked'));
			});
		
		$(".savebutton").click(function(){			
			address = document.getElementById("source").value;
			geocoder.geocode( { 'address': address}, function(results, status) {
			  if (status == google.maps.GeocoderStatus.OK)
			  {
			  	sourceLat = results[0].geometry.location.lat();
			    sourceLng = results[0].geometry.location.lng();
			    time=document.getElementById("time").value;
			    budget=document.getElementById("budget").value;
			    isTimeEnable=document.getElementById("isTimeEnable").value;
			    
			    address = document.getElementById("destination").value;
				geocoder.geocode( { 'address': address}, function(results, status) {
					if (status == google.maps.GeocoderStatus.OK)
					{
						destinationLat = results[0].geometry.location.lat();
						destinationLng = results[0].geometry.location.lng();
					  	$.ajax({		
			       			url: "<c:url value='/savelocation' />",
			       			type: 'POST',            
			       			data:{ 	
			       				sourceLat : sourceLat,
			       				sourceLng : sourceLng, 
			       				destinationLat : destinationLat,
			       				destinationLng : destinationLng,
			       				time:time,
			       				budget:budget,
			       				isTimeEnable:isTimeEnable
			       			},
			       			success: function(response) 
			       			{
			       				window.location.href = response;			       	            
			       			}, 
			       			error: function(xhr, ajaxOptions, thrownError) {
								alert('Error');
			     	 		}
			   			});
					}
				});
			  }
			
			});
		});
	});
	</script>
</head>

<body onload="initialize()">
	<div id="wrapper">
		<div id="content">
			<div>
				<input name = "source" id="source" placeholder="Enter your source" onFocus="geolocate()" type="text"></input><br><br>
				<input name = "destination" id="destination" placeholder="Enter your destination" onFocus="geolocate()" type="text"></input><br><br>
				<input type="checkbox" id="isTimeEnable" name="isTimeEnable">Proceed with time and budget<br><br>
				<input name = "time" id="time" name="time" placeholder="Enter your time limit in mins" onFocus="geolocate()" type="text" disabled="" value="180"></input><br><br>
				<input name = "budget" id="budget" name="budget" placeholder="Enter your budget" onFocus="geolocate()" type="text" disabled="" value="100"></input><br>
				
				<div style= "margin-top:10px; width:300px;">
					<input type="submit" class="savebutton" value = "Next" />
				</div>
			</div>
		</div>
	</div>
</body>

</html>
