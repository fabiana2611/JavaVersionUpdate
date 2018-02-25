package br.bia.diff.j8.support;

public class Person {

	public Person(){
		
	}
	
	public Person(String name, String lastName){
		this.name = name;
		this.lastName = lastName;
	}
	
	public Person(String name, Integer age){
		this.name = name;
		this.age = age;
	}
	
	private Integer age;
    private String name;
    private String lastName;

    public Integer getAge() {
    	return age;
    }
    	
    public void setAge(Integer age){
    	this.age = age;
    }

    public void printPerson() {
       toString();
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String toString(){
		return name + lastName;
	}
    
    
}
