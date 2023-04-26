package ie.tudublin;

import java.util.ArrayList;

import processing.core.PApplet;

public class DANI extends PApplet {

	ArrayList<Word> model;
	String[] sonnet;
	int sonnetX, sonnetY;


	public void settings() {
		size(1000, 1000);
		//fullScreen(SPAN);
	}


	public void setup() {
		colorMode(HSB);
		model = new ArrayList<Word>();
		loadFile("small.txt");
	}

	public Word findWord(String str) {
		for (Word word : model) {
			if (word.getWord().equals(str)) {
				return word;
			}
		}
		return null;
	}

	public void printModel() {
		for (Word word : model) {
			System.out.println(word);
		}
	}

	public void loadFile(String fileName) {
		String[] lines = loadStrings(fileName);
		model = new ArrayList<Word>();
		for (String line : lines) {
			String[] words = split(line, ' ');
			for (int i = 0; i < words.length; i++) {
				String w = words[i].toLowerCase();
				w = w.replaceAll("[^\\w\\s]", "");
				if (w.length() > 0) {
					Word word = findWord(w);
					if (word == null) {
						word = new Word(w);
						model.add(word);
					}
					if (i + 1 < words.length) {
						String followStr = words[i + 1].toLowerCase();
						followStr = followStr.replaceAll("[^\\w\\s]", "");
						if (followStr.length() > 0) {
							Follow follow = word.findFollow(followStr);
							if (follow == null) {
								follow = new Follow(followStr);
								word.addFollow(follow);
							}
							follow.incrementCount();
						}
					}
				}
			}
		}
	}


	public String[] writeSonnet() {
		String[] sonnet = new String[14];
		for (int i = 0; i < 14; i++) {
			String sentence = "";
			String currentWord = model.get((int) random(model.size())).getWord();
			sentence += currentWord;
			for (int j = 0; j < 7; j++) {
				Word word = findWord(currentWord);
				if (word != null && word.getFollows().size() > 0) {
					Follow follow = word.getFollows().get((int) random(word.getFollows().size()));
					sentence += " " + follow.getWord();
					currentWord = follow.getWord();
				} else {
					break;
				}
			}
			sonnet[i] = sentence;
		}
		return sonnet;
	}


	public void keyPressed() {
		if (key == ' ') {
			printModel();;
			sonnet = writeSonnet();
			for (String s : sonnet)
				System.out.print(s+ ' ');
			System.out.println(' ');
		}
	}

	float off = 0;



	public void draw()
	{
		background(0);
		fill(255);
		noStroke();
		textSize(20);
		textAlign(CENTER, CENTER);

	}
}
