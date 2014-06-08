package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.text.DecimalFormat;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class RecordView extends JFrame{
	public RecordView(String dicname,int totalnum,int wordnum,int rightnum,int wrongnum){//所选词库名、所选单词数量、正确单词数、错误单词数、正确率
		JPanel jPanel = new JPanel();
		JTextArea output = new JTextArea(5, 25);
		output.setEditable(false);
		double rate=rightnum*100.0/wordnum;
		DecimalFormat df = new DecimalFormat("#.00");
		output.setText("词库："+dicname+"\n单词数："+wordnum+"\n正确数："+rightnum+"\n错误数："+wrongnum+"\n正确率："+df.format(rate)+"%");
		//output.setBorder(javax.swing.border.LineBorder.createBlackLineBorder());
		JScrollPane scrollPane = new JScrollPane(output);
		//scrollPane.add();
		// Add the text area to the content pane.
		jPanel.add(scrollPane, BorderLayout.CENTER);
		this.add(jPanel);
		// 
		this.setTitle("您的背单词记录");
		this.setSize(600, 400);
		this.setResizable(false);

		// 使窗口居中
		Toolkit kit = Toolkit.getDefaultToolkit(); // 定义工具包
		Dimension screenSize = kit.getScreenSize(); // 获取屏幕的尺寸
		int screenWidth = screenSize.width / 2; // 获取屏幕的宽
		int screenHeight = screenSize.height / 2; // 获取屏幕的高
		int height = this.getHeight();
		int width = this.getWidth();
		setLocation(screenWidth - width / 2, screenHeight - height / 2);

		this.setVisible(true);
	}
}
