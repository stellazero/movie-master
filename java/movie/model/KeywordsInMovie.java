package movie.model;

public class KeywordsInMovie {
	protected int keywordsInMovieId;
	protected Keywords keyword;
	protected Movies movie;
	
	public KeywordsInMovie(int keywordsInMovieId, Keywords keyword, Movies movie) {
		this.keywordsInMovieId = keywordsInMovieId;
		this.keyword = keyword;
		this.movie = movie;
	}
	
	public KeywordsInMovie(int keywordsInMovieId) {
		this.keywordsInMovieId = keywordsInMovieId;
	}
	
	public KeywordsInMovie(Keywords keyword, Movies movie) {
		this.keyword = keyword;
		this.movie = movie;
	}
	public int getKeywordsInMovieId() {
		return keywordsInMovieId;
	}
	public void setKeywordsInMovieId(int keywordsInMovieId) {
		this.keywordsInMovieId = keywordsInMovieId;
	}
	public Keywords getKeyword() {
		return keyword;
	}
	public void setKeyword(Keywords keyword) {
		this.keyword = keyword;
	}
	public Movies getMovie() {
		return movie;
	}
	public void setMovieId(Movies movie) {
		this.movie = movie;
	}
}