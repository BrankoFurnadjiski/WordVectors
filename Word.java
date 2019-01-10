package WordVectors;

import java.util.List;

public class Word {
    private String name;
    private List<Integer> numbers;

    public Word(String name, List<Integer> numbers) {
        this.name = name;
        this.numbers = numbers;
    }

    public String getName() {
        return name;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public String toString() {
        return String.format("%s %d:%d:%d:%d:%d", name, numbers.get(0),numbers.get(1),numbers.get(2),numbers.get(3),numbers.get(4));
    }
}
