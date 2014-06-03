package controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

import model.Record;
import model.Word;

public class WordController {
	String filePath;
	String listname;
	int allcount, recordplace, lastto;
	ArrayList<Word> wordlist;
	ArrayList<Word> currentwordlist;
	HashMap<Integer,Integer> recordmap;
	//ArrayList<Record> recordlist;
	
	RecordController recordController;

	public WordController(String filepath) {
		this.filePath = filepath;
		this.wordlist = new ArrayList<Word>();
		this.recordController = new RecordController();
		loadwordlist();
		loadrecord();
	}
	
	public void loadrecord(){
		this.recordmap=recordController.openRecord();
		this.lastto=recordController.lastto;
		//this.recordlist = recordController.openRecord();
	}
	
	public int getLastto(){
		return lastto;
	}

	// load words from file into a arraylist
	public void loadwordlist() {
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
				Word word = new Word(id, arr[0], arr[arr.length - 1]);
				id++;
				this.wordlist.add(word);
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
	
	public int getAllCount() {
		return this.wordlist.size();
	}

	public String getListName(){
		String[] arr = this.filePath.split("/");
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
	
	public int isValid(int start, int num){
		if (start <0 || num<0 || start>getAllCount()){
			return -1;
		}
		else if ((start+num)>getAllCount()){
			return getAllCount()-start;
		}
		else
			return 0;
	}
	
	//背完一个单词，在record的arraylist上加上这个单词的Record
	public void startReciting(int startid, int duration){
		currentwordlist = new ArrayList<Word>();
		for (int i = startid; i < startid + duration; i++){
			currentwordlist.add(wordlist.get(i));
		}
	}
	
	// 背到了第几个？进行保存
	public boolean finishReciting(int reciteTo){
		//去掉还没背的单词
		for (int i = reciteTo; i < currentwordlist.size();i++){
			currentwordlist.remove(i);//逻辑好像有问题，list一直在变
		}
		
		recordController.saveRecord(this.currentwordlist, getListName());
		return true;
	}
	
	public static boolean isInteger(String str) {
		if (str.length()>7) return false;
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
		return pattern.matcher(str).matches();
	}

}
