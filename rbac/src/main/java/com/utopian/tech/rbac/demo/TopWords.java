package com.utopian.tech.rbac.demo;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class TopWords {

    // java如何快速在一个2G的文本文件中查找出出现频率为前100的单词
    public static void main(String[] args) throws IOException {

        String filePath = "path/to/2GB/file.txt";
        int topN = 100;
        Map<String, Integer> wordMap = new HashMap<String, Integer>();

        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] words = line.split("[^a-zA-Z']+");
                for (String word : words) {
                    if (word.length() > 0) {
                        String lowerCaseWord = word.toLowerCase();
                        wordMap.put(lowerCaseWord, wordMap.getOrDefault(lowerCaseWord, 0) + 1);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // TreeMap 自带排序功能，默认为升序排序
        TreeMap<Integer, String> frequencyMap = new TreeMap<Integer, String>();
        for (Map.Entry<String, Integer> entry : wordMap.entrySet()) {
            String word = entry.getKey();
            int frequency = entry.getValue();
            frequencyMap.put(frequency, word);
            if (frequencyMap.size() > topN) {
                frequencyMap.remove(frequencyMap.firstKey());
            }
        }

        System.out.println("Top " + topN + " words:");
        for (Map.Entry<Integer, String> entry : frequencyMap.descendingMap().entrySet()) {
            System.out.println(entry.getValue() + ": " + entry.getKey());
        }
    }
}
