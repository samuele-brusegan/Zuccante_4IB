import Stack.StackRef;

public class Main {
    public static void main(String[] args) throws WrongBracketExeption {

        String str = "{{{[[()()()]]}}}";

        char[] parenthesisO = new char[]{'{', '[', '('};
        char[] parenthesisC = new char[]{'}', ']', ')'};

        StackRef<Character> stack = new StackRef<>();

        for (int i = 0; i < str.length() -1; i++) {
            char c = str.charAt(i);

            int posInOpen  = findInArray(parenthesisO, c);

            int posInClose = findInArray(parenthesisC, c);

            //È una parentesi aperta
            if (posInOpen != -1) stack.push(c);

            //È una parentesi chiusa
            if (posInClose != -1) {
                char p = stack.pop();
                if (parenthesisO[posInClose] != p) {
                    throw new WrongBracketExeption("The bracket " + p + " don't close" + c);
                }
            }
        }
        System.out.println("Correct!");
    }

    public static int findInArray(char[] array, char needle) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == needle) return i;
        }
        return -1;
    }
}