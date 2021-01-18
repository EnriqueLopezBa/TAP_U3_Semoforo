package Monitor;


import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import static javax.swing.JOptionPane.showMessageDialog;

public class Lienzo extends Canvas{
 
    
    private vtnPrincipal puntero;
  
    SemaforoA semaforoA = new SemaforoA(this);
    SemaforoB semaforoB = new SemaforoB(this);
    Contenedor contenedor = new Contenedor(this);
    Productor productor = new Productor(contenedor);
    Consumidor consumidor = new Consumidor(contenedor,semaforoA,semaforoB);
    Lienzo lienzo = this;
        Thread[] mover = new Thread[10];
    static int contador = 0;

            int mosizq = 400;
            int mosder = 450;
            int mosarr = 290;
            int mosabajo = 260;
  
            
            

    
    public Lienzo(vtnPrincipal puntero){
        this.puntero = puntero;
        setBounds(0, 0, 800, 700);
        setBackground(Color.black);
        semaforoA.start();
        semaforoB.start();
        productor.start();
        consumidor.start();

    }
        
    

    @Override
    public void paint(Graphics g) {
       super.paint(g);
       g.setColor(Color.white);
       //Semaforo 1
       g.drawRect(20, 20, 80, 160);
       g.setFont(new Font("TimesRoman", Font.PLAIN, 24)); 
       g.drawString("A", 25, 40);
    
       g.drawOval(40, 30, 40, 40);
       g.drawOval(40, 80, 40, 40);
       g.drawOval(40, 130, 40, 40);
       //Semaforo 2
       g.drawRect(670, 20, 80, 160);
       g.drawOval(690, 30, 40, 40);
       g.drawOval(690, 80, 40, 40);
       g.drawOval(690, 130, 40, 40);
       g.drawString("B", 675, 40);
       //Calle horizontal
       g.drawLine(220, 260, 400, 260);
       g.drawLine(220, 340, 400, 340);
       g.drawLine(500, 260, 750, 260);
       g.drawLine(500, 340, 750, 340);
       g.drawString("A", 240, 290);
       //Calle vertical
        g.drawLine(400, 70, 400, 260);
        g.drawLine(500, 70, 500, 260);
        g.drawLine(400, 340, 400, 560);
        g.drawLine(500, 340, 500, 560);
        g.drawString("B", 430, 100);
        
        //Area cruce
        g.drawRect(400, 260, 100, 80);
        
       
        //Semaforo 1
       if(semaforoA.isRojo()){
           g.setColor(Color.red);
           g.fillOval(40, 30, 40, 40);
       }
       else if(semaforoA.isAmarillo()){
           g.setColor(Color.yellow);
           g.fillOval(40, 80, 40, 40);
       }
       else if(semaforoA.isVerde()){
           g.setColor(Color.green);
           g.fillOval(40, 130, 40, 40);
       }
       //Semaforo 2
       if(semaforoB.isRojo()){
           g.setColor(Color.red);
           g.fillOval(690, 30, 40, 40);
       }
       else if(semaforoB.isAmarillo()){
           g.setColor(Color.yellow);
           g.fillOval(690, 80, 40, 40);
       }
       else if(semaforoB.isVerde()){
           g.setColor(Color.green);
           g.fillOval(690, 130, 40, 40);
       }

       

            Iterator<Auto> recorrerA = contenedor.calleA.iterator();
            Iterator<Auto> recorrerB = contenedor.calleB.iterator();
            try {
             while(recorrerA.hasNext()){
                Auto temp = recorrerA.next();
                if(temp.getPosicion() == 0)
                     g.drawImage(new ImageIcon(getClass().getResource("/img/autoderecha.png")).getImage(), temp.x, 290, puntero);
            else 
                g.drawImage(new ImageIcon(getClass().getResource("/img/autoizquierda.png")).getImage(), temp.x, 260, puntero);
            }
        } catch (ConcurrentModificationException | NullPointerException e) {
        }
           
            try {
             while(recorrerB.hasNext()){
                Auto temp = recorrerB.next();
                if(temp.getPosicion() == 0)
                    g.drawImage(new ImageIcon(getClass().getResource("/img/autoabajo.png")).getImage(), 400, temp.y, puntero);
            else
                g.drawImage(new ImageIcon(getClass().getResource("/img/autoarriba.png")).getImage(), 450, temp.y, puntero);
            }
        } catch (ConcurrentModificationException | NullPointerException e) {
        }
          

    }



   
}
