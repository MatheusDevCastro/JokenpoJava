/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.jokenpo;

/**
 *
 * @author 22159713
 */
public class Jogador {
    String nick;
    int vitorias;
    int derrotas;

    public Jogador(String nick, int vitorias, int derrotas) {
        this.nick = nick;
        this.vitorias = vitorias;
        this.derrotas = derrotas;
    }
    
    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public int getVitorias() {
        return vitorias;
    }

    public void setVitorias(int vitorias) {
        this.vitorias = vitorias;
    }

    public int getDerrotas() {
        return derrotas;
    }

    public void setDerrotas(int derrotas) {
        this.derrotas = derrotas;
    }
    
    
    
    
    public void info(){
        System.out.println("\n\nNick: " + this.nick +
        "\nVitorias: " + this.vitorias + 
                "\nDerrotas: " + this.derrotas);
    }
    
}
