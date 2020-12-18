package dengtao.Model.dao.Fix;

import java.util.List;
import java.util.Map;

import dengtao.Model.pojo.Fix;

public interface FixMapper {
	//mybatis
	public List<Fix> getFixes();
	public List<Fix> getFixsByStatus();
	public int addFixs(Map<String, Object> info);
	public int modifyFix(Map<String, Object> info);
	public List<Fix> getFixByDate(Map<String, Object> info);
}
