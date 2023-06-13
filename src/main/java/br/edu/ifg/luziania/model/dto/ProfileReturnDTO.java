package br.edu.ifg.luziania.model.dto;

import br.edu.ifg.luziania.model.entity.Profiles;

import java.util.List;

public class ProfileReturnDTO {
    private Integer status;

    private String message;

    private List<Profiles> profiles;

    public ProfileReturnDTO(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public ProfileReturnDTO(Integer status, String message, List<Profiles> profiles) {
        this.status = status;
        this.message = message;
        this.profiles = profiles;
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

    public List<Profiles> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<Profiles> profiles) {
        this.profiles = profiles;
    }
}
