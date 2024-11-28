package com.literatura.literalura.servicios;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.literatura.literalura.modelos.Autor;
import com.literatura.literalura.modelos.Idioma;
import com.literatura.literalura.modelos.Libro;
import com.literatura.literalura.repositorios.AutoresRepositorio;
import com.literatura.literalura.repositorios.LibrosRepositorio;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Transactional
public class ConstructorDatos {
    private ObjectMapper mapper = new ObjectMapper();
    private JsonNode nodos;
    private AutoresRepositorio autoresRepositorio;
    private LibrosRepositorio librosRepositorio;

    public ConstructorDatos(AutoresRepositorio autoresRepositorio, LibrosRepositorio librosRepositorio) {
        this.autoresRepositorio = autoresRepositorio;
        this.librosRepositorio = librosRepositorio;
    }
    public void subeLibro(String datos) {
        try {
            nodos = mapper.readTree(datos);
            // Obtener título y autor del JSON
            String titulo = nodos.get("title").asText();
            String nombreAutor = nodos.get("authors").get(0).get("name").asText();

            // Crear el libro
            String idioma = nodos.get("languages").get(0).asText();
            int descargas = nodos.get("download_count").asInt();
            Libro nuevoLibro = new Libro(titulo, idioma, descargas);

            // Verificar si el autor ya está registrado
            Optional<Autor> autorExistente = autoresRepositorio.findByNombreIgnoreCase(nombreAutor);
            if (autorExistente.isPresent()) {
                Autor autor = autorExistente.get();
                autor.anadeLibro(nuevoLibro);
                autoresRepositorio.save(autor); // Relación actualizada
            } else {
                // Crear un nuevo autor
                int nacimiento = nodos.get("authors").get(0).get("birth_year").asInt();
                int fallecimiento = nodos.get("authors").get(0).get("death_year").asInt();
                Autor nuevoAutor = new Autor(nombreAutor, nacimiento, fallecimiento);
                nuevoAutor.setLibros(new ArrayList<>());
                nuevoAutor.anadeLibro(nuevoLibro);
                autoresRepositorio.save(nuevoAutor);
            }

            System.out.println("Libro guardado exitosamente:\n" + nuevoLibro);

        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error al procesar el JSON", e);
        }
    }

    public Optional<Libro> buscarLibroEnBase(String busqueda) {
        return librosRepositorio.findByTituloContainingIgnoreCase(busqueda);
    }

    public void muestraLibros(){
        List<Libro> listaLibros = librosRepositorio.findAll();
        if( !listaLibros.isEmpty() ){
            listaLibros.forEach(l -> System.out.println(l + "\n"));
        }else{
            System.out.println("Todavia no hay libros registrados");
        }
    }

    public void muestraAutores(){
        List<Autor> listaAutores = autoresRepositorio.findAll();

        if(!listaAutores.isEmpty()){
            listaAutores.forEach(a -> System.out.println(a+"\n"));
        }else{
            System.out.println("Todavia no hay autores registrados");
        }
    }

    public void porAnio(int anio){
        List<Autor> autoresVivos = autoresRepositorio.buscaAutoresVivos(anio);
        if(!autoresVivos.isEmpty()){
            System.out.println("Estos son los autores vivos en dicho año:\n");
            autoresVivos.forEach(a -> System.out.println(a+"\n"));
        }else{
            System.out.println("No se encontro nada");
        }
    }

    public void porIdioma(String id){
        List<Libro> librosIdioma = librosRepositorio.findByIdioma( Idioma.retornaIdioma(id) );
        if(!librosIdioma.isEmpty()){
            System.out.println("Estos son los libros en dicho idioma:\n");
            librosIdioma.forEach( l -> System.out.println(l + "\n") );
        }else{
            System.out.println("No se encontro nada");
        }
    }
}