package qust.gss.control.service.impl;

import java.util.List;
import java.util.Map;

import qust.gss.control.dao.BaseDAO;
import qust.gss.control.dao.ISalesRecordDAO;
import qust.gss.control.dao.IUserDAO;
import qust.gss.control.service.IUserService;
import qust.gss.factory.DAOFactory;
import qust.gss.model.Combo;
import qust.gss.model.ComboSales;
import qust.gss.model.Food;
import qust.gss.model.SalesRecord;

public class UserServiceImpl implements IUserService {
	private IUserDAO udao = DAOFactory.getIUserDAOInstance();
	private ISalesRecordDAO srdao = DAOFactory.getISaleRecordDAOInstance();

	@Override
	public String login(String uno, String pwd) {
		try {
			return udao.doLogin(uno, pwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean updatePwd(String uno, String newPwd) {
		try {
			return udao.doUpdatePwd(uno, newPwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Food> findAllSalableFood() {
		try {
			return udao.findAllSalableFood();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Food> findAllFoodByCategory(String category) {
		try {
			return udao.findAllFoodByCategory(category);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
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
	public boolean saleFood(String uno, double total, String fid, int count) {
		try {
			BaseDAO.beginTran();
			if (udao.updateFoodAmount(fid, count)) {
				SalesRecord vo = new SalesRecord(uno, fid, count, total);
				if (srdao.recordFoodSales(vo)) {
					BaseDAO.commit();
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			BaseDAO.stopTran();
		}
		return false;
	}

	@Override
	public boolean saleCombo(ComboSales vo) {
		try {
			return srdao.recordComboSales(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<String> findUnchoicableCombo(Map<String, Integer> map) {
		try {
			return udao.findUnchoicableCombo(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
