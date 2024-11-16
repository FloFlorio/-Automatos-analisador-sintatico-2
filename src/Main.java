
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String expression = ""; // Escreva aqui a expressão entre aspas
        List<Token> tokens = Tokenizer.tokenize(expression);

        Tokenizer tokenizer = new Tokenizer(tokens);
        Parser parser = new Parser(tokenizer);

        boolean isValid = parser.parse();

        if (isValid) {
            System.out.println("A expressão é válida.");
            System.out.println("Árvore:");
            System.out.println(parser.getRoot().toString());
        } else {
            System.out.println("A expressão é inválida.");
        }
    }
}

