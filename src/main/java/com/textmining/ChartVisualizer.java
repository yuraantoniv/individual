package com.textmining;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.util.Map;

public class ChartVisualizer {
    public static void visualizeWordFrequency(Map<String, Integer> wordFrequency) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()) {
            dataset.addValue(entry.getValue(), "Frequency", entry.getKey());
        }

        JFreeChart barChart = ChartFactory.createBarChart(
                "Word Frequency",
                "Word",
                "Frequency",
                dataset,
                PlotOrientation.VERTICAL,
                false, true, false);

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));

        JFrame frame = new JFrame();
        frame.setContentPane(chartPanel);
        frame.pack();
        frame.setVisible(true);
    }
}