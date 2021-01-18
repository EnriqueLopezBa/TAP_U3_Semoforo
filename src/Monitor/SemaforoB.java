package Monitor;




public class SemaforoB extends Thread{
        
    private Lienzo puntero;
        private boolean rojo,amarillo,verde;
        
    public SemaforoB(Lienzo puntero){
        this.puntero = puntero;
        rojo = amarillo = verde = false;
    }

    public boolean isRojo() {
        return rojo;
    }

    public boolean isAmarillo() {
        return amarillo;
    }

    public boolean isVerde() {
        return verde;
    }
    
    

    
    @Override
    public void run() {
        while(true){
            try {
                verde = false;
                rojo = true;
                puntero.repaint();
                sleep(10000);
                rojo = false;
                amarillo = true;
                puntero.repaint();
                sleep(5000);
                amarillo = false;
                verde = true;
                puntero.repaint();
                sleep(10000);
                
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
    
    
    
}
