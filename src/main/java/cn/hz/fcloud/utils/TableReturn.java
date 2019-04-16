package cn.hz.fcloud.utils;

public class TableReturn {

	private Object rows;
	private int total;
	public TableReturn() {
	}
	public TableReturn(Object rows, int total){
		this.rows=rows;
		this.total=total;
	}
	public Object getRows() {
		return rows;
	}
	public void setRows(Object rows) {
		this.rows = rows;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
}
