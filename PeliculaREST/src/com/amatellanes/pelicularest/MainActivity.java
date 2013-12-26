package com.amatellanes.pelicularest;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ListView;

import com.github.kevinsawicki.http.HttpRequest;
import com.github.kevinsawicki.http.HttpRequest.HttpRequestException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

public class MainActivity extends Activity {

	private EditText inputPelicula;

	private ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Display a indeterminate progress bar on title bar
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);

		setContentView(R.layout.activity_main);

		this.listView = (ListView) findViewById(R.id.listView);

		inputPelicula = (EditText) findViewById(R.id.input_pelicula);
	}

	public void buscarPelicula(View view) {
		String titulo = inputPelicula.getText().toString();
		if (!TextUtils.isEmpty(titulo)) {
			String url = String.format(
					"http://mymovieapi.com/?title=%1$s&type=json&limit=10",
					titulo);
			new LoadFilmTask().execute(url);
		}
	}

	public static final String TAG = "com.amatellanes.pelicularest";

	private class LoadFilmTask extends AsyncTask<String, Long, String> {
		protected String doInBackground(String... urls) {
			try {
				return HttpRequest.get(urls[0]).accept("application/json")
						.body();
			} catch (HttpRequestException exception) {
				return null;
			}
		}

		protected void onPostExecute(String response) {
			Log.i(TAG, response);
			// TextView textView = (TextView) findViewById(R.id.texto);
			// ImageView image = (ImageView) findViewById(R.id.imagen);
			List<Pelicula> peliculas = getPeliculas(response);

			// Pelicula p = peliculas.get(0);
			listView.setAdapter(new ItemAdapter(getApplicationContext(),
					peliculas));

			// Picasso.with(context).load("http://i.imgur.com/DvpvklR.png").into(imageView);
			//
			// Picasso.with(getApplicationContext()).load(p.getPoster().getImdb())
			// .into(image);
		}
	}

	private List<Pelicula> getPeliculas(String json) {
		Gson gson = new Gson();
		Type type = new TypeToken<ArrayList<Pelicula>>() {
		}.getType();
		return gson.fromJson(json, type);
	}

	private String prettyfyJSON(String json) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(json);
		return gson.toJson(element);
	}
}
