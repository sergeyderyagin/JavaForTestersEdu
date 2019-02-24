package ru.stqa.pft.mantis.model;

public class Issue {
    private Project project;
    private String summary;
    private int id;
    private String subject;
    private String description;
    private String state;


    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getState() {
        return state;
    }

    public Issue withId(int id) {
        this.id = id;
        return this;
    }

    public Issue withDescription(String description) {
        this.description = description;
        return this;
    }

    public String getSummary() {
        return summary;
    }

    public Project getProject() {
        return project;
    }

    public Issue withSummary(String summary) {
        this.summary = summary;
        return this;
    }

    public Issue withProject(Project project) {
        this.project = project;
        return this;
    }


    public Issue withState(String state) {
        this.state = state;
        return this;
    }
}