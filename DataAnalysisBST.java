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
            String label = entry.getKey().isEmpty() ? "Indisponível" : entry.getKey();
            double percentage = (entry.getValue() * 100.0) / totalRecords;
            System.out.printf("%s: %d (%.4f%%)%n", label, entry.getValue(), percentage);
        }
    }

    public static void printFieldOccurrencesPerYear(BST tree, int fieldIndex) {
        Map<Integer, Map<String, Integer>> countsPerYear = tree.countByFieldPerYear(fieldIndex);

        for (Map.Entry<Integer, Map<String, Integer>> yearEntry : countsPerYear.entrySet()) {
            int year = yearEntry.getKey();
            Map<String, Integer> fieldCounts = yearEntry.getValue();

            int total = fieldCounts.values().stream().mapToInt(Integer::intValue).sum();

            List<Map.Entry<String, Integer>> sortedEntries = new ArrayList<>(fieldCounts.entrySet());
            sortedEntries.sort((e1, e2) -> Integer.compare(e2.getValue(), e1.getValue()));

            System.out.println("Ano " + year + ":");

            for (Map.Entry<String, Integer> entry : sortedEntries) {
                String label = entry.getKey().isEmpty() ? "Indisponível" : entry.getKey();
                int count = entry.getValue();
                double percentage = (count * 100.0) / total;

                System.out.printf("  %s: %d (%.4f%%)%n", label, count, percentage);
            }

            System.out.println();
        }
    }
}
