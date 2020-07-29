package de.claudioaltamura.java.jsonassert;

public class Employee {

  private long id;

  private String name;

  public Employee(long id, String name) {
    this.id = id;
    this.name = name;
  }

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return "Employee{" + "id=" + id + ", name='" + name + '\'' + '}';
  }
}
