package me.mcluke300.playerlogger.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import me.mcluke300.playerlogger.config.*;

public class mysql {

	// Creating MySQL database
	public static void createDatabase() {
		Connection connection = null;
		Statement st = null;
		int rs = 0;
		try {
			// Connecting to database
			connection = DriverManager.getConnection("jdbc:mysql://" + getConfig.MySQLServer() + "/" + getConfig.MySQLDatabase(), getConfig.MySQLUser(), getConfig.MySQLPassword());
			st = connection.createStatement();
			// Make table if it does not exist query onEnable
			rs = st.executeUpdate("CREATE TABLE IF NOT EXISTS `" + getConfig.MySQLTable() + "` "
					+ "(`id` INT(11) unsigned NOT NULL AUTO_INCREMENT,"
					+ "`playername` varchar(20),"
					+ "`type` varchar(15),"
					+ "`time` INT(255),"
					+ "`data` text,"
					+ "`x` MEDIUMINT(255),"
					+ "`y` MEDIUMINT(255),"
					+ "`z` MEDIUMINT(255),"
					+ "`world` varchar(40),"
					+ "`server` varchar(20),"
					+ "PRIMARY KEY (`id`),"
					+ "KEY `time` (`time`),"
					+ "KEY `playername` (`playername`),"
					+ "KEY `type` (`type`))");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.print(rs);
		} finally {
			try {
				if (st != null) {
					st.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException ex) {
			}
		}
	}

}
