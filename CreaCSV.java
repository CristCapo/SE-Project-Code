import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;
import java.util.TreeMap;
import java.util.Random;



public class CreaCSV {
	
	public int randomGenerate(int K, int N){
		Random random=new Random();
		int tmp= (N - K) + 1;
		int res= random.nextInt(tmp) + K;
		return res;
	}
	
	public TreeMap<Integer,LinkedList<Integer>> csvNodi(int IDAlbero, int splitsize, int depth, LinkedList<String> attrNode, int K, int N) throws IOException{
		
		String file="C:\\Users\\Cristian\\Documents\\Neo4j\\default.graphdb\\Import\\nodiCsv.csv";
		
		FileWriter writer = new FileWriter(file);	
		
		String a=IDAlbero + "," + "ID,Vertex";
		
		for(String b: attrNode){
			
			a+= "," + b;
			
		}
		
		a+='\n';
		
		writer.write(a);
		
		TreeMap<Integer,LinkedList<Integer>> nodLiv = new TreeMap<Integer,LinkedList<Integer>>();
		
		TreeMap<Integer,LinkedList<Integer>> nodPad = new TreeMap<Integer,LinkedList<Integer>>();
		
		LinkedList<Integer> nodi = new LinkedList<Integer>();
		
		a=IDAlbero + "," + 0 + ",Vertex0";
		
		for (String b: attrNode){
			
			a+=","+randomGenerate(K,N); 
		
		}
		a+="\n";
		writer.write(a);
		nodi.add(0);
		
		nodLiv.put(0, nodi);
		
		for(int i=1;i<depth;i++){
			
			LinkedList<Integer> tmp = new LinkedList<Integer>();
			
			
			
			for(int x: nodLiv.get(i-1)){
				
				LinkedList<Integer> padri = new LinkedList<Integer>();
				
				for(int j=1;j<=splitsize;j++){
					a="";
					int padre=(x*splitsize)+j;
					
					a+=IDAlbero + "," + padre+",Vertex"+padre;
					
					for (String b: attrNode){
						
						a+=","+randomGenerate(K,N); 
					
					}
					
					a+='\n';
					
					writer.write(a);
					
					padri.add(padre);
					
					tmp.add(padre);
					
				}
				nodPad.put(x, padri);
				
			}
			
			nodLiv.put(i,tmp);
			
		
		
		}
		
		System.out.println(nodPad);
		System.out.println(nodLiv);
		
		
		writer.flush();
		writer.close();
		
		return nodPad;
	}
	
	public boolean csvArchi(int IdAlbero, TreeMap<Integer,LinkedList<Integer>> nodi, LinkedList<String> attrEdge, int K, int N) throws IOException{

		String dir="C:\\Users\\Cristian\\Documents\\Neo4j\\default.graphdb\\Import\\archicsv.csv";
		
		FileWriter writer=new FileWriter(dir);
		
		LinkedList<String> c=attrEdge;
		
		String a=IdAlbero + "," + "ID,StartVertex,EndVertex";
		
		for(String b: attrEdge){
			
			a+= "," + randomGenerate(K,N);
			
		}
		
		a+= "\n";
		writer.write(a);
		
		
		for(int figlio: nodi.keySet()){
			
			
			
			for(int padre: nodi.get(figlio)){
				a="";
				a=padre + "," + padre + "," + figlio;
				
				for(String b: attrEdge){
					
					a+= "," + randomGenerate(K,N);
					
				}
				
				a+="\n";
				writer.write(a);
				
			}
			
			
			
		}
			
		writer.flush();
		writer.close();
		
		return true;
	
	}
		
	
	
	
	
	
}
