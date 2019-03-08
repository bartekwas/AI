package lab_1.utils;

import lab_1.model.Configuration;
import lab_1.model.Individual;
import lab_1.model.Node;

import java.util.ArrayList;
import java.util.Arrays;

public class AlgorithmUtil {

    public final int POPULATION_SIZE;

    public AlgorithmUtil(int POPULATION_SIZE){
        this.POPULATION_SIZE = POPULATION_SIZE;
    }


    public void initialiazeGeneticAlgorithm(Configuration configuration){
        Node[] allNodes = new Node[configuration.getNodes().size()];
        configuration.getNodes().toArray(allNodes);
        ArrayList<Individual> population = createPopulation(allNodes);
        assessPopulation(population);

        displayPopulation(population);
    }


    public Node[] createRandomNodesArray(Node[] allNodes){
        Node[] randomNodes = new Node[allNodes.length];
        Node[] allNodesCopy = Arrays.copyOf(allNodes, allNodes.length);
        int range = allNodesCopy.length-1;
        for(int i =0; i<allNodesCopy.length-1; i++){
            int random = (int)(Math.random()*(range+1));
            randomNodes[i] = allNodesCopy[random];
            allNodesCopy[random]=allNodesCopy[range-1];
            range--;
        }
        return randomNodes;
    }

    public ArrayList<Individual> createPopulation(Node[] allNodes){
        ArrayList<Individual> population = new ArrayList<Individual>();
        for(int i = 0; i<POPULATION_SIZE; i++){
            population.add(new Individual(createRandomNodesArray(allNodes)));
        }
        return population;
    }

    public void displayPopulation(ArrayList<Individual> population){
        for (int p=0; p<population.size(); p++) {
            StringBuilder sb = new StringBuilder();
            sb.append(p + ". node order: {");
            for (int i = 0; i < (population.get(p).getNodesOrder().length-1); i++) {
                sb.append(population.get(p).getNodesOrder()[i].getId());
                sb.append(" ");
            }
            sb.append("}");
            sb.append(" total distance: " + population.get(p).getTotalDistance());
            System.out.println(sb.toString());
        }
    }


    public void assessPopulation(ArrayList<Individual> population){

        for (int p = 0; p< population.size(); p++){
           Individual individual = population.get(p);
           for(int i = 0; i<individual.getNodesOrder().length-1; i++){
               Double totalDistance =0.0;
               if(i != individual.getNodesOrder().length-2){
                   totalDistance += getDistanceBetweenNodes(individual.getNodesOrder()[i], individual.getNodesOrder()[i+1]);
               } else{
                   totalDistance += getDistanceBetweenNodes(individual.getNodesOrder()[i], individual.getNodesOrder()[0]);
               }
               individual.setTotalDistance(totalDistance);
           }
        }
    }

    public double getDistanceBetweenNodes(Node node1, Node node2){
        double x1 = node1.getX();
        double x2 = node2.getX();

        double y1 = node1.getY();
        double y2 = node2.getY();

        double distance = Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1));

        return distance;

    }





}
