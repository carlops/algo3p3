/**
 * Archivo: Caja.java
 * Descripcion: clase que permite implementar una lista doble enlazada
 * Autor: Alejandro Guevara 09-10971 y Carlo Polisano 09-10672
 * Fecha: 18/11/13
 */

public class Caja<E>{
	private E data;
	private Caja siguiente;
	private Caja anterior;
	
   /**
	* Inicializador de la caja.
	*/
	public Caja() {
	    data = null;
	    siguiente=null;
	    anterior=null;
	}

   /**
	* Le asigna un nuevo valor al atributo generico data
	*/
	public void setDato(E datos){
	    data = datos;
	}

   /**
	* Metodo que retorna la data de la caja
	*/
	public E getDato(){
	    return data;
	}
	
   /**
	* Metodo que modifica el apuntador a anterior de la caja
	*/
	public void  setAnt(Caja C){
	    this.anterior = C;
	}
	
   /**
	* Metodo que retorna el apuntador a anterior de la caja
	*/
	public Caja getAnt(){
	    return this.anterior;
	}
	
   /**
	* Metodo que modifica el apuntador a siguiente de la caja
	*/
	public void  setSig(Caja C){
	    this.siguiente = C;
	}
	
   /**
	* Metodo que retorna el apuntador a siguiente de la caja
	*/
	public Caja getSig(){
	    return this.siguiente;
	}
	
   /**
	* Metodo que retorna si el objeto o es igual a this
	*/
	public boolean equals(Object o){
	    if (!(o instanceof Caja))
			return false;
	    Caja c = (Caja) o;
	    
	    return (this.data.equals(c.data));  
	}
	
   /**
	* Metodo que retorna una 'copia' de this
	*/
	public Caja clone(){
		Caja nueva = new Caja();
		
		nueva=this;	// Se hace de esta manera porque no se logro 
					// implementar el clone generico necesario debido a
					// que el API no deja hacerlo al tenerlo protected
		
		/*if (data instanceof Cloneable){
			Cloneable aux = (Cloneable) data;
			nueva.setDato(aux.clone());
		}*/
		
		return nueva;
	}
	
   /**
	* Retorna la representacion en String de la caja.
	*/
	@Override
	public String toString() { 
	    return "Caja: <" + this.data + ">";  
	}

}