package Clases;

public class Motor {
    private String tipo;
    private int cilindros;
    private double potencia;


    public Motor(String tipo, int cilindros, double potencia) {
        this.tipo = tipo;
        this.cilindros = cilindros;
        this.potencia = potencia;
    }

    public Motor() {
    }

    @Override
    public String toString() {
        return "🔧 Motor\n" +
                "→ Tipo: " + tipo + "\n" +
                "→ Cilindros: " + cilindros + "\n" +
                "→ Potencia: " + potencia + " HP";
    }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public int getCilindros() { return cilindros; }
    public void setCilindros(int cilindros) { this.cilindros = cilindros; }

    public double getPotencia() { return potencia; }
    public void setPotencia(double potencia) { this.potencia = potencia; }
    }

