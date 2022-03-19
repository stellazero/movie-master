package movie.model;

public class Actors {
  protected int ActorId;
  protected String ActorName;

  public Actors(int actorId, String actorName) {
	  this.ActorId = actorId;
	  this.ActorName = actorName;
  }

  
  public Actors(String actorName) {
	super();
	this.ActorName = actorName;
}


public int getActorId() {
    return ActorId;
  }

  public void setActorId(int actorId) {
    this.ActorId = actorId;
  }

  public String getActorName() {
    return ActorName;
  }

  public void setActorName(String actorName) {
	  this.ActorName = actorName;
  }
}
