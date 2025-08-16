package pbl.view.telas.listagem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import pbl.model.obras.Acervo;
import pbl.model.obras.Livro;
import pbl.view.telas.Main;

import java.util.List;
import java.util.stream.Collectors;

public class ListarLivrosController {

    @FXML private Button btVoltar;
    @FXML private ComboBox<String> cbFiltro;
    @FXML private ComboBox<String> cbOrdem;
    @FXML private ListView<String> lvListaLivros;

    private List<Livro> todosLivros;

    @FXML
    public void initialize() {
        cbFiltro.setItems(FXCollections.observableArrayList(
                "", "Título", "Gênero", "Autor", "Ano",
                "Editora", "ISBN", "Data de Leitura", "Avaliação", "Disponibilidade", "Lido"
        ));
        cbFiltro.getSelectionModel().selectFirst();

        cbOrdem.setItems(FXCollections.observableArrayList("Ordem Crescente", "Ordem Decrescente"));
        cbOrdem.getSelectionModel().selectFirst();

        todosLivros = Acervo.getAcervo().getLivros();

        cbFiltro.setOnAction(e -> atualizarLista());
        cbOrdem.setOnAction(e -> atualizarLista());

        atualizarLista();
    }

    private void atualizarLista() {
        String filtro = cbFiltro.getValue();
        String ordem = cbOrdem.getValue();

        List<Livro> filtrados = todosLivros.stream()
                .sorted((l1, l2) -> {
                    if ("Ordem Crescente".equals(ordem)) {
                        return Double.compare(l1.getAvaliacao(), l2.getAvaliacao());
                    } else {
                        return Double.compare(l2.getAvaliacao(), l1.getAvaliacao());
                    }
                })
                .collect(Collectors.toList());

        ObservableList<String> itens = FXCollections.observableArrayList();
        for (Livro livro : filtrados) {
            String texto = String.format("%s (%s) | ★ %d",
                    livro.getTitulo(),
                    livro.getTituloOriginal(),
                    livro.getAvaliacao()
            );

            String valorFiltro = obterValorFiltro(livro, filtro);

            if (!"Avaliação".equals(filtro) && !valorFiltro.isEmpty()) {
                texto += " | " + filtro + ": " + valorFiltro;
            }

            itens.add(texto);
        }

        lvListaLivros.setItems(itens);
    }

    private String obterValorFiltro(Livro livro, String filtro) {
        if (filtro == null || filtro.isEmpty()) return "";

        return switch (filtro) {
            case "Título" -> livro.getTitulo();
            case "Gênero" -> livro.getGenero();
            case "Autor" -> livro.getAutor();
            case "Ano" -> String.valueOf(livro.getAnoLancamento());
            case "Editora" -> livro.getEditora();
            case "ISBN" -> livro.getIsbn();
            case "Data de Leitura" -> livro.getDataVisto() != null ? livro.getDataVisto().toString() : "-";
            case "Avaliação" -> String.valueOf(livro.getAvaliacao());
            case "Disponibilidade" -> livro.isDisponivel() ? "Disponível" : "Indisponível";
            case "Lido" -> livro.isVisto() ? "Lido" : "Não lido";
            default -> "";
        };
    }

    @FXML
    void clickVoltar(ActionEvent event) {
        Main.trocarTelas("MenuListar");
    }
}
