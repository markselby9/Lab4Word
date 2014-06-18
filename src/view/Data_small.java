package view;

import graph.PieChart;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.swing.JOptionPane;

/*
 * data_small_frame.java
 *
 * Created on 2014-6-6, 19:23:27
 */


/**
 *
 * @author Administrator
 */
public class Data_small extends javax.swing.JFrame {
	String wordlistNamestr;
	int totalNumber;
	int alreadyNumber;
	int correctNumber;
	int wrongNumber;
	String correctrate;
	String timestr;
	
    /** Creates new form data_small_frame */
    public Data_small() {
        initComponents();
    }
    public Data_small(String title,String wordlistName, int totalNumber,int alreadyNumber,int correctNumber,String time) {
    	this.setTitle(title);
    	this.wordlistNamestr=wordlistName;
    	this.totalNumber=totalNumber;
    	this.alreadyNumber=alreadyNumber;
    	this.correctNumber=correctNumber;
    	this.wrongNumber=alreadyNumber-correctNumber;
    	this.timestr=time;
    	double rate=correctNumber*100.0/alreadyNumber;
		DecimalFormat df = new DecimalFormat("#.00");
    	this.correctrate=df.format(rate)+"%";
    	if(correctrate.startsWith("."))
    		correctrate="0"+correctrate;
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        totalNumLabel = new javax.swing.JLabel();
        totalNum = new javax.swing.JTextField();
        alreadyNumLabel = new javax.swing.JLabel();
        alreadyNum = new javax.swing.JTextField();
        correctNumLabel = new javax.swing.JLabel();
        correctNum = new javax.swing.JTextField();
        wrongNumLabel = new javax.swing.JLabel();
        wrongNum = new javax.swing.JTextField();
        correctRateLabel = new javax.swing.JLabel();
        correctRate = new javax.swing.JTextField();
        wordlistNameLabel = new javax.swing.JLabel();
        wordlistName = new javax.swing.JTextField();
        timeLabel = new javax.swing.JLabel();
        time = new javax.swing.JTextField();
        logo = new javax.swing.JPanel();
        logoIcon = new javax.swing.JLabel();

        jPanel1.setFont(new java.awt.Font("微软雅黑", 0, 12));
        jPanel1.setPreferredSize(new java.awt.Dimension(468, 357));
        jPanel1.setBackground(Color.white);
        
        totalNumLabel.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        totalNumLabel.setText("单词总数");

        totalNum.setText(totalNumber+"");
        totalNum.setEditable(false);

        alreadyNumLabel.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        alreadyNumLabel.setText("已背单词数");

        alreadyNum.setText(alreadyNumber+"");
        alreadyNum.setEditable(false);

        correctNumLabel.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        correctNumLabel.setText("正确单词数");

        correctNum.setText(correctNumber+"");
        correctNum.setEditable(false);

        wrongNumLabel.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        wrongNumLabel.setText("错误单词数");

        wrongNum.setText(wrongNumber+"");
        wrongNum.setEditable(false);

        correctRateLabel.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        correctRateLabel.setText("正确率");

        correctRate.setText(correctrate);
        correctRate.setEditable(false);

        wordlistNameLabel.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        wordlistNameLabel.setText("词库名称");

        wordlistName.setText(wordlistNamestr);
        wordlistName.setEditable(false);

        timeLabel.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        timeLabel.setText("背词时间");

        time.setEditable(false);
        time.setText(timestr);
        
        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2.setBackground(Color.white);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addGap(34, 34, 34)
                            .addComponent(correctRateLabel)
                            .addGap(10, 10, 10)
                            .addComponent(correctRate, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(wrongNumLabel)
                            .addGap(10, 10, 10)
                            .addComponent(wrongNum, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(correctNumLabel)
                            .addGap(10, 10, 10)
                            .addComponent(correctNum, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(alreadyNumLabel)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(alreadyNum, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addGap(22, 22, 22)
                            .addComponent(totalNumLabel)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(totalNum, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(22, 22, 22)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(timeLabel)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(time, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(wordlistNameLabel)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(wordlistName, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)))))
                    .addContainerGap())
            );
            jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(timeLabel)
                        .addComponent(time, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(wordlistName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(wordlistNameLabel))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(totalNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(totalNumLabel))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(alreadyNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(alreadyNumLabel))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(correctNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(correctNumLabel))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(wrongNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(wrongNumLabel))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(correctRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(correctRateLabel))
                    .addGap(18, 18, 18))
            );

        javax.swing.GroupLayout logoLayout = new javax.swing.GroupLayout(logo);
        logo.setLayout(logoLayout);
        logo.setBackground(Color.white);

        ImageIcon logoPNG = new ImageIcon("./images/logo2.png");
        logoIcon.setIcon(logoPNG);
        logoLayout.setHorizontalGroup(
            logoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(logoIcon, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
        );
        logoLayout.setVerticalGroup(
            logoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(logoIcon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(27, Short.MAX_VALUE))
            );
            jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
            );

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 266, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 421, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
                        .addContainerGap()))
            );

        pack();
    }// </editor-fold>                        
    
    
    
    public static void createAndShowGUI(final String wordlistName,final int totalNumber,final int alreadyNumber,final int correctNumber,final String time){

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            	
        		// 使窗口居中
        		Toolkit kit = Toolkit.getDefaultToolkit(); // 定义工具包
        		Dimension screenSize = kit.getScreenSize(); // 获取屏幕的尺寸
        		int screenWidth = screenSize.width / 2; // 获取屏幕的宽
        		int screenHeight = screenSize.height / 2; // 获取屏幕的高
        		int width = 250;
        		int height = 400;
                Data_small datasmall = new Data_small("背词统计",wordlistName,totalNumber,alreadyNumber,correctNumber,time);
                datasmall.setVisible(true);
                datasmall.setLocation(screenWidth - width / 2, screenHeight - height / 2);
                datasmall.setSize(width,height);
                datasmall.setResizable(false);
                

        		ImageIcon image = new ImageIcon("./images/icon.jpg");
        		datasmall.setIconImage(image.getImage());
        		datasmall.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        });
    }

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
    	//createAndShowGUI();
    }
    

    
    


    // Variables declaration - do not modify                     
    private javax.swing.JTextField alreadyNum;
    private javax.swing.JLabel alreadyNumLabel;
    private javax.swing.JTextField correctNum;
    private javax.swing.JLabel correctNumLabel;
    private javax.swing.JTextField correctRate;
    private javax.swing.JLabel correctRateLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel logo;
    private javax.swing.JLabel logoIcon;
    private javax.swing.JTextField time;
    private javax.swing.JLabel timeLabel;
    private javax.swing.JTextField totalNum;
    private javax.swing.JLabel totalNumLabel;
    private javax.swing.JTextField wordlistName;
    private javax.swing.JLabel wordlistNameLabel;
    private javax.swing.JTextField wrongNum;
    private javax.swing.JLabel wrongNumLabel;
    // End of variables declaration                   

}
