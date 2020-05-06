<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Shopping List</title>
<link rel="stylesheet" href="https://unpkg.com/sakura.css/css/sakura.css" type="text/css">
</head>
<body>
	<h1>Shopping list</h1>

	<form method="post">
		<input name="title" required type="text"
			placeholder=" type item here..." autofocus /> <input type="submit"
			value="Add to list" />
	</form>

	<ul>
		<c:forEach items="${ items }" var="shoppingListItem">
			<li><c:out value="${ shoppingListItem.getTitle() }" /></li>
		</c:forEach>
	</ul>

</body>
</html>