package pl.coderslab.services.database;

import org.springframework.beans.factory.annotation.Autowired;
import pl.coderslab.services.UserLogger;
import pl.coderslab.utils.DbUtil;

import javax.naming.CompositeName;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DbUserLoggerService implements UserLogger {

    private DbUtil dbUtil;
    public final String LOG_QUERY="INSERT INTO BookAPI.log (record) VALUES (?);";

    @Autowired
    public DbUserLoggerService(DbUtil dbUtil) {
        this.dbUtil = dbUtil;
    }

    @Override
    public void log(String methodName) {
        try(Connection connection = dbUtil.connect()) {

            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String log = "<Actual Data and Time>:" + now.format(formatter) + " <action>: " + methodName;

            PreparedStatement statement = connection.prepareStatement(LOG_QUERY);
            statement.setString(1, log);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
