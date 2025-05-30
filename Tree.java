import java.util.*;

public abstract class Tree {
    protected Node root;
    protected int totalRecords = 0;

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public int getTotalRecords() {
        return totalRecords;
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

    public Node searchByKey(CompositeKey key) {
        return searchByKey(root, key);
    }

    protected Node searchByKey(Node current, CompositeKey key) {
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

    private String getFieldValue(Registro r, int fieldIndex) {
        return switch (fieldIndex) {
            case 0 -> r.getcpf();
            case 1 -> Integer.toString(r.getAno());
            case 2 -> Integer.toString(r.getIdMunicipio());
            case 3 -> r.getIdMunicipioNome();
            case 4 -> r.getDataNascimento();
            case 5 -> r.getCampus();
            case 6 -> r.getTurnoCurso();
            case 7 -> r.getSexo();
            case 8 -> r.getNomeMunicipioIes();
            case 9 -> r.getModalidadeEnsino();
            case 10 -> r.getCurso();
            case 11 -> r.getRacaCor();
            case 12 -> r.getTipoBolsa();
            case 13 -> r.getSiglaUf();
            case 14 -> r.getSiglaUfNome();
            case 15 -> Boolean.toString(r.isBeneficiarioDeficiente());
            case 16 -> Integer.toString(r.getIdIes());
            case 17 -> r.getIdIesNome();
            case 18 -> r.getIdIesTipoInstituicao();
            default -> "";
        };
    }

    // Assinaturas dos métodos que devem ser implementados pelas subclasses
    public abstract void insert(Registro registro);
    public abstract void removeByCpf(String cpf);
    public abstract void removeByYearRange(int minYear, int maxYear);
}
