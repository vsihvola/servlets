package json;

import com.google.gson.Gson;

import model.ShoppingListItem;

public class JsonReadingExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String jsonString = "{ 'id': 100, 'title': 'Milk'}";
		Gson gson = new Gson();

		ShoppingListItem item = gson.fromJson(jsonString, ShoppingListItem.class);

		System.out.println(item);
	}

}
