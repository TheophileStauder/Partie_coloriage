import java.io.*;


import java.util.*;
public class Test {


	public static void printLaby(Graph G, int size, String file) {
		{
	/* suppose que G est une grille de taille size x size et 
           crée un .tex qui contient le labyrinthe correspondant */

			try {
				PrintWriter writer = new PrintWriter(file, "UTF-8");
				writer.println("\\documentclass{article}\\usepackage{tikz}\\begin{document}");
				writer.println("\\begin{tikzpicture}");

				for (int i = 0; i < size; i++)
					for (int j = 0; j < size; j++) {
						writer.println(String.format(Locale.US, "\\begin{scope}[xshift=%dcm, yshift=%dcm]", i, j));
						writer.println("\\draw (0.1,0.1) -- (0.4,0.1);");
						writer.println("\\draw (0.6,0.1) -- (0.9,0.1);");
						writer.println("\\draw (0.1,0.9) -- (0.4,0.9);");
						writer.println("\\draw (0.6,0.9) -- (0.9,0.9);");
						writer.println("\\draw (0.1,0.1) -- (0.1, 0.4);");
						writer.println("\\draw (0.1,0.6) -- (0.1, 0.9);");
						writer.println("\\draw (0.9,0.1) -- (0.9,0.4);");
						writer.println("\\draw (0.9,0.6) -- (0.9,0.9);");
						writer.println("\\end{scope}");
					}

				/* bord */
				for (int i = 0; i < size; i++) {
					writer.println(String.format(Locale.US, "\\begin{scope}[xshift=%dcm, yshift=%dcm]", i, 0));
					writer.println("\\draw(0.4,0.1) -- (0.6, 0.1);");
					writer.println("\\end{scope}");
					writer.println(String.format(Locale.US, "\\begin{scope}[xshift=%dcm, yshift=%dcm]", i, size - 1));
					writer.println("\\draw(0.4,0.9) -- (0.6, 0.9);");
					writer.println("\\end{scope}");
					if (i > 0) {
						writer.println(String.format(Locale.US, "\\begin{scope}[xshift=%dcm, yshift=%dcm]", 0, i));
						writer.println("\\draw(0.1,0.4) -- (0.1, 0.6);");
						writer.println("\\end{scope}");

					}
					if (i < size - 1) {
						writer.println(String.format(Locale.US, "\\begin{scope}[xshift=%dcm, yshift=%dcm]", size - 1, i));
						writer.println("\\draw(0.9,0.4) -- (0.9, 0.6);");
						writer.println("\\end{scope}");

					}
					writer.println("\\draw (0,0.4) -- (0.1, 0.4);");
					writer.println("\\draw (0,0.6) -- (0.1, 0.6);");
					writer.println(String.format(Locale.US, "\\draw (%d, %d) ++ (0, 0.4)  -- ++ (-0.1, 0); ", size, size - 1));
					writer.println(String.format(Locale.US, "\\draw (%d, %d) ++ (0, 0.6)  -- ++ (-0.1, 0); ", size, size - 1));

				}


				for (Edge e : G.edges()) {
					int i = e.from % size;
					int j = e.from / size;
					writer.println(String.format(Locale.US, "\\begin{scope}[xshift=%dcm, yshift=%dcm]", i, j));
					if (e.to == e.from + size) {
						/* arête verticale */
						if (!e.used) {
							writer.println("\\draw (0.4,0.9) -- (0.6,0.9);");
							writer.println("\\draw (0.4,1.1) -- (0.6,1.1);");
						} else {
							writer.println("\\draw (0.4,0.9) -- (0.4,1.1);");
							writer.println("\\draw (0.6,0.9) -- (0.6,1.1);");
						}
					} else {
						/* arête horizontale */

						if (!e.used) {
							writer.println("\\draw (0.9,0.4) -- (0.9,0.6);");
							writer.println("\\draw (1.1,0.4) -- (1.1,0.6);");
						} else {
							writer.println("\\draw (0.9,0.4) -- (1.1,0.4);");
							writer.println("\\draw (0.9,0.6) -- (1.1,0.6);");
						}
					}
					writer.println("\\end{scope}");
				}
				writer.println("\\end{tikzpicture}");
				writer.println("\\end{document}");
				writer.close();
			} catch (IOException e) {
			}
		}


	}


	public static void main(String[] args) {

		Graph graph = new Graph("g5.txt");
		Coloriage coloriage = new Coloriage(graph);
		Graph res5 = coloriage.colore();
		if(res5.is3Color()) {
			System.out.println("Graph 5 is 3-colorable");
		}
		else{
			System.out.println("Graph 5 not 3-colorable !");
		}

		Graph graph10 = new Graph("g10.txt");
		coloriage = new Coloriage(graph10);
		Graph res10 = coloriage.colore();
		if(res10.is3Color()) {
			System.out.println("Graph 10 is 3-colorable");
		}
		else{
			System.out.println("Graph 10 not 3-colorable !");
		}

		Graph graph30a = new Graph("g30a.txt");
		coloriage = new Coloriage(graph30a);
		Graph res30a = coloriage.colore();
		if(res10.is3Color()) {
			System.out.println("Graph 30a is 3-colorable");
		}
		else{
			System.out.println("Graph 30a not 3-colorable !");
		}

		Graph graph30b = new Graph("g30b.txt");
		coloriage = new Coloriage(graph30b);
		Graph res30b = coloriage.colore();
		if(res30b.is3Color()) {
			System.out.println("Graph 30b is 3-colorable");
		}
		else{
			System.out.println("Graph 30b not 3-colorable !");
		}


		Graph graph50 = new Graph("g50.txt");
		coloriage = new Coloriage(graph50);
		Graph res50 = coloriage.colore();
		if(res50.is3Color()) {
			System.out.println("Graph 50 is 3-colorable");
		}
		else{
			System.out.println("Graph 50 not 3-colorable !" );
		}

		Graph graph55 = new Graph("g55.txt");
		coloriage = new Coloriage(graph55);
		Graph res55 = coloriage.colore();
		if(res55.is3Color()) {
			System.out.println("Graph 55 is 3-colorable");
		}
		else{
			System.out.println("Graph 55 not 3-colorable !");
		}


		Graph graph60 = new Graph("g60.txt");
		coloriage = new Coloriage(graph60);
		Graph res60 = coloriage.colore();
		if(res60.is3Color()) {
			System.out.println("Graph 60 is  3-colorable");
		}
		else{
			System.out.println("Graph 60 not 3-colorable !");
		}

		Graph graph65 = new Graph("g65.txt");
		coloriage = new Coloriage(graph65);
		Graph res65 = coloriage.colore();
		if(res65.is3Color()) {
			System.out.println("Graph 65 is  3-colorable");
		}
		else{
			System.out.println("Graph 65 not 3-colorable !");
		}

		Graph graph70 = new Graph("g70.txt");
		coloriage = new Coloriage(graph70);
		Graph res70 = coloriage.colore();
		if(res70.is3Color()) {
			System.out.println("Graph 70 is  3-colorable");
		}
		else{
			System.out.println("Graph 70 not 3-colorable !");
		}

		Graph graph75 = new Graph("g75.txt");
		coloriage = new Coloriage(graph75);
		Graph res75 = coloriage.colore();
		if(res70.is3Color()) {
			System.out.println("Graph 75 is  3-colorable");
		}
		else{
			System.out.println("Graph 75 not 3-colorable !");
		}


		Graph graph80 = new Graph("g80.txt");
		coloriage = new Coloriage(graph80);
		Graph res80 = coloriage.colore();
		if(res80.is3Color()) {
			System.out.println("Graph 80 is  3-colorable");
		}
		else{
			System.out.println("Graph 80 not 3-colorable !");
		}

		Graph graph85 = new Graph("g85.txt");
		coloriage = new Coloriage(graph85);
		Graph res85 = coloriage.colore();
		if(res85.is3Color()) {
			System.out.println("Graph 85 is  3-colorable");
		}
		else{
			System.out.println("Graph 85 not 3-colorable !");
		}


		Graph graph90 = new Graph("g90.txt");
		coloriage = new Coloriage(graph90);
		Graph res90 = coloriage.colore();
		if(res90.is3Color()) {
			System.out.println("Graph 90 is  3-colorable");
		}
		else{
			System.out.println("Graph 90 not 3-colorable !");
		}

		Graph graph100 = new Graph("g100.txt");
		coloriage = new Coloriage(graph100);
		Graph res100 = coloriage.colore();
		if(res90.is3Color()) {
			System.out.println("Graph 100 is  3-colorable");
		}
		else{
			System.out.println("Graph 100 not 3-colorable !");
		}

		Graph graph200a = new Graph("g200a.txt");
		coloriage = new Coloriage(graph200a);
		Graph res200a = coloriage.colore();
		if(res200a.is3Color()) {
			System.out.println("Graph 200a is  3-colorable");
		}
		else{
			System.out.println("Graph 200a not 3-colorable !");
		}

		Graph graph200b = new Graph("g200b.txt");
		coloriage = new Coloriage(graph200b);
		Graph res200b = coloriage.colore();
		if(res200a.is3Color()) {
			System.out.println("Graph 200b is  3-colorable");
		}
		else{
			System.out.println("Graph 200b not 3-colorable !");
		}
		/*for(int i = 0 ; i < res.getColors().length;i++){
			System.out.println("COULEUR SOMMET : " + i  + " " + res.getColors()[i]);
		}*/



		/*int size = 4;
		Graph G = Graph.Grid(size);
		Display d = new Display();
		//d.setImage(G.toImage());
		System.out.println("appuyez sur une touche");
		new Scanner(System.in).nextLine();
		d.close();
		printLaby(G, size, "toto.tex");*/


	}
}

