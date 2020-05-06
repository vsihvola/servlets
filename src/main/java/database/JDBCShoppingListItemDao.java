package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.ShoppingListItem;

public class JDBCShoppingListItemDao implements ShoppingListItemDao {

	public List<ShoppingListItem> getAllItems() {

		// SQL string ready for statement
		String sql = "SELECT * FROM ShoppingListItem";

		// Connection variables
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet results = null;

		// List for items
		List<ShoppingListItem> items = new ArrayList<>();

		// Make connection
		try {

			conn = Database.connect();
			statement = conn.prepareStatement(sql);
			results = statement.executeQuery();

			// Print all lines and add to list.
			while (results.next()) {
				long id = results.getLong("id");
				String title = results.getString("title");
				ShoppingListItem newItem = new ShoppingListItem(id, title);
				items.add(newItem);
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			Database.close(conn, statement, results);

		}
		return items;
	}

	@Override
	public ShoppingListItem getItem(long id) {

		// SQL-lines
		String sqlGetInfo = "SELECT * FROM ShoppingListItem WHERE id = (?)";

		long tempId = 0;
		String tempTitle = null;

		// Connection variables
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet results = null;

		// Boolean if id exists
		boolean check = false;

		// Create connection
		try {

			conn = Database.connect();
			statement = conn.prepareStatement(sqlGetInfo);
			statement.setLong(1, id);
			results = statement.executeQuery();

			if (results.getLong("id") == id) {
				check = true;
				tempId = results.getLong("id");
				tempTitle = results.getString("title");
			} else {
				check = false;
			}

		} catch (SQLException e) {

		} finally {
			Database.close(conn, statement, results);

		}

		if (check == true) {
			return new ShoppingListItem(tempId, tempTitle);

		} else {
			return null;
		}

	}

	@Override
	public boolean addItem(ShoppingListItem newItem) {

		// SQL strings ready for statement
		String sqlInsertInto = "INSERT INTO ShoppingListItem (title) VALUES (?) ";
		String sqlGetInfo = "SELECT * FROM ShoppingListItem";

		// For connection
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet results = null;

		long automaticallyGeneratedId = 0;
		// Boolean for checking if there is item on the list already.
		boolean check = true;

		// Create connection
		try {

			conn = Database.connect();
			statement = conn.prepareStatement(sqlGetInfo);
			results = statement.executeQuery();

			// Check if shoppingList contains the item already
			while (results.next()) {

				if (results.getString("title").equals(newItem.getTitle())) {
					check = false;
				}

			}

			// Add item to shoppingList
			if (check == true) {
				PreparedStatement updateItem = conn.prepareStatement(sqlInsertInto, Statement.RETURN_GENERATED_KEYS);
				updateItem.setString(1, newItem.getTitle());
				updateItem.executeUpdate();

				ResultSet rs = updateItem.getGeneratedKeys();
				rs.next();

				automaticallyGeneratedId = rs.getInt(1);

				newItem.setId(automaticallyGeneratedId);

			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			Database.close(conn, statement, results);

		}
		return check;

	}

	@Override
	public boolean removeItem(ShoppingListItem item) {

		// SQL-lines
		String sqlGetInfo = "SELECT * FROM ShoppingListItem";
		String sqlDeleteTheItem = "DELETE FROM ShoppingListItem WHERE title = (?)";

		// Connection variables
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet results = null;

		// Boolean if removing is successful
		boolean check = false;

		// Create connection
		try {

			conn = Database.connect();
			statement = conn.prepareStatement(sqlGetInfo);
			results = statement.executeQuery();

			// tulostetaan kaikki tuloksena saadut rivit
			while (results.next()) {
				if (results.getString("title").equals(item.getTitle())) {

					PreparedStatement deleteFrom = conn.prepareStatement(sqlDeleteTheItem);
					deleteFrom.setString(1, item.getTitle());

					deleteFrom.execute();

					check = true;
					break;

				}
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			Database.close(conn, statement, results);

		}

		return check;

	}

}