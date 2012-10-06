package se.artcomputer.coach;

import org.apache.wicket.Component;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import se.artcomputer.coach.CoachPage;
import se.artcomputer.coach.HomePage;
import se.artcomputer.coach.WicketApplication;

/**
 * Simple test using the WicketTester
 */
public class TestHomePage
{
	private WicketTester tester;

	@Before
	public void setUp()
	{
		tester = new WicketTester(new WicketApplication());
	}

	@Test
	public void homepageRendersSuccessfully()
	{
		tester.startPage(HomePage.class);

		tester.assertRenderedPage(HomePage.class);
	}

    @Test
    public void whenRenderingPage_ThenShowLinkToCoachPage() {
        HomePage homePage = tester.startPage(HomePage.class);

        Component link = homePage.get(HomePage.COACH_LINK);
        Assert.assertNotNull("No coach link found", link);

        tester.clickLink(link);

        tester.assertRenderedPage(CoachPage.class);
    }
}
