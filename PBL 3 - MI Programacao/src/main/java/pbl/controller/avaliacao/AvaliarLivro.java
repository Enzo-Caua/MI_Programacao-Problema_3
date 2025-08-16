package pbl.controller.avaliacao;

import pbl.controller.busca.BuscarLivro;
import pbl.model.obras.Acervo;
import pbl.model.obras.Livro;
import pbl.model.persistencia.Serializacao;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe responsável por avaliar livros e registrar reviews.
 */
public class AvaliarLivro {

    /**
     * Permite ao usuário atribuir uma pontuação a um livro previamente lido.
     *
     * @param entrada Scanner utilizado para entrada de dados do usuário.
     */
    public static void pontuarLivro(Scanner entrada) {
        entrada.nextLine();

        System.out.println("Digite o título do livro que deseja pontuar:");
        String titulo = entrada.nextLine();

        ArrayList<Livro> lista = BuscarLivro.buscaTitulo(titulo);
        Livro livro = lista.getFirst();

        if (!livro.isVisto()) {
            System.out.println("Precisa ter lido antes de pontuar o livro.");
            System.out.println("Deseja marcar o livro como lido?");
            String resposta = entrada.nextLine();

            if (resposta.equalsIgnoreCase("Sim")) {
                System.out.println("Informe a data de leitura do livro: ");
                String dataVisto = entrada.nextLine();
                String visto = livro.alterarVisto(dataVisto);
                System.out.println(visto);
            } else if (resposta.equalsIgnoreCase("Nao")) {
                System.out.println("Não é possível pontuar o livro sem lê-lo!");
                return;
            }
        }

        System.out.println("Digite um número inteiro (1 a 5) para pontuar " + livro.getTitulo() + ":");
        int pontuacao = entrada.nextInt();

        Acervo.getAcervo().atualizarAvaliacaoLivro(livro, pontuacao);
        Serializacao.salvarLivro(Acervo.getAcervo().getLivros());
        System.out.println(livro.getTitulo() + " foi avaliado com a nota " + livro.getAvaliacao() + "!");
    }

    /**
     * Permite ao usuário escrever uma review sobre um livro previamente lido.
     *
     * @param entrada Scanner utilizado para entrada de dados do usuário.
     */
    public static void reviewLivro(Scanner entrada) {
        entrada.nextLine();

        System.out.println("Digite o título do livro que deseja escrever review:");
        String titulo = entrada.nextLine();

        ArrayList<Livro> lista = BuscarLivro.buscaTitulo(titulo);
        Livro livro = lista.getFirst();

        if (!livro.isVisto()) {
            System.out.println("Precisa ter visto antes de adicionar review ao livro");
            System.out.println("Deseja marcar o livro como lido?");
            String resposta = entrada.nextLine();

            if (resposta.equalsIgnoreCase("Sim")) {
                System.out.println("Informe a data de leitura do livro: ");
                String dataVisto = entrada.nextLine();
                String visto = livro.alterarVisto(dataVisto);
                System.out.println(visto);
            } else if (resposta.equalsIgnoreCase("Nao")) {
                System.out.println("Não é possível adicionar review ao livro sem lê-lo!");
                return;
            }
        }

        System.out.println("Digite a sua review para o livro " + livro.getTitulo() + ":");
        String review = entrada.nextLine();

        livro.setReview(review);
        Serializacao.salvarLivro(Acervo.getAcervo().getLivros());
        System.out.println(livro.getTitulo() + " teve a review cadastrada com sucesso!");
    }
}
