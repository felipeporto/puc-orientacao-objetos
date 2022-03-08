package puc.atletas;

import java.io.Serializable;

public class Atleta implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nome;
    private int numero;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "Atleta: " + nome + "\n" +
                "Número: " + numero;
    }
}
