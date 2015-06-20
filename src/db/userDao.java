package db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import db.dbconn;
import db.user;

public class userDao {

	public void adduser(user g) throws Exception{
		Connection conn=dbconn.getConnection();
		String sql="" +
				"insert into imooc_user" +
				"(user_name,sex,age,birthday,email,mobile," +
				"create_user,create_date,update_user,update_date,isdel)" +
				"values(" +
				"?,?,?,?,?,?,?,current_date(),?,current_date(),?)";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		
		ptmt.setString(1, g.getUser_name());
		ptmt.setInt(2, g.getSex());
		ptmt.setInt(3, g.getAge());
		ptmt.setDate(4, new Date(g.getBirthday().getTime()));
		ptmt.setString(5, g.getEmail());
		ptmt.setString(6, g.getMobile());
		ptmt.setString(7, g.getCreate_user());
		ptmt.setString(8, g.getUpdate_user());
		ptmt.setInt(9, g.getIsdel());
		ptmt.execute();
	}
	
	public void updateuser(user g) throws SQLException{
		Connection conn=dbconn.getConnection();
		String sql="" +
				" update imooc_user " +
				" set user_name=?,sex=?,age=?,birthday=?,email=?,mobile=?, " +
				" update_user=?,update_date=current_date(),isdel=? " +
				" where id=? ";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		
		ptmt.setString(1, g.getUser_name());
		ptmt.setString(2, g.getUser_password());
		ptmt.setInt(3, g.getSex());
		ptmt.setInt(4, g.getAge());
		ptmt.setDate(5, new Date(g.getBirthday().getTime()));
		ptmt.setString(6, g.getEmail());
		ptmt.setString(7, g.getMobile());
		ptmt.setString(8, g.getUpdate_user());
		ptmt.setInt(9, g.getIsdel());
		ptmt.setInt(10, g.getId());
		ptmt.execute();
	}
	
	public void deluser(Integer id) throws SQLException{
		Connection conn=dbconn.getConnection();
		String sql="" +
				" delete from imooc_user " +
				" where id=? ";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		
		ptmt.setInt(1, id);
		ptmt.execute();
	}
	public List<user> query() throws Exception{
		List<user> result=new ArrayList<user>();
		
		Connection conn=dbconn.getConnection();
		StringBuilder sb=new StringBuilder();
		sb.append("select id,user_name,age from imooc_user  ");
		
		PreparedStatement ptmt=conn.prepareStatement(sb.toString());
		
		ResultSet rs=ptmt.executeQuery();
		
		user g=null;
		while(rs.next()){
			g=new user();
			g.setId(rs.getInt("id"));
			g.setUser_name(rs.getString("user_name"));
			g.setAge(rs.getInt("age"));
			result.add(g);
		}
		return result;
	}
	public int isLogin(String name,String password) throws Exception{
		
		Connection conn=dbconn.getConnection();
		StringBuilder sb=new StringBuilder();
		sb.append("select * from imooc_user  ");
		
		sb.append(" where user_name like ? and mobile like ? and email like ?");
		
		PreparedStatement ptmt=conn.prepareStatement(sb.toString());
		ptmt.setString(1, "%"+name+"%");
		System.out.println(sb.toString());
		ResultSet rs=ptmt.executeQuery();
		if(rs.next()){
			if(rs.getString("user_password")==password){
				return 1;
			}
		}else{
			return 0;
		}
		return 2;
		
	}
	public List<user> query(String name,String mobile,String email) throws Exception{
		List<user> result=new ArrayList<user>();
		
		Connection conn=dbconn.getConnection();
		StringBuilder sb=new StringBuilder();
		sb.append("select * from imooc_user  ");
		
		sb.append(" where user_name like ? and mobile like ? and email like ?");
		
		PreparedStatement ptmt=conn.prepareStatement(sb.toString());
		ptmt.setString(1, "%"+name+"%");
		ptmt.setString(2, "%"+mobile+"%");
		ptmt.setString(3, "%"+email+"%");
		System.out.println(sb.toString());
		ResultSet rs=ptmt.executeQuery();
		
		user g=null;
		while(rs.next()){
			g=new user();
			g.setId(rs.getInt("id"));
			g.setUser_name(rs.getString("user_name"));
			g.setAge(rs.getInt("age"));
			g.setSex(rs.getInt("sex"));
			g.setBirthday(rs.getDate("birthday"));
			g.setEmail(rs.getString("email"));
			g.setMobile(rs.getString("mobile"));
			g.setCreate_date(rs.getDate("create_date"));
			g.setCreate_user(rs.getString("create_user"));
			g.setUpdate_date(rs.getDate("update_date"));
			g.setUpdate_user(rs.getString("update_user"));
			g.setIsdel(rs.getInt("isdel"));
			
			result.add(g);
		}
		return result;
	}
	public List<user> query(List<Map<String, Object>> params) throws Exception{
		List<user> result=new ArrayList<user>();
		
		Connection conn=dbconn.getConnection();
		StringBuilder sb=new StringBuilder();
		sb.append("select * from imooc_user where 1=1 ");
		
		if(params!=null&&params.size()>0){
			for (int i = 0; i < params.size(); i++) {
				Map<String, Object> map=params.get(i);
				sb.append(" and  "+map.get("name")+" "+map.get("rela")+" "+map.get("value")+" ");
			}
		}
		
		PreparedStatement ptmt=conn.prepareStatement(sb.toString());
		
		System.out.println(sb.toString());
		ResultSet rs=ptmt.executeQuery();
		
		user g=null;
		while(rs.next()){
			g=new user();
			g.setId(rs.getInt("id"));
			g.setUser_name(rs.getString("user_name"));
			g.setAge(rs.getInt("age"));
			g.setSex(rs.getInt("sex"));
			g.setBirthday(rs.getDate("birthday"));
			g.setEmail(rs.getString("email"));
			g.setMobile(rs.getString("mobile"));
			g.setCreate_date(rs.getDate("create_date"));
			g.setCreate_user(rs.getString("create_user"));
			g.setUpdate_date(rs.getDate("update_date"));
			g.setUpdate_user(rs.getString("update_user"));
			g.setIsdel(rs.getInt("isdel"));
			
			result.add(g);
		}
		return result;
	}
	public user get(Integer id) throws SQLException{
		user g=null;
		Connection conn=dbconn.getConnection();
		String sql="" +
				" select * from imooc_user " +
				" where id=? ";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		
		ptmt.setInt(1, id);
		ResultSet rs=ptmt.executeQuery();
		while(rs.next()){
			g=new user();
			g.setId(rs.getInt("id"));
			g.setUser_name(rs.getString("user_name"));
			g.setAge(rs.getInt("age"));
			g.setSex(rs.getInt("sex"));
			g.setBirthday(rs.getDate("birthday"));
			g.setEmail(rs.getString("email"));
			g.setMobile(rs.getString("mobile"));
			g.setCreate_date(rs.getDate("create_date"));
			g.setCreate_user(rs.getString("create_user"));
			g.setUpdate_date(rs.getDate("update_date"));
			g.setUpdate_user(rs.getString("update_user"));
			g.setIsdel(rs.getInt("isdel"));
		}
		return g;
	}
}
