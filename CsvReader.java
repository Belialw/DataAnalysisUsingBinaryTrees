import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {
    
    public void loadCsvIntoTree(String filePath, BST tree) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue; // Pula o cabeçalho
                }

                // Divide os campos por vírgula
                String[] fields = parseCsvLine(line);

                Registro registro = criarRegistro(fields);
                tree.insert(registro);
            }

        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }

    private Registro criarRegistro(String[] campos) {
        try {
            String cpf = parseStringSafe(campos[0]);
            int ano = parseIntSafe(campos[1]);
            int idMunicipio = parseIntSafe(campos[2]);
            String idMunicipioNome = parseStringSafe(campos[3]);
            String dataNascimento = parseStringSafe(campos[4]);
            String campus = parseStringSafe(campos[5]);
            String turnoCurso = parseStringSafe(campos[6]);
            String sexo = parseStringSafe(campos[7]);
            String nomeMunicipioIes = parseStringSafe(campos[8]);
            String modalidadeEnsino = parseStringSafe(campos[9]);
            String curso = parseStringSafe(campos[10]);
            String racaCor = parseStringSafe(campos[11]);
            String tipoBolsa = parseStringSafe(campos[12]);
            String siglaUf = parseStringSafe(campos[13]);
            String siglaUfNome = parseStringSafe(campos[14]);
            boolean beneficiarioDeficiente = "1".equals(campos[15].trim());
            int idIes = parseIntSafe(campos[16]);
            String idIesNome = parseStringSafe(campos[17]);
            String idIesTipoInstituicao = parseStringSafe(campos[18]);

            return new Registro(cpf, ano, idMunicipio, idMunicipioNome,
                    dataNascimento, campus, turnoCurso, sexo,
                    nomeMunicipioIes, modalidadeEnsino, curso,
                    racaCor, tipoBolsa, siglaUf, siglaUfNome,
                    beneficiarioDeficiente, idIes, idIesNome, idIesTipoInstituicao);

        } catch (Exception e) {
            System.err.println("Erro ao criar registro na linha:");
            for (int i = 0; i < campos.length; i++) {
                System.err.println("  Campo[" + i + "]: " + campos[i]);
            }
            System.err.println("Mensagem: " + e.getMessage());
            return null;
        }
    }

    private int parseIntSafe(String s) {
        if (s == null || s.trim().isEmpty() || s.equalsIgnoreCase("Não informado")) {
            return 0;
        }
        return Integer.parseInt(s.trim());
    }

    private String parseStringSafe(String s) {
        if (s == null || s.trim().isEmpty()) {
            return "";
        }
        return s.trim();
    }

    private String[] parseCsvLine(String line) {
        List<String> fields = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        boolean inQuotes = false;

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);

            if (c == '"') {
                inQuotes = !inQuotes; // alterna entre dentro e fora de aspas
            } else if (c == ',' && !inQuotes) {
                fields.add(current.toString().trim());
                current.setLength(0); // limpa o buffer
            } else {
                current.append(c);
            }
        }

        fields.add(current.toString().trim()); // adiciona o último campo
        return fields.toArray(new String[0]);
    }

}
