/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.negocio;
import java.awt.Color;
import ufps.util.Secuencia;

/**
 * @author Nelson Sepulveda 
 * @author Leonar Gonzalez
 */
public class FilaColor {
    
    /**
     * Atributo
     */
    private Secuencia<Pixel> pixeles;
    
    /**
     * Metodo con parametros
     * @param m el cual es la cantidad de elementos
     * que alverga. 
     */
    public FilaColor(int m)
    {
        this.pixeles=new Secuencia<>(m);
    }
    
    public Secuencia<Pixel> getPixeles(){
        return null;
    }
    
    /**
     * Metodo que permite insertar un pixel dentro 
     * de la secuencia
     * @param i el cual es una posicion dentro de la secuencia
     * @param x el cual es el pixel a insertar
     */
    public void insertarPixel(int i , Pixel x)
    {
        this.pixeles.set(i, x);
    }
   
    /**
     * Metodo que permite obtener el color en una posicion 
     * @param i el cual es una posicion dentro de la secuencia
     * @return 
     */
   
    public Color get(int i)
    {
        return this.pixeles.get(i).get();
    }
    
    /**
     * Metodo que permite obtener un pixel dado una posicion
     * @param i el cual es una posicion dentro de la secuencia
     * @return 
     */
    public Pixel getColor(int i )
    {
        return this.pixeles.get(i);
    }
    
    /**
     * 
     * @return la longitud total de la secuencia de Pixeles
     */
    
    public int getLength()
    {
        return this.pixeles.getLength();
    }
    
    /**
     * Metodo que permite saber si dos objetos de FilaColor,
     * son iguales. 
     * @param o de tipo object
     * @return true si las dos Secuencia<Pixel> son iguales
     * en cuanto a longitud e informacion, en caso contrario retorna false.
     * Se recomienda sobreescribir el metodo equals() en la clase Pixel
     */
    public boolean equals(Object o)
    {
        FilaColor temp=(FilaColor)o;
        
        if(this.getLength()!=temp.getLength())
            return false;
        
        
        for(int i=0;i<this.getLength();i++)
        {
            if(!this.pixeles.get(i).equals(temp.pixeles.get(i)))
                return false;
            
        }
        return true;
    }
    
    /**
     * Metodo que permite comparar dos objetos
     * @param o de tipo Object
     * @return la diferencia entre los elementos de la Secuencia<Pixel>
     * y las longitudes de las Secuencias.
     */

     public int compareTo(Object o)
     {
         
         FilaColor t=(FilaColor)o;
         byte tam=(byte) (this.pixeles.getLength()-t.pixeles.getLength());   
         if(tam==0)
         {
             byte c;
             for(byte i=0;i<this.pixeles.getLength();i++)
             {
                 c=(byte) this.pixeles.get(i).compareTo(t.pixeles.get(i));
                 if(c!=0)
                     return c;
             }
         }
         return tam;
     }
    

}
