package com.cobcap.bean;


import java.sql.Date;

public class User {
    private int id;
    private String userName;
    private String mail;
    private String password;
    private String uuid;
    private Date payTime;
    private Date deadLine;
    private int balance;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public User(String userName, String password, String mail) {
        this.userName = userName;
        this.password = password;
        this.mail = mail;
    }

    public User(String userName, String mail, String password, String uuid) {
        this.userName = userName;
        this.mail = mail;
        this.password = password;
        this.uuid = uuid;
    }

    public User(int id, String userName, String mail, String password, String uuid, Date payTime, Date deadLine, int balance) {
        this.id = id;
        this.userName = userName;
        this.mail = mail;
        this.password = password;
        this.uuid = uuid;
        this.payTime = payTime;
        this.deadLine = deadLine;
        this.balance = balance;
    }

    public User() {

    }

    public User(String userName, String mail, String uuid, Date payTime, Date deadLine, int balance) {
        this.userName = userName;
        this.mail = mail;
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

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Date getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(Date deadLine) {
        this.deadLine = deadLine;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
