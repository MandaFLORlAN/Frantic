package JavaFxInterface;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class JavaFxVersion extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/HomeScreen.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().
                getResource("/styles.css")).toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Frantic");
        stage.show();
    }

/*    public static void main(String[] args) {
        launch(args);
    }*/
}
