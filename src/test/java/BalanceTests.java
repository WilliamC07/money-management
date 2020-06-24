import org.junit.jupiter.api.*;


import budgeting.Database;
import budgeting.balance.BalanceTypes;
import budgeting.Environment;
import budgeting.Environment.EnvironmentMode;;

@DisplayName("Testing balance (credit, debit, cash)")
public class BalanceTests{
    private Database database;

    @BeforeAll
    static void setup(){
        Environment.mode = EnvironmentMode.DEV;
    }
    
    @BeforeEach
    void init(){
        database = new Database();
    }

    @Test
    void testInitialBalance(){
        try {
            for(BalanceTypes type : BalanceTypes.values()){
                Assertions.assertEquals(0, database.getBalanceInfo(type));
            }
        }catch(Exception e){
            Assertions.fail("SQL Error:\n" + e.getMessage());
        }
    }

}