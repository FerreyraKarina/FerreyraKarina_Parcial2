
package ferreyrakarina_parcial2;

import catalogo.CatalogoMusical;
import config.Rutas;
import modelo.Cancion;
import modelo.GeneroMusical;

import java.io.IOException;


public class FerreyraKarina_Parcial2 {

    
    
        public static void main(String[] args) {
       try {
            CatalogoMusical<Cancion> catalogo = new CatalogoMusical<>();
            catalogo.agregar(new Cancion(1, "Bohemian Rhapsody", "Queen", GeneroMusical.ROCK));
            catalogo.agregar(new Cancion(2, "Billie Jean", "Michael Jackson", GeneroMusical.POP));
            catalogo.agregar(new Cancion(3, "Shape of You", "Ed Sheeran", GeneroMusical.POP));
            catalogo.agregar(new Cancion(4, "Take Five", "Dave Brubeck", GeneroMusical.JAZZ));
            catalogo.agregar(new Cancion(5, "Canon in D", "Pachelbel", GeneroMusical.CLASICA));

            System.out.println("Catalogo de canciones:");
            catalogo.paraCadaElemento(c -> System.out.println(c));

            System.out.println("\nCanciones de genero JAZZ:");
            catalogo.filtrar(c -> c.getGenero() == GeneroMusical.JAZZ)
                   .forEach(c -> System.out.println(c));

            System.out.println("\nCanciones cuyo titulo contiene 'five':");
            catalogo.filtrar(c -> c.getTitulo().toLowerCase().contains("five"))
                   .forEach(c -> System.out.println(c));

            System.out.println("\nCanciones ordenadas por ID:");
            catalogo.ordenar();
            catalogo.paraCadaElemento(c -> System.out.println(c));

            System.out.println("\nCanciones ordenadas por artista:");
            catalogo.ordenar((c1, c2) -> c1.getArtista().compareToIgnoreCase(c2.getArtista()));
            catalogo.paraCadaElemento(c -> System.out.println(c));

            
            catalogo.guardarEnArchivo(Rutas.RUTA_BINARIO);

            CatalogoMusical<Cancion> cargado = new CatalogoMusical<>();
            cargado.cargarDesdeArchivo(Rutas.RUTA_BINARIO);

            System.out.println("\nCanciones cargadas desde binario:");
            cargado.paraCadaElemento(c -> System.out.println(c));

            
            catalogo.guardarEnCSV(Rutas.RUTA_CSV);

            cargado.cargarDesdeCSV(Rutas.RUTA_CSV, Cancion::fromCSV);

            System.out.println("\nCanciones cargadas desde CSV:");
            cargado.paraCadaElemento(c -> System.out.println(c));

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
        }
    

    
