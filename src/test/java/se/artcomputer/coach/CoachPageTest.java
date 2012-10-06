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
import se.artcomputer.coach.CoachPage;
import se.artcomputer.coach.HomePage;

public class CoachPageTest {

    WicketTester wicketTester = new WicketTester(HomePage.class);
    private static final String SOME_TEXT = "SOME TEXT";

    @Before
    public void beforeEachTest() {

    }

    @Test
    public void renderPage() {
        wicketTester.startPage(CoachPage.class);
    }

    @Test
    public void thereShouldBeATranscript() {
        CoachPage coachPage = wicketTester.startPage(CoachPage.class);
        Component transcript = coachPage.get(CoachPage.TRANSCRIPT);
        Assert.assertNotNull(transcript);
    }

    @Test
    public void thereShouldBeAFormWithTextField() {
        CoachPage coachPage = wicketTester.startPage(CoachPage.class);
        Component form = coachPage.get(CoachPage.FORM);
        Assert.assertNotNull(form);
        coachPage.get(CoachPage.FORM + ":" + CoachPage.ANSWER_FIELD);
    }

    @Test
    public void whenTypingAnAnswerTheTranscriptShouldBeUpdated() {
        wicketTester.startPage(CoachPage.class);
        FormTester ft = wicketTester.newFormTester(CoachPage.FORM);
        ft.setValue(CoachPage.ANSWER_FIELD, SOME_TEXT);
        ft.submit();
        CoachPage coachPage = (CoachPage) wicketTester.getLastRenderedPage();
        // Search transcript items for the text
        final Boolean[] found = {false};
        WebMarkupContainer transcriptContainer = (WebMarkupContainer) coachPage.get(CoachPage.TRANSCRIPT);
        transcriptContainer.visitChildren(new IVisitor<Component, Object>() {
            @Override
            public void component(Component component, IVisit<Object> objectIVisit) {
                String s = component.getDefaultModelObjectAsString();
                if (s.contains(SOME_TEXT)) {
                    found[0] = true;
                }
            }
        });
        Assert.assertTrue("Did not find " + SOME_TEXT + " in transcript", found[0]);

    }
}
