package com.example.practica;

import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private static UserManager instance;
    private final List<Usuario> listaUsuarios;

    private UserManager() {
        listaUsuarios = new ArrayList<>();
        listaUsuarios.add(new Usuario("Andre", 25, "andre@example.com"));
        listaUsuarios.add(new Usuario("Maria", 22, "maria@example.com"));
    }

    public static synchronized UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void addUsuario(Usuario usuario) {
        listaUsuarios.add(usuario);
    }
}
