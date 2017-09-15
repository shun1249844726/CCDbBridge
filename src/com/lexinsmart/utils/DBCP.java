package com.lexinsmart.utils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DBCP {
	
 //   private static ThreadLocal<Connection> tl=new ThreadLocal<Connection>();  

	protected static final Log log = LogFactory.getLog(DBCP.class.getName());
	private static DBCP _instance = null;
	private static DataSource ds;

	public static DBCP getInstance() {
		if (_instance == null) {
			_instance = new DBCP();
		}
		return _instance;
	}

	// 在静态代码块中进行配置文件与程序的关联
	static {
		Properties prop = new Properties();
		String commConfigFilePath = "./conf/dbcp.properties";
		// String commConfigFilePath = DBCP.class.getResource("/").getFile()
		// +"dbcp.properties";
		InputStream in = null;
		try {
			in = new BufferedInputStream(new FileInputStream(commConfigFilePath));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			prop.load(in);
			ds = BasicDataSourceFactory.createDataSource(prop);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * get a connection from the pool
	 * 
	 * @return
	 */

	public Connection getConnection() {
		Connection conn = null;
		if (ds != null) {
			try {
				conn = ds.getConnection();

			} catch (Exception e) {
				log.info("getConnection fail:", e);
				e.printStackTrace();
			}
//			try {
//				conn.setAutoCommit(false);
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
			return conn;
		}
		return conn;
	}

	/**
	 * release the connection
	 * 
	 * @param con
	 */
	public void releaseConnection(Connection con) {
		try {
			if (con != null && !con.isClosed())
				con.close();
		} catch (SQLException ex) {
			log.info("releaseConnection fail:", ex);
			ex.printStackTrace();
		}finally {
		}
	}

	/**
	 * close statement and resultset
	 * 
	 * @param stm
	 * @param result
	 */
	public void closeStatement(Statement stm, ResultSet result) {
		try {
			if (result != null) {
				result.close();
			}
		} catch (SQLException e) {
			log.error("closeStatement error:", e);
			e.printStackTrace();
		}

		try {
			if (stm != null) {
				stm.close();
			}
		} catch (SQLException e) {
			log.error("closeStatement error:", e);
			e.printStackTrace();
		}
	}

	/**
	 * close statement
	 * 
	 * @param st
	 */
	public void closeStatement(Statement st) {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				log.error("closeStatement error:", e);
				e.printStackTrace();
			}
		}
	}

	/**
	 * generic query method
	 * 
	 * @param sql
	 * @return
	 */
	public ResultSet executeQuery(String sql) {
		ResultSet rset = null;
		Statement _stmt = null;
		Connection con = getConnection();
		try {
			_stmt = con.createStatement();
			rset = _stmt.executeQuery(sql);
		} catch (Exception e) {
			log.error("executeQuery error:", e);
			e.printStackTrace();
		} finally {
			closeStatement(_stmt);
			releaseConnection(con);
		}
		return rset;
	}

	/**
	 * generic update method
	 * 
	 * @param sql
	 */
	public void updateDB(String sql) {
		Connection con = getConnection();
		Statement _stmt = null;
		try {
			_stmt = con.createStatement();
			_stmt.executeUpdate(sql);
		} catch (Exception e) {
			log.error("updateDB error:", e);
			e.printStackTrace();
		} finally {
			closeStatement(_stmt);
			releaseConnection(con);
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < 30; i++) {
			long s = System.currentTimeMillis();
			DBCP dbcp = DBCP.getInstance();
			Connection con = dbcp.getConnection();
			System.out.println(con.toString());
			dbcp.releaseConnection(con);
			System.out.println(System.currentTimeMillis() - s);
		}

	}
}

//// 原理：让数据源动态生成连接
// private static DataSource myDataSource;
// private static Connection conn;
// private static Properties prop;
//
//// 单例模式
// private DBCP() {
//
// }
//
//// 在静态代码块中进行配置文件与程序的关联
// static {
// prop = new Properties();
// String commConfigFilePath = "./conf/dbcp.properties";
//// String commConfigFilePath = DBCP.class.getResource("/").getFile()
//// +"dbcp.properties";
// InputStream in = null;
// try {
// in = new BufferedInputStream(new FileInputStream(commConfigFilePath));
// } catch (FileNotFoundException e1) {
// // TODO Auto-generated catch block
// e1.printStackTrace();
// }
// try {
// prop.load(in);
// myDataSource = BasicDataSourceFactory.createDataSource(prop);
//
// } catch (IOException e) {
// e.printStackTrace();
// } catch (Exception e) {
// e.printStackTrace();
// }
// }
//
//// 获取连接
// public static Connection getConnection() throws SQLException {
// try {
// conn = myDataSource.getConnection();
// } catch (SQLException e) {
// // TODO Auto-generated catch block
// e.printStackTrace();
// }
// return conn;
// }
//
//// 断开连接
// public static void closeConnection() {
// try {
// conn.close();
// } catch (SQLException e) {
// e.printStackTrace();
// }
// }
// }

//
//// JDBC 驱动名及数据库 URL
// static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
// static final String DB_URL =
// "jdbc:mysql://mysql.tickrobot.com:3306/cc_entrance_guard?characterEncoding=utf-8&useSSL=false";
//
//// 数据库的用户名与密码，需要根据自己的设置
// static final String USER = "cc_test";
// static final String PASS = "cc_test";
//
// private static Connection conn = null;
// private static Statement stmt = null;
// static {
// // 注册 JDBC 驱动
// try {
// Class.forName("com.mysql.jdbc.Driver");
// try {
// conn = DriverManager.getConnection(DB_URL, USER, PASS);
// } catch (SQLException e) {
// // TODO Auto-generated catch block
// e.printStackTrace();
// }
// } catch (ClassNotFoundException e) {
// // TODO Auto-generated catch block
// e.printStackTrace();
// }
//
// }
//
// public static Connection getConnection() {
// return conn;
// };
// }