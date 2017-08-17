package com.lauracarpaciu;

import java.util.ArrayList;
import java.util.Random;

public class Quiz {


    private int score;
    private long time;

    private static String[][] getData() {

        String[][] data = {{"FC Real Madrid", "Cristiano Ronaldo"}, {"FC Villarreal", "Bruno Soriano"}, {"FC Barcelona", "Lionel Messi"}, {"FC Atl. Madrid", "Gabriel Fernández"}, {"FC Sevilla", "Julien Escudé"}, {"FC Valencia", "Dani Parejo"}, {"FC Ath. Bilbao", "Gorka Iraizoz"}, {"FC Espanyol", "Javi López"}};
        return data;


    }

    public void start() {

        for (Question question : generate(5)
                ) {
            System.out.println(question.getText());
            System.out.println(question.getAnswer());
            System.out.println("-------");
        }

    }

    public void displayResults() {
        // TODO completeaza corp metoda

    }

    public ArrayList<Question> generate(int nrIntrebari) {

        String[][] data = getData();

        ArrayList<Question> questions = new ArrayList<>();

        int index;

        ArrayList<Integer> IndexDejaluat = new ArrayList<>();
        IndexDejaluat.clear();

        for (int i = 0; i < nrIntrebari; i++) {

            do {
                Random random = new Random();
                index = random.nextInt(data.length);
            } while (IndexDejaluat.contains(index));
            IndexDejaluat.add(index);


            String echipa = data[index][0];
            String capitanul = data[index][1];

            String questiontext = String.format("Care este capitanul echipei?:%s", echipa);

            questions.add(new Question(questiontext, capitanul));

        }
        return questions;
    }


}
