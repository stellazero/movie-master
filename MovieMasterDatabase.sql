DROP SCHEMA IF EXISTS MovieMasterDatabase; 
CREATE SCHEMA IF NOT EXISTS MovieMasterDatabase; 
USE MovieMasterDatabase; 
DROP TABLE IF EXISTS KeywordInMovie; 
DROP TABLE IF EXISTS Keywords;
DROP TABLE IF EXISTS Favourites;
DROP TABLE IF EXISTS Collaboration;
DROP TABLE IF EXISTS Director;
DROP TABLE IF EXISTS Actor;
DROP TABLE IF EXISTS Recommendations;
DROP TABLE IF EXISTS Ratings;
DROP TABLE IF EXISTS Movies;
DROP TABLE IF EXISTS CreditCard; 
DROP TABLE IF EXISTS Users;


CREATE TABLE Users(
	UserId INT AUTO_INCREMENT,
	Password VARCHAR(255) NOT NULL,
	UserName VARCHAR(255) NOT NULL,
	FirstName VARCHAR(255) NOT NULL,
	LastName VARCHAR(255) NOT NULL,
    Phone INT,
	Email VARCHAR(255) NOT NULL,
	CONSTRAINT pk_Users_UserId PRIMARY KEY (UserId)
);

CREATE TABLE CreditCards(
	UserId INT,
	UserName VARCHAR(255),
	CardNumber BIGINT,
	Expiration DATE,
	CONSTRAINT pk_CreditCard_CardNumber PRIMARY KEY(CardNumber),
	CONSTRAINT fk_CreditCard_UserId FOREIGN KEY(UserId)
		REFERENCES Users(UserId)
		ON UPDATE CASCADE ON DELETE SET NULL
);

CREATE TABLE Movies(
	MovieId INT AUTO_INCREMENT,
	Title VARCHAR(255) NOT NULL,
	Year INT, 
	Duration INT NOT NULL,
	Languages VARCHAR(255),
    Description VARCHAR(255),
	CONSTRAINT pk_Movies_MovieId PRIMARY KEY (MovieId)
);

CREATE TABLE Ratings(
	RatingId INT AUTO_INCREMENT,
	UserId INT,
	MovieId INT,
	Score INT,
	Time TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
	CONSTRAINT pk_Rating_RatingId PRIMARY KEY (RatingId),
	CONSTRAINT fk_Rating_MovieId FOREIGN KEY (MovieId)
		REFERENCES Movies (MovieId)
	ON UPDATE CASCADE ON DELETE SET NULL,
	CONSTRAINT fk_Rating_UserId FOREIGN KEY (UserId)
    		REFERENCES Users(UserId) 
   		ON UPDATE CASCADE ON DELETE SET NULL

);

CREATE TABLE Recommendations ( 
RecommendationId INT AUTO_INCREMENT, 
UserId INT,
MovieId INT,
CONSTRAINT pk_Recommendation_RecommendationID PRIMARY KEY (RecommendationID), 
CONSTRAINT fk_Recommendation_MovieId FOREIGN KEY (MovieId)
REFERENCES Movies(MovieId) 
ON UPDATE CASCADE ON DELETE CASCADE,
CONSTRAINT fk_Recommendation_UserId FOREIGN KEY (UserId) 
  		REFERENCES Users(UserId) 
  		ON UPDATE CASCADE ON DELETE SET NULL
);


CREATE TABLE Actors(
	ActorId INT AUTO_INCREMENT,
	ActorName VARCHAR(255),
	CONSTRAINT pk_Actor_ActorId PRIMARY KEY (ActorId)
);

CREATE TABLE Directors(
	DirectorId INT AUTO_INCREMENT,
	Name VARCHAR(255),
	CONSTRAINT pk_Director_DirectorId PRIMARY KEY (DirectorId)
);

CREATE TABLE Collaboration ( 
	CollaborationId INT AUTO_INCREMENT,
    MovieId INT,
	ActorId INT,
    ActorName VARCHAR(255),
	DirectorId INT,
    DirectorName VARCHAR(255),
	CONSTRAINT pk_Collaboration_CollaborationId PRIMARY KEY (CollaborationId), 
	CONSTRAINT fk_Collaboration_DirectorId FOREIGN KEY (DirectorId) 
	REFERENCES Directors(DirectorId) 
	ON UPDATE CASCADE ON DELETE SET NULL,
	CONSTRAINT fk_Collaboration_ActorId FOREIGN KEY (ActorId) 
	REFERENCES Actors(ActorId) 
	ON UPDATE CASCADE ON DELETE SET NULL,
	CONSTRAINT fk_Collaboration_MovieId FOREIGN KEY (MovieId) 
	REFERENCES Movies(MovieId) 
	ON UPDATE CASCADE ON DELETE CASCADE
);


CREATE TABLE Favourites(
	FavouritesId INT AUTO_INCREMENT,
	UserId INT,
    MovieId INT,
	RecommendedByMovieMaster BOOL,
	CONSTRAINT pk_Favourites_FavouritesId PRIMARY KEY (FavouritesId),
	CONSTRAINT fk_Movies_MovieId FOREIGN KEY (MovieId)
    REFERENCES Movies (MovieId)
    ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT fk_Favourites_UserId FOREIGN KEY (UserId)
    REFERENCES Users (UserId)
	ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Keywords (
	KeywordId INT AUTO_INCREMENT,
	KeywordName VARCHAR(255), 
		CONSTRAINT pk_Keywords_KeywordId PRIMARY KEY (KeywordId) 
);

CREATE TABLE KeywordsInMovie(
	KeywordsInMovieId INT AUTO_INCREMENT,
	KeywordId INT,
	MovieId INT,
    CONSTRAINT pk_KeywordsInMovie_KeywordsInMovieId PRIMARY KEY(KeywordsInMovieId),
	CONSTRAINT fk_KeywordsInMovie_MovieId FOREIGN KEY(MovieId)
		REFERENCES Movies(MovieId)
		ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT fk_KeywordsInMovie_KeywordsId FOREIGN KEY(KeywordId)
		REFERENCES Keywords(KeywordId)
	ON UPDATE CASCADE ON DELETE SET NULL
); 


-- LOAD DATA LOCAL INFILE '/Users/victoria/Desktop/5200/MovieMaster/processData/OutputData/Movies_output.csv' INTO TABLE Movies
--   # Fields are not quoted.
--   FIELDS TERMINATED BY ','
--   # Windows platforms may need '\r\n'.#  # Windows platforms may need '\r\n'.
--   LINES TERMINATED BY '\n'
--   IGNORE 1 LINES;
-- 
--  
-- LOAD DATA LOCAL INFILE '/Users/victoria/Desktop/5200/MovieMaster/processData/OutputData/Actors_output.csv' INTO TABLE Actors
--   # Fields are not quoted.
--   FIELDS TERMINATED BY ','
--   # Windows platforms may need '\r\n'.#  # Windows platforms may need '\r\n'.
--   LINES TERMINATED BY '\n'
--   IGNORE 1 LINES;
--   
-- LOAD DATA LOCAL INFILE '/Users/victoria/Desktop/5200/MovieMaster/processData/OutputData/Directors_output.csv' INTO TABLE Directors
--   # Fields are not quoted.
--   FIELDS TERMINATED BY ','
--   # Windows platforms may need '\r\n'.#  # Windows platforms may need '\r\n'.
--   LINES TERMINATED BY '\n'
--   IGNORE 1 LINES;
-- 
-- 
-- LOAD DATA LOCAL INFILE '/Users/victoria/Desktop/5200/MovieMaster/processData/OutputData/Collaboration_output.csv' INTO TABLE Collaboration
--   # Fields are not quoted.
--   FIELDS TERMINATED BY ','
--   # Windows platforms may need '\r\n'.#  # Windows platforms may need '\r\n'.
--   LINES TERMINATED BY '\n'
--   IGNORE 1 LINES;
--   
-- LOAD DATA LOCAL INFILE '//Users/victoria/Desktop/5200/MovieMaster/processData/OutputData/Favourite_output.csv' INTO TABLE Favourites
--   # Fields are not quoted.
--   FIELDS TERMINATED BY ','
--   # Windows platforms may need '\r\n'.#  # Windows platforms may need '\r\n'.
--   LINES TERMINATED BY '\n'
--   IGNORE 1 LINES;
--   
-- LOAD DATA LOCAL INFILE '/Users/victoria/Desktop/5200/MovieMaster/processData/OutputData/Keywords_output.csv' INTO TABLE Keywords
--   # Fields are not quoted.
--   FIELDS TERMINATED BY ','
--   # Windows platforms may need '\r\n'.#  # Windows platforms may need '\r\n'.
--   LINES TERMINATED BY '\n'
--   IGNORE 1 LINES;
-- LOAD DATA LOCAL INFILE '/Users/victoria/Desktop/5200/MovieMaster/processData/OutputData/KeywordsInMovie_output.csv' INTO TABLE KeywordsInMovie
--   # Fields are not quoted.
--   FIELDS TERMINATED BY ','
--   # Windows platforms may need '\r\n'.#  # Windows platforms may need '\r\n'.
--   LINES TERMINATED BY '\n'
--   IGNORE 1 LINES;
-- 

-- LOAD DATA LOCAL INFILE '/Users/victoria/Desktop/5200/MovieMaster/processData/OutputData/Rating_output.csv' INTO TABLE Ratings
--   # Fields are not quoted.
--   FIELDS TERMINATED BY ','
--   # Windows platforms may need '\r\n'.#  # Windows platforms may need '\r\n'.
--   LINES TERMINATED BY '\n'
--   IGNORE 1 LINES;
-- LOAD DATA LOCAL INFILE '/Users/mengtianwang/Desktop/5200/Recommendation_output.csv' INTO TABLE Recommendations
--   # Fields are not quoted.
--   FIELDS TERMINATED BY ','
--   # Windows platforms may need '\r\n'.#  # Windows platforms may need '\r\n'.
--   LINES TERMINATED BY '\n'
--   IGNORE 1 LINES;
-- LOAD DATA LOCAL INFILE '/Users/victoria/Desktop/5200/MovieMaster/processData/OutputData/Creditcard_output.csv' INTO TABLE Creditcard
--   # Fields are not quoted.
--   FIELDS TERMINATED BY ','
--   # Windows platforms may need '\r\n'.#  # Windows platforms may need '\r\n'.
--   LINES TERMINATED BY '\n'
--   IGNORE 1 LINES;
--  



-- LOAD DATA LOCAL INFILE '/Users/victoria/Desktop/5200/MovieMaster/processData/OutputData/Users_output.csv' INTO TABLE Users
--   # Fields are not quoted.
--   FIELDS TERMINATED BY ','
--   # Windows platforms may need '\r\n'.#  # Windows platforms may need '\r\n'.
--   LINES TERMINATED BY '\n'
--   IGNORE 1 LINES;
--  
-- 


-- 
-- INSERT INTO Users(UserId, Password, UserName, FirstName, LastName, Phone,Email) VALUE(0,'123','user1','first','last',1234,'email');
-- INSERT INTO Users(UserId, Password, UserName, FirstName, LastName, Phone,Email) VALUE(0,'123','user2','first','last',1234,'email');
-- 
-- SELECT  Movies.MovieId, Movies.Title, Movies.Duration, Movies.Languages, Movies.Description FROM Movies
-- 	INNER JOIN  Collaboration
--     on Movies.MovieId = Collaboration.MovieId
--     WHERE Collaboration.ActorName = 'actor1'
--     GROUP BY Movies.MovieId;
-- 
-- 
-- 
-- SELECT Movies.Title, KeywordsInMovie.KeywordId, KeywordsInMovie.KeywordsInMovieId, KeywordsInMovie.MovieId
-- 	FROM Movies
-- 	INNER JOIN KeywordsInMovie ON
--     KeywordsInMovie.MovieId = Movies.MovieId
--     WHERE Movies.Title = 'movie1';
