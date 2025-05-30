import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
 * Referências dos campos (fieldIndex):
 * 0: CPF
 * 1: Ano
 * 2: Id Município
 * 3: Nome Município
 * 4: Data Nascimento
 * 5: Campus
 * 6: Turno Curso
 * 7: Sexo
 * 8: Nome Município IES
 * 9: Modalidade Ensino
 * 10: Curso
 * 11: Raça/Cor
 * 12: Tipo Bolsa
 * 13: Sigla UF
 * 14: Sigla UF Nome
 * 15: Beneficiário Deficiente (boolean)
 * 16: Id IES
 * 17: Nome IES
 * 18: Tipo Instituição IES
 */

public class DataAnalysisBST {

    public static void AnalyseData(BST tree) {
        // 1. Como a quantidade de bolsas de estudo variou ao longo dos anos?
        // Implementar uma função pra contar as bolsas por ano

        // Analise da proporção entre tipos de bolsa ao longo do tempo:
        System.out.println("2. Qual a proporção de bolsas integrais em relação às bolsas parciais?\n");
        printOccurrencesByField(tree, 12, -1);
        System.out.println("\n2.1 Houveram grandes mudanças durante os anos?\n");
        printFieldOccurrencesPerYear(tree, 12, -1);

        // Analise do perfil racial dos beneficiados ao longo do tempo:
        System.out.println("3. Qual o perfil racial dos estudantes bolsistas?\n");
        System.out.println("3.1. Qual é a distribuição por raça/cor?\n");
        printOccurrencesByField(tree, 11, -1);
        System.out.println("\n3.2. Há variações significativas entre os anos?\n");
        printFieldOccurrencesPerYear(tree, 11, -1);

        System.out.println("4. Qual o percentual de pessoas com deficiência que recebem bolsas de estudo?\n");
        printOccurrencesByField(tree, 15, -1);
        System.out.println("\n4.1. Esse número está crescendo, diminuindo ou estável?\n");
        printFieldOccurrencesPerYear(tree, 15, -1);

        System.out.println("5. Qual o percentual de pessoas com deficiência que recebem bolsas de estudo?\n");
        printOccurrencesByField(tree, 7, -1);
        System.out.println("\n5.1. Esse número está crescendo, diminuindo ou estável?\n");
        printFieldOccurrencesPerYear(tree, 7, -1);

        System.out.println("\n6. Houve aumento de bolsas para cursos EAD ao longo dos anos?\n");
        printFieldOccurrencesPerYear(tree, 9, -1);

        System.out.println("7. Quais cursos mais aparecem entre os bolsistas?\n");
        printOccurrencesByField(tree, 10, 10);

        System.out.println("\n8. Quais estados concentram a maior quantidade de bolsas?\n");
        printOccurrencesByField(tree, 14, -1);

        System.out.println("\n9. Quais municipios concentram a maior quantidade de bolsas?\n");
        printOccurrencesByField(tree, 3, 10);
    }

    public static double calculateMatchPercentage(BST tree, int fieldIndex, String value) {
        List<Registro> matchingRecords = tree.searchByField(fieldIndex, value);
        int total = tree.getTotalRecords();

        if (total == 0) return 0.0;

        return (matchingRecords.size() * 100.0) / total;
    }

    public static void printOccurrencesByField(BST tree, int fieldIndex, int limit) {
        Map<String, Integer> occurrences = tree.countOccurrencesByField(fieldIndex);
        int totalRecords = tree.getTotalRecords();

        List<Map.Entry<String, Integer>> entries = new ArrayList<>(occurrences.entrySet());
        entries.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));

        int count = 0;
        for (Map.Entry<String, Integer> entry : entries) {
            if (limit != -1 && count >= limit) break;
            String label = entry.getKey().isEmpty() ? "Indisponível" : entry.getKey();
            double percentage = (entry.getValue() * 100.0) / totalRecords;
            System.out.printf("%s: %d (%.4f%%)%n", label, entry.getValue(), percentage);
            count++;
        }
    }

    public static void printFieldOccurrencesPerYear(BST tree, int fieldIndex, int limit) {
        Map<Integer, Map<String, Integer>> countsPerYear = tree.countByFieldPerYear(fieldIndex);

        for (Map.Entry<Integer, Map<String, Integer>> yearEntry : countsPerYear.entrySet()) {
            int year = yearEntry.getKey();
            Map<String, Integer> fieldCounts = yearEntry.getValue();

            int total = fieldCounts.values().stream().mapToInt(Integer::intValue).sum();

            List<Map.Entry<String, Integer>> sortedEntries = new ArrayList<>(fieldCounts.entrySet());
            sortedEntries.sort((e1, e2) -> Integer.compare(e2.getValue(), e1.getValue()));

            System.out.println("Ano " + year + ":");

            int count = 0;
            for (Map.Entry<String, Integer> entry : sortedEntries) {
                if (limit != -1 && count >= limit) break;
                String label = entry.getKey().isEmpty() ? "Indisponível" : entry.getKey();
                int fieldCount = entry.getValue();
                double percentage = (fieldCount * 100.0) / total;
                System.out.printf("  %s: %d (%.4f%%)%n", label, fieldCount, percentage);
                count++;
            }

            System.out.println(); // Separação entre anos
        }
    }

}
