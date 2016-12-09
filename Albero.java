import java.io.IOException;
import java.util.LinkedList;
import java.util.TreeMap;

public class Albero {

	private int Depth;
	private int SplitSize;
	private int idAlbero;
	
	public Albero(int depth,int SplitSize, int idAlbero, LinkedList<String> attrNode, LinkedList<String> attrEdge, int N, int K) throws IOException{
		
		this.Depth=depth;
		this.SplitSize=SplitSize;
		this.idAlbero = idAlbero;
		
		TreeMap<Integer,LinkedList<Integer>> nodi = this.creaNodi(this.SplitSize, this.Depth, attrNode, K, N);
		
		this.creaArchi(nodi, attrEdge, K, N);
		
	}
	
	public TreeMap<Integer,LinkedList<Integer>> creaNodi(int SplitSize, int Depth, LinkedList<String> attrNode, int K, int N) throws IOException{
		
		CreaCSV csv = new CreaCSV();
		TreeMap<Integer,LinkedList<Integer>> archi = csv.csvNodi(this.idAlbero, SplitSize, Depth, attrNode, K, N);
		return archi;
		
	}
	
	public boolean creaArchi(TreeMap<Integer,LinkedList<Integer>> nodi, LinkedList<String> attrEdge, int K, int N) throws IOException{
		
		CreaCSV csv = new CreaCSV();
		
		boolean creato = csv.csvArchi(this.idAlbero, nodi, attrEdge, K, N);
		
		return creato;
		
		
	}
	
	public static void main(String[]args) throws IOException{
		
		LinkedList<String> b = new LinkedList<String>();
		b.add("att1");
		b.add("att2");
		b.add("att3");
		b.add("att4");
		b.add("att5");
		b.add("att6");
		
		LinkedList<String> c = new LinkedList<String>();
		c.add("att1");
		c.add("att2");
		c.add("att3");
		c.add("att4");
		
		
		Albero a = new Albero(7,6,1,b,c,40,2);
		
	}
	
	
	
}
