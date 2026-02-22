public class Planeta extends CuerpoCeleste {

    public Planeta(String nombre, double periodoOrbital) {
        super(nombre, periodoOrbital, TipoCuerpoCeleste.PLANETA);
    }

    @Override
    public boolean addSatelite(CuerpoCeleste satelite) {
        if (satelite.getTipoCuerpo() == TipoCuerpoCeleste.LUNA) {
            return super.addSatelite(satelite);
        }
        return false;
    }
}
