package qust.gss.control.service;

import java.util.List;
import java.util.Map;

import qust.gss.model.Combo;
import qust.gss.model.ComboSales;
import qust.gss.model.Food;

public interface IUserService {
	public String login(String uno, String pwd);

	public boolean updatePwd(String uno, String newPwd);

	// 点餐
	public List<Food> findAllSalableFood();

	public List<Food> findAllFoodByCategory(String category);

	public List<Combo> findAllCombo();

	// 卖菜
	public boolean saleFood(String uno, double total, String fid, int count);

	// 卖套餐
	public boolean saleCombo(ComboSales vo);
	
	public List<String> findUnchoicableCombo(Map<String,Integer> map);
	
}
