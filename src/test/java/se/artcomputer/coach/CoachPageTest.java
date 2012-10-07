package se.artcomputer.coach;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.apache.wicket.util.visit.IVisit;
import org.apache.wicket.util.visit.IVisitor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class CoachPageTest {

    WicketTester wicketTester = new WicketTester(HomePage.class);
    private static final String SOME_TEXT = "SOME TEXT";
    private CoachPage coachPage;
    private static final String A_NEW_QUESTION = "What have you tried so far?";

    @Before
    public void beforeEachTest() {
        coachPage = wicketTester.startPage(new CoachPage(mockTheQuestionMaker()));
    }

    private QuestionMaker mockTheQuestionMaker() {
        QuestionMaker questionMaker = Mockito.mock(QuestionMaker.class);
        Mockito.when(questionMaker.nextQuestion()).thenReturn(A_NEW_QUESTION);
        return questionMaker;
    }

    @Test
    public void renderPage() {
        wicketTester.startPage(new CoachPage(mockTheQuestionMaker()));
    }

    @Test
    public void thereShouldBeATranscript() {
        Component transcript = coachPage.get(CoachPage.TRANSCRIPT);
        Assert.assertNotNull(transcript);
    }

    @Test
    public void thereShouldBeAFormWithTextField() {
        Component form = coachPage.get(CoachPage.FORM);
        Assert.assertNotNull(form);
        coachPage.get(CoachPage.FORM + ":" + CoachPage.ANSWER_FIELD);
    }

    @Test
    public void when_typing_an_answer_the_transcript_should_be_updated() {
        enterSomeTextInForm();
        CoachPage coachPage = (CoachPage) wicketTester.getLastRenderedPage();
        Assert.assertTrue("Did not find answer in transcript", searchTranscriptForText(coachPage, SOME_TEXT));
    }

    @Test
    public void when_typing_an_answer_there_should_be_a_new_question() {
        enterSomeTextInForm();
        Boolean aBoolean = searchTranscriptForText(coachPage, A_NEW_QUESTION);
        Assert.assertTrue("Can't see a new question in transcript", aBoolean);
    }

    private void enterSomeTextInForm() {
        FormTester ft = wicketTester.newFormTester(CoachPage.FORM);
        ft.setValue(CoachPage.ANSWER_FIELD, SOME_TEXT);
        ft.submit();
    }

    private Boolean searchTranscriptForText(CoachPage coachPage, final String searchedText) {
        WebMarkupContainer transcriptContainer = (WebMarkupContainer) coachPage.get(CoachPage.TRANSCRIPT);
        Boolean result = transcriptContainer.visitChildren(new IVisitor<Component, Boolean>() {
            @Override
            public void component(Component component, IVisit<Boolean> objectIVisit) {
                String s = component.getDefaultModelObjectAsString();
                if (s.contains(searchedText)) {
                    objectIVisit.stop(Boolean.TRUE);
                }
            }
        });
        return result != null ? result : Boolean.FALSE;
    }


}
