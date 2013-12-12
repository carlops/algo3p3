/**
 * Archivo: Edge.java
 * Descripcion: Clase que almacena la informacion de una Arista (lado)
 * Autor: Eduardo Blanco
 * Fecha: Sep 2010
 */

public class Edge implements Cloneable{

    private String src = null;
    private String dst = null;

    /**
       Constructor de uso exclusivo de la clase edge.
     */
    private Edge() {
    }
    
    /**
     * Crea una arista entre los vertices src y dst.
     */
    public Edge(String src, String dst) {
		this.src=src;
		this.dst=dst;
    }

    /**
     * Retorna una nueva arista que es copia de this.
     */
     @Override
    public Edge clone() {
		Edge ed = new Edge();

		// se copian (clonan) todos los objetos internos, 
		// no solo asignar las referencias
		ed.src = new String(src);
		ed.dst = new String(dst);

		return ed;
    }

    /**
     * Indica si la arista de entrada es igual a this.
     */
    public boolean equals(Object o) {
    
		if (!(o instanceof Edge)){
			return false;
			}
		Edge d = (Edge) o;
		return ((src.equals(d.src)) && (dst.equals(d.dst)));
    }

    /**
     * Retorna el vertice src de la arista.
     */
    public String getSrc() {
		return src;
    }

    /**
     * Retorna el vertice dst de la arista.
     */
    public String getDst() {
		return dst;
    }
    
    /**
     * Retorna la representacion en String de la arista.
     */
    @Override
    public String toString() {
		return "(" + src + ", " + dst + ")";
    }

// End Edge.java
}
