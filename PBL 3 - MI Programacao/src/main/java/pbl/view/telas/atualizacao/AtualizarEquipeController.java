package pbl.view.telas.atualizacao;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import pbl.model.obras.Filme;
import pbl.model.obras.Serie;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Controlador responsável por atualizar os nomes da equipe (atores, diretores e roteiristas)
 * associados a um filme ou série.
 * Permite selecionar um cargo, escolher um nome atual e substituí-lo por um novo.
 */
public class AtualizarEquipeController implements Initializable {

    /** Botão para confirmar a alteração de nome */
    @FXML private Button btConcluir;

    /** Botão para fechar a janela */
    @FXML private Button btVoltar;

    /** ComboBox para selecionar o cargo (ator, diretor ou roteirista) */
    @FXML private ComboBox<String> cbCargo;

    /** ComboBox para selecionar o nome atual */
    @FXML private ComboBox<String> cbNome;

    /** Campo de texto exibindo o nome atual selecionado */
    @FXML private TextField txtNomeAtual;

    /** Campo de texto para inserir o novo nome */
    @FXML private TextField txtNovoNome;

    /** Filme a ser atualizado (caso aplicável) */
    private Filme filme;

    /** Série a ser atualizada (caso aplicável) */
    private Serie serie;

    /**
     * Define o filme cujos dados de equipe serão atualizados.
     *
     * @param filme Filme selecionado
     */
    public void setFilme(Filme filme) {
        this.filme = filme;
        carregarCargos();
    }

    /**
     * Define a série cujos dados de equipe serão atualizados.
     *
     * @param serie Série selecionada
     */
    public void setSerie(Serie serie) {
        this.serie = serie;
        carregarCargos();
    }

    /**
     * Inicializa os componentes da interface e define ações dos botões e ComboBoxes.
     *
     * @param url URL de localização do FXML
     * @param resourceBundle Recursos utilizados para internacionalização (não utilizado aqui)
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cbCargo.setOnAction(e -> carregarNomes());
        cbNome.setOnAction(e -> exibirNomeAtual());
        btConcluir.setOnAction(e -> concluirAlteracao());

        btVoltar.setOnAction(e -> {
            Stage stage = (Stage) btVoltar.getScene().getWindow();
            stage.close();
        });

        Platform.runLater(() -> {
            Stage stage = (Stage) btVoltar.getScene().getWindow();
            if (stage != null) {
                stage.getIcons().add(new Image(getClass().getResourceAsStream("/pbl/view/img/IconeLogo.png")));
            }
        });
    }

    /**
     * Carrega os cargos disponíveis na ComboBox de cargo.
     */
    private void carregarCargos() {
        cbCargo.setItems(FXCollections.observableArrayList("Atores", "Diretores", "Roteiristas"));
    }

    /**
     * Carrega os nomes da equipe com base no cargo e na mídia selecionada (filme ou série).
     */
    private void carregarNomes() {
        cbNome.getItems().clear();
        List<String> nomes = new ArrayList<>();

        if (cbCargo.getValue() == null) return;

        if (filme != null) {
            switch (cbCargo.getValue()) {
                case "Atores" -> nomes = filme.getAtores();
                case "Diretores" -> nomes = filme.getDiretores();
                case "Roteiristas" -> nomes = filme.getRoteiristas();
            }
        } else if (serie != null) {
            switch (cbCargo.getValue()) {
                case "Atores" -> nomes = serie.getAtores();
                case "Diretores" -> nomes = serie.getDiretores();
                case "Roteiristas" -> nomes = serie.getRoteiristas();
            }
        }

        cbNome.setItems(FXCollections.observableArrayList(nomes));
    }

    /**
     * Exibe o nome atual selecionado no campo de texto correspondente.
     */
    private void exibirNomeAtual() {
        if (cbNome.getValue() != null) {
            txtNomeAtual.setText(cbNome.getValue());
        }
    }

    /**
     * Conclui a alteração de nome da equipe, validando os campos e atualizando a lista.
     * Exibe alertas informativos ou de erro conforme o resultado da operação.
     */
    private void concluirAlteracao() {
        String cargo = cbCargo.getValue();
        String nomeAtual = cbNome.getValue();
        String novoNome = txtNovoNome.getText().trim();

        if (cargo == null || nomeAtual == null || novoNome.isEmpty()) {
            mostrarAlerta("Erro", "Preencha todos os campos.", Alert.AlertType.ERROR);
            return;
        }

        List<String> lista = null;

        if (filme != null) {
            lista = switch (cargo) {
                case "Atores" -> filme.getAtores();
                case "Diretores" -> filme.getDiretores();
                case "Roteiristas" -> filme.getRoteiristas();
                default -> null;
            };
        } else if (serie != null) {
            lista = switch (cargo) {
                case "Atores" -> serie.getAtores();
                case "Diretores" -> serie.getDiretores();
                case "Roteiristas" -> serie.getRoteiristas();
                default -> null;
            };
        }

        if (lista != null && lista.contains(nomeAtual)) {
            int idx = lista.indexOf(nomeAtual);
            lista.set(idx, novoNome);
            mostrarAlerta("Sucesso", "Nome alterado com sucesso!", Alert.AlertType.INFORMATION);
            carregarNomes();
            txtNomeAtual.clear();
            txtNovoNome.clear();
        } else {
            mostrarAlerta("Erro", "Nome não encontrado.", Alert.AlertType.ERROR);
        }
    }

    /**
     * Exibe um alerta ao usuário.
     *
     * @param titulo Título da janela de alerta
     * @param msg Mensagem exibida no corpo do alerta
     * @param tipo Tipo de alerta (erro, informação, etc.)
     */
    private void mostrarAlerta(String titulo, String msg, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
