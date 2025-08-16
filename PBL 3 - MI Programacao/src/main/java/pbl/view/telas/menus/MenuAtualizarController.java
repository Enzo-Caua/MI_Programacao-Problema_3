package pbl.view.telas.menus;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import pbl.view.telas.Main;

public class MenuAtualizarController {

    /**
     * Ação para trocar para a tela de atualização de livros.
     * @param event Evento de clique no botão
     */
    @FXML
    protected void clickLivros(ActionEvent event) {
        Main.trocarTelas("AtualizarLivro");
    }

    /**
     * Ação para trocar para a tela de atualização de filmes.
     * @param event Evento de clique no botão
     */
    @FXML
    protected void clickFilmes(ActionEvent event) {
        Main.trocarTelas("AtualizarFilme");
    }

    /**
     * Ação para trocar para a tela de atualização de séries.
     * @param event Evento de clique no botão
     */
    @FXML
    protected void clickSeries(ActionEvent event) {
        Main.trocarTelas("AtualizarSerie");
    }

    /**
     * Ação para voltar ao menu inicial.
     * @param event Evento de clique no botão
     */
    @FXML
    protected void clickVoltar(ActionEvent event) {
        Main.trocarTelas("MenuInicial");
    }
}
