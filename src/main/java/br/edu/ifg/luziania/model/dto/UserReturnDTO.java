package br.edu.ifg.luziania.model.dto;

public class UserReturnDTO {
    private Integer status;

    private String url;
    private String message;

    public UserReturnDTO(Integer status, String url, String message) {
        this.status = status;
        this.url = url;
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
