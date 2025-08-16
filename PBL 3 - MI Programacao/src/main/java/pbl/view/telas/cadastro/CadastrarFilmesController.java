package pbl.view.telas.cadastro;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import pbl.controller.cadastro.CadastrarFilme;
import pbl.view.telas.Main;

import java.util.ArrayList;
import java.util.List;

public class CadastrarFilmesController {

    @FXML private ListView<String> lvAtores;
    @FXML private ListView<String> lvDiretores;
    @FXML private ListView<String> lvRoteiristas;

    @FXML private TextField txtAno;
    @FXML private TextField txtAtores;
    @FXML private TextField txtDiretores;
    @FXML private TextField txtDuracao;
    @FXML private TextField txtGenero;
    @FXML private TextField txtPlataforma;
    @FXML private TextField txtRoteiristas;
    @FXML private TextField txtTitulo;
    @FXML private TextField txtTituloOrig;

    private ObservableList<String> listaAtores = FXCollections.observableArrayList();
    private ObservableList<String> listaDiretores = FXCollections.observableArrayList();
    private ObservableList<String> listaRoteiristas = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        lvAtores.setItems(listaAtores);
        lvDiretores.setItems(listaDiretores);
        lvRoteiristas.setItems(listaRoteiristas);
    }

    @FXML
    public void adicionarAtor() {
        String ator = txtAtores.getText().trim();
        if (!ator.isEmpty()) {
            listaAtores.add(ator);
            txtAtores.clear();
        }
    }

    @FXML
    public void adicionarDiretor() {
        String diretor = txtDiretores.getText().trim();
        if (!diretor.isEmpty()) {
            listaDiretores.add(diretor);
            txtDiretores.clear();
        }
    }

    @FXML
    public void adicionarRoteirista() {
        String roteirista = txtRoteiristas.getText().trim();
        if (!roteirista.isEmpty()) {
            listaRoteiristas.add(roteirista);
            txtRoteiristas.clear();
        }
    }

    public void clickConcluir() {
        if (validarCampos()) {
            List<String> dadosPrincipais = new ArrayList<>();
            dadosPrincipais.add(txtTitulo.getText().trim());          // [0]
            dadosPrincipais.add(txtTituloOrig.getText().trim());      // [1]
            dadosPrincipais.add(txtGenero.getText().trim());          // [2]
            dadosPrincipais.add(txtAno.getText().trim());             // [3]
            dadosPrincipais.add(txtPlataforma.getText().trim());      // [4]
            dadosPrincipais.add(txtDuracao.getText().trim());         // [5]

            List<String> diretores = new ArrayList<>(listaDiretores);
            List<String> roteiristas = new ArrayList<>(listaRoteiristas);
            List<String> atores = new ArrayList<>(listaAtores);

            try {
                CadastrarFilme.dadosFilme(dadosPrincipais, diretores, roteiristas, atores);
                Main.trocarTelas("MenuCadastrar");
            } catch (Exception e) {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Erro ao cadastrar filme");
                alerta.setHeaderText("Falha ao tentar cadastrar o filme.");
                alerta.setContentText(e.getMessage());
                alerta.showAndWait();
            }
        }
    }

    private boolean validarCampos() {
        if (txtTitulo.getText().trim().isEmpty() ||
                txtTituloOrig.getText().trim().isEmpty() ||
                txtGenero.getText().trim().isEmpty() ||
                txtAno.getText().trim().isEmpty() ||
                txtDuracao.getText().trim().isEmpty() ||
                txtPlataforma.getText().trim().isEmpty() ||
                listaAtores.isEmpty() ||
                listaDiretores.isEmpty() ||
                listaRoteiristas.isEmpty()) {

            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Campos obrigatórios");
            alerta.setHeaderText(null);
            alerta.setContentText("Por favor, preencha todos os campos e adicione pelo menos um nome em cada lista.");
            alerta.showAndWait();
            return false;
        }

        return true;
    }

    @FXML
    protected void clickVoltar(ActionEvent event) {
        Main.trocarTelas("MenuCadastrar");
    }
}
