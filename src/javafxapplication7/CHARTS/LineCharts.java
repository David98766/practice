/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxapplication7.CHARTS;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

/**
 *
 * @author I586588
 * 
 */
public class LineCharts {

    
    // Method to initialize the line chart
    public void generateDashboardLineChart(XYChart chartDashboardLineChart) {
        // Create a new series for monthly revenue
        XYChart.Series seriesMonthlyRevenue = new XYChart.Series();
        
        //Setting name for the series in the legend
        //seriesMonthlyRevenue.setName("Monthly Revenue");
        
        // Add data points to the series
        seriesMonthlyRevenue.getData().add(new XYChart.Data("Jan", 300));
        seriesMonthlyRevenue.getData().add(new XYChart.Data("Feb", 700));
        seriesMonthlyRevenue.getData().add(new XYChart.Data("Mar", 600));
        seriesMonthlyRevenue.getData().add(new XYChart.Data("Apr", 500));
        seriesMonthlyRevenue.getData().add(new XYChart.Data("May", 500));
        seriesMonthlyRevenue.getData().add(new XYChart.Data("Jun", 900));
        seriesMonthlyRevenue.getData().add(new XYChart.Data("Jul", 1200));
        seriesMonthlyRevenue.getData().add(new XYChart.Data("Aug", 400));
        seriesMonthlyRevenue.getData().add(new XYChart.Data("Sep", 300));
        seriesMonthlyRevenue.getData().add(new XYChart.Data("Oct", 700));
        seriesMonthlyRevenue.getData().add(new XYChart.Data("Nov", 600));
        seriesMonthlyRevenue.getData().add(new XYChart.Data("Dec", 800));
        
        // Add the series to the line chart
        chartDashboardLineChart.getData().add(seriesMonthlyRevenue);
    }
}
