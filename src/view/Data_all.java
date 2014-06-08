package view;

import graph.BarChart;
import graph.PieChart;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import model.Lexicon;

import org.jfree.chart.ChartPanel;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * data_all_JFrame.java
 *
 * Created on 2014-6-6, 19:19:18
 */


/**
 *
 * @author Administrator
 */
public class Data_all extends javax.swing.JFrame {

    /** Creates new form data_all_JFrame */
    public Data_all() {
        initComponents();
    }
    public Data_all(String wordlistName, int pre_total, int pre_already, int pre_correct, int all_total,
    		 int all_already, int all_correct , ArrayList<Lexicon> lexiconList) {
    	
    	this.wordlistName = wordlistName;
    	this.pre_total = pre_total;
    	this.pre_already = pre_already;
    	this.pre_correct = pre_correct;
    	this.all_total = all_total;
    	this.all_already = all_already;
    	this.all_correct = all_correct;
    	this.lexiconList = lexiconList;
    	
    	if(pre_already==0)
    		this.pre_correctRate=0;
    	else
    		this.pre_correctRate = 100*pre_correct/(pre_already+0.0);
    	DecimalFormat df = new DecimalFormat("#.00");
    	this.pre_correctRateStr=df.format(pre_correctRate)+"%";
    	if(pre_correctRateStr.startsWith(".")){
    		pre_correctRateStr="0"+pre_correctRateStr;
    	}
    	//this.all_correctRate = all_correct/all_already;
    	
    	this.setTitle("总统计");
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
        wordListName = new javax.swing.JTextField();
        wordlistNameLabel = new javax.swing.JLabel();
        piePanel = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        pieGraph2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        logo = new javax.swing.JPanel();
        logoIcon = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        
        
        
        //当前词库正确率
    	pieChart1 = addPieChart(1, pre_correct, pre_already - pre_correct);
        pieChart1.setPreferredSize(new Dimension(200, 160));
    	
    	//所有词库正确率
    	pieChart2 = addPieChart(1, all_correct, all_already - all_correct);
    	pieChart2.setPreferredSize(new Dimension(200, 160));
    	
    	//当前词库已背单词比例
    	pieChart3 = addPieChart(2, pre_already, pre_total - pre_already);
    	pieChart3.setPreferredSize(new Dimension(200, 160));
    	
    	//所有词库已背单词比例
    	pieChart4 = addPieChart(2, all_already, all_total - all_already);
    	pieChart4.setPreferredSize(new Dimension(200, 160));
    	
    	//正确率比例
    	barChart2 = addBarChart(lexiconList,1);
    	barChart2.setPreferredSize(new Dimension(300, 200));
    	
    	//总单词比例
    	barChart1 = addBarChart(lexiconList,2);
    	barChart1.setPreferredSize(new Dimension(300, 200));
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jPanel1.setBackground(Color.white);
        
        totalNumLabel.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        totalNumLabel.setText("当前词库单词总数");

        totalNum.setText(""+this.pre_total);
        totalNum.setEditable(false);

        alreadyNumLabel.setFont(new java.awt.Font("微软雅黑", 0, 12));
        alreadyNumLabel.setText("当前词库已背单词数");

        alreadyNum.setText(""+this.pre_already);
        alreadyNum.setEditable(false);

        correctNumLabel.setFont(new java.awt.Font("微软雅黑", 0, 12));
        correctNumLabel.setText("当前词库正确单词数");

        correctNum.setText(""+this.pre_correct);
        correctNum.setEditable(false);

        wrongNumLabel.setFont(new java.awt.Font("微软雅黑", 0, 12));
        wrongNumLabel.setText("当前词库错误单词数");

        wrongNum.setText(""+(this.pre_already-this.pre_correct));
        wrongNum.setEditable(false);

        correctRateLabel.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        correctRateLabel.setText("当前词库正确率");

        correctRate.setText(pre_correctRateStr);
        correctRate.setEditable(false);
        correctRate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                correctRateActionPerformed(evt);
            }
        });

        wordListName.setText(""+this.wordlistName);
        wordListName.setEditable(false);
        wordListName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wordListNameActionPerformed(evt);
            }
        });

        wordlistNameLabel.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        wordlistNameLabel.setText("当前词库名称");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2.setBackground(Color.white);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(wordlistNameLabel)
                    .addComponent(totalNumLabel)
                    .addComponent(alreadyNumLabel)
                    .addComponent(correctNumLabel)
                    .addComponent(wrongNumLabel)
                    .addComponent(correctRateLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(correctRate)
                    .addComponent(wrongNum)
                    .addComponent(correctNum)
                    .addComponent(alreadyNum)
                    .addComponent(totalNum)
                    .addComponent(wordListName, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(wordListName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(wordlistNameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(totalNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(totalNumLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(alreadyNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(alreadyNumLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(correctNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(correctNumLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(wrongNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(wrongNumLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(correctRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(correctRateLabel)))
        );

        javax.swing.GroupLayout pieChart1Layout = new javax.swing.GroupLayout(pieChart1);
        pieChart1.setLayout(pieChart1Layout);
        pieChart1.setBackground(Color.white);
        pieChart1Layout.setHorizontalGroup(
            pieChart1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 110, Short.MAX_VALUE)
        );
        pieChart1Layout.setVerticalGroup(
            pieChart1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 114, Short.MAX_VALUE)
        );

        jLabel4.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jLabel4.setText("当前词库正确率");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3.setBackground(Color.white);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel4))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pieChart1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pieChart1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addContainerGap())
        );

        javax.swing.GroupLayout pieChart2Layout = new javax.swing.GroupLayout(pieChart2);
        pieChart2.setLayout(pieChart2Layout);
        pieChart2.setBackground(Color.white);
        pieChart2Layout.setHorizontalGroup(
            pieChart2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 114, Short.MAX_VALUE)
        );
        pieChart2Layout.setVerticalGroup(
            pieChart2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 114, Short.MAX_VALUE)
        );

        jLabel5.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jLabel5.setText("所有词库正确率");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4.setBackground(Color.white);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel5))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pieChart2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pieChart2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addContainerGap())
        );

        javax.swing.GroupLayout pieChart3Layout = new javax.swing.GroupLayout(pieChart3);
        pieChart3.setLayout(pieChart3Layout);
        pieChart3.setBackground(Color.white);
        pieChart3Layout.setHorizontalGroup(
            pieChart3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 122, Short.MAX_VALUE)
        );
        pieChart3Layout.setVerticalGroup(
            pieChart3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 111, Short.MAX_VALUE)
        );

        jLabel6.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jLabel6.setText("当前词库已背单词比例");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5.setBackground(Color.white);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(pieChart3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addComponent(pieChart3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addContainerGap())
        );

        javax.swing.GroupLayout pieChart4Layout = new javax.swing.GroupLayout(pieChart4);
        pieChart4.setLayout(pieChart4Layout);
        pieChart4.setBackground(Color.white);
        pieChart4Layout.setHorizontalGroup(
            pieChart4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 129, Short.MAX_VALUE)
        );
        pieChart4Layout.setVerticalGroup(
            pieChart4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 113, Short.MAX_VALUE)
        );

        jLabel7.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jLabel7.setText("所有词库已背单词比例");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6.setBackground(Color.white);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pieChart4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(11, Short.MAX_VALUE)
                .addComponent(pieChart4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addContainerGap())
        );

        javax.swing.GroupLayout piePanelLayout = new javax.swing.GroupLayout(piePanel);
        piePanel.setLayout(piePanelLayout);
        piePanel.setBackground(Color.white);
        piePanelLayout.setHorizontalGroup(
            piePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(piePanelLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        piePanelLayout.setVerticalGroup(
            piePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, piePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(piePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jLabel1.setText("总单词比例");

        jLabel2.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jLabel2.setText("正确率比例");

        javax.swing.GroupLayout barChart1Layout = new javax.swing.GroupLayout(barChart1);
        barChart1.setLayout(barChart1Layout);
        barChart1.setBackground(Color.white);
        barChart1Layout.setHorizontalGroup(
            barChart1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 173, Short.MAX_VALUE)
        );
        barChart1Layout.setVerticalGroup(
            barChart1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 96, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout barChart2Layout = new javax.swing.GroupLayout(barChart2);
        barChart2.setLayout(barChart2Layout);
        barChart2.setBackground(Color.white);
        barChart2Layout.setHorizontalGroup(
            barChart2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 178, Short.MAX_VALUE)
        );
        barChart2Layout.setVerticalGroup(
            barChart2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 96, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pieGraph2Layout = new javax.swing.GroupLayout(pieGraph2);
        pieGraph2.setLayout(pieGraph2Layout);
        pieGraph2.setBackground(Color.white);
        pieGraph2Layout.setHorizontalGroup(
            pieGraph2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pieGraph2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(barChart1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(barChart2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pieGraph2Layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 139, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(50, 50, 50))
        );
        pieGraph2Layout.setVerticalGroup(
            pieGraph2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pieGraph2Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addGroup(pieGraph2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(barChart1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(barChart2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pieGraph2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addContainerGap())
        );

        javax.swing.GroupLayout logoLayout = new javax.swing.GroupLayout(logo);
        logo.setLayout(logoLayout);
        logo.setBackground(Color.white);

        ImageIcon logoPNG = new ImageIcon("./images/logo2.png");
        logoIcon.setIcon(logoPNG);
        logoLayout.setHorizontalGroup(
            logoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(logoIcon, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
        );
        logoLayout.setVerticalGroup(
            logoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(logoIcon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
        );

        jLabel3.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jLabel3.setText("所有词库统计");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(piePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(logo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(62, 62, 62))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(pieGraph2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addGap(147, 147, 147))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(pieGraph2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(piePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1096, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(487, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 442, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        pack();
        
        
        
        
    }// </editor-fold>

    private void correctRateActionPerformed(java.awt.event.ActionEvent evt) {                                          
        // TODO add your handling code here:
}                                         

    private void wordListNameActionPerformed(java.awt.event.ActionEvent evt) {                                             
        // TODO add your handling code here:
}                                            


	/**
	 * type=1为正确率, num1 = correctNumber, num2 = wrongNumber
	 * type=2为总统计, num1 = alreadyNumber, num2 = totalNumber - alreadyNumber
	 * @param type
	 */
    public static ChartPanel addPieChart(int type, int num1, int num2){
    	PieChart pieChart = new PieChart( type,  num1,  num2);
    	return pieChart.getChartPanel();
    }
    
    
    /**这个用于得到总柱状图
	 * type=1为全部词库中已背单词数量
	 * type=2为正确率
	*/
    
    public static ChartPanel addBarChart(ArrayList<Lexicon> lexiconList, int type){
    	BarChart barChart = new BarChart(lexiconList,type);
    	return barChart.getChartPanel();
    }
    
    

    public static void createAndShowGUI(final String wordlistName,final int pre_total,final int pre_already,final int pre_correct,final int all_total,
    		final int all_already,final int all_correct ,final ArrayList<Lexicon> lexiconList){
    	
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
        		// 使窗口居中
        		Toolkit kit = Toolkit.getDefaultToolkit(); // 定义工具包
        		Dimension screenSize = kit.getScreenSize(); // 获取屏幕的尺寸
        		int screenWidth = screenSize.width / 2; // 获取屏幕的宽
        		int screenHeight = screenSize.height / 2; // 获取屏幕的高
        		int width = 930;
        		int height = 600;
                Data_all dataall = new Data_all(wordlistName,pre_total,pre_already, pre_correct, all_total, all_already, all_correct,lexiconList);
                dataall.setVisible(true);
                dataall.setLocation(screenWidth - width / 2, screenHeight - height / 2);
                dataall.setSize(width,height);
                dataall.setResizable(false);
                

        		ImageIcon image = new ImageIcon("./images/icon.jpg");
        		dataall.setIconImage(image.getImage());
        		dataall.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            }
        });
        
        
    }
    
  



	// Variables declaration - do not modify
    private javax.swing.JTextField alreadyNum;
    private javax.swing.JLabel alreadyNumLabel;
    private ChartPanel barChart1;
    private ChartPanel barChart2;
    private javax.swing.JPanel barPanel;
    private javax.swing.JTextField correctNum;
    private javax.swing.JLabel correctNumLabel;
    private javax.swing.JTextField correctRate;
    private javax.swing.JLabel correctRateLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel logo;
    private javax.swing.JLabel logoIcon;
    private ChartPanel pieChart1;
    private ChartPanel pieChart2;
    private ChartPanel pieChart3;
    private ChartPanel pieChart4;
    private javax.swing.JPanel pieGraph2;
    private javax.swing.JPanel piePanel;
    private javax.swing.JTextField totalNum;
    private javax.swing.JLabel totalNumLabel;
    private javax.swing.JTextField wordListName;
    private javax.swing.JLabel wordlistNameLabel;
    private javax.swing.JTextField wrongNum;
    private javax.swing.JLabel wrongNumLabel;
    // End of variables declaration
    
     String wordlistName;
     int pre_total;
     int pre_already;
     int pre_correct;
     int all_total;
     int all_already;
     int all_correct;
     ArrayList<Lexicon> lexiconList;
    
     double pre_correctRate;
     String pre_correctRateStr;
     //double all_correctRate;

}