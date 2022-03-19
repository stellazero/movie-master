package movie.model;



public class Keywords {
	protected int keywordId;
	protected String keywordName;

	public Keywords(int keywordId, String keywordName) {
		this.keywordId = keywordId;
		this.keywordName = keywordName;	
	}
	public Keywords(int keywordId) {
		this.keywordId = keywordId;
	}
	public Keywords(String keywordName) {
		this.keywordName = keywordName;
	}
	public int getKeywordId() {
		return keywordId;
	}
	public void setKeywordId(int keywordId) {
		this.keywordId = keywordId;
	}
	public String getKeywordName() {
		return keywordName;
	}
	public void setKeywordName(String keywordName) {
		this.keywordName = keywordName;
	}
}
