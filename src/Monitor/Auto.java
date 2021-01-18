
package Monitor;

/**
 *
 * @author Enrique
 */
public class Auto {
    int idauto = 0;
    int posicion = 0;
    int calle = 0;
    int x, y;
    boolean sacar = false;

    public boolean isSacar() {
        return sacar;
    }

    public void setSacar(boolean sacar) {
        this.sacar = sacar;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public Auto() {
    }
    public Auto(int idauto,int posicion,int calle){
        this.idauto = idauto;
        this.posicion = posicion;
        this.calle = calle;
    }
    public Auto(int idauto,int posicion,int calle,int x,int y,boolean sacar){
        this.idauto = idauto;
        this.posicion = posicion;
        this.calle = calle;
        this.x = x;
        this.y = y;
        this.sacar = sacar;
    }
    public int getIdauto() {
        return idauto;
    }

    public void setIdauto(int idauto) {
        this.idauto = idauto;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public int getCalle() {
        return calle;
    }

    public void setCalle(int calle) {
        this.calle = calle;
    }


   
    
}
