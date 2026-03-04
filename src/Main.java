import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {

    static Map<String, CuerpoCeleste> sistemaSolar = new HashMap<>();
    static Set<CuerpoCeleste> planetas = new HashSet<>();

    private static void agregarPlaneta(String nombre, double periodo) {
        CuerpoCeleste planeta = new Planeta(nombre, periodo);
        sistemaSolar.put(nombre, planeta);
        planetas.add(planeta);
    }

    public static void main(String[] args) {
        CuerpoCeleste mercurio = new Planeta("Mercurio", 88);
        sistemaSolar.put(mercurio.getNombre(), mercurio);
        planetas.add(mercurio);

        agregarPlaneta("Venus", 225);
        agregarPlaneta("La Tierra", 365);
        agregarPlaneta("Marte", 687);
        agregarPlaneta("Jupiter", 4332);
        agregarPlaneta("Saturno", 10759);
        agregarPlaneta("Urano", 30660);
        agregarPlaneta("Neptuno", 165);
        agregarPlaneta("Pluton", 248);

        CuerpoCeleste luna = new Luna("Luna", 27);
        sistemaSolar.put(luna.getNombre(), luna);
        sistemaSolar.get("La Tierra").addSatelite(luna);

        CuerpoCeleste deimos = new Luna("Deimos", 1.3);
        CuerpoCeleste phobos = new Luna("Phobos", 0.3);
        sistemaSolar.put(deimos.getNombre(), deimos);
        sistemaSolar.put(phobos.getNombre(), phobos);
        sistemaSolar.get("Marte").addSatelite(deimos);
        sistemaSolar.get("Marte").addSatelite(phobos);

        CuerpoCeleste io = new Luna("Io", 1.8);
        CuerpoCeleste europa = new Luna("Europa", 3.5);
        CuerpoCeleste ganymede = new Luna("Ganymede", 7.1);
        CuerpoCeleste callisto = new Luna("Callisto", 16.7);
        sistemaSolar.put(io.getNombre(), io);
        sistemaSolar.put(europa.getNombre(), europa);
        sistemaSolar.put(ganymede.getNombre(), ganymede);
        sistemaSolar.put(callisto.getNombre(), callisto);
        sistemaSolar.get("Jupiter").addSatelite(io);
        sistemaSolar.get("Jupiter").addSatelite(europa);
        sistemaSolar.get("Jupiter").addSatelite(ganymede);
        sistemaSolar.get("Jupiter").addSatelite(callisto);

        System.out.println("--- Nombres de los planetas en el Set ---");
        for (CuerpoCeleste planeta : planetas) {
            System.out.println(planeta.getNombre());
        }

        System.out.println("\n--- Lunas de Marte ---");
        CuerpoCeleste marte = sistemaSolar.get("Marte");
        for (CuerpoCeleste satelite : marte.getSatelites()) {
            System.out.println(satelite.getNombre());
        }

        Set<CuerpoCeleste> lunas = new HashSet<>();
        for (CuerpoCeleste planeta : planetas) {
            lunas.addAll(planeta.getSatelites());
        }

        System.out.println("\n--- Set de lunas (Unión) ---");
        System.out.println("Cantidad de lunas: " + lunas.size());
        for (CuerpoCeleste lunaObj : lunas) {
            System.out.println(lunaObj.getNombre());
        }

        System.out.println("\n--- Creando un nuevo Plutón-884 (Planeta) ---");
        CuerpoCeleste pluton2 = new Planeta("Pluton", 884);
        planetas.add(pluton2);

        System.out.println("Set de planetas después de intentar añadir Plutón-884:");
        for (CuerpoCeleste planeta : planetas) {
            System.out.println(planeta.toString());
        }
        System.out.println("\n¿Se ha agregado este planeta al conjunto? ¿Por qué?");
        System.out.println("Respuesta: No se ha agregado. Porque la clase CuerpoCeleste sobrescribe los métodos equals y hashCode " +
                           "para que dos objetos sean iguales si tienen el mismo nombre y tipo de cuerpo. Como ya existe un Pluton " +
                           "de tipo PLANETA en el conjunto (Set que no permite duplicados), este nuevo objeto no se inserta.");

        System.out.println("\n--- Creando un nuevo Plutón-884 (Planeta Enano) ---");
        CuerpoCeleste plutonEnano = new PlanetaEnano("Pluton", 884);
        planetas.add(plutonEnano);

        System.out.println("Set de planetas después de añadir Plutón (Planeta Enano):");
        for (CuerpoCeleste planeta : planetas) {
            System.out.println(planeta.toString());
        }
        System.out.println("\nRespuesta: Sí se añade en este caso, porque el tipo de cuerpo es diferente (PLANETA_ENANO en lugar de PLANETA), " +
                           "por lo tanto el método equals devuelve false, considerando que son objetos distintos.");

        System.out.println("\n--- Diferencia e Intersección de Conjuntos de Lunas ---");
        Set<CuerpoCeleste> lunasMarte = marte.getSatelites();
        
        // Intersección (retainAll)
        Set<CuerpoCeleste> interseccion = new HashSet<>(lunas);
        interseccion.retainAll(lunasMarte);
        System.out.println("\nIntersección de todas las lunas y las lunas de Marte:");
        System.out.println("Tenemos el conjunto 'lunas' (todas las lunas, tamaño 7) y el conjunto 'lunasMarte' (Deimos, Phobos, tamaño 2).");
        System.out.println("La intersección (retainAll) modifica el primer conjunto para mantener solo los elementos que también están en el segundo.");
        System.out.println("Resultado esperado: Solo las lunas de Marte (Deimos y Phobos). Tamaño final: " + interseccion.size());
        for (CuerpoCeleste l : interseccion) {
            System.out.println(l.getNombre());
        }

        // Diferencia (removeAll)
        Set<CuerpoCeleste> diferencia = new HashSet<>(lunas);
        diferencia.removeAll(lunasMarte);
        System.out.println("\nDiferencia de todas las lunas menos las lunas de Marte:");
        System.out.println("La diferencia (removeAll) elimina del primer conjunto todos los elementos que están presentes en el segundo.");
        System.out.println("Resultado esperado: Las lunas de la Tierra y Júpiter, sin incluir las de Marte. Tamaño final: " + diferencia.size());
        for (CuerpoCeleste l : diferencia) {
            System.out.println(l.getNombre());
        }
    }
}