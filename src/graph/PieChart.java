package graph;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieToolTipGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;

public class PieChart {
	
	ChartPanel frame1;
	
	/**
	 * type=1为正确率, num1 = correctNumber, num2 = wrongNumber
	 * type=2为总统计, num1 = alreadyNumber, num2 = totalNumber - alreadyNumber
	 * @param type
	 */
	public PieChart(int type, int num1, int num2){
		  DefaultPieDataset data;
	      JFreeChart chart = null;
	      
	      if(type == 1){
	    	  data = getDataSetByCorrect(num1, num2);
	    	  chart = ChartFactory.createPieChart3D("正确率与错误率",data,true,false,false);
	      }else{

	    	  data = getDataSetByAlready(num1, num2);
	    	  chart = ChartFactory.createPieChart3D("已背单词比率",data,true,false,false);
	      }
	      
	      

	        // 图片背景色  
	        chart.setBackgroundPaint(Color.white);  
	        // 设置标题文字  
	        frame1 = new ChartPanel(chart, true);  
	        // 取得饼图plot对象  
	        // PiePlot plot = (PiePlot) chart.getPlot();  
	        // 取得3D饼图对象  
	        PiePlot3D plot = (PiePlot3D) chart.getPlot(); 
	        // 图形边框颜色  
	        plot.setBaseSectionOutlinePaint(Color.RED);  
	        // plot.setBaseSectionPaint(Color.WHITE);  
	        // 图形边框粗细  
	        plot.setBaseSectionOutlineStroke(new BasicStroke(1.0f));  
	 
	        // 指定图片的透明度(0.0-1.0)  
	        plot.setForegroundAlpha(0.65f);  
	        // 指定显示的饼图上圆形(false)还椭圆形(true)  
	        plot.setCircular(true);  
	 
	        // 设置第一个 饼块section 的开始位置，默认是12点钟方向  
	        plot.setStartAngle(360);  
	        // 设置鼠标悬停提示  
	        plot.setToolTipGenerator(new StandardPieToolTipGenerator());  
	 
	        // 设置突出显示的数据块  
	        plot.setExplodePercent("One", 0.1D);  
	        // 设置饼图各部分标签字体  
	        plot.setLabelFont(new Font("宋体", Font.ITALIC, 15));  
	        // 设置分饼颜色  
	        plot.setSectionPaint(0, new Color(244, 194, 144));  
	        // plot.setSectionPaint("2", new Color(144, 233, 144));  
	        // 设置图例说明Legend上的文字  
	        chart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 20));  
	        // 定义字体格式  
	        Font font = new java.awt.Font("黑体", java.awt.Font.CENTER_BASELINE,50); 
	 
	}
	
    private static DefaultPieDataset getDataSetByCorrect(int correctNum, int wrongNum) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("正确",correctNum);
        dataset.setValue("错误",wrongNum);
        return dataset;
    }
    private static DefaultPieDataset getDataSetByAlready(int alreadyNum, int totalNum) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("已背单词数",alreadyNum);
        dataset.setValue("总单词数",totalNum);
        return dataset;
    }
    
    
    
    
    
    
    
    
    
    public ChartPanel getChartPanel(){
    	return frame1;
    	
    }
}
