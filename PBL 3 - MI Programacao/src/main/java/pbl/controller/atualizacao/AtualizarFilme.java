package pbl.controller.atualizacao;

import pbl.controller.busca.BuscarFilme;
import pbl.model.obras.Acervo;
import pbl.model.obras.Filme;
import pbl.model.persistencia.Serializacao;

import java.util.Scanner;

/**
 * Classe responsável por atualizar a informação de data em que um filme foi visto.
 * Permite ao usuário informar o título do filme e registrar a data em que ele foi assistido.
 */
public class AtualizarFilme {

    /**
     * Atualiza a data em que um determinado filme foi visto.
     * O usuário informa o título do filme e a nova data, que será armazenada no acervo.
     *
     * @param entrada Scanner utilizado para capturar a entrada do usuário.
     */
    public static void atualizarVisto(Scanner entrada) {

        System.out.print("\u001B[36m");
        entrada.nextLine();
        System.out.print("Digite o título do filme: ");
        String titulo = entrada.nextLine();
        Filme filme = BuscarFilme.buscaTitulo(titulo).getFirst();
        System.out.print("Digite a data em que " + titulo + " foi visto: ");
        String dataVisto = entrada.nextLine();
        filme.alterarVisto(dataVisto);
        Serializacao.salvarFilme(Acervo.getAcervo().getFilmes());
        System.out.println("Atualização feita com sucesso!");
    }

}
