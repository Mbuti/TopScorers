import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class TopScorers {
    public static void main(String[] args) {

        Scanner inputScanner = new Scanner(System.in);
        System.out.println("Enter absolute path to your testDataFile e.g /home/mbuti/Desktop/test_scores.csv");

        String csvFile = inputScanner.nextLine();

        List<Scorer> scorers = readScorers(csvFile);
        List<Scorer> topScorers = getTopScorers(scorers);

        for (Scorer scorer : topScorers) {
            System.out.println(scorer.getName() + " " + scorer.Surname);
        }
        System.out.println("Highest Scorer is : "+topScorers.get(0).score);
    }

    private static List<Scorer> readScorers(String csvFile) {
        List<Scorer> scorers = new ArrayList<>();
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String headers= br.readLine().toString();
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                String name = fields[0];
                String Surname = fields[1];
                int score = Integer.parseInt(fields[2]);
                scorers.add(new Scorer(name, Surname, score));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scorers;
    }

    private static List<Scorer> getTopScorers(List<Scorer> scorers) {

        List<Scorer> Topscorers = new ArrayList<>();
        Collections.sort(scorers, Comparator.comparing(Scorer::getScore).reversed());

        for(int i =0; i < scorers.size();i++){
            if(scorers.get(0).score == scorers.get(i).score){
                Topscorers.add(scorers.get(i));
            }
        }

        Collections.sort(Topscorers, Comparator.comparing(Scorer::getScore));
        return Topscorers;
    }

    private static class Scorer {
        private String name;

        private String Surname;
        private int score;

        public Scorer(String name, String Surname, int score) {
            this.name = name;
            this.Surname = Surname;
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public String getSurname(){
            return Surname;
        }

        public int getScore() {
            return score;
        }
    }
}