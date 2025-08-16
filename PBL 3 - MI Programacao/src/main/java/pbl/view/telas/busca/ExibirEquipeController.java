package pbl.view.telas.busca;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.List;

public class ExibirEquipeController {

    /** Botão para fechar a janela. */
    @FXML private Button btVoltar;
    /** ComboBox para selecionar o cargo (Atores, Diretores, Roteiristas). */
    @FXML private ComboBox<String> cbCargo;
    /** ListView para exibir os nomes da equipe conforme o cargo selecionado. */
    @FXML private ListView<String> lvNomes;

    /** Lista de nomes dos atores. */
    private List<String> atores;
    /** Lista de nomes dos diretores. */
    private List<String> diretores;
    /** Lista de nomes dos roteiristas. */
    private List<String> roteiristas;

    /**
     * Recebe as listas com os nomes da equipe e inicializa a interface.
     * Configura o ComboBox e exibe inicialmente os atores.
     * @param atores Lista de atores.
     * @param diretores Lista de diretores.
     * @param roteiristas Lista de roteiristas.
     */
    public void setDados(List<String> atores, List<String> diretores, List<String> roteiristas) {
        this.atores = atores;
        this.diretores = diretores;
        this.roteiristas = roteiristas;

        cbCargo.setItems(FXCollections.observableArrayList("Atores", "Diretores", "Roteiristas"));
        cbCargo.getSelectionModel().selectFirst();
        atualizarListView("Atores");

        cbCargo.setOnAction(e -> atualizarListView(cbCargo.getValue()));
    }

    /**
     * Atualiza a ListView de nomes conforme o cargo selecionado no ComboBox.
     * @param cargoSelecionado Cargo selecionado ("Atores", "Diretores" ou "Roteiristas").
     */
    private void atualizarListView(String cargoSelecionado) {
        ObservableList<String> nomes = switch (cargoSelecionado) {
            case "Atores" -> FXCollections.observableArrayList(atores);
            case "Diretores" -> FXCollections.observableArrayList(diretores);
            case "Roteiristas" -> FXCollections.observableArrayList(roteiristas);
            default -> FXCollections.observableArrayList();
        };
        lvNomes.setItems(nomes);
    }

    /**
     * Fecha a janela atual ao clicar no botão Voltar.
     * @param event Evento de clique no botão.
     */
    @FXML
    void clickVoltar(ActionEvent event) {
        Stage stage = (Stage) btVoltar.getScene().getWindow();
        stage.close();
    }
}
