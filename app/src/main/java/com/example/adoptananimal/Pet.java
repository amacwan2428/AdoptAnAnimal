package com.example.adoptananimal;

public class Pet {
    private int id;
    private int userId;
    private String name, birthdate,type;

    public Pet(int id, int userId, String name, String birthdate, String type){
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.birthdate = birthdate;
        this.type = type;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
