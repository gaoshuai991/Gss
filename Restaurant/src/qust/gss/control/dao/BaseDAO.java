package qust.gss.control.dao;

import java.sql.SQLException;

import qust.gss.util.JDBCUtil;

public class BaseDAO {

	public static JDBCUtil jutil = null;

	public BaseDAO() {
		if (jutil == null) {
			jutil = new JDBCUtil();
			try {
				jutil.initDBC();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public static void beginTran(){
		try {
			jutil.beginTran();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void stopTran(){
		try {
			jutil.stopTran();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void commit() {
		try {
			jutil.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void rollback() {
		try {
			jutil.rollBack();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void finalize() throws Throwable {
		jutil.close();
		super.finalize();
	}
}
