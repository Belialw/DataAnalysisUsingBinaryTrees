import java.util.ArrayList;
import java.util.List;

public class Node {
    private List<Registro> registros;
    private Node left;
    private Node right;
    private int height;

    public Node(Registro data) {
        this.registros = new ArrayList<>();
        this.registros.add(data);
        this.left = null;
        this.right = null;
        this.height = 1;
    }

    public List<Registro> getRegistros() {
        return registros;
    }

    public void setRegistros(List<Registro> registros) {
        this.registros = registros;
    }

    public void addRegistro(Registro registro) {
        this.registros.add(registro);
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
