package model;

import java.util.ArrayList;

// 单词类
public class Word {
	
	int ID;
	String word;
	String meaning;
	String start;
	
	public Word(){
		
	}
	
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getStart(){
		return start;
	}
	
	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getMeaning() {
		return meaning;
	}

	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}

	public int alphaNumber(){
		return (int) (this.word.length()*1.5);
	}
	
	public ArrayList<Character> alphaToChoose(){
		char[] all = new char[26];
		for (int i = 0; i < 26; i++){
			all[i] = (char) ('a'+i);
		}
		ArrayList<Character> ret = new ArrayList<Character>();
		char[] word = getWord().toCharArray();
		
		for (int i = 0; i < alphaNumber(); i++){
			if (i<word.length){
				ret.add(word[i]);
			}
			else{
				int rand = (int)(Math.random()*26);
				while (ret.contains(all[rand])){
					rand = (int)(Math.random()*26);
					System.out.println(rand);
				}
				ret.add(all[rand]);
			}
		}
		return ret;
	}
	
	//Constructor
	public Word(int ID, String word, String meaning, String start){
		this.ID = ID;
		this.word = word;
		this.meaning = meaning;
		this.start=start;
	}
}
