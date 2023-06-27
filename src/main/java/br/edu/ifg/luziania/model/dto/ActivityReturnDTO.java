package br.edu.ifg.luziania.model.dto;

import br.edu.ifg.luziania.model.entity.Activity;

import java.util.List;

public class ActivityReturnDTO {
    private Integer status;

    private String message;

    private List<Activity> activities;

    public ActivityReturnDTO(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public ActivityReturnDTO(Integer status, String message, List<Activity> profiles) {
        this.status = status;
        this.message = message;
        this.activities = profiles;
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

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }
}
