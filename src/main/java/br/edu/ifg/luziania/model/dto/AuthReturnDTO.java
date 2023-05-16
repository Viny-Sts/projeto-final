package br.edu.ifg.luziania.model.dto;

public class AuthReturnDTO {

    private String message;

    private Boolean isAuth;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getAuth() {
        return isAuth;
    }

    public void setAuth(Boolean auth) {
        isAuth = auth;
    }
}
