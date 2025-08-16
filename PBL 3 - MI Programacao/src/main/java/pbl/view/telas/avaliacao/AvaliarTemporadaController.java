package pbl.view.telas.avaliacao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.util.StringConverter;
import pbl.model.obras.Acervo;
import pbl.model.obras.Serie;
import pbl.model.obras.Temporada;

import java.io.IOException;

/**
 * Controlador da tela de avaliação de temporadas de séries.
 * Permite ao usuário selecionar uma série, uma temporada específica e o tipo de avaliação (pontuar ou comentar).
 * Após a avaliação, o valor é armazenado na temporada correspondente.
 */
public class AvaliarTemporadaController {

    /** Botão para confirmar a avaliação */
    @FXML private Button btAvaliar;

    /** Botão para voltar à tela anterior */
    @FXML private Button btVoltar;

    /** ComboBox para seleção do tipo de avaliação ("Pontuar" ou "Comentar") */
    @FXML private ComboBox<String> cbAvaliacao;

    /** ComboBox para seleção da série */
    @FXML private ComboBox<Serie> cbSerie;

    /** ComboBox para seleção da temporada da série */
    @FXML private ComboBox<Temporada> cbTemporada;

    /**
     * Inicializa os componentes da interface.
     * Popula as listas de séries e tipos de avaliação.
     * Configura o carregamento dinâmico das temporadas conforme a série selecionada.
     */
    @FXML
    public void initialize() {
        cbAvaliacao.setItems(FXCollections.observableArrayList("Pontuar", "Comentar"));
        ObservableList<Serie> series = FXCollections.observableArrayList(Acervo.getAcervo().getSeries());
        cbSerie.setItems(series);

        cbSerie.setConverter(new StringConverter<>() {
            @Override
            public String toString(Serie serie) {
                return (serie != null) ? serie.getTitulo() : "";
            }
            @Override
            public Serie fromString(String string) {
                return null;
            }
        });

        cbTemporada.setConverter(new StringConverter<>() {
            @Override
            public String toString(Temporada temp) {
                if (temp == null) return "";
                return "Temporada " + temp.getTempNumero() + " - " + temp.getAnoLancamento() + " - " + temp.getQntEpisodios() + " episódios";
            }
            @Override
            public Temporada fromString(String string) {
                return null;
            }
        });

        cbSerie.setOnAction(e -> {
            Serie selecionada = cbSerie.getValue();
            if (selecionada != null) {
                cbTemporada.setItems(FXCollections.observableArrayList(selecionada.getTemporadas()));
                cbTemporada.setValue(null);
            }
        });
    }

    /**
     * Método acionado ao clicar no botão "Avaliar".
     * Abre a tela de avaliação correspondente ao tipo selecionado e atualiza a temporada.
     *
     * @param event Evento de clique do botão avaliar
     */
    @FXML
    private void onAvaliar(ActionEvent event) {
        Serie serieSelecionada = cbSerie.getValue();
        Temporada temporadaSelecionada = cbTemporada.getValue();
        String tipoAvaliacao = cbAvaliacao.getValue();

        if (serieSelecionada == null || temporadaSelecionada == null || tipoAvaliacao == null) {
            mostrarAlerta("Erro", "Selecione a série, temporada e tipo de avaliação.", Alert.AlertType.ERROR);
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
            stage.setTitle(tipoAvaliacao + " Temporada");

            if (tipoAvaliacao.equals("Pontuar")) {
                PontuarController controller = loader.getController();
                controller.setTitulo(serieSelecionada.getTitulo() + " - Temporada " + temporadaSelecionada.getTempNumero());
                stage.showAndWait();

                int nota = controller.getNota();
                if (nota > 0) {
                    System.out.println("Nota da temporada: " + nota);
                    temporadaSelecionada.setAvaliacao(nota);
                }

            } else {
                ComentarController controller = loader.getController();
                controller.setTitulo(serieSelecionada.getTitulo() + " - Temporada " + temporadaSelecionada.getTempNumero());
                stage.showAndWait();

                String review = controller.getReview();
                if (review != null && !review.isEmpty()) {
                    System.out.println("Review da temporada: " + review);
                    temporadaSelecionada.setReview(review);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Fecha a janela atual ao clicar no botão "Voltar".
     *
     * @param event Evento de clique do botão voltar
     */
    @FXML
    private void onVoltar(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    /**
     * Exibe uma caixa de alerta com o título, mensagem e tipo especificados.
     *
     * @param titulo Título do alerta
     * @param msg Mensagem do alerta
     * @param tipo Tipo do alerta (erro, informação, etc)
     */
    private void mostrarAlerta(String titulo, String msg, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(msg);
        alerta.showAndWait();
    }
}
