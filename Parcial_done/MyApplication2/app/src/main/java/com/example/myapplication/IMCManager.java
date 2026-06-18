package com.example.myapplication;

import java.util.ArrayList;
import java.util.List;

public class IMCManager {
    private static IMCManager instance;
    private final List<IMC> listaIMC;

    private IMCManager() {
        listaIMC = new ArrayList<>();
    }

    public static synchronized IMCManager getInstance() {
        if (instance == null) {
            instance = new IMCManager();
        }
        return instance;
    }

    public List<IMC> getListaIMC() {
        return listaIMC;
    }

    public void addIMC(IMC imc) {
        listaIMC.add(imc);
    }

}
