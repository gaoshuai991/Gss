package qust.gss.control.dao;

import qust.gss.model.ComboSales;
import qust.gss.model.SalesRecord;

public interface ISalesRecordDAO {
	public boolean recordFoodSales(SalesRecord vo) throws Exception;
	public boolean recordComboSales(ComboSales vo) throws Exception;
}
