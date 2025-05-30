import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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

    public Map<String, Integer> countOccurrencesByField(int fieldIndex) {
        Map<String, Integer> occurrences = new TreeMap<>();
        countRecursive(this.root, fieldIndex, occurrences);
        return occurrences;
    }

    private void countRecursive(Node node, int fieldIndex, Map<String, Integer> occurrences) {
        if (node == null) return;

        countRecursive(node.getLeft(), fieldIndex, occurrences);

        for (Registro r : node.getRegistros()) {
            String value = getFieldValue(r, fieldIndex);
            occurrences.put(value, occurrences.getOrDefault(value, 0) + 1);
        }

        countRecursive(node.getRight(), fieldIndex, occurrences);
    }

    private String getFieldValue(Registro r, int fieldIndex) {
        switch (fieldIndex) {
            case 0: return r.getcpf();
            case 1: return Integer.toString(r.getAno());
            case 2: return Integer.toString(r.getIdMunicipio());
            case 3: return r.getIdMunicipioNome();
            case 4: return r.getDataNascimento();
            case 5: return r.getCampus();
            case 6: return r.getTurnoCurso();
            case 7: return r.getSexo();
            case 8: return r.getNomeMunicipioIes();
            case 9: return r.getModalidadeEnsino();
            case 10: return r.getCurso();
            case 11: return r.getRacaCor();
            case 12: return r.getTipoBolsa();
            case 13: return r.getSiglaUf();
            case 14: return r.getSiglaUfNome();
            case 15: return Boolean.toString(r.isBeneficiarioDeficiente());
            case 16: return Integer.toString(r.getIdIes());
            case 17: return r.getIdIesNome();
            case 18: return r.getIdIesTipoInstituicao();
            default: return "";
        }
    }

    public Map<Integer, Map<String, Integer>> countByFieldPerYear(int fieldIndex) {
        Map<Integer, Map<String, Integer>> yearToFieldCount = new TreeMap<>();
        countByFieldPerYearRecursive(root, fieldIndex, yearToFieldCount);
        return yearToFieldCount;
    }

    private void countByFieldPerYearRecursive(Node node, int fieldIndex,
            Map<Integer, Map<String, Integer>> yearToFieldCount) {
        if (node == null) return;

        countByFieldPerYearRecursive(node.getLeft(), fieldIndex, yearToFieldCount);

        for (Registro r : node.getRegistros()) {
            int year = r.getAno();
            String fieldValue = getFieldValue(r, fieldIndex);

            yearToFieldCount.putIfAbsent(year, new HashMap<>());
            Map<String, Integer> fieldCounts = yearToFieldCount.get(year);
            fieldCounts.put(fieldValue, fieldCounts.getOrDefault(fieldValue, 0) + 1);
        }

        countByFieldPerYearRecursive(node.getRight(), fieldIndex, yearToFieldCount);
    }
}
