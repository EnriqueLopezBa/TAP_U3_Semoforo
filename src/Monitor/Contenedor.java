package Monitor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Contenedor {

    private boolean lleno = false;
    Lienzo puntero;
    int contA = -1, contB = -1;
    
    LinkedList<Auto> calleA = new LinkedList<>();
    LinkedList<Auto> calleB = new LinkedList<>();



    public Contenedor(Lienzo puntero) {
        this.puntero = puntero;
    }

    public synchronized void insertar() {
        while (lleno) {
            try {
                System.out.println("LLENO");
                wait();
            } catch (InterruptedException e) {
            }
        }
        Iterator<Auto> recorrerA = calleA.iterator();
        Iterator<Auto> recorrerB = calleB.iterator();
        if (new Random().nextBoolean()) {
            if (contA < 5) {        
              int posicion = (int) (Math.random() * 2);
                if(posicion == 0){                
                      calleA.add(new Auto(++contA, posicion, 0,puntero.mosizq-=50,260,false));
                }
                else{    
                    calleA.add(new Auto(++contA, posicion, 0,puntero.mosder+=50,260,false));
                }
                  
            }
        } else {
            if (contB < 5) {
            int posicion = (int) (Math.random() * 2);
            if(posicion == 0)
                calleB.add(new Auto(++contB, posicion, 1,400,puntero.mosabajo-=50,false));
            else
                calleB.add(new Auto(++contB, posicion, 1,400,puntero.mosarr+=50,false));
        }
        }
        puntero.repaint();
        if (contA + contB == 8) {
            lleno = true;
        }
        notify();
    }

 

    
    public synchronized void avanzar(int calle) {
        while (contA == -1 && contB == -1) {
            try {
                System.out.println("VACIO");
                wait();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }

        while(!puntero.semaforoA.isVerde() && !puntero.semaforoB.isVerde()){
            try {
                System.out.println("Esperando semaforos..");
                wait();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        
   
        if(calle == 0){
            List<Boolean> boleanes = new ArrayList<>();
            for (int i = 0; i < calleA.size(); i++) {
                boleanes.add(calleA.get(i).isSacar());
            }
            if(boleanes.contains(false)){
       
                for (int i = 0; i < calleA.size(); i++) {
                    if(calleA.get(i).isSacar() == false){
             
                        if(calleA.get(i).getPosicion() == 0)
                            puntero.mosizq +=50;
                        else
                            puntero.mosder -=50;
                        Auto a = calleA.get(i);
                        calleA.get(i).setSacar(true);
                        puntero.mover[Lienzo.contador] = new Thread(new Mover(puntero, a));
                        puntero.mover[Lienzo.contador].start();
                        Lienzo.contador++;
                    }
                    
                }
            }else{
                try {
                    wait();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Contenedor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }else{
            List<Boolean> boleanes = new ArrayList<>();
            for (int i = 0; i < calleB.size(); i++) {
                boleanes.add(calleB.get(i).isSacar());
            }
            if(boleanes.contains(false)){
                for (int i = 0; i < calleB.size(); i++) {
                    if(calleB.get(i).isSacar() == false){
                        if(calleB.get(i).getPosicion() == 0)
                            puntero.mosabajo +=50;
                        else
                            puntero.mosarr -=50;
                        Auto b = calleB.get(i);
                        b.setSacar(true);
                         puntero.mover[Lienzo.contador] = new Thread(new Mover(puntero, b));
                        puntero.mover[Lienzo.contador].start();
                        Lienzo.contador++;   
                    }
                }
            }else{
                try {
                    wait();
                } catch (InterruptedException e) {
                }
            }
               
        }
         notify();
    }

}
