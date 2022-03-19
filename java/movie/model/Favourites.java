package movie.model;

public class Favourites {
	protected int favouritesId;
	protected Users user;
	protected Movies movie;
	protected boolean isRecommended;
	public Favourites(int favouritesId, Users user, Movies movie, boolean isRecommended) {
		super();
		this.favouritesId = favouritesId;
		this.user = user;
		this.movie = movie;
		this.isRecommended = isRecommended;
	}
	public Favourites(Users user, Movies movie, boolean isRecommended) {
		super();
		this.user = user;
		this.movie = movie;
		this.isRecommended = isRecommended;
	}
	public Favourites(int favouritesId) {
		super();
		this.favouritesId = favouritesId;
	}
	public int getFavouritesId() {
		return favouritesId;
	}
	public void setFavouritesId(int favouritesId) {
		this.favouritesId = favouritesId;
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
	public boolean isRecommended() {
		return isRecommended;
	}
	public void setRecommended(boolean isRecommended) {
		this.isRecommended = isRecommended;
	}
	
	

}
