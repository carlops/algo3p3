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
	 * alcanzable desde el nodo inicio se retorna -1.
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
		return -1; //no se encontro un camino de inicio a fin
	}
	
	/**
	 * Metodo que recorre el grafo por capas desde el nodo inicio hasta el 
	 * nodo fin, y retorna la cantidad de caminos entre ellos.
	 **/
	public static int bfsCaminos(Digraph g,Node inicio,Node fin){
		
		MyList cola = new MyList(); //creamos una cola
		cola.add(inicio); //agregamos ese nodo
		inicio.setVisit(true); //se marca como visitado
		inicio.setTotal(1); //
		while (cola.getHead()!=null){ //mientras la cola no este vacia
			Node actual= (Node) cola.getHead().getDato();
			if (actual.equals(fin)) 
				fin=actual; //lo encontramos!
			cola.remove(actual); //desencola
			
			System.out.println("voy por: "+actual);
			
			ListIterator<Node> sucesores= g.getSucs(actual.getId()).iterator();
			while (sucesores.hasNext()){
			//para los sucesores del nodo si no han sido visitados
			//se aumenta su distancia, se visitan y se agregan a la cola
				Node aux=sucesores.next();
				if (!aux.isVisited()){ 
					aux.setTotal(actual.getTotal());//cambia su distancia
					
					System.out.println("---- "+aux+" NO estaba visitado, nuevo total "+aux.getTotal());

					aux.setVisit(true); //se marca como visitado 
					cola.add(aux); //se agrega a la cola
				} else {
					aux.setTotal(aux.getTotal()+actual.getTotal());
					
					System.out.println("---- "+aux+" YA estaba visitado, nuevo total "+aux.getTotal());
					
				}
			}
		}
		return fin.getTotal(); //no se encontro un camino de inicio a fin
	}
	
	
	public static int bfsCosto(Digraph g,Node inicio,Node fin,Digraph sol){
	
		inicio.setVisit(true); //se marca como visitado
		FibonacciHeap cola = new FibonacciHeap(); //creamos una cola
		inicio.setDistancia(inicio.getCosto());
		sol.add(inicio.clone());
		cola.insertar(inicio,inicio.getDistancia()); //agregamos ese nodo
		int total=0;int distTotal=-1;
		
		while (cola.getSize()>0){ //mientras la cola no este vacia
			Node actual= (Node) cola.desencolarMinimo();
			
			System.out.println("Saque a: "+actual+" con costo "+actual.getCosto()+" y la distancia: "+actual.getDistancia());
			ListIterator<Node> sucesores= g.getSucs(actual.getId()).iterator();
			while (sucesores.hasNext()){
			//para los sucesores del nodo si no han sido visitados
			//se aumenta su distancia, se visitan y se agregan a la cola
				Node aux=sucesores.next();
				
				int dist=actual.getDistancia()+aux.getCosto();
				
				if ((dist<=aux.getDistancia()) || (aux.getDistancia()==-1)){
					
					if (dist<aux.getDistancia()||(aux.getDistancia()==-1)){//se encontro por 1era vez
						aux.setDistancia(dist);//cambia su distancia
						aux.unvisit();
						
						if (aux.equals(fin)) {
							distTotal=dist;
						}
					}
					
					if (!aux.isVisited(actual)){ //si no ha sido visitado por actual
						if (!actual.isVisited(aux)){// si actual no lo ha visitado
							if (!aux.isVisited()){
								sol.add(aux.clone());
								cola.insertar(aux,aux.getDistancia());//se agrega a la cola
							}
						}
						if (aux.getCosto()==0){
							aux.setVisit(actual); //se marca como visitado
							Edge e = new Edge(aux.getId(),actual.getId());
							sol.add(e); 
						} else {
							if (!actual.isVisited(aux)){
								aux.setVisit(actual);
								Edge e = new Edge(aux.getId(),actual.getId());
								sol.add(e);
							}
						}
					}
				}
			}
		}
		return distTotal; //no se encontro un camino de inicio a fin
	}
	

}