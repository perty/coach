package se.artcomputer.coach;

import java.io.Serializable;

public class QuestionMaker implements Serializable {
    private int index = 0;
    private String[] questions = {
            "What else?",
            "What have you tried so far?",
            "What is stopping you?",
            "In bigger scheme of things, describe in what way this is important.",
            "What is the worst that could happen?",
            "What is the best that could happen?"
    };

    public String nextQuestion() {
        return questions[index++ % questions.length];
    }
}
