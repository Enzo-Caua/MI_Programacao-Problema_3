package pbl.view.telas.atualizacao;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pbl.model.obras.Acervo;
import pbl.model.obras.Filme;
import pbl.view.telas.Main;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Controlador responsável pela tela de atualização de dados de filmes.
 * Permite alterar informações básicas como título, gênero, ano, duração,
 * plataforma e também o estado de "assistido".
 * Também oferece acesso à tela de atualização da equipe (atores, diretores, roteiristas).
 */
public class AtualizarFilmeController {

    /** ComboBox com a lista de filmes disponíveis no acervo */
    @FXML private ComboBox<String> cbFilme;

    /** ComboBox com os atributos disponíveis para edição */
    @FXML private ComboBox<String> cbDados;

    /** Campo de texto exibindo o valor atual do atributo */
    @FXML private TextField txtDadoAtual;

    /** Campo de texto para digitar o novo valor do atributo */
    @FXML private TextField txtNovoDado;

    /** Botão para voltar ao menu de atualização */
    @FXML private Button btVoltar;

    /** Botão para concluir a alteração do dado */
    @FXML private Button btConcluir;

    /** Botão para abrir a tela de alteração da equipe */
    @FXML private Button btAlterarEquipe;

    /** Filme atualmente selecionado para atualização */
    private Filme filmeSelecionado;

    /** Atributos disponíveis para edição */
    private final String[] atributosEditaveis = {
            "Título", "Título Original", "Gênero", "Ano de Lançamento",
            "Duração", "Plataforma", "Assistido"
    };

    /**
     * Inicializa os componentes da interface e define os listeners de eventos.
     * Popula os ComboBoxes e associa os botões às suas ações.
     */
    @FXML
    public void initialize() {
        List<Filme> filmes = Acervo.getAcervo().getFilmes();
        for (Filme f : filmes) {
            cbFilme.getItems().add(f.getTitulo());
        }

        cbDados.setItems(FXCollections.observableArrayList(atributosEditaveis));

        cbFilme.setOnAction(e -> atualizarFilmeSelecionado());
        cbDados.setOnAction(e -> exibirDadoAtual());
        btConcluir.setOnAction(e -> alterarDado());
        btVoltar.setOnAction(e -> Main.trocarTelas("MenuAtualizar"));
        btAlterarEquipe.setOnAction(e -> abrirTelaEquipe());
    }

    /**
     * Atualiza o objeto {@code filmeSelecionado} com base no título escolhido.
     * Limpa os campos e ComboBox de atributos.
     */
    private void atualizarFilmeSelecionado() {
        String titulo = cbFilme.getValue();
        filmeSelecionado = Acervo.getAcervo().getFilmes().stream()
                .filter(f -> f.getTitulo().equals(titulo))
                .findFirst().orElse(null);

        txtDadoAtual.clear();
        txtNovoDado.clear();
        cbDados.setValue(null);
    }

    /**
     * Exibe o valor atual do atributo selecionado no campo correspondente.
     * Caso o atributo seja "Assistido", mostra a data ou "Não Assistido".
     */
    private void exibirDadoAtual() {
        if (filmeSelecionado == null || cbDados.getValue() == null) {
            txtDadoAtual.setText("");
            return;
        }

        String dado = switch (cbDados.getValue()) {
            case "Título" -> filmeSelecionado.getTitulo();
            case "Título Original" -> filmeSelecionado.getTituloOriginal();
            case "Gênero" -> filmeSelecionado.getGenero();
            case "Ano de Lançamento" -> filmeSelecionado.getAnoLancamento();
            case "Duração" -> filmeSelecionado.getDuracao();
            case "Plataforma" -> filmeSelecionado.getPlataforma();
            case "Assistido" -> filmeSelecionado.isVisto() ? filmeSelecionado.getDataVisto() : "Não Assistido";
            default -> "";
        };

        txtDadoAtual.setText(dado);
    }

    /**
     * Altera o valor do atributo selecionado com o novo valor informado.
     * Valida os campos e exibe alertas de sucesso ou erro conforme o resultado.
     * Caso o atributo seja "Assistido", verifica o formato da data.
     */
    private void alterarDado() {
        if (filmeSelecionado == null || cbDados.getValue() == null || txtNovoDado.getText().isBlank()) {
            mostrarAlerta("Erro", "Preencha todos os campos antes de concluir.", Alert.AlertType.ERROR);
            return;
        }

        String novoValor = txtNovoDado.getText().trim();
        String atributo = cbDados.getValue();

        switch (atributo) {
            case "Título" -> filmeSelecionado.setTitulo(novoValor);
            case "Título Original" -> filmeSelecionado.setTituloOriginal(novoValor);
            case "Gênero" -> filmeSelecionado.setGenero(novoValor);
            case "Ano de Lançamento" -> filmeSelecionado.setAnoLancamento(novoValor);
            case "Duração" -> filmeSelecionado.setDuracao(novoValor);
            case "Plataforma" -> filmeSelecionado.setPlataforma(novoValor);
            case "Assistido" -> {
                if (!validarData(novoValor)) {
                    mostrarAlerta("Erro", "Digite a data no formato dd/MM/aaaa.", Alert.AlertType.ERROR);
                    return;
                }
                filmeSelecionado.alterarVisto(novoValor);
            }
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
     * Valida uma data no formato dd/MM/yyyy.
     *
     * @param data Data em formato de string
     * @return {@code true} se a data for válida, caso contrário {@code false}
     */
    private boolean validarData(String data) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        try {
            sdf.parse(data);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    /**
     * Abre a tela modal para alteração da equipe (atores, diretores, roteiristas) do filme.
     * Passa o filme selecionado para o controlador da nova tela.
     */
    private void abrirTelaEquipe() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pbl/view/telas/atualizacao/AtualizarEquipe.fxml"));
            Scene scene = new Scene(loader.load());

            AtualizarEquipeController controller = loader.getController();
            controller.setFilme(filmeSelecionado);

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Atualizar Equipe");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

        } catch (IOException e) {
            mostrarAlerta("Erro", "Erro ao abrir tela de equipe.", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    /**
     * Exibe uma janela de alerta com a mensagem informada.
     *
     * @param titulo   Título da janela
     * @param mensagem Conteúdo da mensagem
     * @param tipo     Tipo de alerta (informação, erro, etc.)
     */
    private void mostrarAlerta(String titulo, String mensagem, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }
}
