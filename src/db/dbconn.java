package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbconn {
private static final String URL ="jdbc:mysql://127.0.0.1:3306/fuck?useUnicode = true&amp;characterEciding=utf-8";
private static final String USER="root";
private static final String PASSWORD="17862701296ab";
private static Connection conn = null;
static{
	//加载驱动程序
	try {
		Class.forName("com.mysql.jdbc.Driver");
		//获得数据库连接
			conn =DriverManager.getConnection(URL,USER,PASSWORD);
	
		
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	 catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
 public static Connection getConnection(){
	return conn;
}

}
