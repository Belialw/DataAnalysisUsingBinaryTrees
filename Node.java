public class Node {
    private Registro data;
    private Node left;
    private Node right;
    private int height;

    public Node(Registro data) {
        this.data = data;
        this.left = null;
        this.right = null;
        this.height = 1;
    }

    public Registro getData() {
        return data;
    }

    public void setData(Registro data) {
        this.data = data;
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
