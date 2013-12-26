package com.amatellanes.pelicularest;

public class Pelicula {

	private String title;
	private int year;
	private String[] writers;
	private String[] actors;
	private String plot_simple;
	private Poster poster;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String[] getWriters() {
		return writers;
	}

	public void setWriters(String[] writers) {
		this.writers = writers;
	}

	public String[] getActors() {
		return actors;
	}

	public void setActors(String[] actors) {
		this.actors = actors;
	}

	public String getPlot_simple() {
		return plot_simple;
	}

	public void setPlot_simple(String plot_simple) {
		this.plot_simple = plot_simple;
	}

	public Poster getPoster() {
		return poster;
	}

	public void setPoster(Poster poster) {
		this.poster = poster;
	}

}
