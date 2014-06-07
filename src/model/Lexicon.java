package model;

public class Lexicon {
	
	String lexiconName;
	String URL;
	int wrongNum;
	int alreadyNum;
	int totalNum;
	
	public Lexicon(String lexiconName, String URL, int wrongNum, int alreadyNum, int totalNum){
		this.lexiconName = lexiconName;
		this.URL = URL;
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
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
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
