package com.test.dao;

import com.test.entity.TbUser;
import com.test.entity.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @Author mubi
 * @Date 2020/7/2 09:22
 */
@Repository
public class UserRepo {

	private static final Log logger = LogFactory.getLog(UserRepo.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public User getById(Integer id){
		return new User(id, "abc" + id);
	}

	public TbUser getUserById(Integer id) {
		logger.info("getById:" + id);
		String sql = "select * from t_user where id = ?";
		TbUser tbUser = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<TbUser>(TbUser.class), id);
		return tbUser;
	}

	@Transactional(rollbackFor = Exception.class)
	public Boolean insertAUser(TbUser tbUser) {
		String sql = "insert into t_user(sno, name, password) values(?,?,?)";
		int cnt = jdbcTemplate.update(sql, tbUser.getSno(), tbUser.getName(), tbUser.getPassword());
		return cnt > 0 ? true : false;
	}

}
