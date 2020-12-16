package dengtao.Model.service.fix;

import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

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
		
		JSONObject clo= new JSONObject(true);
		clo.put("id", "维修编号");
		clo.put("carIde", "车辆编号");
		clo.put("F_case", "故障描述");
		clo.put("fixDte", "送修日期");
		clo.put("fixedDate", "修复日期");
		clo.put("price","维修费用" );
		
		JSONObject rs= new JSONObject(true);
		rs.put("col", clo);
		rs.put("list", fixs);
		
		return JSON.toJSONString(rs, SerializerFeature.WriteMapNullValue);
	}

}
