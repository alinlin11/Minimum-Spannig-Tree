package com.company;
import java.util.*;

public class Graph {
    private final double[][] adjacencyMatrix;

    private Graph(double[][] adjacencyMatrix) {
        this.adjacencyMatrix = adjacencyMatrix;
    }

    public static Graph fromMatrixString(String s) {
        //if string is empty
        if(s.equals("")) {
            double[][] x = new double[0][0];
            Graph matrix = new Graph(x);

            return matrix;
        }

        //separate string by newline
        String[] temp = s.split("\\r?\\n");

        //find number of values in one line
        String[] numInRow = temp[0].split(" ");

        //initialize array
        String[] array = new String[numInRow.length * temp.length];

        //separate values on each new line
        //and put into array
        int indexOfArray = 0;
        for(int i = 0; i < temp.length; i++) {
            String[] t = temp[i].split(" ");

            for(int j = 0; j < t.length; j++) {
                array[indexOfArray] = t[j];
                indexOfArray++;
            }
        }

        //initialize matrix
        //and turn into double values
        double[][] matrix = new double[temp.length][numInRow.length];

        int i = 0;
        while(i < array.length) {
            for(int j = 0; j < matrix.length; j++) {
                for(int k = 0; k < matrix[j].length; k++) {
                    double value = Double.valueOf(array[i]);
                    matrix[j][k] = value;
                    i++;
                }
            }
        }

        Graph m = new Graph(matrix);
        return m;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            for (int j = 0; j < adjacencyMatrix[i].length; j++) {
                builder.append(String.format("%1.2f ", adjacencyMatrix[i][j]));
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    public Graph getTree() {
        //need to check if value outside of set is connecting to one in set
        //if it is pick the second lowest weight
        //and so on


        //put new sets into new matrix
        //so if 0 connects to 4 put it into a new matrix with the weight in



        //add vertx into set
        ArrayList<Integer> set = new ArrayList<>();
        set.add(0);


        double[][] mst = new double[adjacencyMatrix.length][adjacencyMatrix.length];

        int count = 0;

        while(count < adjacencyMatrix.length) {

            int minFrom = -1;
            int minTo = -1;
            double minWeight = 1;

            for(int i = 0; i < set.size(); i++) {
                int from = set.get(i);
                for(int j = 0; j < adjacencyMatrix.length; j++) {
                    int to = j;

                    if(from == to || set.contains(to))
                        continue;

                    double weight = adjacencyMatrix[from][to];

                    //find min value in row
                    if(weight != 0) {
                        if(minWeight == 1 || weight < minWeight) {
                            minFrom = from;
                            minTo = to;
                            minWeight = weight;
                        }
                    }
                }
            }

            //the connecting node
            if(minTo != -1) {
                set.add(minTo);

                //add the min weighted value to new matrix
                mst[minFrom][minTo] = adjacencyMatrix[minFrom][minTo];
                mst[minTo][minFrom] = adjacencyMatrix[minTo][minFrom];
            }

            count++;
        }


        Graph y = new Graph(mst);
        return y;
    }

    private static final String s =
                    "0.0 0.0 0.5 0.7 0.0 0.0\n" +
                    "0.0 0.0 0.7 0.0 1.0 0.0\n" +
                    "0.5 0.7 0.0 1.0 0.0 0.9\n" +
                    "0.7 0.0 1.0 0.0 0.0 0.0\n" +
                    "0.0 1.0 0.0 0.0 0.0 0.7\n" +
                    "0.0 0.0 0.9 0.0 0.7 0.0";

    public static void main(String... args) {
        // you can assume that incorrect strings will not be provided as input,
        // i.e., any input string will be a valid adjacency matrix
        // representation of an undirected weighted graph

        //full matrix
        Graph g = Graph.fromMatrixString(s);
        System.out.println(g);
        Graph tree = g.getTree();
        System.out.println(tree.toString());

        //empty matrix
        String test = "";
        Graph x = Graph.fromMatrixString(test);
        System.out.println(x);
        Graph empty = x.getTree();
        System.out.println("Empty Tree: " + empty.toString());

        //vertex no edges
        String single = "1.0 0.0\n" +
                        "0.0 0.0\n";
        Graph s = Graph.fromMatrixString(single);
        Graph n = s.getTree();
        System.out.println("Single vertex no edges: " + n.toString());
    }
}

