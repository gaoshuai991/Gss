package qust.gss.factory;

import qust.gss.control.dao.IAdminDAO;
import qust.gss.control.dao.ISalesRecordDAO;
import qust.gss.control.dao.IUserDAO;
import qust.gss.control.dao.impl.AdminDAOImpl;
import qust.gss.control.dao.impl.SalesRecordDAOImpl;
import qust.gss.control.dao.impl.UserDAOImpl;

public class DAOFactory {
	private static IAdminDAO adao = null;
	private static IUserDAO udao = null;

	public static IAdminDAO getIAdminDAOInstance() {
		if (adao == null)
			adao = new AdminDAOImpl();
		return adao;
	}

	public static IUserDAO getIUserDAOInstance() {
		if (udao == null)
			udao = new UserDAOImpl();
		return udao;
	}

	public static ISalesRecordDAO getISaleRecordDAOInstance() {
		return new SalesRecordDAOImpl();
	}
}
