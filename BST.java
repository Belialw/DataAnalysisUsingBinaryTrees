import java.util.ArrayList;
import java.util.List;

public class BST {
    private Node root;
    private int totalRecords = 0;

    public BST() {
        this.root = null;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void insert(Registro registro) {
        root = insert(root, registro);
    }

    private Node insert(Node current, Registro registro) {
        if (current == null) {
            totalRecords++; // novo registro inserido
            return new Node(registro);
        }

        int comparison = registro.getKey().compareTo(current.getRegistros().get(0).getKey());

        if (comparison < 0) {
            current.setLeft(insert(current.getLeft(), registro));
        } else if (comparison > 0) {
            current.setRight(insert(current.getRight(), registro));
        } else {
            current.addRegistro(registro);
            totalRecords++; // chave já existe, mas novo registro adicionado
        }

        return current;
    }

    public void printInOrder() {
        printInOrder(root);
    }

    private void printInOrder(Node node) {
        if (node != null) {
            printInOrder(node.getLeft());

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
            totalRecords -= current.getRegistros().size(); // subtrai todos os registros do nó

            // Caso: folha (sem filhos)
            if (current.getLeft() == null && current.getRight() == null) {
                return null;
            }

            // Caso: um filho apenas
            if (current.getLeft() == null) return current.getRight();
            if (current.getRight() == null) return current.getLeft();

            // Caso: dois filhos — substituir pelo menor da subárvore direita
            Node smallest = findMin(current.getRight());
            totalRecords += smallest.getRegistros().size(); // vamos reinserir esses registros
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

    public Node searchByKey(CompositeKey key) {
        return searchByKey(root, key);
    }

    private Node searchByKey(Node current, CompositeKey key) {
        if (current == null) {
            return null;
        }

        int comparison = key.compareTo(current.getRegistros().get(0).getKey());

        if (comparison < 0) {
            return searchByKey(current.getLeft(), key);
        } else if (comparison > 0) {
            return searchByKey(current.getRight(), key);
        } else {
            return current;
        }
    }

    public List<Registro> searchByField(int fieldIndex, String value) {
        List<Registro> results = new ArrayList<>();
        searchByFieldRecursive(root, fieldIndex, value, results);
        return results;
    }

    private void searchByFieldRecursive(Node node, int fieldIndex, String value, List<Registro> results) {
        if (node != null) {
            searchByFieldRecursive(node.getLeft(), fieldIndex, value, results);

            for (Registro r : node.getRegistros()) {
                if (fieldMatches(r, fieldIndex, value)) {
                    results.add(r);
                }
            }

            searchByFieldRecursive(node.getRight(), fieldIndex, value, results);
        }
    }

    private boolean fieldMatches(Registro r, int fieldIndex, String value) {
        switch (fieldIndex) {
            case 0: return r.getcpf().equals(value);
            case 1: return Integer.toString(r.getAno()).equals(value);
            case 2: return Integer.toString(r.getIdMunicipio()).equals(value);
            case 3: return r.getIdMunicipioNome().equalsIgnoreCase(value);
            case 4: return r.getDataNascimento().equals(value);
            case 5: return r.getCampus().equalsIgnoreCase(value);
            case 6: return r.getTurnoCurso().equalsIgnoreCase(value);
            case 7: return r.getSexo().equalsIgnoreCase(value);
            case 8: return r.getNomeMunicipioIes().equalsIgnoreCase(value);
            case 9: return r.getModalidadeEnsino().equalsIgnoreCase(value);
            case 10: return r.getCurso().equalsIgnoreCase(value);
            case 11: return r.getRacaCor().equalsIgnoreCase(value);
            case 12: return r.getTipoBolsa().equalsIgnoreCase(value);
            case 13: return r.getSiglaUf().equalsIgnoreCase(value);
            case 14: return r.getSiglaUfNome().equalsIgnoreCase(value);
            case 15: return Boolean.toString(r.isBeneficiarioDeficiente()).equalsIgnoreCase(value);
            case 16: return Integer.toString(r.getIdIes()).equals(value);
            case 17: return r.getIdIesNome().equalsIgnoreCase(value);
            case 18: return r.getIdIesTipoInstituicao().equalsIgnoreCase(value);
            default: return false;
        }
    }
}
