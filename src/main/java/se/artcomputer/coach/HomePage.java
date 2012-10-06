package se.artcomputer.coach;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.WebPage;
import se.artcomputer.coach.CoachPage;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;
    public static final String COACH_LINK = "coachLink";

    public HomePage(final PageParameters parameters) {
	super(parameters);

	add(new Label("version", getApplication().getFrameworkSettings().getVersion()));

    add(new Link<CoachPage>(COACH_LINK) {
        @Override
        public void onClick() {
            setResponsePage(CoachPage.class);
        }
    });
    }
}
