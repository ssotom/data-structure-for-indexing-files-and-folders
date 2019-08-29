import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;

/**
 * @author Santiago Soto Marulanda
 * @version 27/10/2017
 */
public class Main2 
{
  //atributos de clase
  protected static AVLTree<Archivo> arbol = new AVLTree<>();;
  protected static String direccion;
  /**
   * Este método se encarga de hacer la lectura del disco duro
   * @param argFile: es el archdirectorio que se va a leer del disco
   * @see insert()
   */
  public static void readDisk(File argFile)throws FileNotFoundException
  {
      if(!(argFile.exists()))
         throw new FileNotFoundException();
      
      File[] lista = argFile.listFiles();
      if (lista != null) 
        {
            for (File elemento : lista)
            {
                if (elemento.isDirectory())
                {
                    Archivo ar = new Archivo(elemento.getName(),elemento.getParent());
                    arbol.insert(ar);
                    readDisk(elemento);
                }
                else
                {
                    Archivo ar = new Archivo(elemento.getName(),elemento.getParent());
                    arbol.insert(ar);
                }
            }
        }
  }
  
  /**
   * Este método es el menu de entrada para el usuario.Esté se encarga de
   * guiar al usuario para realizar la búqueda de archivos y de mostrarle 
   * el archivo buscado.
   * @see search()
   * 
   */
  public static void menu()
  {
       boolean salir = false;
       while(!salir)
       {
           Scanner in = new Scanner(System.in);
           int opcion = 0;
           System.out.print("[1]. Buscar");
           System.out.println(" [2]. Salir");
            
           System.out.print("Escriba una opcion: ");
           try{
               opcion = in.nextInt();
           }catch(Exception e){} 

           switch(opcion)
           {
               case 1:
                   System.out.println("Escriba el nombre del archivo que desea buscar:");
                   in = new Scanner(System.in);
                   String nombreA = in.nextLine();
                   arbol.search(new Archivo((nombreA).trim()));
                   System.out.println();
                   break;
                case 2:
                   salir=true;
                   break;
                default:
                   System.out.println("Opcion invalida");
           }    
       }
  }
  
  /**
   * Este metodo se encarga de realizar la ejecución de la lectura
   * de directorios
   * @see readDisk()
   */
  public static void main(String[] args)
  {
      System.out.println("Ingrese directorio en el que buscara:");
      while(true)
      {
          Scanner in = new Scanner(System.in);
          direccion = in.next();
          try{
              readDisk(new File(direccion));
              break;
          }catch(Exception e){
                System.out.println("Direcotrio no encontrado");
          }
      }
      menu();
  }

}