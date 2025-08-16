package pbl.model.obras;

import java.io.Serializable;

/**
 * Classe que representa uma temporada de uma série.
 */
public class Temporada implements Serializable {

    /**
     * Ano de lançamento da temporada.
     */
    private final String anoLancamento;

    /**
     * Número da temporada.
     */
    private final int tempNumero;

    /**
     * Quantidade de episódios da temporada.
     */
    private final int qntEpisodios;

    /**
     * Avaliação da temporada.
     */
    private int avaliacao;

    /**
     * Review ou comentário sobre a temporada.
     */
    private String review;

    /**
     * Construtor da classe Temporada.
     */
    public Temporada(String anoLancamento, int qntEpisodios, int tempNumero) {
        this.anoLancamento = anoLancamento;
        this.qntEpisodios = qntEpisodios;
        this.tempNumero = tempNumero;
    }

    /**
     * Retorna o ano de lançamento da temporada.
     */
    public String getAnoLancamento() {
        return anoLancamento;
    }

    /**
     * Retorna o número da temporada.
     */
    public int getTempNumero() {
        return tempNumero;
    }

    /**
     * Retorna a quantidade de episódios da temporada.
     */
    public int getQntEpisodios() {
        return qntEpisodios;
    }

    /**
     * Retorna a avaliação da temporada.
     */
    public int getAvaliacao() {
        return avaliacao;
    }

    /**
     * Retorna a review da temporada.
     */
    public String getReview() {
        return review;
    }

    /**
     * Define a avaliação da temporada.
     */
    public void setAvaliacao(int avaliacao) {
        this.avaliacao = avaliacao;
    }

    /**
     * Define a review da temporada.
     */
    public void setReview(String review) {
        this.review = review;
    }
}
