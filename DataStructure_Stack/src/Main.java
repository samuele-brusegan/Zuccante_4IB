public class Main {
    public static void main(String[] args) {
        String str = "la casa";
        StackRef<Character> stack = new StackRef<>();

        for (int i = 0; i < str.length(); i++) {
            stack.push(str.charAt(i));
        }

        System.out.println(stack);

        String out = "";
        for (int i = stack.size(); i > 0; i--) {
            char c = stack.pop();
            out = c + out;
        }
        System.out.println(out);
    }
}