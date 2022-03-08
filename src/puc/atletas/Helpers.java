package puc.atletas;

import javax.swing.*;

public class Helpers {

    private boolean isValidInt(String s) {
        try {
            Integer.parseInt(s);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }

    public int returnInt(String v) {
        while (this.isValidInt(v)) {
            v = JOptionPane.showInputDialog(null, "Valor inválido!\n\nInforme um número inteiro:");
        }

        return Integer.parseInt(v);
    }

    private boolean isValidDouble(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public int returnDouble(String v) {
        while (!this.isValidDouble(v)) {
            v = JOptionPane.showInputDialog(null, "Valor inválido!\n\nInforme um número no formato (00.00):");
        }

        return Integer.parseInt(v);
    }
}
