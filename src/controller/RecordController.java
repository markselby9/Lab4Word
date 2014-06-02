package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import model.Record;
import model.Word;

public class RecordController {
	String recordfilepathString = "./record/record.dat";
	File file;
	
	public RecordController(){
		file = new File(recordfilepathString);
		if (!file.exists())
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	//根据已背单词的Arraylist进行保存，在文件里添加一个Record
	public void saveRecord(ArrayList<Word> currentwordlist, String listname){
		//TODO
		System.out.println("Save record");
		Record record = new Record();
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
		}
	}
	
	//此处应当是读出有很多Record的Arraylist	
	@SuppressWarnings({ "resource", "unchecked" })
	public ArrayList<Record> openRecord(){
		ObjectInputStream ois;
		ArrayList<Record> recordlist=null;
		try {
			FileInputStream fis = new FileInputStream(recordfilepathString);
			ois = new ObjectInputStream(fis);
			Object obj = ois.readObject();
			recordlist = (ArrayList<Record>) obj;
			ois.close();fis.close();
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (recordlist==null){
			recordlist = new ArrayList<Record>();
		}
		return recordlist;
	}
	
	public String saveTime(){
		Calendar cal = Calendar.getInstance();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	return sdf.format(cal.getTime());
	}
	
	public Date parse(String strDate){
       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       try {
		return sdf.parse(strDate);
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	return null;
   }
}
