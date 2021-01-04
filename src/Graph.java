import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.io.*;
import java.awt.*;
import java.awt.image.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Graph{
   private ArrayList<Edge>[] adj;
   private int[] coordX;
   private int[] coordY;
   private int V;
   private int E;
   static ArrayList<String> COLORS = new ArrayList<>();
   private String[] verticesColors;

   public Graph(int N)
         {
		 List<String> colorsList = Arrays.asList("RED","BLUE","GREEN");
		 COLORS.addAll(colorsList);
	     this.V = N;
	     this.E = 0;
	     verticesColors = new String[N];
	     initialiseVerticesColors();
	     adj = (ArrayList<Edge>[]) new ArrayList[N];
	     for (int v= 0; v < N; v++)
		 adj[v] = new ArrayList<Edge>();
	     coordX = new int[N];
	     coordY = new int[N];
	     for (int v= 0; v < N; v++)
		 coordX[v] = 0;
	     for (int v= 0; v < N; v++)
		 coordY[v] = 0;
         }

 	public Graph(String fileName)  {
		List<String> colorsList = Arrays.asList("RED","BLUE","GREEN");
		COLORS.addAll(colorsList);
   		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		if(classLoader != null){
			classLoader.getResource(fileName).getFile();
		}
		URL url = this.getClass().getResource(fileName);
		File file = null;
		try {
			file = new File(url.toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		Scanner myReader = null;
		try {
			myReader = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		int ligne = -1;
		int colonne = 0;
		while (myReader.hasNextLine()) {
			String data = myReader.nextLine();
			if(ligne==-1){
				this.V = Integer.parseInt(data);
				verticesColors = new String[V];
				this.E = 0;
				adj = (ArrayList<Edge>[]) new ArrayList[V];
				int [] verticesColor = new int[V];
				initialiseVerticesColors();
				for (int v= 0; v < V; v++) {
					adj[v] = new ArrayList<Edge>();
					coordX = new int[V];
					coordY = new int[V];
				}
			}else{
				for (int i = 0; i < data.length(); i++){
					char c = data.charAt(i);
					int bit = Character.getNumericValue(c);
					//System.out.println(bit); //DEBUG
					if(bit == 1){
						if(getSpecificEdge(ligne,i).equals(new Edge(-1,-1))) {
							addEdge(new Edge(ligne, i));
						}
					}
					colonne++;
				}
			}

			colonne = 0;
			ligne ++;

		}
		myReader.close();



		/*System.out.println("Nombre de sommet : " + vertices());
		for (Edge e : edges()){
			System.out.println(e.toString());  //DEBUG
		}*/
		int x,y;
		x = y = 100;
		for(int i = 0 ; i < vertices();i++){
			setCoordinate(i,x,y);
			x = x + 100;
			if(i%3 ==0){y = y +100;x = 100;}
		}
	}
    
   public int vertices()
         {
                return V;
         }

    public void setCoordinate(int i, int x, int y)
         {
	     coordX[i] = x;
	     coordY[i] = y;
         }
    
    
   public void addEdge(Edge e)
         {
                int v = e.from;
                int w = e.to;
                adj[v].add(e);
                adj[w].add(e);
         }

   public ArrayList<Edge> adj(int v)
         {
	     return new ArrayList<Edge>(adj[v]);
         }

   public ArrayList<Edge> edges()
    {
	ArrayList<Edge> list = new ArrayList<Edge>();
        for (int v = 0; v < V; v++)
            for (Edge e : adj(v)) {
                if (e.from == v)
                    list.add(e);
            }
        return list;
    }

    public Edge getSpecificEdge(int u , int v){
   		for (Edge e :edges()){
   			if((u == e.getFrom() && v == e.getTo()) || (u == e.getTo() && v == e.getFrom())){
   				return e;
			}
		}
   		return new Edge(-1,-1);
	}

	public int getDegree(int v){
   		return adj[v].size();
	}

	public void setColor(int u ,String c){
   		verticesColors[u] = c;
	}

	public String[] getColors(){
   		return verticesColors;
	}

    static Graph example(){
	Graph g = new Graph(4);
	g.setCoordinate(0, 100,100);
	g.setCoordinate(1, 300,300);
	g.setCoordinate(2, 300,100);
	g.setCoordinate(3, 100,300);
	g.addEdge(new Edge(0,1));
	g.addEdge(new Edge(0,2));
	g.addEdge(new Edge(0,3));
	g.addEdge(new Edge(1,2));
	g.addEdge(new Edge(1,3));
	return g;
    }


    static Graph Grid(int n){
	Graph g = new Graph(n*n);
	int i,j;
	for (i = 0 ; i < n; i ++) 
	    for (j = 0 ; j < n; j ++) 
		g.setCoordinate(n*i+j, 50+(300*i)/n,50+(300*j)/n);

	for (i = 0 ; i < n; i ++) 
	    for (j = 0 ; j < n; j ++){
		if (i < n-1) 
		    g.addEdge(new Edge(n*i+j,n*(i+1)+j));
		if (j < n-1) 
		    g.addEdge(new Edge(n*i+j,n*i+j+1));
	    }
	return g;
    }
    

    public BufferedImage toImage(){
	BufferedImage image = new BufferedImage(400, 400, BufferedImage.TYPE_INT_RGB);
	Graphics2D g2d = image.createGraphics();
	g2d.setBackground(Color.WHITE);
	g2d.fillRect(0, 0, 400, 400);
	g2d.setColor(Color.BLACK);
	BasicStroke bs = new BasicStroke(2);
	g2d.setStroke(bs);
	// dessine les arêtes 
	for (Edge e: edges())
	    {
		int i = e.from;
		int j = e.to;
		if (e.used)
		    g2d.setColor(Color.RED);
		else
		    g2d.setColor(Color.GRAY);
		    
		g2d.drawLine(coordX[i], coordY[i], coordX[j], coordY[j]);
	    }
	// dessine les sommets 
	for (int i = 0; i < V; i++)
	    {
		g2d.setColor(Color.WHITE);
		g2d.fillOval(coordX[i]-15, coordY[i]-15,30,30);
		g2d.setColor(Color.BLACK);
		g2d.drawOval(coordX[i]-15, coordY[i]-15,30,30);
		g2d.drawString(Integer.toString(i), coordX[i], coordY[i]);
	    }

	return image;
    }

    public boolean is3Color(){
   		for(int i = 0 ; i < vertices();i++){
   			String color = verticesColors[i];
   			for(Edge e : adj(i)){
				int voisin = e.other(i);
   				if(verticesColors[voisin].equals(color)){return false;}
			}
		}
   		return true;
	}

	public Edge getConflictEdge(){
		for(int i = 0 ; i < vertices();i++){
			String color = verticesColors[i];
			for(Edge e : adj(i)){
				int voisin = e.other(i);
				if(verticesColors[voisin].equals(color)){return e;}
			}
		}
		return new Edge(-1,-1);
	}

	public void afficheCouleursSommet(){
   		for(int i = 0 ; i < verticesColors.length;i++ ){
			System.out.println("SOMMET " + i + " : " + verticesColors[i]);
		}
	}
    public void writeFile(String s)
    {
	try
	    {                      
		PrintWriter writer = new PrintWriter(s, "UTF-8");
		writer.println("digraph G{");
		for (Edge e: edges())
		    writer.println(e.from + "->" + e.to+";");
		writer.println("}");
		writer.close();
	    }
	catch (IOException e)
	    {
	    }                                             
    }

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Graph graph = (Graph) o;
		return V == graph.V &&
				E == graph.E &&
				Arrays.equals(adj, graph.adj) &&
				Arrays.equals(coordX, graph.coordX) &&
				Arrays.equals(coordY, graph.coordY);
	}

	public void initialiseVerticesColors() {
		for (int i = 0; i < getColors().length; i++) {
			getColors()[i] = "none";
		}
	}

	public void afficheCube(){
   		for(Edge e : edges()){
			System.out.println(e.toString());
		}
	}

}
