package movie.model;

public class Movies {
	protected int movieId;
	protected String title;
	protected int year;
	protected int duration;
	protected String languages;
	protected String description;
	
	public Movies(int movieId, String title, int year, int duration, String languages, String description) {
		this.movieId = movieId;
		this.title = title;
		this.year = year;
		this.duration = duration;
		this.languages = languages;
		this.description = description;
	}
	
	public Movies(int movieId) {
		this.movieId = movieId;
	}
	
	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	
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

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getLanguages() {
		return languages;
	}

	public void setLanguages(String languages) {
		this.languages = languages;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
