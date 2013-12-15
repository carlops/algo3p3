/**
 * Archivo: NodoHeap.java
 * Descripcion: Nodo Utilizado en Heap 
 * Autor: 
 * Fecha: 
 */

	public class NodoHeap<E>{
	private E elemento;
	private NodoHeap siguiente;
	private NodoHeap anterior;
	private NodoHeap padre;
	private DoubleCircularList hijos;
	private int prioridad;
	private boolean marcado;
	private int grado;
	
	public NodoHeap(E elem,int prio) {
		elemento=elem;
		prioridad=prio;
		siguiente=null;
		anterior=null;
		hijos=new DoubleCircularList();
		marcado=false;
		grado=0;
		padre=null;
	}
	
	public void addHijo(NodoHeap hijo){
		hijo.setPadre(this);
		this.grado+=(hijo.getGrado()+1);
		this.hijos.add(hijo);
	}
	
	public void addHijos(DoubleCircularList nuevosHijos){
		DoubleCircularListIterator iter = nuevosHijos.iterator();
		while (iter.hasNext()){
			NodoHeap aux = (NodoHeap) iter.next();
			aux.setPadre(this);
			this.grado+=(aux.getGrado()+1);
		}
		this.hijos.concatenate(nuevosHijos);
		
	}
	
	
	public int getGrado(){
		return grado;
	} 
	
	public DoubleCircularList getHijos(){
		return hijos;
	}
	
	public NodoHeap getHijo(){
		return (NodoHeap) hijos.getHead().getDato();
	}
	
	public void setPadre(NodoHeap p){
		padre=p;
	}
	
	public NodoHeap getPadre(){
		return padre;
	}
	
	
	public void setPrioridad(int num){
		prioridad=num;
	}
	
	public int getPrioridad(){
		return prioridad;
	}
	
	public void setDato(E datos){
		elemento=datos;
	}
	
	public E getDato(){
		return elemento;
	}
	
	public void  setSig(NodoHeap C){
		this.siguiente = C;
	}
	
	public NodoHeap getSig(){
		return this.siguiente;
	}
	
	public void setAnt(NodoHeap C){
		this.anterior = C;
	}
	
	public NodoHeap getAnt(){
		return this.anterior;
	}
	
	public boolean equals(Object o){
		if (!(o instanceof NodoHeap))
			return false;
		NodoHeap c = (NodoHeap) o;
		
		return (this.elemento.equals(c.elemento)); 
	}
	
	@Override
	public String toString() { 
	    return "NodoHeap: < " + this.elemento +" prioridad "+prioridad+" >";  
	}

}