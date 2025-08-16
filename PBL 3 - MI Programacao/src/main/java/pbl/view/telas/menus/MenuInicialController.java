package pbl.view.telas.menus;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import pbl.view.telas.Main;

public class MenuInicialController {

    /**
     * Encerra a aplicação.
     * @param event Evento de clique do botão
     */
    @FXML
    protected void clickEncerrar(ActionEvent event) {
        Platform.exit();
    }

    /**
     * Abre o menu de cadastro.
     * @param event Evento de clique do botão
     */
    @FXML
    protected void clickMenuCadastrar(ActionEvent event) {
        Main.trocarTelas("MenuCadastrar");
    }

    /**
     * Abre o menu de atualização.
     * @param event Evento de clique do botão
     */
    @FXML
    protected void clickMenuAtualizar(ActionEvent event) {
        Main.trocarTelas("MenuAtualizar");
    }

    /**
     * Abre o menu de avaliação.
     * @param event Evento de clique do botão
     */
    @FXML
    protected void clickMenuAvaliar(ActionEvent event) {
        Main.trocarTelas("MenuAvaliar");
    }

    /**
     * Abre o menu de busca.
     * @param event Evento de clique do botão
     */
    @FXML
    protected void clickMenuBuscar(ActionEvent event) {
        Main.trocarTelas("MenuBuscar");
    }

    /**
     * Abre o menu de listagem.
     * @param event Evento de clique do botão
     */
    @FXML
    protected void clickMenuListar(ActionEvent event) {
        Main.trocarTelas("MenuListar");
    }

}
