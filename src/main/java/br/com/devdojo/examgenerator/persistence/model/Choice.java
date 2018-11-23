package br.com.devdojo.examgenerator.persistence.model;

public class Choice extends AbstractEntity {
//    @NotEmpty(message = "O nome título não pode estar vazio")
    private String title;
//    @NotNull(message = "O nome campo correAnswer deve ser verdadeiro ou falso")
    private boolean correctAnswer;
    private Question question;
    private Professor professor;

    public static final class Builder {
        private Choice choice;

        private Builder() {
            choice = new Choice();
        }

        public static Builder newChoice() {
            return new Builder();
        }

        public Builder id(long id) {
            choice.setId(id);
            return this;
        }

        public Builder enabled(boolean enabled) {
            choice.setEnabled(enabled);
            return this;
        }

        public Builder title(String title) {
            choice.setTitle(title);
            return this;
        }

        public Builder correctAnswer(boolean correctAnswer) {
            choice.setCorrectAnswer(correctAnswer);
            return this;
        }

        public Builder question(Question question) {
            choice.setQuestion(question);
            return this;
        }

        public Builder professor(Professor professor) {
            choice.setProfessor(professor);
            return this;
        }

        public Choice build() {
            return choice;
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(boolean correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
}
