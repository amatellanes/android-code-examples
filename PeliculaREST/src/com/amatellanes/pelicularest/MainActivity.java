package com.amatellanes.pelicularest;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.kevinsawicki.http.HttpRequest;
import com.github.kevinsawicki.http.HttpRequest.HttpRequestException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

public class MainActivity extends Activity {

	private EditText inputPelicula;

	private ImageView ivPoster;
	private TextView tvTitle, tvWritters, tvActors, tvPlot;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		prepareUI();
	}

	private void prepareUI() {
		inputPelicula = (EditText) findViewById(R.id.input_pelicula);

		ivPoster = (ImageView) findViewById(R.id.ivPoster);
		tvTitle = (TextView) findViewById(R.id.tvTitle);
		tvWritters = (TextView) findViewById(R.id.tvWritters);
		tvActors = (TextView) findViewById(R.id.tvActors);
		tvPlot = (TextView) findViewById(R.id.tvPlot);
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
			List<Pelicula> peliculas = getPeliculas(response);

			if (!peliculas.isEmpty())
				mostrarPelicula(peliculas.get(0));
		}
	}

	private void mostrarPelicula(Pelicula pelicula) {
		tvTitle.setText(pelicula.getTitle());
		tvWritters.setText(Arrays.toString(pelicula.getWriters()));
		tvActors.setText(Arrays.toString(pelicula.getActors()));
		tvPlot.setText(pelicula.getPlot_simple());
		if (pelicula.getPoster() != null
				&& pelicula.getPoster().getImdb() != null) {
			Picasso.with(getApplicationContext())
					.load(pelicula.getPoster().getImdb()).into(ivPoster);
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
