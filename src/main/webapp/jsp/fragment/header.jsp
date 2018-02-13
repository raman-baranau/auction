<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<ul class="nav-bar">
  <li>
    <a href="controller?command=show_auctions" class="logo" >
      <img src="../images/auction.png"/>
    </a>
  </li>
  <li><a href="controller?command=show_auctions">Home</a></li><li>
  
  <c:choose>
    <c:when test="${role eq 'GUEST'}">
      <li style="float:right"><a href="register.jsp">Sign up</a></li>
      <li style="float:right"><a href="login.jsp">Sign in</a></li>
    </c:when>
    <c:otherwise>
      <li class="signout">
	      <form name="signOutForm" method="POST" action="controller">
	        <input type="hidden" name="command" value="logout" />
	        <input type="submit" class="signoutbtn" value="Sign out"/>
	      </form>
      </li>
      <li class="dropdown">
        <a href="controller?command=show_profile" class="dropbtn">Hello, ${ user.firstName }</a>
        <div class="dropdown-content">
          <a href="controller?command=show_profile">My profile</a>
          <c:choose>
            <c:when test="${ role eq 'CLIENT' }">
              <a href="controller?command=show_auctions&ownerId=${ user.id }">
                My lots
              </a>
              <a href="profile_edit.jsp">My settings</a>
              <a href="auction_create.jsp">Create lot</a>
            </c:when>
          </c:choose>
        </div>
      </li>
    </c:otherwise>
  </c:choose>
</ul>
