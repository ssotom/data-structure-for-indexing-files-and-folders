/**
 * @autho Santiago Soto Marulanda 
 * @version 27/10/2017
 */
public class Archivo implements Comparable<Archivo>
{
    //atributos de clase
    private String nombre;
    private String direccion;
    
    /**
     * Este es el constructor por defecto de Archivo
     * @param nombre: es el nombre del archivo
     */
    public Archivo(String nombre)
    {
        this.nombre = nombre;
    }
    
    /**
     * Este es el constructor de Archivo teniendo conocimiento 
     * de la dirección del archivo
     * @param nombre: es el nombre del archivo
     * @param dirección: es la dirección del archivo
     */
    public Archivo(String nombre, String direccion)
    {
        this.nombre = nombre;
        this.direccion = direccion;
    }
    
    /**
     * Este método muestra el nombre del archivo
     * @return el nombre del archivo
     */
    public String getNombre()
    {
        return nombre;
    }
    
    /**
     * Este metodo muestra la dirección que posee el archivo
     * @return la dirección del archivo
     */
    public String getDireccion()
    {
        return direccion;
    }
    
    /**
     * Este metodo se encarga de comparar dos archivos  
     * lexicográficamente respecto a su nombre
     * @param otroArchivo: es uno de los archivos que se comparará
     * @see compareToIgnoreCase()
     * @return cuál de los dos archivos es mayor o 
     */
    @Override
    public int compareTo ( Archivo otroArchivo)
    {
        return nombre.compareToIgnoreCase(otroArchivo.getNombre());
    }
    
    /**
     * Este metodo convierte los atributos 
     * de Archivo una cade de String
     * @return la cade de String
     */
    @Override
    public String toString ()
    {
        return "["+direccion+"] "+nombre;
    }
}