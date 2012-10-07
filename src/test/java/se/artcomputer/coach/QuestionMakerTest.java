package se.artcomputer.coach;

import junit.framework.Assert;
import org.junit.Test;

public class QuestionMakerTest {

    @Test
    public void should_have_different_questions() {
        QuestionMaker questionMaker = new QuestionMaker();
        String firstQuestion = questionMaker.nextQuestion();
        String secondQuestion = questionMaker.nextQuestion();
        assertUnEqualStrings(firstQuestion, secondQuestion);
    }

    @Test
    public void should_handle_thousands_of_questions() {
        QuestionMaker questionMaker = new QuestionMaker();
        String lastQuestion = "";
        for(int n = 0; n < 1000; n++) {
            String newQuestion = questionMaker.nextQuestion();
            assertUnEqualStrings(lastQuestion, newQuestion);
            lastQuestion = newQuestion;
        }
    }

    private void assertUnEqualStrings(String firstQuestion, String secondQuestion) {
        Assert.assertTrue(String.format("Oh, no '%s' is equal to '%s'", firstQuestion, secondQuestion),
                !firstQuestion.equalsIgnoreCase(secondQuestion));
    }
}
