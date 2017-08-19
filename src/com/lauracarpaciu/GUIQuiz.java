package com.lauracarpaciu;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;


public class GUIQuiz {

    private static final int NO_OF_QTIONS = 3;
    boolean done = false;
    private int score, nrIntrebari;
    private long timeElapsed;


    public GUIQuiz(int nrIntrebari) {


        this.nrIntrebari = nrIntrebari;

    }

    private static HashMap<String, String> getData() {

        HashMap<String, String> data = new HashMap<>();
        data.put("FC Real Madrid", "Cristiano Ronaldo");
        data.put("FC Villarreal", "Bruno Soriano");
        data.put("FC Barcelona", "Lionel Messi");
        data.put("FC Atl. Madrid", "Gabriel Fernández");
        data.put("FC Sevilla", "Julien Escudé");
        data.put("FC Valencia", "Dani Parejo");
        data.put("FC Ath. Bilbao", "Gorka Iraizoz");
        data.put("FC Espanyol", "Javi López");

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

        Random random = new Random();

        HashMap<String, String> data = getData();


        if (nrIntrebari > data.size()) {

            throw new IllegalArgumentException("Nu sunt decat " + data.size() + " intrebari posibile!");
        }

        ArrayList<Question> questions = new ArrayList<>();
        ArrayList<String> echipe = new ArrayList<>();

        for (String echipa :
                data.keySet()) {
            echipe.add(echipa);

        }


        for (int i = 0; i < nrIntrebari; i++) {


            String echipa = echipe.get(random.nextInt(echipe.size()));

            String capitanul = data.get(echipa);
            echipe.remove(random.nextInt(echipe.size()));


            String questiontext = String.format("Care este capitanul echipei %s?", echipa);

            questions.add(new Question(questiontext, capitanul));

        }
        return questions;
    }

}
