import java.util.*;

public class TreeBenchmark {

    public static void compareTrees() {
        String filePath = "bq-results-20250529-041250-1748492082608.csv";

        // Chaves específicas para demonstrar que a busca funciona corretamente
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

        // Gerar todas as chaves compostas uma única vez
        List<CompositeKey> allKeys = CsvReader.loadAllKeys(filePath);

        // === BST ===
        BST treeBST = new BST();

        long startBST = System.nanoTime();
        CsvReader.loadCsvIntoTree(filePath, treeBST);
        long endBST = System.nanoTime();
        long insertTimeBST = endBST - startBST;

        // Busca de demonstração
        for (CompositeKey key : demoKeys) {
            Node result = treeBST.searchByKey(key);
            if (result != null) {
                for (Registro r : result.getRegistros()) {
                    System.out.println(r);
                }
            }
        }

        // Busca de performance
        startBST = System.nanoTime();
        for (CompositeKey key : allKeys) {
            treeBST.searchByKey(key);
        }
        endBST = System.nanoTime();
        long searchTimeBST = endBST - startBST;

        startBST = System.nanoTime();
        treeBST.removeByYearRange(2010, 2018);
        endBST = System.nanoTime();
        long removeRangeTimeBST = endBST - startBST;

        System.out.println("=== BST ===");
        System.out.println("Tempo de inserção: " + insertTimeBST + " ns");
        System.out.println("Tempo de busca: " + searchTimeBST + " ns");
        System.out.println("Tempo de remoção (2010 - 2018): " + removeRangeTimeBST + " ns");

        treeBST = null;
        System.gc();

        // === AVL ===
        AVL treeAVL = new AVL();

        long startAVL = System.nanoTime();
        CsvReader.loadCsvIntoTree(filePath, treeAVL);
        long endAVL = System.nanoTime();
        long insertTimeAVL = endAVL - startAVL;

        // Busca de demonstração
        for (CompositeKey key : demoKeys) {
            Node result = treeAVL.searchByKey(key);
            if (result != null) {
                for (Registro r : result.getRegistros()) {
                    System.out.println(r);
                }
            }
        }

        // Busca de performance
        startAVL = System.nanoTime();
        for (CompositeKey key : allKeys) {
            treeAVL.searchByKey(key);
        }
        endAVL = System.nanoTime();
        long searchTimeAVL = endAVL - startAVL;

        startAVL = System.nanoTime();
        treeAVL.removeByYearRange(2010, 2018);
        endAVL = System.nanoTime();
        long removeRangeTimeAVL = endAVL - startAVL;

        System.out.println("\n=== AVL ===");
        System.out.println("Tempo de inserção: " + insertTimeAVL + " ns");
        System.out.println("Tempo de busca: " + searchTimeAVL + " ns");
        System.out.println("Tempo de remoção (2010 - 2018): " + removeRangeTimeAVL + " ns");
    }
}
