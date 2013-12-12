/**
 * Archivo: MyList.java
 * Descripcion: Clase que implementa la interfaz List
 *  Esta es una clase parametrizada con tipo (clase) E; i.e., la
 *  lista contiene elementos de tipo E
 * Autor: Alejandro Guevara 09-10971 y Carlo Polisano 09-10672
 * Fecha: 18/11/13
 */
public class MyList<E> implements List<E>{

    /**
     * Modelo de representacion: lista doble enlazada con cabecera.
     *
     */
    private int size;
    private Caja Head;
    private Caja Tail;
    
   /**
	* Constructor
	*/
    public MyList() {
		size = 0;
		Head=null;
		Tail=null;
    }

   /**
	* Agrega un elemento al final de la lista.
	*/
	public boolean add(E element) {
		Caja aux = new Caja();//se crea la nuevo caja
		if (aux==null)
			return false;
		
		aux.setDato(element);//se le agrega element a la caja
		if(size==0){
			//la lista esta vacia
			Head = aux;
		} else{
			Tail.setSig(aux);//el ultimo apunta a la nueva caja
			aux.setAnt(Tail);//el anterior de la nueva caja es Tail
		}
		Tail = aux;//ahora la nueva caja es la ultima de la lista
		size++;
		return true;
	}
	
   /**
	* Agrega una caja al final de la lista.
	*/
	public boolean addCaja(Caja C) {
		return this.add((E)C.getDato());//se usa el add con el elemento
	}
    
    
    /**
     * Elimina todos los elementos de la lista. La lista queda
     * como recien creada.
     */
    public void clear(){
		Head=null;
		Tail=null;
		size=0;
    }
	
   /**
	* Determina si el elemento dado esta en la lista.
	*/
	public boolean contains(Object element){
		ListIterator<E> iter = iterator();
		
		while (iter.hasNext()) {
			if (iter.next().equals(element))
				return true;
		} 
		return false;
	}

   /**
	* Determina si la lista dada es igual a la lista.
	*/
	public boolean equals(Object o){
		
		if (!(o instanceof MyList))
			return false;
		
		MyList list = (MyList) o;
		
		if (this.size == list.size){
			ListIterator<E> iter = iterator();
			while (iter.hasNext()){
				if (!list.contains(iter.next())) 
					return false;
			}
			return true; // Recorrio toda la lista y son iguales
		}
		return false;//tamano diferentes ==> listas distintas
	}

   /**
	* Determina si la lista es vacia.
	*/
    public boolean isEmpty(){
		return size == 0;
    }

   /**
	* Retorna el elemento en la posicion pos,
	* 0 <= pos < this.getSize()
	*/
    public E get(int pos){
		ListIterator<E> iter = iterator();
		int i=0; 
		E aux=null;
		
		while (iter.hasNext() && i<pos+1) {
			aux=iter.next();
			i++;
		}
		return aux;
    }
    
    

    /**
     * Elimina el elemento que esta en la posicion pos de la lista. Si
     * la lista cambia, retorna true, sino retorna false.  
     *
     * Utilizar esta operacion puede hacer invalido los iteradores
     * sobre this
     */
    public boolean remove(int pos){
		if (size<=pos) return false;
		ListIterator<E> iter = iterator();
		int i=0; 
		
		while (iter.hasNext() && i<pos+1) {
			iter.next();
			i++;
		}
		iter.unlink();//se eliminara el ultimo elemento retornado por next
		size--;
		return true;
    }

    /**
     * Elimina el elemento dado de la lista. Si la lista cambia,
     * retorna true, sino retorna false.
     */
	public boolean remove(E element){
		ListIterator<E> iter = iterator();
		while (iter.hasNext()) {
			E aux=iter.next();
			if (aux.equals(element)) {
				iter.unlink();//eliminara el ultimo elemento retornado por next
				if (size>0) 
					size--;//si size era igual 1, el unlink hara clear 
							//dejando ya el size en 0
				return true;
			}
        } 
        return false;
    }

    /**
     * Retorna el numero de elementos en la lista
     */
    public int getSize(){
		return size;
    }

    /**
     * Devuelve un iterador sobre la lista.
     */
    public ListIterator<E> iterator() {
		return new MyListIterator(this);
    }
    
    
		/** ----------- Funciones Adicionales ------------ 
		* Funciones adicionales a las requeridas por implementar
		*/
        
   /**
	* Devuelve el primer elemento de la lista.
	*/
    public Caja getHead(){
		return Head;
    }
    
   /**
	* Cambia el primer elemento de la lista.
	*/
    public void setHead(Caja primera){
		Head=primera;
    }
    
   /**
	* Devuelve el ultimo elemento de la lista.
	*/
    public Caja getTail(){
		return Tail;
    }

   /**
	* Cambia el ultimo elemento de la lista.
	*/
    public void setTail(Caja ultima){
		Tail=ultima;
    }
	
   /**
	* Copia todos los elementos de la lista this en una nueva y
	* la retorna
	*/
	public MyList<E> clone(){
		MyList<E> aux = new MyList();
		return aux=this;// Se hace de esta manera porque no se logro 
						//implementar el clone generico necesario debido a
						//que el API no deja hacerlo al tenerlo protected
		
		/*
		Caja cajaAux= this.getHead();
		if (this.getHead()==null)//lista a clonar es nula
			return aux;
		// Ciclo clonador
		while (cajaAux!=null){
			this.addCaja((Caja)cajaAux.clone());
			cajaAux=cajaAux.getSig();
		}
		*/
	}
    
   /**
	* Concatena dos listas, retorna l1 + this (sin repeticiones)
	* y lo retorna en una lista nueva
	*/
	
	public void concatenate(MyList<E> l1){
		Caja cajaAux= l1.getHead(); //obtengo el primer elemento de l1
		while (cajaAux!=null){
			//recorro l1
			this.add((E)cajaAux.getDato());//agrega los elementos de l1 a this
			cajaAux=cajaAux.getSig();
		}
    }
    
   /**
	* Retorna la representacion lista a imprimir en un String
	*/
	@Override
	public String toString() {
		ListIterator<E> iter = iterator();
		String ret;
		ret="Lista: Tamanio:"+size+"\n";
		while (iter.hasNext()) {
			ret+="\t"+iter.next()+"\n";
		}
		return ret;
	}
    
	/**
	* Busca y retorna el elemento de la clave proporsionada,
	* si no lo encuentra retorna null
	*/
	@SuppressWarnings("unchecked")
	public E getElem(Object elemento){
		ListIterator<E> iter = iterator();
		E aux=null;
		
		// Se itera sobre la lista buscando la clave 
		while (iter.hasNext()){
			aux= iter.next();
			if (elemento.equals(aux)){ 
			//compara el elemento retornado por next con la clave dada
				return aux;//Si son iguales retorna el elemento encontrado 
			}
		}
		return null;
    }
    
    /** ------------ Fin de Funciones Adicionales ------------ */
    
}// End List.
