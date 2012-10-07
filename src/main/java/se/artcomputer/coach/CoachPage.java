package se.artcomputer.coach;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.PropertyModel;

import java.io.Serializable;

public class CoachPage extends BasePage {
    static final String TRANSCRIPT = "transcript";
    static final String FORM = "form";
    static final String ANSWER_FIELD = "answer";

    private final RepeatingView transcript;
    private final QuestionMaker questionMaker;

    public CoachPage(QuestionMaker questionMaker) {
        this.questionMaker = questionMaker;
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
            addText(data.answer);
            addText(questionMaker.nextQuestion());
            data.answer = "";
        }

        private void addText(String text) {
            transcript.add(new Label(transcript.newChildId(), text));
        }
    }

    private class CoachData implements Serializable {
        String answer = "";
    }
}
