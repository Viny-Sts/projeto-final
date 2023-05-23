package br.edu.ifg.luziania.model.dto;

public class AuthReturnDTO {
    private String url;
    private String message;

    private Boolean isAuth;

    public AuthReturnDTO(String url, String message, Boolean isAuth) {
        this.url = url;
        this.message = message;
        this.isAuth = isAuth;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

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
