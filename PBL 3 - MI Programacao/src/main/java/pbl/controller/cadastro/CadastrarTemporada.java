package pbl.controller.cadastro;


import pbl.model.obras.Serie;
import pbl.model.obras.Temporada;

import java.util.Scanner;

/**
 * Classe responsável pelo cadastro de temporadas para uma série existente.
 */
public class CadastrarTemporada {

    /**
     * Solicita ao usuário os dados da nova temporada e a adiciona à série.
     *
     * @param serie   Série à qual a nova temporada será adicionada.
     * @param entrada Scanner para entrada dos dados do usuário.
     */
    public static void dadosTemporada(Serie serie, Scanner entrada) {

        entrada.nextLine();

        System.out.print("Ano de lançamento da temporada " + (serie.getQntTemporadas() + 1) + ": ");
        String anoLancamento = entrada.nextLine();

        System.out.print("Quantidade de episódios da temporada " + (serie.getQntTemporadas() + 1) + ": ");
        int qntEpisodios = entrada.nextInt();

        Temporada temporada = new Temporada(anoLancamento, qntEpisodios, serie.getQntTemporadas() + 1);
        serie.addTemporadas(temporada);
    }
}
