package pbl.view.telas.avaliacao;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;

/**
 * Controlador para a tela de comentário (review) de um item (filme, livro, temporada, etc).
 * Permite ao usuário inserir um comentário que será retornado para a tela chamadora.
 */
public class ComentarController {

    /** Campo de texto para exibir o título do item avaliado (não editável pelo usuário). */
    @FXML
    private TextField txtTitulo;

    /** Área de texto para o usuário escrever sua review/comentário. */
    @FXML
    private TextArea txtaReview;

    /** Armazena o comentário escrito pelo usuário para ser retornado à tela anterior. */
    private String review;

    /**
     * Define o título do item avaliado na interface, normalmente chamado pela tela anterior.
     * @param titulo Título do item (filme, livro, temporada etc)
     */
    public void setTitulo(String titulo) {
        txtTitulo.setText(titulo);
    }

    /**
     * Retorna o comentário/review digitado pelo usuário.
     * @return String com a review; pode ser null se nenhum comentário foi enviado.
     */
    public String getReview() {
        return review;
    }

    /**
     * Evento acionado ao confirmar o comentário.
     * Salva o texto do comentário e fecha a janela.
     * @param event Evento de clique do botão comentar
     */
    @FXML
    private void onComentar(ActionEvent event) {
        review = txtaReview.getText();
        fechar(event);
    }

    /**
     * Evento acionado ao cancelar a ação (voltar).
     * Define o comentário como null e fecha a janela.
     * @param event Evento de clique do botão voltar
     */
    @FXML
    private void onVoltar(ActionEvent event) {
        review = null; // Nenhum comentário enviado
        fechar(event);
    }

    /**
     * Fecha a janela atual.
     * @param event Evento que dispara o fechamento
     */
    private void fechar(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
