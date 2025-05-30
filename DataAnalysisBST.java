import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataAnalysisBST {

    public static double calculateMatchPercentage(BST tree, int fieldIndex, String value) {
        List<Registro> matchingRecords = tree.searchByField(fieldIndex, value);
        int total = tree.getTotalRecords();

        if (total == 0) return 0.0;

        return (matchingRecords.size() * 100.0) / total;
    }

    public static void printOccurrencesByField(BST tree, int fieldIndex) {
        Map<String, Integer> occurrences = tree.countOccurrencesByField(fieldIndex);
        int totalRecords = tree.getTotalRecords();

        List<Map.Entry<String, Integer>> entries = new ArrayList<>(occurrences.entrySet());

        entries.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));

        for (Map.Entry<String, Integer> entry : entries) {
            double percentage = (entry.getValue() * 100.0) / totalRecords;
            System.out.printf("%s: %d (%.2f%%)\n", entry.getKey(), entry.getValue(), percentage);
        }
    }
}
