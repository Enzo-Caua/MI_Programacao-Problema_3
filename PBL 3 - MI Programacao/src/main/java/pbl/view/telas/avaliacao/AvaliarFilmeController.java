package pbl.view.telas.avaliacao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.util.StringConverter;
import pbl.model.obras.Acervo;
import pbl.model.obras.Filme;

import java.io.IOException;

/**
 * Controlador da tela de avaliação de filmes.
 * Permite que o usuário selecione um filme e escolha entre pontuar ou comentar.
 * Ao final da avaliação, a nota ou o comentário são atribuídos ao objeto {@code Filme}.
 */
public class AvaliarFilmeController {

    /** Botão para iniciar a avaliação do filme */
    @FXML private Button btAvaliar;

    /** Botão para voltar à tela anterior */
    @FXML private Button btVoltar;

    /** ComboBox com as opções de tipo de avaliação ("Pontuar", "Comentar") */
    @FXML private ComboBox<String> cbAvaliacao;

    /** ComboBox com a lista de filmes disponíveis para avaliação */
    @FXML private ComboBox<Filme> cbFilme;

    /**
     * Inicializa os componentes da interface.
     * Popula as ComboBoxes com os filmes do acervo e os tipos de avaliação.
     * Também define como os filmes devem ser exibidos na ComboBox.
     */
    @FXML
    public void initialize() {
        cbAvaliacao.setItems(FXCollections.observableArrayList("Pontuar", "Comentar"));
        ObservableList<Filme> filmes = FXCollections.observableArrayList(Acervo.getAcervo().getFilmes());
        cbFilme.setItems(filmes);

        cbFilme.setConverter(new StringConverter<>() {
            @Override
            public String toString(Filme filme) {
                return (filme != null) ? filme.getTitulo() : "";
            }

            @Override
            public Filme fromString(String string) {
                return null; // Não necessário neste contexto
            }
        });
    }

    /**
     * Ação executada ao clicar no botão "Avaliar".
     * Abre a tela correspondente ao tipo de avaliação selecionado
     * e atualiza o objeto {@code Filme} com a nota ou comentário, se fornecidos.
     *
     * @param event Evento de clique do botão
     */
    @FXML
    private void onAvaliar(ActionEvent event) {
        Filme filmeSelecionado = cbFilme.getValue();
        String tipoAvaliacao = cbAvaliacao.getValue();

        if (filmeSelecionado == null || tipoAvaliacao == null) {
            System.out.println("Filme ou tipo de avaliação não selecionado.");
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
            stage.setTitle(tipoAvaliacao + " Filme");

            if (tipoAvaliacao.equals("Pontuar")) {
                PontuarController controller = loader.getController();
                controller.setTitulo(filmeSelecionado.getTitulo());
                stage.showAndWait();

                int nota = controller.getNota();
                if (nota > 0) {
                    System.out.println("Nota do filme '" + filmeSelecionado.getTitulo() + "': " + nota);
                    filmeSelecionado.setAvaliacao(nota);
                }

            } else {
                ComentarController controller = loader.getController();
                controller.setTitulo(filmeSelecionado.getTitulo());
                stage.showAndWait();

                String review = controller.getReview();
                if (review != null && !review.isEmpty()) {
                    System.out.println("Review do filme: " + review);
                    filmeSelecionado.setReview(review);
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
