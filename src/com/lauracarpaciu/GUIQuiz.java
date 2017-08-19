package com.lauracarpaciu;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GUIQuiz {

    private static final int NO_OF_QTIONS = 3;
    boolean done = false;
    private int score, nrIntrebari;
    private long timeElapsed;


    public GUIQuiz(int nrIntrebari) {


        this.nrIntrebari = nrIntrebari;

    }

    private static String[][] getData() {

        String[][] data = {{"FC Real Madrid", "Cristiano Ronaldo"}, {"FC Villarreal", "Bruno Soriano"}, {"FC Barcelona", "Lionel Messi"}, {"FC Atl. Madrid", "Gabriel Fernández"}, {"FC Sevilla", "Julien Escudé"}, {"FC Valencia", "Dani Parejo"}, {"FC Ath. Bilbao", "Gorka Iraizoz"}, {"FC Espanyol", "Javi López"}};
        return data;


    }

    public void start() {

        try {
            long startTime = System.currentTimeMillis();

            for (Question question : generate(nrIntrebari)
                    ) {
                String useranswer = JOptionPane.showInputDialog(question.getText());

                if (useranswer.equalsIgnoreCase(question.getAnswer())) {
                    score++;
                    JOptionPane.showMessageDialog(null, "Bravo. Raspuns corect!\n");
                } else {

                    JOptionPane.showMessageDialog(null, String.format("Raspuns nu este corect!\nRaspunsul corect este " + question.getAnswer() + ".\n"));
                }
            }
            done = true;
            long endTime = System.currentTimeMillis();
            timeElapsed = getTimeElapsedInSeconds(endTime - startTime);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

    private int getTimeElapsedInSeconds(long timeInMilliseconds) {
        return (int) timeInMilliseconds / 1000;
    }



    public void displayResults() {
        if (done) {
            displayScore();
            displayTimeElapsed();
        }
    }

    private void displayTimeElapsed() {


        JOptionPane.showMessageDialog(null, String.format("Ati avut nevoie de aproximativ %d secunde pentru a raspunde la un numar de %s intrebari.\n", timeElapsed, nrIntrebari));


    }



    private void displayScore() {
        JOptionPane.showMessageDialog(null, String.format("Scorul Dvs. final este %d/%d\n", score, nrIntrebari));
    }


    public ArrayList<Question> generate(int nrIntrebari) {

        String[][] data = getData();

        if (nrIntrebari > data.length) {

            throw new IllegalArgumentException("Nu sunt decat " + data.length + " intrebari posibile!");
        }

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

            String questiontext = String.format("Care este capitanul echipei %s?", echipa);

            questions.add(new Question(questiontext, capitanul));

        }
        return questions;
    }

}
