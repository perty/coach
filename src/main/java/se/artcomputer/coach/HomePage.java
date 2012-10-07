package se.artcomputer.coach;

import org.apache.wicket.markup.html.link.Link;

public class HomePage extends BasePage {
    private static final long serialVersionUID = 1L;
    public static final String COACH_LINK = "coachLink";

    public HomePage() {

        add(new Link<CoachPage>(COACH_LINK) {
            @Override
            public void onClick() {
                setResponsePage(new CoachPage(new QuestionMaker()));
            }
        });
    }
}
