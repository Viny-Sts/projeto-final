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

    private String ip;
    private String name;
    private String date;
    private String activityLog;

    public Activity() {
    }

    public Activity(String ip, String name, String date, String activityLog) {
        this.ip = ip;
        this.name = name;
        this.date = date;
        this.activityLog = activityLog;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getActivityLog() {
        return activityLog;
    }

    public void setActivityLog(String activityLog) {
        this.activityLog = activityLog;
    }
}
