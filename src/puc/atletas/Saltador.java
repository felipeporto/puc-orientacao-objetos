package puc.atletas;

public class Saltador extends Atleta {
    private double altura;

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
                "Altura: " + String.format("%.2f", altura) + "\n" +
                "Tipo: Saltador";
    }
}
