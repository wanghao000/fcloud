package cn.hz.fcloud.dao;

import java.util.List;
import java.util.Map;

public interface CigaretteMapper {

	List<Map<String, Object>> findAll();
	List<Map<String, Object>> findOne(String imei);
	void addRecord(Map<String, Object> map);

}
