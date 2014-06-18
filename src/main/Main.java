/*
 * Author: Feng Chaoyi
 * SE　lab4
 */ 

package main;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import controller.WordController;
import view.MainView;

//main方法

public class Main {
	/*
	public static void main(String[] args){
		WordController w = new WordController("./wordlist/dictionary.txt");
	}*/
	
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
    	try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MainView.createAndShowGUI();
            }
        });
    }
}
