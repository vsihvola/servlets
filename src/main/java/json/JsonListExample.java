package json;

import java.util.List;

import com.google.gson.Gson;

import model.ShoppingListItem;

public class JsonListExample {

	public static void main(String[] args) {
		List<ShoppingListItem> items = List.of(new ShoppingListItem(1, "Milk"), new ShoppingListItem(2, "Coffee"),
				new ShoppingListItem(3, "Bread"));

		String json = new Gson().toJson(items);
		System.out.println(json);

	}

}
