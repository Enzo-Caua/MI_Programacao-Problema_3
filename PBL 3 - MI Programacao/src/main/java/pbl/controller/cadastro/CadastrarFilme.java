package pbl.controller.cadastro;

import pbl.model.obras.Acervo;
import pbl.model.obras.Filme;
import pbl.model.persistencia.Serializacao;

import java.util.ArrayList;
import java.util.List;

public class CadastrarFilme {

    public static void dadosFilme(
            List<String> dadosFilme,
            List<String> diretores,
            List<String> roteiristas,
            List<String> atores
    ) {

        System.out.println("Recebendo dados do filme...");

        String titulo = dadosFilme.get(0);
        String tituloOriginal = dadosFilme.get(1);
        String genero = dadosFilme.get(2);
        String anoLancamento = dadosFilme.get(3);
        String plataforma = dadosFilme.get(4);
        String duracao = dadosFilme.get(5);

        Filme filme = new Filme(
                titulo, tituloOriginal, genero, anoLancamento,
                duracao,
                new ArrayList<>(diretores),
                new ArrayList<>(roteiristas),
                new ArrayList<>(atores),
                plataforma
        );

        Acervo acervo = Acervo.getAcervo();
        acervo.addFilme(filme);
        Serializacao.salvarFilme(acervo.getFilmes());

        System.out.println("O filme " + titulo + " foi cadastrado com sucesso!\n");
        System.out.println(filme);
    }
}
