package pkgReinoAnimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * Controlador de la fauna: administra la colección de animales
 * (agregar, buscar, eliminar, filtrar, estadísticas) y ofrece un menú por
 * consola.
 * Usa las clases definidas en Fauna.java (mismo paquete ReinoAnimal).
 */
public class ControladorFauna {
    private final List<Animal> animales = new ArrayList<>();

    /* ===================== OPERACIONES DEL CONTROLADOR ===================== */

    public void agregar(Animal animal) {
        if (animal == null) {
            throw new IllegalArgumentException("El animal no puede ser nulo.");
        }
        animales.add(animal);
    }

    public Animal buscar(String nombre) {
        for (Animal a : animales) {
            if (a.nombre.equalsIgnoreCase(nombre)) {
                return a;
            }
        }
        return null;
    }

    public boolean eliminar(String nombre) {
        Animal a = buscar(nombre);
        return a != null && animales.remove(a);
    }

    public List<Animal> getAnimales() {
        return Collections.unmodifiableList(animales);
    }

    /**
     * Filtra por cualquier clase o interfaz: Mamifero.class, Ave.class,
     * Volador.class, etc.
     */
    public List<Animal> filtrarPorTipo(Class<?> tipo) {
        return animales.stream().filter(tipo::isInstance).collect(Collectors.toList());
    }

    public void listar() {
        if (animales.isEmpty()) {
            System.out.println("No hay animales registrados.");
            return;
        }
        for (Animal a : animales) {
            System.out.println("----------------------------------");
            a.describir();
        }
    }

    public void hacerSonarATodos() {
        animales.forEach(Animal::emitirSonido);
    }

    public void moverATodos() {
        animales.forEach(Animal::moverse);
    }

    public void alimentarATodos() {
        animales.forEach(Animal::comer);
    }

    public void mostrarEstadisticas() {
        if (animales.isEmpty()) {
            System.out.println("No hay datos para mostrar.");
            return;
        }
        double pesoTotal = animales.stream().mapToDouble(a -> a.peso).sum();
        double edadPromedio = animales.stream().mapToInt(a -> a.edad).average().orElse(0);
        Animal masPesado = animales.stream()
                .max((a, b) -> Double.compare(a.peso, b.peso)).get();
        Animal masViejo = animales.stream()
                .max((a, b) -> Integer.compare(a.edad, b.edad)).get();

        Map<String, Long> porEspecie = animales.stream().collect(
                Collectors.groupingBy(a -> a.getClass().getSimpleName(), TreeMap::new, Collectors.counting()));

        System.out.println("Total de animales : " + animales.size());
        System.out.println("Peso total        : " + pesoTotal + " kg");
        System.out.println("Edad promedio     : " + String.format("%.1f", edadPromedio) + " años");
        System.out.println("Más pesado        : " + masPesado.nombre + " (" + masPesado.peso + " kg)");
        System.out.println("Más viejo         : " + masViejo.nombre + " (" + masViejo.edad + " años)");
        System.out.println("Por especie       : " + porEspecie);
        System.out.println("Mamíferos: " + filtrarPorTipo(Mamifero.class).size()
                + " | Aves: " + filtrarPorTipo(Ave.class).size()
                + " | Reptiles: " + filtrarPorTipo(Reptil.class).size()
                + " | Peces: " + filtrarPorTipo(Pez.class).size()
                + " | Insectos: " + filtrarPorTipo(Insecto.class).size()
                + " | Arácnidos: " + filtrarPorTipo(Aracnido.class).size());
    }

    public void cargarDatosIniciales() {
        agregar(new Perro("Rocky", 5, 18.5, "Labrador"));
        agregar(new Gato("Misu", 3, 4.2));
        agregar(new Delfin("Flipper", 10, 150));
        agregar(new Aguila("Zeus", 7, 5.3));
        agregar(new Pinguino("Pingu", 4, 6.8));
        agregar(new Serpiente("Kaa", 6, 12, true));
        agregar(new Tiburon("Bruce", 15, 900));
        agregar(new Mariposa("Monarca", 1, 0.0005));
        agregar(new Arana("Charlotte", 2, 0.01));
    }

    /* ===================== MENÚ POR CONSOLA ===================== */

    private static final Scanner sc = new Scanner(System.in);

    private static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return sc.nextLine().trim();
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            try {
                return Integer.parseInt(leerTexto(mensaje));
            } catch (NumberFormatException e) {
                System.out.println("Ingresa un número entero válido.");
            }
        }
    }

    private static double leerDecimal(String mensaje) {
        while (true) {
            try {
                return Double.parseDouble(leerTexto(mensaje).replace(',', '.'));
            } catch (NumberFormatException e) {
                System.out.println("Ingresa un número válido.");
            }
        }
    }

    private static void mostrarMenu() {
        System.out.println("\n========== CONTROLADOR DE FAUNA ==========");
        System.out.println("1. Listar animales");
        System.out.println("2. Agregar animal");
        System.out.println("3. Buscar animal por nombre");
        System.out.println("4. Eliminar animal");
        System.out.println("5. Hacer sonar a todos");
        System.out.println("6. Mover a todos");
        System.out.println("7. Alimentar a todos");
        System.out.println("8. Filtrar por categoría");
        System.out.println("9. Estadísticas");
        System.out.println("0. Salir");
    }

    private Animal crearAnimalDesdeConsola() {
        System.out.println("Tipo: 1.Perro 2.Gato 3.Delfín 4.Águila 5.Pingüino "
                + "6.Serpiente 7.Tiburón 8.Mariposa 9.Araña");
        int tipo = leerEntero("Elige el tipo: ");
        if (tipo < 1 || tipo > 9) {
            System.out.println("Tipo no válido.");
            return null;
        }
        String nombre = leerTexto("Nombre: ");
        int edad = leerEntero("Edad (años): ");
        double peso = leerDecimal("Peso (kg): ");

        switch (tipo) {
            case 1:
                return new Perro(nombre, edad, peso, leerTexto("Raza: "));
            case 2:
                return new Gato(nombre, edad, peso);
            case 3:
                return new Delfin(nombre, edad, peso);
            case 4:
                return new Aguila(nombre, edad, peso);
            case 5:
                return new Pinguino(nombre, edad, peso);
            case 6:
                boolean venenosa = leerTexto("¿Es venenosa? (s/n): ").equalsIgnoreCase("s");
                return new Serpiente(nombre, edad, peso, venenosa);
            case 7:
                return new Tiburon(nombre, edad, peso);
            case 8:
                return new Mariposa(nombre, edad, peso);
            default:
                return new Arana(nombre, edad, peso);
        }
    }

    private void menuFiltrar() {
        System.out.println("Categoría: 1.Mamíferos 2.Aves 3.Reptiles 4.Peces 5.Insectos "
                + "6.Arácnidos 7.Voladores 8.Nadadores");
        int op = leerEntero("Elige: ");
        Class<?> tipo;
        switch (op) {
            case 1:
                tipo = Mamifero.class;
                break;
            case 2:
                tipo = Ave.class;
                break;
            case 3:
                tipo = Reptil.class;
                break;
            case 4:
                tipo = Pez.class;
                break;
            case 5:
                tipo = Insecto.class;
                break;
            case 6:
                tipo = Aracnido.class;
                break;
            case 7:
                tipo = Volador.class;
                break;
            case 8:
                tipo = Nadador.class;
                break;
            default:
                System.out.println("Opción no válida.");
                return;
        }
        List<Animal> resultado = filtrarPorTipo(tipo);
        if (resultado.isEmpty()) {
            System.out.println("No hay animales en esa categoría.");
        } else {
            resultado.forEach(a -> {
                System.out.println("----------------------------------");
                a.describir();
            });
        }
    }

    public void iniciar() {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Opción: ");
            System.out.println();
            switch (opcion) {
                case 1:
                    listar();
                    break;
                case 2:
                    Animal nuevo = crearAnimalDesdeConsola();
                    if (nuevo != null) {
                        agregar(nuevo);
                        System.out.println("Animal agregado correctamente.");
                    }
                    break;
                case 3:
                    Animal encontrado = buscar(leerTexto("Nombre a buscar: "));
                    if (encontrado != null) {
                        encontrado.describir();
                    } else {
                        System.out.println("No se encontró ese animal.");
                    }
                    break;
                case 4:
                    System.out.println(eliminar(leerTexto("Nombre a eliminar: "))
                            ? "Animal eliminado."
                            : "No se encontró ese animal.");
                    break;
                case 5:
                    hacerSonarATodos();
                    break;
                case 6:
                    moverATodos();
                    break;
                case 7:
                    alimentarATodos();
                    break;
                case 8:
                    menuFiltrar();
                    break;
                case 9:
                    mostrarEstadisticas();
                    break;
                case 0:
                    System.out.println("Hasta pronto.");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    public static void main(String[] args) {
        ControladorFauna controlador = new ControladorFauna();
        controlador.cargarDatosIniciales();
        controlador.iniciar();
    }
}