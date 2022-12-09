package com.example.adoptananimal;
// Class for holding adoption information
public class Adoption {
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    Pet pet;
    UserInformation user;
    Adoption(Pet pet, UserInformation user)
    {
        this.pet = pet;
        this.user = user;
    }
    Adoption()
    {
        this.pet = new Pet();
        this.user = new UserInformation();
    }

    public UserInformation getUser() {
        return user;
    }

    public void setUser(UserInformation user) {
        this.user = user;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }
}
