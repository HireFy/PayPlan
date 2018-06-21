package com.cobcap.bean;

import java.sql.Timestamp;

public class User {
    private int id;
    private String userName;
    private String mail;
    private String password;
    private String uuid;
    private Timestamp payTime;
    private Timestamp deadLine;
    private int balance;

    public User(String userName, String mail, String password, String uuid) {
        this.userName = userName;
        this.mail = mail;
        this.password = password;
        this.uuid = uuid;
    }

    public User(int id, String userName, String mail, String password, String uuid, Timestamp payTime, Timestamp deadLine, int balance) {
        this.id = id;
        this.userName = userName;
        this.mail = mail;
        this.password = password;
        this.uuid = uuid;
        this.payTime = payTime;
        this.deadLine = deadLine;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Timestamp getPayTime() {
        return payTime;
    }

    public void setPayTime(Timestamp payTime) {
        this.payTime = payTime;
    }

    public Timestamp getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(Timestamp deadLine) {
        this.deadLine = deadLine;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
