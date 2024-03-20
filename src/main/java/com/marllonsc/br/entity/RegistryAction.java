package com.marllonsc.br.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "registry_action")
public class RegistryAction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @Enumerated(EnumType.STRING)
    @Column(name = "action_name", nullable = false)
    private Actions actionName;

    @Column(name = "date_executed", nullable = false)
    private Date dateExecuted;

    @Column(name = "user", nullable = false, length = 100)
    private String user;

    // Constructors, getters, and setters
    public RegistryAction() {
    }

    public RegistryAction(Project project, Actions actionName, Date dateExecuted, String user) {
        this.project = project;
        this.actionName = actionName;
        this.dateExecuted = dateExecuted;
        this.user = user;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Project getProjectId() {
        return project;
    }

    public void setProjectId(Project project) {
        this.project = project;
    }

    public Actions getActionName() {
        return actionName;
    }

    public void setActionName(Actions actionName) {
        this.actionName = actionName;
    }

    public Date getDateExecuted() {
        return dateExecuted;
    }

    public void setDateExecuted(Date dateExecuted) {
        this.dateExecuted = dateExecuted;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}