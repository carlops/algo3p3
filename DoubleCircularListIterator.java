/**
 * Archivo: DoubleCircularListIterator.java
 * Descripcion: Clase que implementa la interfaz ListIterator
 *  Es una interface parametrizada con tipo (clase) E
 *  lista contiene elementos de tipo E
 *  la lista es doble enlazada y circular
 * Autor: Alejandro Guevara 09-10971 y Carlo Polisano 09-10672
 * Fecha: 18/11/13
 */
public class DoubleCircularListIterator<E> implements ListIterator<E> {
    
    
    private DoubleCircularList<E> elemento;
    private Caja primer;
    private Caja actual;
    private int tam;
    
    // Bob
    public DoubleCircularListIterator(DoubleCircularList<E> lista){
		elemento=lista;
		primer=elemento.getHead();
		actual=elemento.getHead();
		tam=elemento.getSize();
    }
    
    /**
     * Comprueba que exista un proximo elemento.
     */
    public boolean hasNext(){
		return (tam>0);
    }

    /**
     * Devuelve el elemento asociado y avanza el iterador.
     */
    public E next(){
		E temp=(E) actual.getDato();
		actual=actual.getSig();
		tam--;
		return temp;
    }

    /**
     * Remueve de la lista el ultimo elemento retornado por next()
     */
    public void unlink(){
// 		if (elemento.getSize()==1) 
// 			elemento.clear(); //es el unico elemento
// 		else {
// 			if (actual==null){ 
// 				//es el ultimo elemento de la lista
// 				this.elemento.getTail().getAnt().setSig(null);
// 						//el anterior del ultimo ahora apunta a null 
// 				this.elemento.setTail(this.elemento.getTail().getAnt());//
// 			} else {
// 				if (actual.getAnt().getAnt()==null) {
// 					//es el primer elemento de la lista
// 					actual.setAnt(null);
// 					elemento.setHead(actual);
// 				} else { 
// 					//es un elemento intermedio
// 					actual.getAnt().getAnt().setSig(actual);
// 				}
// 			}
// 		}
    }
// fin DoubleCircularListIterator
}