import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    Database database = new Database();

    @Override
    public void start(Stage stage) {
        VBox container = new VBox();
        container.getChildren().add(new Label("asd"));
        
        Scene scene = new Scene(container, 1280, 800);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.setTitle("MoneyManagement");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}