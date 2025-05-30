import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String filePath = "bq-results-20250529-041250-1748492082608.csv";
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("=====================================");
            System.out.println("       MENU PRINCIPAL - PROJETO      ");
            System.out.println("=====================================");
            System.out.println("1. Comparar desempenho entre BST e AVL");
            System.out.println("2. Analisar dados usando BST");
            System.out.println("0. Sair");
            System.out.print("\nEscolha uma opção: ");

            String choice = scanner.nextLine();
            System.out.println();

            switch (choice) {
                case "1":
                    TreeBenchmark.compareTrees(filePath);
                    break;

                case "2":
                    BST treeBST = new BST();
                    System.out.println("Carregando dados na BST para análise...\n");
                    CsvReader.loadCsvIntoTree(filePath, treeBST);
                    System.out.println("Dados carregados com sucesso.\n");
                    DataAnalysisBST.AnalyseData(treeBST);
                    break;

                case "0":
                    System.out.println("Encerrando o programa. Até mais!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opção inválida. Tente novamente.\n");
            }

            System.out.println("\nPressione Enter para continuar...");
            scanner.nextLine(); // pausa até o Enter
            System.out.println("\n");
        }
    }
}
