public class Registro {
    private CompositeKey key;
    private String cpf;
    private int idMunicipio;
    private String idMunicipioNome;
    private String dataNascimento;
    private String campus;
    private String turnoCurso;
    private String sexo;
    private String nomeMunicipioIes;
    private String modalidadeEnsino;
    private String curso;
    private String racaCor;
    private String tipoBolsa;
    private String siglaUf;
    private String siglaUfNome;
    private boolean beneficiarioDeficiente;
    private int idIes;
    private String idIesNome;
    private String idIesTipoInstituicao;

    public Registro(String cpf, int ano, int idMunicipio, String idMunicipioNome,
                    String dataNascimento, String campus, String turnoCurso, String sexo,
                    String nomeMunicipioIes, String modalidadeEnsino, String curso,
                    String racaCor, String tipoBolsa, String siglaUf, String siglaUfNome,
                    boolean beneficiarioDeficiente, int idIes, String idIesNome, String idIesTipoInstituicao) {

        this.cpf = cpf;
        this.key = new CompositeKey(cpf, ano);
        this.idMunicipio = idMunicipio;
        this.idMunicipioNome = idMunicipioNome;
        this.dataNascimento = dataNascimento;
        this.campus = campus;
        this.turnoCurso = turnoCurso;
        this.sexo = sexo;
        this.nomeMunicipioIes = nomeMunicipioIes;
        this.modalidadeEnsino = modalidadeEnsino;
        this.curso = curso;
        this.racaCor = racaCor;
        this.tipoBolsa = tipoBolsa;
        this.siglaUf = siglaUf;
        this.siglaUfNome = siglaUfNome;
        this.beneficiarioDeficiente = beneficiarioDeficiente;
        this.idIes = idIes;
        this.idIesNome = idIesNome;
        this.idIesTipoInstituicao = idIesTipoInstituicao;
    }

    public CompositeKey getKey() {
        return key;
    }

    public void setKey(CompositeKey key) {
        this.key = key;
    }

    public String getcpf() {
        return cpf;
    }

    public void setcpf(String cpf) {
        this.cpf = cpf;
        this.key.setKeyCpf(cpf);
    }

    public int getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(int idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public String getIdMunicipioNome() {
        return idMunicipioNome;
    }

    public void setIdMunicipioNome(String idMunicipioNome) {
        this.idMunicipioNome = idMunicipioNome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public String getTurnoCurso() {
        return turnoCurso;
    }

    public void setTurnoCurso(String turnoCurso) {
        this.turnoCurso = turnoCurso;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getNomeMunicipioIes() {
        return nomeMunicipioIes;
    }

    public void setNomeMunicipioIes(String nomeMunicipioIes) {
        this.nomeMunicipioIes = nomeMunicipioIes;
    }

    public String getModalidadeEnsino() {
        return modalidadeEnsino;
    }

    public void setModalidadeEnsino(String modalidadeEnsino) {
        this.modalidadeEnsino = modalidadeEnsino;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getRacaCor() {
        return racaCor;
    }

    public void setRacaCor(String racaCor) {
        this.racaCor = racaCor;
    }

    public String getTipoBolsa() {
        return tipoBolsa;
    }

    public void setTipoBolsa(String tipoBolsa) {
        this.tipoBolsa = tipoBolsa;
    }

    public String getSiglaUf() {
        return siglaUf;
    }

    public void setSiglaUf(String siglaUf) {
        this.siglaUf = siglaUf;
    }

    public String getSiglaUfNome() {
        return siglaUfNome;
    }

    public void setSiglaUfNome(String siglaUfNome) {
        this.siglaUfNome = siglaUfNome;
    }

    public boolean isBeneficiarioDeficiente() {
        return beneficiarioDeficiente;
    }

    public void setBeneficiarioDeficiente(boolean beneficiarioDeficiente) {
        this.beneficiarioDeficiente = beneficiarioDeficiente;
    }

    public int getIdIes() {
        return idIes;
    }

    public void setIdIes(int idIes) {
        this.idIes = idIes;
    }

    public String getIdIesNome() {
        return idIesNome;
    }

    public void setIdIesNome(String idIesNome) {
        this.idIesNome = idIesNome;
    }

    public String getIdIesTipoInstituicao() {
        return idIesTipoInstituicao;
    }

    public void setIdIesTipoInstituicao(String idIesTipoInstituicao) {
        this.idIesTipoInstituicao = idIesTipoInstituicao;
    }
}
