package com.mylibrary;

import com.mylibrary.database.dbutils.DbManager;
import com.mylibrary.utils.FxmlUtils;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.Objects;

public class Main extends Application {

    public static final String MAIN_FXML = "/fxml/Main.fxml";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Locale.setDefault(new Locale("en"));
        stage.setScene(new Scene(Objects.requireNonNull(FxmlUtils.fxmlLoader(MAIN_FXML))));
        stage.setTitle(FxmlUtils.getResourceBundle().getString("title_application"));
        stage.show();

        DbManager.initDatabase();
    }
}