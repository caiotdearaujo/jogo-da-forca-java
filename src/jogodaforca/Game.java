package jogodaforca;

import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;

public class Game {

	protected String title = "Jogo da Forca";
	private Random random = new Random();

	public void play() {
		
		int tries = 0;

		String word = getWord();
		char[] hiddenWord = hideWord(word);

		char guess;
		ArrayList<Integer> indexArray;

		boolean loopFinished = true;

		while (charInArray('_', hiddenWord)) {
			guess = getGuess(hiddenWord, tries);

			if (guess == '#') {
				loopFinished = false;
				break;
			}

			indexArray = checkGuess(guess, word);

			tries++;

			for (int i : indexArray) {
				hiddenWord[i] = guess;
			}
		}

		if (loopFinished) {
			JOptionPane.showMessageDialog(null, "Parabéns, a palavra era %s!%sVocê acertou em %d tentativas!"
					.formatted(word, System.lineSeparator(), tries), title, 1);
		}

	}

	private String getWord() {

		String[] words = { "macaco", "ornintorrinco" };
		String word = words[random.nextInt(words.length)];

		return word;

	}

	private char[] hideWord(String word) {

		int wordLength = word.length();
		char[] hiddenWord = new char[wordLength];

		for (int i = 0; i < wordLength; i++) {
			hiddenWord[i] = '_';
		}

		return hiddenWord;

	}

	private boolean charInArray(char c, char[] a) {

		for (char i : a) {
			if (c == i) {
				return true;
			}
		}

		return false;

	}

	private char getGuess(char[] hiddenWord, int tries) {

		String userInput;
		char guess;

		String stringHiddenWord = charArrayToString(hiddenWord);
		String message = "Tentativas: %d%s%s%sInsira uma letra:".formatted(tries, System.lineSeparator(),
				stringHiddenWord, System.lineSeparator());

		while (true) {
			userInput = JOptionPane.showInputDialog(null, message, title, 3);

			if (userInput == null) {
				return '#';
			}

			if (userInput.length() == 1) {
				if (userInput.matches("[a-zA-Z]*")) {
					guess = userInput.toLowerCase().charAt(0);
					return guess;
				} else {
					JOptionPane.showMessageDialog(null, "Insira apenas letras!", title, 0);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Insira apenas uma letra!", title, 0);
			}
		}

	}

	private String charArrayToString(char[] a) {

		String string = new String();

		for (Character i : a) {
			string = string.concat(i.toString());
		}

		return string;

	}

	private ArrayList<Integer> checkGuess(char guess, String word) {

		ArrayList<Integer> indexList = new ArrayList<>();

		for (int i = 0; i < word.length(); i++) {
			if (word.charAt(i) == guess) {
				indexList.add(i);
			}
		}

		if (indexList.isEmpty()) {
			JOptionPane.showMessageDialog(null, "A letra " + guess + " não está na palavra!", title, 0);
		}

		return indexList;

	}

}
