# Literalura
Literalura es una aplicación basada en consola desarrollada en Java para gestionar y consultar autores y libros.
Esta herramienta interactúa con una API externa para buscar libros, además de proporcionar funcionalidades para filtrar libros y autres bajo diversos criterios, como el idioma o el año de vida.

## Características
- Consultar libros por título a través de una API externa.
- Registrar autores y libros en una base de datos.
- Listar libros y autores registrados.
- Filtrar autores vivos en un año específico.
- Filtrar libros por idioma.

## Requisitos previos
Antes de ejecutar la aplicación, asegúrate de tener instalados:
- Java 17 o superior.
- Maven 3.6 o superior.

## Tecnologías utilizadas
- Java 17
- Spring Boot
- JPA / Hibernate para el acceso a datos.
- H2 Database como base de datos en memoria.
- Jackson para procesar JSON.
- HttpClient para interactuar con la API externa.

# Estructura del proyecto
## Principales
### LiteraluraApplication
- Clase principal que inicializa el proyecto y muestra el menú interactivo.
- Implementa CommandLineRunner para ejecutar el programa desde la línea de comandos.
### Menu
Proporciona una interfaz interactiva para que el usuario elija opciones como buscar libros, listar autores y más.

### ConstructorDatos
Se encarga de gestionar la lógica de negocio:
- Registra autores y libros en la base de datos.
- Realiza consultas sobre los datos almacenados.
- Filtra autores y libros según los criterios seleccionados.

### ConsultasApi
- Realiza búsquedas de libros en la API externa Gutenberg Books API.
- Procesa y retorna información relevante sobre los libros encontrados.

## Modelos

### Autor
Representa a un autor, incluyendo su información básica y sus libros.
### Libro
Representa a un libro, con atributos como título, idioma y número de descargas.
### Idioma 
Enumera los idiomas soportados (español, inglés, francés, portugués).

## Repositorios
### AutoresRepositorio
Proporciona métodos personalizados para buscar autores en la base de datos.
### LibrosRepositorio
Proporciona métodos para buscar y filtrar libros.

# Instalación y ejecución
1. Clona el repositorio
```

https://github.com/MoisesMGuillen/literalura.git

```
3. Construye el proyecto con Maven
```

mvn clean install

```
5. Ejecuta la aplicación
```

mvn spring-boot:run

```

# Uso
Al iniciar, se mostrará un menú interactivo con las siguientes opciones:

```
-------------------------------------
Elija la opción a través de un número:
1. Buscar libro por título
2. Listar libros registrados
3. Listar autores registrados
4. Listar autores vivos en determinado año
5. Listar libros por idioma
0. Salir
```

## Ejemplo de uso
### 1. Buscar libro por título
- Ingresa el titulo de un libro para buscarlo en la API externa.
- Si no está registrado, se registrará automáticamente en la base de datos.
### 2. Listar libros registrados
- Muestra todos los libros registrados en la base de datos
### 3. Listar autores registrados
- Muestra todos los libros
### 4. Listar autores vivos en un determinado año
- Ingresa un año, y la aplicación mostrará los autores vivos durante ese año.
### 5. Listar libros por idioma
- Ingresa un idioma de una determinada lista:
  - **es - español**
  - **en - inglés**
  - **fr - francés**
  - **pt - portugués**
  - **it - italiano**

# Detalles técnicos
- **Base de datos**: Se utiliza H2 Database para almacenar autores y libros.
- **Consulta a API externa:** La clase ConsultasApi utiliza HttpClient para interactuar con Gutenberg Books API y procesa los resultados con Jackson.
- **Gestión de relaciones:** Se usa una relación One-to-Many entre Autor y Libro.

# Limitaciones actuales
- Solo soporta los idiomas enumerados previamente
- La API externa retorna solo el primer resultado de la búsqueda del libro

# API externa
El API utilizada es la de **Gutendex**. Puede consultarse en el siguiente enlace:
https://gutendex.com/
