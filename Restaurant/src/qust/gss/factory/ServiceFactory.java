package qust.gss.factory;

import qust.gss.control.service.IAdminService;
import qust.gss.control.service.IUserService;
import qust.gss.control.service.impl.AdminServiceImpl;
import qust.gss.control.service.impl.UserServiceImpl;

public class ServiceFactory {
	private static IAdminService adminService = null;
	private static IUserService userService = null;

	public static IAdminService getIAdminServiceInstance() {
		if (adminService == null)
			adminService = new AdminServiceImpl();
		return adminService;
	}

	public static IUserService getIUserServiceInstance() {
		if (userService == null)
			userService = new UserServiceImpl();
		return userService;
	}
}
