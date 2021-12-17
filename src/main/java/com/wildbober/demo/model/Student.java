package com.wildbober.demo.model;

import com.wildbober.demo.utils.Identifiable;

public class Student extends Identifiable {

    private String firstName;
    private String secondName;
    private int studentAge;

    public Student(){

    }

    public Student(int id, String firstName, String secondName, int studentAge) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.studentAge = studentAge;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public int getStudentAge() {
        return studentAge;
    }

    public void setStudentAge(int studentAge) {
        this.studentAge = studentAge;
    }
}
