package pbl.controller.busca;


import pbl.model.obras.Acervo;
import pbl.model.obras.Serie;
import pbl.model.obras.Temporada;

import java.util.ArrayList;

/**
 * Classe responsável por realizar buscas no acervo de séries com base em diferentes critérios.
 */
public class BuscarSerie {
    static ArrayList<Serie> lista = Acervo.getAcervo().getSeries();

    /**
     * Busca séries pelo título ou título original.
     *
     * @param titulo Título a ser buscado.
     * @return Lista de séries com o título correspondente.
     */
    public static ArrayList<Serie> buscaTitulo(String titulo) {
        ArrayList<Serie> listaResultado = new ArrayList<>();
        for (Serie serie : lista) {
            if (titulo.equals(serie.getTitulo()) || titulo.equals(serie.getTituloOriginal())) {
                listaResultado.add(serie);
            }
        }
        return listaResultado;
    }

    /**
     * Busca séries por gênero.
     *
     * @param genero Gênero a ser buscado.
     * @return Lista de séries do gênero especificado.
     */
    public static ArrayList<Serie> buscaGenero(String genero) {
        ArrayList<Serie> listaResultado = new ArrayList<>();
        for (Serie serie : lista) {
            if (genero.equals(serie.getGenero())) {
                listaResultado.add(serie);
            }
        }
        return listaResultado;
    }

    /**
     * Busca séries por ano de lançamento.
     *
     * @param anoLancamento Ano de lançamento desejado.
     * @return Lista de séries lançadas no ano especificado.
     */
    public static ArrayList<Serie> buscaAnoLancamento(String anoLancamento) {
        ArrayList<Serie> listaResultado = new ArrayList<>();
        for (Serie serie : lista) {
            if (anoLancamento.equals(serie.getAnoLancamento())) {
                listaResultado.add(serie);
            }
        }
        return listaResultado;
    }

    /**
     * Busca séries que foram vistas ou não vistas.
     *
     * @param visto Indica se a série foi vista.
     * @return Lista de séries com o status correspondente.
     */
    public static ArrayList<Serie> buscaVisto(boolean visto) {
        ArrayList<Serie> listaResultado = new ArrayList<>();
        for (Serie serie : lista) {
            if (visto == serie.isVisto()) {
                listaResultado.add(serie);
            }
        }
        return listaResultado;
    }

    /**
     * Busca séries por avaliação.
     *
     * @param avaliacao Avaliação da série.
     * @return Lista de séries com a avaliação informada.
     */
    public static ArrayList<Serie> buscaAvaliacao(int avaliacao) {
        ArrayList<Serie> listaResultado = new ArrayList<>();
        for (Serie serie : lista) {
            if (avaliacao == serie.getAvaliacao()) {
                listaResultado.add(serie);
            }
        }
        return listaResultado;
    }

    /**
     * Busca séries com um determinado ator.
     *
     * @param nomeAtor Nome do ator a ser buscado.
     * @return Lista de séries que possuem o ator no elenco.
     */
    public static ArrayList<Serie> buscaAtor(String nomeAtor) {
        ArrayList<Serie> listaResultado = new ArrayList<>();
        for (Serie serie : lista) {
            for (String ator : serie.getAtores()) {
                if (ator.equalsIgnoreCase(nomeAtor)) {
                    listaResultado.add(serie);
                    break;
                }
            }
        }
        return listaResultado;
    }

    /**
     * Busca séries dirigidas por um determinado diretor.
     *
     * @param nomeDiretor Nome do diretor.
     * @return Lista de séries com o diretor informado.
     */
    public static ArrayList<Serie> buscaDiretor(String nomeDiretor) {
        ArrayList<Serie> listaResultado = new ArrayList<>();
        for (Serie serie : lista) {
            for (String diretor : serie.getDiretores()) {
                if (diretor.equalsIgnoreCase(nomeDiretor)) {
                    listaResultado.add(serie);
                    break;
                }
            }
        }
        return listaResultado;
    }

    /**
     * Busca séries com um determinado roteirista.
     *
     * @param nomeRoteirista Nome do roteirista.
     * @return Lista de séries que possuem o roteirista informado.
     */
    public static ArrayList<Serie> buscaRoteirista(String nomeRoteirista) {
        ArrayList<Serie> listaResultado = new ArrayList<>();
        for (Serie serie : lista) {
            for (String roteirista : serie.getRoteiristas()) {
                if (roteirista.equalsIgnoreCase(nomeRoteirista)) {
                    listaResultado.add(serie);
                    break;
                }
            }
        }
        return listaResultado;
    }

    /**
     * Busca uma temporada específica de uma série.
     *
     * @param tempNumero Número da temporada (baseado em 1).
     * @param serie      Série que contém a temporada.
     * @return A temporada correspondente ao número informado.
     */
    public static Temporada buscaTemporada(int tempNumero, Serie serie) {
        ArrayList<Temporada> listaTemporadas = serie.getTemporadas();
        try {
            return listaTemporadas.get(tempNumero - 1);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }
}
