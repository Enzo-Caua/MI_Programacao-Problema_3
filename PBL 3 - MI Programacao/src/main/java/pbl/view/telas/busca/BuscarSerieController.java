package pbl.view.telas.busca;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pbl.model.obras.Acervo;
import pbl.model.obras.Serie;
import pbl.view.telas.Main;
import pbl.view.telas.busca.ExibirReviewController;
import pbl.view.telas.listagem.ListarTemporadasController;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class BuscarSerieController {

    /** ComboBox para seleção do filtro da busca (ex: Título, Gênero, Ano, etc). */
    @FXML private ComboBox<String> cbFiltro;
    /** ListView que exibe as séries filtradas. */
    @FXML private ListView<String> lvListaLivros;
    /** Campo de texto para digitar o termo da busca. */
    @FXML private TextField txtBusca;
    /** Botão para visualizar a equipe da série selecionada. */
    @FXML private Button btVerEquipe;

    /** Labels para exibir informações detalhadas da série selecionada. */
    @FXML private Label txtInfo1, txtInfo2, txtInfo3, txtInfo4, txtInfo5,
            txtInfo6, txtInfo7, txtInfo8, txtInfo12, txtInfo13;

    /** Lista das séries que correspondem ao filtro e termo de busca atual. */
    private List<Serie> seriesFiltradas;

    /**
     * Inicializa o controlador, configurando filtros e eventos.
     */
    @FXML
    public void initialize() {
        cbFiltro.setItems(FXCollections.observableArrayList(
                "Título", "Gênero", "Ano", "Visto",
                "Data de Visto", "Temporadas", "Plataforma", "Avaliação",
                "Atores", "Diretores", "Roteiristas"
        ));
        cbFiltro.getSelectionModel().selectFirst();

        lvListaLivros.setOnMouseClicked(event -> exibirDetalhesSerie());
        txtBusca.setOnKeyReleased(this::onBusca);
    }

    /**
     * Evento disparado ao digitar no campo de busca, que aplica o filtro.
     * @param event Evento de tecla liberada.
     */
    @FXML
    private void onBusca(KeyEvent event) {
        aplicarFiltro(txtBusca.getText().toLowerCase(), cbFiltro.getValue());
    }

    /**
     * Filtra as séries com base no termo e filtro selecionado.
     * Atualiza a ListView com os resultados filtrados.
     * @param termo Texto digitado para busca.
     * @param filtro Critério para filtrar (ex: "Título", "Ano", etc).
     */
    private void aplicarFiltro(String termo, String filtro) {
        List<Serie> series = Acervo.getAcervo().getSeries();

        seriesFiltradas = series.stream().filter(s -> switch (filtro) {
            case "Título" -> (s.getTitulo() != null && s.getTitulo().toLowerCase().contains(termo)) ||
                    (s.getTituloOriginal() != null && s.getTituloOriginal().toLowerCase().contains(termo));
            case "Gênero" -> s.getGenero() != null && s.getGenero().toLowerCase().contains(termo);
            case "Ano" -> String.valueOf(s.getAnoLancamento()).contains(termo);
            case "Visto" -> (s.isVisto() ? "sim" : "não").contains(termo);
            case "Data de Visto" -> s.getDataVisto() != null && s.getDataVisto().toLowerCase().contains(termo);
            case "Temporadas" -> String.valueOf(s.getQntTemporadas()).contains(termo);
            case "Plataforma" -> s.getPlataforma() != null && s.getPlataforma().toLowerCase().contains(termo);
            case "Avaliação" -> String.valueOf(s.getAvaliacao()).contains(termo);
            case "Atores" -> s.getAtores().stream().anyMatch(a -> a.toLowerCase().contains(termo));
            case "Diretores" -> s.getDiretores().stream().anyMatch(d -> d.toLowerCase().contains(termo));
            case "Roteiristas" -> s.getRoteiristas().stream().anyMatch(r -> r.toLowerCase().contains(termo));
            default -> false;
        }).collect(Collectors.toList());

        ObservableList<String> nomes = FXCollections.observableArrayList();
        for (Serie s : seriesFiltradas) {
            String info = switch (filtro) {
                case "Título" -> s.getTituloOriginal();
                case "Gênero" -> s.getGenero();
                case "Ano" -> String.valueOf(s.getAnoLancamento());
                case "Visto" -> s.isVisto() ? "Sim" : "Não";
                case "Data de Visto" -> s.getDataVisto();
                case "Temporadas" -> String.valueOf(s.getQntTemporadas());
                case "Plataforma" -> s.getPlataforma();
                case "Avaliação" -> s.getAvaliacao() + " ★";
                case "Atores" -> String.join(", ", s.getAtores());
                case "Diretores" -> String.join(", ", s.getDiretores());
                case "Roteiristas" -> String.join(", ", s.getRoteiristas());
                default -> "";
            };
            nomes.add(s.getTitulo() + " - " + filtro + ": " + info);
        }

        lvListaLivros.setItems(nomes);
    }

    /**
     * Exibe os detalhes da série selecionada nos labels da interface.
     */
    private void exibirDetalhesSerie() {
        int index = lvListaLivros.getSelectionModel().getSelectedIndex();
        if (index >= 0 && index < seriesFiltradas.size()) {
            Serie serie = seriesFiltradas.get(index);
            txtInfo1.setText(serie.getTitulo() != null ? serie.getTitulo() : "");
            txtInfo2.setText(serie.getTituloOriginal() != null ? serie.getTituloOriginal() : "");
            txtInfo3.setText(serie.getGenero() != null ? serie.getGenero() : "");
            txtInfo4.setText(String.valueOf(serie.getAnoLancamento()));
            txtInfo5.setText(serie.isVisto() ? "Sim" : "Não");
            txtInfo6.setText(serie.getDataVisto() != null ? serie.getDataVisto() : "");
            txtInfo7.setText(String.valueOf(serie.getQntTemporadas()));
            txtInfo8.setText(serie.getPlataforma() != null ? serie.getPlataforma() : "");
            txtInfo12.setText(serie.getAvaliacao() + " ★");
        }
    }

    /**
     * Evento disparado ao clicar no botão para visualizar a equipe da série selecionada.
     * @param event Evento de ação do botão.
     */
    @FXML
    private void onVerEquipe(ActionEvent event) {
        int index = lvListaLivros.getSelectionModel().getSelectedIndex();
        if (index >= 0 && index < seriesFiltradas.size()) {
            Serie serieSelecionada = seriesFiltradas.get(index);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/pbl/view/telas/busca/ExibirEquipe.fxml"));
                Scene scene = new Scene(loader.load(), 660, 415);

                ExibirEquipeController controller = loader.getController();
                controller.setDados(serieSelecionada.getAtores(), serieSelecionada.getDiretores(), serieSelecionada.getRoteiristas());

                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Equipe da Série");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
                mostrarAlerta("Não foi possível abrir a tela da equipe.");
            }
        } else {
            mostrarAlerta("Selecione uma série da lista para ver a equipe.");
        }
    }

    /**
     * Mostra um alerta do tipo warning com a mensagem fornecida.
     * @param msg Mensagem do alerta.
     */
    private void mostrarAlerta(String msg) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Aviso");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    /**
     * Volta para o menu principal de busca.
     */
    @FXML
    protected void onVoltar() {
        Main.trocarTelas("MenuBuscar");
    }

    /**
     * Evento para abrir a lista de temporadas da série selecionada.
     * @param event Evento de ação do botão.
     */
    @FXML
    protected void clickBtListarTemporadas(ActionEvent event) {
        int index = lvListaLivros.getSelectionModel().getSelectedIndex();
        if (index >= 0 && index < seriesFiltradas.size()) {
            Serie serie = seriesFiltradas.get(index);

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/pbl/view/telas/listagem/ListarTemporadas.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(loader.load()));
                stage.setTitle("Temporadas da Série");
                stage.initModality(Modality.APPLICATION_MODAL);

                ListarTemporadasController controller = loader.getController();
                controller.setTemporadas(serie.getTitulo(), serie.getTemporadas());

                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText(null);
                alert.setContentText("Não foi possível abrir a lista de temporadas.");
                alert.showAndWait();
            }
        } else {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Nenhuma Série Selecionada");
            alerta.setHeaderText(null);
            alerta.setContentText("Por favor, selecione uma série para ver as temporadas.");
            alerta.showAndWait();
        }
    }

    /**
     * Evento disparado ao clicar no botão para visualizar a review da série selecionada.
     * @param event Evento de ação do botão.
     */
    @FXML
    private void onVerReview(ActionEvent event) {
        int index = lvListaLivros.getSelectionModel().getSelectedIndex();
        if (index >= 0 && index < seriesFiltradas.size()) {
            Serie serieSelecionada = seriesFiltradas.get(index);
            abrirReviewSerie(serieSelecionada);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText(null);
            alert.setContentText("Selecione uma série da lista para ver a review.");
            alert.showAndWait();
        }
    }

    /**
     * Abre uma janela modal para exibir a review da série.
     * @param serie Série cuja review será exibida.
     */
    private void abrirReviewSerie(Serie serie) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pbl/view/telas/busca/ExibirReview.fxml"));
            Scene scene = new Scene(loader.load(), 660, 415);

            ExibirReviewController controller = loader.getController();
            controller.setDados(serie.getTitulo(), serie.getReview());

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Review da Série");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setContentText("Não foi possível abrir a tela de review.");
            alert.showAndWait();
        }
    }

}
