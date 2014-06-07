package graph;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;

import model.Lexicon;

public class Test {

	public static void main(String args[]){  
	    JFrame frame=new JFrame("Java数据统计图");  
	    frame.setLayout(new GridLayout(2,2,10,10));  
	    
	    Lexicon lex1 = new Lexicon("A",10,20,200);
	    Lexicon lex2 = new Lexicon("B",10,40,200);
	    Lexicon lex3 = new Lexicon("C",10,80,200);
	    Lexicon lex4 = new Lexicon("D",10,20,200);
	    Lexicon lex5 = new Lexicon("E",10,40,200);
	    Lexicon lex6 = new Lexicon("F",10,80,200);
	    Lexicon lex7 = new Lexicon("G",10,20,200);
	    Lexicon lex8 = new Lexicon("H",10,40,200);
	    Lexicon lex9 = new Lexicon("I",10,80,200);
	    Lexicon lex10 = new Lexicon("J",10,20,200);
	    Lexicon lex11 = new Lexicon("K",10,40,200);
	    Lexicon lex12 = new Lexicon("L",10,80,200);
	    Lexicon lex13 = new Lexicon("M",10,20,200);
	    Lexicon lex14 = new Lexicon("N",10,40,200);
	    Lexicon lex15 = new Lexicon("O",10,80,200);
	    Lexicon lex16 = new Lexicon("P",10,20,200);
	    Lexicon lex17 = new Lexicon("Q",10,40,200);
	    Lexicon lex18 = new Lexicon("R",10,80,200);
	    Lexicon lex19 = new Lexicon("S",10,20,200);
	    Lexicon lex20 = new Lexicon("T",10,40,200);
	    Lexicon lex21 = new Lexicon("U",10,80,200);
	    Lexicon lex22 = new Lexicon("V",10,20,200);
	    Lexicon lex23 = new Lexicon("W",10,40,200);
	    Lexicon lex24 = new Lexicon("X",10,80,200);
	    Lexicon lex25 = new Lexicon("Y",10,20,200);
	    Lexicon lex26 = new Lexicon("Z",10,40,200);
	    ArrayList<Lexicon> array = new ArrayList<Lexicon>();
	    array.add(lex1);
	    array.add(lex2);
	    array.add(lex3);
	    array.add(lex4);
	    array.add(lex5);
	    array.add(lex6);
	    array.add(lex7);
	    array.add(lex8);
	    array.add(lex9);
	    array.add(lex10);
	    array.add(lex11);
	    array.add(lex12);
	    array.add(lex13);
	    array.add(lex14);
	    array.add(lex15);
	    array.add(lex16);
	    array.add(lex17);
	    array.add(lex18);
	    array.add(lex19);
	    array.add(lex20);
	    array.add(lex21);
	    array.add(lex22);
	    array.add(lex23);
	    array.add(lex24);
	    array.add(lex25);
	    array.add(lex26);
	    
	    frame.add(new BarChart(array,1).getChartPanel());           //添加柱形图  
	    frame.add(new PieChart(1,20,40).getChartPanel());           //添加饼状图  
	    frame.setBounds(50, 50, 300, 100);  
	    frame.setVisible(true);
	}  
}
