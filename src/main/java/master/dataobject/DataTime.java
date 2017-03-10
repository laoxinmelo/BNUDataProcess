package master.dataobject;

import java.util.Date;

/**
 * 用于数据插补
 * 对应了某一条record的时间
 * 及其该record中各个属性维度的数据
 */
public class DataTime {

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getRecord() {
		return record;
	}

	public void setRecord(String record) {
		this.record = record;
	}

	//record的所对应时间
	public Date date = new Date();

	//record的其他属性维度的数据
	public String record;
	
	public DataTime(int year,int month,int day,int hour,int minute,String record) throws Exception {
		this.date = new Date(year,month,day,hour,minute);
		this.record = record;
	}
}
