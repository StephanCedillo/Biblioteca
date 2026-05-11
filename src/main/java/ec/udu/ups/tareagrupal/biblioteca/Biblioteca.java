/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package ec.udu.ups.tareagrupal.biblioteca;

import ec.edu.ups.tareagrupal.clases.Prestamo;
import ec.edu.ups.tareagrupal.clases.Usuario;
import ec.edu.ups.tareagrupal.clases.Libro;
import ec.edu.ups.tareagrupal.clases.Persona;

import ec.edu.ups.tareagrupal.clases.Autor;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author stephancedillo
 */
public class Biblioteca {

    public static void main(String[] args) {

        ArrayList<Usuario> usuarios = new ArrayList<>();
        ArrayList<Libro> libros = new ArrayList<>();
        ArrayList<Autor> autores = new ArrayList<>();
        ArrayList<Prestamo> prestamos = new ArrayList<>();
        

        // ======================
        // AUTORES
        // ======================
        Autor autor1 = new Autor(
                "0102030405",
                45,
                "Gabriel",
                "Garcia",
                "Colombia",
                true,
                false,
                "Masculino"
        );

        Autor autor2 = new Autor(
                "0912345678",
                38,
                "Isabel",
                "Allende",
                "Chile",
                true,
                false,
                "Femenino"
        );

        autores.add(autor1);
        autores.add(autor2);

        // ======================
        // LIBROS
        // ======================
        Libro libro1 = new Libro(
                "9781234567890",
                autor1,
                "Cien Años de Soledad",
                "Novela",
                false,
                471,
                "Español",
                true,
                25.50
        );

        Libro libro2 = new Libro(
                "9789876543210",
                autor2,
                "La Casa de los Espíritus",
                "Drama",
                true,
                390,
                "Español",
                true,
                30.00
        );

        libros.add(libro1);
        libros.add(libro2);

        // Agregar libros a los autores
        autor1.agregarLibro(libro1);
        autor2.agregarLibro(libro2);

        // ======================
        // USUARIOS
        // ======================
        Usuario usuario1 = new Usuario(
                "juan@gmail.com",
                "1234",
                "1100110011",
                20,
                "Juan",
                "Perez",
                "Cuenca",
                true,
                false,
                "Masculino"
        );

        Usuario usuario2 = new Usuario(
                "maria@gmail.com",
                "abcd",
                "2200220022",
                17,
                "Maria",
                "Lopez",
                "Quito",
                true,
                true,
                "Femenino"
        );

        usuarios.add(usuario1);
        usuarios.add(usuario2);

        Scanner leer = new Scanner(System.in);

        int opcion;
        String continuar;

        do {

            System.out.println("\n========= BIBLIOTECA =========");
            System.out.println("1. Ingresar nuevo usuario");
            System.out.println("2. Ingresar nuevo libro");
            System.out.println("3. Registrar  prestamos");
            System.out.println("4. Registrar devolución");
            System.out.println("5. Mostrar usuarios");
            System.out.println("6. Mostrar libros");
            System.out.println("7. Mostrar prestamos");
            
            System.out.println("8. Renovar membresía");
            System.out.println("9. Buscar Libro");
            System.out.println("10. Buscar Usuario");
            System.out.println("11. Buscar Prestamo");
            System.out.println("12. Buscar Autor");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = leer.nextInt();
            leer.nextLine();

            switch (opcion) {

                case 1:
                    System.out.println("\n--- INGRESAR USUARIO ---");
                    do {
                        System.out.println("Ingresa su email");
                        String email = leer.next();

                        System.out.println("Ingresa su contraseña");
                        String contraseña = leer.next();

                        Persona datos = pedirDatosPersonales(true);

                        Usuario usuario = new Usuario(email, contraseña, datos.getCedula(), datos.getEdad(), datos.getNombre(), datos.getApellido(), datos.getDireccion(), datos.isEstadoVivo(), datos.isTieneDiscapacidad(), datos.getGenero());
                        usuario.agregarMembresia();
                        usuarios.add(usuario);
                        System.out.println("Desea ingresar otro usuario? ( S/N) ");
                        continuar = leer.next();
                    } while (continuar.equalsIgnoreCase("S"));

                    break;

                case 2:

                    System.out.println("\n--- INGRESAR LIBRO ---");
                    do {
                        System.out.println("Ingresa su ISBN ");
                        String ISBN = leer.next();
                        System.out.println("Su autor ya esta dentro del sistema?? ");
                        String autorBusqueda = leer.next();
                        Autor autor = buscarAutor(autores, autorBusqueda);
                        if (autor == null) {
                            autor = crearAutor();
                            autores.add(autor);
                        }
                        System.out.println("\n--- INGRESAR DATOS DEL LIBRO ---");
                        System.out.println("Ingresa su nombre ");
                        String nombre = leer.nextLine();
                        leer.nextLine();
                        System.out.println("Ingresa su genero literario ");
                        String genero = leer.next();
                        System.out.print("Ingresa si tiene restriccion de edad: ");
                        boolean sirestriccionEdad = pedirSiNo();
                        System.out.println("Ingresa su numero de paginas:  ");
                        int numeroPaginas = leer.nextInt();
                        System.out.println("Ingresa su idioma: ");
                        String idioma = leer.next();
                        System.out.println("Ingresa su costo:  ");
                        double costoLibro = leer.nextDouble();

                        Libro libro = new Libro(ISBN, autor, nombre, genero, sirestriccionEdad, numeroPaginas, idioma, true, costoLibro);

                        autor.agregarLibro(libro);
                        libros.add(libro);
                        System.out.println("Desea ingresar otro libro? ( S/N) ");
                        continuar = leer.next();
                    } while (continuar.equalsIgnoreCase("S"));

                    break;

                case 3:
                    continuar = "";
                    Prestamo prestamo = null;
                    System.out.println("\n--- REGISTRAR PRÉSTAMO ---");
                    int cantidadDisponibilidadLibros = buscarDisponibilidadLibros(libros);
                    if (!usuarios.isEmpty() && !libros.isEmpty() && cantidadDisponibilidadLibros > 0) {

                        boolean requisito = false;

                        Usuario usuario = null;

                        while (usuario == null) {
                            System.out.println("Ingresa la cedula de su usuario ");
                            String usuarioBusqueda = leer.next();
                            usuario = buscarUsuario(usuarios, usuarioBusqueda);
                            if (usuario == null) {
                                System.out.println("Usuario no encontrado");
                                continue;
                            }

                        }
                        int cont = 0;

                        do {
                            requisito = false;

                            while (!requisito) {

                                if (cont < usuario.getMembresia().calcularCantidadLibros()) {
                                    Libro libro = null;
                                    while (libro == null) {
                                        System.out.println("Ingresa el ISBN de su libro ");
                                        String libroBusqueda = leer.next();
                                        libro = buscarLibro(libros, libroBusqueda);
                                        if (libro == null) {
                                            System.out.println("Libro no encontrado");
                                            continue;
                                        }
                                        boolean disponibilidad = libro.isSiestadoDisponibilidad();

                                        while (!disponibilidad) {
                                            System.out.println("Libro no disponible, busque otro: ");
                                            System.out.println("Ingresa el ISBN de su libro ");
                                            libroBusqueda = leer.next();
                                            libro = buscarLibro(libros, libroBusqueda);
                                            disponibilidad = libro.isSiestadoDisponibilidad();
                                        }

                                    }

                                    boolean esParaMayoresEdad = libro.isSirestriccionEdad();

                                    if (esParaMayoresEdad && usuario.getEdad() < 18) {
                                        System.out.println("Usted no tiene la edad permitida para este libro");
                                        continue;
                                    }

                                    requisito = true;

                                    prestamo = new Prestamo(usuario, true);
                                    prestamo.agregarLibro(libro);
                                    cont++;

                                    System.out.println("Desea ingresar otro libro? ( S/N) ");
                                    continuar = leer.next();
                                } else {
                                    continuar = "N";
                                    requisito = true;
                                    System.out.println("Usted no tiene permitido tener mas libros ");
                                }
                            }
                        } while (continuar.equalsIgnoreCase("S"));
                        prestamos.add(prestamo);

                    } else {
                        if (usuarios.isEmpty() || libros.isEmpty()) {
                            System.out.println("No hay libros o usuarios registrados, primero realice eso");
                        } else {
                            System.out.println("No hay libros disponibles");
                        }

                    }

                    break;

                case 4:
                    System.out.println("\n--- REGISTRAR DEVOLUCIÓN ---");

                    Prestamo prestamoEncontrado = buscarPrestamos(prestamos);

                    prestamoEncontrado.registrarDevolucion();

                    System.out.println("Usted realizo la devolucion del libro ");

                    break;

                case 5:
                    System.out.println("\n--- LISTA DE USUARIOS ---");
                    imprimirUsuarios(usuarios);
                    break;

                case 6:
                    System.out.println("\n--- LISTA DE LIBROS ---");
                    imprimirLibros(libros);
                    break;

                case 7:
                    System.out.println("\n--- HISTORIAL ---");
                    imprimirPrestamos(prestamos);
                    break;

                case 8:
                    System.out.println("\n--- RENOVAR MEMBRESÍA ---");
                    System.out.println("Ingresa la cedula de su usuario ");
                    String usuarioBusqueda = leer.next();
                    Usuario usuario = buscarUsuario(usuarios, usuarioBusqueda);
                    usuario.renovarMembresia();
                    break;

                case 9:
                    System.out.println("\n--- BUSCAR LIBRO ---");
                    System.out.println("Ingresa el ISBN del libro ");
                    String isbnBusqueda = leer.next();

                    Libro libroEncontrado1 = buscarLibro(libros, isbnBusqueda);

                    if (libroEncontrado1 == null) {
                        System.out.println("No se ha encontrado el libro buscado");

                    } else {
                        System.out.println(libroEncontrado1);
                    }

                    break;

                case 10:
                    System.out.println("\n--- BUSCAR USUARIO ---");
                    System.out.println("Ingresa la cedula de su usuario ");
                    String usuarioBusqueda1 = leer.next();
                    Usuario usuariobuscado = buscarUsuario(usuarios, usuarioBusqueda1);
                    if (usuariobuscado == null) {
                        System.out.println("No se ha encontrado el usuario buscado");

                    } else {
                        System.out.println(usuariobuscado);
                    }

                    break;
                case 11:
                    System.out.println("\n--- BUSCAR PRESTAMO ---");

                    Prestamo prestamoEncontrado1 = buscarPrestamos(prestamos);

                    if (prestamoEncontrado1 == null) {
                        System.out.println("No se ha encontrado el prestamo buscado");

                    } else {
                        System.out.println(prestamoEncontrado1);
                    }

                    break;
                case 12:
                    System.out.println("\n--- BUSCAR AUTOR ---");
                    System.out.println("Ingresa el nombre del autor ");
                    String autorBusqueda1 = leer.next();
                    Autor autorBuscado = buscarAutor(autores, autorBusqueda1);
                    if (autorBuscado == null) {
                        System.out.println("No se ha encontrado el autor buscado");

                    } else {
                        System.out.println(autorBuscado);
                    }

                    break;

                case 0:
                    System.out.println("\nSaliendo del sistema...");
                    break;

                default:
                    System.out.println("\nOpción inválida.");

            }

        } while (opcion != 0);

        leer.close();
    }

    public static Persona pedirDatosPersonales(boolean isPedidoUsuario) {
        Scanner leer = new Scanner(System.in);

        System.out.println("\n--- INGRESAR DATOS PERSONALES DEL USUARIO ---");
        System.out.println("Ingresa su cedula: ");
        String cedula = leer.next();

        System.out.println("Ingresa su edad: ");
        int edad = leer.nextInt();

        System.out.println("Ingresa su nombre: ");
        String nombre = leer.next();

        System.out.println("Ingresa su apellido: ");
        String apellido = leer.next();
        leer.nextLine();
        System.out.println("Ingresa su dirrecion: ");
        String direccion = leer.nextLine();

        boolean tieneDiscapacidad = false;
        System.out.print("Ingresar si tiene discapacidad??");
        tieneDiscapacidad = pedirSiNo();

        boolean estadoVivo = true;
        if (!isPedidoUsuario) {
            System.out.print("Ingresar si el autor esta vivo??");
            estadoVivo = pedirSiNo();
        }

        System.out.println("Ingresar su genero: ");
        String genero = leer.next();

        return new Persona(cedula, edad, nombre, apellido, direccion, estadoVivo, tieneDiscapacidad, genero);
    }

    public static boolean pedirSiNo() {

        Scanner leer = new Scanner(System.in);
        String variableLectora;
        System.out.println("(S/N)");
        variableLectora = leer.next();

        while (!variableLectora.equalsIgnoreCase("S") && !variableLectora.equalsIgnoreCase("N")) {
            System.out.println("Datos Incorrectos:Ingresar (S/N)");
            variableLectora = leer.next();

        }

        if (variableLectora.equalsIgnoreCase("S")) {
            return true;
        } else {
            return false;
        }

    }

    public static LocalDate pedirFechaLocalDate() {

        Scanner leer = new Scanner(System.in);

        System.out.print("Ingrese una fecha (AAAA-MM-DD): ");

        String fechaTexto = leer.nextLine();

        return LocalDate.parse(fechaTexto);

    }

    public static Autor crearAutor() {
        Persona datos = pedirDatosPersonales(false);
        Autor autor = new Autor(datos.getCedula(), datos.getEdad(), datos.getNombre(), datos.getApellido(), datos.getDireccion(), datos.isEstadoVivo(), datos.isTieneDiscapacidad(), datos.getGenero());
        return autor;

    }

    public static Autor buscarAutor(ArrayList<Autor> autores, String nombre) {

        for (Autor autor : autores) {

            if (autor.getCedula()
                    .equalsIgnoreCase(nombre)) {

                return autor;
            }
        }

        return null;
    }

    public static Libro buscarLibro(ArrayList<Libro> libros, String ISBN) {

        for (Libro libro : libros) {

            if (libro.getISBN().equalsIgnoreCase(ISBN)) {

                return libro;
            }
        }

        return null;
    }

    public static Usuario buscarUsuario(ArrayList<Usuario> usuarios, String cedula) {

        for (Usuario usuario : usuarios) {

            if (usuario.getCedula().equalsIgnoreCase(cedula)) {

                return usuario;
            }
        }

        return null;
    }

    public static Prestamo buscarPrestamoId(ArrayList<Prestamo> prestamos, int id) {

        for (Prestamo prestamo : prestamos) {

            if (prestamo.getId() == id) {

                return prestamo;
            }
        }

        return null;
    }

    public static Prestamo buscarPrestamoUsuario(ArrayList<Prestamo> prestamos, String cedula) {

        for (Prestamo prestamo : prestamos) {

            if (prestamo.getUsuario().getCedula().equalsIgnoreCase(cedula)) {

                return prestamo;
            }
        }

        return null;
    }

    public static Prestamo buscarPrestamoLibro(ArrayList<Prestamo> prestamos, String ISBN) {

        for (Prestamo prestamo : prestamos) {
            List<Libro> libros = prestamo.getLibro();
            for (Libro libro : libros) {
                if (libro.getISBN().equalsIgnoreCase(ISBN)) {

                    return prestamo;
                }

            }

        }

        return null;
    }

    public static int buscarDisponibilidadLibros(ArrayList<Libro> libros) {
        int cont = 0;
        for (Libro libro : libros) {

            if (libro.isSiestadoDisponibilidad()) {
                cont++;

            }
        }

        return cont;
    }

    public static void imprimirUsuarios(ArrayList<Usuario> usuarios) {
        for (Usuario usuario : usuarios) {
            System.out.println(usuario);
        }
    }

    public static void imprimirLibros(ArrayList<Libro> libros) {
        for (Libro libro : libros) {
            System.out.println(libro);
        }
    }

    public static void imprimirPrestamos(ArrayList<Prestamo> prestamos) {
        for (Prestamo prestamo : prestamos) {
            System.out.println(prestamo);
        }
    }

    public static Prestamo buscarPrestamos(ArrayList<Prestamo> prestamos) {
        Scanner leer = new Scanner(System.in);
        System.out.println("\n========= MENU DE BUSQUEDA =========");
        System.out.println("1. Id del prestamo");
        System.out.println("2. Cedula Usuario");
        System.out.println("3. ISBN libro");
        int opcionMenuBusqueda1 = leer.nextInt();
        Prestamo prestamoBuscado1 = null;

        switch (opcionMenuBusqueda1) {
            case 1:
                System.out.println("Ingresa el id de su prestamo ");
                int idPrestamoBuscado = leer.nextInt();
                prestamoBuscado1 = buscarPrestamoId(prestamos, idPrestamoBuscado);
                break;
            case 2:
                System.out.println("Ingresa la cedula del usuario");
                String cedulaPrestamoBuscado = leer.next();
                prestamoBuscado1 = buscarPrestamoUsuario(prestamos, cedulaPrestamoBuscado);
                break;
            case 3:
                System.out.println("Ingresa el ISBN del libro");
                String ISBNPrestamoBuscado = leer.next();
                prestamoBuscado1 = buscarPrestamoLibro(prestamos, ISBNPrestamoBuscado);
                break;
            default:
                System.out.println("Usted ingreso los datos incorrectos");

                break;

        }

        return prestamoBuscado1;
    }
}