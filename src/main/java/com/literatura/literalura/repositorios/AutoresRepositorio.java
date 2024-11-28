package com.literatura.literalura.repositorios;

import com.literatura.literalura.modelos.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AutoresRepositorio extends JpaRepository<Autor,Long> {
    Optional<Autor> findByNombreIgnoreCase(String nombre);
    //Opcion 4
    @Query(value = "SELECT a FROM Autor a WHERE a.fechaDeNacimiento <= :anio AND a.fechaDeFallecimiento >= :anio")
    List<Autor> buscaAutoresVivos( int anio);
}