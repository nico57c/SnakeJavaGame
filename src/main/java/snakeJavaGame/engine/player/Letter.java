package snakeJavaGame.engine.player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Letter {
    A('A'), B('B'), C('C'), D('D'), E('E'), F('F'), G('G'), H('H'), I('I'), J('J'), K('K'), L('L'), M('M'), N('N'), O(
            'O'), P('P'), Q('Q'), R('R'), S('S'), T('T'), U('U'), V('V'), W('W'), X('X'), Y('Y'), Z('Z'), DASH(
                    '-'), UNDERSCORE('_'), SPACE(' ');

    public final static List<Letter> letters = new ArrayList<Letter>(Arrays.asList(Letter.values()));

    private char value;

    Letter(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public boolean equals(String test) {
        return String.valueOf(value).compareToIgnoreCase(test) == 0;
    }

    public boolean equals(char test) {
        return value == test;
    }

    public static Letter create(String value) {
        return letters.stream().filter(item -> {
            return 0 == value.compareToIgnoreCase(String.valueOf(item.getValue()));
        }).findFirst().orElse(null);
    }

    public static String regex() {
        return "[" + letters.stream().map(item -> {
            if (Character.isLetterOrDigit(item.getValue())) {
                return "" + item.getValue();
            } else {
                return "\\" + item.getValue();
            }
        }).reduce("", String::concat) + "]+";
    }
}
