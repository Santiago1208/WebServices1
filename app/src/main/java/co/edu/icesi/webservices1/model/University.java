package co.edu.icesi.webservices1.model;

public class University {

    private boolean abet;

    private String nombre;

    private int ranking;

    private String ubicacion;

    public University() {
        // Required empty constructor
    }

    public boolean isAbet() {
        return abet;
    }

    public void setAbet(boolean abet) {
        this.abet = abet;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
}
