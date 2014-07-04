<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <title>RealTime Trip Planner </title>
  </head>
  <body>
 
    <div id="wrapper">
    	<div id="content">
    	   <table align="center">
      <tr><td colspan="8"><h3 align="center">Welcome to RealTime Trip Planer</h3></td></tr>
      <tr>
        <!-- <td>
          <a href="socialauth.do?id=foursquare">
            <img src="resources/images/foursquare.png" alt="FourSquare" title="FourSquare" border="0"/>
          </a>
        </td> -->
        <td>
          <a href="socialauth.do?id=facebook">
            <img src="resources/images/facebook.png" alt="Facebook" title="Facebook" border="0"/>
          </a>
        </td>
      </tr>
    </table>
    	<!--  <h2>Places According to Explore</h2>
    	  <c:set var="count" value="0" scope="page" />
			<c:forEach var="entry" items="${places}">
				<c:set var="count" value="${count + 1}" scope="page"/>
				<c:out value="${count}" />
    			<b>Name: </b> ${entry.name}
    			<b>Categories: </b> ${entry.types}
    			<b>Checkins: </b> ${entry.stats}
    			<b>Rating: </b> ${entry.rating}
    			<b>Likes: </b> ${entry.likes}
    			
    			<br/> <br/>	
			</c:forEach>-->
		</div>
	</div>	
	
  </body>
</html>