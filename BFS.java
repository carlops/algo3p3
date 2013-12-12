/**
 * Archivo: BFS.java 
 * Descripcion: Clase que implementa el algoritmo bfs para recorrer un grafo
 * Autor: Alejandro Guevara (09-10971) y Carlo Polisano (09-10672) Grupo 10
 * Fecha: 30/11/2013
 **/
 
public class BFS {
	
	/**
	 * Metodo que recorre el grafo por capas desde el nodo inicio hasta el 
	 * nodo fin, y retorna la distancia entre ellos, si el nodo fin no es 
	 * alcanzable desde el nodo inicio se retorna 0.
	 **/
	public static int bfs(Digraph g,Node inicio,Node fin){

		MyList cola = new MyList(); //creamos una cola
		cola.add(inicio); //agregamos ese nodo
		inicio.setVisit(true); //se marca como visitado
		while (cola.getHead()!=null){ //mientras la cola no este vacia
			Node actual= (Node) cola.getHead().getDato();
			if (actual.equals(fin)) 
				return actual.getDistancia(); //lo encontramos!
			cola.remove(actual); //desencola
			ListIterator<Node> sucesores= g.getSucs(actual.getId()).iterator();
			while (sucesores.hasNext()){
			//para los sucesores del nodo si no han sido visitados
			//se aumenta su distancia, se visitan y se agregan a la cola
				Node aux=sucesores.next();
				if (!aux.isVisited()){ 
					aux.setDistancia(actual.getDistancia()+1);//cambia su distancia
					aux.setVisit(true); //se marca como visitado 
					cola.add(aux); //se agrega a la cola
				}
			}
		}
		return 0; //no se encontro un camino de inicio a fin
	}
}