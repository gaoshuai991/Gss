package qust.gss.control.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import qust.gss.control.dao.IAdminDAO;
import qust.gss.control.dao.IUserDAO;
import qust.gss.control.service.IAdminService;
import qust.gss.factory.DAOFactory;
import qust.gss.model.Combo;
import qust.gss.model.Food;
import qust.gss.model.User;

public class AdminServiceImpl implements IAdminService {
	private IAdminDAO dao = DAOFactory.getIAdminDAOInstance();
	private IUserDAO udao = DAOFactory.getIUserDAOInstance();

	@Override
	public String login(String ano, String pwd) {
		try {
			return dao.doLogin(ano, pwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean insertUser(User vo) {
		try {
			return dao.doInsertUser(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean resetUserPwd(String uno, String newPwd) {
		try {
			return dao.doResetUserPwd(uno, newPwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<User> findAllUser() {
		try {
			return dao.dofindAllUser();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean insertFood(Food vo) {
		try {
			return dao.doInsertFood(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateFood(Food vo) {
		try {
			return dao.doUpdateFood(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Food> findAllFood() {
		try {
			return dao.findAllFood();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean insertCombo(Combo vo) {
		try {
			return dao.doInsertCombo(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateCombo(Combo vo) {
		try {
			return dao.doUpdateCombo(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean removeBatch(Set<String> cids) {
		try {
			return dao.doRemoveComboBatch(cids);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Combo> findAllCombo() {
		try {
			return udao.findAllCombo();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Food> findFoodStatistics(String begin, String end) {
		try {
			return dao.findFoodStatistics(begin, end);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<User> findSalesStatisticsByUser() {
		try {
			List<User> foodStat = dao.findFoodStatisticsByUser();
			List<User> comboStat = dao.findComboStatisticsByUser();
			if (foodStat != null && comboStat != null) {
				Iterator<User> iterf = foodStat.iterator();
				while (iterf.hasNext()) {
					User userf = iterf.next();
					Iterator<User> iterc = comboStat.iterator();
					while (iterc.hasNext()) {
						User userc = iterc.next();
						if (userc.getUno().equals(userf.getUno()))
							userf.setPrice(userf.getPrice() + userc.getPrice());
					}
				}
			}
			return foodStat;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Combo> findComboStatistics() {
		try {
			return dao.findComboStatistics();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
