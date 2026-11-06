package pkgReinoAnimal;

/* ===================== INTERFACES ===================== */
interface Volador {
    void volar();
}

interface Nadador {
    void nadar();
}

/* ===================== CLASE BASE ===================== */
abstract class Animal {
    protected String nombre;
    protected int edad;
    protected double peso;

    public Animal(String nombre, int edad, double peso) {
        this.nombre = nombre;
        this.edad = edad;
        this.peso = peso;
    }

    public abstract void emitirSonido();

    public abstract void moverse();

    public void comer() {
        System.out.println(nombre + " está comiendo.");
    }

    public void dormir() {
        System.out.println(nombre + " está durmiendo.");
    }

    public void describir() {
        System.out.println("Nombre: " + nombre + " | Edad: " + edad + " años | Peso: " + peso + " kg");
    }
}

/* ===================== VERTEBRADOS / INVERTEBRADOS ===================== */
abstract class Vertebrado extends Animal {
    protected boolean columnaVertebral = true;

    public Vertebrado(String nombre, int edad, double peso) {
        super(nombre, edad, peso);
    }

    @Override
    public void describir() {
        super.describir();
        System.out.println("Tipo: Vertebrado (con columna vertebral)");
    }
}

abstract class Invertebrado extends Animal {
    protected boolean exoesqueleto;

    public Invertebrado(String nombre, int edad, double peso, boolean exoesqueleto) {
        super(nombre, edad, peso);
        this.exoesqueleto = exoesqueleto;
    }

    @Override
    public void describir() {
        super.describir();
        System.out.println("Tipo: Invertebrado | Exoesqueleto: " + (exoesqueleto ? "sí" : "no"));
    }
}

/* ===================== CLASES DE VERTEBRADOS ===================== */
abstract class Mamifero extends Vertebrado {
    public Mamifero(String nombre, int edad, double peso) {
        super(nombre, edad, peso);
    }

    public void amamantar() {
        System.out.println(nombre + " amamanta a sus crías.");
    }

    @Override
    public void describir() {
        super.describir();
        System.out.println("Clase: Mamífero (sangre caliente, pelo)");
    }
}

abstract class Ave extends Vertebrado {
    protected double envergadura;

    public Ave(String nombre, int edad, double peso, double envergadura) {
        super(nombre, edad, peso);
        this.envergadura = envergadura;
    }

    public void ponerHuevos() {
        System.out.println(nombre + " pone huevos.");
    }

    @Override
    public void describir() {
        super.describir();
        System.out.println("Clase: Ave | Envergadura: " + envergadura + " m");
    }
}

abstract class Reptil extends Vertebrado {
    public Reptil(String nombre, int edad, double peso) {
        super(nombre, edad, peso);
    }

    public void mudarPiel() {
        System.out.println(nombre + " muda de piel.");
    }

    @Override
    public void describir() {
        super.describir();
        System.out.println("Clase: Reptil (sangre fría, escamas)");
    }
}

abstract class Pez extends Vertebrado implements Nadador {
    public Pez(String nombre, int edad, double peso) {
        super(nombre, edad, peso);
    }

    public void respirarPorBranquias() {
        System.out.println(nombre + " respira por branquias.");
    }

    @Override
    public void nadar() {
        System.out.println(nombre + " nada con sus aletas.");
    }

    @Override
    public void moverse() {
        nadar();
    }

    @Override
    public void describir() {
        super.describir();
        System.out.println("Clase: Pez (branquias, aletas)");
    }
}

/* ===================== CLASES DE INVERTEBRADOS ===================== */
abstract class Insecto extends Invertebrado {
    protected final int numeroPatas = 6;

    public Insecto(String nombre, int edad, double peso) {
        super(nombre, edad, peso, true);
    }

    @Override
    public void describir() {
        super.describir();
        System.out.println("Clase: Insecto | Patas: " + numeroPatas);
    }
}

abstract class Aracnido extends Invertebrado {
    protected final int numeroPatas = 8;

    public Aracnido(String nombre, int edad, double peso) {
        super(nombre, edad, peso, true);
    }

    public void tejerTela() {
        System.out.println(nombre + " teje una tela.");
    }

    @Override
    public void describir() {
        super.describir();
        System.out.println("Clase: Arácnido | Patas: " + numeroPatas);
    }
}

/* ===================== ANIMALES CONCRETOS ===================== */
class Perro extends Mamifero {
    private String raza;

    public Perro(String nombre, int edad, double peso, String raza) {
        super(nombre, edad, peso);
        this.raza = raza;
    }

    @Override
    public void emitirSonido() {
        System.out.println(nombre + " dice: ¡Guau guau!");
    }

    @Override
    public void moverse() {
        System.out.println(nombre + " corre en cuatro patas.");
    }

    @Override
    public void describir() {
        super.describir();
        System.out.println("Especie: Perro | Raza: " + raza);
    }
}

class Gato extends Mamifero {
    public Gato(String nombre, int edad, double peso) {
        super(nombre, edad, peso);
    }

    @Override
    public void emitirSonido() {
        System.out.println(nombre + " dice: ¡Miau!");
    }

    @Override
    public void moverse() {
        System.out.println(nombre + " camina sigilosamente.");
    }

    @Override
    public void describir() {
        super.describir();
        System.out.println("Especie: Gato");
    }
}

class Delfin extends Mamifero implements Nadador {
    public Delfin(String nombre, int edad, double peso) {
        super(nombre, edad, peso);
    }

    @Override
    public void emitirSonido() {
        System.out.println(nombre + " emite clics y silbidos.");
    }

    @Override
    public void nadar() {
        System.out.println(nombre + " nada y salta sobre las olas.");
    }

    @Override
    public void moverse() {
        nadar();
    }

    @Override
    public void describir() {
        super.describir();
        System.out.println("Especie: Delfín");
    }
}

class Aguila extends Ave implements Volador {
    public Aguila(String nombre, int edad, double peso) {
        super(nombre, edad, peso, 2.1);
    }

    @Override
    public void emitirSonido() {
        System.out.println(nombre + " lanza un chillido agudo.");
    }

    @Override
    public void volar() {
        System.out.println(nombre + " vuela a gran altura.");
    }

    @Override
    public void moverse() {
        volar();
    }

    @Override
    public void describir() {
        super.describir();
        System.out.println("Especie: Águila");
    }
}

class Pinguino extends Ave implements Nadador {
    public Pinguino(String nombre, int edad, double peso) {
        super(nombre, edad, peso, 0.7);
    }

    @Override
    public void emitirSonido() {
        System.out.println(nombre + " dice: ¡Honk honk!");
    }

    @Override
    public void nadar() {
        System.out.println(nombre + " nada rápido bajo el agua.");
    }

    @Override
    public void moverse() {
        System.out.println(nombre + " camina torpemente y luego nada.");
        nadar();
    }

    @Override
    public void describir() {
        super.describir();
        System.out.println("Especie: Pingüino (no vuela)");
    }
}

class Serpiente extends Reptil {
    private boolean venenosa;

    public Serpiente(String nombre, int edad, double peso, boolean venenosa) {
        super(nombre, edad, peso);
        this.venenosa = venenosa;
    }

    @Override
    public void emitirSonido() {
        System.out.println(nombre + " dice: ¡Ssssss!");
    }

    @Override
    public void moverse() {
        System.out.println(nombre + " se arrastra por el suelo.");
    }

    @Override
    public void describir() {
        super.describir();
        System.out.println("Especie: Serpiente | Venenosa: " + (venenosa ? "sí" : "no"));
    }
}

class Tiburon extends Pez {
    public Tiburon(String nombre, int edad, double peso) {
        super(nombre, edad, peso);
    }

    @Override
    public void emitirSonido() {
        System.out.println(nombre + " no emite sonidos (silencio bajo el agua).");
    }

    @Override
    public void comer() {
        System.out.println(nombre + " caza peces y focas.");
    }

    @Override
    public void describir() {
        super.describir();
        System.out.println("Especie: Tiburón");
    }
}

class Mariposa extends Insecto implements Volador {
    public Mariposa(String nombre, int edad, double peso) {
        super(nombre, edad, peso);
    }

    @Override
    public void emitirSonido() {
        System.out.println(nombre + " vuela en silencio.");
    }

    @Override
    public void volar() {
        System.out.println(nombre + " aletea entre las flores.");
    }

    @Override
    public void moverse() {
        volar();
    }

    @Override
    public void describir() {
        super.describir();
        System.out.println("Especie: Mariposa");
    }
}

class Arana extends Aracnido {
    public Arana(String nombre, int edad, double peso) {
        super(nombre, edad, peso);
    }

    @Override
    public void emitirSonido() {
        System.out.println(nombre + " no emite sonidos audibles.");
    }

    @Override
    public void moverse() {
        System.out.println(nombre + " camina con sus 8 patas.");
    }

    @Override
    public void describir() {
        super.describir();
        System.out.println("Especie: Araña");
    }
}

/* ===================== CLASE PRINCIPAL ===================== */
public class Fauna {
    public static void main(String[] args) {
        Animal[] zoologico = {
                new Perro("Rocky", 5, 18.5, "Labrador"),
                new Gato("Misu", 3, 4.2),
                new Delfin("Flipper", 10, 150),
                new Aguila("Zeus", 7, 5.3),
                new Pinguino("Pingu", 4, 6.8),
                new Serpiente("Kaa", 6, 12, true),
                new Tiburon("Bruce", 15, 900),
                new Mariposa("Monarca", 1, 0.0005),
                new Arana("Charlotte", 2, 0.01)
        };

        for (Animal a : zoologico) {
            System.out.println("==================================");
            a.describir();
            a.emitirSonido();
            a.moverse();
            a.comer();

            // Comportamientos propios de cada tipo (polimorfismo + instanceof)
            if (a instanceof Mamifero m)
                m.amamantar();
            if (a instanceof Ave av)
                av.ponerHuevos();
            if (a instanceof Reptil r)
                r.mudarPiel();
            if (a instanceof Aracnido ar)
                ar.tejerTela();
            if (a instanceof Volador v)
                v.volar();
            if (a instanceof Nadador n)
                n.nadar();
        }
    }
}
