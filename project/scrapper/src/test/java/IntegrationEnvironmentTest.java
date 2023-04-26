import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.core.PostgresDatabase;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.DirectoryResourceAccessor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IntegrationEnvironmentTest extends IntegrationEnvironment {
    private static final String INSERT = "INSERT INTO chats(id, last_command) VALUES (1, '/start')";
    private static final String LAST_COMMAND = "/start";
    private static final String SELECT = "SELECT last_command FROM chats WHERE id = 1";
    private static final String CHANGE_LOG_FILE = "master.xml";
    private static final String MIGRATIONS_DIRECTORY_NAME = "migrations";
    private static final int COLUMN_INDEX = 1;
    @Test
    public void testLiquibaseOnChatsTable() {
        Path path = Paths.get("").toAbsolutePath().getParent().resolve(MIGRATIONS_DIRECTORY_NAME);

        try (Connection connection = DriverManager.getConnection(
                getJdbcUrl(), getUsername(), getPassword())) {
            Database database = new PostgresDatabase();
            database.setConnection(new JdbcConnection(connection));
            Liquibase liquibase = new Liquibase(
                    CHANGE_LOG_FILE, new DirectoryResourceAccessor(path), database);
            liquibase.update(new Contexts(), new LabelExpression());
            try (PreparedStatement stmt = connection.prepareStatement(INSERT)) {
                stmt.executeUpdate();
            }

            try (PreparedStatement stmt = connection.prepareStatement(SELECT)) {
                try (ResultSet rs = stmt.executeQuery()) {
                    rs.next();
                    String result = rs.getString(COLUMN_INDEX);
                    assertEquals(LAST_COMMAND, result);
                }
            }
            liquibase.close();
        } catch (SQLException | LiquibaseException | FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
