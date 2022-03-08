package puc.atletas;

public class Nadador extends Atleta {
    private String estilo;

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
                "Velocidade: " + estilo + "\n" +
                "Tipo: Nadador";
    }
}
