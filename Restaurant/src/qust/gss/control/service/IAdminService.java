package qust.gss.control.service;

import java.util.List;
import java.util.Set;

import qust.gss.model.Combo;
import qust.gss.model.Food;
import qust.gss.model.User;

public interface IAdminService {
	public String login(String ano,String pwd);
	// 用户管理
	public boolean insertUser(User vo);
	public boolean resetUserPwd(String uno,String newPwd);
	public List<User> findAllUser();
	// 菜品管理
	public boolean insertFood(Food vo);
	public boolean updateFood(Food vo);
	public List<Food> findAllFood();
	// 套餐管理
	public boolean insertCombo(Combo vo);
	public boolean updateCombo(Combo vo);
	public boolean removeBatch(Set<String> cids);
	public List<Combo> findAllCombo();
	// 统计管理
	public List<Food> findFoodStatistics(String begin,String end);
	public List<User> findSalesStatisticsByUser();
	public List<Combo> findComboStatistics();

}
