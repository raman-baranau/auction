<%@ page language="java" contentType="text/html; utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Create new lot</title>
    <link rel="stylesheet" type="text/css" href="../css/header.css">
    <link rel="stylesheet" type="text/css" href="../css/create_auction.css">
  </head>
  <body>
    <c:import url="/jsp/fragment/header.jsp"></c:import>
    <form id="createLotForm" method="POST" action="controller" onsubmit="return validate();">
      <div class="container"> 
        <input type="hidden" name="command" value="create_auction" />
        <hr class="line">
      
	      <label><b>Lot name<span class="required">*</span></b></label>
	      <input type="text" name="lotName" value="" placeholder="Enter lot name"/>
	      <p class="error" id="lotNameError"></p><br/>
	    
	      <label><b>Lot description<span class="required">*</span></b></label>
	      <textarea name="lotDescription"></textarea>
	      <p class="error" id="lotDescriptionError"></p><br/>
	      
	      <label><b>Initial price<span class="required">*</span></b></label>
	      <input type="text" 
	             name="initialPrice"
	             value=""
	             onkeypress='return isValidDigit();'
	             placeholder="Enter initial price"/>
	      <p class="error" id="initialPriceError"></p><br/>
        
        <label><b>Duration of the auction<span class="required">*</span></b></label>
        <div class="duration">
          <input type="text"
                 name="daysDuration" 
                 value=""
                 onkeypress='return isValidDigit();'
                 min="1"
                 max="14"
                 placeholder="Days (1-14)"/>
          <input type="text" 
                 name="hoursDuration" 
                 value=""
                 onkeypress='return isValidDigit();'
                 min="0"
                 max="23"
                 placeholder="Hours (0-23)"/>
        </div>
        
        <select name="auctionType">
			    <option value="straight" selected>Straight</option>
			    <option value="reverse">Reverse</option>
        </select>
	      
	      <div>${ errorValidateAuctionMessage }</div>
	      <p>Fields marked as <span class="required">*</span> are required to fill in.</p>
	      <p>By creating an auction you agree to our <a href="#">Terms &#38; Privacy.</a></p>
	      <input type="button" class="cancelbtn" 
	          onclick="window.location.href='main.jsp';" value="Cancel"/>
	      <input type="submit" class="signupbtn" value="Create lot"/>
      </div>
    </form>
    <script type="text/javascript">
      <%@include file="../js/create_lot.js"%>
    </script>
  </body>
</html>