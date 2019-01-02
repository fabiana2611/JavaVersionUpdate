package br.bia.upgrade.objective.stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class Streams {

	public static void main(String[] args) {
//		Stream.of("sun", "pool", "beach", "kid", "island", "sea", "sand")
//	    .map(str -> str.length())
//	    .filter(i -> i > 3)
//	    .limit(2)
//	    .forEach(System.out::println);
//		
//		Stream.of("sun", "pool", "beach", "kid", "island", "sea", "sand")
//	    .filter(str -> str.length() > 3)
//	    .limit(2)
//	    .forEach(System.out::println);
		
//		Stream<String> stream = Stream.iterate("", (s) -> s + "1");
//		System.out.println(stream.limit(2).map(x -> x + "2"));
		
//		String[] words = {"Hello", "world"};
//		Stream<String> streamOfWords = Arrays.stream(words);
//		System.out.println(streamOfWords
//				.map(word -> word.split("")) 
//				.flatMap(Arrays::stream)
//				.distinct()
//				.collect(Collectors.toList()));
		
		// Square
//		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5); 
//		List<Integer> squares =
//				numbers.stream()
//				.map(n -> n * n)
//				.collect(Collectors.toList());
//		
//		
//		System.out.println(squares);
		
		// pair numbers
//		List<Integer> numbers1 = Arrays.asList(1, 2, 3); 
//		List<Integer> numbers2 = Arrays.asList(3, 4); 
//		List<int[]> pairs =
//				numbers1.stream()
//				.flatMap(i -> numbers2.stream().map(j -> new int[]{i, j}))
//				.collect(Collectors.toList());
//		System.out.println(pairs);
		
		// Pairs divisible by 3
//		List<Integer> numbers1 = Arrays.asList(1, 2, 3); 
//		List<Integer> numbers2 = Arrays.asList(3, 4); 
		//List<int[]> pairs =
//				numbers1.stream()
//				.flatMap(i -> numbers2.stream()
//						.filter(j -> (i + j) % 3 == 0)
//						.map(j -> new int[]{i, j})) 
//				.collect(Collectors.toList());
		//System.out.println(pairs);
		
		test();

	}
	
	private static void test() {
		Trader raoul = new Trader("Raoul", "Cambridge");
		Trader mario = new Trader("Mario","Milan"); Trader alan = new Trader("Alan","Cambridge"); Trader brian = new Trader("Brian","Cambridge");
		List<Transaction> transactions = Arrays.asList( new Transaction(brian, 2011, 300),
		new Transaction(raoul, 2012, 1000),
		new Transaction(raoul, 2011, 400),
		new Transaction(mario, 2012, 710), new Transaction(mario, 2012, 700), new Transaction(alan, 2012, 950)
		);
		
		//findTransactionByYearSortByValue(transactions);
		//uniqueCities(transactions);
		//findTradesSortByName(transactions);
		//alphabeticallyOrder(transactions);
		//tradersBase(transactions);
		tradersLivingCambridge(transactions);
		highestValue(transactions);
		smallestValue(transactions);
	}
	
	// Find all transactions in the year 2011 and sort them by value (small to high).
	private static void findTransactionByYearSortByValue (List<Transaction> transactions) {
		List<Transaction> tr2011 = transactions.stream()
				.filter(transaction -> transaction.getYear() == 2011)
				.sorted(Comparator.comparing (Transaction::getValue))
				.collect(Collectors.toList());
		System.out.println(tr2011);
	}
	
	// What are all the unique cities where the traders work
	private static void uniqueCities (List<Transaction> transactions) {
		List<String> cities = transactions.stream()
				.map(transaction -> transaction.getTrader().getCity())
				.distinct()
				.collect(Collectors.toList());
		System.out.println(cities);
		
		// or similar
		Set<String >citiesSet = transactions.stream()
				.map(transaction -> transaction.getTrader().getCity())
				.collect(Collectors.toSet());
		System.out.println(citiesSet);
	}
	
	//Find all traders from Cambridge and sort them by name. 
	private static void findTradesSortByName (List<Transaction> transactions) {
		List<Trader> traders = transactions.stream()
				.map(Transaction::getTrader)
				.filter(trader -> trader.getCity().equals("Cambridge"))
				.distinct()
				.sorted(Comparator.comparing(Trader::getName))
				.collect(Collectors.toList());
		System.out.println(traders);		
	}
	
	// Return a string of all traders’ names sorted alphabetically.
	private static void alphabeticallyOrder (List<Transaction> transactions) {
		String traderStr = 
				transactions.stream()
				.map(transaction->transaction.getTrader().getName())
				.distinct()
				.sorted()
				.reduce("", (n1,n2)->n1+n2);
		System.out.println(traderStr);
		
		// or
		String traderStr2 = 
				transactions.stream()
				.map(transaction->transaction.getTrader().getName())
				.distinct()
				.sorted()
				.collect(Collectors.joining());
		System.out.println(traderStr2);
	}
	
	//Are any traders based in Milan? 
	private static void tradersBase (List<Transaction> transactions) {
		boolean millanBased = transactions.stream()
				.anyMatch(transaction->transaction.getTrader().getCity().equals("Milan"));
		System.out.println(millanBased);
	}
	
	// Print all transactions’ values from the traders living in Cambridge. 
	private static void tradersLivingCambridge (List<Transaction> transactions) {
		transactions.stream()
		.filter(t->"Cambridge".equals(t.getTrader().getCity()))
		.map(Transaction::getValue)
		.forEach(System.out::println);
	}
	
	// What’s the highest value of all the transactions?	
	private static void highestValue (List<Transaction> transactions) {
		Optional<Integer> highestValue = transactions.stream()
				.map(Transaction::getValue)
				.reduce(Integer::max);
		System.out.println(highestValue.get());
	}

 	// Find the transaction with the smallest value.
	private static void smallestValue (List<Transaction> transactions) {
		Optional<Transaction> smallest = transactions.stream()
				.reduce((t1,t2) -> t1.getValue() < t2.getValue() ? t1:t2);
		System.out.println(smallest.get().getValue());
		
		// or
		Optional<Transaction> smallest2 = transactions.stream()
				.min(Comparator.comparing(Transaction::getValue));
		System.out.println(smallest2.get().getValue());
		
	}
}
