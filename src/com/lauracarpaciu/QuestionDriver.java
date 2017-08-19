package com.lauracarpaciu;

public class QuestionDriver {

    public static void main(String[] args) {
        GUIQuiz quiz = new GUIQuiz(8);

        quiz.start();
        quiz.displayResults();
    }
}
