public class Token {
    private final String data;
    private final TokenType type;

    public Token(String data, TokenType type) {
        this.data = data;
        this.type = type;
    }

    // Retorna o valor do token
    public String getData() {
        return data;
    }

    // Retorna o tipo do token
    public TokenType getType() {
        return type;
    }

    // Vê se o valor atual e o esperado são iguais
    public boolean match(String expected) {
        return this.data.equals(expected);
    }

    // Vê se o token atual é um número
    public boolean matchDigit() {
        return this.type == TokenType.NUMBER;
    }
}
