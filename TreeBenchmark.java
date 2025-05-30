import java.util.*;

public class TreeBenchmark {

    public static void compareTrees(String filePath) {

        CompositeKey[] demoKeys = {
            new CompositeKey("***015295**", 2006),
            new CompositeKey("089.***.***-40", 2020),
            new CompositeKey("***562645**", 2006),
            new CompositeKey("***42285354**", 2017),
            new CompositeKey("***982302**", 2008),
            new CompositeKey("***012672**", 2012),
            new CompositeKey("***964633**", 2011),
            new CompositeKey("***317371**", 2013),
            new CompositeKey("082.***.***-05", 2020),
            new CompositeKey("***17986500**", 2018),
            new CompositeKey("***945601**", 2013),
            new CompositeKey("***811465**", 2014),
            new CompositeKey("***222454**", 2009),
            new CompositeKey("***689455**", 2015),
            new CompositeKey("***791181**", 2016),
            new CompositeKey("***25911380**", 2017),
            new CompositeKey("***457856**", 2005),
            new CompositeKey("***13533588**", 2018),
            new CompositeKey("***19159407**", 2019),
            new CompositeKey("087.***.***-01", 2020),
            new CompositeKey("***786803**", 2010)
        };

        System.out.println("\nIniciando leitura do arquivo para gerar todas as chaves...");
        List<CompositeKey> allKeys = CsvReader.loadAllKeys(filePath);
        System.out.println("Chaves carregadas com sucesso. Total de chaves: " + allKeys.size() + "\n");

        // === BST ===
        System.out.println("=== BST ===\n");
        System.out.println("Iniciando leitura e inserção dos dados na árvore (pode demorar um pouco)...\n");
        BST treeBST = new BST();
        long start = System.nanoTime();
        CsvReader.loadCsvIntoTree(filePath, treeBST);
        long end = System.nanoTime();
        long insertTimeBST = end - start;
        System.out.println("Inserção concluída. Tempo: " + insertTimeBST + " ns\n");

        System.out.println("Iniciando busca de demonstração (21 chaves)...\n");
        for (CompositeKey key : demoKeys) {
            Node result = treeBST.searchByKey(key);
            if (result != null) {
                for (Registro r : result.getRegistros()) {
                    System.out.println(r);
                }
            }
        }
        System.out.println("\nDemonstração concluída.\n");

        System.out.println("Iniciando busca de performance por todas as chaves...\n");
        start = System.nanoTime();
        for (CompositeKey key : allKeys) {
            treeBST.searchByKey(key);
        }
        end = System.nanoTime();
        long searchTimeBST = end - start;
        System.out.println("Busca concluída. Tempo: " + searchTimeBST + " ns\n");

        System.out.println("Iniciando remoção por intervalo de anos (2010 - 2018)...\n");
        start = System.nanoTime();
        treeBST.removeByYearRange(2010, 2018);
        end = System.nanoTime();
        long removeRangeTimeBST = end - start;
        System.out.println("Remoção concluída. Tempo: " + removeRangeTimeBST + " ns\n");

        System.out.println("=== Resultados BST ===");
        System.out.println("Tempo de inserção: " + insertTimeBST + " ns");
        System.out.println("Tempo de busca: " + searchTimeBST + " ns");
        System.out.println("Tempo de remoção (2010 - 2018): " + removeRangeTimeBST + " ns\n");

        treeBST = null;
        System.gc();

        // === AVL ===
        System.out.println("=== AVL ===\n");
        System.out.println("Iniciando leitura e inserção dos dados na árvore (pode demorar um pouco)...\n");
        AVL treeAVL = new AVL();
        start = System.nanoTime();
        CsvReader.loadCsvIntoTree(filePath, treeAVL);
        end = System.nanoTime();
        long insertTimeAVL = end - start;
        System.out.println("Inserção concluída. Tempo: " + insertTimeAVL + " ns\n");

        System.out.println("Iniciando busca de demonstração (21 chaves)...\n");
        for (CompositeKey key : demoKeys) {
            Node result = treeAVL.searchByKey(key);
            if (result != null) {
                for (Registro r : result.getRegistros()) {
                    System.out.println(r);
                }
            }
        }
        System.out.println("\nDemonstração concluída.\n");

        System.out.println("Iniciando busca de performance por todas as chaves...\n");
        start = System.nanoTime();
        for (CompositeKey key : allKeys) {
            treeAVL.searchByKey(key);
        }
        end = System.nanoTime();
        long searchTimeAVL = end - start;
        System.out.println("Busca concluída. Tempo: " + searchTimeAVL + " ns\n");

        System.out.println("Iniciando remoção por intervalo de anos (2010 - 2018)...\n");
        start = System.nanoTime();
        treeAVL.removeByYearRange(2010, 2018);
        end = System.nanoTime();
        long removeRangeTimeAVL = end - start;
        System.out.println("Remoção concluída. Tempo: " + removeRangeTimeAVL + " ns\n");

        System.out.println("=== Resultados AVL ===");
        System.out.println("Tempo de inserção: " + insertTimeAVL + " ns");
        System.out.println("Tempo de busca: " + searchTimeAVL + " ns");
        System.out.println("Tempo de remoção (2010 - 2018): " + removeRangeTimeAVL + " ns\n");

        treeAVL = null;
        System.gc();
    }
}
