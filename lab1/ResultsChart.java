import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;

public class ResultsChart {

    public ResultsChart(int[] sizes, double[] timeIn, double[] timeNotIn, double[] timeUnion) {
        createSearchChart(sizes, timeIn, timeNotIn);
        createUnionChart(sizes, timeUnion);
    }

    private void createSearchChart(int[] sizes, double[] timeIn, double[] timeNotIn) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 0; i < sizes.length; i++) {
            dataset.addValue(timeIn[i], "Search (element in set)", String.valueOf(sizes[i]));
            dataset.addValue(timeNotIn[i], "Search (element not in set)", String.valueOf(sizes[i]));
        }

        JFreeChart chart = ChartFactory.createLineChart(
                "Search Operation Execution Time",
                "Set Size",
                "Time (microseconds)",
                dataset
        );

        showChart(chart, "Search Time Chart");
    }

    private void createUnionChart(int[] sizes, double[] timeUnion) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 0; i < sizes.length; i++) {
            dataset.addValue(timeUnion[i], "Union", String.valueOf(sizes[i]));
        }

        JFreeChart chart = ChartFactory.createLineChart(
                "Union Operation Execution Time",
                "Set Size",
                "Time (microseconds)",
                dataset
        );

        showChart(chart, "Union Time Chart");
    }

    private void showChart(JFreeChart chart, String title) {
        ChartPanel chartPanel = new ChartPanel(chart);
        JFrame frame = new JFrame(title);
        frame.setSize(1000, 600);
        frame.setContentPane(chartPanel);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
