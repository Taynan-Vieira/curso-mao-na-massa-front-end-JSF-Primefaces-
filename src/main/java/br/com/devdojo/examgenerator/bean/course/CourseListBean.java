package br.com.devdojo.examgenerator.bean.course;

import br.com.devdojo.examgenerator.persistence.dao.CourseDAO;
import br.com.devdojo.examgenerator.persistence.model.Course;
import org.omnifaces.util.Messages;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Named
@ViewScoped
public class CourseListBean implements Serializable {

    private CourseDAO courseDAO;
    private List<Course> courseList, listDateCreationCourse, listDateEventCourse;
    private String name = "";
    private LocalDate dateCreationCourse, dateEventCourse;

    @PostConstruct
    public void init(){
        search();
        search2();
        search3();
    }

    @Inject
    public CourseListBean(CourseDAO courseDAO) {
        this.courseDAO = courseDAO;
    }


    public void search(){
        courseList = courseDAO.list(name);
        listDateCreationCourse = courseDAO.listDateCreateCourse(dateCreationCourse);

    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void search2(){
        listDateCreationCourse = courseDAO.listDateCreateCourse(dateCreationCourse);
    }

    public List<Course> getDatesCourses() {
        return listDateCreationCourse;
    }

    public void search3(){
        listDateEventCourse = courseDAO.listDateEventCourse(dateEventCourse);
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
