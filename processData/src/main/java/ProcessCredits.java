import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProcessCredits {
    public static List<String> imdb_title_id = new ArrayList<String>();
    public static List<String> title = new ArrayList<String>();
    public static List<String> year = new ArrayList<String>();
    public static List<String> genre = new ArrayList<String>();
    public static List<String> duration = new ArrayList<String>();
    public static List<String> country = new ArrayList<String>();
    public static List<String> language = new ArrayList<String>();
    public static List<String> directors = new ArrayList<String>();
    public static List<String> actors = new ArrayList<String>();
    public static List<String> description = new ArrayList<String>();
    public static Map<String, Integer> directorsIdMap = new HashMap<>();
    public static Map<String, Integer> actorsIdMap = new HashMap<>();
    public static Map<String, Integer> keywordsIdMap = new HashMap<>();

    public static void readFile(String path){
        List<String[]> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line = "";
            while ((line = reader.readLine()) != null){
                String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                for(String t : tokens) {
                    t = t.replace("\"", "");
                    System.out.println("> "+t);
                }
                imdb_title_id.add(tokens[0]);
                title.add(tokens[1]);
                year.add(tokens[2]);
                genre.add(tokens[3]);
                duration.add(tokens[4]);
                country.add(tokens[5]);
                language.add(tokens[6]);
                directors.add(tokens[7]);
                actors.add(tokens[8]);
                description.add(tokens[9]);

                System.out.println("");
                lines.add(tokens);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getMovieTable() throws FileNotFoundException {
        PrintWriter out = new PrintWriter("/Users/victoria/Desktop/5200/5200GroupProject/MovieMaster/processData/OutputData/Movies_output.csv");
        int id = 1;
        out.println("MovieId,Title,Year,Duration,Language,Description");
        for (int i = 1; i < imdb_title_id.size(); i++){
            imdb_title_id.set(i, String.valueOf(i));
            out.println(id++ + "," + title.get(i) + "," + year.get(i) + "," + duration.get(i)+ ","
                    + language.get(i)+ "," + description.get(i));
        }
        out.close();
    }

    public static void getDirectorTable() throws FileNotFoundException {
        PrintWriter out = new PrintWriter("/Users/victoria/Desktop/5200/5200GroupProject/MovieMaster/processData/OutputData/Directors_output.csv");
        List<String[]> DirectorList = new ArrayList<>();
        for (String aMovieDirector: directors){
            String[] tokens = aMovieDirector.split(",");
            DirectorList.add(tokens);
        }
        out.println("DirectorId,DirectorName");
        int id = 1;
        for (int i = 1; i < imdb_title_id.size(); i++){
            for (String t: DirectorList.get(i)){
                t = t.replace("\"", "").strip();
                if (!directorsIdMap.containsKey(t) && t!="") directorsIdMap.put(t, id++);
            }
        }
        for (Map.Entry<String,Integer> entry : directorsIdMap.entrySet()){
            out.println(entry.getValue()+","+entry.getKey());
        }
        out.close();
    }

    public static void getActorTable() throws FileNotFoundException {
        PrintWriter out = new PrintWriter("/Users/victoria/Desktop/5200/5200GroupProject/MovieMaster/processData/OutputData/Actors_output.csv");
        List<String[]> actorList = new ArrayList<>();
        for (String anActor: actors){
            String[] tokens = anActor.split(",");
            actorList.add(tokens);
        }
        out.println("ActorId,ActorName");
        int actorId = 1;
        for (int i = 1; i < imdb_title_id.size(); i++){
            for (String t: actorList.get(i)){
                t = t.replace("\"", "").strip();
                if (!actorsIdMap.containsKey(t) && t!="") actorsIdMap.put(t, actorId++);
            }

        }
        for (Map.Entry<String,Integer> entry : actorsIdMap.entrySet()){
            out.println(entry.getValue()+","+entry.getKey());
        }
        out.close();
    }

    public static void getCollaborationTable() throws FileNotFoundException {
        PrintWriter out = new PrintWriter("/Users/victoria/Desktop/5200/5200GroupProject/MovieMaster/processData/OutputData/Collaboration_output.csv");
        out.println("CollaborationId,MovieId,ActorId,ActorName,DirectorId,DirectorName");
        List<String[]> DirectorList = new ArrayList<>();
        for (String aMovieDirector: directors){
            String[] tokens = aMovieDirector.split(",");
            DirectorList.add(tokens);
        }
        List<String[]> actorList = new ArrayList<>();
        for (String anActor: actors){
            String[] tokens = anActor.split(",");
            actorList.add(tokens);
        }
        int id = 1;
        for (int i = 1; i < imdb_title_id.size(); i++){
            for (String t_director: DirectorList.get(i)) {
                t_director = t_director.replace("\"", "").strip();
                for (String t_actor : actorList.get(i)) {
                    t_actor = t_actor.replace("\"", "").strip();
                    out.println(id++ + "," + imdb_title_id.get(i) + "," + actorsIdMap.get(t_actor) + "," + t_actor + ","
                            + directorsIdMap.get(t_director) + "," + t_director);
                }
            }
        }
        out.close();
    }

    public static void getKeywordsTable() throws FileNotFoundException {
        PrintWriter out = new PrintWriter("/Users/victoria/Desktop/5200/5200GroupProject/MovieMaster/processData/OutputData/Keywords_output.csv");
        List<String[]> keywordList = new ArrayList<>();
        for (String keywords: genre){
            String[] tokens = keywords.split(",");
            keywordList.add(tokens);
        }
        out.println("KeywordsId,KeywordName");
        int keywordsId = 1;
        for (int i = 1; i < imdb_title_id.size(); i++){
            for (String t: keywordList.get(i)){
                t = t.replace("\"", "").strip();
                if (!keywordsIdMap.containsKey(t) && t!="") keywordsIdMap.put(t, keywordsId++);
            }
        }
        for (Map.Entry<String,Integer> entry : keywordsIdMap.entrySet()){
            out.println(entry.getValue()+","+entry.getKey());
        }
        out.close();
    }

    public static void getKeywordsInMovieTable() throws FileNotFoundException{
        PrintWriter out = new PrintWriter("/Users/victoria/Desktop/5200/5200GroupProject/MovieMaster/processData/OutputData/KeywordsInMovie_output.csv");
        List<String[]> keywordList = new ArrayList<>();
        for (String keywords: genre){
            String[] tokens = keywords.split(",");
            keywordList.add(tokens);
        }
        out.println("KeywordsInMovieId,KeywordsId,MovieId");
        int keywordsInMovieId = 1;
        for (int i = 1; i < imdb_title_id.size(); i++){
            for (String t: keywordList.get(i)){
                t = t.replace("\"", "").strip();
                out.println(keywordsInMovieId++ + "," + keywordsIdMap.get(t) + "," + imdb_title_id.get(i));
            }
        }
        out.close();
    }

    public static void main(String[] args) throws FileNotFoundException {
        String path = "/Users/victoria/Desktop/5200/5200GroupProject/MovieMaster/processData/InputData/IMDb movies.csv";
        readFile(path);
        getMovieTable();
        getDirectorTable();
        getActorTable();
        getCollaborationTable();
        getKeywordsTable();
        getKeywordsInMovieTable();
    }
}
