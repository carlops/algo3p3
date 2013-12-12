/**
 * Archivo: InfoNodo.java
 * Descripcion: clase que contiene un elemento y dos listas,
 * 	una de predecesores y otra de sucesores, las listas contienen Edges
 * Autor: Alejandro Guevara 09-10971 y Carlo Polisano 09-10672
 * Fecha: 18/11/13
 */

public class InfoNodo<E> implements Cloneable{
	private E actual;
	private MyList<Edge> predecesores;
	private MyList<Edge> sucesores;
	
   /**
	* Inicializador de la caja.
	*/
	public InfoNodo() {
		actual = null;
		predecesores= new MyList();
		sucesores= new MyList();
	}
	
   /**
	* Inicializador de la caja pasandole un elemento tipo E.
	*/
	public InfoNodo(E n) {
		actual = n;
		predecesores=new MyList();
		sucesores=new MyList();
	}
	
   /**
	* Cambia el elemento actual por datos
	*/
	public void setDato(E datos){
	    actual = datos;
	}
	
   /**
	* Retorna el elemento tipo E
	*/
	public E getDato(){
	    return actual;
	}
	
   /**
	* Agrega un Edge a la lista de predecesores
	*/
	public boolean addPre(Edge C){
	    return this.predecesores.add(C);//se usa el add de lista 
	}
	
   /**
	* Retorna la lista de predecesores
	*/
	public MyList<Edge> getPre(){
		return this.predecesores;
	}
	
   /**
	* Cambia la lista de predecesores de por nuevoPre
	*/
	public void setPre(MyList<Edge> nuevoPre){
		predecesores=nuevoPre;
	}
	
   /**
	* Retorna el tamano de la lista de predecesores
	*/
	public int getPreSize(){
		return predecesores.getSize();
	}
	
   /**
	* Agrega un Edge a la lista de sucesores
	*/
	public boolean addSuc(Edge C){
	    return this.sucesores.add(C);
	}
	
   /**
	* Retorna la lista de sucesores
	*/
	public MyList<Edge> getSuc(){
		return this.sucesores;
	}
	
   /**
	* Cambia la lista de predecesores de por nuevoSuc
	*/
	public void setSuc(MyList<Edge> nuevoSuc){
		sucesores=nuevoSuc;
	}
	
   /**
	* Retorna el tamano de la lista de sucesores
	*/
	public int getSucSize(){
		return sucesores.getSize();
	}
	
   /**
	* Elimina el edge C de la lista de sucesores, retorna true si lo elimino
	* false en caso contrario, si no esta retorna false 
	*/
	public boolean removeSuc(Edge C){
		return sucesores.remove(C);
	}
	
   /**
	* Elimina el edge C de la lista de predecesores, retorna true si lo 
	* elimino false en caso contrario, si no esta retorna false 
	*/
	public boolean removePre(Edge C){
		return predecesores.remove(C);
	}
	
   /**
	* Retorna si el objeto o es igual a this
	*/
	public boolean equals(Object o){
	    if (!(o instanceof InfoNodo))
			return false;
	    InfoNodo c = (InfoNodo) o;
	    return (this.actual.equals(c.getDato()));  
	}
	
   /**
	* Retorna true si el edge C esta en la lista de predecesores, false si no
	*/
	public boolean containsPre(Edge C){
		return this.predecesores.contains(C);
	}
	
   /**
	* Retorna true si el edge C esta en la lista de sucesores, false si no
	*/
	public boolean containsSuc(Edge C){
		return this.sucesores.contains(C);
	}
	
   /**
	* Retorna una copia de este infonodo
	*/
	@Override
	public InfoNodo clone(){
		InfoNodo aux = new InfoNodo();
		aux.setSuc(this.sucesores.clone());
		aux.setPre(this.predecesores.clone());
		aux.setDato((E) ((Node)actual).clone());
		return aux;
	}
	
   /**
	* Devuelve el string de lo que tiene el infonodo
	*/
	@Override
	public String toString() { 
	    return "InfoNodo: <" + this.actual + ">";
	}
}