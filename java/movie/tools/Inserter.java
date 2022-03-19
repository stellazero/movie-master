package movie.tools;

import movie.dal.*;
import movie.model.*;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;


/**
 * main() runner, used for the app demo.
 * 
 * Instructions:
 * 1. Create a new MySQL schema and then run the CREATE TABLE statements from lecture:
 * http://goo.gl/86a11H.
 * 2. Update ConnectionManager with the correct user, password, and schema.
 */
public class Inserter {

	public static void main(String[] args) throws SQLException {
		// DAO instances.

		UsersDao usersDao = UsersDao.getInstance();
		CreditCardsDao creditCardsDao = CreditCardsDao.getInstance();
		MoviesDao moviesDao = MoviesDao.getInstance();
		RecommendationsDao recommendationsDao = RecommendationsDao.getInstance();
		FavouritesDao favouritesDao = FavouritesDao.getInstance();
		RatingsDao ratingsDao = RatingsDao.getInstance();
		DirectorsDao directorsDao = DirectorsDao.getInstance();
		ActorsDao actorsDao = ActorsDao.getInstance();
	    CollaborationDao collaborationDao = CollaborationDao.getInstance();
	    KeywordsDao keywordsDao = KeywordsDao.getInstance();
	    KeywordsInMovieDao keywordsInMovieDao = KeywordsInMovieDao.getInstance();
	    Date date = new Date();
		
		// INSERT objects from our model.
		Users user = new Users(001,"111","Andy","A","B","ab@email.com",111111);
		user = usersDao.create(user);
		Users user1 = new Users(002,"222","Bruce","B","C","bc@email.com",222222);
		user1 = usersDao.create(user1);
		Users user2 = new Users(003,"333","Candice","C","D","cd@email.com",333333);
		user2 = usersDao.create(user2);
		
		CreditCards creditCard = new CreditCards(1111222233334444L,new Date(2022-04-30),"Andy",001);
		creditCard = creditCardsDao.create(creditCard);
		CreditCards creditCard1 = new CreditCards(4444333322221111L,new Date(2021-11-28),"Bruce", 002);
		creditCard1 = creditCardsDao.create(creditCard1);
		CreditCards creditCard2 = new CreditCards(1111222277778888L,new Date(2023-01-01),"Candice", 003);
		creditCard2 = creditCardsDao.create(creditCard2);
		
		Movies movie1 = new Movies(1, "movie1", 2021, 120, "Chinese", "test");
		movie1 = moviesDao.create(movie1);
		Movies movie2 = new Movies(2, "movie2", 2021, 120, "English", "test");
		movie2 = moviesDao.create(movie2);
		Movies movie3 = new Movies(3, "movie3", 2021, 120, "French", "test");
		movie3 = moviesDao.create(movie3);
		Movies movie4 = new Movies(4, "movie4", 2020, 120, "French", "test");
		movie4 = moviesDao.create(movie4);
		Movies dummy = new Movies(5, "dummy", 2020, 120, "dummy", "dummy");
		dummy = moviesDao.create(dummy);

		Recommendations recommendation1 = new Recommendations(0,user,movie1);
		Recommendations recommendation2 = new Recommendations(0,user,movie2);
		Recommendations recommendation3 = new Recommendations(0,user,movie3);
		Recommendations recommendation4 = new Recommendations(0,user2,movie1);
		Recommendations recommendation5 = new Recommendations(0,user2,movie4);
		recommendation1  = recommendationsDao.create(recommendation1);
		recommendation2  = recommendationsDao.create(recommendation2);
		recommendation3  = recommendationsDao.create(recommendation3);
		recommendation4  = recommendationsDao.create(recommendation4);
		recommendation5  = recommendationsDao.create(recommendation5);
		
		Actors actor1 = new Actors(0,"actor1");
		Actors actor2 = new Actors(0,"actor2");
		Actors actor3 = new Actors(0,"actor3");
		Actors actor4 = new Actors(0,"actor4");
		actor1 = actorsDao.create(actor1);
		actor2 = actorsDao.create(actor2);
		actor3 = actorsDao.create(actor3);
		actor4 = actorsDao.create(actor4);
		
		Directors director1 = new Directors(0, "director1");
		Directors director2 = new Directors(0, "director2");
		Directors director3 = new Directors(0, "director3");
		Directors director4 = new Directors(0, "director4");
		director1 = directorsDao.create(director1);
		director2 = directorsDao.create(director2);
		director3 = directorsDao.create(director3);
		director4 = directorsDao.create(director4);
		
		Collaboration collab1 = new Collaboration(0, movie1, actor1,director1);
		Collaboration collab2 = new Collaboration(0, movie1, actor1,director2);		
		Collaboration collab3 = new Collaboration(0, movie1, actor2,director1);
		Collaboration collab4 = new Collaboration(0, movie1, actor2,director2);
		Collaboration collab5 = new Collaboration(0, movie2, actor3,director3);
		Collaboration collab6 = new Collaboration(0, movie2, actor4,director3);
		Collaboration collab7 = new Collaboration(0, movie2, actor3,director4);
		Collaboration collab8 = new Collaboration(0, movie2, actor4,director4);
		Collaboration collab9 = new Collaboration(0, movie3, actor4,director4);
		Collaboration collab10 = new Collaboration(0, movie4, actor1,director4);
		collab1 = collaborationDao.create(collab1);
		collab2 = collaborationDao.create(collab2);
		collab3 = collaborationDao.create(collab3);
		collab4 = collaborationDao.create(collab4);
		collab5 = collaborationDao.create(collab5);
		collab6 = collaborationDao.create(collab6);
		collab7 = collaborationDao.create(collab7);
		collab8 = collaborationDao.create(collab8);
		collab9 = collaborationDao.create(collab9);
		collab10 = collaborationDao.create(collab10);
		
		Keywords keyword1 = new Keywords(0, "drama");
		Keywords keyword2 = new Keywords(0, "history");
		Keywords keyword3 = new Keywords(0, "action");
		keyword1 = keywordsDao.create(keyword1);
		keyword2 = keywordsDao.create(keyword2);
		keyword3 = keywordsDao.create(keyword3);
		
		KeywordsInMovie km1 = new KeywordsInMovie(0,keyword1, movie1);
		KeywordsInMovie km2 = new KeywordsInMovie(0,keyword2, movie1);
		KeywordsInMovie km3 = new KeywordsInMovie(0,keyword3, movie2);
		km1 = keywordsInMovieDao.create(km1);
		km2 = keywordsInMovieDao.create(km2);
		km3 = keywordsInMovieDao.create(km3);
		
		
		Favourites favourite = new Favourites(user, movie1, false);
		Favourites favourite1 = new Favourites(user1, movie1, false);
		Favourites favourite2 = new Favourites(user1, movie2, false);
		
		Ratings rating = new Ratings(2, user, movie1, date);
		Ratings rating1 = new Ratings(3, user1, movie1, date);
		Ratings rating2 = new Ratings(5, user2, movie1, date);
		Ratings rating3 = new Ratings(2, user1, movie2, date);
		
		
		// READ.
		
		Users u1 = usersDao.getUserByUserId(001);
		System.out.format("Reading user: ui:%s u:%s f:%s l:%s \n",u1.getUserId(), u1.getUserName(), u1.getFirstName(), u1.getLastName());

		CreditCards cc1 = creditCardsDao.getCreditCardByCardNumber(1111222233334444L);
		System.out.format("Reading CreditCard: cn:%s e:%s u:%s id%s \n",
				cc1.getCardNumber(), cc1.getExpiration() ,cc1.getUserName() ,cc1.getUserId());

		List<CreditCards> ccList1 = creditCardsDao.getCreditCardsByUserId(002);
		for(CreditCards card : ccList1) {
			System.out.format("Looping administrators: u:%s f:%s l:%s id:%s \n",
					card.getCardNumber(), card.getExpiration() ,card.getUserName() ,card.getUserId());
		}
		
		System.out.println("test getMovieByMovieId()");
		Movies m1 = moviesDao.getMovieByMovieId(1);
		System.out.format("Reading movies: movieId:%s title:%s year:%s duration:%s languages:%s description:%s \n",
				m1.getMovieId(), m1.getTitle(),m1.getYear(), m1.getDuration(), 
				m1.getLanguages(), m1.getDescription());
		  
		System.out.println("test getMovieByYear()");
		  List<Movies> movies = moviesDao.getMovieByYear(2021);
		  for (Movies m: movies) {
		  System.out.format("Reading movies: movieId:%s title:%s year:%s duration:%s languages:%s description:%s \n",
				  m.getMovieId(), m.getTitle(),m.getYear(), m.getDuration(), 
				  m.getLanguages(), m.getDescription());
		  }
		  
		  System.out.println("test getMovieByLanguage()");
		  List<Movies> movies2 = moviesDao.getMovieByLanguage("French");
		  for (Movies m: movies2) {
		  System.out.format("Reading movies: movieId:%s title:%s year:%s duration:%s languages:%s description:%s \n",
				  m.getMovieId(), m.getTitle(),m.getYear(), m.getDuration(), 
				  m.getLanguages(), m.getDescription());
		  }
		  
		  System.out.println("test getMovieByTitle()");
		  List<Movies> movies3 = moviesDao.getMovieByTitle("movie1");
		  for (Movies m: movies3) {
		  System.out.format("Reading movies: movieId:%s title:%s year:%s duration:%s languages:%s description:%s \n",
				  m.getMovieId(), m.getTitle(),m.getYear(), m.getDuration(), 
				  m.getLanguages(), m.getDescription());
		  }
		  
		  System.out.println("test getMovieByActorName()");
		  List<Movies> movies4 = moviesDao.getMovieByActorName("actor1");
		  for (Movies m: movies4) {
		  System.out.format("Reading movies: movieId:%s title:%s year:%s duration:%s languages:%s description:%s \n",
				  m.getMovieId(), m.getTitle(),m.getYear(), m.getDuration(), 
				  m.getLanguages(), m.getDescription());
		  }
		  
		  System.out.println("test getMovieByDirectorName()");
		  List<Movies> movies5 = moviesDao.getMovieByDirectorName("director1");
		  for (Movies m: movies5) {
		  System.out.format("Reading movies: movieId:%s title:%s year:%s duration:%s languages:%s description:%s \n",
				  m.getMovieId(), m.getTitle(),m.getYear(), m.getDuration(), 
				  m.getLanguages(), m.getDescription());
		  }
		  
		  System.out.println("test getRecommendationByUserId()");
		  List<Recommendations> rec1 = recommendationsDao.getRecommendationByUserId(1);
		  for (Recommendations r: rec1) {
		  System.out.format("Reading movies: recommendationId:%s userId:%s movieId:%s \n",
				 r.getRecommendationId(), r.getUser().getUserId(), r.getMovie().getMovieId());
		  }
		  
		  System.out.println("test getRecommendationByMovieId()");
		  List<Recommendations> rec2 = recommendationsDao.getRecommendationByMovieId(1);
		  for (Recommendations r: rec2) {
		  System.out.format("Reading movies: recommendationId:%s userId:%s movieId:%s \n",
				 r.getRecommendationId(), r.getUser().getUserId(), r.getMovie().getMovieId());
		  }
		  
		  Keywords k1 = keywordsDao.getKeywordById(1);
		  System.out.format("Reading keyword: keywordId:%s key:%s \n",
					 k1.getKeywordId(), k1.getKeywordName());
		  
		  KeywordsInMovie km = keywordsInMovieDao.getKeywordsInMovieById(1);
		  System.out.format("Reading keywordsInMovie: keywordInMovieId:%s key:%s movieTitle:%s \n",
					 km.getKeywordsInMovieId(), km.getKeyword().getKeywordName(), km.getMovie().getTitle());
		  List<KeywordsInMovie> kmList = keywordsInMovieDao.getKeywordNameByMovieTitle("movie1");
		  for (KeywordsInMovie key: kmList) {
			  System.out.format("Reading keywordsInMovie from list:  keywordInMovieId:%s key:%s movieTitle:%s \n",
			  key.getKeywordsInMovieId(), key.getKeyword().getKeywordName(), key.getMovie().getTitle());
		  }
		  
		  
		  Favourites f1 = favouritesDao.create(favourite);
		  System.out.format("Reading new favorite: FavouritesId:%s User:%s Movie:%s Recommended: %s \n",
					f1.getFavouritesId(), f1.getUser().getUserName(), f1.getMovie().getTitle(), f1.isRecommended());
			
		  Favourites f2 = favouritesDao.create(favourite1);
		  Favourites f3 = favouritesDao.create(favourite2);
		  

		  List<Favourites> flist1 = favouritesDao.getFavouritesForUser(user1);
		  for (Favourites f: flist1) {
			  System.out.format("Looping favorite for USER1: FavouritesId:%s User:%s Movie:%s Recommended: %s \n",
			  f.getFavouritesId(), f.getUser().getUserName(), f.getMovie().getTitle(), f.isRecommended());
			}
			
		  System.out.print("test getFavouriteById()\n");
		  Favourites rf2 = favouritesDao.getFavouriteById(2);
   		  System.out.format("Reading Favourites: FavouritesId:%s User:%s Movie:%s Recommended: %s \n",
		  rf2.getFavouritesId(), rf2.getUser().getUserName(), rf2.getMovie().getTitle(), rf2.isRecommended());

   		  Ratings r1 = ratingsDao.create(rating);

   		  System.out.format("Reading rating: RatingsId:%s User:%s Movie:%s score: %s times: %s \n",
			r1.getRatingId(), r1.getUser().getUserName(),r1.getMovie().getTitle(), r1.getScore(), r1.getTimes());
   		  
   		  System.out.print("test getRatingsById()\n");
   		  Ratings r2 = ratingsDao.getRatingById(1);
   		  System.out.format("Reading rating: RatingsId:%s User:%s Movie:%s score: %s times: %s \n",
   				r2.getRatingId(), r2.getUser().getUserName(),r2.getMovie().getTitle(), r2.getScore(), r2.getTimes());
   	   		  
	
   		  Ratings r3 = ratingsDao.create(rating1);
   		  Ratings r4 = ratingsDao.create(rating2);
   		  Ratings r5 = ratingsDao.create(rating3);
   		  
   		  System.out.print("test getRatingsByMovieId()\n");
  		  List<Ratings> rlist1 = ratingsDao.getRatingsByMovieId(1);
  		  for (Ratings r: rlist1) {
  		  System.out.format("Looping rating for movie1: RatingsId:%s User:%s Movie:%s score: %s times: %s \n",
  				r.getRatingId(), r.getUser().getUserName(),r.getMovie().getTitle(), r.getScore(), r.getTimes());
  		  }

   		  System.out.print("test getRatingsByUserId()\n");
  		  List<Ratings> rlist2 = ratingsDao.getRatingsByUserId(2);
  		  for (Ratings r: rlist2) {
  		  System.out.format("Looping rating for User: RatingsId:%s User:%s Movie:%s score: %s times: %s \n",
  				r.getRatingId(), r.getUser().getUserName(),r.getMovie().getTitle(), r.getScore(), r.getTimes());
  		  }
		//Update
		
		CreditCards ccu = creditCardsDao.updateExpiration(creditCard2, date);
		System.out.format("Reading CreditCard: cn:%s e:%s u:%s id:%s \n",
				ccu.getCardNumber(), ccu.getExpiration() ,ccu.getUserName(), ccu.getUserId());
		
		Ratings r6 = ratingsDao.updateScore(rating, 5);
		System.out.format("Updating rating: RatingsId:%s User:%s Movie:%s score: %s times: %s \n",
   				r6.getRatingId(), r6.getUser().getUserName(),r6.getMovie().getTitle(), r6.getScore(), r6.getTimes());
   	   		  
		//Delete
		
		Users du = usersDao.delete(user);
		System.out.println("Deleted user:"+ du);
		
		CreditCards dcc = creditCardsDao.delete(creditCard);
		System.out.println("Deleted creditCard:"+ dcc);
	
		dummy = moviesDao.delete(dummy);
		System.out.println("test delete movies：" + dummy);
		
		Ratings r7 = ratingsDao.delete(rating);
		System.out.println("test delete Ratings：" + r7);
		
		Favourites f4 = favouritesDao.delete(favourite);
		System.out.println("test delete Favourites：" + f4);
		}   
	    
}
