package qust.gss.control.dao.impl;

import java.time.LocalDateTime;

import qust.gss.control.dao.BaseDAO;
import qust.gss.control.dao.ISalesRecordDAO;
import qust.gss.model.ComboSales;
import qust.gss.model.SalesRecord;

public class SalesRecordDAOImpl extends BaseDAO implements ISalesRecordDAO {

	@Override
	public boolean recordFoodSales(SalesRecord vo) throws Exception {
		String sql = "INSERT INTO salesrecord(uno,fid,amount,total,saledate) VALUES(?,?,?,?,?)";
		return jutil.executeUpdate(sql, vo.getUno(), vo.getFid(), vo.getAmount(), vo.getTotal(),
				LocalDateTime.now()) > 0;
	}

	@Override
	public boolean recordComboSales(ComboSales vo) throws Exception {
		String sql = "INSERT INTO combosales(cid,uno,amount,total,saledate) VALUES(?,?,?,?,?)";
		return jutil.executeUpdate(sql, vo.getCid(),vo.getUno(), vo.getCount(), vo.getTotal(), LocalDateTime.now()) > 0;
	}

}
