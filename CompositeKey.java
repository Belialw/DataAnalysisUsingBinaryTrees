public class CompositeKey implements Comparable<CompositeKey> {
    private String cpf;
    private int ano;

    public CompositeKey(String censoredCpf, int ano) {
        this.cpf = censoredCpf.replaceAll("\\D", "");
        this.ano = ano;
    }

    @Override
    public int compareTo(CompositeKey other) {
        int cpfCompare = this.cpf.compareTo(other.cpf);
        if (cpfCompare != 0) return cpfCompare;
        return Integer.compare(this.ano, other.ano);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CompositeKey)) return false;
        CompositeKey other = (CompositeKey) obj;
        return this.cpf.equals(other.cpf) && this.ano == other.ano;
    }

    public String getKeyCpf() {
        return this.cpf;
    }

    public void setKeyCpf(String cpf) {
        this.cpf = cpf.replaceAll("\\D", "");
    }
}
