/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.negocio;

import java.awt.Color;

/**
 * @author Nelson Sepulveda
 * @author Leonar Gonzalez
 */
public class Pixel {

    private Color rgb;

    /**
     * Constructor Vacio
     */
    public Pixel() {}

    /**
     * Constructor con parametros
     * @param red de tipo entero
     * @param green de tipo entero
     * @param blue de tipo entero
     * Los valores de las variable se toman para formar
     * un color.
     */
    public Pixel(int red, int green, int blue) {
        this.rgb = new Color(red, green, blue);
    }

    

    /**
     * Meyodo set()
     * @param x el cual es un color
     */
    public void setRGB(Color x) {
        this.rgb = x;
    }

    /**
     * Metodo get()
     * @return el color
     */
    public Color get() {
        return rgb;
    }

    
    /**
     * Metodo que permite convertir 
     * a color negro 
     */
    public void convertirNegro() {
        this.rgb = new Color(0, 0, 0);
    }

    /**
     * Metodo que permite convertir
     * a color blanco
     * 
     */
    
    public void convertirBlanco() {
        this.rgb = new Color(255, 255, 255);
    }
  
    /**
     * Metodo que permite convertir a gris
     * segun sea la gama del color que se deseea
     * convertir
     */
    
    public void convertirGris() {
        int pixelGris;
        int r = rgb.getRed();
        int g = rgb.getGreen();
        int b = rgb.getBlue();
        pixelGris = (int) (0.3*(double)r + 0.59 *(double) g + 0.11 *(double) b);
        this.rgb = new Color(pixelGris,pixelGris,pixelGris);
    }

    
    /**
     * Metodo equals()
     * @param o el cual es de tipo Object
     * @return true si dos colores son iguales en caso contrario
     */
    public boolean equals(Object o)
    {
        Pixel p=(Pixel)o;
        return this.rgb.equals(p);
    }
            
    /**
     * Metodo compareTo()
     * @param o de tipo Object
     * @return la diferencia de gama entre dos colores
     */
    
    public int compareTo(Object o)
    {
        Pixel  p= (Pixel)o;
        return this.getSumaColor()-p.getSumaColor();
    }
    
    /**
     * Metodo que obtiene la gama, 
     * sumando los valores de el rojo, verde , azul
     * @return 
     */
    private int getSumaColor()
    {
        return rgb.getRed()+rgb.getGreen()+rgb.getBlue();
    }
    
    
}
