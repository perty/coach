package se.artcomputer.coach;

import java.io.Serializable;

public class QuestionMaker implements Serializable {
    private int index = 0;
    private String[] questions = {
            "What else?",
            "What is in it for you?",
            "What have you tried so far?",
            "What is stopping you?",
            "What plans do you currently have?",
            "In bigger scheme of things, describe in what way this is important.",
            "What is the worst that could happen?",
            "What is the best that could happen?",
            "Where could you find support or help in this?",
            "How would you tackle this if you had one superpower?",
            "What would be a definition of success in this?"
    };

    public String nextQuestion() {
        return questions[index++ % questions.length];
    }
}
