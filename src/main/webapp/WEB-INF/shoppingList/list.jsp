<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Shopping List</title>
<link rel="stylesheet" href="https://unpkg.com/sakura.css/css/sakura.css" type="text/css">
 <script src="scripts/app.js"></script>
</head>
<body>

	<h1>Shopping list</h1>

	<form method="post">
		<input name="title" required type="text"
			placeholder=" type item here..." autofocus /> <input type="submit"
			value="Add to list"  />
	</form>

	<ul>
		<c:forEach items="${ items }" var="shoppingListItem">
			<li id="product-${shoppingListItem.getId() }">
			<c:out value="${ shoppingListItem.getTitle() }  " />
			<button onclick="removeProduct(${ shoppingListItem.getId() })">Remove</button>
			</li>
			
		</c:forEach>
	</ul>


</body>
</html>