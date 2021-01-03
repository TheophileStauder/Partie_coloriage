import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Coloriage {
    private Graph graph;

    public Coloriage(Graph graph){
        this.graph = graph;
    }

    public Graph colore (){
        Graph res = graph;
        ArrayList<Integer> coloriedVertice =  new ArrayList<>();
        int [] verticesDegree = new int[res.vertices()];
        Integer [] verticesDegreeByOrder  = new Integer[res.vertices()];
        for( int i = 0; i < verticesDegree.length;i++){
            verticesDegree[i] = res.getDegree(i);
            verticesDegreeByOrder[i] = res.getDegree(i);
        }
        Arrays.sort(verticesDegreeByOrder, Collections.reverseOrder());
        int indiceColor = 0;




        /*for( int i = 0; i < verticesDegree.length;i++){
            System.out.println(verticesDegreeByOrder[i]);
        }*/
        while(coloriedVertice.size() < res.vertices()){
            String currentColor = Graph.COLORS.get(indiceColor);
            for( int i = 0; i < verticesDegree.length - coloriedVertice.size()-1;i++){
                verticesDegreeByOrder[i] = verticesDegreeByOrder[i+1];
            }

            indiceColor++;
        }



        /*for( int i = 0; i < verticesDegree.length;i++){
            System.out.println(verticesDegreeByOrder[i]);
        }*/
        //enelever la premiere valeur , reconstruire le tableau
        //chercher dans la liste des sommets lequel est pas utilisé
        return res;
    }

}
