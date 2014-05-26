package model;


//词库的每条记录
public class Record {
	String listname;	//词库名
	int ID;
	String time;
	int wordcount;
	String startword;
	String endword;

	public Record(){
		
	}
	
	public int getID() {
		return ID;
	}
	public String getListname() {
		return listname;
	}
	public void setListname(String listname) {
		this.listname = listname;
	}
	public int getWordcount() {
		return wordcount;
	}
	public void setWordcount(int wordcount) {
		this.wordcount = wordcount;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getStartword() {
		return startword;
	}
	public void setStartword(String startword) {
		this.startword = startword;
	}
	public String getEndword() {
		return endword;
	}
	public void setEndword(String endword) {
		this.endword = endword;
	}
	
}
