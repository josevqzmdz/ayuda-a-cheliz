package com.example.recyclerview_ejercicio_2;

// http://www.sgoliver.net/blog/interfaz-de-usuario-en-android-controles-de-seleccion-ii/
public class Titular {
    private String titulo;
    private String subtitulo;

    public Titular(String tit, String sub){
        titulo = tit;
        subtitulo = sub;
    }

    public String getTitulo(){
        return titulo;
    }

    public String getSubtitulo(){
        return subtitulo;
    }
}
