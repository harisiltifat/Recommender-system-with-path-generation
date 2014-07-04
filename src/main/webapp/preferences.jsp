<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<link rel='stylesheet' type='text/css' href='/ThesisProject/resources/css/style.css'>
	<link href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:400,700" rel="stylesheet" type="text/css"/>
	<script language="javascript" type="text/javascript" src="/ThesisProject/resources/js/jquery.js"></script>
	<script language="javascript" type="text/javascript" src="/ThesisProject/resources/js/barrating.js"></script>
	<title>Your Preferences</title>
	<script type="text/javascript">
		$( document ).ready(function() {
			
			$('#museum').barrating('show', {
                showValues:true,
                showSelectedRating:false,
                onSelect:function(value, text) {
                	$("#museum").val(value);
                }
            });
			
			$('#nightlife').barrating('show', {
                showValues:true,
                showSelectedRating:false,
                onSelect:function(value, text) {
                	$("#nightlife").val(value);
                }
            });
			
			$('#food').barrating('show', {
                showValues:true,
                showSelectedRating:false,
                onSelect:function(value, text) {
                	$("#food").val(value);
                }
            });
			
			$('#nature').barrating('show', {
                showValues:true,
                showSelectedRating:false,
                onSelect:function(value, text) {
                	$("#nature").val(value);
                }
            });
			
			$('#music').barrating('show', {
                showValues:true,
                showSelectedRating:false,
                onSelect:function(value, text) {
                	$("#music").val(value);
                }
            });
			
			$('#shopping').barrating('show', {
                showValues:true,
                showSelectedRating:false,
                onSelect:function(value, text) {
                	$("#shopping").val(value);
                }
            });
			
			$('#sports').barrating('show', {
                showValues:true,
                showSelectedRating:false,
                onSelect:function(value, text) {
                	$("#sports").val(value);
                }
            });
		
		});
	</script>		
</head>

<body>
	<div id="wrapper">
    	<div id="content">
    	<form method="POST" action="savepreferences">
    		<div class="input select rating-c">
    			<span>Art/Museums</span>
            	<select id="museum" name="museum">
                	<option value=""></option>
                	<option value="1">1</option>
                	<option value="2">2</option>
                	<option value="3">3</option>
                	<option value="4">4</option>
                	<option value="5">5</option>
            	</select>
        	</div>
        	
        	<div class="input select rating-c">
        		<span>Night Life</span>
            	<select id="nightlife" name="nightlife">
                	<option value=""></option>
                	<option value="1">1</option>
                	<option value="2">2</option>
                	<option value="3">3</option>
                	<option value="4">4</option>
                	<option value="5">5</option>
            	</select>
        	</div>
        	
        	<div class="input select rating-c">
        		<span>Food</span>
            	<select id="food" name="food">
                	<option value=""></option>
                	<option value="1">1</option>
                	<option value="2">2</option>
                	<option value="3">3</option>
                	<option value="4">4</option>
                	<option value="5">5</option>
            	</select>
        	</div>
        	
        	<div class="input select rating-c">
        		<span>Nature</span>
            	<select id="nature" name="nature">
                	<option value=""></option>
                	<option value="1">1</option>
                	<option value="2">2</option>
                	<option value="3">3</option>
                	<option value="4">4</option>
                	<option value="5">5</option>
            	</select>
        	</div>
        	
        	<div class="input select rating-c">
        		<span>Music</span>
            	<select id="music" name="music">
                	<option value=""></option>
                	<option value="1">1</option>
                	<option value="2">2</option>
                	<option value="3">3</option>
                	<option value="4">4</option>
                	<option value="5">5</option>
            	</select>
        	</div>
        	
        	<div class="input select rating-c">
        		<span>Shopping</span>
            	<select id="shopping" name="shopping">
                	<option value=""></option>
                	<option value="1">1</option>
                	<option value="2">2</option>
                	<option value="3">3</option>
                	<option value="4">4</option>
                	<option value="5">5</option>
            	</select>
        	</div>
        	
        	<div style= "margin-top:10px; width:194px;">
        		<input type="submit" class="savebutton" value = "Show Places" />
        	</div>
        	
        </form>
		</div>
	</div>	
</body>

</html>
