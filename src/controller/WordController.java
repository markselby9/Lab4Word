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

import model.Record;
import model.Word;

public class WordController {
	String filePath;
	String listname;
	int allcount, recordplace;
	int [] lastto;
	ArrayList<Word> wordlist;
	//ArrayList<Word> currentwordlist;
	HashMap<Integer,Integer> newrecordmap;
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
		newrecordmap=new HashMap<Integer,Integer>();
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

	// 从ID获取单词
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
	
	// 判断用户的开始单词、单词次数的选择是否可行
	public boolean isValid(String str1, String str2){
		if (str1.length()==0||str2.length()==0){
			return false;
		}
		if (!isInteger(str1)||!isInteger(str2)){
			return false;
		}
		if (Integer.parseInt(str1)<0 || Integer.parseInt(str1)>getAllCount()){
			return false;
		}
		if (Integer.parseInt(str2)<0 || Integer.parseInt(str1)+Integer.parseInt(str2)>getAllCount()){
			return false;
		}
		return true;
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
		newrecordmap.put(id, pass); 
	}
	
	// 背到了第几个？进行保存
	public boolean mergerecord(){
		Set<Integer> keys=newrecordmap.keySet();
		Iterator<Integer> ite=keys.iterator();
		//if(keys.size()>0)
			//lastto=0;
		while(ite.hasNext()){
			int tmp=ite.next();
			//lastto=Math.max(lastto, tmp);
			if(recordmap.containsKey(tmp)){
				if(recordmap.get(tmp)==0&&newrecordmap.get(tmp)==1)
					recordmap.put(tmp, 1);
			}
			else{
				recordmap.put(tmp, newrecordmap.get(tmp));
			}
		}
		newrecordmap.clear();
		return true;
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
