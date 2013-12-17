/**
 * Archivo: Main.java 
 * Descripcion: Aplicacion que recibe dos argumentos por consola, el primero 
 *  es el nombre del archivo de entrada y el segundo el nombre del archivo de
 *  salida, la aplicacion lee el archivo de entrada que contiene una serie de
 *  laberintos, en donde hay un inicio y un fin, luego determina el tiempo mas
 *  corto en el que se puede escapar(cada movimiento cuesta 1 seg), o si es 
 *  que no se puede escapar
 * Autor: Alejandro Guevara (09-10971) y Carlo Polisano (09-10672) Grupo 10
 * Fecha: 30/11/2013
 **/
 
 import java.io.*;

 public class Main {
 
	public static void main(String args[]) {
		try{
			BufferedReader in = new BufferedReader(new FileReader(args[0]));
			PrintStream out = new PrintStream(args[1]);

			String linea1 = in.readLine(); //linea con el numero de laberintos
			int laberintos = Integer.parseInt(linea1);// Numero de laberintos
			
			Digraph grafo=null;
			
			while (laberintos>0) {
				MyList lista = new MyList();
				grafo = Lectura(in,lista);
				
				int num[] = new int[2];
				num = Djkstra(grafo,lista);

				Salida(out,num);
				laberintos--;
			}
		in.close();
		out.close();
			
			
			
		} catch (FileNotFoundException fnfe) {
		System.err.println("FileNotFoundException: "+fnfe.getMessage());
		}   catch (IOException ioe) {
	    System.err.println("IOException: "+ioe.getMessage());
		}
	}
	
	/**
	 * Metodo que lee el archivo con los laberintos, lo almacena en un grafo y
	 * llama a bfs para que lo recorre y retorna cual es la menor distancia 
	 * entre los nodos establecidos
	 */
    public static Digraph Lectura(BufferedReader fin,List lista) {
	try {
		String line = fin.readLine(); //linea con el numero de filas
		int filas =Integer.parseInt(line);// entero: # de filas
		
		line = fin.readLine(); //linea con el numero de columnas
		int columnas =Integer.parseInt(line);// entero: # de columnas
		
		int auxColumnas= 1;
		int auxFilas=1;
		Node nodo=null;
		Node nodoInicial=null;
		Node nodoFinal=null;
		
		Digraph grafo= new DigraphTablaDeHash();
		while (auxFilas<filas+1){
			line = fin.readLine(); //cada fila es una nueva linea
			String[] e = line.split(" ");//los costos estan separados por " "
			while (auxColumnas<columnas+1){
				int costo = Integer.parseInt(e[auxColumnas-1]);
				nodo= new Node(auxFilas+","+auxColumnas, costo);
				grafo.add(nodo);
				if ((auxFilas==1)&&(auxColumnas==1)){
					nodoInicial=nodo;//se guarda cual es el nodo inicial
				}
				if ((auxColumnas==columnas)&&(auxFilas==filas)){
					nodoFinal=nodo;//se guarda cual es el nodo final
				}
				
				if (auxFilas!=1){//no hay filas mas arriba
					String vertice= (auxFilas-1)+","+auxColumnas;
						Edge lado = new Edge(nodo.getId(),vertice);
						grafo.add(lado);
						lado = new Edge(vertice,nodo.getId());
						grafo.add(lado);
				}
				
				if (auxColumnas!=1){ //no hay columnas mas atras
					String vertice= auxFilas+","+(auxColumnas-1);
						Edge lado = new Edge(nodo.getId(),vertice);
						grafo.add(lado);
						lado = new Edge(vertice,nodo.getId());
						grafo.add(lado);
				}
				auxColumnas++;//siguiente columna
			}
			auxFilas++;//siguiente fila
			auxColumnas=1;
		}
		lista.add(nodoInicial);
		lista.add(nodoFinal);
		return grafo;
		
	}   catch (IOException ioe) {
	    System.err.println("IOException: "+ioe.getMessage());
	    return null;
	}
	}
	
	
	public static int[] Djkstra(Digraph grafo,MyList lista){
		//Se recorre el grafo con bfs para encontrar el camino de menor costo 
		//entre nodoInicial y nodoFinal, si es 0 no hay un camino
		BFS busqueda= new BFS();
		Node nodoInicial =(Node)lista.get(0);
		Node nodoFinal =(Node) lista.get(1);
		Digraph grafoSol = new DigraphTablaDeHash();
		int num[] = new int[2]; 
		num[0] =busqueda.bfsCosto(grafo,nodoInicial,nodoFinal,grafoSol);
		
		System.out.println("Grafo Sol:\n"+grafoSol+"\n");
		num[1] =busqueda.bfsCaminos(grafoSol,nodoFinal,nodoInicial);
		
		return num;
	}
	
	public static int Caminos2(Node nodo,Node nfin,MyList lista){
		Node aux;
// 		int distancia =nodo.getDistancia()-nodo.getCosto();
		int num =nodo.getVisitas().getSize();
// 		System.out.println(""+nodo+"\n\t"+ nodo.getVisitas());
		if (nodo.equals(nfin))
			return 1;
			
		
		if (lista.contains(nodo))
			return 0;
			
		num=0;
		lista.add(nodo);
		System.out.println("lista: "+ lista);
		ListIterator iter = nodo.getVisitas().iterator();
		while (iter.hasNext()){
			aux=(Node)iter.next();
// 			aux.setDistancia(nodo.getDistancia()-nodo.getCosto());
			num = num+Caminos2(aux,nfin,lista.clone());
			System.out.println("soy "+aux+"\n\t");
		}
		return num;
	}
		
	public static void Salida(PrintStream fout,int[] num){
		fout.printf("%d %d\n",num[1],num[0]);

	}
	
 }
 
 