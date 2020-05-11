package json;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;

import com.google.gson.Gson;

import json.dicogs.Artist;
import json.dicogs.Release;
import json.dicogs.ReleasesResponse;

public class DiscogsExample {

	public static void main(String[] args) throws Exception {
		String json = get("https://api.discogs.com/artists/2209399");

		Gson gson = new Gson();

		Artist some = gson.fromJson(json, Artist.class);

		System.out.println(some.getName());
		System.out.println(some.getReleases_url());

		String releasesJson = get(some.getReleases_url());

		ReleasesResponse response = gson.fromJson(releasesJson, ReleasesResponse.class);

		List<Release> albums = response.getReleases();

		for (Release album : albums) {
			System.out.println(album.getTitle());
		}
	}

	public static String get(String uri) throws Exception {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(uri)).build();

		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

		return response.body();
	}

}
