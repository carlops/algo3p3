/**
 * Archivo: DoubleCircularList.java
 * Descripcion: 
 * 
 * Autor: Alejandro Guevara 09-10971 y Carlo Polisano 09-10672
 * Fecha:
 */
public class DoubleCircularList<E> implements List<E>{

    /**
     * Modelo de representacion: lista doble enlazada y circular.
     *
     */
    private int size;
    private Caja Actual;
    
   /**
	* Constructor
	*/
    public DoubleCircularList() {
		size = 0;
		Actual=null;
    }

   /**
	* Agrega un elemento a la lista.
	*/
	public boolean add(E element) {
		Caja aux = new Caja();//se crea la nuevo caja
		if (aux==null)
			return false;
		
		aux.setDato(element);//se le agrega element a la caja
		if(size==0){
			//la lista esta vacia
			Actual = aux;
			Actual.setSig(aux);
			Actual.setAnt(aux);
		} else{
			aux.setSig(Actual.getSig());//nueva caja apunta a la que apuntaba Actual 
			aux.getSig().setAnt(aux);//la nueva caja es el anterior del que ella apunta ahora
			Actual.setSig(aux);//Actual apunta a la nueva caja
			aux.setAnt(Actual);//el anterior de la nueva caja es Actual
		}
		size++;
		return true;
	}
	
   /**
	* Agrega una caja a la lista.
	*/
	public boolean addCaja(Caja C) {
		return this.add((E)C.getDato());//se usa el add con el elemento
	}
    
    
    /**
     * Elimina todos los elementos de la lista. La lista queda
     * como recien creada.
     */
    public void clear(){
		Actual=null;
		size=0;
    }
	
   /**
	* Determina si el elemento dado esta en la lista.
	*/
	public boolean contains(Object element){
		ListIterator<E> iter = iterator();
		int count = 0;
		while (iter.hasNext()&&count<size) {
			if (iter.next().equals(element))
				return true;
			count++;
		} 
		return false;
	}

   /**
	* Determina si la lista dada es igual a la lista.
	*/
	public boolean equals(Object o){
		
		if (!(o instanceof DoubleCircularList))
			return false;
		
		DoubleCircularList list = (DoubleCircularList) o;
		int count=0;
		if (this.size == list.size){
			ListIterator<E> iter = iterator();
			while (iter.hasNext()&&count<size){
				if (!list.contains(iter.next())) 
					return false;
				count++;
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
		int count =0;
		while (iter.hasNext()&&count<size) {
			E aux=iter.next();
			if (aux.equals(element)) {
				iter.unlink();//eliminara el ultimo elemento retornado por next
				if (size>0) 
					size--;//si size era igual 1, el unlink hara clear 
							//dejando ya el size en 0
				return true;
			}
			count++;
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
		return new DoubleCircularListIterator(this);
    }
    
    
		/** ----------- Funciones Adicionales ------------ 
		* Funciones adicionales a las requeridas por implementar
		*/
        
   /**
	* Devuelve el primer elemento de la lista.
	*/
    public Caja getHead(){
		return Actual;
    }
    
    
   /**
	* Cambia el primer elemento de la lista.
	*/
    public void setHead(Caja primera){
		Actual=primera;
    }
	
   /**
	* Copia todos los elementos de la lista this en una nueva y
	* la retorna
	*/
	public DoubleCircularList<E> clone(){
		DoubleCircularList<E> aux = new DoubleCircularList();
		return aux=this;// Se hace de esta manera porque no se logro 
						//implementar el clone generico necesario debido a
						//que el API no deja hacerlo al tenerlo protected
	}
 
 
 //////////////////
 
  /**
	* Concatena dos listas, retorna l1 + this (sin repeticiones)
	* y lo retorna en una lista nueva
	*/	
	public void concatenate(DoubleCircularList<E> l1){
		Caja CajaAux;
		if (!l1.isEmpty()&&!this.isEmpty()){
			CajaAux= l1.getHead(); //obtengo el primer elemento de l1
			
			Caja SigCAct=Actual.getSig();
			Actual.setSig(CajaAux.getSig());
			CajaAux.getSig().setAnt(Actual);
			CajaAux.setSig(SigCAct);
			SigCAct.setAnt(CajaAux);
			this.size += l1.getSize(); 
		}
		if (this.isEmpty()){
			this.setHead(l1.getHead());
			this.size = l1.getSize();
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
		int count =0;
		// Se itera sobre la lista buscando la clave 
		while (iter.hasNext()&&count<size){
			aux= iter.next();
			if (elemento.equals(aux)){ 
			//compara el elemento retornado por next con la clave dada
				return aux;//Si son iguales retorna el elemento encountrado 
			}
			count++;
		}
		return null;
    }
    
    /** ------------ Fin de Funciones Adicionales ------------ */
    
}// End List.
