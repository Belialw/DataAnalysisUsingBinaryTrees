import java.util.ArrayList;
import java.util.List;

public class BST extends Tree {

    @Override
    public void insert(Registro registro) {
        root = insert(root, registro);
    }

    private Node insert(Node current, Registro registro) {
        if (current == null) {
            totalRecords++;
            return new Node(registro);
        }

        int comparison = registro.getKey().compareTo(current.getRegistros().get(0).getKey());

        if (comparison < 0) {
            current.setLeft(insert(current.getLeft(), registro));
        } else if (comparison > 0) {
            current.setRight(insert(current.getRight(), registro));
        } else {
            current.addRegistro(registro);
            totalRecords++;
        }

        return current;
    }

    @Override
    public void removeByCpf(String rawCpf) {
        String cleanCpf = rawCpf.replaceAll("\\D", "");
        for (int ano = 2005; ano <= 2020; ano++) {
            CompositeKey key = new CompositeKey(cleanCpf, ano);
            root = remove(root, key);
        }
    }

    private Node remove(Node current, CompositeKey key) {
        if (current == null) return null;

        int comparison = key.compareTo(current.getRegistros().get(0).getKey());

        if (comparison < 0) {
            current.setLeft(remove(current.getLeft(), key));
        } else if (comparison > 0) {
            current.setRight(remove(current.getRight(), key));
        } else {
            totalRecords -= current.getRegistros().size();

            if (current.getLeft() == null && current.getRight() == null) return null;
            if (current.getLeft() == null) return current.getRight();
            if (current.getRight() == null) return current.getLeft();

            Node smallest = findMin(current.getRight());
            totalRecords += smallest.getRegistros().size();
            current.setRegistros(smallest.getRegistros());
            current.setRight(remove(current.getRight(), smallest.getRegistros().get(0).getKey()));
        }

        return current;
    }

    private Node findMin(Node node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }

    
    @Override
    public void removeByYearRange(int minYear, int maxYear) {
        List<CompositeKey> keysToRemove = new ArrayList<>();
        collectKeysInRange(root, minYear, maxYear, keysToRemove);

        for (CompositeKey key : keysToRemove) {
            root = remove(root, key);
        }
    }

    private void collectKeysInRange(Node node, int minYear, int maxYear, List<CompositeKey> keysToRemove) {
        if (node == null) return;

        collectKeysInRange(node.getLeft(), minYear, maxYear, keysToRemove);

        // Verifica os registros do nó atual
        for (Registro r : node.getRegistros()) {
            if (r.getAno() >= minYear && r.getAno() <= maxYear) {
                keysToRemove.add(r.getKey()); // CompositeKey
                break; // Como todos os registros compartilham a mesma chave, basta um match
            }
        }

        collectKeysInRange(node.getRight(), minYear, maxYear, keysToRemove);
    }

}
