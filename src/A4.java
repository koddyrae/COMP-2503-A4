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
	private TreeMap<Token, Token> wordsByNaturalOrder, wordsByLength, wordsByFreqDesc;
	
	// TODO: Must initialize the above HashMap and the TreeMap objects before using them.

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
	private int stopTokenCount = 0;

	public static void main(String[] args) {
		A4 a4 = new A4();
		a4.run();
	}
	
	/* Run the program. */
	private void run() {
		readFile();
		removeStop();
		createFreqLists();
		printResults();
	}

	private void printResults() {
		System.out.println("Total Words: " + totalTokenCount);
		System.out.println("Unique Words: " + tokens.size());
		System.out.println("Stop Words: " + stopTokenCount);
		System.out.println();

		System.out.println("10 Most Frequent");
		
		// TODO: print the 10 most frequent   
		
		System.out.println();

		System.out.println("10 Longest");

		// TODO: print the 10 longest
		
		System.out.println();

		System.out.println("The longest word is " + returnLongestWord(wordsByLength));
		System.out.println("The shortest word is " + returnShortestWord(wordsByLength));
		System.out.println("The average word length is " + avgLength());

		System.out.println();
		System.out.println("All");
				
		// TODO: print all words in alphabetical order
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
	
	private void removeStop() {
		//TODO: stop word counter needs to be added

		for (String word : stopTokens) {
			//stopWordCounter++;
			// if you had the stopWordCounter here i think it increments for each iteration regardless of the actual stop word
			tokens.remove(word);
		}
	}	

	private void createFreqLists() {
		// TODO Auto-generated method stub
		// This is where you add them to the tree maps
	}
}
