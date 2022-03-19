import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

public class GenerateRating {
    public static int randInt(int min, int max) {
        return min + (int)(Math.random() * ((max - min) + 1));
    }

    public static long randEpoch(long epoch1, long epoch2){
        long randomEpoch = epoch1 + Math.abs(new Random().nextLong()) % (epoch2-epoch1);
        return randomEpoch;
    }

    public static void main(String[] args) throws FileNotFoundException {
        int numberOfRatings = 501;
        PrintWriter out = new PrintWriter("/Users/victoria/Desktop/5200/5200GroupProject/MovieMaster/processData/OutputData/Rating_output.csv");
        out.println("RatingId,UserId,MovieId,Score,Timestamp");
        for(int i = 1; i < numberOfRatings; i++){
            int score = randInt(1, 5);
            int uerId = randInt(1, 30);
            int movieId = randInt(500, 800);
            // 2019-10-27 to 2022-10-27
            long epoch = randEpoch(1572163622, 1635322022);
            out.println(i + "," + uerId + "," + movieId + "," + score + "," + epoch);
        }
        out.close();
    }
}
