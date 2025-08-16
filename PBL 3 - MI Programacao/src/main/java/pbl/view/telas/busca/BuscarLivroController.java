package pbl.view.telas.busca;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pbl.model.obras.Acervo;
import pbl.model.obras.Livro;
import pbl.view.telas.Main;
import pbl.view.telas.busca.ExibirReviewController;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class BuscarLivroController {

    /** ComboBox para seleção do filtro da busca (ex: Título, Gênero, Autor, etc). */
    @FXML private ComboBox<String> cbFiltro;
    /** ListView que exibe os livros filtrados. */
    @FXML private ListView<String> lvListaLivros;
    /** Campo de texto para digitar o termo da busca. */
    @FXML private TextField txtBusca;
    /** Botão para visualizar a review do livro selecionado. */
    @FXML private Button btVerReview;

    /** Labels para exibir informações detalhadas do livro selecionado. */
    @FXML private Label txtInfo1, txtInfo2, txtInfo3, txtInfo4, txtInfo5,
            txtInfo7, txtInfo8, txtInfo9, txtInfo10,
            txtInfo11, txtInfo14;

    /** Lista dos livros que correspondem ao filtro e termo de busca atual. */
    private List<Livro> livrosFiltrados;

    /**
     * Inicializa o controlador, configura os filtros e eventos dos componentes.
     */
    @FXML
    public void initialize() {
        cbFiltro.setItems(FXCollections.observableArrayList(
                "Título", "Gênero", "Autor", "Ano",
                "Editora", "ISBN", "Data de Leitura", "Avaliação", "Disponibilidade", "Lido"
        ));
        cbFiltro.getSelectionModel().selectFirst();

        lvListaLivros.setOnMouseClicked(event -> exibirDetalhesLivro());
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
     * Filtra os livros com base no termo e filtro selecionado.
     * Atualiza a ListView com os resultados filtrados.
     * @param termo Texto digitado para busca.
     * @param filtro Critério para filtrar (ex: "Título", "Autor", etc).
     */
    private void aplicarFiltro(String termo, String filtro) {
        List<Livro> livros = Acervo.getAcervo().getLivros();

        livrosFiltrados = livros.stream().filter(l -> {
            return switch (filtro) {
                case "Título" -> (l.getTitulo() != null && l.getTitulo().toLowerCase().contains(termo)) ||
                        (l.getTituloOriginal() != null && l.getTituloOriginal().toLowerCase().contains(termo));
                case "Gênero" -> l.getGenero() != null && l.getGenero().toLowerCase().contains(termo);
                case "Autor" -> l.getAutor() != null && l.getAutor().toLowerCase().contains(termo);
                case "Ano" -> String.valueOf(l.getAnoLancamento()).contains(termo);
                case "Editora" -> l.getEditora() != null && l.getEditora().toLowerCase().contains(termo);
                case "ISBN" -> l.getIsbn() != null && l.getIsbn().toLowerCase().contains(termo);
                case "Data de Leitura" -> l.getDataVisto() != null && l.getDataVisto().toLowerCase().contains(termo);
                case "Avaliação" -> String.valueOf(l.getAvaliacao()).contains(termo);
                case "Disponibilidade" -> (l.isDisponivel() ? "sim" : "não").contains(termo);
                case "Lido" -> (l.isVisto() ? "sim" : "não").contains(termo);
                default -> false;
            };
        }).collect(Collectors.toList());

        ObservableList<String> nomes = FXCollections.observableArrayList();
        for (Livro l : livrosFiltrados) {
            String info = switch (filtro) {
                case "Título" -> l.getTituloOriginal();
                case "Gênero" -> l.getGenero();
                case "Autor" -> l.getAutor();
                case "Ano" -> String.valueOf(l.getAnoLancamento());
                case "Editora" -> l.getEditora();
                case "ISBN" -> l.getIsbn();
                case "Data de Leitura" -> l.getDataVisto();
                case "Avaliação" -> l.getAvaliacao() + " ★";
                case "Disponibilidade" -> l.isDisponivel() ? "Sim" : "Não";
                case "Lido" -> l.isVisto() ? "Sim" : "Não";
                default -> "";
            };
            nomes.add(l.getTitulo() + " - " + filtro + ": " + info);
        }

        lvListaLivros.setItems(nomes);
    }

    /**
     * Exibe os detalhes do livro selecionado na ListView nos labels da interface.
     */
    private void exibirDetalhesLivro() {
        int index = lvListaLivros.getSelectionModel().getSelectedIndex();
        if (index >= 0 && index < livrosFiltrados.size()) {
            Livro livro = livrosFiltrados.get(index);
            txtInfo1.setText(livro.getTitulo());
            txtInfo2.setText(livro.getTituloOriginal());
            txtInfo3.setText(livro.getGenero());
            txtInfo4.setText(String.valueOf(livro.getAnoLancamento()));
            txtInfo5.setText(livro.isVisto() ? "Sim" : "Não");
            txtInfo7.setText(livro.getAutor());
            txtInfo8.setText(livro.getEditora());
            txtInfo9.setText(livro.getIsbn());
            txtInfo10.setText(livro.isDisponivel() ? "Sim" : "Não");
            txtInfo11.setText(livro.getAvaliacao() + " ★");
            txtInfo14.setText(livro.getDataVisto());
        }
    }

    /**
     * Evento disparado ao clicar no botão para visualizar a review do livro selecionado.
     * @param event Evento de ação do botão.
     */
    @FXML
    private void onVerReview(ActionEvent event) {
        int index = lvListaLivros.getSelectionModel().getSelectedIndex();
        if (index >= 0 && index < livrosFiltrados.size()) {
            Livro livroSelecionado = livrosFiltrados.get(index);
            abrirReviewLivro(livroSelecionado);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText(null);
            alert.setContentText("Selecione um livro da lista para ver a review.");
            alert.showAndWait();
        }
    }

    /**
     * Abre uma janela modal para exibir a review do livro.
     * @param livro Livro cujo review será exibido.
     */
    private void abrirReviewLivro(Livro livro) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pbl/view/telas/busca/ExibirReview.fxml"));
            Scene scene = new Scene(loader.load(), 660, 415);

            ExibirReviewController controller = loader.getController();
            controller.setDados(livro.getTitulo(), livro.getReview());

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Review do Livro");
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

    /**
     * Evento para voltar para o menu de busca principal.
     */
    @FXML
    protected void onVoltar() {
        Main.trocarTelas("MenuBuscar");
    }
}
