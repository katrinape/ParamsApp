package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.controller.MainController;
import sample.model.Datasource;
import java.util.Locale;
import java.util.ResourceBundle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Locale.setDefault(new Locale("en"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/fxml/main.fxml"));
        ResourceBundle bundle = ResourceBundle.getBundle("sample.bundles.messages");
        loader.setResources(bundle);
        Parent root = loader.load();
        MainController controller = loader.getController();
        controller.listParameters();
        primaryStage.setTitle(bundle.getString("app.title"));

        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    @Override
    public void init() throws Exception {
        super.init();
        if (!Datasource.getInstance().open()) {
            System.out.println("FATAL ERROR: Couldn't connect to database");
            Platform.exit();
        } else {
            Datasource.getInstance().createTable();
        }
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        Datasource.getInstance().close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
