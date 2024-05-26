package com.textmining;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextProcessor {
    public static List<String> preprocessText(List<String> text) {
        List<String> processedText = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\b\\w+\\b");

        for (String line : text) {
            line = line.toLowerCase();
            Matcher matcher = pattern.matcher(line);

            while (matcher.find()) {
                processedText.add(matcher.group());
            }
        }
        return processedText;
    }
}

