package graph;

import java.awt.Font;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

public class PieChart {
	
	ChartPanel frame1;
	
	/**
	 * type=1为正确率, num1 = correctNumber, num2 = wrongNumber
	 * type=2为总统计
	 * @param type
	 */
	public PieChart(int type, int num1, int num2){
		  DefaultPieDataset data = null;
	      JFreeChart chart = null;
	      
	      if(type == 1){
	    	  data = getDataSetByCorrect(num1, num2);
	    	  chart = ChartFactory.createPieChart3D("正确率与错误率",data,true,false,false);
	      }else{

	    	  data = getDataSetByAlready(num1, num2);
	    	  chart = ChartFactory.createPieChart3D("已背单词比率",data,true,false,false);
	      }
	      
	      
	    //设置百分比
	      PiePlot pieplot = (PiePlot) chart.getPlot();
	      DecimalFormat df = new DecimalFormat("0.00%");//获得一个DecimalFormat对象，主要是设置小数问题
	      NumberFormat nf = NumberFormat.getNumberInstance();//获得一个NumberFormat对象
	      StandardPieSectionLabelGenerator sp1 = new StandardPieSectionLabelGenerator("{0}  {2}", nf, df);//获得StandardPieSectionLabelGenerator对象
	      pieplot.setLabelGenerator(sp1);//设置饼图显示百分比
	  
	  //没有数据的时候显示的内容
	      pieplot.setNoDataMessage("无数据显示");
	      pieplot.setCircular(false);
	      pieplot.setLabelGap(0.02D);
	  
	      pieplot.setIgnoreNullValues(true);//设置不显示空值
	      pieplot.setIgnoreZeroValues(true);//设置不显示负值
	     frame1=new ChartPanel (chart,true);
	      chart.getTitle().setFont(new Font("微软雅黑",Font.BOLD,20));//设置标题字体
	      PiePlot piePlot= (PiePlot) chart.getPlot();//获取图表区域对象
	      piePlot.setLabelFont(new Font("微软雅黑",Font.BOLD,10));//解决乱码
	      chart.getLegend().setItemFont(new Font("微软雅黑",Font.BOLD,10));
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
