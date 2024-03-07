package models;

public class Pessoa {
	
    private String nome;
    private double salarioBruto;
    private int numeroDependentes;
    private boolean vr;
    private boolean va;
    private boolean vt;
    private boolean planoSaude;

    public Pessoa(String nome, double salarioBruto, int numeroDependentes, boolean vr, boolean va, boolean vt, boolean planoSaude) {
        this.nome = nome;
        this.salarioBruto = salarioBruto;
        this.numeroDependentes = numeroDependentes;
        this.vr = vr;
        this.va = va;
        this.vt = vt;
        this.planoSaude = planoSaude;
    }

    public String getNome() {
        return nome;
    }

    public double getSalarioBruto() {
        return salarioBruto;
    }

    public int getNumeroDependentes() {
        return numeroDependentes;
    }

    public boolean getVr() {
        return vr;
    }

    public boolean getVa() {
        return va;
    }

    public boolean getVt() {
        return vt;
    }

    public boolean getPlanoDeSaude() {
        return planoSaude;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSalarioBruto(double salarioBruto) {
        this.salarioBruto = salarioBruto;
    }

    public void setNumeroDependentes(int numeroDependentes) {
        this.numeroDependentes = numeroDependentes;
    }

    public void setVr(boolean vr) {
        this.vr = vr;
    }

    public void setVa(boolean va) {
        this.va = va;
    }

    public void setVt(boolean vt) {
        this.vt = vt;
    }

    public void setPlanoDeSaude(boolean planoSaude) {
        this.planoSaude = planoSaude;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + ", Salário Bruto: " + salarioBruto + ", Número de Dependentes: " + numeroDependentes +
                ", VR: " + (vr ? "Sim" : "Não") + ", VA: " + (va ? "Sim" : "Não") + ", VT: " + (vt ? "Sim" : "Não") +
                ", Plano de Saúde: " + (planoSaude ? "Sim" : "Não");
    }
}