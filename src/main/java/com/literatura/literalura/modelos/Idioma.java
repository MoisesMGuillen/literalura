package com.literatura.literalura.modelos;

public enum Idioma {
    ESPANOL("es"),
    INGLES("en"),
    FRANCES("fr"),
    PORTUGUES("pt");
    private String idioma;

    Idioma(String idioma){
        this.idioma = idioma;
    }

    public static Idioma retornaIdioma(String id) {
        for (Idioma idioma : Idioma.values()) {
            if (idioma.idioma.equalsIgnoreCase(id)) {
                return idioma;
            }
        }
        throw new IllegalArgumentException("Ningun idioma encontrada: " + id);
    }

    public String getIdioma() {
        return idioma;
    }
}