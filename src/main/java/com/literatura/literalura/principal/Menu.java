package com.literatura.literalura.principal;

import com.literatura.literalura.modelos.Libro;
import com.literatura.literalura.servicios.ConstructorDatos;
import com.literatura.literalura.servicios.ConsultasApi;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.Scanner;

public class Menu {
    private Scanner entrada = new Scanner(System.in);
    private ConsultasApi consulta = new ConsultasApi();
    @Autowired
    private ConstructorDatos constructor;

    public Menu(ConstructorDatos constructor) {
        this.constructor = constructor;
    }

    public void muestraMenu(){
        int opcion = -1;
        while(opcion!=0) {
            System.out.println("""
                    -------------------------------------
                    Elija la opcion a traves de un numero
                    1. Buscar libro por titulo
                    2. Listar libros registrados
                    3. Listar autores registrados
                    4. Listar autores vivos en determinado año
                    5. Listar libros por idioma
                    0. Salir
                    """);
            opcion=entrada.nextInt();
            entrada.nextLine();

            switch (opcion){
                case 1:
                    buscarLibro();
                    break;
                case 2:
                    listarLibros();
                    break;
                case 3:
                    listarAutores();
                    break;
                case 4:
                    listarPorAnio();
                    break;
                case 5:
                    listarPorIdioma();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Presione una opcion valida, por favor.");
            }

        }
    }

    public void buscarLibro(){
        System.out.println("Ingrese el nombre del libro que desea buscar");
        String busqueda = entrada.nextLine();
        //Buscar en la base antes de hacer la busqueda
        if(constructor.buscarLibroEnBase(busqueda).isPresent()){
            System.out.println("No se puede registrar el mismo libro dos veces.\n Este es el libro que buscabas:");
            Libro encontrado = constructor.buscarLibroEnBase(busqueda).get();
            System.out.println(encontrado.toString());
            return;
        }
        //Busqueda del libro por internet
        String json = consulta.buscaLibro(busqueda.replaceAll(" ","+"));
        if(!json.equals("libro no encontrado")){
            constructor.subeLibro(json);
        }else{
            System.out.println(json);
        }
    }

    public void listarLibros(){
        constructor.muestraLibros();
    }

    public void listarAutores(){
        constructor.muestraAutores();
    }

    public void listarPorAnio(){
        System.out.println("Ingrese el año vivo de autor(es) que desea buscar");
        int anio = entrada.nextInt();
        entrada.nextLine();
        constructor.porAnio(anio);
    }

    public void listarPorIdioma(){
        System.out.println("""
                Ingrese el idioma para buscar los libros. Por ejemplo:
                es - español
                en - inglés
                fr - francés
                pt - portugués
                """);
        String id = entrada.nextLine();
        constructor.porIdioma(id);
    }
}