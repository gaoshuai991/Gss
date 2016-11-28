package qust.gss.control.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import qust.gss.control.dao.BaseDAO;
import qust.gss.control.dao.IUserDAO;
import qust.gss.model.Combo;
import qust.gss.model.Food;

public class UserDAOImpl extends BaseDAO implements IUserDAO {

	@Override
	public String doLogin(String uno, String pwd) throws Exception {
		String sql = "SELECT name FROM user WHERE uno=? AND password=?";
		ResultSet rs = jutil.executeQuery(sql, uno, pwd);
		if (rs.next() && rs.getString(1) != null) {
			return rs.getString(1);
		}
		return null;
	}

	@Override
	public boolean doUpdatePwd(String uno, String newPwd) throws Exception {
		String sql = "UPDATE user SET password=? WHERE uno=?";
		return jutil.executeUpdate(sql, newPwd, uno) > 0;
	}

	@Override
	public List<Food> findAllSalableFood() throws Exception {
		List<Food> list = null;
		String sql = "SELECT fid,name,category,amount,price FROM food WHERE amount>0";
		ResultSet rs = jutil.executeQuery(sql);
		while (rs.next()) {
			if (list == null)
				list = new ArrayList<>();
			list.add(new Food(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getDouble(5)));
		}
		return list;
	}

	@Override
	public List<Food> findAllFoodByCategory(String category) throws Exception {
		List<Food> list = null;
		String sql = "SELECT fid,name,category,amount,price FROM food WHERE amount>0 AND category=?";
		ResultSet rs = jutil.executeQuery(sql, category);
		while (rs.next()) {
			if (list == null)
				list = new ArrayList<>();
			list.add(new Food(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getDouble(5)));
		}
		return list;
	}

	@Override
	public List<Combo> findAllCombo() throws Exception {
		List<Combo> list = null;
		Map<Integer, String> dict = new HashMap<Integer, String>();
		dict.put(1, "一");
		dict.put(2, "两");
		dict.put(3, "三");
		dict.put(4, "四");
		String sql = "SELECT cid,staple,vegetables,meat,gruel,price FROM combo";
		ResultSet rs = jutil.executeQuery(sql);
		while (rs.next()) {
			if (list == null)
				list = new ArrayList<>();
			StringBuffer comboName = new StringBuffer();
			if (rs.getInt(2) != 0)
				comboName.append(dict.get(rs.getInt(2)) + "种主食、");
			if (rs.getInt(3) != 0)
				comboName.append(dict.get(rs.getInt(3)) + "种素菜、");
			if (rs.getInt(4) != 0)
				comboName.append(dict.get(rs.getInt(4)) + "种荤菜、");
			if (rs.getInt(5) != 0)
				comboName.append(dict.get(rs.getInt(5)) + "种汤粥、");
			list.add(new Combo(rs.getString(1), comboName.deleteCharAt(comboName.length() - 1).toString(), rs.getInt(2),
					rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getDouble(6)));
		}
		return list;
	}

	@Override
	public boolean updateFoodAmount(String fid, int count) throws Exception {
		String sql = "UPDATE food SET amount=amount-? WHERE fid=?";
		return jutil.executeUpdate(sql, count, fid) > 0;
	}

	@Override
	public List<String> findUnchoicableCombo(Map<String, Integer> map) throws Exception {
		List<String> list = null;
		String sql = "SELECT cid FROM combo WHERE staple<? OR vegetables<? OR meat<? OR gruel<?";
		ResultSet rs = jutil.executeQuery(sql, map.get("主食"), map.get("素菜"), map.get("荤菜"), map.get("汤粥"));
		while (rs.next()) {
			if (list == null)
				list = new ArrayList<>();
			list.add(rs.getString(1));
		}
		return list;
	}

}
