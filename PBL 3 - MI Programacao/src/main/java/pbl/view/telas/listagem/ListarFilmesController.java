package pbl.view.telas.listagem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import pbl.model.obras.Acervo;
import pbl.model.obras.Filme;
import pbl.view.telas.Main;

import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class ListarFilmesController implements Initializable {

    @FXML private ListView<String> lvListaFilmes;
    @FXML private ComboBox<String> cbFiltro;
    @FXML private ComboBox<String> cbOrdem;
    @FXML private TextField txtBusca;

    private List<Filme> todosFilmes;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cbFiltro.setItems(FXCollections.observableArrayList(
                "", "Título", "Gênero", "Ano", "Visto",
                "Data de Visto", "Duração", "Plataforma", "Avaliação",
                "Atores", "Diretores", "Roteiristas"
        ));
        cbFiltro.getSelectionModel().selectFirst();

        cbOrdem.setItems(FXCollections.observableArrayList("Ordem Crescente", "Ordem Decrescente"));
        cbOrdem.getSelectionModel().selectFirst();

        todosFilmes = Acervo.getAcervo().getFilmes();

        txtBusca.textProperty().addListener((obs, oldVal, newVal) -> atualizarLista());
        cbFiltro.setOnAction(e -> atualizarLista());
        cbOrdem.setOnAction(e -> atualizarLista());

        atualizarLista();
    }

    private void atualizarLista() {
        String textoBusca = txtBusca.getText().toLowerCase().trim();
        String filtro = cbFiltro.getValue();
        String ordem = cbOrdem.getValue();

        List<Filme> filtrados = todosFilmes.stream()
                .filter(filme -> {
                    if (filtro == null || filtro.isEmpty() || textoBusca.isEmpty()) return true;

                    return switch (filtro) {
                        case "Título" -> filme.getTitulo().toLowerCase().contains(textoBusca) ||
                                filme.getTituloOriginal().toLowerCase().contains(textoBusca);
                        case "Gênero" -> filme.getGenero().toLowerCase().contains(textoBusca);
                        case "Ano" -> String.valueOf(filme.getAnoLancamento()).contains(textoBusca);
                        case "Visto" -> (filme.isVisto() ? "sim" : "não").contains(textoBusca);
                        case "Data de Visto" -> filme.getDataVisto() != null &&
                                filme.getDataVisto().toString().contains(textoBusca);
                        case "Duração" -> filme.getDuracao().toLowerCase().contains(textoBusca);
                        case "Plataforma" -> filme.getPlataforma().toLowerCase().contains(textoBusca);
                        case "Avaliação" -> String.valueOf(filme.getAvaliacao()).contains(textoBusca);
                        case "Atores" -> filme.getAtores().stream().anyMatch(a -> a.toLowerCase().contains(textoBusca));
                        case "Diretores" -> filme.getDiretores().stream().anyMatch(d -> d.toLowerCase().contains(textoBusca));
                        case "Roteiristas" -> filme.getRoteiristas().stream().anyMatch(r -> r.toLowerCase().contains(textoBusca));
                        default -> true;
                    };
                })
                .sorted((f1, f2) -> {
                    if ("Ordem Crescente".equals(ordem)) {
                        return Integer.compare(f1.getAvaliacao(), f2.getAvaliacao());
                    } else {
                        return Integer.compare(f2.getAvaliacao(), f1.getAvaliacao());
                    }
                })
                .collect(Collectors.toList());

        ObservableList<String> itens = FXCollections.observableArrayList();

        for (Filme filme : filtrados) {
            String info = String.format("%s (%s) | ★ %d",
                    filme.getTitulo(),
                    filme.getTituloOriginal(),
                    filme.getAvaliacao()
            );

            String valorFiltro = obterValorFiltro(filme, filtro);
            if (!"Avaliação".equals(filtro) && !valorFiltro.isEmpty()) {
                info += " | " + filtro + ": " + valorFiltro;
            }

            itens.add(info);
        }

        lvListaFilmes.setItems(itens);
    }

    private String obterValorFiltro(Filme filme, String filtro) {
        if (filtro == null || filtro.isEmpty()) return "";

        return switch (filtro) {
            case "Título" -> filme.getTitulo();
            case "Gênero" -> filme.getGenero();
            case "Ano" -> String.valueOf(filme.getAnoLancamento());
            case "Visto" -> filme.isVisto() ? "Sim" : "Não";
            case "Data de Visto" -> filme.getDataVisto() != null ? filme.getDataVisto().toString() : "-";
            case "Duração" -> filme.getDuracao();
            case "Plataforma" -> filme.getPlataforma();
            case "Avaliação" -> String.valueOf(filme.getAvaliacao());
            case "Atores" -> String.join(", ", filme.getAtores());
            case "Diretores" -> String.join(", ", filme.getDiretores());
            case "Roteiristas" -> String.join(", ", filme.getRoteiristas());
            default -> "";
        };
    }

    @FXML
    protected void clickVoltar(ActionEvent event) {
        Main.trocarTelas("MenuListar");
    }
}
