import java.sql.SQLException;

/**
 * Created by simon.knott on 01.06.2018.
 */
public class AbimottoDB {
    public static boolean isRegisteredUser(String uid) throws SQLException {
        QueryResult r = DB.query("SELECT COUNT(*) FROM user WHERE uid = '%s'", uid);
        return relationToCount(r) > 0;
    }

    public static boolean userAlreadyExists(String vorname, String nachname) throws SQLException {
        QueryResult r = DB.query("SELECT COUNT(*) FROM user WHERE vorname = '%s' AND nachname = '%s'", vorname, nachname);
        return relationToCount(r) > 0;
    }

    public static void createUser(String vorname, String nachname, String passwort) throws SQLException {
        DB.exec("INSERT INTO user(`vorname`, `nachname`, `passwort`) VALUES ('%s', '%s', '%s')", vorname, nachname, passwort);
    }

    public static String findUserID(String vorname, String nachname, String passwort) throws SQLException {
        QueryResult r = DB.query("SELECT UID FROM user WHERE Vorname = '%s' AND Nachname = '%s' AND Passwort = '%s'", vorname, nachname, passwort);
        return field(0, 0, r);
    }

    public static String findMID(String motto) throws SQLException {
        QueryResult r = DB.query("SELECT MID FROM motto WHERE motto = '%s'", motto);
        return singleValue(r);
    }

    public static boolean mottoExists(String motto) throws SQLException {
        QueryResult r = DB.query("SELECT COUNT(*) FROM motto WHERE Motto = '%s'", motto);
        return relationToCount(r) > 0;
    }

    public static void addMotto(String motto) throws SQLException {
        if (!mottoExists(motto)) {
            DB.exec("INSERT INTO motto(`Motto`) VALUES ('%s')", motto);
        }
    }

    public static String ranking() throws SQLException {
        return DB.queryAndFormat("" +
                "SELECT COUNT(UID) AS Votes, Motto FROM motto " +
                "JOIN wahl USING (MID) " +
                "GROUP BY MID " +
                "ORDER BY Votes DESC");
    }

    public static void voteMotto(String UID, String motto) throws SQLException {
        String MID = findMID(motto);
        vote(UID, MID);
    }

    private static void vote(String UID, String MID) throws SQLException {
        DB.exec("INSERT INTO wahl(`UID`, `MID`) VALUES ('%s', '%s')", UID, MID);
    }

    public static void voteOnce(String UID, String motto) throws SQLException, UserNotFoundException {
        String MID = findMID(motto);
        if (MID == null) {
            throw new UserNotFoundException();
        }
        QueryResult r = DB.query("SELECT COUNT(*) FROM wahl WHERE MID = '%s'", MID);
        boolean alreadyVoted = relationToCount(r) > 0;
        if (alreadyVoted) {
            DB.exec("UPDATE wahl SET MID = '%s' WHERE UID = '%s'", MID, UID);
        } else {
            vote(UID, MID);
        }
    }

    public static String[] mottoList() throws SQLException {
        QueryResult r = DB.query("SELECT Motto FROM motto");
        return row(0, r);
    }

    private static String singleValue(QueryResult r) {
        return field(0, 0, r);
    }

    private static String field(int x, int y, QueryResult r) {
        try {
            return r.getData()[x][y];
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    private static String[] row(int y, QueryResult r) {
        try {
            String[][] data = r.getData();
            String[] result = new String[data.length];
            for (int i = 0; i < data.length; i++) {
                result[i] = data[i][y];
            }
            return result;
        } catch (IndexOutOfBoundsException e) {
            return new String[] {};
        }
    }

    private static int relationToCount(QueryResult r) {
        return Integer.parseInt(singleValue(r));
    }

    public static class UserNotFoundException extends Error {
        UserNotFoundException() {
            super("User not found.");
        }
        UserNotFoundException(String message) {
            super(message);
        }
    }

}
