package movie.model;

public class Directors {
  protected Integer DirectorId;
  protected String Name;

  public Directors(Integer directorId, String name) {
    DirectorId = directorId;
    Name = name;
  }

  public Directors(String name) {
	super();
	Name = name;
}

public Integer getDirectorId() {
    return DirectorId;
  }

  public void setDirectorId(Integer directorId) {
    DirectorId = directorId;
  }

  public String getName() {
    return Name;
  }

  public void setName(String name) {
    Name = name;
  }
}

