import java.sql.SQLClientInfoException;
import java.sql.SQLException;

/**
 * Created by simon.knott on 01.06.2018.
 */
public class DB {
    static DatabaseConnector connector;

    public static void setup(String ip, int port, String database, String username, String passwort) throws SQLException {
        connector = new DatabaseConnector(ip, port, database, username, passwort);
        String errorMessage = connector.getErrorMessage();
        if (errorMessage != null) {
            throw new SQLException(errorMessage);
        }
    }

    public static String queryAndFormat(String query, String... args) throws SQLException {
        return format(query(query, args));
    }

    public static QueryResult query(String query, String... args) throws SQLException {
        exec(query, args);

        return connector.getCurrentQueryResult();
    }

    public static void exec(String statement, String... args) throws SQLException {
        String formattedStatement = String.format(statement, args);
        connector.executeStatement(formattedStatement);

        String errorMessage = connector.getErrorMessage();
        if (errorMessage != null) {
            throw new SQLException(errorMessage);
        }
    }

    private static String format(QueryResult queryResult) {
        StringBuilder result = new StringBuilder();

        for (String column : queryResult.getColumnNames()) {
            result.append(column);
            result.append('\t');
        }
        result.append('\n');
        for (String[] row : queryResult.getData()) {
            for (String attribute : row) {
                result.append(attribute);
                result.append('\t');
            }
            result.append('\n');
        }

        return result.toString();
    }
}
