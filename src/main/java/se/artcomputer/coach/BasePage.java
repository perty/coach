package se.artcomputer.coach;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

public class BasePage  extends WebPage {
    static final String VERSION = "version";

    public BasePage() {
        add(new Label(VERSION, "1.0beta"));
    }
}
