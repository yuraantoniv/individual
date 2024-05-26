package com.textmining;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        try {
            String filePath = "src/main/resources/textfile.txt";
            List<String> text = TextReader.readText(filePath);
            List<String> processedText = TextProcessor.preprocessText(text);

            WindowAnalyzer analyzer = new WindowAnalyzer(5); // Розмір вікна - 5 слів
            Map<String, Integer> overallFrequency = analyzer.analyze(processedText);

            overallFrequency.forEach((word, frequency) ->
                    System.out.println(word + ": " + frequency));

            ChartVisualizer.visualizeWordFrequency(overallFrequency);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
