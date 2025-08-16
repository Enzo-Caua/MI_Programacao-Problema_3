package pbl.view.telas.atualizacao;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import pbl.model.obras.Acervo;
import pbl.model.obras.Livro;
import pbl.model.persistencia.Serializacao;
import pbl.view.telas.Main;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class AtualizarLivroController implements Initializable {

    @FXML private ComboBox<String> cbLivro;
    @FXML private ComboBox<String> cbDados;
    @FXML private TextField txtDadoAtual;
    @FXML private TextField txtNovoDado;
    @FXML private Button btVoltar;
    @FXML private Button btConcluir;

    private Livro livroSelecionado;

    private final List<String> atributosLivro = List.of(
            "Título", "Título Original", "Gênero", "Ano de Lançamento",
            "Autor", "Editora", "ISBN", "Disponível", "Leitura"
    );

    private final DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Popula os ComboBoxes
        List<String> titulos = new ArrayList<>();
        for (Livro livro : Acervo.getAcervo().getLivros()) {
            titulos.add(livro.getTitulo());
        }
        cbLivro.setItems(FXCollections.observableArrayList(titulos));
        cbDados.setItems(FXCollections.observableArrayList(atributosLivro));

        // Eventos para atualizar o dado atual
        cbLivro.setOnAction(event -> atualizarDadoAtual());
        cbDados.setOnAction(event -> atualizarDadoAtual());
    }

    private void atualizarDadoAtual() {
        String tituloSelecionado = cbLivro.getValue();
        String dadoSelecionado = cbDados.getValue();

        if (tituloSelecionado == null || dadoSelecionado == null) {
            txtDadoAtual.setText("");
            return;
        }

        // Busca o livro selecionado
        livroSelecionado = null;
        for (Livro l : Acervo.getAcervo().getLivros()) {
            if (l.getTitulo().equals(tituloSelecionado)) {
                livroSelecionado = l;
                break;
            }
        }

        if (livroSelecionado == null) {
            txtDadoAtual.setText("");
            return;
        }

        // Mostra o valor atual no campo
        String valorAtual = switch (dadoSelecionado) {
            case "Título" -> livroSelecionado.getTitulo();
            case "Título Original" -> livroSelecionado.getTituloOriginal();
            case "Gênero" -> livroSelecionado.getGenero();
            case "Ano de Lançamento" -> livroSelecionado.getAnoLancamento();
            case "Autor" -> livroSelecionado.getAutor();
            case "Editora" -> livroSelecionado.getEditora();
            case "ISBN" -> livroSelecionado.getIsbn();
            case "Disponível" -> livroSelecionado.isDisponivel() ? "Sim" : "Não";
            case "Leitura" -> livroSelecionado.isVisto() ? livroSelecionado.getDataVisto() : "Não Lido";
            default -> "";
        };

        txtDadoAtual.setText(valorAtual);
    }

    @FXML
    private void concluirAlteracao() {
        if (livroSelecionado == null || cbDados.getValue() == null) {
            mostrarAlerta("Erro", "Selecione um livro e um dado para alterar.", Alert.AlertType.ERROR);
            return;
        }

        String novoDado = txtNovoDado.getText().trim();
        if (novoDado.isEmpty()) {
            mostrarAlerta("Erro", "Digite o novo valor.", Alert.AlertType.ERROR);
            return;
        }

        String dadoSelecionado = cbDados.getValue();

        switch (dadoSelecionado) {
            case "Título" -> livroSelecionado.setTitulo(novoDado);
            case "Título Original" -> livroSelecionado.setTituloOriginal(novoDado);
            case "Gênero" -> livroSelecionado.setGenero(novoDado);
            case "Ano de Lançamento" -> livroSelecionado.setAnoLancamento(novoDado);
            case "Autor" -> livroSelecionado.setAutor(novoDado);
            case "Editora" -> livroSelecionado.setEditora(novoDado);
            case "ISBN" -> livroSelecionado.setIsbn(novoDado);
            case "Disponível" -> {
                String val = novoDado.toLowerCase();
                if (val.equals("sim") || val.equals("true")) {
                    livroSelecionado.setDisponivel(true);
                } else if (val.equals("nao") || val.equals("não") || val.equals("false")) {
                    livroSelecionado.setDisponivel(false);
                } else {
                    mostrarAlerta("Erro", "Digite 'Sim' ou 'Não' para o campo Disponível.", Alert.AlertType.ERROR);
                    return;
                }
            }
            case "Leitura" -> {
                try {
                    // Valida o formato da data
                    LocalDate.parse(novoDado, formatoData);
                    // Chama o método alterarVisto da classe Livro passando a string
                    String resultado = livroSelecionado.alterarVisto(novoDado);
                    if (!resultado.equals(novoDado)) {
                        mostrarAlerta("Aviso", "O livro já foi marcado como visto anteriormente na data: " + resultado, Alert.AlertType.INFORMATION);
                    }
                } catch (DateTimeParseException e) {
                    mostrarAlerta("Erro", "Data inválida! Use o formato dd/mm/aaaa.", Alert.AlertType.ERROR);
                    return;
                }
            }
            default -> {
                mostrarAlerta("Erro", "Atributo desconhecido.", Alert.AlertType.ERROR);
                return;
            }
        }

        // Atualiza visual e salva
        txtDadoAtual.setText(novoDado);
        txtNovoDado.clear();
        Serializacao.salvarLivro(Acervo.getAcervo().getLivros());

        mostrarAlerta("Sucesso", "Dado atualizado com sucesso!", Alert.AlertType.INFORMATION);
    }

    @FXML
    private void voltarAoMenu() {
        Main.trocarTelas("MenuAtualizar");
    }

    private void mostrarAlerta(String titulo, String mensagem, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
