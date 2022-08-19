package day24pmTestSimplify.dao.impl;

import day24pmTestSimplify.dao.PersonDao;
import day24pmTestSimplify.model.Person;

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
        Connection conn = null;
        PreparedStatement ps = null;
        int result = 0;
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            String sql = "insert into person (name, age, salary, born) values (?, ?, ?, ?) ";
            ps = conn.prepareStatement(sql);
            ps.setString(1, person.getName());
            ps.setInt(2, person.getAge());
            ps.setDouble(3, person.getSalary());
            ps.setString(4, person.getBorn());
            result = ps.executeUpdate();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                ps.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }

    @Override
    public int update(Person person) {
        //尝试使用JDBC代码连接数据库进行添加操作！(SQL语句由Java编写发送到数据库！)
        Connection conn = null;
        PreparedStatement ps = null;
        int result = 0;
        try {
            //(1). 加载JDBC驱动(保证JDBC可以运行！)(获取Driver类的字节码信息！)
            Class.forName(DRIVER);
            //(2). 获取连接对象(建立和MySQL的连接通道)
            //注意：选择java.sql包下的文件！！！
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            //conn：指代连接数据库的通道！
            //(3). 准备SQL语句(通过定义字符串变量即可！)
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
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //(6). 关闭资源(后创建的资源先关闭！！！)
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public int delete(int id) {
        //尝试使用JDBC代码连接数据库进行添加操作！(SQL语句由Java编写发送到数据库！)
        Connection conn = null;
        PreparedStatement ps = null;
        int result = 0;
        try {
            //(1). 加载JDBC驱动(保证JDBC可以运行！)(获取Driver类的字节码信息！)
            Class.forName(DRIVER);
            //(2). 获取连接对象(建立和MySQL的连接通道)
            //注意：选择java.sql包下的文件！！！
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            //conn：指代连接数据库的通道！
            //(3). 准备SQL语句(通过定义字符串变量即可！)
            String sql = "delete from person where id = ?";
            //(4). 获取预编译对象装载SQL语句(找一台车装货！)
            ps = conn.prepareStatement(sql);
            //ps：指代预编译对象(车)
            //(5). 通过预编译对象将SQL语句发送到MySQL并执行该SQL语句！
            //1>. 执行DML语句：ps.executeUpdate();   (方法返回整数结果)
            //2>. 执行DQL语句：ps.executeQuery();    (方法返回结果集结果)
            ps.setInt(1, id);
            result = ps.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //(6). 关闭资源(后创建的资源先关闭！！！)
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public List<Integer> selectId() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Integer> integers = new ArrayList<>();
        try {
            //(1). 加载JDBC驱动(保证JDBC可以运行！)(获取Driver类的字节码信息！)
            Class.forName(DRIVER);
            //(2). 获取连接对象(建立和MySQL的连接通道)
            //注意：选择java.sql包下的文件！！！
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            //conn：指代连接数据库的通道！
            //(3). 准备SQL语句(通过定义字符串变量即可！)
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

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //(6). 关闭资源(后创建的资源先关闭！！！)
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return integers;
    }

    @Override
    public List<Person> selectAll() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Person> people = new ArrayList<>();
        try {
            //(1). 加载JDBC驱动(保证JDBC可以运行！)(获取Driver类的字节码信息！)
            Class.forName(DRIVER);
            //(2). 获取连接对象(建立和MySQL的连接通道)
            //注意：选择java.sql包下的文件！！！
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            //conn：指代连接数据库的通道！
            //(3). 准备SQL语句(通过定义字符串变量即可！)
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

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //(6). 关闭资源(后创建的资源先关闭！！！)

            try {
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return people;
    }

    @Override
    public List<Person> selectByName(String name) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Person> people = new ArrayList<>();
        try {
            //(1). 加载JDBC驱动(保证JDBC可以运行！)(获取Driver类的字节码信息！)
            Class.forName(DRIVER);
            //(2). 获取连接对象(建立和MySQL的连接通道)
            //注意：选择java.sql包下的文件！！！
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            //conn：指代连接数据库的通道！
            //(3). 准备SQL语句(通过定义字符串变量即可！)
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


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //(6). 关闭资源(后创建的资源先关闭！！！)

            try {
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return people;
    }

    @Override
    public Person selectById(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Person person = null;
        try {
            //(1). 加载JDBC驱动(保证JDBC可以运行！)(获取Driver类的字节码信息！)
            Class.forName(DRIVER);
            //(2). 获取连接对象(建立和MySQL的连接通道)
            //注意：选择java.sql包下的文件！！！
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            //conn：指代连接数据库的通道！
            //(3). 准备SQL语句(通过定义字符串变量即可！)
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

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //(6). 关闭资源(后创建的资源先关闭！！！)

            try {
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return person;
    }
}
