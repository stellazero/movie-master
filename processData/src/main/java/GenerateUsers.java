import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class GenerateUsers {
    final String emailLexicon = "abcdefghijklmnopqrstuvwxyz12345674890";
    final String userNameLexicon = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ12345674890!@#$%^&*()_{}:;?/";
    final String nameLexicon = "abcdefghijklmnopqrstuvwxyz";
    final java.util.Random rand = new java.util.Random();


    // consider using a Map<String,Boolean> to say whether the identifier is being used or not
    final Set<String> identifiers = new HashSet<String>();

    public String randomGenerate(String lexicon) {
        StringBuilder builder = new StringBuilder();
        while(builder.toString().length() == 0) {
            int length = rand.nextInt(5)+5;
            for(int i = 0; i < length; i++) {
                builder.append(lexicon.charAt(rand.nextInt(lexicon.length())));
            }
            if(identifiers.contains(builder.toString())) {
                builder = new StringBuilder();
            }
        }
        return builder.toString();
    }

    public List<String> generateRandomEmail(int num){
        List<String> emailList = new ArrayList<>();
        while(emailList.size() < num){
            String pref = randomGenerate(emailLexicon);
            String email = pref + "@gmail.com";
            if(!emailList.contains(email)) emailList.add(email);
        }
        return emailList;
    }

    public List<String> generateRandomUserName(int num){
        List<String> userList = new ArrayList<>();
        while(userList.size() < num){
            String name = randomGenerate(userNameLexicon);
            if(!userList.contains(name)) userList.add(name);
        }
        return userList;
    }

    public List<String> generateRandomName(int num){
        List<String> nameList = new ArrayList<>();
        while(nameList.size() < num) {
            String name = randomGenerate(nameLexicon);
            nameList.add(name);
        }
        return nameList;
    }

    public List<String> generatePhoneNumber(int num){
        List<String> phoneList = new ArrayList<>();
        while(phoneList.size() < num) {
            long number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
            if(!phoneList.contains(number)) phoneList.add(String.valueOf(number));
        }
        return phoneList;
    }

    public static void main(String[] args) throws IOException {
        int numberOfUsers = 30;
        GenerateUsers test = new GenerateUsers();
        List<String> emailList = test.generateRandomEmail(numberOfUsers);
        List<String> userList = test.generateRandomUserName(numberOfUsers);
        List<String> passwordList = test.generateRandomUserName(numberOfUsers);
        List<String> firstNameList = test.generateRandomName(numberOfUsers);
        List<String> lastNameList = test.generateRandomName(numberOfUsers);
        List<String> phoneList = test.generatePhoneNumber(numberOfUsers);
        PrintWriter out = new PrintWriter("/Users/victoria/Desktop/5200/5200GroupProject/MovieMaster/processData/OutputData/Users_output.csv");
        int id = 1;
        out.println("UserId,UserName,Password,FirstName,LastName,Phone,Email");
        for (int i = 0; i < numberOfUsers; i++) {
            out.println(id++ + "," + userList.get(i) + "," + passwordList.get(i) + "," +
                    firstNameList.get(i) + "," + lastNameList.get(i) + "," + phoneList.get(i) + ","
                    + emailList.get(i));
        }
        out.close();
    }
}
