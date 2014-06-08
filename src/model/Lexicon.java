package model;

public class Lexicon {
	
	String lexiconName;
	int wrongNum;
	int alreadyNum;
	int totalNum;
	
	public Lexicon(String lexiconName, int wrongNum, int alreadyNum, int totalNum){
		this.lexiconName = lexiconName;
		this.wrongNum = wrongNum;
		this.alreadyNum = alreadyNum;
		this.totalNum = totalNum;
	}
	
	public String getLexiconName() {
		return lexiconName;
	}
	public void setLexiconName(String lexiconName) {
		this.lexiconName = lexiconName;
	}
	public int getWrongNum() {
		return wrongNum;
	}
	public void setWrongNum(int wrongNum) {
		this.wrongNum = wrongNum;
	}
	public int getAlreadyNum() {
		return alreadyNum;
	}
	public void setAlreadyNum(int alreadyNum) {
		this.alreadyNum = alreadyNum;
	}
	public int getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}
	
	
	
}
