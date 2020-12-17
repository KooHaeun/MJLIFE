package com.example.mjlife;

public class BusReservation {

    public String date;
    public String serviceNum;
    public String seatNum;
    public String where;



    public BusReservation(){}

    public BusReservation(String date, String serviceNum, String seatNum, String where){
        this.date = date;
        this.serviceNum = serviceNum;
        this.seatNum=seatNum;
        this.where = where;
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
