package br.edu.ifg.luziania.model.dto;

public class ActivityDTO {
    private String name;
    private String activityLog;

    public ActivityDTO(String name, String activityLog) {
        this.name = name;
        this.activityLog = activityLog;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getActivityLog() {
        return activityLog;
    }

    public void setActivityLog(String activityLog) {
        this.activityLog = activityLog;
    }
}
