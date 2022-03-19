package movie.model;

public class Recommendations {
	protected int recommendationId;
	protected Users user;
	protected Movies movie;
	public Recommendations(int recommendationId, Users user, Movies movie) {
		this.recommendationId = recommendationId;
		this.user = user;
		this.movie = movie;
	}
	public Recommendations(Users user, Movies movie) {
		this.user = user;
		this.movie = movie;
	}
	public int getRecommendationId() {
		return recommendationId;
	}
	public void setRecommendationId(int recommendationId) {
		this.recommendationId = recommendationId;
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
}
