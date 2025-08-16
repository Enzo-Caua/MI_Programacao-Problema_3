package pbl.model.obras;

import java.io.Serializable;

/**
 * Classe abstrata que representa uma mídia, como livros, filmes ou séries.
 * Armazena informações básicas como título, gênero, ano de lançamento,
 * status de visualização, avaliação e review.
 * <p>
 * Esta classe deve ser estendida por tipos específicos de mídia.
 */
public abstract class Midia implements Serializable {

    /**
     * Título da mídia.
     */
    protected String titulo;

    /**
     * Título original da mídia (caso seja uma adaptação ou tradução).
     */
    protected String tituloOriginal;

    /**
     * Gênero da mídia (ex: Ação, Drama, Comédia).
     */
    protected String genero;

    /**
     * Ano de lançamento da mídia.
     */
    protected String anoLancamento;

    /**
     * Indica se a mídia já foi vista.
     */
    protected boolean visto;

    /**
     * Data em que a mídia foi vista.
     */
    protected String dataVisto;

    /**
     * Avaliação dada à mídia (ex: de 0 a 10).
     */
    protected int avaliacao;

    /**
     * Comentário ou opinião pessoal sobre a mídia.
     */
    protected String review;

    /**
     * Construtor base para objetos do tipo Midia.
     *
     * @param titulo         título da mídia
     * @param tituloOriginal título original da mídia
     * @param genero         gênero da mídia
     * @param anoLancamento  ano de lançamento da mídia
     */
    public Midia(String titulo, String tituloOriginal, String genero, String anoLancamento) {
        this.titulo = titulo;
        this.tituloOriginal = tituloOriginal;
        this.genero = genero;
        this.anoLancamento = anoLancamento;
        this.dataVisto = "Sem Registro";
    }

    /**
     * @return título da mídia
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @return título original da mídia
     */
    public String getTituloOriginal() {
        return tituloOriginal;
    }

    /**
     * @return gênero da mídia
     */
    public String getGenero() {
        return genero;
    }

    /**
     * @return ano de lançamento da mídia
     */
    public String getAnoLancamento() {
        return anoLancamento;
    }

    /**
     * @return avaliação atribuída à mídia
     */
    public int getAvaliacao() {
        return avaliacao;
    }

    /**
     * Define a avaliação da mídia.
     *
     * @param avaliacao valor da avaliação (ex: de 0 a 10)
     */
    public void setAvaliacao(int avaliacao) {
        this.avaliacao = avaliacao;
    }

    /**
     * @return review da mídia
     */
    public String getReview() {
        return review;
    }

    /**
     * Define a review da mídia.
     *
     * @param review comentário pessoal sobre a mídia
     */
    public void setReview(String review) {
        this.review = review;
    }

    /**
     * Marca a mídia como vista e armazena a data de visualização.
     * Caso já tenha sido vista, apenas retorna a data registrada.
     *
     * @param dataVisto data em que foi vista
     * @return mensagem indicando se foi vista e quando
     */
    public String alterarVisto(String dataVisto) {
        boolean foiVisto = this.visto;

        if (foiVisto) {
            return this.dataVisto;
        }
        this.visto = true;
        this.dataVisto = dataVisto;
        return this.dataVisto;
    }

    /**
     * @return true se a mídia já foi vista
     */
    public boolean isVisto() {
        return visto;
    }

    /**
     * @return data em que a mídia foi vista
     */
    public String getDataVisto() {
        return this.dataVisto;
    }

    /**
     * Define o título da mídia.
     *
     * @param titulo o novo título da mídia
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Define o título original da mídia.
     *
     * @param tituloOriginal o novo título original da mídia
     */
    public void setTituloOriginal(String tituloOriginal) {
        this.tituloOriginal = tituloOriginal;
    }

    /**
     * Define o gênero da mídia.
     *
     * @param genero o novo gênero da mídia
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }

    /**
     * Define o ano de lançamento da mídia.
     *
     * @param anoLancamento o novo ano de lançamento da mídia
     */
    public void setAnoLancamento(String anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

}
