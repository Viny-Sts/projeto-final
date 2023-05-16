package br.edu.ifg.luziania.model.dto;
//class made for users, in which they're all objects.
//everytime someone signs in or up, the class in instanced.
public class AuthDTO {
    private String email;
    private String password;

    private Boolean admin;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }
}
