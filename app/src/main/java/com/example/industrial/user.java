package com.example.industrial;

public class user {
    String name;
    String phone;
    String specialize;
    String mainlocation;
    String anotherlocation;
    String imageindustry;



    public user(String name, String phone, String specialize, String mainlocation, String anotherlocation, String imageindustry) {
        this.name = name;
        this.phone = phone;
        this.specialize = specialize;
        this.mainlocation = mainlocation;
        this.anotherlocation = anotherlocation;
        this.imageindustry = imageindustry;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSpecialize() {
        return specialize;
    }

    public void setSpecialize(String specialize) {
        this.specialize = specialize;
    }

    public String getMainlocation() {
        return mainlocation;
    }

    public void setMainlocation(String mainlocation) {
        this.mainlocation = mainlocation;
    }

    public String getAnotherlocation() {
        return anotherlocation;
    }

    public void setAnotherlocation(String anotherlocation) {
        this.anotherlocation = anotherlocation;
    }
    public String getImageindustry() {
        return imageindustry;
    }

    public void setImageindustry(String imageindustry) {
        this.imageindustry = imageindustry;
    }
}
