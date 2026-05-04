/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package ec.udu.ups.tareagrupal.biblioteca;

import ec.edu.ups.tareagrupal.clases.Registro;
import ec.edu.ups.tareagrupal.clases.Usuario;
import ec.edu.ups.tareagrupal.clases.Libro;
import ec.edu.ups.tareagrupal.clases.Persona;
import ec.edu.ups.tareagrupal.clases.Membresia;
import ec.edu.ups.tareagrupal.clases.Autor;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;

/**
 *
 * @author stephancedillo
 */
public class Biblioteca {

    public static void main(String[] args) {

        ArrayList<Usuario> usuarios = new ArrayList<>();
        ArrayList<Libro> libros = new ArrayList<>();
        ArrayList<Registro> registros = new ArrayList<>();
        ArrayList<Autor> autores = new ArrayList<>();

        Scanner leer = new Scanner(System.in);

        int opcion;
        double ganancias = 0;
        String continuar;

        do {

            System.out.println("\n========= BIBLIOTECA =========");
            System.out.println("1. Ingresar nuevo usuario");
            System.out.println("2. Ingresar nuevo libro");
            System.out.println("3. Registrar registro de nuevo libro");
            System.out.println("4. Registrar devolución");
            System.out.println("5. Mostrar usuarios");
            System.out.println("6. Mostrar libros");
            System.out.println("7. Mostrar historial");
            System.out.println("8. Mostrar factura");
            System.out.println("9. Obtener ganancias");
            System.out.println("10. Renovar membresía");
            System.out.println("11. Buscar Libro");
            System.out.println("12. Buscar Usuario");
            System.out.println("13. Buscar Registro");
            System.out.println("14. Buscar Autor");
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

                        Persona persona = pedirDatosPersonales(true);

                        Membresia membresia = pedirMembresia();

                        usuarios.add(new Usuario(email, contraseña, persona, membresia));
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
                        System.out.println("Ingresa su nombre ");
                        String nombre = leer.next();
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
                    System.out.println("\n--- REGISTRAR PRÉSTAMO ---");
                    int cantidadDisponibilidadLibros = buscarDisponibilidadLibros(libros);
                    if (!usuarios.isEmpty() && !libros.isEmpty() && cantidadDisponibilidadLibros > 0) {
                        do {
                            boolean requisito = false;
                            while (!requisito) {

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

                                if (esParaMayoresEdad && usuario.getDatosPersonales().getEdad() < 18) {
                                    System.out.println("Usted no tiene la edad permitida para este libro");
                                    continue;
                                }

                                requisito = true;

                                libro.setSiestadoDisponibilidad(false);
                                Registro registro = new Registro(usuario, libro, true);
                                registros.add(registro);
                                System.out.println(registro.getFactura().imprimirFactura());

                                ganancias += registro.getFactura().calcularTotal();
                                System.out.println("Desea ingresar otro libro? ( S/N) ");
                                continuar = leer.next();

                            }
                        } while (continuar.equalsIgnoreCase("S"));
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

                    Registro registroEncontrado = buscarRegistros(registros);
                    registroEncontrado.getLibro().setSiestadoDisponibilidad(true);

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
                    imprimirRegistros(registros);
                    break;

                case 8:

                    System.out.println("\n--- BUSCAR FACTURA ---");
                    Registro registroEncontrado2 = buscarRegistros(registros);
                    registroEncontrado2.getFactura().imprimirFactura();

                    break;

                case 9:
                    System.out.println("\n--- GANANCIAS ---");
                    System.out.println("El total de ganancias ganadas es " + ganancias);
                    // obtenerGanancias()
                    break;

                case 10:
                    System.out.println("\n--- RENOVAR MEMBRESÍA ---");
                    System.out.println("Ingresa la cedula de su usuario ");
                    String usuarioBusqueda = leer.next();
                    Usuario usuario = buscarUsuario(usuarios, usuarioBusqueda);
                    usuario.renovarMembresia();
                    // renovarMembresia()
                    break;

                case 11:
                    System.out.println("\n--- BUSCAR LIBRO ---");
                    System.out.println("Ingresa el ISBN del libro ");
                    String isbnBusqueda = leer.next();

                    Libro libroEncontrado1 = buscarLibro(libros, isbnBusqueda);

                    if (libroEncontrado1 == null) {
                        System.out.println("No se ha encontrado el usuario buscado");

                    } else {
                        System.out.println(libroEncontrado1);
                    }

                    break;

                case 12:
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
                case 13:
                    System.out.println("\n--- BUSCAR REGISTRO ---");

                    Registro registroEncontrado1 = buscarRegistros(registros);

                    if (registroEncontrado1 == null) {
                        System.out.println("No se ha encontrado el usuario buscado");

                    } else {
                        System.out.println(registroEncontrado1);
                    }

                    break;
                case 14:
                    System.out.println("\n--- BUSCAR AUTOR ---");
                    System.out.println("Ingresa el nombre del autor ");
                    String autorBusqueda = leer.next();
                    Autor autorBuscado = buscarAutor(autores, autorBusqueda);
                    if (autorBuscado == null) {
                        System.out.println("No se ha encontrado el usuario buscado");

                    } else {
                        System.out.println(autorBuscado);
                    }

                    break;

                /*
                     System.out.println("11. Buscar Libro");
            System.out.println("12. Buscar Usuario");
            System.out.println("13. Buscar Registro");
            System.out.println("13. Buscar Autor");*/
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

        System.out.println("Ingresa su dirrecion: ");
        String direccion = leer.nextLine();

        boolean tieneDiscapacidad = false;
        System.out.print("Ingresar si tiene discapacidad??");
        tieneDiscapacidad = pedirSiNo();

        boolean estadoVivo = true;
        if (!isPedidoUsuario) {
            System.out.print("Ingresar si tiene discapacidad??");
            estadoVivo = pedirSiNo();
        }

        System.out.println("Ingresar su genero: ");
        String genero = leer.next();

        return new Persona(cedula, edad, nombre, apellido, direccion, estadoVivo, tieneDiscapacidad, genero);
    }

    public static Membresia pedirMembresia() {
        Scanner leer = new Scanner(System.in);

        System.out.println("\n--- INGRESAR DATOS DE MEMBRESIA ---");
        System.out.println("Usted quiere tener membresia?(S/N) ");
        boolean resultado = pedirSiNo();
        if (resultado) {
            System.out.println("Ingresa su tipo de membresia: ");
            String tipo = leer.next();

            LocalDate fechaInicio = pedirFechaLocalDate();

            LocalDate fechaVencimiento = pedirFechaLocalDate();

            return new Membresia(tipo, fechaInicio, fechaVencimiento);

        } else {
            return new Membresia();
        }

    }

    public static boolean pedirSiNo() {

        Scanner leer = new Scanner(System.in);
        String variableLectora;
        System.out.println("(S/N)");
        variableLectora = leer.next();

        while (!variableLectora.equalsIgnoreCase("S") && !variableLectora.equalsIgnoreCase("N")) {
            System.out.println("Datos Incorrectos:Ingresar si  tiene discapasidad?? (S/N)");
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
        Persona persona = pedirDatosPersonales(false);
        Autor autor = new Autor(persona);
        return autor;

    }

    public static Autor buscarAutor(ArrayList<Autor> autores, String nombre) {

        for (Autor autor : autores) {

            if (autor.getDatoPersona().getNombre()
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

            if (usuario.getDatosPersonales().getCedula().equalsIgnoreCase(cedula)) {

                return usuario;
            }
        }

        return null;
    }

    public static Registro buscarRegistroId(ArrayList<Registro> registros, int id) {

        for (Registro registro : registros) {

            if (registro.getId() == id) {

                return registro;
            }
        }

        return null;
    }

    public static Registro buscarRegistroUsuario(ArrayList<Registro> registros, String cedula) {

        for (Registro registro : registros) {

            if (registro.getUsuario().getDatosPersonales().getCedula().equalsIgnoreCase(cedula)) {

                return registro;
            }
        }

        return null;
    }

    public static Registro buscarRegistroLibro(ArrayList<Registro> registros, String ISBN) {

        for (Registro registro : registros) {

            if (registro.getLibro().getISBN().equalsIgnoreCase(ISBN)) {

                return registro;
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

    public static void imprimirRegistros(ArrayList<Registro> registros) {
        for (Registro registro : registros) {
            System.out.println(registro);
        }
    }

    public static Registro buscarRegistros(ArrayList<Registro> registros) {
        Scanner leer = new Scanner(System.in);
        System.out.println("\n========= MENU DE BUSQUEDA =========");
        System.out.println("1. Id del registro");
        System.out.println("2. Cedula Usuario");
        System.out.println("3. ISBN libro");
        int opcionMenuBusqueda1 = leer.nextInt();
        Registro registroBuscado1 = null;

        switch (opcionMenuBusqueda1) {
            case 1:
                System.out.println("Ingresa el id de su registro ");
                int idRegistroBuscado = leer.nextInt();
                registroBuscado1 = buscarRegistroId(registros, idRegistroBuscado);
                break;
            case 2:
                System.out.println("Ingresa la cedula del usuario");
                String cedulaRegistroBuscado = leer.next();
                registroBuscado1 = buscarRegistroUsuario(registros, cedulaRegistroBuscado);
                break;
            case 3:
                System.out.println("Ingresa el ISBN del libro");
                String ISBNRegistroBuscado = leer.next();
                registroBuscado1 = buscarRegistroLibro(registros, ISBNRegistroBuscado);
                break;
            default:
                System.out.println("Usted ingreso los datos incorrectos");

                break;

        }

        return registroBuscado1;
    }
}
