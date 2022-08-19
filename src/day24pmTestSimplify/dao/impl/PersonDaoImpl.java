package day24pmTestSimplify.dao.impl;

import day24pmTestSimplify.dao.PersonDao;
import day24pmTestSimplify.model.Person;
import day24pmTestSimplify.util.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDaoImpl implements PersonDao {
    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3306/20220819_day24";
    public static final String USER = "root";
    public static final String PASSWORD = "root";

    @Override
    public int add(Person person) {
        Connection conn=JDBCUtil.getConnection();
        PreparedStatement ps = null;
        int result = 0;
        try {

            String sql = "insert into person (name, age, salary, born) values (?, ?, ?, ?) ";
            ps = conn.prepareStatement(sql);
            ps.setString(1, person.getName());
            ps.setInt(2, person.getAge());
            ps.setDouble(3, person.getSalary());
            ps.setString(4, person.getBorn());
            result = ps.executeUpdate();

        }  catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(null,ps,conn);
        }
        return result;
    }

    @Override
    public int update(Person person) {
        Connection conn=JDBCUtil.getConnection();
        PreparedStatement ps = null;
        int result = 0;
        try {
            String sql = "update person set name = ?, age = ?, salary = ?, born =? where id = ?;";
            //(4). 获取预编译对象装载SQL语句(找一台车装货！)
            ps = conn.prepareStatement(sql);
            //ps：指代预编译对象(车)
            //(5). 通过预编译对象将SQL语句发送到MySQL并执行该SQL语句！
            //1>. 执行DML语句：ps.executeUpdate();   (方法返回整数结果)
            //2>. 执行DQL语句：ps.executeQuery();    (方法返回结果集结果)
            ps.setString(1, person.getName());
            ps.setInt(2, person.getAge());
            ps.setDouble(3, person.getSalary());
            ps.setString(4, person.getBorn());
            ps.setInt(5, person.getId());
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(null,ps,conn);
            //(6). 关闭资源(后创建的资源先关闭！！！)
        }
        return result;
    }

    @Override
    public int delete(int id) {
        //尝试使用JDBC代码连接数据库进行添加操作！(SQL语句由Java编写发送到数据库！)
        Connection conn=JDBCUtil.getConnection();
        PreparedStatement ps = null;
        int result = 0;
        try {
            String sql = "delete from person where id = ?";
            //(4). 获取预编译对象装载SQL语句(找一台车装货！)
            ps = conn.prepareStatement(sql);
            //ps：指代预编译对象(车)
            //(5). 通过预编译对象将SQL语句发送到MySQL并执行该SQL语句！
            //1>. 执行DML语句：ps.executeUpdate();   (方法返回整数结果)
            //2>. 执行DQL语句：ps.executeQuery();    (方法返回结果集结果)
            ps.setInt(1, id);
            result = ps.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(null,ps,conn);
        }
        return result;
    }

    @Override
    public List<Integer> selectId() {
        Connection conn=JDBCUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Integer> integers = new ArrayList<>();
        try {
            String sql = "select id from person";
            //(4). 获取预编译对象装载SQL语句(找一台车装货！)
            ps = conn.prepareStatement(sql);
            //ps：指代预编译对象(车)
            //(5). 通过预编译对象将SQL语句发送到MySQL并执行该SQL语句！
            //1>. 执行DML语句：ps.executeUpdate();   (方法返回整数结果)
            //2>. 执行DQL语句：ps.executeQuery();    (方法返回结果集结果)
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                integers.add(id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs,ps,conn);
        }
        return integers;
    }

    @Override
    public List<Person> selectAll() {
        Connection conn=JDBCUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Person> people = new ArrayList<>();
        try {
            String sql = "select id, name, age, salary, born from person";
            //(4). 获取预编译对象装载SQL语句(找一台车装货！)
            ps = conn.prepareStatement(sql);
            //ps：指代预编译对象(车)
            //(5). 通过预编译对象将SQL语句发送到MySQL并执行该SQL语句！
            //1>. 执行DML语句：ps.executeUpdate();   (方法返回整数结果)
            //2>. 执行DQL语句：ps.executeQuery();    (方法返回结果集结果)
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                double salary = rs.getDouble("salary");
                String born = rs.getString("born");
                Person person = new Person(id, name, age, salary, born);
                people.add(person);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs,ps,conn);
        }
        return people;
    }

    @Override
    public List<Person> selectByName(String name) {
        Connection conn=JDBCUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Person> people = new ArrayList<>();
        try {
            String sql = "select id, name, age, salary, born from person where name = ?";
            //(4). 获取预编译对象装载SQL语句(找一台车装货！)
            ps = conn.prepareStatement(sql);
            //ps：指代预编译对象(车)
            //(5). 通过预编译对象将SQL语句发送到MySQL并执行该SQL语句！
            //1>. 执行DML语句：ps.executeUpdate();   (方法返回整数结果)
            //2>. 执行DQL语句：ps.executeQuery();    (方法返回结果集结果)
            ps.setString(1,name);
            rs = ps.executeQuery();

            while (rs.next()){
                int id = rs.getInt("id");
                int age = rs.getInt("age");
                double salary = rs.getDouble("salary");
                String born = rs.getString("born");
                people.add(new Person(id, name, age, salary, born));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs,ps,conn);
        }
        return people;
    }

    @Override
    public Person selectById(int id) {
        Connection conn=JDBCUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Person person = null;
        try {
            String sql = "select id, name, age, salary, born from person where id = ?";
            //(4). 获取预编译对象装载SQL语句(找一台车装货！)
            ps = conn.prepareStatement(sql);
            //ps：指代预编译对象(车)
            //(5). 通过预编译对象将SQL语句发送到MySQL并执行该SQL语句！
            //1>. 执行DML语句：ps.executeUpdate();   (方法返回整数结果)
            //2>. 执行DQL语句：ps.executeQuery();    (方法返回结果集结果)
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.next();
            String name = rs.getString("name");
            int age = rs.getInt("age");
            double salary = rs.getDouble("salary");
            String born = rs.getString("born");
            person = new Person(id, name, age, salary, born);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs,ps,conn);
        }
        return person;
    }
}
