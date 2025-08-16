package pbl.controller.atualizacao;

import pbl.controller.busca.BuscarLivro;
import pbl.model.obras.Acervo;
import pbl.model.obras.Livro;
import pbl.model.persistencia.Serializacao;

import java.util.Scanner;

/**
 * Classe responsável por atualizar informações de livros no acervo,
 * como a data de leitura e a disponibilidade para leitura.
 */
public class AtualizarLivro {

    /**
     * Atualiza a data em que um determinado livro foi lido.
     * O usuário informa o título do livro e a nova data, que será armazenada no acervo.
     *
     * @param entrada Scanner utilizado para capturar a entrada do usuário.
     */
    public static void atualizarLido(Scanner entrada) {

        System.out.print("\u001B[36m");
        entrada.nextLine();
        System.out.print("Digite o título do livro: ");
        String titulo = entrada.nextLine();

        Livro livro = BuscarLivro.buscaTitulo(titulo).getFirst();

        System.out.print("Digite a data em que " + titulo + " foi lido: ");
        String dataVisto = entrada.nextLine();
        livro.alterarVisto(dataVisto);
        Serializacao.salvarLivro(Acervo.getAcervo().getLivros());
        System.out.println("Atualização feita com sucesso!");
    }

    /**
     * Atualiza a disponibilidade de um livro no acervo.
     * O usuário é informado sobre o estado atual e pode optar por alterá-lo.
     *
     * @param entrada Scanner utilizado para capturar a entrada do usuário.
     */
    public static void atualizarDisponibilidade(Scanner entrada) {

        System.out.print("\u001B[36m");
        entrada.nextLine();
        System.out.print("Digite o título do livro: ");
        String titulo = entrada.nextLine();
        Livro livro = BuscarLivro.buscaTitulo(titulo).getFirst();
        System.out.println("O livro " + livro.getTitulo() +
                " está: " + (livro.isDisponivel() ? "Disponível" : "Indisponível"));
        System.out.print("Deseja alterar disponibilidade do livro? ");
        String resposta = entrada.nextLine();
        if (resposta.equalsIgnoreCase("Sim")) {
            livro.alterarDisponivel();
            Serializacao.salvarLivro(Acervo.getAcervo().getLivros());
            System.out.println("Atualização feita com sucesso!");
        } else {
            System.out.println("Disponibilidade não foi alterada!");
        }
    }
}
