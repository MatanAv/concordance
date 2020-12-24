package concordance;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Program {

	public static void main(String[] args) throws IOException {

		FileReader file = new FileReader("text.txt");
		BufferedReader br = new BufferedReader(file);
		List wordList = new List();
		
		insertWords(br, wordList);		// O(k*n^2)		n = numOfWords,	k = numOfLines
		br.close();

		wordList.sort();		// O(n*lg n)	QuickSort
		
		wordList.find("Dog");

		System.out.println(wordList.toString());		// O(n*k)	(wordList*linesList)
		
	}

	private static void insertWords(BufferedReader br, List wordList) {			// O(k*n^2)
		
		String line;
		String nextWord = "";
		int lineCount = 0;
	
		try {
			
			while ((line = br.readLine()) != null) {	// k times
				
				lineCount++;
				
				for (int i = 0; i < line.length(); i++) {		// n times
					
					if (Character.isAlphabetic(line.charAt(i)))
						nextWord += line.charAt(i);
					else if (!nextWord.isEmpty()) {
						Word checkWord = null;
						
						checkWord = wordList.find(nextWord);		// O(n)	n times
						
						if (checkWord != null)
							checkWord.addLine(lineCount);			// adding new line number into the linked list
						else
							wordList.add(new Word(nextWord, lineCount));			// inserting the word into the list
						
						nextWord = "";
					}
					
				}
				
			}
			
			wordList.trimToSize();
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
}
