/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.negocio;

import java.awt.Color;
import java.util.Random;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import sun.awt.image.BufferedImageGraphicsConfig;

import ufps.util.Secuencia;

/**
 * @author Nelson Sepulveda
 * @author Leonar Gonzalez
 */
public class Imagen {

    private Secuencia<FilaColor> filaColores;

    /**
     * Constructor vacio
     */
    public Imagen() {
    }

    /**
     * Constructor con parametros
     * @param ancho el cual representa el ancho de la imagen de pixeles
     * @param alto el cual representa el alto de la imagen de pixeles
     * @param img el cual representa la direccion de la imagen a cargar
     * 
     */
    public Imagen(int ancho, int alto, String img) throws IOException {
        this.filaColores = new Secuencia<>(ancho);
        this.crearImagen(ancho, alto, img);
    }

    /**
     * Metodo que permite crear la imagen dado el valor de las variables,
     * donde se crean valores aleatorios para cargar debidamente la imagen.
     * Asi tambien permite cargar un imagen del equipo y re-dimensionarla
     * @param ancho 
     * @param alto
     * @param imagen
     * Para mas informacion ver Solucion.pdf
     */
    private void crearImagen(int ancho, int alto, String imagen) throws IOException {
        if(alto==0) 
        {
            alto += (int) (Math.random() * 50) + 1;
        }
        Random r = new Random();
        int c;
        Pixel add;
        if (imagen.equals("")) {
            for (int i = 0; i < this.filaColores.getLength(); i++) {
        FilaColor x = new FilaColor(alto);
                c = x.getLength();
                for (int j = 0; j < c; j++) {
                    add = new Pixel(r.nextInt(256), r.nextInt(256), r.nextInt(256));
                    x.insertarPixel(j, add);
                }
                this.filaColores.set(i, x);
            }
        } else {
            BufferedImage image = ImageIO.read(new File(imagen));
            BufferedImage img = resize(image, ancho, alto);
            for (int i = 0; i < this.filaColores.getLength(); i++) {
        FilaColor x = new FilaColor(alto);
                c = x.getLength();
                for (int j = 0; j < c; j++) {
                    int color = img.getRGB(j, i);
                    add = this.pixelImagen(color);
                    x.insertarPixel(j, add);
                }
                this.filaColores.set(i, x);
            }
        }
    }

  
    /**
     * Metodo que obtiene el color del pixel por la posición
     * @param color
     * @return 
     */
    private Pixel pixelImagen(int color) {
        int red = (color & 0x00ff0000) >> 16;
        int green = (color & 0x0000ff00) >> 8;
        int blue = color & 0x000000ff;
        return new Pixel(red, green, blue);
    }
    
    /**
     * Método que redimensiona la imagen cargada y retorna una imagen nueva
     * @param image
     * @param width
     * @param height
     * @return 
     */

    private static BufferedImage resize(BufferedImage image, int width, int height) {
        image = createCompatibleImage(image);
        int type = image.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : image.getType();
        BufferedImage resizedImage = new BufferedImage(width, height, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(image, 0, 0, width, height, null);
        g.dispose();
        return resizedImage;
    }
   
    /**
     * Método que crea una imagen que soporta píxeles transparentes
     * @param image
     * @return 
     */
    private static BufferedImage createCompatibleImage(BufferedImage image) {
        GraphicsConfiguration gc = BufferedImageGraphicsConfig.getConfig(image);
        int w = image.getWidth();
        int h = image.getHeight();
        BufferedImage result = gc.createCompatibleImage(w, h, Transparency.BITMASK);
        Graphics2D g2 = result.createGraphics();
        g2.drawRenderedImage(image, null);
        g2.dispose();
        return result;
    }

    /**
     * Metodo que permite rellenar los costados de la imagen
     * que se carga, sobresalantado el color negro sobre la imagen. 
     */
    public void crearMarco() {

        for (int i = 0; i < this.filaColores.getLength(); i++) {
            for (int j = 0; j < this.filaColores.get(i).getLength(); j++) {
                this.filaColores.get(0).getColor(j).convertirNegro();
                this.filaColores.get(i).getColor(0).convertirNegro();
                this.filaColores.get(this.filaColores.getLength() - 1).getColor(j).convertirNegro();
                this.filaColores.get(i).getColor(this.filaColores.get(i).getLength() - 1).convertirNegro();
            }
        }
    }

    /**
     * Metodo que permite invertir la imagen de manera vertical.
     */
    public void flipOrizontal() {
        int l = this.filaColores.getLength();
        for (int i = 0, k = l - 1; i < (l / 2); i++, k--)
        {
            Object fila1 = this.filaColores.get(i);
            Object fila2 = this.filaColores.get(k);
            this.filaColores.set(i, (FilaColor) fila2);
            this.filaColores.set(k, (FilaColor) fila1);
        }
    }

    /**
     * Metodo que permite invertir los pixeles de la
     * imagen que pertenezcan a una gama determinada. 
     * @param tipoColorRGB el cual es de tipo String, donde este recibe 
     * el color que se desea invertir a gris.
     */
    
    public void invertirGama(String tipoColorRGB) {
        String c = tipoColorRGB;
        for (byte i = 0; i < this.filaColores.getLength(); i++) {
            for (byte j = 0; j < this.filaColores.get(i).getLength(); j++) {
                Color color = this.getFilaColores(i).get(j);
                int r = color.getRed();
                int g = color.getGreen();
                int b = color.getBlue();
                if (r >= g && r >= b && "rojo".equals(c)) {
                    this.filaColores.get(i).getColor(j).convertirGris();
                } else if (g > r && g >= b && "verde".equals(c)) {
                    this.filaColores.get(i).getColor(j).convertirGris();
                } else if (b > r && b > g && "azul".equals(c)) {
                    this.filaColores.get(i).getColor(j).convertirGris();
                }
            }
        }
    }

    /**
     * Metodo que permite detectar y eliminar 
     * una gama completa de colores, dado un color.
     * Donde estas tomarian un patron neutro o blanco, para que se
     * realice la transformacion debe haber mas de dos patrones juntos 
     * de manera horizontal.
     * @param tipoColorRGB el cual es de tipo String, donde este recibe el color
     * y alli buscar las respectivas gamas.
     */
    
    public void detectarPatron(String tipoColorRGB) {
        int cr = 0;
        int cg = 0;
        int cb = 0;
        String c = tipoColorRGB;

        for (int i = 0; i < this.filaColores.getLength(); i++) {
            for (int j = 0; j < this.filaColores.get(i).getLength(); j++) {
                Color color = this.getFilaColores(i).get(j);
                int r = color.getRed();
                int g = color.getGreen();
                int b = color.getBlue();

                if (r >= g && r >= b && "rojo".equals(c)) {
                    if (cr == j - 1) 
                    {
                        this.filaColores.get(i).getColor(j).convertirBlanco();
                        this.filaColores.get(i).getColor(j - 1).convertirBlanco();
                    }
                    cr = j;
                } else if (g > r && g >= b && c == "verde") {
                    if (cg == j - 1) {
                        this.filaColores.get(i).getColor(j).convertirBlanco();
                        this.filaColores.get(i).getColor(j - 1).convertirBlanco();
                    }
                    cg = j;
                } else if (b > r && b > g && c == "azul") {
                    if (cb == j - 1) {
                        this.filaColores.get(i).getColor(j).convertirBlanco();
                        this.filaColores.get(i).getColor(j - 1).convertirBlanco();
                    }
                    cb = j;
                }
            }
        }

    }

    /**
     * Metodo get(), el cual permite obtener
     * una Secuencia<Pixel> dada una posicion
     * @param i el cual es una posicion dentro de la 
     * Secuencia<FilaColor>.
     * @return 
     */
    
    public FilaColor getFilaColores(int i) {//*
        return this.filaColores.get(i);
    }

    
   /**
    * Metodo que permite cortar una parte 
    * especifica de la imagen. Una vez señalada 
    * inmediatemente se convierte a blanco.
    * @param fila de tipo entero, el cual recibe el numero de la fila
    * @param columna de tipo entero, el cual recibe el numero de la columna
    * @param ancho
    * @param alto 
    */
    

    public void cortar(int fila, int columna, int ancho, int alto) {
        for (int i = 0, k = columna; i < alto; i++, k++) {
            for (int j = 0, l = fila; j < ancho; j++, l++) {
                this.filaColores.get(k).getColor(l).convertirBlanco();
            }
        }
    }
    
    /**
     * Metodo que permite obtener la longitud de la Secuencia<FilaColor>. 
     * @return 
     */
     public int getLength() {//*
        return this.filaColores.getLength();
     }

    /**
     * Metodo sobre-escrito de la clase String que permite obtener 
     * todos los elementos de la Secuencia
     * @return
     */
    public String toString() {
        String m = "";
        for (FilaColor x : this.filaColores) {
            for (int i = 0; i < x.getLength(); i++) {
                m += x.get(i) + "\t";
            }
            m += "\n";
        }
        return (m);
    }

    
    /**
     * Método que permite saber si dos objetos son iguales
     * @param o de tipo Object
     * @return true si dos Secuencias<FilaColor> son iguales
     * tanto en sus elementos como en sus longitudes.
     * Se recomienda tener sobre-escrito el metodo equals en la 
     * clase FilaColor
     */
    public boolean equals(Object o)
    {
        
        Imagen temp= (Imagen)o;
        
        if(this.filaColores.getLength()!=temp.filaColores.getLength())
            return false;
        
        for(byte i=0;i<this.filaColores.getLength();i++)
        {
          
            if(!this.filaColores.get(i).equals(temp.filaColores.get(i)))
                return false;
        }
        
        return true;
    }
    
    
    /**
     * Metodo que permite compara si un objeto es mayor a otro.
     * @param o de tipo object
     * @return la diferencia entre dos Secuencias<FilaColor> tanto en
     * sus longitudes como sus elementos.
     */
    
    public int compareTo(Object o)
    {
        
        Imagen t=(Imagen)o;
        
         byte tam=(byte) (this.filaColores.getLength()-t.filaColores.getLength());   
          
         if(tam==0)
         {
             byte c;
             for(byte i=0;i<this.filaColores.getLength();i++)
             {
                 c=(byte) this.filaColores.get(i).compareTo(t.filaColores.get(i));
                 if(c!=0)
                     return c;
             }
         }
         return tam;
        
    }
    
}
