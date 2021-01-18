package Monitor;


/**
 *
 * @author Enrique
 */
public class Productor extends Thread{

    private Contenedor  contenedor;
    
    public Productor(Contenedor c){
        this.contenedor = c;
    }
    
    @Override
    public void run() {
       while(true){
           try {
      
               contenedor.insertar();
//               System.out.println("Se inserta un auto: ");
               
               sleep((int)(Math.random()*(3000 - 2000)+2000));
           } catch (InterruptedException e) {
           }
       }
    }
    
    
    
}
