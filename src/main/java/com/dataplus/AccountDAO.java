package com.dataplus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static com.dataplus.SQLConnection.*;

import com.dataplus.models.AccountModel;

public class AccountDAO {
    private static final String SQL_SELECT = "SELECT id_account, account, email, pass FROM accounts";
    private static final String SQL_INSERT = "INSERT INTO accounts(account, email, pass) VALUES (?,?,?)";
    private static final String SQL_DELETE = "DELETE FROM accounts WHERE id_account = ?";
    private static final String SQL_UPDATE = "UPDATE accounts SET account = ?, email = ?, pass = ? WHERE id_account = ?";

    public List<AccountModel> selectAccount(){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        AccountModel accountModel = null;
        List<AccountModel> accounts = new ArrayList<>();

        try {
            conn = SQLConnection.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while(rs.next()){
                int idAccount = rs.getInt("id_account");
                String account = rs.getString("account");
                String email = rs.getString("email");
                String pass = rs.getString("pass");

                accountModel = new AccountModel(idAccount, account, email, pass);
                accounts.add(accountModel);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        finally{
            try {
                SQLConnection.close(rs);
                SQLConnection.close(stmt);
                SQLConnection.close(conn);
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }
        return accounts;
    }

    public int addAccount(AccountModel accountModel){
        Connection conn = null;
        PreparedStatement stmt = null;
        int registors = 0;
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, accountModel.getAccount());
            stmt.setString(2, accountModel.getEmail());
            stmt.setString(3, accountModel.getPassword());
            registors = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        finally{
            try {
                close(stmt);
                close(conn);
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }
        return registors;
    }

    public int updateAccount(AccountModel accountModel){
        Connection conn = null;
        PreparedStatement stmt = null;
        int registors = 0;
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, accountModel.getAccount());
            stmt.setString(2, accountModel.getEmail());
            stmt.setString(3, accountModel.getPassword());
            stmt.setInt(4, accountModel.getId());
            registors = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        finally{
            try {
                close(stmt);
                close(conn);
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }
        return registors;
    }

    public int deleteAccount(AccountModel accountModel){
        Connection conn = null;
        PreparedStatement stmt = null;
        int registors = 0;
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, accountModel.getId());
            registors = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        finally{
            try {
                close(stmt);
                close(conn);
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }
        return registors;
    }
}
