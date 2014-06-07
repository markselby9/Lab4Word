package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import model.Record;
import model.Word;

public class RecordController {
	String recordfilepathString = "./record/record.dat";
	File file;
	int [] lastto;
	
	public RecordController(){
		file = new File(recordfilepathString);
		if (!file.exists())
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		lastto=new int[26];
		for(int i=0;i<26;i++)
			lastto[i]=-1;
	}
	
	//根据已背单词的Arraylist进行保存，在文件里添加一个Record
	public boolean saveRecord(HashMap<Integer,Integer> recordmap, int [] lastto){
		//TODO
		System.out.println("Save record");
		OutputStreamWriter osr = null;
		try {
			osr = new OutputStreamWriter(new FileOutputStream(recordfilepathString), "gb2312");
		} catch (UnsupportedEncodingException | FileNotFoundException e1) {
			e1.printStackTrace();
			return false;
		}
		BufferedWriter bwr = new BufferedWriter(osr);
		try {
			for(int i=0;i<lastto.length;i++){
				if(lastto[i]>=0)
					bwr.write("lastto:"+(char)(65+i)+":"+lastto[i]);
			}
			Iterator<Integer> ite=recordmap.keySet().iterator();
			while(ite.hasNext()){
				int tmp=ite.next();
				bwr.newLine();
				bwr.write(tmp+" "+recordmap.get(tmp));
			}
			bwr.flush();
			bwr.close();
			osr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
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
			String [] lasttos;
			line = br.readLine();
			while (line != null) {
				System.out.println(line);
				if(line.startsWith("lastto:")){
					lasttos=line.split(":");
					lastto[lasttos[1].toUpperCase().charAt(0)-65]=Integer.parseInt(lasttos[2]);
				}
				else{
					String[] arr = line.split(" ");
					//Word word = new Word(id, arr[0], arr[arr.length - 1]);
					if((!arr[0].equals(""))&&arr.length>1)
						recordmap.put(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]));
				}
				line = br.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("LOADED " + recordmap.size() + " RECORDS");
		
		return recordmap;
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
