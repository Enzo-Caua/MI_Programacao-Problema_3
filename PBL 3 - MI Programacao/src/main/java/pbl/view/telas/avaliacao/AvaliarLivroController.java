package pbl.view.telas.avaliacao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Node;
import pbl.model.obras.Acervo;
import pbl.model.obras.Livro;

import java.io.IOException;

/**
 * Controlador da tela de avaliação de livros.
 * Permite que o usuário selecione um livro e escolha entre pontuar ou comentar.
 * A avaliação é armazenada no objeto {@link Livro} correspondente.
 */
public class AvaliarLivroController {

    /** ComboBox com os tipos de avaliação disponíveis ("Pontuar" ou "Comentar"). */
    @FXML private ComboBox<String> cbAvaliacao;

    /** ComboBox com a lista de livros disponíveis para avaliação. */
    @FXML private ComboBox<Livro> cbLivro;

    /**
     * Inicializa os componentes da interface.
     * Preenche os ComboBoxes com os livros do acervo e os tipos de avaliação.
     */
    @FXML
    public void initialize() {
        cbAvaliacao.setItems(FXCollections.observableArrayList("Pontuar", "Comentar"));

        ObservableList<Livro> livros = FXCollections.observableArrayList(Acervo.getAcervo().getLivros());
        cbLivro.setItems(livros);

        cbLivro.setConverter(new javafx.util.StringConverter<>() {
            @Override
            public String toString(Livro livro) {
                return (livro != null) ? livro.getTitulo() : "";
            }

            @Override
            public Livro fromString(String string) {
                return null; // Conversão reversa não utilizada
            }
        });
    }

    /**
     * Abre a tela de avaliação com base nas escolhas do usuário.
     * Atualiza o livro com a nota ou o comentário informado.
     *
     * @param event Evento de clique do botão de avaliação
     */
    @FXML
    private void onAvaliar(ActionEvent event) {
        Livro livroSelecionado = cbLivro.getValue();
        String tipoAvaliacao = cbAvaliacao.getValue();

        if (livroSelecionado == null || tipoAvaliacao == null) {
            System.out.println("Livro ou tipo de avaliação não selecionado.");
            return;
        }

        try {
            String fxml = tipoAvaliacao.equals("Pontuar")
                    ? "/pbl/view/telas/avaliacao/Pontuar.fxml"
                    : "/pbl/view/telas/avaliacao/Comentar.fxml";

            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            Scene scene = new Scene(loader.load(), 660, 415);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle(tipoAvaliacao + " Livro");

            if (tipoAvaliacao.equals("Pontuar")) {
                PontuarController controller = loader.getController();
                controller.setTitulo(livroSelecionado.getTitulo());
                stage.showAndWait();

                int nota = controller.getNota();
                if (nota > 0) {
                    System.out.println("Nota do livro '" + livroSelecionado.getTitulo() + "': " + nota);
                    livroSelecionado.setAvaliacao(nota);
                }
            } else {
                ComentarController controller = loader.getController();
                controller.setTitulo(livroSelecionado.getTitulo());
                stage.showAndWait();

                String review = controller.getReview();
                if (review != null && !review.isEmpty()) {
                    System.out.println("Review: " + review);
                    livroSelecionado.setReview(review);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Fecha a janela atual ao clicar no botão "Voltar".
     *
     * @param event Evento de clique do botão
     */
    @FXML
    private void onVoltar(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
