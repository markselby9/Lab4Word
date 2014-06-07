package graph;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;

import model.Lexicon;

public class Test {

	public static void main(String args[]){  
	    JFrame frame=new JFrame("Java数据统计图");  
	    frame.setLayout(new GridLayout(2,2,10,10));  
	    
	    Lexicon lex1 = new Lexicon("大学英语四级","",10,20,200);
	    Lexicon lex2 = new Lexicon("大学英语六级","",10,40,200);
	    Lexicon lex3 = new Lexicon("大学英语八级","",10,80,200);
	    ArrayList<Lexicon> array = new ArrayList<Lexicon>();
	    array.add(lex1);
	    array.add(lex2);
	    array.add(lex3);
	    
	    frame.add(new BarChart(array,1).getChartPanel());           //添加柱形图  
	    //frame.add(new PieChart(array,1).getChartPanel());           //添加饼状图  
	    frame.setBounds(50, 50, 800, 600);  
	    frame.setVisible(true);
		//String str="Z";
		//System.out.println((char)(65+25));
	}  
}
