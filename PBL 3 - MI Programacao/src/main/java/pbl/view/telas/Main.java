package pbl.view.telas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import pbl.model.persistencia.PersistirAcervo;

import java.io.IOException;

/**
 * Classe principal da aplicação JavaFX.
 * Responsável por inicializar a interface gráfica, carregar o acervo persistido
 * e gerenciar a troca de telas por meio do método {@code trocarTelas}.
 */
public class Main extends Application {

    /** Janela principal da aplicação */
    private static Stage stage;

    /**
     * Método principal que inicia a aplicação.
     * Também realiza a desserialização do acervo persistido.
     *
     * @param args Argumentos de linha de comando.
     */
    public static void main(String[] args) {
        // Carrega o acervo salvo
        PersistirAcervo.deserializarAcervo();
        launch(args);
    }

    /**
     * Inicializa a aplicação JavaFX.
     *
     * @param primaryStage Janela principal fornecida pelo JavaFX.
     */
    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;

        Image iconeLogo = new Image(getClass().getResourceAsStream("/pbl/view/img/IconeLogo.png"));
        stage.getIcons().add(iconeLogo);
        stage.setResizable(false);
        stage.setTitle("Diário Cultural by JE");

        trocarTelas("Main");
    }

    /**
     * Troca a tela da aplicação com base no nome fornecido.
     * O nome deve corresponder ao nome do arquivo FXML (sem caminho completo).
     * O método busca o arquivo FXML no diretório correspondente, de acordo com o prefixo:
     * <ul>
     *     <li>{@code Menu} → /menus/</li>
     *     <li>{@code Buscar} → /busca/</li>
     *     <li>{@code Cadastrar} → /cadastro/</li>
     *     <li>{@code Listar} → /listagem/</li>
     *     <li>{@code Atualizar} → /atualizacao/</li>
     *     <li>{@code Avaliar} → /avaliacao/</li>
     *     <li>Demais → raiz de /telas/</li>
     * </ul>
     *
     * @param nomeTela Nome da tela (sem extensão e sem caminho).
     */
    public static void trocarTelas(String nomeTela) {
        try {
            String caminho = "/pbl/view/telas/";

            if (nomeTela.startsWith("Menu")) {
                caminho += "menus/" + nomeTela + ".fxml";
            } else if (nomeTela.startsWith("Buscar")) {
                caminho += "busca/" + nomeTela + ".fxml";
            } else if (nomeTela.startsWith("Cadastrar")) {
                caminho += "cadastro/" + nomeTela + ".fxml";
            } else if (nomeTela.startsWith("Listar")) {
                caminho += "listagem/" + nomeTela + ".fxml";
            } else if (nomeTela.startsWith("Atualizar")) {
                caminho += "atualizacao/" + nomeTela + ".fxml";
            } else if (nomeTela.startsWith("Avaliar")) {
                caminho += "avaliacao/" + nomeTela + ".fxml";
            } else {
                caminho += nomeTela + ".fxml"; // para Main.fxml ou telas livres
            }

            FXMLLoader loader = new FXMLLoader(Main.class.getResource(caminho));
            Scene novaCena = new Scene(loader.load(), 880, 550);
            stage.setScene(novaCena);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Erro ao carregar a tela: " + nomeTela);
        }
    }

    /**
     * Retorna a janela principal da aplicação.
     *
     * @return {@code Stage} atual da aplicação.
     */
    public static Stage getStage() {
        return stage;
    }
}
