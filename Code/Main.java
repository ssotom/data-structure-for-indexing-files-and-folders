import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author: Santiago Soto Marulanda
 * @version 27/10/2017
 */
public class Main 
{
  //atributos de clase
  protected static AVLTree<Archivo> arbol;
  protected static String aEntrada = "juegos.txt";
  
  /**
   * Este metodo se encarga de realizar la lectura de un fiche txt
   * @see insert()
   */
  public static void dataSets() throws FileNotFoundException, IOException
   {
        String cadena;
        FileReader f = new FileReader(aEntrada);
        BufferedReader b = new BufferedReader(f);
        String dir = b.readLine();
        dir = dir.substring(0,dir.length()-1);
        arbol = new AVLTree<>();
        arbol.insert(new Archivo(dir,""));
        while(((cadena = b.readLine())!=null) && !(cadena.equals(""))) 
        {
            int contT = cadena.lastIndexOf("[")/4;
            String direccion = "";
            String[] temp = dir.split("/");
            for(int i=0;i<temp.length;i++)
            {
                if(i<contT){direccion+=temp[i]+'/';}
            }
            dir = direccion.substring(0,direccion.length()-1);
            int index = cadena.indexOf("]")+3;
            String subCadena = cadena.substring(index);
            if(subCadena.indexOf("/")>=0)
            {
                Archivo ar = new Archivo(subCadena,dir);
                arbol.insert(ar);
            }else
            {
                Archivo ar = new Archivo(subCadena,dir);
                arbol.insert(ar);
                dir = dir+'/'+subCadena;
            }
        }
        b.close();
        f.close();
  } 
     
  /**
   * Este método se encarga tomar el tiempo creacion de la estructura
   * @see dataSets()
  */
  public static void tiempo()throws FileNotFoundException, IOException
  {     
        AVLTree<Archivo> arbol = new AVLTree<>(); 
        for(int i=0;i<100;i++)
        {
            long startTime = System.currentTimeMillis();
            dataSets();
            long estimatedTime = System.currentTimeMillis() - startTime;
            System.out.println(estimatedTime);
        }
  }
  
  /**
   * Este metodo es el menu de entrada para el usuario.Éste 
   * se encarga de guiar al usuario en la busqueda de archivos
   * dentro del directorio y de mostrarle el archivo buscado
   * @see search()
   * @see dataSets()
  */
  public static void menu()
    {
       boolean salir = false;
       while(!salir)
       {
           Scanner in = new Scanner(System.in);
           int opcion = 0;
           System.out.print("[1]. Buscar");
           System.out.print(" [2]. Configurar");
           System.out.println(" [3]. Salir");
            
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
                   System.out.println("Escriba el nombre de archivo de entrada");
                   aEntrada = in.next();
                   try{
                       dataSets();
                   }catch(Exception e){
                       System.out.println("Archivo no encontrado");
                   } 
                   break;
                case 3:
                   salir=true;
                   break;
                default:
                   System.out.println("Opcion invalida");
           }    
       }
    }
  
  /**
   * Este metodo se encarga de ejecutar el programa
   */
  public static void main(String[] args) throws FileNotFoundException, IOException
  {
       dataSets();
       menu();
  }

}