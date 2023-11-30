import java.util.Scanner;
import java.util.Iterator;
import java.util.HashMap;
import java.util.TreeMap;

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
	private final TreeMap<Token, Token> wordsByNaturalOrder = new TreeMap<>(Token.CompFreqAsc);
	private final TreeMap<Token, Token> wordsByLength = new TreeMap<>(Token.CompLengthDesc);
	private final TreeMap<Token, Token> wordsByFreqDesc = new TreeMap<>(Token.CompFreqDesc);

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
		int printSize = Math.min(10, wordsByFreqDesc.size());
		Iterator<Token> frequencyIterator = wordsByFreqDesc.values().iterator();
		if (printSize != 0) {
			while (printSize > 0 && frequencyIterator.hasNext()) {
				printSize--;
				Token temp = frequencyIterator.next();
				System.out.println(temp.toString());
			}
		}
		System.out.println();

		System.out.println("10 Longest");
		printSize = Math.min(10, wordsByLength.size());
		Iterator<Token> lengthIterator = wordsByLength.values().iterator();
		if (printSize != 0) {
			while (printSize > 0 && lengthIterator.hasNext()) {
				printSize--;
				Token temp = lengthIterator.next();
				System.out.println(temp.toString());
			}
		}
		System.out.println();

		System.out.println("The longest word is " + returnLongestWord(wordsByLength));
		System.out.println("The shortest word is " + returnShortestWord(wordsByLength));
		System.out.println("The average word length is " + avgLength());
		System.out.println();
		
		System.out.println("All");
		printSize = wordsByNaturalOrder.size();
		Iterator<Token> naturalIterator = wordsByNaturalOrder.values().iterator();
		if (printSize != 0) {
			while (printSize > 0 && naturalIterator.hasNext()) {
				printSize--;
				Token temp = naturalIterator.next();
				System.out.println(temp.toString());
			}
		}
		System.out.println();
	}

	/**
	 * Method to print the average length of words in the file
	 * @return length the average length of words
	 */
	private int avgLength() {
		int totalLength = 0;
		int wordCount = 0;

        // Iterate over the words in the tree
        for (Token token : wordsByNaturalOrder.values()) {
            totalLength += token.getWord().length();
            wordCount++;
        }

		// Calculate and return the average length
		// If there are words in the tree, compute the average; otherwise, return 0 to avoid division by zero
		return (wordCount > 0) ? totalLength / wordCount : 0;
	}

	/**
	 * Method to return the shortest word in the treemap
	 * @param wordsByLength2 the treemap
	 * @return shortest the shortest word, if null returns "None"
	 */
	private String returnShortestWord(TreeMap<Token, Token> wordsByLength2) {
		if (wordsByLength2.isEmpty()) {
			return "None";
		}
		else {
			Iterator<Token> iterator = wordsByLength2.values().iterator();
			Token shortest = iterator.next();

			while (iterator.hasNext()) {
				shortest = iterator.next();
			}
			return (shortest != null) ? shortest.getWord() : "None";
		}
	}

	/**
	 * Method to return the longest word in the treemap
	 * @param wordsByLength2 the treemap
	 * @return longest the longest word, if null returns "None"
	 */
	private String returnLongestWord(TreeMap<Token, Token> wordsByLength2) {
		if (wordsByLength2.isEmpty()) {
			return "None";
		}
		else {
			Iterator<Token> iterator = wordsByLength2.values().iterator();
			Token longest = iterator.next();
			return (longest != null) ? longest.getWord() : "None";
		}
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
		// This is where you add them to the tree maps

        for (Token temp : tokens.values()) {
			//Add to wordsByNaturalOrder (to order alphabetically)
            wordsByNaturalOrder.put(temp, temp);

            //Add to wordsByLength (to order by descending length)
            wordsByLength.put(temp, temp);

            //Add to wordsByFreq treemap (to order by descending frequency)
            wordsByFreqDesc.put(temp, temp);
        }
	}
}
