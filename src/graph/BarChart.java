package graph;

import java.awt.Font;
import java.util.ArrayList;

import model.Lexicon;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class BarChart {
	
	ChartPanel barChart;
	
	/**这个用于得到总柱状图
	 * type=1为全部词库中已背单词数量
	 * type=2为正确率
	*/
	public BarChart(ArrayList<Lexicon> lexiconList, int type){
		CategoryDataset dataset;
		JFreeChart chart = null ;
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~"+lexiconList.get(0));
		if(type == 1){
			dataset = getDataSetByAlready(lexiconList);
	        chart = ChartFactory.createBarChart3D(
	       		                 "全部词库中已背单词数量", // 图表标题
	                            "词库种类", // 目录轴的显示标签
	                            "已背单词数量", // 数值轴的显示标签
	                            dataset, // 数据集
	                            PlotOrientation.VERTICAL, // 图表方向：水平、垂直
	                            true,           // 是否显示图例(对于简单的柱状图必须是false)
	                            false,          // 是否生成工具
	                            false           // 是否生成URL链接
	                            );
			
		}else{
			dataset = getDataSetByCorrect(lexiconList);
	        chart = ChartFactory.createBarChart3D(
	       		                 "全部词库中已背单词正确率", // 图表标题
	                            "词库种类", // 目录轴的显示标签
	                            "已背单词正确率", // 数值轴的显示标签
	                            dataset, // 数据集
	                            PlotOrientation.VERTICAL, // 图表方向：水平、垂直
	                            true,           // 是否显示图例(对于简单的柱状图必须是false)
	                            false,          // 是否生成工具
	                            false           // 是否生成URL链接
	                            );
			
			
		}
		
        //从这里开始
        CategoryPlot plot=chart.getCategoryPlot();//获取图表区域对象
        CategoryAxis domainAxis=plot.getDomainAxis();         //水平底部列表
         domainAxis.setLabelFont(new Font("",Font.ITALIC,10));         //水平底部标题
         domainAxis.setTickLabelFont(new Font("微软雅黑",Font.ITALIC,10));  //垂直标题
         ValueAxis rangeAxis=plot.getRangeAxis();//获取柱状
         rangeAxis.setLabelFont(new Font("微软雅黑",Font.ITALIC,12));
          chart.getLegend().setItemFont(new Font("微软雅黑", Font.ITALIC, 8));
          chart.getTitle().setFont(new Font("微软雅黑",Font.ITALIC,12));//设置标题字体
          
          
         barChart = new ChartPanel(chart,true);        //这里也可以用chartFrame,可以直接生成一个独立的Frame
         
	}
	
	/**
	 * 已背单词数
	 * @param lexiconList
	 * @return
	 */
	   private static CategoryDataset getDataSetByAlready(ArrayList<Lexicon> lexiconList) {
           DefaultCategoryDataset dataset = new DefaultCategoryDataset();

           for(int i = 0; i < lexiconList.size(); i++){
               dataset.addValue(lexiconList.get(i).getAlreadyNum(),
            		   lexiconList.get(i).getLexiconName(), lexiconList.get(i).getLexiconName());
           }
           return dataset;
	   }


		/**
		 * 已背单词正确率
		 * @param lexiconList
		 * @return
		 */
		private static CategoryDataset getDataSetByCorrect(ArrayList<Lexicon> lexiconList) {
	       DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	       double correctRate;
	       for(int i = 0; i < lexiconList.size(); i++){
	    	   if(lexiconList.get(i).getAlreadyNum() == 0){
	    		   correctRate = 0;
	    	   }else{
		    	   correctRate = (lexiconList.get(i).getAlreadyNum()-lexiconList.get(i).getWrongNum())
		    			   /lexiconList.get(i).getAlreadyNum();
	    	   }
	           dataset.addValue(correctRate,
	            		   lexiconList.get(i).getLexiconName(), lexiconList.get(i).getLexiconName());
	       }
	       return dataset;
		}
	   
	   
	   public ChartPanel getChartPanel(){
		   return barChart;
		 }
}
