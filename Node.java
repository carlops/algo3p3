/**
 * Archivo: Node.java
 * Descripcion: Clase que almacena la informacion de un vertice
 * Autor: Alejandro Guevara (09-10971) y Carlo Polisano (09-10672) Grupo 10
 * Fecha: 30/11/2013
 */

public class Node implements Cloneable {

    // Se asume que el id es unico
    private String id;
    private boolean visitado;
    private int distancia;
    private int costo;

    public Node(String i){
	id = new String(i);
	visitado = false;
	distancia = 0;
	costo = 0;
    }
    
    public Node(String i, int c){
	id = new String(i);
	visitado = false;
	distancia = 0;
	costo = c;
    }

   /**
    * Metodo que retorna el valor de distancia
    */
    public int getDistancia(){
		return distancia;
    }
   /**
    * Metodo que modifica el valor de distancia
    */
    public void setDistancia(int v){
		distancia=v;
    }
    
   /**
    * Metodo que retorna si ya fue visitada la caja
    */
    public boolean isVisited(){
		return visitado;
    }
   /**
    * Metodo que modifica el valor de visitado
    */
    public void setVisit(boolean v){
		visitado=v;
    }
    
    /**
     * Retorna una nueva arista que es copia de this.
     */
     @Override
    protected Node clone() {
		return new Node(id);
    }

    /**
     * Indica si la arista de entrada es igual a this.
     */
	public boolean equals(Object o) {
		Node d;
		
		if (!(o instanceof Node))
			return false;
		d = (Node) o;
		return d.getId().equalsIgnoreCase(id);
    }

    /**
     * Retorna la representacion en String de la arista.
     */
    @Override
    public String toString() {
		return "<" + this.id + ">";
    }
    
    public String getId(){
		return id;
    }
    
    /**
     * Retorna el codigo hash para un nodo.
     */
    @Override
    public int hashCode() {
		return this.id.hashCode();
    }
    
}

// End Node.java
