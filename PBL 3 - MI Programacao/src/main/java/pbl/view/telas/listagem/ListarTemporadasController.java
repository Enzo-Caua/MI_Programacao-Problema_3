package pbl.view.telas.listagem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import pbl.model.obras.Temporada;

import java.util.ArrayList;

public class ListarTemporadasController {

    @FXML
    private Button btVoltar;

    @FXML
    private ListView<String> lvTempCadastrada;

    @FXML
    private Label txtNome;

    private ObservableList<String> temporadas = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        lvTempCadastrada.setItems(temporadas);
    }

    public void setTemporadas(String nomeSerie, ArrayList<Temporada> temporadas) {
        txtNome.setText(nomeSerie);

        ObservableList<String> listaFormatada = FXCollections.observableArrayList();
        for (Temporada temp : temporadas) {
            String info = String.format("Temporada %d - %s - %d episódio(s)",
                    temp.getTempNumero(), temp.getAnoLancamento(), temp.getQntEpisodios());
            listaFormatada.add(info);
        }

        lvTempCadastrada.setItems(listaFormatada);
    }


    @FXML
    public void clickVoltar(ActionEvent event) {
        Stage stage = (Stage) btVoltar.getScene().getWindow();
        stage.close();
    }


}
