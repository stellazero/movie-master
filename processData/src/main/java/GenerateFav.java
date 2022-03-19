import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

public class GenerateFav {
    public static int randInt(int min, int max) {
        return min + (int)(Math.random() * ((max - min) + 1));
    }

    public static boolean randBool(){
        Random rd = new Random();
        return rd.nextBoolean();
    }

    public static void main(String[] args) throws FileNotFoundException {
        int numberOfFavs = 501;
        PrintWriter out = new PrintWriter("/Users/victoria/Desktop/5200/5200GroupProject/MovieMaster/processData/OutputData/Favourite_output.csv");
        out.println("FavoritesId,UserId,MovieId,RecommendedByMovieMaster");
        for(int i = 1; i < numberOfFavs; i++){
            int movieId = randInt(2000, 2400);
            int userId = randInt(1, 30);
            boolean bool = randBool();
            out.println(i + "," + userId + "," + movieId + "," + bool);
        }
        out.close();
    }
}
