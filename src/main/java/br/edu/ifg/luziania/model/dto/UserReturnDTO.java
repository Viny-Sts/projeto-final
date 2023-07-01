package br.edu.ifg.luziania.model.dto;

import br.edu.ifg.luziania.model.entity.Users;

import java.util.List;

public class UserReturnDTO {
    private Integer status;

    private String url;
    private String message;

    private List<Users> users;

    public UserReturnDTO(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public UserReturnDTO(Integer status, String message, List<Users> users) {
        this.status = status;
        this.message = message;
        this.users = users;
    }

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

    public List<Users> getUsers() {
        return users;
    }

    public void setUsers(List<Users> users) {
        this.users = users;
    }
}
