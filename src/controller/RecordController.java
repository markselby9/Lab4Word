package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import model.Record;
import model.Word;

public class RecordController {
	String recordfilepathString = "./record/record.dat";
	File file;
	int lastto;
	
	public RecordController(){
		file = new File(recordfilepathString);
		if (!file.exists())
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		lastto=-1;
	}
	
	//根据已背单词的Arraylist进行保存，在文件里添加一个Record
	public void saveRecord(ArrayList<Word> currentwordlist, String listname){
		//TODO
		System.out.println("Save record");
		
		/*Record record = new Record();
		record.setID(openRecord().size()+1);
		record.setListname(listname);
		record.setTime(saveTime());
		record.setStartword(currentwordlist.get(0).getWord());
		record.setEndword(currentwordlist.get(currentwordlist.size()-1).getWord());
		
		try {
			FileOutputStream fos = new FileOutputStream(recordfilepathString);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(currentwordlist);
			oos.close();
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	//此处应当是读出有很多Record的Arraylist	
	@SuppressWarnings({ "resource", "unchecked" })
	public HashMap<Integer,Integer> openRecord(){
		//int id = 0;
		InputStreamReader isr = null;
		try {
			isr = new InputStreamReader(new FileInputStream(recordfilepathString), "gb2312");
		} catch (UnsupportedEncodingException | FileNotFoundException e1) {
			e1.printStackTrace();
		}
		BufferedReader br = new BufferedReader(isr);
		String line = null;
		HashMap<Integer,Integer> recordmap=new HashMap<Integer,Integer>();
		try {
			line = br.readLine();
			while (line != null) {
				if(line.startsWith("last_to:")){
					this.lastto=Integer.parseInt(line.substring(8));
				}
				else{
					String[] arr = line.split(" ");
					//Word word = new Word(id, arr[0], arr[arr.length - 1]);
					recordmap.put(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]));
					line = br.readLine();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("LOADED " + recordmap.size() + " RECORDS");
		
		return recordmap;
		/*ObjectInputStream ois;
		ArrayList<Record> recordlist=null;
		try {
			FileInputStream fis = new FileInputStream(recordfilepathString);
			ois = new ObjectInputStream(fis);
			Object obj = ois.readObject();
			recordlist = (ArrayList<Record>) obj;
			ois.close();
			fis.close();
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (recordlist==null){
			recordlist = new ArrayList<Record>();
			System.out.println("create a new recordlist");
		}
		return recordlist;
		*/
	}
	
	/*public String saveTime(){
		Calendar cal = Calendar.getInstance();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	return sdf.format(cal.getTime());
	}*/
	
	/*public Date parse(String strDate){
       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       try {
		return sdf.parse(strDate);
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	return null;
   }*/
}
