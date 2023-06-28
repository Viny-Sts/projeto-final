package br.edu.ifg.luziania.model.entity;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id"})
})
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String activityLog;
    private String activityDetails;

    public Activity() {
    }

    public Activity(String activityLog, String activityDetails) {
        this.activityLog = activityLog;
        this.activityDetails = activityDetails;
    }

    public String getActivityLog() {
        return activityLog;
    }

    public void setActivityLog(String activityLog) {
        this.activityLog = activityLog;
    }

    public String getActivityDetails() {
        return activityDetails;
    }

    public void setActivityDetails(String activityDetails) {
        this.activityDetails = activityDetails;
    }
}
