import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class GenerateRecommendation {
    public static int randInt(int min, int max) {
        return min + (int)(Math.random() * ((max - min) + 1));
    }

    public static void main(String[] args) throws FileNotFoundException {
        int numberOfRecommendations = 501;
        PrintWriter out = new PrintWriter("/Users/victoria/Desktop/5200/5200GroupProject/MovieMaster/processData/OutputData/Recommendation_output.csv");
        out.println("RecommendationId,UserId,MovieId");
        for(int i = 1; i < numberOfRecommendations; i++){
            int movieId = randInt(2000, 2400);
            int userId = randInt(1, 30);
            out.println(i + "," + userId + "," + movieId);
        }
        out.close();
    }
}
