package pbl.model.obras;

/**
 * Classe que representa um livro, estendendo a classe Midia.
 */
public class Livro extends Midia {

    /**
     * Nome do autor do livro.
     */
    private String autor;

    /**
     * Nome da editora do livro.
     */
    private String editora;

    /**
     * Código ISBN do livro.
     */
    private String isbn;

    /**
     * Indica se o livro está disponível fisicamente.
     */
    private boolean disponivel;

    /**
     * Construtor da classe Livro.
     */
    public Livro(String titulo, String tituloOriginal, String genero, String anoLancamento,
                 String autor, String editora, String isbn, boolean disponivel) {
        super(titulo, tituloOriginal, genero, anoLancamento);
        this.autor = autor;
        this.editora = editora;
        this.isbn = isbn;
        this.disponivel = disponivel;
    }

    /**
     * Retorna o nome do autor do livro.
     */
    public String getAutor() {
        return autor;
    }

    /**
     * Retorna o nome da editora do livro.
     */
    public String getEditora() {
        return editora;
    }

    /**
     * Retorna o código ISBN do livro.
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Retorna true se o livro está disponível.
     */
    public boolean isDisponivel() {
        return disponivel;
    }

    /**
     * Alterna o estado de disponibilidade do livro.
     */
    public void alterarDisponivel() {
        this.disponivel = !this.disponivel;
    }

    /**
     * Define o autor do livro.
     *
     * @param autor o nome do autor
     */
    public void setAutor(String autor) {
        this.autor = autor;
    }

    /**
     * Define a editora do livro.
     *
     * @param editora o nome da editora
     */
    public void setEditora(String editora) {
        this.editora = editora;
    }

    /**
     * Define o ISBN do livro.
     *
     * @param isbn o código ISBN do livro
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * Define a disponibilidade do livro.
     *
     * @param disponivel true se o livro estiver disponível, false caso contrário
     */
    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

}
