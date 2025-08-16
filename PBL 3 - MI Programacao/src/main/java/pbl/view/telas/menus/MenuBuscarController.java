package pbl.view.telas.menus;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import pbl.view.telas.Main;

public class MenuBuscarController {

    /**
     * Abre a tela de busca de livros.
     * @param event Evento de clique do botão
     */
    @FXML
    protected void clickLivros(ActionEvent event) {
        Main.trocarTelas("BuscarLivro");
    }

    /**
     * Abre a tela de busca de filmes.
     * @param event Evento de clique do botão
     */
    @FXML
    protected void clickFilmes(ActionEvent event) {
        Main.trocarTelas("BuscarFilme");
    }

    /**
     * Abre a tela de busca de séries.
     * @param event Evento de clique do botão
     */
    @FXML
    protected void clickSeries(ActionEvent event) {
        Main.trocarTelas("BuscarSerie");
    }

    /**
     * Volta para o menu inicial.
     * @param event Evento de clique do botão
     */
    @FXML
    protected void clickVoltar(ActionEvent event) {
        Main.trocarTelas("MenuInicial");
    }
}
