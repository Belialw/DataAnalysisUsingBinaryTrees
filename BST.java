public class BST {
    private Node root;

    public BST() {
        this.root = null;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public void insert(Registro registro) {
        root = insert(root, registro);
    }

    private Node insert(Node current, Registro registro) {
        if (current == null) {
            return new Node(registro);
        }

        int comparison = registro.getKey().compareTo(current.getRegistros().get(0).getKey());

        if (comparison < 0) {
            current.setLeft(insert(current.getLeft(), registro));
        } else if (comparison > 0) {
            current.setRight(insert(current.getRight(), registro));
        } else {
            current.addRegistro(registro);
        }

        return current;
    }

    public void printInOrder() {
        printInOrder(root);
    }

    private void printInOrder(Node node) {
        if (node != null) {
            printInOrder(node.getLeft());
            
            // Imprime todos os registros daquele nó (já que pode ter duplicatas)
            for (Registro r : node.getRegistros()) {
                System.out.println(r);
            }

            printInOrder(node.getRight());
        }
    }

    public void removeByCpf(String rawCpf) {
        String cleanCpf = rawCpf.replaceAll("\\D", "");

        for (int ano = 2005; ano <= 2019; ano++) {
            CompositeKey keyToRemove = new CompositeKey(cleanCpf, ano);
            root = remove(root, keyToRemove);
        }
    }

    private Node remove(Node current, CompositeKey key) {
        if (current == null) {
            return null;
        }

        int comparison = key.compareTo(current.getRegistros().get(0).getKey());

        if (comparison < 0) {
            current.setLeft(remove(current.getLeft(), key));
        } else if (comparison > 0) {
            current.setRight(remove(current.getRight(), key));
        } else {
            // Caso: folha (sem filhos)
            if (current.getLeft() == null && current.getRight() == null) {
                return null;
            }

            // Caso: um filho apenas
            if (current.getLeft() == null) return current.getRight();
            if (current.getRight() == null) return current.getLeft();

            // Caso: dois filhos — substituir pelo menor da subárvore direita
            Node smallest = findMin(current.getRight());
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
    

}
