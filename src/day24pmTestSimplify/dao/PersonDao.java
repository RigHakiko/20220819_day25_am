package day24pmTestSimplify.dao;

import day24pmTestSimplify.model.Person;

import java.util.List;

public interface PersonDao {
    int add(Person person);
    int update(Person person);
    int delete(int id);
    List<Integer> selectId();
    List<Person> selectAll();
    List<Person> selectByName(String name);
    Person selectById(int id);
}
