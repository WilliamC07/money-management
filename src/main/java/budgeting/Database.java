package budgeting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import budgeting.Environment;
import budgeting.Environment.EnvironmentMode;
import budgeting.balance.BalanceTypes;

import java.nio.file.Path;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Tables:
 * 1. Balance
 * 2. 
 * 
 * 
 * 
 */
public class Database {
    private Connection connection;

    private final Path PROGRAM_DATA_PATH = Path.of(System.getProperty("user.home"), "MoneyManagement");
    private final String DATABASE_NAME = "database.sqlite";

    public Database() {
        // default to true for when mode is "dev"
        boolean needToPrepareDatabase = true;

        if(Environment.mode == EnvironmentMode.PRODUCTION){
            try {
                needToPrepareDatabase = createSystemFiles();
            }catch(IOException e){
                System.out.println("Failed to create needed files");
            }
        }

        // Establish database connection
        try {
            if(Environment.mode == EnvironmentMode.PRODUCTION){
                connection = DriverManager.getConnection("jdbc:sqlite:" + this.PROGRAM_DATA_PATH.resolve(this.DATABASE_NAME).toString());
            }else{
                connection = DriverManager.getConnection("jdbc:sqlite::memory:");
            }

            if(needToPrepareDatabase){
                prepareDatabase();
            }
        }catch(SQLException e){
            System.out.println("Failed to make database command: " + e.getMessage());
        }
    }

    /**
     * Creates all the needed files and directory for the program to store data.
     * Created directory:
     * <home directory/{@link Database#PROGRAM_DATA_PATH}
     * 
     * Created Files:
     * Database file: {@link Database#DATABASE_NAME}
     * README.md
     * 
     * @return True if created database file, false otherwise
     * @throws IOException IOException from creating any needed files/directory or writing to README.md
     * */
    private boolean createSystemFiles() throws IOException {
        boolean createdDatabaseFile = false;

        // create needed directory
        File programDataDirectory = new File(this.PROGRAM_DATA_PATH.toString());
        if (!programDataDirectory.exists()) {
            programDataDirectory.mkdir();
            System.out.println("Created program directory");
        }

        // create the sqlite file
        File programSqliteFile = new File(this.PROGRAM_DATA_PATH.resolve(this.DATABASE_NAME).toString());
        if (programSqliteFile.createNewFile()) {
            createdDatabaseFile = true;
            System.out.println("Created database file");
        }

        // create a description file
        File programMetaFile = new File(this.PROGRAM_DATA_PATH.resolve("README.md").toString());
        if (programMetaFile.createNewFile()) {
            FileWriter writer = new FileWriter(programMetaFile);
            String github = "https://github.com/WilliamC07/money-management";
            writer.write("Storage for money management program created by William Cao. \n" + github);
            writer.close();
        }

        return createdDatabaseFile;
    }

    /**
     * Create all the needed tables, and initial values for database.
     */
    private void prepareDatabase() throws SQLException{
        // store cash, debit, and credit
        connection.createStatement().execute("CREATE TABLE Balance (type text PRIMARY KEY, value INTEGER);");
        
        // set initial values of balance to 0
        for(BalanceTypes type : BalanceTypes.values()){
            String statement = String.format("INSERT INTO BALANCE VALUES (\"%s\", 0);", type);
            System.out.println(statement);
            connection.createStatement().execute(statement);
        }
    }

    public int getBalanceInfo(BalanceTypes type) throws SQLException{
        String statement = "SELECT value FROM Balance WHERE type = \"" + type + "\";";
        return connection.createStatement().executeQuery(statement).getInt(1);
    }
}