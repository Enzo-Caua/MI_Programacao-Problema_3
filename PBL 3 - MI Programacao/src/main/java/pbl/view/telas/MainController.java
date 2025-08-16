package pbl.view.telas;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 * Controlador da tela principal da aplicação.
 * Responsável por lidar com eventos da interface inicial (Main.fxml).
 */
public class MainController {

    /**
     * Método executado quando o botão "Iniciar App" é clicado.
     * Redireciona o usuário para a tela do menu inicial.
     *
     * @param event Evento de clique no botão.
     */
    @FXML
    protected void clickIniciarApp(ActionEvent event) {
        System.out.println("Iniciando App");
        Main.trocarTelas("MenuInicial");
    }
}
