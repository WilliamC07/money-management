import org.junit.jupiter.api.*;


import budgeting.util.FormatCurrency;

@DisplayName("Testing formatting cash")
public class FormatCurrencyTest{
    
    @Test
    @DisplayName("Testing formatting currency")
    void testFormat(){
        Assertions.assertEquals("1.00", FormatCurrency.format(100));
        Assertions.assertEquals("10.00", FormatCurrency.format(1000));
        Assertions.assertEquals("1.23", FormatCurrency.format(123));
        Assertions.assertEquals("0.02", FormatCurrency.format(2));
        Assertions.assertEquals("0.30", FormatCurrency.format(30));
    }

}