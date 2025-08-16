package pbl.view.telas.cadastro;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pbl.controller.cadastro.CadastrarSerie;
import pbl.model.obras.Temporada;
import pbl.view.telas.Main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CadastrarSeriesController {

    @FXML private TextField txtTitulo, txtTituloOrig, txtGenero, txtAno, txtPlataforma, txtNTemporadas;
    @FXML private TextField txtAtores, txtDiretores, txtRoteiristas;
    @FXML private ListView<String> lvAtores, lvDiretores, lvRoteiristas;

    private List<String> listaAtores = new ArrayList<>();
    private List<String> listaDiretores = new ArrayList<>();
    private List<String> listaRoteiristas = new ArrayList<>();

    @FXML
    public void adicionarAtor() {
        String ator = txtAtores.getText().trim();
        if (!ator.isEmpty()) {
            listaAtores.add(ator);
            txtAtores.clear();
            lvAtores.getItems().setAll(listaAtores); // Atualiza a ListView
        }
    }

    @FXML
    public void adicionarDiretor() {
        String diretor = txtDiretores.getText().trim();
        if (!diretor.isEmpty()) {
            listaDiretores.add(diretor);
            txtDiretores.clear();
            lvDiretores.getItems().setAll(listaDiretores); // Atualiza a ListView
        }
    }

    @FXML
    public void adicionarRoteirista() {
        String roteirista = txtRoteiristas.getText().trim();
        if (!roteirista.isEmpty()) {
            listaRoteiristas.add(roteirista);
            txtRoteiristas.clear();
            lvRoteiristas.getItems().setAll(listaRoteiristas); // Atualiza a ListView
        }
    }

    @FXML
    void clickConcluir(ActionEvent event) {
        if (!validarCampos()) return;

        try {
            int numTemporadas = Integer.parseInt(txtNTemporadas.getText().trim());

            if (numTemporadas < 1) {
                mostrarAlerta("Erro", "Número de temporadas deve ser maior que zero.", Alert.AlertType.ERROR);
                return;
            }

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pbl/view/telas/cadastro/CadastrarTemporadas.fxml"));
            AnchorPane root = loader.load();

            CadastrarTemporadasController controller = loader.getController();
            controller.inicializar(numTemporadas);

            Stage stage = new Stage();
            stage.setTitle("Cadastro de Temporadas");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.showAndWait();

            ArrayList<Temporada> temporadasCadastradas = controller.getTemporadasCadastradas();

            // NOVO: Verifica se todas as temporadas foram cadastradas
            if (temporadasCadastradas.size() != numTemporadas) {
                mostrarAlerta("Cadastro incompleto",
                        "Você não cadastrou todas as temporadas. Série não cadastrada.",
                        Alert.AlertType.WARNING);
                return;  // interrompe o cadastro da série
            }

            List<String> dadosSerie = List.of(
                    txtTitulo.getText().trim(),
                    txtTituloOrig.getText().trim(),
                    txtGenero.getText().trim(),
                    txtAno.getText().trim(),
                    txtPlataforma.getText().trim()
            );

            CadastrarSerie.dadosSerie(
                    dadosSerie,
                    new ArrayList<>(listaDiretores),
                    new ArrayList<>(listaRoteiristas),
                    new ArrayList<>(listaAtores),
                    numTemporadas,
                    temporadasCadastradas
            );

            mostrarAlerta("Sucesso", "Série cadastrada com sucesso!", Alert.AlertType.INFORMATION);

            Main.trocarTelas("MenuCadastrar");

        } catch (NumberFormatException e) {
            mostrarAlerta("Erro", "Número de temporadas inválido.", Alert.AlertType.ERROR);
        } catch (IOException e) {
            mostrarAlerta("Erro", "Erro ao abrir a janela de cadastro de temporadas.", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }


    @FXML
    void clickVoltar(ActionEvent event) {
        Main.trocarTelas("MenuCadastrar");
    }

    private boolean validarCampos() {
        if (txtTitulo.getText().trim().isEmpty() ||
                txtTituloOrig.getText().trim().isEmpty() ||
                txtGenero.getText().trim().isEmpty() ||
                txtAno.getText().trim().isEmpty() ||
                txtPlataforma.getText().trim().isEmpty() ||
                txtNTemporadas.getText().trim().isEmpty() ||
                listaAtores.isEmpty() || listaDiretores.isEmpty() || listaRoteiristas.isEmpty()) {

            mostrarAlerta("Erro","Preencha todos os campos e adicione pelo menos um ator, diretor e roteirista.", Alert.AlertType.ERROR);
            return false;
        }
        return true;
    }

    private void mostrarAlerta(String titulo, String mensagem, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }


}
