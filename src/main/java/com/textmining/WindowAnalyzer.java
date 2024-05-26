package com.textmining;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class WindowAnalyzer {
    private int windowSize;

    public WindowAnalyzer(int windowSize) {
        this.windowSize = windowSize;
    }

    public Map<String, Integer> analyze(List<String> text) throws InterruptedException {
        Map<String, Integer> overallFrequency = new ConcurrentHashMap<>();
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(4);

        for (int i = 0; i <= text.size() - windowSize; i++) {
            final int start = i;
            executor.execute(() -> {
                List<String> window = text.subList(start, start + windowSize);
                Map<String, Integer> wordFrequency = new ConcurrentHashMap<>();
                for (String word : window) {
                    wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
                }
                synchronized (overallFrequency) {
                    wordFrequency.forEach((k, v) -> overallFrequency.merge(k, v, Integer::sum));
                }
            });
        }

        executor.shutdown();
        while (!executor.isTerminated()) {
            Thread.sleep(100);
        }

        return overallFrequency;
    }
}
