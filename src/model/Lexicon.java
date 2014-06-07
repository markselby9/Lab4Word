package model;

public class Lexicon {
	
	String lexiconName;
	//String URL;
	int lastto;
	int wrongNum;
	int alreadyNum;
	int totalNum;
	
	public Lexicon(String lexiconName, int lastto, int wrongNum, int alreadyNum, int totalNum){
		this.lexiconName = lexiconName;
		this.lastto=lastto;
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
	public void setLastto(int lastto){
		this.lastto=lastto;
	}
	public int getLastto(){
		return lastto;
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
