/**
 * Archivo: DigraphTablaDeHash
 * Decripcion: Clase concreta de DiGraph, se implementa el Digraph
 * 	utilizando una Tabla de Hash y e manejan las colisiones usando listas
 * 	dentro de la lista se almacena cada nodo y junto con ellos dos listas,
 * 	una con los edges de sus sucesores y otra con sus predecesores
 * Autor: Alejandro Guevara 09-10971 y Carlo Polisano 09-10672
 * Fecha: 18/11/13
 */
public class DigraphTablaDeHash<E> extends Digraph{
	
	private MyList<E> tabla[]; 
	private static int TAM = 201;
	
   /**
	* Construye un grafo vacio.
	*/
	public DigraphTablaDeHash() {
		super();
		this.tabla = new MyList[TAM];
		for (int i=0; i<TAM; i++) {
			this.tabla[i]= new MyList<E>(); // en este caso E es InfoNodo
		}
	}
	
   /**
	* Agrega la arista dada al grafo. Si los vertices de la arista
	* no existen o el grafo tiene una arista entre dichos vertices,
	* retorna false. Si se agrega la nueva arista, retorna true.
	* 
	* Complejidad: O(p + q), p << | V | y q << | E |
	*/
	public boolean add(Edge e){
		InfoNodo pre, suc;
		
		int hsuc = getPosition(e.getSrc()); //devuelve la pos en la tabla del nodo src
		suc=getInfoNodo(e.getSrc(),this.tabla[hsuc]);//se busca en la lista el nodo src
													//y devuelve su infonodo
		if (suc==null) 
			return false; //el nodo con src no esta
			
		int hpre = getPosition(e.getDst()); //devuelve la pos en la tabla del nodo src
		pre=getInfoNodo(e.getDst(),this.tabla[hpre]);//se busca en la lista el nodo dst
													//y devuelve su infonodo
		if (pre==null)
			return false; //el nodo con dst no esta
				
		if (this.contains(e.getSrc(),e.getDst())) 
			return false; // caso en que la arista ya esta
			
		// Agredando en Sucesores
		if (!suc.addSuc(e)){
			System.out.println("\n Error agregando "+ e.getDst() + 
			" como sucesor de " + e.getSrc());
			return false;
		}
		// Agredando en Predecesores
		if (!pre.addPre(e)){
			System.out.println("\n Error agregando "+ e.getSrc() + 
			" como sucesor de " + e.getDst());
			return false;
		}
		numEdges++;
		return true;
		
	}

   /**
	* Agrega el nodo n. Si el vertice ya existe, retorna false. Si
	* se agrega el nodo, retorna true.
	*
	* Complejidad: O(p), p << | V |
	*/
	public boolean add(Node n){
		E aux = (E) new InfoNodo(n);
		if (this.contains(n.getId())) 
			return false; //caso en que ya contiene el nodo
		int h = getPosition(n.getId());
		this.tabla[h].add(aux); //Se agrega en la lista de su pos en la tabla de hash
		numVertices++;
		return true;
	}

   /**
	* Elimina los nodos y aristas del grafo.
	* 
	* Complejidad: O(1)
	*/
	public void clear(){
		for (int i=0; i<TAM; i++) {
			this.tabla[i]= null;
		}
	}

   /**
	* Retorna un nuevo grafo que es una copia del grafo actual.
	* 
	* Complejidad: O(|V| + |E|)
	*/
	public Object clone(){
		if (this.numVertices==0) 
			return this;//Grafo a clonar vacio
		// Se crea el nuevo Digraph
		DigraphTablaDeHash Clon = new DigraphTablaDeHash();
		Clon.numEdges = this.numEdges;
		Clon.numVertices = this.numVertices;
		for (int i=0; i<TAM; i++){
			if (this.tabla[i].getHead()!=null){
				Clon.tabla[i] = this.tabla[i].clone(); //se clonan todas las listas
			}
		}
		return Clon;
	}

   /**
	* Chequa que el grafo contiene una arista (src, dst).
	* 
	* Complejidad: O(p), p << | E |
	*/
	public boolean contains(String src, String dst){
		int hsuc = getPosition(src);
		InfoNodo aux = getInfoNodo(src,this.tabla[hsuc]);//se busca en la lista el 
													//nodo src y devuelve su infonodo
		Edge arista= new Edge(src,dst); //crea la arista para poder comparar mejor
		if (aux!=null)
			return aux.containsSuc(arista);
		return false;
	}

   /**
	* Chequa que el grafo contiene una nodo con id nod
	*
	* Complejidad: O(p), p << | V |
	*/
	public boolean contains(String nod) {
		int h = getPosition(nod);
		Node n = new Node(nod);
		E aux = (E) new InfoNodo(n);
		if (this.tabla[h].contains(aux))
			return true;
		else 
			return false;
	}

   /**
	* Retorna la arista del grafo que conecta a los vertices
	* src y dst. Si no existe dicha arista, retorna null.
	* 
	*  Complejidad: O(p), p << | E |
	*/
	public Edge get(String src, String dst){
		int hsuc = getPosition(src);
		InfoNodo suc = getInfoNodo(src,this.tabla[hsuc]);
		//suc tiene el infonodo que tiene a src dentro del grafo
		if (suc==null)//El vertice fuente no existe
			return null;
		//Recorro la lista de sucesores buscando la arista
		ListIterator<Edge> iter = suc.getSuc().iterator();
		while (iter.hasNext()){
			Edge aristaAux = (Edge) iter.next(); 
			if (aristaAux.getDst().equals(dst))	
				// si el dst de la lista de sucesores del nodo src es igual
				// al que se pasa a este metodo
				return aristaAux;
		}
		return null;
	}

   /**
	* Retorna todas las aristas del grafo
	*
	* Complejidad: O(???)
	*/
	public List<Edge> getEdges(){
		MyList <Edge> todosLados= new MyList();
		for (int i=0; i<TAM; i++) {
		//se recorre cada casilla de la tabla de hash
			Caja aux=this.tabla[i].getHead(); 
			//recorremos las listas de colisiones
			while (aux!=null) {
				InfoNodo aux2 = (InfoNodo) aux.getDato();//obtengo cada infonodo
				if (aux2.getSuc()!=null){
					todosLados.concatenate((MyList<Edge>) aux2.getSuc());
					//se van concatenando las listas de sucesores
				}
				aux=aux.getSig();//siguente nodo en la lista de colisiones
			}
		}
		return todosLados;
	}
	
   /**
	* Retorna el nodo con id nod. Si no existe dicho nodo, 
	* retorna null.
	*
	* Complejidad: O(p), p << |V|
	*/
	public Node get(String nod){
		int h= getPosition(nod);
		InfoNodo aux=getInfoNodo(nod,this.tabla[h]);//se busca en la lista el
													//nodo nod y devuelve su infonodo
		if (aux!=null)
			return ((Node) aux.getDato());
		return null;
	}

   /** 
	* Retorna todos los nodos del grafo.
	*
	* Complejidad: O(???)
	*/
	public List<Node> getNodes(){
		List <Node> todosNodos= new MyList();
		for (int i=0; i<TAM; i++) {
			//se recorre todq la tabla
			Caja aux=this.tabla[i].getHead();
			while (aux!=null) {
				//se recorre la lista y se agrega cada nodo a todosNodos
				InfoNodo aux2 = (InfoNodo) aux.getDato();
				todosNodos.add( (Node) aux2.getDato());
				aux=aux.getSig();
			}
		}
		return todosNodos;
	}

   /**
	* Retorna el numero de aristas en el grafo.
	*
	* Complejidad: O(1)
	*/
	public int getNumEdges() {
		return numEdges;
	}

   /**
	* Retorna el numero de vertices en el grafo.
	* 
	* Complejidad: O(1)
	*/
	public int getNumVertices() {
		return numVertices;
	}

   /**
	* Retorna la lista de lados que tienen al vertice dado como
	* destino. Si el vertice no existe, retorna null.
	* 
	* Complejidad: O(p), p << | E |
	*/
	public List<Edge> getInEdges(String node){
		int h = getPosition(node);
		InfoNodo pre=getInfoNodo(node,this.tabla[h]);//se busca en la lista el 
													//nodo node y devuelve su infonodo
		if (pre==null)
			return null;
		return pre.getPre();//retorna la lista de los predecesores de node
	}

   /**
	* Retorna los predecesores del nodo con id node
	* 
	* Complejidad: O(p), p << | E |
	*/
	@SuppressWarnings("unchecked")
	public List<Node> getPreds(String node){
		if (!contains(node))
			return null;
		
		List<Edge> inEdges = getInEdges(node);
	
		if (inEdges == null)
			return null;
		
		ListIterator<Edge> li = inEdges.iterator();
		List<Node> preds = (List<Node>) new MyList();
		
		while (li.hasNext()) {
			Edge e = li.next();
			String sid = e.getSrc();
			preds.add(this.get(sid));
		}
		return preds;
	}

	/**
	* Retorna la lista de lados que tienen al vertice dado como
	* origen. Si el vertice no existe, retorna null.
	*
	* Complejidad: O(p), p << | E |
	*/
	public   List<Edge> getOutEdges(String node){
		int h = getPosition(node);
		InfoNodo suc= getInfoNodo(node,this.tabla[h]);//se busca en la lista el 
												//nodo node y devuelve su infonodo
		if (suc==null)
			return null;
		return suc.getSuc();//retorna la lista de los sucesores de node
	}

	/**
	* Retorna los sucesores del nodo con id node
	*
	* Complejidad: O(p), p << | E |
	*/
// 	@SuppressWarnings("unchecked")
	public  List<Node> getSucs(String node){
	if (!contains(node))
		return null;
	List<Edge> outEdges = getOutEdges(node);
	if (outEdges == null)
		return null;

	ListIterator<Edge> li = outEdges.iterator();
	List<Node> sucs = new MyList();

	while (li.hasNext()) {
		Edge e = li.next();
		String sid = e.getDst();

		sucs.add(this.get(sid));
	}
		
	return sucs;
	}

	/**
	* Retorna el in-degree del vertice dado. Si el
	* vertice no existe, retorna -1.
	*
	* Complejidad: O(p), p << | E |
	*/
	public int getInDegree(String node)  {
		int h = getPosition(node);
		InfoNodo aux = getInfoNodo(node,this.tabla[h]);
		if (aux!=null) return aux.getPreSize();//retorna el tam de la lista 
											//de predecesores de node
		else return -1;
		
	}
	/**
	* Retorna el out-degree del vertice dado. Si el
	* vertice no existe, retorna -1.
	*
	* Complejidad: O(p), p << | E |
	*/
	public int getOutDegree(String node) {
		int h = getPosition(node);
		InfoNodo aux = getInfoNodo(node,this.tabla[h]);
		if (aux!=null) return aux.getSucSize();//retorna el tam de la lista 
											//de sucesores de node
		else return-1;
	}

	/**
	* Remueve la arista del grafo que conecta a los vertices src y
	* dst. Si el grafo no cambia, retorna false. Si el grafo cambia,
	* retorna true.
	* 
	* Complejidad: O(p), p << | E |
	*/
	@Override
	public   boolean remove(String src, String dst){
		Edge arista= new Edge(src,dst); 
		
		int hsuc = getPosition(src);
		InfoNodo suc=getInfoNodo(src,this.tabla[hsuc]);
		if (suc==null)
			return false;//no esta el nodo con src
		if (!suc.removeSuc(arista))
			return false;//si no se pudo quitar la arista de la lista 
						//de sucesores de src 
		
		int hpre = getPosition(dst);
		InfoNodo pre=getInfoNodo(dst,this.tabla[hpre]);
		if (pre==null) 
			return false; //no esta el nodo con dst
		if (!pre.removePre(arista))
			return false;//si no se pudo quitar la arista de la lista 
						//de predecesores de dst 
		
		numEdges--;
		return true;
	}

	/**
	* Remueve el nodo del grafo con id nod. Si el grafo no cambia,
	* retorna false. Si el grafo cambia, retorna true.
	*
	* Complejidad: O(p), p << | V |
	*/
	@Override
	public   boolean remove(String nod){
		int h = getPosition(nod);
		InfoNodo NodoEliminar= getInfoNodo(nod,this.tabla[h]);
		if (NodoEliminar==null) // Nodo a eliminar no existe
			return false;
			
		// Primero se eliminaran las aristas del nodo
		
		/* Se Recorrera la lista de sucesores del nodo a eliminar,
		* buscando el nodo destino de cada arista en el grafo , 
		* para asi eliminar en este, de su lista de predecesores, 
		* la arista con el nodo a eliminar.
		*/
		// Lista de Sucesores en suc
		ListIterator<Edge> suc=NodoEliminar.getSuc().iterator();
		// Se recorrera suc metiendo en 'e' como auxiliar
		while (suc.hasNext()){
			Edge e = suc.next();
			//Se eliminan las aristas del grafo
			if (!remove(e.getSrc(),e.getDst())){
			    System.out.println("\nError Eliminando "+e);
			    return false;
			}
		}
			//Se hace lo mismo Con la lista de predecesores
		ListIterator<Edge> pre=NodoEliminar.getPre().iterator();
		while (pre.hasNext()){
			Edge e2 = pre.next();
			if (!remove(e2.getSrc(),e2.getDst())){
			    System.out.println("\nError Elimando "+e2);
			    return false;
			}
		}
		// Por ultimo se elimina el nodo del grafo
		if (!this.tabla[h].remove((E) NodoEliminar)){
			System.out.println("Error Eliminando el nodo");
			return false;
		}
		numVertices--;
		return true;
	}

	/**
	* Construye una representacion en String del grafo.
	*/
	@Override
	public String toString() {
		String ret = numVertices + ":" + numEdges +"\n";
		
		ListIterator<Node> nods = getNodes().iterator();
		while (nods.hasNext()) {
			Node n = nods.next();
			ret += "\n" + n.toString();
		}
		
		ListIterator<Edge> edgs = getEdges().iterator();
		
		while (edgs.hasNext()) {
			Edge e = edgs.next();
			ret += "\n" + e.toString();
		}       
		return ret;
	}
	
	/** ----------- Funciones Adicionales ------------ 
	* Funciones adicionales a las requeridas por implementar
	*/
        
   /**
	* Retorna la posicion en la tabla de hash del elemento
	* (Parametro de entrada String podria cambiarse por Object)
	*/
        private int getPosition(String n){
			int h = (n.hashCode()) % TAM ;
			if (h<0) h=-h;
			return h;
        }
        
   /**
	* Retorna el InfoNodo de la clave proporsionada en la lista dada
	*/
        private InfoNodo getInfoNodo(String n, MyList list){
			Node fuente = new Node(n);
			InfoNodo aux = new InfoNodo(fuente);
			return ((InfoNodo) list.getElem(aux));
        }
        /** ------------ Fin de Funciones Adicionales ------------ */
        
	
}

// End Digraph.java
