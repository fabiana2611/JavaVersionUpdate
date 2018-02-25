package br.bia.diff.j8;

import java.util.Optional;

import br.bia.diff.j8.support.Person;

public class OptionalExamples {

	/**
	 * Optional.of(x) to wrap a non-null value, 
	 * Optional.empty() to represent a missing value, or
	 * Optional.ofNullable(x) to create an Optional from a reference that may or may not be null.
	 * After creating an instance of Optional, you then use isPresent() to determine if the 
	 * there is a value and get() to get the value, and orElse(T) to returns the given default 
	 * value if the Optional is empty.
	 */
	public void nullValues(){
		System.out.println("--- Optional [value=3]:");
		Integer nullValue = null;
		Integer value = 3;
		
		System.out.println("0: [Optional.empty()]: "+ Optional.empty());
		
		try{
			Optional.of(nullValue);
		} catch (NullPointerException e){
			System.out.println("1: [Optional.of(nullValue)]: Exception "+ e.getClass());
		}
		
		System.out.println("2: [Optional.ofNullable(nullValue)]: "+ Optional.ofNullable(nullValue));
		
		System.out.println("3: [Optional.of(value)]: "+ Optional.of(value));
		System.out.println("4: [Optional.of(value).get()]: "+ Optional.of(value).get());
		System.out.println("5: [Optional.ofNullable(value)]: "+ Optional.ofNullable(value));
		
		System.out.println("6: [Optional.ofNullable(nullValue).orElse(5)]: "+ Optional.ofNullable(nullValue).orElse(5));
		System.out.println("7: [Optional.ofNullable(value).orElse(5)]: "+ Optional.ofNullable(value).orElse(5));
		
		System.out.println("8: [Optional.of(value).orElse(5)]: "+ Optional.of(value).orElse(5));
		
		System.out.println();
	}
	
	public void isPresent(){
		Person person = new Person();
		Optional<Person> op = Optional.of(person);
		System.out.println("isPresent: "+op.isPresent());
		
		System.out.println();
	}
	
	public void defaultValue(){
		System.out.println("--- defaultValue:");
		Optional<Person> o = Optional.empty();
		System.out.println("isPresent: "+o.isPresent());
		Person p = o.orElse(new Person("Ana","Maria"));
		System.out.println("p [orElse]:"+p.toString());
		
		System.out.println();
	}
}
