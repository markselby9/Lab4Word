package model;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JTextField;


public class Timer implements Runnable {
	SimpleDateFormat dateFormat;
	Calendar startCalendar;
	long startTime, nowTime, showTime, showSec, showMin;
    Calendar now;
	boolean flag;
	JTextField clock;
	
	public Timer(JTextField clock){
		this.clock=clock;
		init();
	}
	
	public void init(){
		dateFormat = new SimpleDateFormat("hh:mm:ss");
        startCalendar = Calendar.getInstance();
        startTime = startCalendar.getTime().getTime(); // 获得开始时候的那个时间点
        flag = true;
	}
		
	public void stop(){
		flag = false;
	}

	@Override
	public void run() {
		while(true && flag)
        {
            now = Calendar.getInstance();
            nowTime = now.getTime().getTime();
            showTime = nowTime-startTime;
            showSec = showTime/1000;
            showMin = showSec / 60;
            showSec -= showMin*60;
            clock.setText(showMin+":"+showSec);
                        
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
	
	public long getMinite(){
		return showMin;
	}
	
	public long getSecond(){
		return showSec;
	}
}
