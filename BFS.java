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
	
	
	public static int bfsCosto(Digraph g,Node inicio,Node fin){
		
		FibonacciHeap cola = new FibonacciHeap(); //creamos una cola
		cola.insertar(inicio,inicio.getCosto()); //agregamos ese nodo
// 		inicio.setVisit(true); //se marca como visitado
		inicio.setDistancia(inicio.getCosto());
		int total=0;int distTotal=-1;
		while (cola.getSize()>0){ //mientras la cola no este vacia
			Node actual= (Node) cola.desencolarMinimo();
			
			ListIterator<Node> sucesores= g.getSucs(actual.getId()).iterator();
			while (sucesores.hasNext()){
			//para los sucesores del nodo si no han sido visitados
			//se aumenta su distancia, se visitan y se agregan a la cola
				Node aux=sucesores.next();
				int dist=actual.getDistancia()+aux.getCosto();
				if ((dist<=aux.getDistancia()) || (aux.getDistancia()==-1)){
					aux.setDistancia(dist);//cambia su distancia
					System.out.println("voy por "+actual+" con costo "+dist+" a "+aux);
// 					aux.setAnterior(actual);
					if (aux.equals(fin)) {
						System.out.println("--llegue a fin por "+actual+" con costo "+dist);
						if (distTotal!=dist){ //lo encontramos por primera vez
							distTotal=dist;
							total=1;
						} else{
							total++;
						}
					}
					if (!aux.isVisited(actual)){ 
						aux.setVisit(actual); //se marca como visitado 
						if (!aux.equals(fin)){
							cola.insertar(aux,aux.getCosto()); //se agrega a la cola
						}
					}
				}
			}
		}
		System.out.println("cant: "+total+"\tdist: "+distTotal);
		return distTotal; //no se encontro un camino de inicio a fin
	}
}


// 	public static int bfsCosto(Digraph g,Node inicio,Node fin){
// 		
// 		FibonacciHeap cola = new FibonacciHeap(); //creamos una cola
// 		cola.insertar(inicio,inicio.getCosto()); //agregamos ese nodo
// // 		inicio.setVisit(true); //se marca como visitado
// 		inicio.setDistancia(inicio.getCosto());
// 		inicio.setTotal(1);
// // 		int total=0;int distTotal=-1;
// 		while (cola.getSize()>0){ //mientras la cola no este vacia
// 			Node actual= (Node) cola.desencolarMinimo();
// 			
// 			ListIterator<Node> sucesores= g.getSucs(actual.getId()).iterator();
// 			while (sucesores.hasNext()){
// 			//para los sucesores del nodo si no han sido visitados
// 			//se aumenta su distancia, se visitan y se agregan a la cola
// 				Node aux=sucesores.next();
// 				int dist=actual.getDistancia()+aux.getCosto();
// 				if ((dist<=aux.getDistancia()) || (aux.getDistancia()==-1)){
// 					System.out.println("voy por "+actual+" con costo "+dist+" a "+aux);
// // 					aux.setAnterior(actual);
// // 					if (aux.equals(fin)) {
// // 					System.out.println("--llegue a fin por "+actual+" con costo "+dist);
// 					if (dist<aux.getDistancia()||(aux.getDistancia()==-1)){ //lo encontramos por primera vez
// 						aux.setDistancia(dist);//cambia su distancia
// // 						distTotal=dist;
// 						aux.setTotal(actual.getTotal());
// 					} else{
// 						aux.setTotal(aux.getTotal()+actual.getTotal());
// 					}
// // 					}
// 					if (!aux.isVisited(actual)){ 
// 						aux.setVisit(actual); //se marca como visitado 
// 						if (!aux.equals(fin)){
// 							cola.insertar(aux,aux.getCosto()); //se agrega a la cola
// 						}
// 					}
// 				}
// 			}
// 		}
// 		
// 		System.out.println("cant: "+fin.getTotal()+"\tdist: "+fin.getDistancia()+"\n");
// 		return 0;//distTotal; //no se encontro un camino de inicio a fin
// 	}