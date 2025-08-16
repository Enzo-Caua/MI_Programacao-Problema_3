package pbl.controller.avaliacao;


import pbl.controller.busca.BuscarSerie;
import pbl.model.obras.Acervo;
import pbl.model.obras.Serie;
import pbl.model.obras.Temporada;
import pbl.model.persistencia.Serializacao;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe responsável por permitir avaliação e escrita de reviews
 * para temporadas de séries específicas.
 */
public class AvaliarTemporada {

    /**
     * Permite ao usuário avaliar uma temporada específica de uma série.
     * A nota é atribuída diretamente à temporada e influencia a média da série.
     *
     * @param entrada Scanner utilizado para entrada de dados do usuário.
     */
    public static void pontuarTemporada(Scanner entrada) {
        entrada.nextLine();

        System.out.println("Digite o título da série que deseja avaliar:");
        String titulo = entrada.nextLine();

        ArrayList<Serie> lista = BuscarSerie.buscaTitulo(titulo);
        Serie serie = lista.getFirst();

        System.out.println("Digite o número da temporada que deseja pontuar:");
        int num = entrada.nextInt();
        Temporada temporada = BuscarSerie.buscaTemporada(num, serie);

        if (temporada != null) {
            System.out.println("Digite um número inteiro (1 a 5) para pontuar a temporada " +
                    temporada.getTempNumero() + " de " + serie.getTitulo() + ":");
            int pontuacao = entrada.nextInt();

            temporada.setAvaliacao(pontuacao);
            Acervo.getAcervo().avaliarTemporadaSerie(serie, pontuacao);

            System.out.println("A temporada " + temporada.getTempNumero() +
                    " foi avaliada com a nota " + temporada.getAvaliacao() + "!");
        } else {
            System.out.println("Temporada não existente");
        }

    }

    /**
     * Permite ao usuário escrever uma review para uma temporada específica de uma série.
     *
     * @param entrada Scanner utilizado para entrada de dados do usuário.
     */
    public static void reviewTemporada(Scanner entrada) {
        entrada.nextLine();

        System.out.println("Digite o título da série que deseja avaliar:");
        String titulo = entrada.nextLine();

        ArrayList<Serie> lista = BuscarSerie.buscaTitulo(titulo);
        Serie serie = lista.getFirst();

        System.out.println("Digite o número da temporada que deseja fazer review:");
        int num = entrada.nextInt();
        Temporada temporada = BuscarSerie.buscaTemporada(num, serie);

        if (temporada != null) {
            entrada.nextLine();

            System.out.println("Digite a sua review para a temporada " +
                    temporada.getTempNumero() + " de " + serie.getTitulo() + ":");
            String review = entrada.nextLine();

            temporada.setReview(review);
            Serializacao.salvarSerie(Acervo.getAcervo().getSeries());
            System.out.println("A temporada " + temporada.getTempNumero() +
                    " teve a review cadastrada com sucesso!");
        } else {
            System.out.println("Temporada não existente");
        }
    }
}
