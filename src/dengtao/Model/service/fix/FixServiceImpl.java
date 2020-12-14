package dengtao.Model.service.fix;

import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import dengtao.Model.dao.Fix.FixMapper;
import dengtao.Model.dao.util.MybatisUtils;
import dengtao.Model.pojo.Fix;

public class FixServiceImpl implements FixService {
	private FixMapper fixdao;
	private SqlSession sqlSession;

	@Override
	public String getFixs() {
		// TODO Auto-generated method stub
		sqlSession = MybatisUtils.getSqlSession();
		// getMapper 执行sql
		fixdao = sqlSession.getMapper(FixMapper.class);
		List<Fix> fixs = fixdao.getFixes();
		sqlSession.close();
		
		List<JSONObject> data = new ArrayList<>();
		
		for (Fix fix : fixs) {
			JSONObject fixData = new JSONObject(true);
			fixData.put("id", fix.getId());
			fixData.put("carId", fix.getCarId());
			fixData.put("F_case", fix.getF_case());
			fixData.put("fixDte", fix.getFixDate());
			fixData.put("fixedDate", fix.getFixedDate());
			fixData.put("price", fix.getPrice());
			data.add(fixData);
		}
		JSONObject clo= new JSONObject(true);
		clo.put("id", "维修记录编号");
		clo.put("carIde", "车辆编号");
		clo.put("F_case", "故障描述");
		clo.put("fixDte", "送修日期");
		clo.put("fixedDate", "修复日期");
		clo.put("price","维修费用" );
		
		JSONObject rs= new JSONObject();
		rs.put("col", clo);
		rs.put("list", data);
		// close
		
		
		return JSON.toJSONString(rs);
	}

}
