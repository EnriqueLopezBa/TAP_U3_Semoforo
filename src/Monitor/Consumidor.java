package Monitor;

/**
 *
 * @author Enrique
 */
public class Consumidor extends Thread{
    
    private Contenedor contenedor;
    private SemaforoA semaforoA;
    private SemaforoB semaforoB;

    public Consumidor(Contenedor c,SemaforoA a,SemaforoB b){
        this.contenedor = c;
        this.semaforoA = a;
        this.semaforoB = b;
    }

    @Override
    public void run() {
        while(true){
            try {
                
            while(semaforoA.isVerde() && !contenedor.calleA.isEmpty()){
               
              
                for (int i = 0; i < contenedor.calleA.size(); i++) {
                    if(contenedor.calleA.get(i).isSacar() == false)
                         contenedor.avanzar(0);
                 sleep(500);
                }
              
               sleep(500);
               
            }
            while(semaforoB.isVerde() && !contenedor.calleB.isEmpty()){
                try {
                    for (int i = 0; i < contenedor.calleB.size(); i++) {
                    if(contenedor.calleB.get(i).isSacar() == false)
                     contenedor.avanzar(1);
                    sleep(500);
                }
                sleep(500);
                } catch (NullPointerException e) {
                }
                
            }
                sleep(700);
                
            } catch (InterruptedException | NullPointerException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
    
}
