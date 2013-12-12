/**
 * Archivo: FibonacciHeap.java 
 * Descripcion: 
 *
 *
 * Autor: Alejandro Guevara (09-10971) y Carlo Polisano (09-10672) Grupo 10
 * Fecha: 30/11/2013
 **/

public class FibonacciHeap<T> {
	
	private DoubleCircularList raices;
	private NodoHeap min;
	private int size;
// 	private int marcados;
	
	public FibonacciHeap(){
		raices=new DoubleCircularList();
		min=null;
		size=0;
	}
	
	public void insertar(T elem,int prioridad){
		NodoHeap nuevo = new NodoHeap(elem,prioridad);
		if (min==null)
			min=nuevo;
		else 
			if (prioridad<min.getPrioridad())
				min=nuevo;
		
		raices.add(nuevo);
		size++;
	}
	
	public <T> desencolarMinimo(){
		T mindata = min.getDato();
		size--;
		DoubleCircularList minHijos = min.getHijos();
		raices.remove(mindata);
		
		DoubleCircularListIterator iter = new minHijos.iterator();
		while (iter.hasNext()){
			NodoHeap aux = (NodoHeap) iter.next();
			aux.setPadre(null);
		}
		raices.concatenate(minHijos);
		if (raices.isEmpty())
			return mindata;
			
			
		
		return mindata;
		
	}
	
}
