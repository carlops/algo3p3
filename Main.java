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
		LecturaYBusqueda(args[0],args[1]);
	}
	
	/**
	 * Metodo que lee el archivo con los laberintos, lo almacena en un grafo y
	 * llama a bfs para que lo recorre y retorna cual es la menor distancia 
	 * entre los nodos establecidos
	 */
    public static void LecturaYBusqueda(String in,String out) {
	try {
	    PrintStream fout = new PrintStream(out);
	    BufferedReader fin = new BufferedReader(new FileReader(in));
	    
		//se separan y guardan los enteros 
		String line = fin.readLine(); //linea con el numero de laberintos
		int laberintos = Integer.parseInt(line);// Numero de laberintos
		
		line = fin.readLine(); //linea con el numero de filas
		int filas =Integer.parseInt(line);// entero: # de filas
		
		line = fin.readLine(); //linea con el numero de columnas
		int columnas =Integer.parseInt(line);// entero: # de columnas
		
		int auxColumnas= 1;
		int auxFilas=1;
		Node nodo=null;
		Node nodoInicial=null;
		Node nodoFinal=null;
		
		// Para el ordenamiento de los nodos, nos basamos en un sistema
		// de coordenas tridimencional (x,y,z) siendo:
		// x: Numero de pisos
		// y: Numero de filas
		// z: Numero de columnas
		
		while ((laberintos!=0)&&(filas!=0)&&(columnas!=0)){//termina si los 3 enteros son 0
			Digraph grafo= new DigraphTablaDeHash();//inicializa el grafo por escenario
				while (auxFilas<filas+1){
					line = fin.readLine(); //cada fila es una nueva linea
						String[] e = line.split(" ");
					while (auxColumnas<columnas+1){
						//se recorre la linea caracter por caracter
						
// 						if ((line.charAt(auxColumnas-1)==' ')){
// 							como el caracter es '$' salta al siguiente caracter 
// 							auxColumnas++;
// 							continue;
// 						}
						
						//si no es '$' se crea y agrega el nuevo nodo al grafo
						//con id ( x(piso),y(fila),z(columna) ) 
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
// 							if (grafo.contains(vertice)){
								//el grafo contiene al nodo que esta justo en la 
								//fila de abajo, se hace la coneccion
								Edge lado = new Edge(nodo.getId(),vertice);
								grafo.add(lado);
								lado = new Edge(vertice,nodo.getId());
								grafo.add(lado);
// 							}
						}
						
						if (auxColumnas!=1){ //no hay columnas mas atras
							String vertice= auxFilas+","+(auxColumnas-1);
// 							if (grafo.contains(vertice)){
								//el grafo contiene al nodo que esta justo en la 
								//columna anterior, se hace la coneccion
								Edge lado = new Edge(nodo.getId(),vertice);
								grafo.add(lado);
								lado = new Edge(vertice,nodo.getId());
								grafo.add(lado);
// 							}
						}
						auxColumnas++;//siguiente columna
					}
					auxFilas++;//siguiente fila
					auxColumnas=1;
				}
				auxFilas=1;
				laberintos--;
			
			//Se recorre el grafo con bfs para encontrar la menor distancia 
			//entre nodoInicial y nodoFinal, si es 0 no hay un camino
			BFS busqueda= new BFS();
			int num= busqueda.bfs(grafo,nodoInicial,nodoFinal);
			
			if (num==0) fout.printf("%s\n", "Atrapado!");
			else fout.printf("%s\n", "Escape en "+num+" minuto(s).");

			System.out.println("\n"+grafo+"\n");
			
			if (laberintos!=0){
				//Se inicializa nuevamente para el siguiente ciclo
				line = fin.readLine();
	// 			e = line.split(" ");
	// 			pisos = Integer.parseInt(e[0]);
				filas =Integer.parseInt(line);
				
				line = fin.readLine();
				columnas =Integer.parseInt(line);
			}
			  
		}
		
		fin.close();
		fout.close();
		
	} catch (FileNotFoundException fnfe) {
		System.err.println("FileNotFoundException: "+fnfe.getMessage());
	}  catch (IOException ioe) {
	    System.err.println("IOException: "+ioe.getMessage());
	}
	}
	
 }
 
 