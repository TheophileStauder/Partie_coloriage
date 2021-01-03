import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Coloriage {
    private Graph graph;

    public Coloriage(Graph graph){
        this.graph = graph;
    }

    public Graph colore (){
        Graph res = graph;
        ArrayList<Vertice> coloriedVertice =  new ArrayList<>(res.vertices());
        /*int [] verticesDegree = new int[res.vertices()];
        Integer [] verticesDegreeByOrder  = new Integer[res.vertices()];
        for( int i = 0; i < verticesDegree.length;i++){
            verticesDegree[i] = res.getDegree(i);
            verticesDegreeByOrder[i] = res.getDegree(i);
        }
        Arrays.sort(verticesDegreeByOrder, Collections.reverseOrder());*/
        ArrayList<Vertice> vertices = new ArrayList<>();
        for( int i = 0; i < res.vertices();i++){
            Vertice v = new Vertice(i);
            v.setDegree(res.getDegree(i));
            vertices.add(v);
        }
        vertices = sortVerticesByDegree(vertices);
        int indiceColor = 0;


        boolean coloriable = true;
        while(coloriedVertice.size() < res.vertices() && coloriable){

            String currentColor = Graph.COLORS.get(indiceColor);
            for(Vertice v : vertices){
                if(!coloriedVertice.contains(v)){
                    boolean canBeColored = true;
                    for(Edge e : res.adj(v.getNumber())){
                        int voisin = e.other(v.getNumber());
                        //DEBUG
                        // System.out.println("Je suis le sommet " +  v.getNumber() + " mon voisin est " + voisin  +" ("+ res.getColors()[voisin]+")");
                        if(res.getColors()[voisin].equals(currentColor)){canBeColored = false;}
                    }
                    if(canBeColored){
                        coloriedVertice.add(v);
                        res.setColor(v.getNumber(),currentColor);
                        //System.out.println("COLORED VERTICE " + v.getNumber() + " IN " + currentColor); //debug
                    }else{
                        if(indiceColor == 2){coloriable = false;}
                    }

                }
            }


            indiceColor++;
        }

        return res;
    }

    public ArrayList<Vertice> sortVerticesByDegree(ArrayList<Vertice> vertices){
        vertices.sort(Comparator.comparing(Vertice::getDegree));
        Collections.reverse(vertices);
        return vertices;
    }

}