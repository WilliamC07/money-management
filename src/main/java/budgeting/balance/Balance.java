package budgeting.balance;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public final class Balance {
    private final IntegerProperty credit = new SimpleIntegerProperty();
    private final IntegerProperty debit = new SimpleIntegerProperty();
    private final IntegerProperty cash = new SimpleIntegerProperty();

    public Balance(int credit, int debit, int cash){
        this.credit.set(credit);
        this.debit.set(debit);
        this.cash.set(cash);
    }

    public void setCredit(int value){
        this.credit.set(value);
    }
    public int getCredit(){
        return this.credit.get();
    }
    public IntegerProperty creditProperty(){
        return this.credit;
    }

    public void setDebit(int value){
        this.debit.set(value);
    }
    public int getDebit(){
        return this.debit.get();
    }
    public IntegerProperty debitProperty(){
        return this.debit;
    }
    
    public void setCash(int value){
        this.cash.set(value);
    }
    public int getCash(){
        return this.cash.get();
    }
    public IntegerProperty cashProperty(){
        return this.cash;
    }
}