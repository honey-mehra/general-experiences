import java.util.Stack;

/*

Make sure to enable assertions by passing VM argument -ea to Solution.java when running the same.
 */
public class Solution {

    public static void main(String[] args) {


        BracketValidator validator = new BracketValidator();

        assert validator.isValid("{[]()}")   == true;
        assert validator.isValid("{[(])}")   == false;
        assert validator.isValid("{[}")      == false;
        assert validator.isValid("[{{()}}]") == true;
        assert validator.isValid("{[}]")     == false;
        assert validator.isValid("{")        == false;
        assert validator.isValid("}")        == false;

        System.out.println("End Program");
    }
}

/*
This class has a utility method which verifies if the brackets or parentheses are balanced.

openers --> '(','{','['
closers --> ')','}',']'

Examples:

    "{ [] }"        correct
    "{ [] () }"     correct
    "{ [[ ]] }"     correct
    "{ [(] ) }"     wrong
    "{ [ }"         wrong
    "{ [ } ]"       wrong
    "{} [] ()"      correct
    "[{ { () } }]"  correct
 */
class BracketValidator {

    public static boolean isValid(String input) {

        if(input == null || input.length()==0) {
            return true;
        }
        char[] brackets = input.toCharArray();

        Stack<Character> stack = new Stack<>();
        for(char bracket : brackets) {

            switch(bracket) {

                case '(':
                    stack.push(')');
                    break;
                case '{':
                    stack.push('}');
                    break;
                case '[':
                    stack.push(']');
                    break;

                default:
                    if(stack.isEmpty() || bracket != stack.peek()) {
                        return false;
                    }
                    stack.pop();
                    break;
            }
        }
        return stack.isEmpty();
    }
}