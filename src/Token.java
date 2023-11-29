import java.util.Comparator;

/**
 * 
 * A Token object represents a word (string) and how many times it has
 * occurred in a given text.
 */
public class Token implements Comparable<Token> {

	// The word!
	private String word;
	// How many times it has occurred
	private int count;

	/**
	 * Constructor. Set the string and initialize the count to 0.
	 * 
	 * @param w the string representing this word.
	 */
	public Token(String w) {
		this.word = w;
		this.count = 1;
	}

	/**
	 * Comparator to sort from high to low frequency (count).
	 */
	public static Comparator<Token> CompFreqDesc = new Comparator<Token>() {
		/**
		 * Method to compare two objects
		 * @param w1 the first object to be compared.
		 * @param w2 the second object to be compared.
		 * @return comparison the difference between the frequency in descending order, if equal alphabetical
		 */
		public int compare(Token w1, Token w2) {
			int f1 = w1.getCount();
			int f2 = w2.getCount();
			if (f2 - f1 == 0)
				return w1.compareTo(w2);
			else
				return f2 - f1;
		}
	};

	/**
	 * Comparator to sort from low to high frequency (count).
	 */
	public static Comparator<Token> CompFreqAsc = new Comparator<Token>() {
		/**
		 * Method to compare two objects
		 * @param w1 the first object to be compared.
		 * @param w2 the second object to be compared.
		 * @return comparison the difference between the frequency in ascending order, if equal alphabetical
		 */
		public int compare(Token w1, Token w2) {

			int f1 = w1.getCount();
			int f2 = w2.getCount();

			if (f1 - f2 == 0)
				return w1.compareTo(w2);
			else
				return f1 - f2;
		}
	};

	/**
	 * Comparator to sort from high to low word length.
	 */
	public static Comparator<Token> CompLengthDesc = new Comparator<Token>() {

		/**
		 * Method to compare two objects
		 * @param w1 the first object to be compared.
		 * @param w2 the second object to be compared.
		 * @return comparison the difference between the lengths, if equal alphabetical
		 */
		public int compare(Token w1, Token w2) {

			int f1 = w1.getLength();
			int f2 = w2.getLength();

			if (f1 - f2 == 0)
				return w1.compareTo(w2);
			else
				return f2 - f1;
		}
	};

	/**
	 * Method to return the word of a token
	 * @return word the word of a token
	 */
	public String getWord() {
		return word;
	}

	/**
	 * Method to reutrn the count of a token
	 * @return count the frequency of a token
	 */
	public int getCount() {
		return count;
	}

	/**
	 * Method to increment the count of a token
	 */
	public void incrementCount() {
		count++;
	}

	/**
	 * Return the length of the word.
	 */
	public int getLength() {
		return getWord().length();
	}

	/**
	 * Method to return token as a string
	 * @return token in string format with its values separated by colons.
	 */
	public String toString() {
		return getWord() + " : " + getLength() + " : " + getCount();
	}

	/**
	 * Method to compare two objects.
	 * A word is equal to another word if the string word is equal.
	 */
	public boolean equals(Object other) {
		if (other == this)
			return true;
		if (other == null)
			return false;
		if (this.getClass() != other.getClass())
			return false;
		Token p = (Token) other;
		return this.getWord().equals(p.getWord());
	}

	/**
	 * Method to compare two words. This will order Words alphabetically.
	 */
	public int compareTo(Token o) {
		if (this.equals(o))
			return 0;
		else
			return this.getWord().compareTo(o.getWord());
	}
}
