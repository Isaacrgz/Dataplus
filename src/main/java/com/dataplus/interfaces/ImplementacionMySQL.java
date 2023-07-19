package com.dataplus.interfaces;

import com.dataplus.models.AccountModel;

import java.util.List;

import com.dataplus.AccountDAO;

public class ImplementacionMySQL implements MySQLInterface {
    
    AccountDAO accountDAO = new AccountDAO();

    @Override
    public void insertar(String acc, String email, String pass) {
        AccountModel accountModel = new AccountModel(acc, email, pass);
        accountDAO.addAccount(accountModel);
    }

    @Override
    public List<AccountModel> listar() {
        List<AccountModel> accounts = accountDAO.selectAccount();
        return accounts;
    }

    @Override
    public void actualizar(int id, String acc, String email, String pass) {
        AccountModel accountModel = new AccountModel(id, acc, email, pass);
        accountDAO.updateAccount(accountModel);
    }

    @Override
    public void eliminar(int id) {
        AccountModel accountModel = new AccountModel(id);
        accountDAO.deleteAccount(accountModel);
    }
    
}
