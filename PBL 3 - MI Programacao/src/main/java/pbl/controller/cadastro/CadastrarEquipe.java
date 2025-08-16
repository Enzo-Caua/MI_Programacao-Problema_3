package pbl.controller.cadastro;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe responsável por cadastrar pessoas no elenco.
 */
public class CadastrarEquipe {

    /**
     * Solicita os dados dos diretores ao usuário e os adiciona ao elenco.
     *
     * @param entrada Scanner para entrada de dados.
     */
    public static ArrayList<String> cadastrarDiretores(Scanner entrada) {
        ArrayList<String> diretores = new ArrayList<>();

        System.out.print("Número de diretores: ");
        int num = entrada.nextInt();

        entrada.nextLine();

        for (int i = 1; i <= num; i++) {
            System.out.print("Digite o nome do(a) diretor(a) " + i + ": ");
            String nome = entrada.nextLine();
            diretores.add(nome);
        }
        return diretores;
    }

    /**
     * Solicita os dados dos roteiristas ao usuário e os adiciona ao elenco.
     *
     * @param entrada Scanner para entrada de dados.
     */
    public static ArrayList<String> cadastrarRoteiristas(Scanner entrada) {
        ArrayList<String> roteiristas = new ArrayList<>();

        System.out.print("Número de roteiristas: ");
        int num = entrada.nextInt();

        entrada.nextLine();

        for (int i = 1; i <= num; i++) {
            System.out.print("Digite o nome do(a) roteirista " + i + ": ");
            String nome = entrada.nextLine();
            roteiristas.add(nome);
        }
        return roteiristas;
    }

    /**
     * Solicita os dados dos atores ao usuário e os adiciona ao elenco.
     *
     * @param entrada Scanner para entrada de dados.
     */
    public static ArrayList<String> cadastrarAtores(Scanner entrada) {
        ArrayList<String> atores = new ArrayList<>();

        System.out.print("Número de atores no elenco: ");
        int num = entrada.nextInt();

        entrada.nextLine();

        for (int i = 1; i <= num; i++) {
            System.out.print("Digite o nome do ator " + i + ": ");
            String nome = entrada.nextLine();
            atores.add(nome);
        }
        return atores;
    }
}
