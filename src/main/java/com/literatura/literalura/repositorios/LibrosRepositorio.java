package com.literatura.literalura.repositorios;

import com.literatura.literalura.modelos.Idioma;
import com.literatura.literalura.modelos.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LibrosRepositorio extends JpaRepository<Libro,Long> {
    Optional<Libro> findByTituloContainingIgnoreCase(String titulo);
    //Opcion 5
    List<Libro> findByIdioma(Idioma id);
}
