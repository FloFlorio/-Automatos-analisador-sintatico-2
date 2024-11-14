
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String expression = "(3 + 5) * (10 - 2)";
        List<Token> tokens = Tokenizer.tokenize(expression);

        Tokenizer tokenizer = new Tokenizer(tokens);
        Parser parser = new Parser(tokenizer);

        boolean isValid = parser.parse();

        if (isValid) {
            System.out.println("A expressão é válida.");
            System.out.println("Árvore sintática:");
            System.out.println(parser.getRoot().toString());
        } else {
            System.out.println("A expressão é inválida.");
        }
    }
}

