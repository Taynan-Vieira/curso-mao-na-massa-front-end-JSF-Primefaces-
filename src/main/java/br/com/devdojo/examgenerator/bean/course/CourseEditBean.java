package br.com.devdojo.examgenerator.bean.course;

import br.com.devdojo.examgenerator.annotation.ExceptionHandler;
import br.com.devdojo.examgenerator.persistence.dao.CourseDAO;
import br.com.devdojo.examgenerator.persistence.model.Course;
import org.omnifaces.util.Messages;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class CourseEditBean implements Serializable {
    private final CourseDAO courseDAO;
    private long id;
    private Course course;
    private String name;

    @Inject
    public CourseEditBean(CourseDAO courseDAO) {
        this.courseDAO = courseDAO;
    }

    public void init() {
        course = courseDAO.findBy(id);
    }

    @ExceptionHandler
    public String update() {
        courseDAO.update(course);
        Messages.create("O curso {0} foi atualizado com sucesso.", course.getName()).flash().add();
        return "list.xhtml?faces-redirect=true";
    }

    @ExceptionHandler
    public String delete() {
        courseDAO.delete(course);
        Messages.create("O curso {0} foi deletado com sucesso.", course.getName()).flash().add();
        return "list.xhtml?faces-redirect=true";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}