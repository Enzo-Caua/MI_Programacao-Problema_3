package pbl.model.obras;

import java.util.ArrayList;

/**
 * Classe singleton que armazena e organiza livros, filmes e séries.
 */
public class Acervo {
    /**
     * Instância única do acervo.
     */
    private static Acervo acervo;

    /**
     * Lista de livros no acervo.
     */
    private final ArrayList<Livro> livros;

    /**
     * Lista de filmes no acervo.
     */
    private final ArrayList<Filme> filmes;

    /**
     * Lista de séries no acervo.
     */
    private final ArrayList<Serie> series;

    /**
     * Construtor privado que inicializa as listas.
     */
    private Acervo() {
        this.livros = new ArrayList<>();
        this.filmes = new ArrayList<>();
        this.series = new ArrayList<>();
    }

    /**
     * Retorna a instância única do acervo.
     */
    public static Acervo getAcervo() {
        if (acervo == null) {
            acervo = new Acervo();
        }
        return acervo;
    }

    /**
     * Adiciona um livro ao acervo e ordena por avaliação.
     */
    public void addLivro(Livro livro) {
        this.livros.add(livro);
        this.livros.sort((l1, l2) -> Integer.compare(l2.getAvaliacao(), l1.getAvaliacao()));
    }

    /**
     * Adiciona um filme ao acervo e ordena por avaliação.
     */
    public void addFilme(Filme filme) {
        this.filmes.add(filme);
        this.filmes.sort((l1, l2) -> Integer.compare(l2.getAvaliacao(), l1.getAvaliacao()));
    }

    /**
     * Adiciona uma série ao acervo e ordena por avaliação.
     */
    public void addSerie(Serie serie) {
        this.series.add(serie);
        this.series.sort((l1, l2) -> Integer.compare(l2.getAvaliacao(), l1.getAvaliacao()));
    }

    /**
     * Retorna a lista de livros do acervo.
     */
    public ArrayList<Livro> getLivros() {
        return livros;
    }

    /**
     * Retorna a lista de filmes do acervo.
     */
    public ArrayList<Filme> getFilmes() {
        return filmes;
    }

    /**
     * Retorna a lista de séries do acervo.
     */
    public ArrayList<Serie> getSeries() {
        return series;
    }

    /**
     * Define a lista de livros do acervo (substitui a atual).
     */
    public void setLivros(ArrayList<Livro> novosLivros) {
        this.livros.clear();
        this.livros.addAll(novosLivros);
        this.livros.sort((l1, l2) -> Integer.compare(l2.getAvaliacao(), l1.getAvaliacao()));
    }

    /**
     * Define a lista de filmes do acervo (substitui a atual).
     */
    public void setFilmes(ArrayList<Filme> novosFilmes) {
        this.filmes.clear();
        this.filmes.addAll(novosFilmes);
        this.filmes.sort((l1, l2) -> Integer.compare(l2.getAvaliacao(), l1.getAvaliacao()));
    }

    /**
     * Define a lista de séries do acervo (substitui a atual).
     */
    public void setSeries(ArrayList<Serie> novasSeries) {
        this.series.clear();
        this.series.addAll(novasSeries);
        this.series.sort((l1, l2) -> Integer.compare(l2.getAvaliacao(), l1.getAvaliacao()));
    }

    /**
     * Atualiza a avaliação de um livro e reordena a lista.
     */
    public void atualizarAvaliacaoLivro(Livro livro, int novaAvaliacao) {
        if (this.livros.contains(livro)) {
            livro.setAvaliacao(novaAvaliacao);
            this.livros.sort((l1, l2) -> Integer.compare(l2.getAvaliacao(), l1.getAvaliacao()));
        }
    }

    /**
     * Atualiza a avaliação de um filme e reordena a lista.
     */
    public void atualizarAvaliacaoFilme(Filme filme, int novaAvaliacao) {
        if (this.filmes.contains(filme)) {
            filme.setAvaliacao(novaAvaliacao);
            this.filmes.sort((f1, f2) -> Integer.compare(f2.getAvaliacao(), f1.getAvaliacao()));
        }
    }

    /**
     * Adiciona uma avaliação a uma temporada de uma série e reordena a lista.
     */
    public void avaliarTemporadaSerie(Serie serie, int nota) {
        if (this.series.contains(serie)) {
            serie.addAvaliacaoTemporadas(nota);
            this.series.sort((s1, s2) -> Integer.compare(s2.getAvaliacao(), s1.getAvaliacao()));
        }
    }
}
