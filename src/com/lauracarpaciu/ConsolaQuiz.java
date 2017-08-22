package com.lauracarpaciu;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class ConsolaQuiz {


    private static final int NO_OF_QTIONS = 6;
    private boolean done = false;
    private final Scanner scanner = new Scanner(System.in);
    private int score;
    private final int nrIntrebari;
    private long timeElapsed;

    private ConsolaQuiz(int nrIntrebari) {
        this.nrIntrebari = nrIntrebari;
    }


    private static String[][] getData() {
        String [][] data = {{"FC Real Madrid", "Cristiano Ronaldo"}, {"FC Villarreal", "Bruno Soriano"}, {"FC Barcelona", "Lionel Messi"}, {"FC Atl. Madrid", "Gabriel Fernández"}, {"FC Sevilla", "Julien Escudé"}, {"FC Valencia", "Dani Parejo"}, {"FC Ath. Bilbao", "Gorka Iraizoz"}, {"FC Espanyol", "Javi López"}};
        return data;
    }

    private void start() {


        try {
            long startTime = System.currentTimeMillis();


            for (Question question : generate(nrIntrebari)
                    ) {
                System.out.println(question.getText());
                String useranswer = scanner.nextLine();
                if (useranswer.equalsIgnoreCase(question.getAnswer())) {
                    score++;
                    System.out.println("Bravo. Raspuns corect!\n");
                } else {

                    System.out.println("Raspuns nu este corect!");
                    System.out.println("Raspunsul corect este " + question.getAnswer() + ".\n");


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

        char replayUser;

        do {
            start();

            if (done) {
                displayScore();
                displayTimeElapsed();
            }


            do {
                System.out.println("Doriti sa continuati? D/N");

                replayUser = scanner.nextLine().toLowerCase().charAt(0);

            } while (replayUser != 'd' && replayUser != 'n');


        } while (replayUser == 'd');


        System.out.println("La revedere!.Apasati \"Enter\"");
        scanner.nextLine();
        scanner.close();
    }


    private void displayTimeElapsed() {
        System.out.printf("Ati avut nevoie de aproximativ %d secunde pentru a raspunde la un numar de %s intrebari.\n", timeElapsed, nrIntrebari);
    }

    private void displayScore() {
        System.out.printf("Scorul Dvs. final este %d/%d\n", score, nrIntrebari);
    }

    private ArrayList<Question> generate(int nrIntrebari) {

        String[][] data = getData();

        if (nrIntrebari > data.length) {

            throw new IllegalArgumentException("Nu sunt decat " + data.length + " intrebari posibile!");
        }

        ArrayList<Question> questions = new ArrayList<>();

        int index;

        ArrayList<Integer> IndexDejaluat = new ArrayList<>();
        IndexDejaluat.clear();

        for (int i = 1; i <= nrIntrebari; i++) {

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
