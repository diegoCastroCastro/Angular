package ec.edu.ups.rest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

import ec.edu.ups.ejb.UsuarioFacade;
import ec.edu.ups.entidad.*;

public class Test {

	public static void main(String[] args) {
/**
		// create a simple Person object like this:
		Person person1 = new Person(1, "Jhon", "jhon@test.com", 20, LocalDate.of(2020, 6, 30),
				BigDecimal.valueOf(1000));

		// instantiate the Jsonb class:
		Jsonb jsonb = JsonbBuilder.create();

		// use the toJson method:
		String jsonPerson = jsonb.toJson(person1);

		// print the JSON object
		System.out.println(jsonPerson);

		// if we want to do the conversion the other way, we can use the fromJson
		// method:
		Person person2 = jsonb.fromJson(jsonPerson, Person.class);

		// print the Person object
		System.out.println(person2);

		// we can also process collections:
		List<Person> personList1 = new ArrayList<Person>();
		personList1.add(
				new Person(1, "Pepito", "pepito@test.com", 20, LocalDate.of(2020, 6, 30), BigDecimal.valueOf(1000)));
		personList1.add(new Person(2, "Juanito", "juanito@test.com", 20, LocalDate.of(2020, 6, 20),
				BigDecimal.valueOf(1231.23)));

		String jsonArrayPerson = jsonb.toJson(personList1);
		// print the JSON Array
		System.out.println(jsonArrayPerson);

		// to convert from JSON array to List we'll use the fromJson API:
		List<Person> personList2 = jsonb.fromJson(jsonArrayPerson, new ArrayList<Person>() {
		}.getClass().getGenericSuperclass());

		// print the List
		System.out.println(personList2);
		*/
		
		
		
		
	}
}
