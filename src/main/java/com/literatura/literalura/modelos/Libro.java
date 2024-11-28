package com.literatura.literalura.modelos;

import jakarta.persistence.*;

@Entity
@Table (name = "Libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private String titulo;
    @ManyToOne
    private Autor autor;
    @Enumerated(EnumType.STRING)
    private Idioma idioma;
    private int numeroDescargas;

    public Libro(){

    }

    public Libro(String titulo, String idioma, int numeroDescargas) {
        this.titulo = titulo;
        this.idioma = Idioma.retornaIdioma(idioma);
        this.numeroDescargas = numeroDescargas;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
    }

    public int getNumeroDescargas() {
        return numeroDescargas;
    }

    public void setNumeroDescargas(int numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }

    @Override
    public String toString() {
        return "----- LIBRO -----"+
                "\nTitulo: "+titulo+
                "\nAutor: " + autor.getNombre()+
                "\nIdioma: " + idioma.getIdioma() +
                "\nNumero de descargas: "+ numeroDescargas+
                "\n-----------------";
    }
}
