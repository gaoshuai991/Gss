package qust.gss.control.dao;

import java.util.List;
import java.util.Map;

import qust.gss.model.Combo;
import qust.gss.model.Food;

public interface IUserDAO {
	public String doLogin(String uno,String pwd) throws Exception;
	public boolean doUpdatePwd(String uno,String newPwd) throws Exception;
	// 点餐
	public List<Food> findAllSalableFood() throws Exception;
	public List<Food> findAllFoodByCategory(String category) throws Exception;
	public List<Combo> findAllCombo() throws Exception;
	// 卖菜
	public boolean updateFoodAmount(String fid,int count) throws Exception;
	
	public List<String> findUnchoicableCombo(Map<String,Integer> map) throws Exception;
}
