package pbl.model.persistencia;

import pbl.model.obras.Acervo;
import pbl.model.obras.Filme;
import pbl.model.obras.Livro;
import pbl.model.obras.Serie;

import java.util.ArrayList;

/**
 * Classe utilitária responsável por persistir e recuperar os dados do acervo.
 * Utiliza os métodos de serialização e desserialização para armazenar e
 * restaurar as listas de livros, filmes e séries.
 */
public class PersistirAcervo {

    /**
     * Serializa o estado atual do acervo.
     * <p>
     * Obtém as listas de livros, filmes e séries da instância singleton de {@link Acervo}
     * e as salva em arquivos por meio da classe {@link Serializacao}.
     * </p>
     */
    public static void serializarAcervo() {
        ArrayList<Livro> livros = Acervo.getAcervo().getLivros();
        ArrayList<Filme> filmes = Acervo.getAcervo().getFilmes();
        ArrayList<Serie> series = Acervo.getAcervo().getSeries();

        Serializacao.salvarLivro(livros);
        Serializacao.salvarFilme(filmes);
        Serializacao.salvarSerie(series);
    }

    /**
     * Desserializa os dados previamente salvos e os carrega no acervo.
     * <p>
     * Lê os arquivos contendo os livros, filmes e séries usando a classe {@link Desserializacao},
     * e atualiza o conteúdo do singleton {@link Acervo} com os dados lidos.
     * </p>
     */
    public static void deserializarAcervo() {
        ArrayList<Livro> livros = Desserializacao.lerLivros();
        ArrayList<Filme> filmes = Desserializacao.lerFilmes();
        ArrayList<Serie> series = Desserializacao.lerSeries();

        Acervo.getAcervo().setLivros(livros);
        Acervo.getAcervo().setFilmes(filmes);
        Acervo.getAcervo().setSeries(series);
    }
}
