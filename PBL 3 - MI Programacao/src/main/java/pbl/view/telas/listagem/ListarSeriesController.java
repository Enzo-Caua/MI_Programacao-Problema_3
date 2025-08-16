package pbl.view.telas.listagem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pbl.model.obras.Acervo;
import pbl.model.obras.Serie;
import pbl.view.telas.Main;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class ListarSeriesController {

    @FXML private ListView<String> lvListaSeries;
    @FXML private ComboBox<String> cbFiltro;
    @FXML private ComboBox<String> cbOrdem;
    @FXML private TextField txtBusca;

    private List<Serie> todasSeries;

    @FXML
    public void initialize() {
        cbFiltro.setItems(FXCollections.observableArrayList(
                "", "Título", "Gênero", "Ano", "Visto",
                "Data de Visto", "Temporadas", "Plataforma", "Avaliação",
                "Atores", "Diretores", "Roteiristas"
        ));
        cbFiltro.getSelectionModel().selectFirst();

        cbOrdem.setItems(FXCollections.observableArrayList("Ordem Crescente", "Ordem Decrescente"));
        cbOrdem.getSelectionModel().selectFirst();

        todasSeries = Acervo.getAcervo().getSeries();

        txtBusca.textProperty().addListener((obs, oldVal, newVal) -> atualizarLista());
        cbFiltro.setOnAction(e -> atualizarLista());
        cbOrdem.setOnAction(e -> atualizarLista());

        atualizarLista();

        lvListaSeries.setOnMouseClicked(this::exibirDetalhesTemporadas);
    }

    private void atualizarLista() {
        String textoBusca = txtBusca.getText().toLowerCase().trim();
        String filtro = cbFiltro.getValue();
        String ordem = cbOrdem.getValue();

        List<Serie> filtradas = todasSeries.stream()
                .filter(serie -> {
                    if (filtro == null || filtro.isEmpty() || textoBusca.isEmpty()) return true;

                    return switch (filtro) {
                        case "Título" -> serie.getTitulo().toLowerCase().contains(textoBusca) ||
                                serie.getTituloOriginal().toLowerCase().contains(textoBusca);
                        case "Gênero" -> serie.getGenero().toLowerCase().contains(textoBusca);
                        case "Ano" -> String.valueOf(serie.getAnoLancamento()).contains(textoBusca);
                        case "Visto" -> (serie.isVisto() ? "sim" : "não").contains(textoBusca);
                        case "Data de Visto" -> serie.getDataVisto() != null &&
                                serie.getDataVisto().toString().contains(textoBusca);
                        case "Temporadas" -> String.valueOf(serie.getQntTemporadas()).contains(textoBusca);
                        case "Plataforma" -> serie.getPlataforma().toLowerCase().contains(textoBusca);
                        case "Avaliação" -> String.valueOf(serie.getAvaliacao()).contains(textoBusca);
                        case "Atores" -> serie.getAtores().stream().anyMatch(a -> a.toLowerCase().contains(textoBusca));
                        case "Diretores" -> serie.getDiretores().stream().anyMatch(d -> d.toLowerCase().contains(textoBusca));
                        case "Roteiristas" -> serie.getRoteiristas().stream().anyMatch(r -> r.toLowerCase().contains(textoBusca));
                        default -> true;
                    };
                })
                .sorted((s1, s2) -> {
                    if ("Ordem Crescente".equals(ordem)) {
                        return Integer.compare(s1.getAvaliacao(), s2.getAvaliacao());
                    } else {
                        return Integer.compare(s2.getAvaliacao(), s1.getAvaliacao());
                    }
                })
                .collect(Collectors.toList());

        ObservableList<String> itens = FXCollections.observableArrayList();

        for (Serie serie : filtradas) {
            String info = String.format("%s (%s) | ★ %d",
                    serie.getTitulo(),
                    serie.getTituloOriginal(),
                    serie.getAvaliacao());

            String valorFiltro = obterValorFiltro(serie, filtro);
            if (!"Avaliação".equals(filtro) && !valorFiltro.isEmpty()) {
                info += " | " + filtro + ": " + valorFiltro;
            }

            itens.add(info);
        }

        lvListaSeries.setItems(itens);
    }

    private String obterValorFiltro(Serie serie, String filtro) {
        if (filtro == null || filtro.isEmpty()) return "";

        return switch (filtro) {
            case "Título" -> serie.getTitulo();
            case "Gênero" -> serie.getGenero();
            case "Ano" -> String.valueOf(serie.getAnoLancamento());
            case "Visto" -> serie.isVisto() ? "Sim" : "Não";
            case "Data de Visto" -> serie.getDataVisto() != null ? serie.getDataVisto().toString() : "-";
            case "Temporadas" -> String.valueOf(serie.getQntTemporadas());
            case "Plataforma" -> serie.getPlataforma();
            case "Avaliação" -> String.valueOf(serie.getAvaliacao());
            case "Atores" -> String.join(", ", serie.getAtores());
            case "Diretores" -> String.join(", ", serie.getDiretores());
            case "Roteiristas" -> String.join(", ", serie.getRoteiristas());
            default -> "";
        };
    }

    private void exibirDetalhesTemporadas(MouseEvent event) {
        if (event.getClickCount() == 2) {
            clickBtListarTemporadas(null);
        }
    }

    @FXML
    protected void clickBtListarTemporadas(ActionEvent event) {
        int index = lvListaSeries.getSelectionModel().getSelectedIndex();
        if (index >= 0 && index < todasSeries.size()) {
            Serie serie = todasSeries.get(index);

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
            }
        } else {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Nenhuma Série Selecionada");
            alerta.setHeaderText(null);
            alerta.setContentText("Por favor, selecione uma série para ver as temporadas.");
            alerta.showAndWait();
        }
    }

    @FXML
    protected void clickVoltar(ActionEvent event) {
        Main.trocarTelas("MenuListar");
    }
}
