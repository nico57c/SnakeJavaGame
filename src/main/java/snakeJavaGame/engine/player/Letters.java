package snakeJavaGame.engine.player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Letters {

    private final static List<LETTER> letters = new ArrayList<LETTER>(Arrays.asList(LETTER.values()));

    public static enum LETTER {
        A('A'), B('B'), C('C'), D('D'), E('E'), F('F'), G('G'), H('H'), I('I'), J('J'), K('K'), L('L'), M('M'), N(
                'N'), O('O'), P('P'), Q('Q'), R('R'), S('S'), T('T'), U('U'), V('V'), W('W'), X('X'), Y('Y'), Z(
                        'Z'), DASH('-'), UNDERSCORE('_'), SPACE(' ');

        private char value;

        LETTER(char value) {
            this.value = value;
        }

        public char getValue() {
            return value;
        }

        public String toString() {
            return "" + value;
        }

        public boolean equals(String test) {
            return toString().equals(test);
        }

        public boolean equals(char test) {
            return value == test;
        }

        public static LETTER create(String value) {
            int index = letters.indexOf(value);
            if (index >= 0) {
                return letters.get(index);
            } else {
                return null;
            }
        }

        public static String regex() {
            return "[" + letters.stream().map(item -> item.toString()).reduce("", String::concat) + "]*";
        }
    }
}
