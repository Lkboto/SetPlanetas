import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class CuerpoCeleste {

    public enum TipoCuerpoCeleste {
        ESTRELLA, PLANETA, PLANETA_ENANO, LUNA, COMETA, ASTEROIDE
    }

    private String nombre;
    private double periodoOrbital;
    private Set<CuerpoCeleste> satelites;
    private TipoCuerpoCeleste tipoCuerpo;

    public CuerpoCeleste(String nombre, double periodoOrbital, TipoCuerpoCeleste tipoCuerpo) {
        this.nombre = nombre;
        this.periodoOrbital = periodoOrbital;
        this.tipoCuerpo = tipoCuerpo;
        this.satelites = new HashSet<>();
    }

    public double getPeriodoOrbital() {
        return periodoOrbital;
    }

    public String getNombre() {
        return nombre;
    }

    public TipoCuerpoCeleste getTipoCuerpo() {
        return tipoCuerpo;
    }

    public Set<CuerpoCeleste> getSatelites() {
        return new HashSet<>(satelites);
    }

    public boolean addSatelite(CuerpoCeleste satelite) {
        return satelites.add(satelite);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof CuerpoCeleste) {
            CuerpoCeleste that = (CuerpoCeleste) obj;
            return Objects.equals(this.nombre, that.nombre) && this.tipoCuerpo == that.tipoCuerpo;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hashNombre = (nombre != null) ? nombre.hashCode() : 0;
        int hashTipo = (tipoCuerpo != null) ? tipoCuerpo.hashCode() : 0;
        int numeroArbitrario = 17;
        return hashNombre + hashTipo + numeroArbitrario;
    }

    @Override
    public String toString() {
        return nombre + ": " + tipoCuerpo + ", " + periodoOrbital;
    }
}
