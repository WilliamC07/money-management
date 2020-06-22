import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.nio.file.Path;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Database {
    private Connection connection;

    private final Path PROGRAM_DATA_PATH = Path.of(System.getProperty("user.home"), "MoneyManagement");
    private final String DATABASE_NAME = "database.sqlite";

    public Database() {
        try {
            createSystemFiles();
            System.out.println("ran this");
        }catch(IOException e){
            System.out.println("Failed to create needed files");
        }

        // Establish database connection
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + this.PROGRAM_DATA_PATH.resolve(this.DATABASE_NAME).toString());
        }catch(SQLException e){
            System.out.println("Failed to make database connection");
        }
    }

    private void createSystemFiles() throws IOException {
        // create needed directory
        File programDataDirectory = new File(this.PROGRAM_DATA_PATH.toString());
        if (!programDataDirectory.exists()) {
            programDataDirectory.mkdir();
            System.out.println("Created program directory");
        }

        // create the sqlite file
        File programSqliteFile = new File(this.PROGRAM_DATA_PATH.resolve(this.DATABASE_NAME).toString());
        if (programSqliteFile.createNewFile()) {
            System.out.println("Creatd database file");
        }

        // create a description file
        File programMetaFile = new File(this.PROGRAM_DATA_PATH.resolve("README.md").toString());
        if (programMetaFile.createNewFile()) {
            FileWriter writer = new FileWriter(programMetaFile);
            String github = "https://github.com/WilliamC07/money-management";
            writer.write("Storage for money management program created by William Cao. \n" + github);
            writer.close();
        }
    }
}