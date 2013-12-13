/**
 * Archivo: MyList.java
 * Descripcion: Clase que implementa la interfaz ListIterator
 *  Es una interface parametrizada con tipo (clase) E
 *  lista contiene elementos de tipo E
 *  la lista es doble enlazada
 * Autor: Alejandro Guevara 09-10971 y Carlo Polisano 09-10672
 * Fecha: 18/11/13
 */
public class MyListIterator<E> implements ListIterator<E> {
    
    
    private MyList<E> elemento;
    private Caja actual;
    
    // Bob
    public MyListIterator(MyList<E> lista){
		elemento=lista;
		actual=elemento.getHead();
    }
    
    /**
     * Comprueba que exista un proximo elemento.
     */
    public boolean hasNext(){
		return (actual!=null);
    }

    /**
     * Devuelve el elemento asociado y avanza el iterador.
     */
    public E next(){
		E temp=(E) actual.getDato();
		actual=actual.getSig();
		return temp;
    }

    /**
     * Remueve de la lista el ultimo elemento retornado por next()
     */
    public void unlink(){
		if (elemento.getSize()==1) 
			elemento.clear(); //es el unico elemento
		else {
			if (actual==null){ 
				//es el ultimo elemento de la lista
				this.elemento.getTail().getAnt().setSig(null);
						//el anterior del ultimo ahora apunta a null 
				this.elemento.setTail(this.elemento.getTail().getAnt());//
			} else {
				if (actual.getAnt().getAnt()==null) {
					//es el primer elemento de la lista
					actual.setAnt(null);
					elemento.setHead(actual);
				} else { 
					//es un elemento intermedio
					actual.getAnt().getAnt().setSig(actual);
					actual.setAnt(actual.getAnt().getAnt());
				}
			}
		}
    }
// fin MyListIterator
}