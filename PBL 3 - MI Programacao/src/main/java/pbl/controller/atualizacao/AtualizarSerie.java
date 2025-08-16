package pbl.controller.atualizacao;

import pbl.controller.busca.BuscarSerie;
import pbl.controller.cadastro.CadastrarTemporada;
import pbl.model.obras.Acervo;
import pbl.model.obras.Serie;
import pbl.model.persistencia.Serializacao;

import java.util.Scanner;

public class AtualizarSerie {

    public static void atualizarVisto(Scanner entrada) {
        System.out.print("\u001B[36m");
        entrada.nextLine();
        System.out.print("Digite o título da série: ");
        String titulo = entrada.nextLine();
        Serie serie = BuscarSerie.buscaTitulo(titulo).getFirst();
        System.out.print("Digite a data em que " + titulo + " foi vista: ");
        String dataVisto = entrada.nextLine();
        serie.alterarVisto(dataVisto);
        Serializacao.salvarSerie(Acervo.getAcervo().getSeries());
        System.out.println("Atualização feita com sucesso!");
    }

    public static void atualizarTemporada(Scanner entrada) {
        System.out.print("\u001B[36m");
        entrada.nextLine();
        System.out.print("Digite o título da série: ");
        String titulo = entrada.nextLine();
        Serie serie = BuscarSerie.buscaTitulo(titulo).getFirst();
        System.out.print("Digite o número de temporadas a serem cadastradas: ");
        int num = entrada.nextInt();
        for (int i = 1; i <= num; i++) {
            CadastrarTemporada.dadosTemporada(serie, entrada);
        }
        Serializacao.salvarSerie(Acervo.getAcervo().getSeries());
        System.out.println("Atualização feita com sucesso!");
    }

    public static void atualizarTermino(Scanner entrada) {
        System.out.print("\u001B[36m");
        entrada.nextLine();
        System.out.print("Digite o título da série: ");
        String titulo = entrada.nextLine();
        Serie serie = BuscarSerie.buscaTitulo(titulo).getFirst();
        System.out.print("Digite o ano de termino da série: ");
        String ano = entrada.nextLine();

        serie.setAnoTermino(ano);

        Serializacao.salvarSerie(Acervo.getAcervo().getSeries());
        System.out.println("Atualização feita com sucesso!");
    }
}
