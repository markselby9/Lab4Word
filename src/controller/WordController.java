package controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Pattern;

import model.Lexicon;
import model.Record;
import model.Word;

public class WordController {
	String filePath;
	String listname;
	int allcount, recordplace;
	int [] lastto;
	ArrayList<Word> wordlist;
	//ArrayList<Word> currentwordlist;
	HashMap<Integer,Integer> recordmap;
	HashMap<Integer,String> startmap;
	//ArrayList<Record> recordlist;
	
	RecordController recordController;

	public WordController(String filepath) {
		this.filePath = filepath;
		this.wordlist = new ArrayList<Word>();
		this.recordController = new RecordController();
		startmap=new HashMap<Integer,String>();
		loadwordlist();
		loadrecord();
	}
	
	public void loadrecord(){
		this.recordmap=recordController.openRecord();
		this.lastto=recordController.lastto;
		//this.recordlist = recordController.openRecord();
	}
	
	public int getLasttoInLexicon(String start){
		return lastto[start.toUpperCase().charAt(0)-65];
	}

	// load words from file into a arraylist
	public void loadwordlist() {
		//startmap=new HashMap<Integer,String>();
		int id = 0;
		InputStreamReader isr = null;
		try {
			isr = new InputStreamReader(new FileInputStream(filePath), "gb2312");
		} catch (UnsupportedEncodingException | FileNotFoundException e1) {
			e1.printStackTrace();
		}
		BufferedReader br = new BufferedReader(isr);
		String line = null;
		try {
			line = br.readLine();
			while (line != null) {
				String[] arr = line.split(" ");
				Word word = new Word(id, arr[0], arr[arr.length - 1],arr[0].substring(0, 1).toUpperCase());
				id++;
				this.wordlist.add(word);
				startmap.put(word.getID(), word.getStart());
				line = br.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("LOADED " + wordlist.size() + " WORDS");
	}

	public Word getWordByID(int id) {
		return this.wordlist.get(id);
	}

	public int getIDByWord(String word) {
		Word tmp=null;
		for(int i=0;i<wordlist.size();i++){
			tmp=wordlist.get(i);
			if(word.equals(tmp.getWord())){
				return tmp.getID();
			}
		}
		return -1;
		//return this.wordlist.get(id);
	}
	
	public int getStartInLexicon(String start){
		Word tmp=null;
		for(int i=0;i<wordlist.size();i++){
			tmp=wordlist.get(i);
			if(tmp.getStart().toUpperCase().equals(start.toUpperCase()))
				return tmp.getID();
		}
		return -1;
	}
	
	public int getLastInLexicon(String start){
		int last=-1;
		boolean find=false;
		Word tmp=null;
		for(int i=0;i<wordlist.size();i++){
			tmp=wordlist.get(i);
			if(tmp.getStart().toUpperCase().equals(start.toUpperCase())){
				last=tmp.getID();
				find=true;
			}
			else{
				if(find)
					break;
			}
		}
		return last;
	}
	
	public int getTotalInLexicon(String start){
		int tmp=getStartInLexicon(start);
		if(tmp==-1){
			return 0;
		}
		else
			return getLastInLexicon(start)-tmp+1;
	}
	
	public int getAlreadyNumInLexicon(String start){
		int tmp=0;
		int key=0;
		Iterator<Integer> ite=recordmap.keySet().iterator();
		while(ite.hasNext()){
			key=ite.next();
			if(startmap.get(key).toUpperCase().equals(start.toUpperCase())){
				tmp++;
			}
		}
		return tmp;
	}
	
	public int getWrongNumInLexicon(String start){
		int tmp=0;
		int key=0;
		Iterator<Integer> ite=recordmap.keySet().iterator();
		while(ite.hasNext()){
			key=ite.next();
			if(startmap.get(key).toUpperCase().equals(start.toUpperCase())&&recordmap.get(key)==0){
				tmp++;
			}
		}
		return tmp;
	}
	
	public ArrayList<Lexicon> getLexiconStats(){
		ArrayList<Lexicon> stats=new ArrayList<Lexicon>();
		//String lexiconName, int wrongNum, int alreadyNum, int totalNum
		char tmp='A';
		int wrongNum=0;
		int alreadyNum=0;
		int totalNum=0;
		for(int i=0;i<26;i++){
			totalNum=getTotalInLexicon(tmp+"");
			alreadyNum=getAlreadyNumInLexicon(tmp+"");
			wrongNum=getWrongNumInLexicon(tmp+"");
			stats.add(new Lexicon(tmp+"",wrongNum,alreadyNum,totalNum));
			tmp++;
		}
		return stats;
	}
	
	public ArrayList<String> getSimilarWords(String word, String start){
		ArrayList<String> words=new ArrayList<String>();
		if(!word.toUpperCase().startsWith(start.toUpperCase()))
			return words;
		String tmp="";
		for(int i=0;i<wordlist.size();i++){
			tmp=wordlist.get(i).getWord();
			if(tmp.startsWith(word)){
				words.add(tmp);
			}
			if(words.size()>=10)
				return words;
		}
		return words;
	}
	
	public int getAllCount() {
		return this.wordlist.size();
	}
	
	public int getRecordCount(){
		return recordmap.size();
	}
	
	public int getRecordRightCount(){
		Collection <Integer> values=recordmap.values();
		Integer [] arr=values.toArray(new Integer [0]);
		int result=0;
		for(int i=0;i<arr.length;i++){
			result+=arr[i].intValue();
		}
		return result;
	}

	public String getListName(){
		String[] arr = this.filePath.split("\\\\");
		return arr[arr.length-1];
	}
	
	public int isValid(int start, int num, String lexicon){
		int lastinlexicon=getLastInLexicon(lexicon.toUpperCase());
		if (start <0 || num < 0 ){
			return -1;
		}
		else if(start > lastinlexicon){
			return -2;
		}
		else if ((start+num)>lastinlexicon+1){
			return lastinlexicon-start+1;
		}
		else
			return 0;
	}
	
	public void addRecord(int id, int pass){
		String start=startmap.get(id);
		lastto[start.toUpperCase().charAt(0)-65]=id;
		if(recordmap.containsKey(id)){
			if(recordmap.get(id)==0&&pass==1)
				recordmap.put(id, 1);
		}
		else{
			recordmap.put(id,pass);
		}
	}
	
	public boolean saveRecord(){
		return recordController.saveRecord(recordmap,lastto);
	}
	
	public static boolean isInteger(String str) {
		if (str.length()>7) return false;
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
		return pattern.matcher(str).matches();
	}

}
