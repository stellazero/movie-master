package movie.model;

import java.util.Date;

public class Ratings {
	
	protected int ratingId;
	protected int score;
	protected Users user;
	protected Movies movie;
	protected Date times;
	public Ratings(int ratingId, int score, Users user, Movies movie, Date times) {
		super();
		this.ratingId = ratingId;
		this.score = score;
		this.user = user;
		this.movie = movie;
		this.times = times;
	}
	public Ratings(int score, Users user, Movies movie, Date times) {
		super();
		this.score = score;
		this.user = user;
		this.movie = movie;
		this.times = times;
	}
	public Ratings(int ratingId) {
		super();
		this.ratingId = ratingId;
	}
	public int getRatingId() {
		return ratingId;
	}
	public void setRatingId(int ratingId) {
		this.ratingId = ratingId;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public Movies getMovie() {
		return movie;
	}
	public void setMovie(Movies movie) {
		this.movie = movie;
	}
	public Date getTimes() {
		return times;
	}
	public void setTime(Date times) {
		this.times = times;
	}

	
}