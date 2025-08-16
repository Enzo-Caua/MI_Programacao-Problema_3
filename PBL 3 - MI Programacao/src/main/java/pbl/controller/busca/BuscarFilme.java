package pbl.controller.busca;


import pbl.model.obras.Acervo;
import pbl.model.obras.Filme;

import java.util.ArrayList;

/**
 * Classe responsável por realizar buscas no acervo de filmes com base em diferentes critérios.
 */
public class BuscarFilme {

    static ArrayList<Filme> lista = Acervo.getAcervo().getFilmes();

    /**
     * Busca filmes pelo título ou título original.
     *
     * @param titulo Título a ser buscado.
     * @return Lista de filmes que correspondem ao título fornecido.
     */
    public static ArrayList<Filme> buscaTitulo(String titulo) {
        ArrayList<Filme> listaResultado = new ArrayList<>();
        for (Filme filme : lista) {
            if (titulo.equals(filme.getTitulo()) || titulo.equals(filme.getTituloOriginal())) {
                listaResultado.add(filme);
            }
        }
        return listaResultado;
    }

    /**
     * Busca filmes por gênero.
     *
     * @param genero Gênero dos filmes a serem buscados.
     * @return Lista de filmes que pertencem ao gênero informado.
     */
    public static ArrayList<Filme> buscaGenero(String genero) {
        ArrayList<Filme> listaResultado = new ArrayList<>();
        for (Filme filme : lista) {
            if (genero.equals(filme.getGenero())) {
                listaResultado.add(filme);
            }
        }
        return listaResultado;
    }

    /**
     * Busca filmes por ano de lançamento.
     *
     * @param anoLancamento Ano de lançamento desejado.
     * @return Lista de filmes lançados no ano informado.
     */
    public static ArrayList<Filme> buscaAnoLancamento(String anoLancamento) {
        ArrayList<Filme> listaResultado = new ArrayList<>();
        for (Filme filme : lista) {
            if (anoLancamento.equals(filme.getAnoLancamento())) {
                listaResultado.add(filme);
            }
        }
        return listaResultado;
    }

    /**
     * Busca filmes com base na informação se foram vistos ou não.
     *
     * @param visto Valor booleano indicando se o filme foi visto.
     * @return Lista de filmes com o mesmo estado de visualização.
     */
    public static ArrayList<Filme> buscaVisto(boolean visto) {
        ArrayList<Filme> listaResultado = new ArrayList<>();
        for (Filme filme : lista) {
            if (visto == filme.isVisto()) {
                listaResultado.add(filme);
            }
        }
        return listaResultado;
    }

    /**
     * Busca filmes por avaliação (número de estrelas).
     *
     * @param avaliacao Avaliação desejada.
     * @return Lista de filmes com a avaliação especificada.
     */
    public static ArrayList<Filme> buscaAvaliacao(int avaliacao) {
        ArrayList<Filme> listaResultado = new ArrayList<>();
        for (Filme filme : lista) {
            if (avaliacao == filme.getAvaliacao()) {
                listaResultado.add(filme);
            }
        }
        return listaResultado;
    }

    /**
     * Busca filmes em que um determinado ator participa.
     *
     * @param nomeAtor Nome do ator a ser buscado.
     * @return Lista de filmes em que o ator está presente.
     */
    public static ArrayList<Filme> buscaAtor(String nomeAtor) {
        ArrayList<Filme> listaResultado = new ArrayList<>();
        for (Filme filme : lista) {
            for (String ator : filme.getAtores()) {
                if (ator.equalsIgnoreCase(nomeAtor)) {
                    listaResultado.add(filme);
                    break;
                }
            }
        }
        return listaResultado;
    }

    /**
     * Busca filmes dirigidos por um diretor específico.
     *
     * @param nomeDiretor Nome do diretor a ser buscado.
     * @return Lista de filmes dirigidos pelo diretor especificado.
     */
    public static ArrayList<Filme> buscaDiretor(String nomeDiretor) {
        ArrayList<Filme> listaResultado = new ArrayList<>();
        for (Filme filme : lista) {
            for (String diretor : filme.getDiretores()) {
                if (diretor.equalsIgnoreCase(nomeDiretor)) {
                    listaResultado.add(filme);
                    break;
                }
            }
        }
        return listaResultado;
    }

    /**
     * Busca filmes escritos por um roteirista específico.
     *
     * @param nomeRoteirista Nome do roteirista a ser buscado.
     * @return Lista de filmes com o roteirista informado.
     */
    public static ArrayList<Filme> buscaRoteirista(String nomeRoteirista) {
        ArrayList<Filme> listaResultado = new ArrayList<>();
        for (Filme filme : lista) {
            for (String roteirista : filme.getRoteiristas()) {
                if (roteirista.equalsIgnoreCase(nomeRoteirista)) {
                    listaResultado.add(filme);
                    break;
                }
            }
        }
        return listaResultado;
    }
}
