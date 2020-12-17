package com.example.mjlife;

public class CounselReservation {

    public String date, professor, time, where;

    public CounselReservation(){}

    public CounselReservation(String date, String professor, String time, String where){
        this.date = date;
        this.professor=professor;
        this.time=time;
        this.where=where;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }
}
