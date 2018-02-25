package br.bia.diff.j8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import br.bia.diff.j8.support.Person;

/**
 * https://leanpub.com/whatsnewinjava8/read
 * http://www.oracle.com/technetwork/pt/articles/java/processamento-streams-java-se-8-2763688-ptb.html
 * http://www.oracle.com/technetwork/pt/articles/java/streams-api-java-8-3410098-ptb.html
 *
 */
public class StreamExamples {

	public void streamingTextPattern() {
		System.out.println("--- StreamPatter: ");
		Pattern patt = Pattern.compile(",");
		patt.splitAsStream("a,b,c").forEach(System.out::println);
		
		System.out.println();
	}

	public void streamIterate() {
		System.out.println("--- StreamIterate: ");
		Stream.iterate(5, i -> i + 1).limit(10).forEach(System.out::print);
		
		System.out.println();
	}

	// Exclusive
	public void rangeNumber() {
		// Print from 1 to 10
		System.out.println("--- StreamNumberRange: ");
		IntStream.range(1, 11).forEach(System.out::print);
		
		System.out.println();
	}
	
	//Inclusive
	public void rangeClosedNumber() {
		// Print from 1 to 10
		System.out.println("--- RangeClosed: ");
		IntStream.rangeClosed(1, 11).forEach(System.out::print);
		
		System.out.println();
	}

	public void streamingAnything() {
		System.out.println("--- Stream: Anything");
		Stream.of("one", "two", "three", "four").filter(e -> e.length() > 3)
				.peek(e -> System.out.println("Filtered value: " + e)).map(String::toUpperCase)
				.peek(e -> System.out.println("Mapped value: " + e)).collect(Collectors.toList());
		
		Stream<Integer> numbersFromValues = Stream.of(1, 2, 3, 4);
		numbersFromValues.forEach(System.out::println);
		
		int[] numbers = {1, 2, 3, 4}; 
		IntStream numbersFromArray = Arrays.stream(numbers);
		numbersFromArray.forEach(System.out::println);
		
		System.out.println();
	}

	public void streamSortLimit() {
		System.out.println("--- StreamSortLimit: ");
		Random rnd = new Random();
		rnd.ints().limit(10).sorted().forEach(System.out::println);
		
		System.out.println();
	}
	
	public void streamSortedComparator(){
		System.out.println("--- streamSortedComparator: ");
		
		Person anaMaria = new Person("Ana", "Maria");
		Person joseAntonio = new Person("Jose", "Antorio");
		Person anaPaula = new Person("Ana", "Paula");
		List<Person> list = new ArrayList<Person>();
		list.add(anaMaria);
		list.add(joseAntonio);
		list.add(anaPaula);
		
		Stream<Person> stream = list.stream().
		      filter(pessoa -> pessoa.getName().equals("Ana")).
		          sorted(Comparator.comparing(Person::getName) );
		
		stream.forEach(System.out::println);

		System.out.println();
	}
	
	public void streamMach(){
		System.out.println("--- streamMach: ");
		
		Person ana = new Person("Ana", 38);
		Person jose = new Person("Jose", 21);
		Person paula = new Person("Paula", 14);
		List<Person> list = new ArrayList<Person>();
		list.add(ana);
		list.add(jose);
		list.add(paula);
		
		boolean allMach = list.stream().allMatch(p->p.getAge() > 18);
		boolean anyMach = list.stream().anyMatch(p->p.getAge() > 18);
		boolean noneMach = list.stream().noneMatch(p->p.getAge() > 18);
		
		System.out.println("[38, 21, 14]: ");
		System.out.println("allMach > 18: " + allMach);
		System.out.println("anyMach > 18: "+ anyMach);
		System.out.println("noneMach > 18: " + noneMach);
		
		System.out.println();
	}
	
	public void reduce(){
		System.out.println("--- reduce {1,2,3,4,5,6}:");
		Integer[] list = {1,2,3,4,5,6};
		List<Integer> numbers = Arrays.asList(list);
		int mult = numbers.stream().reduce(1, (a, b) -> a * b);
		int max = numbers.stream().reduce(1, Integer::max);
		System.out.println("mult: " + mult);
		System.out.println("max: " + max);
		
		System.out.println();
	}
	
	public void mapToInt(){
		System.out.println("--- sumIntStream: ");
		
		Person ana = new Person("Ana", 38);
		Person jose = new Person("Jose", 21);
		Person paula = new Person("Paula", 14);
		List<Person> list = new ArrayList<Person>();
		list.add(ana);
		list.add(jose);
		list.add(paula);
		
		double ave = list.stream().mapToInt(Person::getAge).average().getAsDouble();
		int sum = list.stream().mapToInt(Person::getAge).sum();
		
		System.out.println("average: " + ave);
		System.out.println("sum: " + sum);
		
		System.out.println();
	}

	public void streamJoinString() {
		System.out.println("--- StreamJoining: ");
		List<String> list = Arrays.asList("Ram", "Shyam", "Shiv", "Mahesh");
		String result = list.stream().collect(Collectors.joining());
		System.out.println(result);
		result = list.stream().collect(Collectors.joining(","));
		System.out.println(result);
		result = list.stream().collect(Collectors.joining("-", "[", "]"));
		System.out.println(result);
		
		System.out.println();
	}

	public void streamJoinObject() {
		System.out.println("--- StreamJoinObject: ");
		
		Person ana = new Person("Ana", "Maria");
		Person jose = new Person("Jose", "Antorio");
		List<Person> list = new ArrayList<Person>();
		list.add(ana);
		list.add(jose);

		System.out.println("--Join person name--");
		String result = list.stream().map(p -> p.getName()).collect(Collectors.joining());
		System.out.println(result);
		result = list.stream().map(p -> p.getName()).collect(Collectors.joining("|"));
		System.out.println(result);
		result = list.stream().map(p -> p.getName()).collect(Collectors.joining("-", "[", "]"));
		System.out.println(result);

		System.out.println("--Join person name-age--");
		result = list.stream().map(p -> p.getName() + "-" + p.getLastName()).collect(Collectors.joining("|"));
		System.out.println(result);
		result = list.stream().map(p -> p.getName() + "-" + p.getLastName()).collect(Collectors.joining("|", "[", "]"));
		System.out.println(result);
		
		System.out.println();
	}

	public void groupingBy1() {
		System.out.println("--- StreamGroupByFirstLatter: ");
		// Group by first letter of name
		Person ana = new Person("Ana", "Maria");
		Person jose = new Person("Jose", "Antorio");
		List<Person> list = new ArrayList<Person>();
		list.add(ana);
		list.add(jose);

		Map<Character, List<Person>> result = list.stream().collect(Collectors.groupingBy(p -> p.getName().charAt(0)));

		System.out.println(result);
		
		System.out.println();
	}

	// https://www.mkyong.com/java8/java-8-collectors-groupingby-and-mapping-example/
	public void groupingBy2() {
		System.out.println("--- StreamGroupByIdentityCounting: ");
		List<String> items = Arrays.asList("apple", "apple", "banana", "apple", "orange", "banana", "papaya");

		Map<String, Long> result = items.stream()
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		System.out.println(result);
	}

	// https://www.concretepage.com/java/jdk-8/java-8-collectors-partitioningby-example
	public void partitioningBy() {
		System.out.println("--- StreamPartitioningBy: ");

		Person s1 = new Person("Ram", 18);
		Person s2 = new Person("Shyam", 22);
		Person s3 = new Person("Mohan", 19);
		Person s4 = new Person("Mahesh", 20);
		Person s5 = new Person("Krishna", 21);

		List<Person> list = Arrays.asList(s1, s2, s3, s4, s5);
		// partition of Student on the basis of age
		System.out.println("----Partition of Student on the basis of age >20 ----");
		Map<Boolean, List<Person>> stdByClass = list.stream().collect(Collectors.partitioningBy(s -> s.getAge() > 20));

		stdByClass.forEach((k, v) -> System.out.println("Key:" + k + "  "
				+ ((List<Person>) v).stream().map(s -> s.getName()).collect(Collectors.joining(","))));
		
		System.out.println();
	}

	public void findingMaximum() {
		
		List<Double> list = new ArrayList<>();
		list.add(1d);
		list.add(10d);
		list.add(5d);
		list.add(10.1d);
		list.add(8.1d);
		
		double max = 0;
		//1 
		max = list.stream().reduce(0.0, Math::max);
		 
		// 2
		 max = list.stream().mapToDouble(Number::doubleValue).max().getAsDouble();
		 
		 System.out.println("StreamFindMaximum [1,10,5,10.1, 8.1]: "+max);
		 
		 System.out.println();
	}

	public void average() {
		System.out.println("---StreamAverage:");
		List<Double> list = new ArrayList<>();
		list.add(1d);
		list.add(10d);
		list.add(5d);
		list.add(10.1d);
		list.add(8.1d);
		
		double ave = list.stream().mapToDouble(Number::doubleValue).average().getAsDouble();
		System.out.println("average [1,10,5,10.1, 8.1]: "+ave);
		
		System.out.println();
	}
	
}
