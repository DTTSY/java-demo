package dengtao.Model.service.fix;

import java.util.Map;

public interface FixService {
	public String getFixs();
	public String addFix(Map<String, Object> info);
	public String getUnderFixing();
	public String modifyFix(Map<String, Object> info);
	public String getFixByDate(Map<String, Object> info);
}
