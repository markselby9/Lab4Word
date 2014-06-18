package model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import javax.swing.JTextField;


public class Timer implements Runnable {
	SimpleDateFormat dateFormat;
	Calendar startCalendar;
	long startTime, nowTime, showTime;
	String hms;
    Calendar now;
	boolean flag;
	JTextField clock;
	
	public Timer(JTextField clock){
		this.clock=clock;
		init();
	}
	
	public void init(){
		dateFormat = new SimpleDateFormat("HH:mm:ss");
		dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        startCalendar = Calendar.getInstance();
        startTime = startCalendar.getTime().getTime(); // 获得开始时候的那个时间点
        flag = true;
	}
		
	public void stop(){
		flag = false;
	}

	@Override
	public void run() {
		while(flag)
        {
            now = Calendar.getInstance();
            nowTime = now.getTime().getTime();
            showTime = nowTime-startTime;
            //showSec = showTime/1000;
            hms = dateFormat.format(showTime);
            
            //showMin = showSec / 60;
            //showSec -= showMin*60;
            clock.setText(hms);
                        
            try
            {
                Thread.sleep(1000);
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        }
	}
	
	public String gethms(){
		return hms;
	}
}
