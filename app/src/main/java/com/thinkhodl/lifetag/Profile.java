package com.thinkhodl.lifetag;

public class Profile {

    private String firstName,lastName,age,bloodType,allergies,illnesses,vaccines,
            isDonor,contactName,contactNumber;

    public Profile(String text){
        String[] splitted = text.split(";", 0);
        firstName=splitted[0];
        lastName=splitted[1];
        age=splitted[2];
        bloodType=splitted[3];
        allergies=splitted[4];
        illnesses=splitted[5];
        vaccines=splitted[6];
        isDonor=splitted[7];
        contactName=splitted[8];
        contactNumber=splitted[9];
    }

    public Profile(){

    }

    public String encode(){

        return firstName+";"+lastName+";"+age+";"+bloodType+";"+allergies+";"+illnesses+";"+vaccines+";"+isDonor+";"+contactName+";"+contactNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public String getIllnesses() {
        return illnesses;
    }

    public void setIllnesses(String illnesses) {
        this.illnesses = illnesses;
    }

    public String getVaccines() {
        return vaccines;
    }

    public void setVaccines(String vaccines) {
        this.vaccines = vaccines;
    }

    public String getIsDonor() {
        return isDonor;
    }

    public void setIsDonor(String isDonor) {
        this.isDonor = isDonor;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}

