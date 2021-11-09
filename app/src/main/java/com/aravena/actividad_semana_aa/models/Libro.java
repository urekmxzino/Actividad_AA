package com.aravena.actividad_semana_aa.models;

public class Libro {
    private int id;
    private String titulo;
    private String descripcion;
    private String fechaP;
    private int copias;
    private int cantP;
    private Autor autor;
    private Editorial editorial;
    private Estante estante;


    public Libro() {
    }

    public Libro(int id, String titulo, String descripcion, String fechaP, int copias, int cantP, Autor autor, Editorial editorial, Estante estante) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaP = fechaP;
        this.copias = copias;
        this.cantP = cantP;
        this.autor = autor;
        this.editorial = editorial;
        this.estante = estante;
    }

    public Libro(String titulo, String descripcion, String fechaP, int copias, int cantP, Autor autor, Editorial editorial, Estante estante) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaP = fechaP;
        this.copias = copias;
        this.cantP = cantP;
        this.autor = autor;
        this.editorial = editorial;
        this.estante = estante;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFechaP() {
        return fechaP;
    }

    public void setFechaP(String fechaP) {
        this.fechaP = fechaP;
    }

    public int getCopias() {
        return copias;
    }

    public void setCopias(int copias) {
        this.copias = copias;
    }

    public int getCantP() {
        return cantP;
    }

    public void setCantP(int cantP) {
        this.cantP = cantP;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Editorial getEditorial() {
        return editorial;
    }

    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }

    public Estante getEstante() {
        return estante;
    }

    public void setEstante(Estante estante) {
        this.estante = estante;
    }

    @Override
    public String toString() {
        return this.titulo;
    }
}
