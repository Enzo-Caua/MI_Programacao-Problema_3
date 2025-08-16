package pbl.view.telas.cadastro;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import pbl.model.obras.Temporada;

import java.util.ArrayList;

public class CadastrarTemporadasController {

    @FXML private TextField txtAno;
    @FXML private TextField txtQtdEpisodios;
    @FXML private Button btCadastrar;
    @FXML private Button btConcluir;
    @FXML private Button btVoltar;
    @FXML private ListView<String> lvTemporadas;

    private ObservableList<String> listaTemporadas = FXCollections.observableArrayList();
    private ArrayList<Temporada> temporadasCadastradas = new ArrayList<>();
    private int totalTemporadasEsperadas;
    private int contadorTemporadas = 1;

    public void inicializar(int totalTemporadas) {
        this.totalTemporadasEsperadas = totalTemporadas;
        lvTemporadas.setItems(listaTemporadas);
    }

    public ArrayList<Temporada> getTemporadasCadastradas() {
        return temporadasCadastradas;
    }

    @FXML
    private void cadastrarTemporada(ActionEvent event) {
        String ano = txtAno.getText().trim();
        String qtd = txtQtdEpisodios.getText().trim();

        if (ano.isEmpty() || qtd.isEmpty()) {
            mostrarAlerta("Campos obrigatórios", "Preencha todos os campos.", Alert.AlertType.WARNING);
            return;
        }

        try {
            int qtdInt = Integer.parseInt(qtd);

            if (contadorTemporadas > totalTemporadasEsperadas) {
                mostrarAlerta("Limite atingido", "Todas as temporadas já foram cadastradas.", Alert.AlertType.INFORMATION);
                return;
            }

            Temporada temporada = new Temporada(ano, qtdInt, contadorTemporadas);
            temporadasCadastradas.add(temporada);
            listaTemporadas.add("Temporada " + contadorTemporadas + " - " + ano + " - " + qtdInt + " episódios");

            contadorTemporadas++;
            txtAno.clear();
            txtQtdEpisodios.clear();

        } catch (NumberFormatException e) {
            mostrarAlerta("Erro de formato", "Ano e quantidade devem ser números válidos.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void concluirCadastro(ActionEvent event) {
        if (temporadasCadastradas.size() != totalTemporadasEsperadas) {
            mostrarAlerta("Cadastro incompleto", "Cadastre todas as temporadas antes de concluir.", Alert.AlertType.WARNING);
            return;
        }

        mostrarAlerta("Sucesso", "Série cadastrada com sucesso!", Alert.AlertType.INFORMATION);
        fecharJanela();
    }

    @FXML
    private void clickVoltar(ActionEvent event) {
        mostrarAlerta("Cadastro cancelado", "As temporadas não foram cadastradas.", Alert.AlertType.INFORMATION);
        fecharJanela();
    }

    private void fecharJanela() {
        Stage stage = (Stage) btVoltar.getScene().getWindow();
        stage.close();
    }

    private void mostrarAlerta(String titulo, String mensagem, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }
}
