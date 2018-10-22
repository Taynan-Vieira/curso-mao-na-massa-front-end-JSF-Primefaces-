package br.com.devdojo.examgenerator.persistence.model;

public class Professor extends  AbstractEntity {
 //   @NotEmpty (message = "O campo nome não pode estar vazio")
    private String name;
//    @Email(message = "Este e-mail não é válido")
//    @NotEmpty(message = "O campo e-mail não pode estar vazio")
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
