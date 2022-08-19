package day24pmTestSimplify02.view;


import day24pmTestSimplify02.dao.impl.PersonDaoImpl;
import day24pmTestSimplify02.model.Person;

import java.util.List;
import java.util.Scanner;

public class PersonView {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PersonDaoImpl personDao = new PersonDaoImpl();
        select1: while (true) {
            System.out.println("请选择：1. 添加信息 2. 修改信息 3. 删除信息 4. 查询信息 5. 退出");
            int choose1 = scanner.nextInt();
            switch (choose1) {
                case 1: {
                    System.out.println("请输入姓名: ");
                    String name = scanner.next();
                    System.out.println("请输入年龄: ");
                    Integer age = scanner.nextInt();
                    System.out.println("请输入工资: ");
                    Double salary = scanner.nextDouble();
                    System.out.println("请输入出生日期: ");
                    String born = scanner.next();
                    Person person = new Person();
                    person.setName(name);
                    person.setAge(age);
                    person.setSalary(salary);
                    person.setBorn(born);
                    int result = personDao.add(person);

                    if (result > 0) {
                        System.out.println("添加成功");
                    } else {
                        System.out.println("添加失败");
                    }


                    break;
                }

                case 2: {
                    System.out.println("所有编号如下:");
                    List<Integer> ids = personDao.selectId();
                    for (Integer i :
                            ids) {
                        System.out.print(i + " ");
                    }
                    System.out.println();
                    System.out.println("请输入要修改的人的编号：");
                    int id = scanner.nextInt();

                    if (personDao.selectById(id) == null) {
                        System.out.println("编号错误! ");
                        break;
                    }

                    System.out.println("请输入修改后的姓名:");
                    String name = scanner.next();
                    System.out.println("请输入修改后的年龄:");
                    int age = scanner.nextInt();
                    System.out.println("请输入修改后的工资:");
                    double salary = scanner.nextDouble();
                    System.out.println("请输入修改后的出生日期:");
                    String born = scanner.next();
                    int result = personDao.update(new Person(id, name, age, salary, born));
                    if (result > 0) {
                        System.out.println("修改成功");
                    } else {
                        System.out.println("修改失败");
                    }
                    break;
                }


                case 3: {
                    System.out.println("所有编号如下:");
                    List<Integer> ids = personDao.selectId();
                    for (Integer i :
                            ids) {
                        System.out.print(i + " ");
                    }
                    System.out.println();
                    System.out.println("请输入要删除的人的编号：");
                    int id = scanner.nextInt();
                    int result = personDao.delete(id);
                    if (result > 0) {
                        System.out.println("删除成功");
                    } else {
                        System.out.println("删除失败");
                    }
                    break;
                }

                case 4://查询信息
                {
                    System.out.println("请选择：1. 查询所有信息  2. 根据姓名模糊查询  3. 根据编号查询");
                    int choose = scanner.nextInt();
                    switch (choose) {
                        case 1: {
                            List<Person> people = personDao.selectAll();
                            for (Person person :
                                    people) {
                                System.out.println(person);
                            }
                            break;
                        }
                        case 2: {
                            System.out.println("请输入姓名: ");
                            String name = scanner.next();
                            List<Person> people = personDao.selectByName(name);
                            for (Person person :
                                    people) {
                                System.out.println(person);
                            }
                            break;
                        }
                        case 3: {
                            System.out.println("请输入编号: ");
                            int id = scanner.nextInt();
                            Person person = personDao.selectById(id);
                            System.out.println(person);
                            break;
                        }
                        default:
                            System.out.println("输入有误");
                    }
                    break;
                }

                case 5: {

                    System.out.println("退出成功");
                    break select1;
                }
                default:
                    System.out.println("输入错误请重试");
            }
        }
    }
}
