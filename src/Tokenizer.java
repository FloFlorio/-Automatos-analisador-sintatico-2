import java.util.ArrayList;
import java.util.List;

public class Tokenizer {
    private final List<Token> tokens;
    private int pos;

    public Tokenizer(List<Token> tokens) {
        this.tokens = tokens;
        this.pos = 0;
    }

    // Retorna a lista de tokens
    public List<Token> getTokens() {
        return tokens;
    }

    // Retorna o token atual
    public Token currentToken() {
        if (pos < tokens.size()) {
            return tokens.get(pos);
        }
        return null;
    }

    // Retorna o próximo token e avança
    public Token getNextToken() {
        if (pos < tokens.size()) {
            return tokens.get(pos++);
        }
        return null;
    }

    // Função de tokenização para convrter em uma lista de tokens
    public static List<Token> tokenize(String expression) {
        List<Token> tokenList = new ArrayList<>(); // Lista de tokens que será retornada
        int i = 0;

        while (i < expression.length()) {
            char currentChar = expression.charAt(i);

            if (Character.isWhitespace(currentChar)) {
                i++; // Ignora espaços
            } else if (Character.isDigit(currentChar)) {
                StringBuilder number = new StringBuilder(); // Acumular números
                while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
                    number.append(expression.charAt(i));
                    i++;
                }
                tokenList.add(new Token(number.toString(), TokenType.NUMBER)); // Token de número
            } else { // Processa os operadores e parênteses
                switch (currentChar) {
                    case '+':
                    case '-':
                    case '*':
                    case '/':
                        tokenList.add(new Token(String.valueOf(currentChar), TokenType.OPERATOR)); // Token de operador
                        i++;
                        break;
                    case '(':
                        tokenList.add(new Token("(", TokenType.LEFT_PARENTHESIS)); // Token de parêntese esquerdo
                        i++;
                        break;
                    case ')':
                        tokenList.add(new Token(")", TokenType.RIGHT_PARENTHESIS)); // Token de parêntese direito
                        i++;
                        break;
                    default:  // Lança exceção se o caractere não for reconhecido
                        throw new IllegalArgumentException("Unexpected character: " + currentChar); // Erro se caracter inválido
                }
            }
        }
        return tokenList;  // Retorna a lista de tokens
    }
}
