package budgeting;

import budgeting.Database;
import budgeting.balance.BalanceTypes;
import budgeting.components.NavBar;
import budgeting.balance.Balance;

import java.util.Map;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    private Database database;
    private Balance balance;

    @Override
    public void init() throws Exception{
        this.database = new Database();
        this.balance = new Balance(database.getBalanceInfo(BalanceTypes.CREDIT), database.getBalanceInfo(BalanceTypes.DEBIT), database.getBalanceInfo(BalanceTypes.CASH));
    }

    @Override
    public void start(Stage stage) {
        VBox container = new VBox();
        container.getChildren().add(new NavBar(this.balance));
        
        Scene scene = new Scene(container, 1280, 800);
        scene.getStylesheets().add(getClass().getClassLoader().getResource("style.css").toExternalForm());
        System.out.println(getClass().getClassLoader().getResource("style.css").toExternalForm());

        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.setTitle("MoneyManagement");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}