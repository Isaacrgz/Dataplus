package com.dataplus.interfaces;

import java.util.List;

import com.dataplus.models.AccountModel;

public interface MySQLInterface {
    void insertar(String acc, String email, String pass);

    List<AccountModel> listar();

    void actualizar(int id, String acc, String email, String pass);

    void eliminar(int id);

}