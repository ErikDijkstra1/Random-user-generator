package com.example.randomusergenerator;

public class Person {
    private String picture;
    private String firstName;
    private String lastName;
    private String nationality;
    private String country;
    private String birthday;
    private String phoneNumber;
    private String address;
    private String gender;
    private String email;

    public Person(String picture, String firstName, String lastName, String nationality, String country, String birthday, String phoneNumber, String address, String gender, String email) {
        this.picture = picture;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationality = nationality;
        this.country = country;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.email = email;
        this.address = address;
    }

    public Person() {

    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getBasicData() {
        return nationality + "\n" + country;
    }

    public String getAllInfo() {
        return nationality + "\n" + country + "\n" + birthday + "\n" +phoneNumber + "\n" + address  + "\n" + gender  + "\n" + email;
    }

    public String getPicture() {
        return picture;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNationality() {
        return nationality;
    }

    public String getCountry() {
        return country;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
