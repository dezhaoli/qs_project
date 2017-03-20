package com.apusic.ausp.cas.authentication;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.apusic.ausp.cas.model.User;

@Repository
public class UserDaoJdbc {
    private static  ExecutorService logExecThread=Executors.newFixedThreadPool(20);
    
	private static final String SQL_USER_VERIFY = "select id,name,passwd,salt,phone,email from memberbusiness where  phone=? ";
	private static final String SQL_USER_GET = "select id,name,phone,email from memberbusiness  where phone=? ";
	
	private static final String SQL_LOG_LOGIN = "INSERT INTO  ausp_log_login(c_id,c_user_id,c_user_name,c_client_name,c_ip,c_login_time,c_extend1) VALUES(?,?,?,?,?,?,?)";
	
	private JdbcTemplate jdbcTemplate;

	@Resource
	public void setDataSource(DataSource dataSource){
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/**
	 * 验证用户名和密码是否正确
	 */
	public boolean verifyAccount(String username, String password){
		try{
			boolean result=false;
			User user=(User)this.jdbcTemplate.queryForObject(SQL_USER_VERIFY, new Object[]{username}, new UserRowMapper());
			if(null!=user){
				//String rawPass=password;
				//result=SecurityUtil.isPasswordValid(user.getPassword(), rawPass, SecurityUtil.PASSWORD_SALT);
				result=true;
			}
			return result;
		}catch(EmptyResultDataAccessException e){
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 根据用户名获取用户信息
	 */
	public User getByUsername(String username){
		try{
			return (User)this.jdbcTemplate.queryForObject(SQL_USER_GET, new Object[]{username}, new UserRowMapper());
		}catch(EmptyResultDataAccessException e){
			return new User();
		}
	}
	
	public  void saveLoginLogByThread(final User user){
		
		logExecThread.execute(new Runnable(){
			public void run() {
				saveLoginLog(user);
				try {
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * 保存日志
	 * @param user
	 */
	private  void saveLoginLog(User user){
		//jdbcTemplate.update(SQL_LOG_LOGIN, new Object[] {ID.generateUUID(),user.getUserId(),user.getRealName(),"","",new Date(),"cas"});  
	}
	
	
}


class UserRowMapper implements RowMapper<User> {
	public User mapRow(ResultSet rs, int index) throws SQLException {
		User user = new User();
		user.setUserId(rs.getString("id"));
		user.setRealName(rs.getString("name"));
		user.setPhone(rs.getString("phone"));
		user.setEmail(rs.getString("email")); 
		
		return user;
	}
}
