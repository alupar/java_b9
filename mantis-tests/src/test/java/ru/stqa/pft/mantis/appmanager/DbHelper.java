package ru.stqa.pft.mantis.appmanager;

import ru.stqa.pft.mantis.model.UserData;

import java.sql.*;

public class DbHelper {

  public static UserData getUsernameFromDB() {
    Connection conn = null;
    UserData result = null;
    try {
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bugtracker?user=root&password=&serverTimezone=UTC");
      Statement st = conn.createStatement();
      ResultSet rs = st.executeQuery("SELECT username, email FROM `mantis_user_table` WHERE username != 'administrator' limit 1");
      while (rs.next()) {
        result = new UserData(rs.getString("username"), rs.getString("email"));
      }
      rs.close();
      st.close();
      conn.close();
    } catch (SQLException ex) {
      System.out.println(ex.getMessage());
    }
    return result;
  }
}