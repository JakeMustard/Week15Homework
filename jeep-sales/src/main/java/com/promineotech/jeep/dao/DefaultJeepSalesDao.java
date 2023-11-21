package com.promineotech.jeep.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.promineotech.jeep.entity.Jeep;
import com.promineotech.jeep.entity.JeepModel;

import lombok.extern.slf4j.Slf4j;


@Component
@Slf4j

public class DefaultJeepSalesDao implements JeepSalesDao {
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	

	@Override
	public List<Jeep> fetchjeeps(JeepModel model, String trim) {
		// TODO Auto-generated method stub
		log.debug("DAO: model={}, trim{}", model, trim);
		
		
		// @formetter:off
		String sql = ""
				+ "SELECT * "
				+ "FROM models "
				+ "WHERE model_id = :model_id and trim_level = :trim_level";
		//@formatter:on
		
		Map<String, Object> params = new HashMap<>();
		params.put("model_id", model.toString());
		params.put("trim_level",trim);
		
		return jdbcTemplate.query(sql,  params,
				new RowMapper<>() {
			@Override
				public Jeep mapRow(ResultSet rs, int rowNum) throws SQLException {
				//@formatter:off
				return Jeep.builder()
			            .modelPK(rs.getLong("model_pk"))
			            .modelId(JeepModel.valueOf(rs.getString("model_id")))
			            .trimLevel(rs.getString("trim_level"))
			            .numDoors(rs.getInt("num_doors"))
			            .wheelSize(rs.getInt("wheel_size"))
			            .basePrice(new BigDecimal(rs.getString("base_price")))
			            .build();				
			}

			
			});
		}
		
}



