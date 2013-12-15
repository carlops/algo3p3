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
	
	public DoubleCircularList getRaices(){
		return raices;
	}
	
	
	public int getSize(){
		return size;
	}
	
	public void insertar(T elem,int prioridad){
		NodoHeap nuevo = new NodoHeap(elem,prioridad);
		raices.add(nuevo);
		if (min==null)
			min=nuevo;
		else 
			if (prioridad<min.getPrioridad()){
				min=nuevo;
				raices.setHead(nuevo);
				}
		size++;
	}
	
	public T desencolarMinimo(){
		T mindata = (T) min.getDato();
		size--;
		DoubleCircularList minHijos = min.getHijos();
		
		raices.remove(min);
		
		if (raices.isEmpty()){
			min=null;
			return mindata;
		}
		
		min= (NodoHeap) raices.getHead().getSig().getDato();
		
		//el padre de los hijos ahora es null 
		DoubleCircularListIterator iter = minHijos.iterator();
		while (iter.hasNext()){
			NodoHeap aux = (NodoHeap) iter.next();
			aux.setPadre(null);
		}
		raices.concatenate(minHijos);
		
		
		//cosolidamos el fibonacci heap
		NodoHeap[] grados=new NodoHeap[raices.getSize()];
		
		iter = raices.iterator();
		while (iter.hasNext()){
			NodoHeap actual =(NodoHeap) iter.next();
			int pos = actual.getGrado();
			NodoHeap aux= grados[pos];
			while (true){
				if (aux==null){ //no hay heap con ese grado
					aux=actual;
					if (actual.getPrioridad()<min.getPrioridad()){
						
						min=actual;//hay nuevo minimo
						raices.setHead(actual);
					}
					break;//se termino d consolidar ese heap 
					
				} else {//hay heap con mismo grado
					grados[pos]=null;
					if (actual.getPrioridad()<aux.getPrioridad()){//actual<aux
						if (actual.getPrioridad()<min.getPrioridad()){
							min=actual;
							raices.setHead(actual);
						}
						
						actual.addHijo(aux);
						raices.remove(aux);
						
						//para la siguiente iteracion
						aux= grados[actual.getGrado()];
					} else { //aux<=actual
					
						aux.addHijo(actual);
						raices.remove(actual);
						
						//para la siguiente iteracion
						actual=aux;
						aux= grados[aux.getGrado()];
					}
				}
			}
		} //fin consolidacion
		return mindata;
	}
	
	public String toString(){
		String ret;
		ret="Fib: Tamanio:"+size+"\n";
		ret+="\t"+raices;
		
		return ret;
	}
	
	
	public void imprimirHeap() {
		NodoHeap aux = min;
		if (aux == null)
			return;
		else {
			imprimirArbol(aux, "", true);
			System.out.println();
			aux = aux.getSig();
			while (aux != min) {
				imprimirArbol(aux, "", true);
				System.out.println();
				aux = aux.getSig();
			}
		}
	}
	
	private void imprimirArbol(NodoHeap n, String prefix, boolean isTail) {
		String s = prefix;
		if (isTail)
			s = s + "'-- ";
		else
			s = s + "|-- ";

		System.out.print(s);
		System.out.println(n.getDato());
		int nHijos = 0;
		DoubleCircularListIterator iter = n.getHijos().iterator();
		while(iter.hasNext()) {
			NodoHeap aux =(NodoHeap) iter.next();
			nHijos++;
			
			DoubleCircularListIterator iter2 = n.getHijos().iterator();
			while(iter2.hasNext()) {
				NodoHeap aux2 =(NodoHeap) iter2.next();
				String s2 = "";
				if (nHijos > 0) {
					while (nHijos > 0) {
						s2 = prefix;
						if (isTail)
							s2 = s2 + "    ";
						else
							s2 = s2 + "|   ";
						
						if (nHijos > 1)
							imprimirArbol(aux2, s2, false);
						else
							imprimirArbol(aux2, s2, true);
						nHijos--;
					}
				}
			}
		}
	}


}
