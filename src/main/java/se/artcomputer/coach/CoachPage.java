package se.artcomputer.coach;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import java.io.Serializable;

public class CoachPage extends WebPage {
    static final String TRANSCRIPT = "transcript";
    static final String FORM = "form";
    static final String ANSWER_FIELD = "answer";

    private final RepeatingView transcript;

    public CoachPage() {
        transcript = new RepeatingView(TRANSCRIPT);
        add(transcript);
        add(new CoachForm(FORM));
    }

    private class CoachForm extends Form {
        CoachData data = new CoachData();

        public CoachForm(String id) {
            super(id);
            add(new TextField<String>(ANSWER_FIELD, new PropertyModel<String>(data, "answer")));
        }

        @Override
        protected void onSubmit() {
            transcript.add(new Label(transcript.newChildId(), data.answer));
            data.answer = "";
        }
    }

    private class CoachData implements Serializable {
        String answer = "";
    }
}
