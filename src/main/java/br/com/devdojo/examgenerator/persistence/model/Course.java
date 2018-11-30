package br.com.devdojo.examgenerator.persistence.model;

import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class Course extends AbstractEntity {
    //    @NotEmpty(message = "O nome do curso não pode estar vazio")
    private String name;
    private Professor professor;
    private LocalDate dateCreation;
    private LocalDate dateEvent;

    public LocalDate getDateEvent() {
        return dateEvent;
    }

    public void setDateEvent(LocalDate dateEvent) {
        this.dateEvent = dateEvent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Professor getProfessor() {
        return professor;
    }

    public LocalDate getDate() {
        return dateCreation;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }



    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public static final class Builder {
        private Course course;

        private Builder() {
            course = new Course();
        }

        public static Builder newCourse() {
            return new Builder();
        }

        public Builder withName(String name) {
            course.setName(name);
            return this;
        }

        public Builder withId(long id) {
            course.setId(id);
            return this;
        }

        public Builder withProfessor(Professor professor) {
            course.setProfessor(professor);
            return this;
        }

        public Course build() {
            return course;
        }
    }

}

