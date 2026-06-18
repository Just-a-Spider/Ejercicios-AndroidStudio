package com.example.myapplication;
import java.io.Serializable;

public class IMC implements Serializable {
    private Float peso;
    private Float altura;
    private Float imc;

    public IMC(Float peso, Float altura) {
        this.altura = altura;
        this.peso = peso;
        this.imc = peso / (altura * altura);
    }

    public Float getPeso() {
        return peso;
    }

    public Float getAltura(){
        return altura;
    }

    public Float getImc() {
        return imc;
    }

    public void setPeso(Float peso) {
        this.peso = peso;
    }

    public void setAltura(Float altura){
        this.altura = altura;
    }
}
