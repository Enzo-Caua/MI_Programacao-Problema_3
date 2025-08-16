package pbl.view.telas.atualizacao;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pbl.model.obras.Acervo;
import pbl.model.obras.Serie;
import pbl.view.telas.Main;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Controlador da tela de atualização de dados de séries.
 * Permite editar atributos como título, gênero, ano de lançamento, plataforma,
 * estado de assistido e também acessar a tela de edição da equipe (atores, diretores, roteiristas).
 */
public class AtualizarSerieController {

    /** Botão para concluir a alteração do dado selecionado */
    @FXML private Button btConcluir;

    /** Botão para voltar ao menu de atualização */
    @FXML private Button btVoltar;

    /** Botão para abrir a tela de alteração da equipe da série */
    @FXML private Button btAlterarEquipe;

    /** ComboBox com os atributos disponíveis para edição */
    @FXML private ComboBox<String> cbDados;

    /** ComboBox com as séries disponíveis no acervo */
    @FXML private ComboBox<String> cbSerie;

    /** Campo de texto que exibe o valor atual do atributo selecionado */
    @FXML private TextField txtDadoAtual;

    /** Campo de texto para digitar o novo valor do atributo */
    @FXML private TextField txtNovoDado;

    /** Série atualmente selecionada para alteração */
    private Serie serieSelecionada;

    /** Lista de atributos da série disponíveis para edição */
    private final String[] atributos = {
            "Título", "Título Original", "Gênero", "Ano de Lançamento",
            "Plataforma", "Assistido"
    };

    /**
     * Inicializa os componentes da interface e associa eventos aos botões e ComboBoxes.
     * Popula as ComboBoxes com as séries do acervo e atributos editáveis.
     */
    @FXML
    public void initialize() {
        for (Serie s : Acervo.getAcervo().getSeries()) {
            cbSerie.getItems().add(s.getTitulo());
        }

        cbDados.setItems(FXCollections.observableArrayList(atributos));

        cbSerie.setOnAction(e -> atualizarSerieSelecionada());
        cbDados.setOnAction(e -> exibirDadoAtual());
        btConcluir.setOnAction(e -> alterarDado());
        btVoltar.setOnAction(e -> Main.trocarTelas("MenuAtualizar"));
        btAlterarEquipe.setOnAction(e -> abrirTelaEquipe());
    }

    /**
     * Atualiza a série selecionada com base no título escolhido pelo usuário.
     * Limpa os campos de texto e ComboBox de dados.
     */
    private void atualizarSerieSelecionada() {
        String titulo = cbSerie.getValue();
        for (Serie s : Acervo.getAcervo().getSeries()) {
            if (s.getTitulo().equals(titulo)) {
                serieSelecionada = s;
                txtDadoAtual.clear();
                txtNovoDado.clear();
                cbDados.setValue(null);
                break;
            }
        }
    }

    /**
     * Exibe o valor atual do atributo selecionado da série escolhida.
     */
    private void exibirDadoAtual() {
        if (serieSelecionada == null || cbDados.getValue() == null) {
            txtDadoAtual.setText("");
            return;
        }

        String dado = switch (cbDados.getValue()) {
            case "Título" -> serieSelecionada.getTitulo();
            case "Título Original" -> serieSelecionada.getTituloOriginal();
            case "Gênero" -> serieSelecionada.getGenero();
            case "Ano de Lançamento" -> serieSelecionada.getAnoLancamento();
            case "Plataforma" -> serieSelecionada.getPlataforma();
            case "Assistido" -> serieSelecionada.isVisto() ? serieSelecionada.getDataVisto() : "Não Assistido";
            default -> "";
        };

        txtDadoAtual.setText(dado);
    }

    /**
     * Altera o atributo selecionado da série com o novo valor fornecido.
     * Se for uma data de visualização, valida o formato.
     */
    private void alterarDado() {
        if (serieSelecionada == null || cbDados.getValue() == null || txtNovoDado.getText().isBlank()) {
            mostrarAlerta("Erro", "Preencha todos os campos antes de concluir.", Alert.AlertType.ERROR);
            return;
        }

        String novoValor = txtNovoDado.getText().trim();

        switch (cbDados.getValue()) {
            case "Título" -> serieSelecionada.setTitulo(novoValor);
            case "Título Original" -> serieSelecionada.setTituloOriginal(novoValor);
            case "Gênero" -> serieSelecionada.setGenero(novoValor);
            case "Ano de Lançamento" -> serieSelecionada.setAnoLancamento(novoValor);
            case "Plataforma" -> serieSelecionada.setPlataforma(novoValor);
            case "Assistido" -> {
                if (!validarData(novoValor)) {
                    mostrarAlerta("Erro", "Digite a data no formato dd/MM/aaaa.", Alert.AlertType.ERROR);
                    return;
                }
                serieSelecionada.alterarVisto(novoValor);
            }
            case "Diretores" -> serieSelecionada.setDiretores(new ArrayList<>(List.of(novoValor.split(",\\s*"))));
            case "Roteiristas" -> serieSelecionada.setRoteiristas(new ArrayList<>(List.of(novoValor.split(",\\s*"))));
            case "Atores" -> serieSelecionada.setAtores(new ArrayList<>(List.of(novoValor.split(",\\s*"))));
            default -> {
                mostrarAlerta("Erro", "Atributo inválido.", Alert.AlertType.ERROR);
                return;
            }
        }

        mostrarAlerta("Sucesso", "Dado atualizado com sucesso!", Alert.AlertType.INFORMATION);
        exibirDadoAtual();
        txtNovoDado.clear();
    }

    /**
     * Valida uma string de data no formato {@code dd/MM/yyyy}.
     *
     * @param data String representando a data
     * @return {@code true} se a data for válida, {@code false} caso contrário
     */
    private boolean validarData(String data) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        try {
            sdf.parse(data);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Abre a tela de alteração da equipe da série em uma nova janela modal.
     * Requer que uma série esteja previamente selecionada.
     */
    private void abrirTelaEquipe() {
        if (serieSelecionada == null) {
            mostrarAlerta("Erro", "Selecione uma série primeiro.", Alert.AlertType.ERROR);
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pbl/view/telas/atualizacao/AtualizarEquipe.fxml"));
            Scene scene = new Scene(loader.load());

            AtualizarEquipeController controller = loader.getController();
            controller.setSerie(serieSelecionada);

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Atualizar Equipe da Série");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

        } catch (IOException e) {
            mostrarAlerta("Erro", "Erro ao abrir a tela de equipe.", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    /**
     * Exibe um alerta com o título, a mensagem e o tipo especificados.
     *
     * @param titulo   Título da janela de alerta
     * @param mensagem Mensagem exibida ao usuário
     * @param tipo     Tipo do alerta (informação, erro, etc.)
     */
    private void mostrarAlerta(String titulo, String mensagem, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }
}
