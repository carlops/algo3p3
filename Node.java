/**
 * Archivo: Node.java
 * Descripcion: Clase que almacena la informacion de un vertice
 * Autor: Alejandro Guevara (09-10971) y Carlo Polisano (09-10672) Grupo 10
 * Fecha: 30/11/2013
 */

public class Node implements Cloneable {

    // Se asume que el id es unico
    private String id;
    private MyList visitadores;
    private int distancia;
    private int costo;
    private Node anterior;
    private boolean visitado;
    private int total;

    public Node(String i){
		id = new String(i);
		visitadores = new MyList();
		distancia=-1;
		costo = 0;
		anterior=null;
		visitado=false;
		total=0;
	}
	
	public Node(String i, int c){
		id = new String(i);
		visitadores = new MyList();
		distancia=-1;
		costo = c;
		anterior=null;
		visitado=false;
		total=0;
	}
	
// 	public void setAnterior(Node ant){
// 		anterior=ant;
// 	}
// 	
// 	public Node getAnterior(){
// 		return anterior;
// 	}
    
	public int getTotal(){
		return total;
	}
	
	public void setTotal(int num){
		total=num;
	}
    
	public int getCosto(){
		return costo;
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
    public boolean isVisited(Node nodo){
		return (visitado || visitadores.contains(nodo));
    }
   /**
    * Metodo que modifica el valor de visitado
    */
    public void setVisit(Node nodo){
		visitadores.add(nodo);
    }
    
    public void unvisit(){
		visitadores= new MyList();
    }
    
    public MyList getVisitas(){
		return visitadores;
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
