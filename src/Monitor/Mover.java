package Monitor;

import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 *
 * @author Enrique
 */
public class Mover extends Thread{
 
    private Lienzo lienzo;
    private Auto auto;
    boolean matar = false;
    
    public Mover(Lienzo lienzo,Auto auto){
        this.lienzo = lienzo;
        this.auto = auto;
    }

    @Override
    public void run() {
        while(matar == false){
            try {
          
                if(auto.getCalle() == 0){
                           
                    boolean espera = false;
                    do{
                        for (int i = 0; i < lienzo.contenedor.calleB.size(); i++){
                             if(lienzo.contenedor.calleB.get(i).getX() > 400 && lienzo.contenedor.calleB.get(i).getX() < 500)
                     if(lienzo.contenedor.calleB.get(i).getY() > 260 && lienzo.contenedor.calleB.get(i).getY() < 340){
                           espera = true;
                                Thread.sleep(100);
                           break;
                     }else{
                         espera = false;
                     }
                       
                        }
                        
                    }while(espera == true);
               
                        if(lienzo.semaforoA.isVerde()){
                        

                
                    if(auto.getPosicion() == 0){
                        while(auto.getX() < 600){
                                         auto.setX(auto.getX()+3);
                                         lienzo.repaint();
                                         sleep(90);                                  
                        }
                           }else{
                        while(auto.getX() > 200){
                                       auto.setX(auto.getX()-3);
                                         lienzo.repaint();
                                         sleep(90);
                           }
                    }
                     Lienzo.contador--;
                     lienzo.contenedor.contA--;
                           lienzo.contenedor.calleA.remove(auto);
                        }
                }else{
                            boolean espera = false;
                    do{
                        for (int i = 0; i < lienzo.contenedor.calleA.size(); i++){
                             if(lienzo.contenedor.calleA.get(i).getX() > 400 && lienzo.contenedor.calleA.get(i).getX() < 500)
                     if(lienzo.contenedor.calleA.get(i).getY() > 260 && lienzo.contenedor.calleA.get(i).getY() < 340){
                           espera = true;
                           break;
                     }else{
                         espera = false;
                     }
                       
                        }
                             Thread.sleep(100);
                    }while(espera == true);
                    
                    if(lienzo.semaforoB.isVerde()){
                     if(auto.getPosicion() == 0){
                         while(auto.getY() < 400){
                                        auto.setY(auto.getY() + 3);
                                        lienzo.repaint();
                                        sleep(90);
                         }
                           }else{
                         while(auto.getY() > 200){
                                        auto.setY(auto.getY() - 3);
                                        lienzo.repaint();
                                        sleep(90);

                         }
                           }
                      Lienzo.contador--;
                      lienzo.contenedor.contB--;
                           lienzo.contenedor.calleB.remove(auto);
                    }
                }
               
               
                sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(Mover.class.getName()).log(Level.SEVERE, null, ex);
            }
             matar = true;
        }
    }
    
    
    
}
