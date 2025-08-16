package pbl.view.telas.avaliacao;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

/**
 * Controlador para a tela de avaliação por estrelas.
 * Permite ao usuário selecionar uma nota de 0 a 5 estrelas para um item.
 */
public class PontuarController {

    /** Botão estrela 1 */
    @FXML private ToggleButton star1;

    /** Botão estrela 2 */
    @FXML private ToggleButton star2;

    /** Botão estrela 3 */
    @FXML private ToggleButton star3;

    /** Botão estrela 4 */
    @FXML private ToggleButton star4;

    /** Botão estrela 5 */
    @FXML private ToggleButton star5;

    /** Campo de texto que mostra o título do item avaliado */
    @FXML private TextField txtTitulo;

    /** Array que agrupa as estrelas para manipulação */
    private ToggleButton[] stars;

    /** Nota atual selecionada, variando entre 0 e 5 */
    private int nota = 0;

    /**
     * Inicializa o controlador, configurando o array de estrelas
     * e definindo o estado inicial (todas vazias).
     */
    @FXML
    public void initialize() {
        stars = new ToggleButton[]{star1, star2, star3, star4, star5};
        updateStars(0);
    }

    /**
     * Define o título do item a ser avaliado na interface.
     * @param titulo título do filme, livro, temporada etc.
     */
    public void setTitulo(String titulo) {
        txtTitulo.setText(titulo);
    }

    /**
     * Retorna a nota selecionada pelo usuário.
     * @return inteiro de 0 a 5 representando as estrelas selecionadas
     */
    public int getNota() {
        return nota;
    }

    /**
     * Manipula o evento de clique em uma estrela.
     * Caso a primeira estrela esteja selecionada e seja clicada novamente,
     * desmarca todas (nota 0).
     * @param event evento do clique na estrela
     */
    @FXML
    public void handleRating(ActionEvent event) {
        ToggleButton clickedStar = (ToggleButton) event.getSource();
        int clickedIndex = -1;

        for (int i = 0; i < stars.length; i++) {
            if (stars[i] == clickedStar) {
                clickedIndex = i;
                break;
            }
        }

        if (clickedIndex == 0 && nota == 1) {
            nota = 0;
        } else {
            nota = clickedIndex + 1;
        }

        updateStars(nota);
        System.out.println("Avaliação: " + nota + " estrela(s)");
    }

    /**
     * Atualiza a interface gráfica das estrelas,
     * marcando as selecionadas e atualizando o texto.
     * @param rating número de estrelas selecionadas (0 a 5)
     */
    private void updateStars(int rating) {
        for (int i = 0; i < stars.length; i++) {
            String simbolo = i < rating ? "★" : "☆";
            stars[i].setText(simbolo + " " + (i + 1));
            stars[i].setSelected(i < rating);
        }
    }

    /**
     * Evento chamado ao concluir a pontuação.
     * Valida se alguma nota foi selecionada antes de fechar a janela.
     * @param event evento do clique no botão concluir
     */
    @FXML
    private void concluirPontuacao(ActionEvent event) {
        if (nota == 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Avaliação não realizada");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, selecione uma nota antes de concluir.");
            alert.showAndWait();
            return;
        }

        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    /**
     * Fecha a janela sem salvar avaliação.
     * @param event evento do clique no botão voltar
     */
    @FXML
    private void voltar(ActionEvent event) {
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

}
