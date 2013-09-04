<%@ include file="/common/taglibs.jsp" %>

<head>
    <title>Make Admin</title>
    <meta name="menu" content="AdminMenu"/>
</head>
<body id="make admin">

<div class="span10">
    <h2>Make Admin</h2>
    <c:choose>
    	<c:when test="${empty error}">
    	User <c:out value="${username}"/> was successfully given admin privileges
    	</c:when>
    	
    	<c:otherwise>
    	Error granting admin privileges to user <c:out value="${username}"/>
    	</c:otherwise>
    	
    </c:choose>
	
</div>
</body>
