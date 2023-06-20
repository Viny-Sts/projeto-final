package br.edu.ifg.luziania.model.entity;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name"})
})
public class Profiles {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String name;

    private Boolean mainAccess;
    private Boolean activityAccess;
    private Boolean userManagement;
    private Boolean profileManagement;

    public Profiles() {

    }

    public Profiles(String name, Boolean mainAccess, Boolean activityAccess, Boolean userManagement,
                    Boolean profileManagement) {
        this.name = name;

        this.mainAccess = mainAccess;
        this.activityAccess = activityAccess;
        this.userManagement = userManagement;
        this.profileManagement = profileManagement;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
