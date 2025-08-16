package pbl.view.telas.menus;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import pbl.view.telas.Main;

public class MenuCadastrarController {

    /**
     * Abre a tela de cadastro de livros.
     * @param event Evento de clique do botão
     */
    @FXML
    protected void clickLivros(ActionEvent event) {
        Main.trocarTelas("CadastrarLivros");
    }

    /**
     * Abre a tela de cadastro de filmes.
     * @param event Evento de clique do botão
     */
    @FXML
    protected void clickFilmes(ActionEvent event) {
        Main.trocarTelas("CadastrarFilmes");
    }

    /**
     * Abre a tela de cadastro de séries.
     * @param event Evento de clique do botão
     */
    @FXML
    protected void clickSeries(ActionEvent event) {
        Main.trocarTelas("CadastrarSeries");
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
