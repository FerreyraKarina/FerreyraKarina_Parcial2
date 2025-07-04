
package catalogo;
 import Interfaces.CSVSerializable;

import java.io.*;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import Interfaces.FuncionCSV;


   

public class CatalogoMusical<T extends CSVSerializable & Comparable<T>> {

    private List<T> lista =  new ArrayList<>();
    

    public void agregar(T item) {
        lista.add(item);
    }
    
    public void eliminarPorIndice(int indice) {
        if (indice < 0 || indice >= lista.size()) throw new IndexOutOfBoundsException("Índice fuera de rango");
        lista.remove(indice);
    }


    public T obtener(int indice) {
        if (indice < 0 || indice >= lista.size()) throw new IndexOutOfBoundsException("Índice fuera de rango");
        return lista.get(indice);
    }

   
    public List<T> filtrar(Predicate<? super T> criterio) {
        List<T> resultado = new ArrayList<>();
        for (T item : lista) {
            if (criterio.test(item)) resultado.add(item);
        }
        return resultado;
    }

    public void ordenar() {
        Collections.sort(lista);
    }

    public void ordenar(Comparator<? super T> comparator) {
        lista.sort(comparator);
    }

    public void paraCadaElemento(Consumer<? super T> accion) {
        for (T item : lista) {
            accion.accept(item);
        }
    }

   public void guardarEnArchivo(String ruta) throws IOException {
    File archivo = new File(ruta);
    File carpeta = archivo.getParentFile();

    if (carpeta != null && !carpeta.exists()) {
        if (carpeta.mkdirs()) {
            System.out.println("Carpeta creada: " + carpeta.getAbsolutePath());
        } else {
            System.out.println("No se pudo crear la carpeta: " + carpeta.getAbsolutePath());
        }
    }

    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
        oos.writeObject(lista);
    }
}


    @SuppressWarnings("unchecked")
    public void cargarDesdeArchivo(String ruta) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ruta))) {
            lista = (List<T>) ois.readObject();
        }
    }

    public void guardarEnCSV(String ruta) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ruta))) {
            for (T item : lista) {
                pw.println(item.toCSV());
            }
        }
    }

    public void cargarDesdeCSV(String ruta, FuncionCSV<T> fromCSVFunction) throws IOException {
        List<T> nuevaLista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                T obj = fromCSVFunction.fromCSV(linea);
                nuevaLista.add(obj);
            }
        }
        this.lista = nuevaLista;
    }

    
   
}
