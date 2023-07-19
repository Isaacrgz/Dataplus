package com.dataplus.models;

public class AccountModel {
    protected int id;
    protected String account;
    protected String email;
    protected String password;

    public AccountModel(){
    }
    public AccountModel(int id) {
        this.id = id;
    }
    public AccountModel(String account,String email, String password){
        this.account = account;
        this.email = email;
        this.password = password;
    }
    public AccountModel(int id, String account, String email, String password) {
        this.id = id;
        this.account = account;
        this.email = email;
        this.password = password;
    }
    public String getAccount() {
        return account;
    }
    public void setAccount(String account) {
        this.account = account;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    @Override
    public String toString() {
        return "AccountModel [id=" + id + ", account=" + account + ", password=" + password + ", email=" + email + "]";
    }

}
