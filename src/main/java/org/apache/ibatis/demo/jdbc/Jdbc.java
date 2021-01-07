package org.apache.ibatis.demo.jdbc;


import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/***
 * 面向对象
 */
@Slf4j
public class Jdbc {

  public static void main(String[] args) {
    User user = new User();
    user.setUsername("Monkey");
    user.setAge(18);
    user.setId(2);
    insert(user);//插入
    user = query(1);  //查询

  }

  static void insert(User c) {
    String sql = "insert into user(username,age) value(?,?)";
    Connection conn = DbUtil.open();
    try {
      PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
      pstmt.setString(1, c.getUsername());
      pstmt.setInt(2, c.getAge());
      pstmt.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      DbUtil.close(conn);
    }


  }

  static User query(int id) {
    String sql = "select * from user where id=?";
    Connection conn = DbUtil.open();
    try {
      PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
      pstmt.setInt(1, id);
      ResultSet rs = pstmt.executeQuery();
      if (rs.next()) {
        String name = rs.getString("username");
        Integer id2 = rs.getInt("id");
        User c = new User();
        c.setId(id);
        c.setUsername(name);
        return c;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      DbUtil.close(conn);
    }
    return null;
  }

}
