package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.JDBCShoppingListItemDao;
import database.ShoppingListItemDao;
import model.ShoppingListItem;

@WebServlet("/list")
public class ShoppingListServlet extends HttpServlet {

	private ShoppingListItemDao dao = new JDBCShoppingListItemDao();

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<ShoppingListItem> allItems = this.dao.getAllItems();

		req.setAttribute("items", allItems);

		req.getRequestDispatcher("/WEB-INF/shoppingList/list.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// todo: get the product title from request parameters
		// todo: use the title to create a new product object
		// todo: use the DAO to store the product object into the database
		ShoppingListItem item = new ShoppingListItem(req.getParameter("title"));
		this.dao.addItem(item);
		resp.sendRedirect("/list");
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// TODO: selvitä mikä `id` annettiin pyynnön mukana
		// TODO: käytä dao-olioita id-arvoa vastaavan olion poistamiseksi kannasta

		long id = Long.parseLong(req.getParameter("id"));
		ShoppingListItem item = dao.getItem(id);
		if (item != null) {
			dao.removeItem(item);
		}

	}
}
