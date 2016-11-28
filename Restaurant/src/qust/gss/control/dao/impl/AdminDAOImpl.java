package qust.gss.control.dao.impl;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import qust.gss.control.dao.BaseDAO;
import qust.gss.control.dao.IAdminDAO;
import qust.gss.model.Combo;
import qust.gss.model.Food;
import qust.gss.model.User;

public class AdminDAOImpl extends BaseDAO implements IAdminDAO {

	@Override
	public String doLogin(String ano, String pwd) throws Exception {
		String sql = "SELECT name FROM admin WHERE ano=? AND password=?";
		ResultSet rs = jutil.executeQuery(sql, ano, pwd);
		if (rs.next() && rs.getString(1) != null) {
			return rs.getString(1);
		}
		return null;
	}

	@Override
	public boolean doInsertUser(User vo) throws Exception {
		String sql = "INSERT INTO user(uno,name,password,regdate) VALUES(?,?,?,?)";
		return jutil.executeUpdate(sql, vo.getUno(), vo.getName(), vo.getPassword(), LocalDateTime.now()) > 0;
	}

	@Override
	public boolean doResetUserPwd(String uno, String newPwd) throws Exception {
		String sql = "UPDATE user SET password=? WHERE uno=?";
		return jutil.executeUpdate(sql, newPwd, uno) > 0;
	}

	@Override
	public List<User> dofindAllUser() throws Exception {
		List<User> list = null;
		String sql = "SELECT uno,name,password,regdate FROM user";
		ResultSet rs = jutil.executeQuery(sql);
		while (rs.next()) {
			if (list == null)
				list = new ArrayList<>();
			list.add(new User(rs.getString(1), rs.getString(2), rs.getString(3),
					new SimpleDateFormat("yyyy-MM-dd HH:mm").format((Date) rs.getTimestamp(4))));
		}
		return list;
	}

	@Override
	public boolean doInsertFood(Food vo) throws Exception {
		String sql = "INSERT INTO food(fid,name,category,price,amount) VALUES(?,?,?,?,?)";
		return jutil.executeUpdate(sql, vo.getFid(), vo.getName(), vo.getCategory(), vo.getPrice(), vo.getAmount()) > 0;
	}

	@Override
	public boolean doUpdateFood(Food vo) throws Exception {
		String sql = "UPDATE food SET name=?,price=?,amount=? WHERE fid=?";
		return jutil.executeUpdate(sql, vo.getName(), vo.getPrice(), vo.getAmount(), vo.getFid()) > 0;
	}

	@Override
	public List<Food> findAllFood() throws Exception {
		List<Food> list = null;
		String sql = "SELECT fid,name,category,price,amount FROM food";
		ResultSet rs = jutil.executeQuery(sql);
		while (rs.next()) {
			if (list == null)
				list = new ArrayList<>();
			list.add(new Food(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(5), rs.getDouble(4)));
		}
		return list;
	}

	@Override
	public boolean doInsertCombo(Combo vo) throws Exception {
		String sql = "INSERT INTO combo(cid,staple,vegetables,meat,gruel,price) VALUES(?,?,?,?,?,?);";
		return jutil.executeUpdate(sql, vo.getCid(), vo.getStaple(), vo.getVegetables(), vo.getMeat(), vo.getGruel(),
				vo.getPrice()) > 0;
	}

	@Override
	public boolean doUpdateCombo(Combo vo) throws Exception {
		String sql = "UPDATE combo SET staple=?,vegetables=?,meat=?,gruel=?,price=? WHERE cid=?";
		return jutil.executeUpdate(sql, vo.getStaple(), vo.getVegetables(), vo.getMeat(), vo.getGruel(), vo.getPrice(),
				vo.getCid()) > 0;
	}

	@Override
	public boolean doRemoveComboBatch(Set<String> cids) throws Exception {
		StringBuffer sql = new StringBuffer("DELETE FROM combo WHERE cid IN(");
		Iterator<String> iter = cids.iterator();
		while (iter.hasNext()) {
			sql.append(iter.next() + ",");
		}
		sql.deleteCharAt(sql.length() - 1).append(")");
		return jutil.executeUpdate(sql.toString()) == cids.size();
	}

	@Override
	public double findComboUnitPrice(String cid) throws Exception {
		String sql = "SELECT price FROM combo WHERE cid=?";
		ResultSet rs = jutil.executeQuery(sql, cid);
		if (rs.next()) {
			return rs.getDouble(1);
		}
		return 0;
	}

	@Override
	public List<Food> findFoodStatistics(String begin, String end) throws Exception {
		List<Food> list = null;
		String sql = "SELECT food.fid,food.name,food.category,food.price,temp.c FROM food,(SELECT fid,SUM(amount) c FROM salesrecord WHERE TO_DAYS(?)<=TO_DAYS(saledate) AND TO_DAYS(saledate)<=TO_DAYS(?) GROUP BY fid ORDER BY SUM(amount) DESC) temp WHERE food.fid=temp.fid";
		ResultSet rs = jutil.executeQuery(sql, begin, end);
		while (rs.next()) {
			if (list == null)
				list = new ArrayList<>();
			list.add(new Food(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(5), rs.getDouble(4),
					rs.getInt(5) * rs.getDouble(4)));
		}
		return list;
	}

	@Override
	public List<User> findFoodStatisticsByUser() throws Exception {
		List<User> list = null;
		String sql = "SELECT user.uno,user.name,temp.s FROM user,(SELECT uno,SUM(total) s FROM salesrecord GROUP BY uno ORDER BY SUM(total) DESC) temp WHERE user.uno=temp.uno";
		ResultSet rs = jutil.executeQuery(sql);
		while (rs.next()) {
			if (list == null)
				list = new ArrayList<>();
			list.add(new User(rs.getString(1), rs.getString(2), rs.getDouble(3)));
		}
		return list;
	}

	@Override
	public List<Combo> findComboStatistics() throws Exception {
		List<Combo> list = null;
		Map<Integer, String> dict = new HashMap<Integer, String>();
		dict.put(1, "一");
		dict.put(2, "两");
		dict.put(3, "三");
		dict.put(4, "四");
		String sql = "SELECT staple,vegetables,meat,gruel,temp.c,combo.cid FROM combo,(SELECT cid,COUNT(amount) c FROM combosales GROUP BY cid ORDER BY COUNT(amount) DESC) temp WHERE combo.cid=temp.cid";
		ResultSet rs = jutil.executeQuery(sql);
		while (rs.next()) {
			if (list == null)
				list = new ArrayList<>();
			StringBuffer comboName = new StringBuffer();
			if (rs.getInt(1) != 0)
				comboName.append(dict.get(rs.getInt(1)) + "种主食、");
			if (rs.getInt(2) != 0)
				comboName.append(dict.get(rs.getInt(2)) + "种素菜、");
			if (rs.getInt(3) != 0)
				comboName.append(dict.get(rs.getInt(3)) + "种荤菜、");
			if (rs.getInt(4) != 0)
				comboName.append(dict.get(rs.getInt(4)) + "种汤粥、");
			list.add(new Combo(rs.getString(6), comboName.deleteCharAt(comboName.length() - 1).toString(),
					rs.getInt(5)));
		}
		return list;
	}

	@Override
	public List<User> findComboStatisticsByUser() throws Exception {
		List<User> list = null;
		String sql = "SELECT user.uno,name,temp.s FROM user,(SELECT uno,SUM(total) s FROM combosales GROUP BY uno) temp WHERE temp.uno=user.uno";
		ResultSet rs = jutil.executeQuery(sql);
		while(rs.next()){
			if(list == null)
				list = new ArrayList<>();
			list.add(new User(rs.getString(1),rs.getString(2),rs.getDouble(3)));
		}
		return list;
	}

}
