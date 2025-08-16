package pbl.view.telas.menus;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.stage.Window;
import pbl.view.telas.Main;

import java.io.IOException;

public class MenuAvaliarController {

    /**
     * Abre a janela modal para avaliação de livros.
     * @param event Evento de clique do botão
     */
    @FXML
    protected void clickLivros(ActionEvent event) {
        abrirJanelaModal("AvaliarLivro", event);
    }

    /**
     * Abre a janela modal para avaliação de filmes.
     * @param event Evento de clique do botão
     */
    @FXML
    protected void clickFilmes(ActionEvent event) {
        abrirJanelaModal("AvaliarFilme", event);
    }

    /**
     * Abre a janela modal para avaliação de temporadas de séries.
     * @param event Evento de clique do botão
     */
    @FXML
    protected void clickSeries(ActionEvent event) {
        abrirJanelaModal("AvaliarTemporada", event);
    }

    /**
     * Volta para o menu inicial.
     * @param event Evento de clique do botão
     */
    @FXML
    protected void clickVoltar(ActionEvent event) {
        Main.trocarTelas("MenuInicial");
    }

    /**
     * Método privado que abre uma janela modal com a FXML especificada.
     * @param nomeFXML Nome do arquivo FXML (sem extensão)
     * @param event Evento para obter a janela pai
     */
    private void abrirJanelaModal(String nomeFXML, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pbl/view/telas/avaliacao/" + nomeFXML + ".fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle(nomeFXML);
            stage.setScene(new Scene(root, 660, 415));
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/pbl/view/img/IconeLogo.png")));

            // Configura a janela como modal e define a janela pai
            stage.initModality(Modality.WINDOW_MODAL);
            Window janelaPai = ((Node) event.getSource()).getScene().getWindow();
            stage.initOwner(janelaPai);

            stage.showAndWait(); // Aguarda o fechamento da janela
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
