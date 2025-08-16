package pbl.model.obras;

import java.util.ArrayList;

/**
 * Classe que representa uma série, estendendo a classe Midia.
 */
public class Serie extends Midia {

    /**
     * Ano de término da série ou indicação de que ainda está em exibição.
     */
    private String anoTermino = "Série em Exibição";

    /**
     * Lista de diretores da série.
     */
    private ArrayList<String> diretores;

    /**
     * Lista de roteiristas da série.
     */
    private ArrayList<String> roteiristas;

    /**
     * Lista de atores da série.
     */
    private ArrayList<String> atores;

    /**
     * Plataforma onde a série é exibida.
     */
    private String plataforma;

    /**
     * Quantidade de temporadas da série.
     */
    private int qntTemporadas;

    /**
     * Lista de objetos Temporada da série.
     */
    private ArrayList<Temporada> temporadas;

    /**
     * Soma das avaliações das temporadas, usada para cálculo da média.
     */
    private int avaliacaoTemporadas;

    /**
     * Construtor da classe Serie.
     */
    public Serie(String titulo, String tituloOriginal, String genero, String anoLancamento,
                 ArrayList<String> diretores, ArrayList<String> roteiristas,
                 ArrayList<String> atores, String plataforma) {
        super(titulo, tituloOriginal, genero, anoLancamento);
        this.diretores = diretores;
        this.roteiristas = roteiristas;
        this.atores = atores;
        this.plataforma = plataforma;
        this.qntTemporadas = 0;
        this.temporadas = new ArrayList<>();
        this.avaliacaoTemporadas = 0;
    }

    /**
     * Retorna a lista de diretores da série.
     */
    public ArrayList<String> getDiretores() {
        return diretores;
    }

    /**
     * Retorna a lista de roteiristas da série.
     */
    public ArrayList<String> getRoteiristas() {
        return roteiristas;
    }

    /**
     * Retorna a lista de atores da série.
     */
    public ArrayList<String> getAtores() {
        return atores;
    }

    /**
     * Retorna a plataforma da série.
     */
    public String getPlataforma() {
        return plataforma;
    }

    /**
     * Retorna o ano de término da série.
     */
    public String getAnoTermino() {
        return anoTermino;
    }

    /**
     * Define o ano de término da série.
     */
    public void setAnoTermino(String anoTermino) {
        this.anoTermino = anoTermino;
    }

    /**
     * Retorna a quantidade de temporadas da série.
     */
    public int getQntTemporadas() {
        return qntTemporadas;
    }

    /**
     * Adiciona uma temporada à série.
     */
    public void addTemporadas(Temporada temporada) {
        this.qntTemporadas++;
        this.temporadas.add(temporada);
    }

    /**
     * Retorna a lista de temporadas da série.
     */
    public ArrayList<Temporada> getTemporadas() {
        return temporadas;
    }

    /**
     * Soma a avaliação de uma temporada à pontuação total.
     */
    public void addAvaliacaoTemporadas(int avaliacaoTemporada) {
        this.avaliacaoTemporadas += avaliacaoTemporada;
    }

    /**
     * @return avaliação atribuída à mídia
     */
    @Override
    public int getAvaliacao() {
        if (this.qntTemporadas > 0) {
            avaliacao = (avaliacaoTemporadas / qntTemporadas);
        } else avaliacao = 0;
        return avaliacao;
    }

    /**
     * Define a quantidade de temporadas da série.
     *
     * @param qntTemporadas o número total de temporadas
     */
    public void setQntTemporadas(int qntTemporadas) {
        this.qntTemporadas = qntTemporadas;
    }

    /**
     * Define a plataforma onde a série está disponível.
     *
     * @param plataforma o nome da plataforma (ex: "Netflix", "HBO Max")
     */
    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    /**
     * Define a lista de atores da série.
     *
     * @param atores uma lista com os nomes dos atores
     */
    public void setAtores(ArrayList<String> atores) {
        this.atores = atores;
    }

    /**
     * Define a lista de roteiristas da série.
     *
     * @param roteiristas uma lista com os nomes dos roteiristas
     */
    public void setRoteiristas(ArrayList<String> roteiristas) {
        this.roteiristas = roteiristas;
    }

    /**
     * Define a lista de diretores da série.
     *
     * @param diretores uma lista com os nomes dos diretores
     */
    public void setDiretores(ArrayList<String> diretores) {
        this.diretores = diretores;
    }

}
