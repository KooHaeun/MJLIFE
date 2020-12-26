package com.example.mjlife;

public class Bus {

    public String date;
    public String serviceNum;
    public String seatNum;
    public String where;
    public String ID;



    public Bus(){}

    public Bus(String ID, String date, String serviceNum, String seatNum, String where){
        this.ID = ID;
        this.date = date;
        this.serviceNum = serviceNum;
        this.seatNum=seatNum;
        this.where = where;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getServiceNum() {
        return serviceNum;
    }

    public void setServiceNum(String serviceNum) {
        this.serviceNum = serviceNum;
    }

    public String getSeatNum() {
        return seatNum;
    }

    public void setSeatNum(String seatNum) {
        this.seatNum = seatNum;
    }

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }





}
