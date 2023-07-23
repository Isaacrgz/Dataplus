package com.dataplus.models;

import java.util.Arrays;

public class FolderModel {
    
    private String folderName;
    private int id;
    private AccountModel accounts[];
    private static int folderCounter;
    private int accountCounter;

    public FolderModel(String folderName) {
        this.id = ++folderCounter;
        this.folderName = folderName;
    }

    public void setFolder(String newFolder){
        this.folderName = newFolder;
    }
    public String getFolder(){
        return this.folderName;
    }

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }

    public void addAccount(AccountModel account){
        accounts[accountCounter++] = account;
    }

    @Override
    public String toString() {
        return "FolderModel [folderName=" + folderName + ", id=" + id + ", accounts=" + Arrays.toString(accounts)
                + ", accountCounter=" + accountCounter + "]";
    }
}