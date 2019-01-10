package WordVectors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class WordVectors {

    private Map<String, Word> words;
    private List<Word> windowWords;

    public WordVectors(String[] words, List<List<Integer>> vectors) {
        windowWords = new ArrayList<>();
        this.words = new HashMap<>();
        IntStream.range(0, words.length)
                        .mapToObj(index -> new Word(words[index], vectors.get(index)))
                        .forEach(word -> this.words.put(word.getName(), word));
    }

    public void readWords(List<String> wordsList) {
        this.windowWords = wordsList.stream()
                                    .map(word -> {
                                        Word newWord = null;
                                        for(String s : words.keySet()){
                                            if(s.length() != word.length())
                                                continue;
                                            int difference = compareStrings(s, word);
                                            if(difference < 2) {
                                                newWord = new Word(word, words.get(s).getNumbers());
                                                break;
                                            }
                                        }
                                        if(newWord == null) {
                                            List<Integer> temp = new ArrayList<>();
                                            temp.add(5);
                                            temp.add(5);
                                            temp.add(5);
                                            temp.add(5);
                                            temp.add(5);
                                            newWord = new Word(word, temp);
                                        }
                                        return newWord;
                                    }).collect(Collectors.toList());
    }

    private int compareStrings(String string1, String string2) {
        return IntStream.range(0, string1.length())
                .map(index -> {
                    if(string1.charAt(index) == string2.charAt(index))
                        return 0;
                    return 1;
                }).sum();
    }

    public List<Integer> slidingWindow(int n) {
         return IntStream.range(0, windowWords.size() - n + 1)
                 .map(index -> {
                     List<List<Integer>> temp = new ArrayList<>();
                     temp = IntStream.range(index, index+n)
                                .mapToObj(newIndex -> windowWords.get(newIndex).getNumbers())
                                .collect(Collectors.toList());
                     return getVectorSum(temp);
                 })
                 .boxed()
                 .collect(Collectors.toList());
    }

    private Integer getVectorSum(List<List<Integer>> vectors) {
        return IntStream.range(0,5)
                    .map(index -> {
                            return vectors.stream()
                                    .mapToInt(lista -> lista.get(index))
                                    .sum();
                    }).max().orElse(0);
    }
}
