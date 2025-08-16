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
import pbl.model.obras.Filme;
import pbl.view.telas.Main;
import pbl.view.telas.busca.ExibirReviewController;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class BuscarFilmeController {

    /** ComboBox para seleção do filtro de busca (ex: Título, Gênero, Ano, etc). */
    @FXML private ComboBox<String> cbFiltro;
    /** ListView que exibe a lista de filmes filtrados. */
    @FXML private ListView<String> lvListaFilmes;
    /** Campo de texto para digitar o termo da busca. */
    @FXML private TextField txtBusca;

    /** Labels para exibição dos detalhes do filme selecionado. */
    @FXML private Label txtInfo1, txtInfo2, txtInfo3, txtInfo4, txtInfo5,
            txtInfo6, txtInfo7, txtInfo8, txtInfo12;

    /** Lista dos filmes que correspondem ao filtro e termo de busca atual. */
    private List<Filme> filmesFiltrados;

    /**
     * Inicializa o controlador, configura os itens do filtro e eventos dos componentes.
     */
    @FXML
    public void initialize() {
        cbFiltro.setItems(FXCollections.observableArrayList(
                "Título", "Gênero", "Ano", "Visto",
                "Data de Visto", "Duração", "Plataforma", "Avaliação",
                "Atores", "Diretores", "Roteiristas"
        ));
        cbFiltro.getSelectionModel().selectFirst();

        lvListaFilmes.setOnMouseClicked(event -> exibirDetalhesFilme());
        txtBusca.setOnKeyReleased(this::onBusca);
    }

    /**
     * Evento disparado ao digitar no campo de busca, aplicando o filtro.
     * @param event Evento de tecla liberada.
     */
    @FXML
    private void onBusca(KeyEvent event) {
        aplicarFiltro(txtBusca.getText().toLowerCase(), cbFiltro.getValue());
    }

    /**
     * Aplica o filtro aos filmes com base no termo e filtro selecionado.
     * Atualiza a ListView com os resultados filtrados.
     * @param termo Texto digitado para busca.
     * @param filtro Critério para filtrar (ex: "Título", "Gênero", etc).
     */
    private void aplicarFiltro(String termo, String filtro) {
        List<Filme> filmes = Acervo.getAcervo().getFilmes();

        filmesFiltrados = filmes.stream().filter(f -> switch (filtro) {
            case "Título" -> (f.getTitulo() != null && f.getTitulo().toLowerCase().contains(termo)) ||
                    (f.getTituloOriginal() != null && f.getTituloOriginal().toLowerCase().contains(termo));
            case "Gênero" -> f.getGenero() != null && f.getGenero().toLowerCase().contains(termo);
            case "Ano" -> String.valueOf(f.getAnoLancamento()).contains(termo);
            case "Visto" -> (f.isVisto() ? "sim" : "não").contains(termo);
            case "Data de Visto" -> f.getDataVisto() != null && f.getDataVisto().toLowerCase().contains(termo);
            case "Duração" -> f.getDuracao() != null && f.getDuracao().toLowerCase().contains(termo);
            case "Plataforma" -> f.getPlataforma() != null && f.getPlataforma().toLowerCase().contains(termo);
            case "Avaliação" -> String.valueOf(f.getAvaliacao()).contains(termo);
            case "Atores" -> f.getAtores().stream().anyMatch(a -> a.toLowerCase().contains(termo));
            case "Diretores" -> f.getDiretores().stream().anyMatch(d -> d.toLowerCase().contains(termo));
            case "Roteiristas" -> f.getRoteiristas().stream().anyMatch(r -> r.toLowerCase().contains(termo));
            default -> false;
        }).collect(Collectors.toList());

        ObservableList<String> nomes = FXCollections.observableArrayList();
        for (Filme f : filmesFiltrados) {
            String info = switch (filtro) {
                case "Título" -> f.getTituloOriginal();
                case "Gênero" -> f.getGenero();
                case "Ano" -> String.valueOf(f.getAnoLancamento());
                case "Visto" -> f.isVisto() ? "Sim" : "Não";
                case "Data de Visto" -> f.getDataVisto();
                case "Duração" -> f.getDuracao();
                case "Plataforma" -> f.getPlataforma();
                case "Avaliação" -> f.getAvaliacao() + " ★";
                case "Atores" -> String.join(", ", f.getAtores());
                case "Diretores" -> String.join(", ", f.getDiretores());
                case "Roteiristas" -> String.join(", ", f.getRoteiristas());
                default -> "";
            };
            nomes.add(f.getTitulo() + " - " + filtro + ": " + info);
        }

        lvListaFilmes.setItems(nomes);
    }

    /**
     * Exibe os detalhes do filme selecionado na ListView nos labels da interface.
     */
    private void exibirDetalhesFilme() {
        int index = lvListaFilmes.getSelectionModel().getSelectedIndex();
        if (index >= 0 && index < filmesFiltrados.size()) {
            Filme filme = filmesFiltrados.get(index);
            txtInfo1.setText(filme.getTitulo() != null ? filme.getTitulo() : "");
            txtInfo2.setText(filme.getTituloOriginal() != null ? filme.getTituloOriginal() : "");
            txtInfo3.setText(filme.getGenero() != null ? filme.getGenero() : "");
            txtInfo4.setText(String.valueOf(filme.getAnoLancamento()));
            txtInfo5.setText(filme.isVisto() ? "Sim" : "Não");
            txtInfo6.setText(filme.getDataVisto() != null ? filme.getDataVisto() : "");
            txtInfo7.setText(filme.getDuracao() != null ? filme.getDuracao() : "");
            txtInfo8.setText(filme.getPlataforma() != null ? filme.getPlataforma() : "");
            txtInfo12.setText(filme.getAvaliacao() + " ★");
        }
    }

    /**
     * Evento disparado ao clicar no botão para visualizar a review do filme selecionado.
     * @param event Evento de ação do botão.
     */
    @FXML
    private void onVerReview(ActionEvent event) {
        int index = lvListaFilmes.getSelectionModel().getSelectedIndex();
        if (index >= 0 && index < filmesFiltrados.size()) {
            Filme filmeSelecionado = filmesFiltrados.get(index);
            abrirReviewFilme(filmeSelecionado);
        } else {
            mostrarAlerta("Selecione um filme da lista para ver a review.");
        }
    }

    /**
     * Abre uma janela modal para exibir a review do filme.
     * @param filme Filme cujo review será exibido.
     */
    private void abrirReviewFilme(Filme filme) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pbl/view/telas/busca/ExibirReview.fxml"));
            Scene scene = new Scene(loader.load(), 660, 415);

            ExibirReviewController controller = loader.getController();
            controller.setDados(filme.getTitulo(), filme.getReview());

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Review do Filme");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta("Não foi possível abrir a tela de review.");
        }
    }

    /**
     * Evento disparado ao clicar no botão para visualizar a equipe do filme selecionado.
     * @param event Evento de ação do botão.
     */
    @FXML
    private void onVerEquipe(ActionEvent event) {
        int index = lvListaFilmes.getSelectionModel().getSelectedIndex();
        if (index >= 0 && index < filmesFiltrados.size()) {
            Filme filmeSelecionado = filmesFiltrados.get(index);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/pbl/view/telas/busca/ExibirEquipe.fxml"));
                Scene scene = new Scene(loader.load(), 660, 415);

                ExibirEquipeController controller = loader.getController();
                controller.setDados(filmeSelecionado.getAtores(), filmeSelecionado.getDiretores(), filmeSelecionado.getRoteiristas());

                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Equipe do Filme");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
                mostrarAlerta("Não foi possível abrir a tela da equipe.");
            }
        } else {
            mostrarAlerta("Selecione um filme da lista para ver a equipe.");
        }
    }

    /**
     * Exibe uma janela de alerta do tipo aviso com a mensagem especificada.
     * @param msg Mensagem a ser exibida no alerta.
     */
    private void mostrarAlerta(String msg) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Aviso");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    /**
     * Evento para voltar para o menu de busca principal.
     */
    @FXML
    protected void onVoltar() {
        Main.trocarTelas("MenuBuscar");
    }
}
