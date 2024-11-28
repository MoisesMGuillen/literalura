package com.literatura.literalura;

import com.literatura.literalura.principal.Menu;
import com.literatura.literalura.repositorios.AutoresRepositorio;
import com.literatura.literalura.repositorios.LibrosRepositorio;
import com.literatura.literalura.servicios.ConstructorDatos;
import com.literatura.literalura.servicios.ConsultasApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {
	private final AutoresRepositorio autoresRepositorio;
	private final LibrosRepositorio librosRepositorio;

	public LiteraluraApplication(AutoresRepositorio autoresRepositorio, LibrosRepositorio librosRepositorio) {
		this.autoresRepositorio = autoresRepositorio;
		this.librosRepositorio = librosRepositorio;
	}

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ConstructorDatos constructorDatos = new ConstructorDatos(autoresRepositorio,librosRepositorio);
		Menu menu = new Menu(constructorDatos);
		menu.muestraMenu();
	}
}