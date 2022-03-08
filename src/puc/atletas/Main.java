package puc.atletas;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class Main {
    private JPanel mainPanel;
    private JButton cadastrarButton;
    private JButton limparButton;
    private JButton listarButton;
    private JButton gravarButton;
    private JButton recuprarButton;
    private JButton sairButton;
    private JLabel titleLabel;

    ArrayList<Atleta> atletas = new ArrayList<>();
    Helpers helpers = new Helpers();

    public static void main(String[] args) {
        JFrame frame = new JFrame("Gestão de Atletas");
        frame.setContentPane(new Main().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(500, 250));
        frame.setVisible(true);
    }

    public Main() {
        menuItemsBind();

//        Lista de atletas para validações:
//
//        Nadador atl1 = new Nadador();
//        atl1.setNome("Felipe Porto");
//        atl1.setNumero(123);
//        atl1.setEstilo("Borboleta");
//
//        Corredor atl2 = new Corredor();
//        atl2.setNome("John Doe");
//        atl2.setNumero(345);
//        atl2.setVelocidade(44);
//
//        atletas.add(atl1);
//        atletas.add(atl2);
    }

    /**
     * Adiciona listeners para cada botão da tela principal
     */
    private void menuItemsBind() {
        cadastrarButton.addActionListener(e -> menuItemClick(1));
        listarButton.addActionListener(e -> menuItemClick(2));
        limparButton.addActionListener(e -> menuItemClick(3));
        gravarButton.addActionListener(e -> menuItemClick(4));
        recuprarButton.addActionListener(e -> menuItemClick(5));
        sairButton.addActionListener(e -> menuItemClick(6));
    }

    /**
     * Executa as ações relacionadas a cada botão
     *
     * @param menuOption Item do menu que está sendo clicado
     */
    private void menuItemClick(int menuOption) {
        /**
         * Nota:
         * Poderia executar a ação de cada botão diretamente no listener de cada botão
         * mas a atividade pede que seja criado o switch
         */
        switch (menuOption) {
            case 1 -> addAction();
            case 2 -> listAction();
            case 3 -> clearAction();
            case 4 -> saveAction();
            case 5 -> restoreAction();
            case 6 -> exitAction();
            default -> throw new IllegalStateException("Valor inesperado: " + menuOption);
        }
    }

    private void addAction() {
        Atleta atl;

        String menuModalidade = """
                Cadastro de atletas:

                Informe a modalidade:
                1 - Corredor
                2 - Nadador
                3 - Saltador""";

        int modalidade = helpers.returnInt(JOptionPane.showInputDialog(mainPanel, menuModalidade + "\n\n"));

        if (modalidade < 1 || modalidade > 3) {
            JOptionPane.showMessageDialog(mainPanel, "Opção inválida!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String atlNome = JOptionPane.showInputDialog(mainPanel, "Informe o nome: ");
        int atlNum = helpers.returnInt(JOptionPane.showInputDialog(mainPanel, "Informe o número de registro: "));

        switch (modalidade) {
            case 1 -> atl = createCorredor();
            case 2 -> atl = createNadador();
            case 3 -> atl = createSaltador();
            default -> throw new IllegalStateException("Valor inesperado: " + modalidade);
        }

        atl.setNome(atlNome);
        atl.setNumero(atlNum);

        atletas.add(atl);

        JOptionPane.showMessageDialog(mainPanel, "Atleta cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    private Corredor createCorredor() {
        Corredor atl = new Corredor();

        double velocidade = helpers.returnDouble(JOptionPane.showInputDialog(mainPanel, "Informe a velocidade (km/h): "));
        atl.setVelocidade(velocidade);

        return atl;
    }

    private Nadador createNadador() {
        Nadador atl = new Nadador();

        String estilo = JOptionPane.showInputDialog(mainPanel, "Informe o estilo de nado: ");
        atl.setEstilo(estilo);

        return atl;
    }

    private Saltador createSaltador() {
        Saltador atl = new Saltador();

        double altura = helpers.returnDouble(JOptionPane.showInputDialog(mainPanel, "Informe a altura (metros): "));
        atl.setAltura(altura);

        return atl;
    }

    /**
     * Menu: Listar
     * Lista todos atlétas que estão salvos em memória
     */
    private void listAction() {
        if (atletas.size() == 0) {
            JOptionPane.showMessageDialog(mainPanel, "Nenhum atleta cadastrado.", "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }

        StringBuilder message = new StringBuilder();

        for (Atleta atl : atletas) {
            message.append(atl.toString());
            message.append("\n------\n");
        }

        JOptionPane.showMessageDialog(mainPanel, message.toString());
    }

    /**
     * Menu: Limpar
     * Limpa a lista de atletas em memória
     */
    private void clearAction() {
        atletas.clear();
        JOptionPane.showMessageDialog(mainPanel, "Lista de atletas limpa com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Menu: Salvar
     * Salva em disco a lista de atléticas que está em memória
     *
     * Os dados serão salvos no arquivo data.dat, localizado na mesma pasta do projeto
     */
    private void saveAction() {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("data.dat"));

            for (Atleta atl : atletas)
                outputStream.writeObject(atl);

            outputStream.flush();
            outputStream.close();

            JOptionPane.showMessageDialog(mainPanel, "Atletas salvos com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(mainPanel, "Não foi possível salvar o arquivo!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Menu: Recuperar
     * Recupera as informações do disco e salva em memória
     *
     * Lê do arquivo data.dat, que deve estar localizado na mesma pasta do projeto
     */
    private void restoreAction() {
        atletas.clear();

        ObjectInputStream inputStream = null;

        try {
            inputStream = new ObjectInputStream(new FileInputStream("data.dat"));

            Object obj;
            while ((obj = inputStream.readObject()) != null) {
                if (obj instanceof Atleta) {
                    atletas.add((Atleta) obj);
                }
            }
        }
        catch (EOFException e) {}
        catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(mainPanel, "Não foi possível recuperar a lista de atletas!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        finally {
            try {
                if (inputStream != null) {
                    inputStream.close();

                    JOptionPane.showMessageDialog(mainPanel, "Atletas recuperados com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Menu: Sair
     * Fecha o programa
     */
    private void exitAction() {
        JOptionPane.showMessageDialog(mainPanel, "O aplicativo será fechado.", "Atenção", JOptionPane.WARNING_MESSAGE);
        System.exit(0);
    }
}