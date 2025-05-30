import java.util.ArrayList;
import java.util.List;

public class AVL extends Tree {

    @Override
    public void insert(Registro registro) {
        root = insertAVL(root, registro);
    }

    private Node insertAVL(Node node, Registro registro) {
        if (node == null) {
            totalRecords++;
            return new Node(registro);
        }

        int comparison = registro.getKey().compareTo(node.getRegistros().get(0).getKey());

        if (comparison < 0) {
            node.setLeft(insertAVL(node.getLeft(), registro));
        } else if (comparison > 0) {
            node.setRight(insertAVL(node.getRight(), registro));
        } else {
            node.addRegistro(registro);
            totalRecords++;
            return node;
        }

        updateHeight(node);
        return balance(node);
    }

    @Override
    public void removeByCpf(String rawCpf) {
        String cleanCpf = rawCpf.replaceAll("\\D", "");
        for (int ano = 2005; ano <= 2020; ano++) {
            CompositeKey key = new CompositeKey(cleanCpf, ano);
            root = removeAVL(root, key);
        }
    }

    private Node removeAVL(Node node, CompositeKey key) {
        if (node == null) return null;

        int comparison = key.compareTo(node.getRegistros().get(0).getKey());

        if (comparison < 0) {
            node.setLeft(removeAVL(node.getLeft(), key));
        } else if (comparison > 0) {
            node.setRight(removeAVL(node.getRight(), key));
        } else {
            totalRecords -= node.getRegistros().size();

            if (node.getLeft() == null && node.getRight() == null) return null;
            if (node.getLeft() == null) return node.getRight();
            if (node.getRight() == null) return node.getLeft();

            Node minRight = findMin(node.getRight());
            node.setRegistros(minRight.getRegistros());
            node.setRight(removeAVL(node.getRight(), minRight.getRegistros().get(0).getKey()));
        }

        updateHeight(node);
        return balance(node);
    }

    private Node findMin(Node node) {
        while (node.getLeft() != null)
            node = node.getLeft();
        return node;
    }

    private int height(Node node) {
        return node == null ? -1 : node.getHeight();
    }

    private void updateHeight(Node node) {
        node.setHeight(1 + Math.max(height(node.getLeft()), height(node.getRight())));
    }

    private int getBalanceFactor(Node node) {
        return node == null ? 0 : height(node.getLeft()) - height(node.getRight());
    }

    private Node balance(Node node) {
        int balanceFactor = getBalanceFactor(node);

        // Left heavy
        if (balanceFactor > 1) {
            if (getBalanceFactor(node.getLeft()) < 0)
                node.setLeft(rotateLeft(node.getLeft()));
            return rotateRight(node);
        }

        // Right heavy
        if (balanceFactor < -1) {
            if (getBalanceFactor(node.getRight()) > 0)
                node.setRight(rotateRight(node.getRight()));
            return rotateLeft(node);
        }

        return node;
    }

    private Node rotateRight(Node y) {
        Node x = y.getLeft();
        Node T2 = x.getRight();

        x.setRight(y);
        y.setLeft(T2);

        updateHeight(y);
        updateHeight(x);

        return x;
    }

    private Node rotateLeft(Node x) {
        Node y = x.getRight();
        Node T2 = y.getLeft();

        y.setLeft(x);
        x.setRight(T2);

        updateHeight(x);
        updateHeight(y);

        return y;
    }

    @Override
    public void removeByYearRange(int minYear, int maxYear) {
        List<CompositeKey> keysToRemove = new ArrayList<>();
        collectKeysInRange(root, minYear, maxYear, keysToRemove);

        for (CompositeKey key : keysToRemove) {
            root = removeAVL(root, key);
        }
    }

    private void collectKeysInRange(Node node, int minYear, int maxYear, List<CompositeKey> keysToRemove) {
        if (node == null) return;

        collectKeysInRange(node.getLeft(), minYear, maxYear, keysToRemove);

        for (Registro r : node.getRegistros()) {
            if (r.getAno() >= minYear && r.getAno() <= maxYear) {
                keysToRemove.add(r.getKey());
                break;
            }
        }

        collectKeysInRange(node.getRight(), minYear, maxYear, keysToRemove);
    }

}
