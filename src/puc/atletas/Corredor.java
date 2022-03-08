package puc.atletas;

public class Corredor extends Atleta {
    private double velocidade;

    public double getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(double velocidade) {
        this.velocidade = velocidade;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
                "Velocidade: " + String.format("%.2f", velocidade) + "km/h\n" +
                "Tipo: Corredor";
    }
}
