
package modelo;

import Interfaces.CSVSerializable;
import java.io.Serializable;


public class Cancion implements CSVSerializable, Comparable<Cancion>, Serializable {
    private int id;
    private String titulo;
    private String artista;
    private GeneroMusical genero;

    public Cancion(int id, String titulo, String artista, GeneroMusical genero) {
        this.id = id;
        this.titulo = titulo;
        this.artista = artista;
        this.genero = genero;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getArtista() {
        return artista;
    }

    public GeneroMusical getGenero() {
        return genero;
    }

    @Override
    public int compareTo(Cancion c) {
        return Integer.compare(this.id, c.id);
    }

    @Override
    public String toString() {
        return String.format("Cancion{id=%d, titulo='%s', artista='%s', genero=%s}", id, titulo, artista, genero);
    }

    @Override
    public String toCSV() {
        return String.format("%d,%s,%s,%s", id, titulo, artista, genero);
    }

    public static Cancion fromCSV(String linea) {
        String[] partes = linea.split(",");
        if (partes.length != 4) throw new IllegalArgumentException("CSV inválido para Cancion: " + linea);
        int id = Integer.parseInt(partes[0].trim());
        String titulo = partes[1].trim();
        String artista = partes[2].trim();
        GeneroMusical genero = GeneroMusical.valueOf(partes[3].trim());
        return new Cancion(id, titulo, artista, genero);
    }
}