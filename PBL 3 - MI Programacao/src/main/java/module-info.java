module pbl {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    // Permite que o JavaFX acesse os controladores com @FXML
    opens pbl.view.telas to javafx.fxml;
    opens pbl.view.telas.menus to javafx.fxml;
    opens pbl.view.telas.cadastro to javafx.fxml;
    opens pbl.view.telas.listagem to javafx.fxml;
    opens pbl.view.telas.busca to javafx.fxml;
    opens pbl.view.telas.atualizacao to javafx.fxml;
    opens pbl.view.telas.avaliacao to javafx.fxml;

    // Exporta seus pacotes públicos
    exports pbl.view.telas;
}
