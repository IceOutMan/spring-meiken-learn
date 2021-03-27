package com.meiken;

import java.sql.*;

/**
 * @Author glf
 * @Date 2020/8/19
 */
public class Test {

    public static void main(String[] args) {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/developer_use?characterEncoding=utf-8", "glf", "glf");

            String querySql = "select id,tid,tname from Teacher";

            statement = connection.createStatement();

            resultSet = statement.executeQuery(querySql);
            while (resultSet.next()){

                int id = resultSet.getInt("id");
                String tid = resultSet.getString("tid");
                String tname = resultSet.getString("tname");

                System.out.println(id+" - " + tid + " - " + tname );
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {

            if (resultSet !=null){
                try {
                    resultSet.close();
                } catch (SQLException throwables) {
                }
            }
            if (statement !=null){
                try {
                    statement.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (connection !=null){
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }
}
