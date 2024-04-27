package gui;

import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author TVDd
 */
public class TestBarChart extends JFrame{
	
	public static int lan1;
	public static int lan2;
	
	public static int getLan1() {
		return lan1;
	}

	public static void setLan1(int l1) {
		TestBarChart.lan1 = l1;
	}

	public static int getLan2() {
		return lan2;
	}

	public static void setLan2(int lan2) {
		TestBarChart.lan2 = lan2;
	}

	public static int getLan3() {
		return lan3;
	}

	public static void setLan3(int lan3) {
		TestBarChart.lan3 = lan3;
	}

	public static int lan3;
	
	public static void getL1(int l1){
		setLan1(l1);
	}
	
    public JFreeChart createChart() {
        JFreeChart barChart = ChartFactory.createBarChart(
                "BIỂU ĐỒ SỐ LƯỢNG TRẢ TIỀN",
                "Số lần", "Số người",
                createDataset(Incomemanagement.getSoluong1(), Incomemanagement.getSoluong2(), Incomemanagement.getSoluong3()), PlotOrientation.VERTICAL, false, false, false);
        return barChart;
    }

    public CategoryDataset createDataset(int lan1,int lan2,int lan3) {
    	System.out.println(lan1);
    	System.out.println(lan2);
    	System.out.println(lan3);
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(lan1, "Số người", "Pay1");
        dataset.addValue(lan2, "Số người", "Pay2");
        dataset.addValue(lan3, "Số người", "Pay3");
        return dataset;
    }

    public TestBarChart(){
        ChartPanel chartPanel = new ChartPanel(createChart());
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
        JFrame frame = new JFrame();
        frame.add(chartPanel);
        frame.setTitle("Biểu đồ JFreeChart trong Java Swing");
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }
    
    public static void main(String[] args) {
		System.out.println(getLan1());
	}

}