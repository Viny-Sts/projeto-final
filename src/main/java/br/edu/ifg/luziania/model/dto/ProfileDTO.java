package br.edu.ifg.luziania.model.dto;

public class ProfileDTO {
    private String name;

    private Boolean mainAccess;
    private Boolean activityAccess;
    private Boolean userManagement;
    private Boolean profileManagement;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getMainAccess() {
        return mainAccess;
    }

    public void setMainAccess(Boolean mainAccess) {
        this.mainAccess = mainAccess;
    }

    public Boolean getActivityAccess() {
        return activityAccess;
    }

    public void setActivityAccess(Boolean activityAccess) {
        this.activityAccess = activityAccess;
    }

    public Boolean getUserManagement() {
        return userManagement;
    }

    public void setUserManagement(Boolean userManagement) {
        this.userManagement = userManagement;
    }

    public Boolean getProfileManagement() {
        return profileManagement;
    }

    public void setProfileManagement(Boolean profileManagement) {
        this.profileManagement = profileManagement;
    }
}
