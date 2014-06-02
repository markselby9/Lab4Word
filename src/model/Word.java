package model;

// 单词类
public class Word {
	int ID;
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	String word;
	String meaning;
	
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

	public Word(){
		
	}
	
	//Constructor
	public Word(int ID, String word, String meaning){
		this.ID = ID;
		this.word = word;
		this.meaning = meaning;
	}
}
