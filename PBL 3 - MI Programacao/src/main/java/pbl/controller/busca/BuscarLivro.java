package pbl.controller.busca;


import pbl.model.obras.Acervo;
import pbl.model.obras.Livro;

import java.util.ArrayList;

/**
 * Classe responsável por realizar buscas no acervo de livros com base em diferentes critérios.
 */
public class BuscarLivro {

    static ArrayList<Livro> lista = Acervo.getAcervo().getLivros();

    /**
     * Busca livros cujo título ou título original contenham o texto informado (ignorando maiúsculas/minúsculas).
     *
     * @param titulo Parte ou todo do título a ser buscado.
     * @return Lista de livros que correspondem ao título fornecido.
     */
    public static ArrayList<Livro> buscaTitulo(String titulo) {
        ArrayList<Livro> listaResultado = new ArrayList<>();
        if (titulo == null || lista == null) return listaResultado;

        String tituloBusca = titulo.toLowerCase();
        for (Livro livro : lista) {
            if (livro.getTitulo() != null && livro.getTitulo().toLowerCase().contains(tituloBusca)) {
                listaResultado.add(livro);
            } else if (livro.getTituloOriginal() != null && livro.getTituloOriginal().toLowerCase().contains(tituloBusca)) {
                listaResultado.add(livro);
            }
        }
        return listaResultado;
    }


    /**
     * Busca livros pelo título ou título original.
     *
     * @param titulo Título a ser buscado.
     * @return Lista de livros que correspondem ao título fornecido.

    public static ArrayList<Livro> buscaTitulo(String titulo) {
    ArrayList<Livro> listaResultado = new ArrayList<>();
    for (Livro livro : lista) {
    if (titulo.equals(livro.getTitulo()) || titulo.equals(livro.getTituloOriginal())) {
    listaResultado.add(livro);
    }
    }
    return listaResultado;
    }
     */

    /**
     * Busca livros por gênero.
     *
     * @param genero Gênero dos livros a serem buscados.
     * @return Lista de livros que pertencem ao gênero informado.
     */
    public static ArrayList<Livro> buscaGenero(String genero) {
        ArrayList<Livro> listaResultado = new ArrayList<>();
        for (Livro livro : lista) {
            if (genero.equals(livro.getGenero())) {
                listaResultado.add(livro);
            }
        }
        return listaResultado;
    }

    /**
     * Busca livros por ano de lançamento.
     *
     * @param anoLancamento Ano de lançamento desejado.
     * @return Lista de livros lançados no ano informado.
     */
    public static ArrayList<Livro> buscaAnoLancamento(String anoLancamento) {
        ArrayList<Livro> listaResultado = new ArrayList<>();
        for (Livro livro : lista) {
            if (anoLancamento.equals(livro.getAnoLancamento())) {
                listaResultado.add(livro);
            }
        }
        return listaResultado;
    }

    /**
     * Busca livros com base na informação se foram lidos (visto) ou não.
     *
     * @param visto Valor booleano indicando se o livro foi lido.
     * @return Lista de livros com o mesmo estado de leitura.
     */
    public static ArrayList<Livro> buscaVisto(boolean visto) {
        ArrayList<Livro> listaResultado = new ArrayList<>();
        for (Livro livro : lista) {
            if (visto == livro.isVisto()) {
                listaResultado.add(livro);
            }
        }
        return listaResultado;
    }

    /**
     * Busca livros por avaliação (número de estrelas).
     *
     * @param avaliacao Avaliação desejada.
     * @return Lista de livros com a avaliação especificada.
     */
    public static ArrayList<Livro> buscaAvaliacao(int avaliacao) {
        ArrayList<Livro> listaResultado = new ArrayList<>();
        for (Livro livro : lista) {
            if (avaliacao == livro.getAvaliacao()) {
                listaResultado.add(livro);
            }
        }
        return listaResultado;
    }

    /**
     * Busca livros por nome do autor.
     *
     * @param autor Nome do autor a ser buscado.
     * @return Lista de livros escritos pelo autor informado.
     */
    public static ArrayList<Livro> buscaAutor(String autor) {
        ArrayList<Livro> listaResultado = new ArrayList<>();
        for (Livro livro : lista) {
            if (autor.equals(livro.getAutor())) {
                listaResultado.add(livro);
            }
        }
        return listaResultado;
    }

    /**
     * Busca livros por código ISBN.
     *
     * @param isbn Código ISBN a ser buscado.
     * @return Lista de livros com o ISBN especificado.
     */
    public static ArrayList<Livro> buscaISBN(String isbn) {
        ArrayList<Livro> listaResultado = new ArrayList<>();
        for (Livro livro : lista) {
            if (isbn.equals(livro.getIsbn())) {
                listaResultado.add(livro);
            }
        }
        return listaResultado;
    }
}
