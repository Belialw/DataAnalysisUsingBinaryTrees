public class TreeBenchmark {

    public static void compareTrees() {
        BST treeBST = new BST();

        // BST Insertion:
        long startBST = System.nanoTime();
        CsvReader.loadCsvIntoTree("bq-results-20250529-041250-1748492082608.csv", treeBST);
        long endBST = System.nanoTime();
        long insertTimeBST = endBST - startBST;

        // BST Removal:
        startBST = System.nanoTime();
        treeBST.removeByYearRange(2010, 2018);
        endBST = System.nanoTime();
        long removeRangeTimeBST = endBST - startBST;

        System.out.println("Tempo de inserção na BST: " + insertTimeBST + "ns");
        System.out.println("Tempo de remoção na BST (2010 - 2018): " + removeRangeTimeBST + "ns");

        treeBST = null;
        System.gc();

        // AVL Tree
        AVL treeAVL = new AVL();

        // AVL Insertion:
        long startAVL = System.nanoTime();
        CsvReader.loadCsvIntoTree("bq-results-20250529-041250-1748492082608.csv", treeAVL);
        long endAVL = System.nanoTime();
        long insertTimeAVL = endAVL - startAVL;

        // AVL Removal:
        startAVL = System.nanoTime();
        treeAVL.removeByYearRange(2010, 2018);
        endAVL = System.nanoTime();
        long removeRangeTimeAVL = endAVL - startAVL;

        System.out.println("Tempo de inserção na AVL: " + insertTimeAVL + "ns");
        System.out.println("Tempo de remoção na AVL (2010 - 2018): " + removeRangeTimeAVL + "ns");
    }

}
