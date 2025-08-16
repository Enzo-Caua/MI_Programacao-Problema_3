package pbl.controller.avaliacao;

import pbl.controller.busca.BuscarFilme;
import pbl.model.obras.Acervo;
import pbl.model.obras.Filme;
import pbl.model.persistencia.Serializacao;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe responsável por avaliar filmes e registrar reviews.
 */
public class AvaliarFilme {

    /**
     * Permite ao usuário atribuir uma pontuação a um filme previamente assistido.
     *
     * @param entrada Scanner utilizado para entrada de dados do usuário.
     */
    public static void pontuarFilme(Scanner entrada) {
        entrada.nextLine();

        System.out.println("Digite o título do filme que deseja pontuar:");
        String titulo = entrada.nextLine();

        ArrayList<Filme> lista = BuscarFilme.buscaTitulo(titulo);
        Filme filme = lista.getFirst();

        if (!filme.isVisto()) {
            System.out.println("Precisa ter visto antes de pontuar o filme.");
            System.out.println("Deseja marcar o filme como visto?");
            String resposta = entrada.nextLine();

            if (resposta.equalsIgnoreCase("Sim")) {
                System.out.println("Informe a data de visualização: ");
                String dataVisto = entrada.nextLine();
                String visto = filme.alterarVisto(dataVisto);
                System.out.println(visto);
            } else if (resposta.equalsIgnoreCase("Não")) {
                System.out.println("Não é possível pontuar o filme sem vê-lo!");
                return;
            }
        }

        System.out.println("Digite um número inteiro (1 a 5) para pontuar " + filme.getTitulo() + ":");
        int pontuacao = entrada.nextInt();

        Acervo.getAcervo().atualizarAvaliacaoFilme(filme, pontuacao);
        Serializacao.salvarFilme(Acervo.getAcervo().getFilmes());
        System.out.println(filme.getTitulo() + " foi avaliado com a nota " + filme.getAvaliacao() + "!");
    }

    /**
     * Permite ao usuário escrever uma review sobre um filme previamente assistido.
     *
     * @param entrada Scanner utilizado para entrada de dados do usuário.
     */
    public static void reviewFilme(Scanner entrada) {
        entrada.nextLine();

        System.out.println("Digite o título do filme que deseja escrever review:");
        String titulo = entrada.nextLine();

        ArrayList<Filme> lista = BuscarFilme.buscaTitulo(titulo);
        Filme filme = lista.getFirst();

        if (!filme.isVisto()) {
            System.out.println("Precisa ter visto antes de adicionar review ao filme.");
            System.out.println("Deseja marcar o filme como visto?");
            String resposta = entrada.nextLine();

            if (resposta.equalsIgnoreCase("Sim")) {
                System.out.println("Informe a data de visualização: ");
                String dataVisto = entrada.nextLine();
                String visto = filme.alterarVisto(dataVisto);
                System.out.println(visto);
            } else if (resposta.equalsIgnoreCase("Não")) {
                System.out.println("Não é possivel adicionar review ao filme sem vê-lo!");
                return;
            }
        }

        System.out.println("Digite a sua review para o filme " + filme.getTitulo() + ":");
        String review = entrada.nextLine();

        filme.setReview(review);
        Serializacao.salvarFilme(Acervo.getAcervo().getFilmes());
        System.out.println(filme.getTitulo() + " teve a review cadastrada com sucesso!");
    }
}
