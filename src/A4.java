import java.util.Scanner;
import java.util.Iterator;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.Collection;
import java.util.Set;

/**
 * 
 * This program reads a text file and compiles a list of the Tokens together
 * with the frequency of the Tokens. Use HashMaps from Java collections
 * Framework for storing the Tokens. Tokens from a standard list of stop Tokens
 * are then deleted. Then create TreeMaps with ordering based on frequency
 * (ascending and descending) in order to produce the required output.
 * 
 */
public class A4 {
	/* The HashMap of Tokens. */
	private final HashMap<String, Token> tokens = new HashMap<>();

	/* The ordered tree maps of Tokens. */
	private TreeMap<Token, Token> wordsByNaturalOrder = new TreeMap<>(Token.CompFreqAsc);
	private TreeMap<Token, Token> wordsByLength = new TreeMap<>(Token.CompLengthDesc);
	private TreeMap<Token, Token> wordsByFreqDesc = new TreeMap<>(Token.CompFreqDesc);

	// there are 103 stopTokens in this list
	private final String[] stopTokens = { "a", "about", "all", "am", "an", "and", "any", "are", "as", "at", "be", "been",
			"but", "by", "can", "cannot", "could", "did", "do", "does", "else", "for", "from", "get", "got", "had",
			"has", "have", "he", "her", "hers", "him", "his", "how", "i", "if", "in", "into", "is", "it", "its", "like",
			"more", "me", "my", "no", "now", "not", "of", "on", "one", "or", "our", "out", "said", "say", "says", "she",
			"so", "some", "than", "that", "thats", "the", "their", "them", "then", "there", "these", "they", "this",
			"to", "too", "us", "upon", "was", "we", "were", "what", "with", "when", "where", "which", "while", "who",
			"whom", "why", "will", "you", "your", "up", "down", "left", "right", "man", "woman", "would", "should",
			"dont", "after", "before", "im", "men" };

	private int totalTokenCount = 0;
	private int stopTokenCounter = 0;
	private int printSize = 0;

	/**
	 * Main Method
	 * @param args command lines args
	 */
	public static void main(String[] args) {
		A4 a4 = new A4();
		a4.run();
	}
	
	/**
	 * Method to run the program.
	 */
	private void run() {
		readFile();
		removeStop();
		createFreqLists();
		printResults();
	}

	/**
	 * Method to print results of the required treemaps in desired orders
	 */
	private void printResults() {
		System.out.println("Total Words: " + totalTokenCount);
		System.out.println("Unique Words: " + tokens.size());
		System.out.println("Stop Words: " + stopTokenCounter);
		System.out.println();

		System.out.println("10 Most Frequent");
		printSize = Math.min(10, wordsByFreqDesc.size());
		if (printSize != 0) {
			while (printSize > 0) {
				printSize--;
			}
		}
		System.out.println();

		System.out.println("10 Longest");
		printSize = Math.min(10, wordsByLength.size());
		if (printSize != 0) {
			while (printSize > 0) {
				printSize--;
			}
		}
		System.out.println();

		System.out.println("The longest word is " + returnLongestWord(wordsByLength));
		System.out.println("The shortest word is " + returnShortestWord(wordsByLength));
		System.out.println("The average word length is " + avgLength());

		System.out.println();
		System.out.println("All");
		printSize = wordsByNaturalOrder.size();
		if (printSize != 0) {
			while (printSize > 0) {
				printSize--;
			}
		}
		System.out.println();
	}

	private int avgLength() {
		// TODO Auto-generated method stub
		return 0;
	}

	private String returnShortestWord(TreeMap<Token, Token> wordsByLength2) {
		// TODO Auto-generated method stub
		return "None";
	}

	private String returnLongestWord(TreeMap<Token, Token> wordsByLength2) {
		// TODO Auto-generated method stub
		return "None";
	}

	/**
	 * Method to read the file and add words to the hashmap.
	 */
	private void readFile() {
		Scanner input = new Scanner(System.in);
		while (input.hasNext()) {
			String word = input.next().toLowerCase().trim().replaceAll("[^a-z]", "");

			if (!word.isEmpty()) {
				totalTokenCount++;

				Token newElement = new Token(word);
				Token existElement = tokens.get(word);

				// From my knowledge of hashmaps this should be okay
				if(!newElement.equals(existElement)) {
					tokens.put(word, newElement);
				}
				else {
					existElement.incrementCount();
				}
			}
		}
		input.close();
	}

	/**
	 * Method to check and remove the array of stop words from the hashmap if present.
	 */
	private void removeStop() {
		for (String word : stopTokens) {

			//Inner if statement needed to only increment stopTokenCounter for each stop word removed
			if (tokens.containsKey(word)) {
				stopTokenCounter++;
				tokens.remove(word);
			}
		}
	}

	/**
	 * Method to create the natural order, frequency and length lists.
	 */
	private void createFreqLists() {
		// TODO Auto-generated method stub
		// This is where you add them to the tree maps
	}
}
