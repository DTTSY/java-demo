package dengtao.Model.service.profit;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import com.alibaba.fastjson.JSONObject;
import dengtao.Model.dao.Fix.FixMapper;
import dengtao.Model.dao.Order.OrderMapper;
import dengtao.Model.dao.util.MybatisUtils;
import dengtao.Model.pojo.Fix;
import dengtao.Model.pojo.Order;

public class profitServiceImpl implements profitService{
	private FixMapper fixMapper;
	private SqlSession sqlSession;
	private OrderMapper orderMapper;

	@Override
	public String getProfit(Map<String, Object> info) {
		// TODO Auto-generated method stub
		float income=0;
		float payment=0;
		sqlSession = MybatisUtils.getSqlSession();
		// getMapper 执行sql
		fixMapper = sqlSession.getMapper(FixMapper.class);
		orderMapper = sqlSession.getMapper(OrderMapper.class);
		List<Fix> fixs = fixMapper.getFixByDate(info);
		List<Order> orders = orderMapper.getOrdersByDate(info);
		sqlSession.close();
		
		for (Order order : orders) {
			income+=order.getTotal();
		}
		for (Fix fix : fixs) {
			payment+=fix.getPrice();
		}
		JSONObject pie1 = new JSONObject();
		JSONObject pie2 = new JSONObject();
		pie1.put("name", "租车费用");
		pie1.put("value", income);
		pie2.put("name", "维修费用");
		pie2.put("value", payment);
		List<JSONObject> piedata=new ArrayList<>();
		piedata.add(pie1);
		piedata.add(pie2);
		JSONObject rs=new JSONObject();
		rs.put("pieDate", piedata);
		rs.put("profit", income-payment);
		
		return rs.toJSONString();
	}
}
