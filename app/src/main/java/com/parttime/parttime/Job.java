package com.parttime.parttime;

/**
 * Created by danasw on 17/05/2018.
 */

public class Job {

    private String id;
    private String job;
    private String place;
    private String schdule;
    private String time;
    private String lastEducation;
    private String salary;
    private String gender;
    private String description;
    private String contact;
    private String category;

    public Job(String id, String job, String place, String schdule, String time, String lastEducation, String salary, String gender, String description, String contact, String category) {
        this.id = id;
        this.job = job;
        this.place = place;
        this.schdule = schdule;
        this.time = time;
        this.lastEducation = lastEducation;
        this.salary = salary;
        this.gender = gender;
        this.description = description;
        this.contact = contact;
        this.category = category;
    }

    public Job(String job, String place, String schdule, String time, String lastEducation, String salary, String gender, String description, String contact, String category) {
        this.job = job;
        this.place = place;
        this.schdule = schdule;
        this.time = time;
        this.lastEducation = lastEducation;
        this.salary = salary;
        this.gender = gender;
        this.description = description;
        this.contact = contact;
        this.category = category;
    }

    public Job() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getSchdule() {
        return schdule;
    }

    public void setSchdule(String schdule) {
        this.schdule = schdule;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLastEducation() {
        return lastEducation;
    }

    public void setLastEducation(String lastEducation) {
        this.lastEducation = lastEducation;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
