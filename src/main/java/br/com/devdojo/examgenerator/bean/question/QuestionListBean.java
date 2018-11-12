package br.com.devdojo.examgenerator.bean.question;

import br.com.devdojo.examgenerator.persistence.dao.CourseDAO;
import br.com.devdojo.examgenerator.persistence.dao.QuestionDAO;
import br.com.devdojo.examgenerator.persistence.model.Course;
import br.com.devdojo.examgenerator.persistence.model.Question;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class QuestionListBean implements Serializable {

    private QuestionDAO questionDAO;
    private CourseDAO courseDAO;
    private List<Question> questionList;
    private Course course;
    private String title = "";
    private long courseId;

    @Inject
    public QuestionListBean(CourseDAO courseDAO, QuestionDAO questionDAO) {
        this.questionDAO = questionDAO;
        this.courseDAO = courseDAO;
    }

    public void init(){
        course = courseDAO.findBy(courseId);
        search();
    }

    public void search(){
        questionList = questionDAO.list(courseId, title);
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }
}
