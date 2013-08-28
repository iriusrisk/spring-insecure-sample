<%@ include file="/common/taglibs.jsp"%>

<head>
<title>Hello</title>
</head>
<body id="hello">

	<form method="get" id="helloForm"
		action="<c:url value='/sayhello'/>">
		<c:if test="${param.name != null}">
		<ol>
			<li>Hello <%= request.getParameter("name") %></li>
			<li>Hello <c:out escapeXml="false" value="${param.name}"/></li>
			<li>Hello ${param.name}</li>
			<li><script>document.write("Hello "+"${param.name}")</script></li>
		</ol>
		</c:if>
		<br><br>
		Please enter your name to be greeted 4 times <input type="text" name="name"/>
		<br>
		<button type="submit" class="btn btn-large btn-primary" name="login">
			Submit
		</button>
	</form>

</body>