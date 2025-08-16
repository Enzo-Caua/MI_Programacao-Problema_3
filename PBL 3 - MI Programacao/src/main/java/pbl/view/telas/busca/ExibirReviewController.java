package pbl.view.telas.busca;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ExibirReviewController {

    /** Botão para fechar a janela. */
    @FXML
    private Button btVoltar;

    /** Campo de texto que exibe o título da mídia. */
    @FXML
    private TextField txtTitulo;

    /** Área de texto que exibe o conteúdo da review. */
    @FXML
    private TextArea txtaReview;

    /**
     * Define os dados da review exibida na tela.
     *
     * @param titulo Título da mídia
     * @param review Texto da review
     */
    public void setDados(String titulo, String review) {
        txtTitulo.setText(titulo);
        txtaReview.setText(review);
    }

    /**
     * Fecha a janela ao clicar no botão voltar.
     *
     * @param event Evento de clique no botão
     */
    @FXML
    void clickVoltar(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
