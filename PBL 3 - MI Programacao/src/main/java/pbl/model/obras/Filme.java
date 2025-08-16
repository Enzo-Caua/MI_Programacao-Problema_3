package pbl.model.obras;

import java.util.ArrayList;

/**
 * Classe que representa um filme, estendendo a classe Midia.
 */
public class Filme extends Midia {

    /**
     * Duração do filme em minutos.
     */
    private String duracao;

    /**
     * Lista de diretores do filme.
     */
    private ArrayList<String> diretores;

    /**
     * Lista de roteiristas do filme.
     */
    private ArrayList<String> roteiristas;

    /**
     * Lista de atores do filme.
     */
    private ArrayList<String> atores;

    /**
     * Plataforma de exibição do filme (ex: Netflix, cinema).
     */
    private String plataforma;

    /**
     * Construtor da classe Filme.
     */
    public Filme(String titulo, String tituloOriginal, String genero, String anoLancamento,
                 String duracao, ArrayList<String> diretores, ArrayList<String> roteiristas,
                 ArrayList<String> atores, String plataforma) {
        super(titulo, tituloOriginal, genero, anoLancamento);
        this.diretores = diretores;
        this.roteiristas = roteiristas;
        this.atores = atores;
        this.duracao = duracao;
        this.plataforma = plataforma;
    }

    /**
     * Retorna a duração do filme.
     */
    public String getDuracao() {
        return duracao;
    }

    /**
     * Retorna a lista de diretores do filme.
     */
    public ArrayList<String> getDiretores() {
        return diretores;
    }

    /**
     * Retorna a lista de roteiristas do filme.
     */
    public ArrayList<String> getRoteiristas() {
        return roteiristas;
    }

    /**
     * Retorna a lista de atores do filme.
     */
    public ArrayList<String> getAtores() {
        return atores;
    }

    /**
     * Retorna a plataforma onde o filme foi exibido.
     */
    public String getPlataforma() {
        return plataforma;
    }

    /**
     * Define a duração da mídia audiovisual.
     *
     * @param duracao a duração no formato de texto (ex: "2h 15min")
     */
    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    /**
     * Define a lista de diretores da mídia.
     *
     * @param diretores uma lista com os nomes dos diretores
     */
    public void setDiretores(ArrayList<String> diretores) {
        this.diretores = diretores;
    }

    /**
     * Define a lista de roteiristas da mídia.
     *
     * @param roteiristas uma lista com os nomes dos roteiristas
     */
    public void setRoteiristas(ArrayList<String> roteiristas) {
        this.roteiristas = roteiristas;
    }

    /**
     * Define a lista de atores da mídia.
     *
     * @param atores uma lista com os nomes dos atores
     */
    public void setAtores(ArrayList<String> atores) {
        this.atores = atores;
    }

    /**
     * Define a plataforma de exibição da mídia.
     *
     * @param plataforma o nome da plataforma (ex: "Netflix", "Prime Video")
     */
    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

}
