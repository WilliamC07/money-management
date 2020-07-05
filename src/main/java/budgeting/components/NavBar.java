package budgeting.components;

import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.beans.value.ObservableValue;

import budgeting.balance.Balance;
import budgeting.util.FormatCurrency;

public class NavBar extends HBox{
    private final Text creditText = new Text();
    private final Text debitText = new Text();
    private final Text cashText = new Text();

    public NavBar(Balance balance){
        // css
        this.setId("nav-bar");

        // add to view
        this.getChildren().addAll(creditText, debitText, cashText);

        // initialize text
        creditText.setText("Credit: $" + FormatCurrency.format(balance.getCredit()));
        debitText.setText("Debit: $" + FormatCurrency.format(balance.getDebit()));
        cashText.setText("Cash: $" + FormatCurrency.format(balance.getCash()));

        // listeners for balance change
        balance.cashProperty().addListener((ObservableValue<? extends Number> o, Number oldValue, Number newValue) -> {
            creditText.setText(String.format("Credit: $%d", FormatCurrency.format(newValue.intValue())));
        });
        balance.debitProperty().addListener((ObservableValue<? extends Number> o, Number oldValue, Number newValue) -> {
            debitText.setText(String.format("Debit: $%d", FormatCurrency.format(newValue.intValue())));
        });
        balance.cashProperty().addListener((ObservableValue<? extends Number> o, Number oldValue, Number newValue) -> {
            cashText.setText(String.format("Cash: $%d", FormatCurrency.format(newValue.intValue())));
        });
    }
}
