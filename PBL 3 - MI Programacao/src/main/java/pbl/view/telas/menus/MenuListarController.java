package pbl.view.telas.menus;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import pbl.view.telas.Main;

public class MenuListarController {

    /**
     * Abre a tela de listagem de livros.
     * @param event Evento de clique do botão
     */
    @FXML
    protected void clickLivros(ActionEvent event) {
        Main.trocarTelas("ListarLivros");
    }

    /**
     * Abre a tela de listagem de filmes.
     * @param event Evento de clique do botão
     */
    @FXML
    protected void clickFilmes(ActionEvent event) {
        Main.trocarTelas("ListarFilmes");
    }

    /**
     * Abre a tela de listagem de séries.
     * @param event Evento de clique do botão
     */
    @FXML
    protected void clickSeries(ActionEvent event) {
        Main.trocarTelas("ListarSeries");
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
