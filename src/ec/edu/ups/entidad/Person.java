package ec.edu.ups.entidad;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.json.bind.annotation.JsonbNumberFormat;
import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTransient;

public class Person {

    private int id;

    private String name;

    private String email;
    private int age;
    private LocalDate registeredDate;

    private BigDecimal salary;

    public BigDecimal getSalary() {
	return salary;
    }

    public Person() {

    }

    public Person(int id, String name, String email, int age, LocalDate registeredDate, BigDecimal salary) {
	this.id = id;
	this.name = name;
	this.email = email;
	this.age = age;
	this.registeredDate = registeredDate;
	this.salary = salary;
    }       

    public Person(int id, String name) {
	this.id = id;
	this.name = name;
    }

    // Necesario para que pueda ser parmetro: "id,name"
    public static Person valueOf(String s) {
	Person u = new Person();
	try {
	    String id = s.substring(0, s.indexOf(","));
	    u.setId(Integer.valueOf(id));
	    u.setName(s.substring(s.indexOf(",") + 1));
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return u;
    }

    // getters & setters

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public int getAge() {
	return age;
    }

    public void setAge(int age) {
	this.age = age;
    }

    public LocalDate getRegisteredDate() {
	return registeredDate;
    }

    public void setRegisteredDate(LocalDate registeredDate) {
	this.registeredDate = registeredDate;
    }

    public void setSalary(BigDecimal salary) {
	this.salary = salary;
    }

    @Override
    public String toString() {
	return "Person [id=" + id + ", name=" + name + ", email=" + email + ", age=" + age + ", registeredDate="
		+ registeredDate + ", salary=" + salary + "]";
    }

}
