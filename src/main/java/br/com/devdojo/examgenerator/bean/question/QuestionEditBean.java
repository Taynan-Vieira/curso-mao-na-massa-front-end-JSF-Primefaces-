package br.com.devdojo.examgenerator.bean.question;

import br.com.devdojo.examgenerator.annotation.ExceptionHandler;
import br.com.devdojo.examgenerator.persistence.dao.CourseDAO;
import br.com.devdojo.examgenerator.persistence.dao.QuestionDAO;
import br.com.devdojo.examgenerator.persistence.model.Course;
import br.com.devdojo.examgenerator.persistence.model.Question;
import org.omnifaces.util.Messages;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class QuestionEditBean implements Serializable {
    private final QuestionDAO questionDAO;
    private Question question;
    private long questionId;

    @Inject
    public QuestionEditBean(QuestionDAO questionDAO) {
        this.questionDAO = questionDAO;
    }

    @ExceptionHandler
    public void init() {
        question = questionDAO.findBy(questionId);
    }

    @ExceptionHandler
    public String update() {
        questionDAO.update(question);
        Messages.create("A questão {0} foi atualizada com sucesso.", question.getTitle()).flash().add();
        return "list.xhtml?faces-redirect=true&courseId=" + question.getCourse().getId();
    }

    @ExceptionHandler
    public String delete() {
        questionDAO.delete(question);
        Messages.create("O curso {0} foi deletado com sucesso.", question.getTitle()).flash().add();
        return "list.xhtml?faces-redirect=true&courseId=" + question.getCourse().getId();
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }
}
