package pbl.view.telas.cadastro;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import pbl.controller.cadastro.CadastrarLivro;
import pbl.view.telas.Main;

import java.util.ArrayList;
import java.util.List;

public class CadastrarLivrosController {

    @FXML private RadioButton marcaNao;
    @FXML private RadioButton marcaSim;
    @FXML private TextField txtAno;
    @FXML private TextField txtAutor;
    @FXML private TextField txtEditora;
    @FXML private TextField txtGenero;
    @FXML private TextField txtISBN;
    @FXML private TextField txtTitulo;
    @FXML private TextField txtTituloOrig;

    public void clickConcluir() {
        if (validarCampos()) {
            List<String> dados = new ArrayList<>();

            dados.add(txtTitulo.getText());
            dados.add(txtTituloOrig.getText());
            dados.add(txtGenero.getText());
            dados.add(txtAno.getText());
            dados.add(txtAutor.getText());
            dados.add(txtEditora.getText());
            dados.add(txtISBN.getText());

            String disponibilidade = marcaSim.isSelected() ? "Sim" : "Não";
            dados.add(disponibilidade);

            try {
                CadastrarLivro.dadosLivro(dados);
                Main.trocarTelas("MenuCadastrar");
            } catch (Exception e) {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Erro ao cadastrar");
                alerta.setHeaderText("Ocorreu um erro ao cadastrar o livro.");
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
                txtAutor.getText().trim().isEmpty() ||
                txtEditora.getText().trim().isEmpty() ||
                txtISBN.getText().trim().isEmpty() ||
                (!marcaSim.isSelected() && !marcaNao.isSelected())) {

            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Campos obrigatórios");
            alerta.setHeaderText(null);
            alerta.setContentText("Por favor, preencha todos os campos e selecione a disponibilidade.");
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
