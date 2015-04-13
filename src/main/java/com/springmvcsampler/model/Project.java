package com.springmvcsampler.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by atheedom on 10/04/15.
 */
@Entity
@Table(name = "Projects",
        indexes = {
                @Index(name = "name_index", columnList = "name"),
                @Index(name = "clientName_index", columnList = "clientName"),
                @Index(name = "startDate_index", columnList = "startDate"),
                @Index(name = "endDate_index", columnList = "endDate")
        }
)
public class Project extends BaseEntity<Project> {

    @Column(unique = true, nullable = false)
    private String name;

    @Column(unique = false, nullable = false)
    private String clientName;

    @Column(unique = false, nullable = false)
    private Date startDate;

    @Column(unique = false, nullable = false)
    private Date endDate;

    @Column(nullable = false, length = 1024)
    private String description = "";

    @Column(nullable = false)
    private Double globalOHP = 0.0d;


    public Project() {
    }

    public Project(String name, String clientName, Date startDate, Date endDate, String description, Double globalOHP) {
        this.name = name;
        this.clientName = clientName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.globalOHP = globalOHP;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getGlobalOHP() {
        return globalOHP;
    }

    public void setGlobalOHP(Double globalOHP) {
        this.globalOHP = globalOHP;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Project)) return false;

        Project project = (Project) o;

        if (name != null ? !name.equals(project.name) : project.name != null) return false;
        if (clientName != null ? !clientName.equals(project.clientName) : project.clientName != null) return false;
        if (startDate != null ? !startDate.equals(project.startDate) : project.startDate != null) return false;
        if (endDate != null ? !endDate.equals(project.endDate) : project.endDate != null) return false;
        if (description != null ? !description.equals(project.description) : project.description != null) return false;
        return !(globalOHP != null ? !globalOHP.equals(project.globalOHP) : project.globalOHP != null);

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (clientName != null ? clientName.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (globalOHP != null ? globalOHP.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Project{" +
                "name='" + name + '\'' +
                ", clientName='" + clientName + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", description='" + description + '\'' +
                ", globalOHP=" + globalOHP +
                '}';
    }
}
