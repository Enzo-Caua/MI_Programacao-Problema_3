package pbl.controller.cadastro;

import pbl.model.obras.Acervo;
import pbl.model.obras.Serie;
import pbl.model.obras.Temporada;
import pbl.model.persistencia.Serializacao;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por cadastrar séries no acervo.
 */
public class CadastrarSerie {

    /**
     * Recebe os dados da série por meio de listas e adiciona ao acervo.
     *
     * @param dadosSerie   Lista com os seguintes dados:
     *                     [0] - Título
     *                     [1] - Título original
     *                     [2] - Gênero
     *                     [3] - Ano de lançamento
     *                     [4] - Plataforma
     * @param diretores    Lista de nomes dos diretores
     * @param roteiristas  Lista de nomes dos roteiristas
     * @param atores       Lista de nomes dos atores
     * @param qtdTemporadas   Quantidade de temporadas
     * @param listaTemporadas Lista de Temporadas
     */
    public static void dadosSerie(List<String> dadosSerie,
                                  ArrayList<String> diretores,
                                  ArrayList<String> roteiristas,
                                  ArrayList<String> atores,
                                  int qtdTemporadas,
                                  ArrayList<Temporada> listaTemporadas) {

        System.out.println("Recebendo dados da série...");

        String titulo = dadosSerie.get(0);
        String tituloOriginal = dadosSerie.get(1);
        String genero = dadosSerie.get(2);
        String anoLancamento = dadosSerie.get(3);
        String plataforma = dadosSerie.get(4);

        Serie serie = new Serie(titulo, tituloOriginal, genero, anoLancamento,
                diretores, roteiristas, atores, plataforma);

        for (Temporada temp : listaTemporadas) {
            serie.addTemporadas(temp);
        }

        Acervo acervo = Acervo.getAcervo();
        acervo.addSerie(serie);

        Serializacao.salvarSerie(acervo.getSeries());

        System.out.println("A série " + titulo + " foi cadastrada com sucesso!\n");
        System.out.print(serie);
    }
}
