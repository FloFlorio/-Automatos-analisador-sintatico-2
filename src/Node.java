import java.util.ArrayList;
import java.util.List;

public class Node {
    private final String data;
    private final List<Node> children;

    public Node(String data) {
        this.data = data;
        this.children = new ArrayList<>();
    }

    //Adiciona nó filho na lista de filhos
    public void setChildren(Node child) {
        children.add(child);
    }

    //Retorna o valor do nó
    public String getData() {
        return data;
    }
    //Retorna a lista de filhos do nó
    public List<Node> getChildren() {
        return children;
    }

    // Override com método toString para representar o nó e seus filhos
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        toStringRecursive(sb, 0);
        return sb.toString();
    }

    private void toStringRecursive(StringBuilder sb, int level) {
        for (int i = 0; i < level; i++) { // Identação com base no nível de profundidade do nó
            sb.append("  ");
        }
        sb.append(data).append("\n");
        for (Node child : children) {
            child.toStringRecursive(sb, level + 1);
        }
    }
}
