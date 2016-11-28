package qust.gss.control.dao;

import java.util.List;
import java.util.Set;

import qust.gss.model.Combo;
import qust.gss.model.Food;
import qust.gss.model.User;

public interface IAdminDAO {
	public String doLogin(String ano,String pwd) throws Exception;
	// 用户管理
	public boolean doInsertUser(User vo) throws Exception;
	public boolean doResetUserPwd(String uno,String newPwd) throws Exception;
	public List<User> dofindAllUser() throws Exception;
	// 菜品管理
	public boolean doInsertFood(Food vo) throws Exception;
	public boolean doUpdateFood(Food vo) throws Exception;
	public List<Food> findAllFood() throws Exception;
	// 套餐管理
	public boolean doInsertCombo(Combo vo) throws Exception;
	public boolean doUpdateCombo(Combo vo) throws Exception;
	public boolean doRemoveComboBatch(Set<String> cids) throws Exception;
	public double findComboUnitPrice(String cid) throws Exception;
	// 统计管理
	public List<Food> findFoodStatistics(String begin,String end) throws Exception;
	public List<User> findFoodStatisticsByUser() throws Exception;
	public List<User> findComboStatisticsByUser() throws Exception;
	public List<Combo> findComboStatistics() throws Exception;
}
