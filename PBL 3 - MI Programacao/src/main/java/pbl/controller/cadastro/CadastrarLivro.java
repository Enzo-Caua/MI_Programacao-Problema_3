package pbl.controller.cadastro;

import pbl.model.obras.Acervo;
import pbl.model.obras.Livro;
import pbl.model.persistencia.Serializacao;

import java.util.List;

/**
 * Classe responsável por cadastrar livros no acervo.
 */
public class CadastrarLivro {

    /**
     * Recebe os dados do livro por meio de uma lista e o adiciona ao acervo.
     *
     * @param dadosLista Lista com os dados do livro na seguinte ordem:
     *                   [0] - Título
     *                   [1] - Título original
     *                   [2] - Gênero
     *                   [3] - Ano de lançamento
     *                   [4] - Autor
     *                   [5] - Editora
     *                   [6] - ISBN
     *                   [7] - Possui exemplar ("SIM" ou "NAO")
     */
    public static void dadosLivro(List<String> dadosLista) {

        boolean disponivel = false;

        System.out.println("Recebendo dados do livro...");

        String titulo = dadosLista.get(0);
        String tituloOriginal = dadosLista.get(1);
        String genero = dadosLista.get(2);
        String anoLancamento = dadosLista.get(3);
        String autor = dadosLista.get(4);
        String editora = dadosLista.get(5);
        String isbn = dadosLista.get(6);
        String posse = dadosLista.get(7).toUpperCase();

        if (posse.equals("SIM")) {
            disponivel = true;
        } else if (posse.equals("NAO") || posse.equals("NÃO")) {
            disponivel = false;
        }

        Livro livro = new Livro(titulo, tituloOriginal, genero, anoLancamento,
                autor, editora, isbn, disponivel);
        Acervo.getAcervo().addLivro(livro);
        Serializacao.salvarLivro(Acervo.getAcervo().getLivros());

        System.out.print("O livro " + titulo + " foi cadastrado com sucesso!\n");
        System.out.println(livro);

    }
}
