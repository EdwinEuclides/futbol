package com.futbol.domain;

public class jugador extends miembro {

    private int altura;
    private int posicion;
    private int cantGoles;
    private boolean esCapitan;
    private int nroCamiseta;
    
    public jugador(String nombre, String apellido, int altura, int posicion, int cantGoles, boolean esCapitan,
            int nroCamiseta) {
        super(nombre, apellido);
        this.altura = altura;
        this.posicion = posicion;
        this.cantGoles = cantGoles;
        this.esCapitan = esCapitan;
        this.nroCamiseta = nroCamiseta;
    }

    public jugador(String nombre, String apellido, equipo equipo, int altura, int posicion, int cantGoles,
            boolean esCapitan, int nroCamiseta) {
        super(nombre, apellido, equipo);
        this.altura = altura;
        this.posicion = posicion;
        this.cantGoles = cantGoles;
        this.esCapitan = esCapitan;
        this.nroCamiseta = nroCamiseta;
    }


    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public int getCantGoles() {
        return cantGoles;
    }

    public void setCantGoles(int cantGoles) {
        this.cantGoles = cantGoles;
    }

    public boolean isEsCapitan() {
        return esCapitan;
    }

    public void setEsCapitan(boolean esCapitan) {
        this.esCapitan = esCapitan;
    }

    public int getNroCamiseta() {
        return nroCamiseta;
    }

    public void setNroCamiseta(int nroCamiseta) {
        this.nroCamiseta = nroCamiseta;
    }

    @Override
    public String toString() {
        return "jugador [id=" + this.getId() + ", altura=" + altura + ", posicion=" + posicion + ", cantGoles=" + cantGoles
                + ", esCapitan=" + esCapitan + ", nroCamiseta=" + nroCamiseta + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + super.hashCode();
        result = prime * result + altura;
        result = prime * result + posicion;
        result = prime * result + cantGoles;
        result = prime * result + (esCapitan ? 1231 : 1237);
        result = prime * result + nroCamiseta;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        jugador other = (jugador) obj;
        if(this.getId() != other.getId())
            return false;
        if (altura != other.altura)
            return false;
        if (posicion != other.posicion)
            return false;
        if (cantGoles != other.cantGoles)
            return false;
        if (esCapitan != other.esCapitan)
            return false;
        if (nroCamiseta != other.nroCamiseta)
            return false;
        return true;
    }

   
   
    
    

    
}
