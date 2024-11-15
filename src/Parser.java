public class Parser {
    private final Tokenizer tokenizer;
    private Token current;
    private final Node root;

    public Parser(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
        this.current = tokenizer.getNextToken(); // Obtém o primeiro token
        this.root = new Node("S");  // O nó raiz começa com o símbolo inicial S.
    }

    // Retorna o nó raiz da árvore
    public Node getRoot() {
        return root;
    }

    // Começa a análise pela raiz, o símbolo "S"
    public boolean parse() {
        return parseS(root);
    }

    // Analisa as regras de S
    private boolean parseS(Node parent) {
        Node node = new Node("S"); // Cria um novo nó para "S"
        parent.setChildren(node);

        // Verifica se o token atual é '+' ou '-'
        if (current != null && (current.match("+") || current.match("-"))) {
            node.setChildren(new Node(current.getData())); // Adiciona o sinal como um filho
            consume();
        }
        if (parseT(node)) {
            return parseK(node);
        }
        return false; // Retorna falso se "T" não for válido
    }

    // Analisa as regras de K
    private boolean parseK(Node parent) {
        Node node = new Node("K");
        parent.setChildren(node);

        // Verificar se o token atual é '+' ou '-'
        if (current != null && (current.match("+") || current.match("-"))) {
            node.setChildren(new Node(current.getData()));
            consume();
            if (parseT(node)) {
                return parseK(node);
            }
            return false; // Retorna falso se "T" não for válido
        }
        return true;  // Caso vazio
    }

    // Analisa as regras de T
    private boolean parseT(Node parent) {
        Node node = new Node("T");
        parent.setChildren(node);

        if (parseF(node)) {
            return parseZ(node);
        }
        return false; // Retorna falso se "F" não for válido
    }

    // Analisa as regras de F
    private boolean parseF(Node parent) {
        Node node = new Node("F");
        parent.setChildren(node);

        // Verificar se o token atual é um parêntese '('
        if (current != null && current.match("(")) {
            node.setChildren(new Node("("));
            consume();
            if (parseS(node)) {  // Analisa a expressão dentro dos parênteses
                if (current != null && current.match(")")) {
                    node.setChildren(new Node(")"));
                    consume();
                    return true;
                }
            }
            return false; // Retorna falso se a expressão entre parênteses não for válida
        } else if (current != null && current.match("-")) {
            node.setChildren(new Node("-"));
            consume();
            return parseN(node);
        } else {
            return parseN(node);  // Caso seja um número
        }
    }

    // Analisa as regras de N
    private boolean parseN(Node parent) {
        Node node = new Node("N");
        parent.setChildren(node);

        // Verificar se o token atual é um número
        if (current != null && current.matchDigit()) {
            node.setChildren(new Node(current.getData()));
            consume();
            return parseD(node);
        }
        return false; // Retorna falso se o token não for um número
    }

    // Analisa as regras de D
    private boolean parseD(Node parent) {
        Node node = new Node("D");
        parent.setChildren(node);

        // Verificar se o token atual é um dígito
        if (current != null && current.matchDigit()) {
            node.setChildren(new Node(current.getData()));
            consume();
            return parseD(node);
        }
        return true; // Caso vazio
    }

    // Analisa as regras de Z
    private boolean parseZ(Node parent) {
        Node node = new Node("Z");
        parent.setChildren(node);

        // Verificar se o token atual é '*' ou '/'
        if (current != null && (current.match("*") || current.match("/"))) {
            node.setChildren(new Node(current.getData()));
            consume();
            if (parseF(node)) {
                return parseZ(node);
            }
            return false; // Retorna falso se "F" não for válido
        }
        return true;  // Caso vazio
    }

    // Consome e avança para o próximo token
    private void consume() {
        if (current != null) {
            current = tokenizer.getNextToken();
        }
    }
}
