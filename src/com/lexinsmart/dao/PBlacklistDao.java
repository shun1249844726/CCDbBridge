package com.lexinsmart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.lexinsmart.model.BlacklistP;
import com.lexinsmart.model.OABlacklist;
import com.lexinsmart.utils.DBCP;
import com.lexinsmart.utils.DateUtils;
import com.lexinsmart.utils.TypeChange;

/**
 * 人员黑名单表管理
 * @author xushun
 *
 */
public class PBlacklistDao {
	Connection connection = null;
	PreparedStatement ptmt = null;
	DBCP dbcp = DBCP.getInstance();

	public PBlacklistDao(Connection connection) {
		this.connection = connection;
	}
	/**
	 * 添加人员黑名单
	 * @param blacklistp
	 */
	public void addBlackList(BlacklistP blacklistp) {

		String sql = "insert into blacklist_p "
				+ " (idcard,name,mantype,company,isblack,blackdate,reason,plleavedate,plemployno,pldepartment) "
				+ "values(?,?,?,?,?,?,?,?,?,?) ";
		try {
			ptmt = connection.prepareStatement(sql);
			ptmt.setString(1, blacklistp.getIdcard());
			ptmt.setString(2, blacklistp.getName());
			ptmt.setString(3, blacklistp.getMantype());
			ptmt.setString(4, blacklistp.getCompany());
			ptmt.setBoolean(5, blacklistp.getIsblack());
			ptmt.setTimestamp(6, blacklistp.getBlackdate());
			ptmt.setString(7, blacklistp.getReason());
			ptmt.setTimestamp(8, blacklistp.getPlleavedate());
			ptmt.setString(9, blacklistp.getPlemployno());
			ptmt.setString(10, blacklistp.getPldepartment());

			ptmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 根据身份证号码 获取ID
	 * @param idcard
	 * @return
	 */
	public int getId(String idcard){
		int id =-1;
		try {
			String sql = " select id from blacklist_p where idcard=?";
			ptmt = connection.prepareStatement(sql);
			ptmt.setString(1, idcard);
			ResultSet rest = ptmt.executeQuery();
			while(rest.next()){
				id = rest.getInt("id");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}
	/**
	 * 根据ID 删除一列
	 * @param id
	 */
	public void delete(int id){
		try {
			String sql ="delete from blacklist_p where id="+id;
			ptmt=connection.prepareStatement(sql);
			ptmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void edit(BlacklistP blacklistP){
		String sql ="update blacklist_p set "+
				"  name=?, "+
				"   mantype=?, "+
				"   company=?, "+
				"   isblack=?, "+
				"   reason=?, "+
				"   plemployno=?, "+
				"   pldepartment=? "+
				" where idcard=?";
		try {
			ptmt = connection.prepareStatement(sql);
			ptmt.setString(1, blacklistP.getName());
			ptmt.setString(2, blacklistP.getMantype());
			ptmt.setString(3, blacklistP.getCompany());
			ptmt.setBoolean(4, blacklistP.getIsblack());
			ptmt.setString(5, blacklistP.getReason());
			ptmt.setString(6, blacklistP.getPlemployno());
			ptmt.setString(7, blacklistP.getPldepartment());
			ptmt.setString(8, blacklistP.getIdcard());
			
			ptmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
