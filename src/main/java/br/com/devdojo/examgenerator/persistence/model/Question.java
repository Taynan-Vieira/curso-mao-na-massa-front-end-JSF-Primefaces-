package br.com.devdojo.examgenerator.persistence.model;

public class Question extends AbstractEntity{
//    @NotEmpty(message = "O nome título não pode estar vazio")
    private String title;
    private Course course;
    private Professor professor;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
}
