package com.hzw.monitor.mysqlbinlog.event.data;

/**
 * 
 * @author zhiqiang.liu
 * @2016年1月1日
 * @qq: 837500869
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.hzw.monitor.mysqlbinlog.event.EventHeader;
import com.hzw.monitor.mysqlbinlog.event.RowEventData;
import com.hzw.monitor.mysqlbinlog.utils.LoggerUtils;
import com.hzw.monitor.mysqlbinlog.utils.MyConstants;
import com.hzw.monitor.mysqlbinlog.utils.StringUtils;

public class WriteRowsEventData implements RowEventData {
	/**
	 * 
	 */
	private static final Logger logger = LogManager.getLogger(WriteRowsEventData.class);
	private static final long serialVersionUID = 1289052277519877485L;
	private long tableId;
	// private BitSet includedColumns;
	// 转换为列名 column name
	private String[] includedColumnNames;
	private List<Serializable[]> rows;

	public long getTableId() {
		return tableId;
	}

	public void setTableId(long tableId) {
		this.tableId = tableId;
	}

	public void setIncludedColumns(BitSet includedColumns, HashMap<String, String> mappings) {
		// this.includedColumns = includedColumns;
		// 转换为本地的列名
		// LoggerUtils.debug(logger, mappings.toString());
		this.includedColumnNames = StringUtils.map(includedColumns, mappings);
	}

	public List<Serializable[]> getRows() {
		return rows;
	}

	public void setRows(List<Serializable[]> rows) {
		this.rows = rows;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("WriteRowsEventData");
		sb.append("{tableId=").append(tableId);
		sb.append(", includedColumns=").append(Arrays.toString(includedColumnNames));
		sb.append(", rows=[");
		for (Object[] row : rows) {
			sb.append("\n    ").append(Arrays.toString(row)).append(",");
		}
		if (!rows.isEmpty()) {
			sb.replace(sb.length() - 1, sb.length(), "\n");
		}
		sb.append("]}");
		return sb.toString();
	}

	private EventHeader header;

	@Override
	public void setEventHeader(EventHeader h) {
		this.header = h;
	}

	private TableMapEventData tableMapEventData;

	@Override
	public void setTableMapEventData(TableMapEventData t) {
		tableMapEventData = t;
		database = tableMapEventData.getDatabase();
		table = tableMapEventData.getTable();
	}

	@Override
	public ArrayList<JSONObject> toJson() {
		ArrayList<JSONObject> result = new ArrayList<JSONObject>();
		// 准备工作1
		String[] columns = this.includedColumnNames;
		int length;
		for (Serializable[] row : rows) {
			// 遍历每一个row
			JSONObject object = new JSONObject();
			// strBuilder.append("\"" + MyConstants.UUID + "\":\"" + uuid +
			// "\",");
			object.put(MyConstants.DATABASE, database);
			object.put(MyConstants.TABLE, table);
			object.put(MyConstants.ACTION_TYPE, MyConstants.ACTION_WRITE);
			object.put(MyConstants.ACTION_TIME, header.getTimestamp());
			length = row.length;
			for (int index = 0; index < length; index++) {
				object.put(columns[index], row[index]);
			}
			result.add(object);
			// LoggerUtils.debug(logger, strBuilder.toString());
		}
		return result;
	}

	private String database;
	private String table;

	@Override
	public String getDatabase() {
		return database;
	}

	@Override
	public String getTable() {

		return table;
	}

}
