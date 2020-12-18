package dengtao.Model.service.fix;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import dengtao.Model.dao.Fix.FixMapper;
import dengtao.Model.dao.util.MybatisUtils;
import dengtao.Model.pojo.Fix;

public class FixServiceImpl implements FixService {
	private FixMapper fixMapper;
	private SqlSession sqlSession;

	@Override
	public String getFixs() {
		// TODO Auto-generated method stub
		sqlSession = MybatisUtils.getSqlSession();
		// getMapper 执行sql
		fixMapper = sqlSession.getMapper(FixMapper.class);
		List<Fix> fixs = fixMapper.getFixes();
		
		sqlSession.close();
		
		return getFixListYoJSON(fixs);
	}

	@Override
	public String addFix(Map<String, Object> info) {
		// TODO Auto-generated method stub
		sqlSession = MybatisUtils.getSqlSession();
		// getMapper 执行sql
		fixMapper = sqlSession.getMapper(FixMapper.class);
		JSONObject okJsonObject = new JSONObject();
		
		try {
			if (fixMapper.addFixs(info)>0) {
				okJsonObject.put("ok", 1);
			} else {
				okJsonObject.put("ok", 0);
			}
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		return okJsonObject.toJSONString();
	}
private String getFixListYoJSON(List<Fix> fixs) {
	JSONObject clo= new JSONObject(true);
	clo.put("维修编号", "维修编号");
	clo.put("车辆编号", "车辆编号");
	clo.put("故障描述", "故障描述");
	clo.put("送修日期", "送修日期");
	clo.put("修复日期", "修复日期");
	clo.put("维修费用","维修费用" );
	
	JSONObject rs= new JSONObject(true);
	rs.put("col", clo);
	rs.put("list", fixs);
	return JSON.toJSONString(rs, SerializerFeature.WriteMapNullValue);
}
	@Override
	public String getUnderFixing() {
		// TODO Auto-generated method stub
		sqlSession = MybatisUtils.getSqlSession();
		// getMapper 执行sql
		fixMapper = sqlSession.getMapper(FixMapper.class);
		List<Fix> fixs = fixMapper.getFixsByStatus();
		sqlSession.close();
		
		return getFixListYoJSON(fixs);
	}

	@Override
	public String modifyFix(Map<String, Object> info) {
		// TODO Auto-generated method stub
		sqlSession = MybatisUtils.getSqlSession();
		// getMapper 执行sql
		fixMapper = sqlSession.getMapper(FixMapper.class);
		JSONObject okJsonObject = new JSONObject();
		try {
			if (fixMapper.modifyFix(info)>0) {
				okJsonObject.put("ok", 1);
			} else {
				okJsonObject.put("ok", 0);
			}
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		return okJsonObject.toJSONString();
	}

	@Override
	public String getFixByDate(Map<String, Object> info) {
		// TODO Auto-generated method stub
		sqlSession = MybatisUtils.getSqlSession();
		// getMapper 执行sql
		fixMapper = sqlSession.getMapper(FixMapper.class);
		List<Fix> fixs = fixMapper.getFixByDate(info);
		sqlSession.close();
		return getFixListYoJSON(fixs);
	}
}
