package lab_1.utils;

import lab_1.model.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class AlgorithmUtil {

    public final int POPULATION_SIZE;
    private final double MAX_BACKPACK_CAPACITY;
    private final double MAX_VELOCITY;
    private final double MIN_VELOCITY;
    private final boolean LOGGING;
    private final boolean BACKPACK_ALGORITHM_LOGGING;
    private final Configuration configuration;

    public AlgorithmUtil(Configuration configuration, int POPULATION_SIZE, boolean LOGGING, boolean BACKPACK_ALGORITHM_LOGGING){
        this.POPULATION_SIZE = POPULATION_SIZE;
        this.MAX_BACKPACK_CAPACITY = configuration.getCapacity();
        this.MAX_VELOCITY = configuration.getMaxSpeed();
        this.MIN_VELOCITY = configuration.getMinSpeed();
        this.LOGGING = LOGGING;
        this.BACKPACK_ALGORITHM_LOGGING = BACKPACK_ALGORITHM_LOGGING;
        this.configuration = configuration;
    }


    public void initialiazeGeneticAlgorithm(){
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
        population = sortPopulation(population);
        System.out.println("Assessment results: ");
        for (int p=0; p<population.size(); p++) {
            StringBuilder sb = new StringBuilder();
            if(LOGGING){
                sb.append("\n");
                sb.append("\n");
                sb.append("\n");
            }
            sb.append("indyvidual." +p);
            if(LOGGING == true){
                sb.append(" - node order: {");
                for (int i = 0; i < (population.get(p).getNodesOrder().length-1); i++) {
                    sb.append(population.get(p).getNodesOrder()[i].getId());
                    sb.append(" ");
                }
                sb.append("}");
                sb.append("\n");
                sb.append(" items collected: {");
                for (int i =0; i<population.get(p).getItems().size(); i++){
                    sb.append("\n");
                    sb.append("p:" +population.get(p).getItems().get(i).getProfit());
                    sb.append(", w:" +population.get(p).getItems().get(i).getWeight());
                }
                sb.append(" }");
                sb.append("\n");
            }

            sb.append(" total time: " + round(population.get(p).getTotalTime(), 2) + ";");
            sb.append(" total profit: " + population.get(p).getTotalProfit()+ ";");
            sb.append(" final ratio: " + population.get(p).getFinalRatio());
            System.out.println(sb.toString());
        }
    }


    public void assessPopulation(ArrayList<Individual> population){

        for (int p = 0; p< population.size(); p++){
           Individual individual = population.get(p);
           Double totalTime =0.0;
           for(int i = 0; i<individual.getNodesOrder().length-1; i++){
               Node actualNode = individual.getNodesOrder()[i];

               if(i != individual.getNodesOrder().length-2){
                   makeDecisionAboutItem(individual, actualNode);
                   totalTime += getTimeBetweenNodes(individual.getNodesOrder()[i], individual.getNodesOrder()[i+1], individual);
               } else{
                   totalTime += getTimeBetweenNodes(individual.getNodesOrder()[i], individual.getNodesOrder()[0], individual);
               }

           }
            individual.setTotalTime(totalTime);
           individual.setFinalRatio(individual.getTotalProfit()-totalTime);
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

    public double getIndyvidualVelocity(Individual individual){
        return (MAX_VELOCITY - ((individual.getTotalWeight()/MAX_BACKPACK_CAPACITY)*(MAX_VELOCITY - MIN_VELOCITY)));
    }

    public double getTimeBetweenNodes(Node node1, Node node2, Individual individual){
        double distance = getDistanceBetweenNodes(node1, node2);
        double velocity = getIndyvidualVelocity(individual);
        return distance/velocity;
    }

    public void makeDecisionAboutItem(Individual individual, Node node){
        makeDecisionBasedOnProfitToWeightRation(individual, node);
    }



    public void makeDecisionBasedOnProfitToWeightRation(Individual individual, Node node){
        double indywidualAveregeProfitWeightRation = 0;
        if(!(individual.getItems() == null)){
            if(!individual.getItems().isEmpty()) {
                indywidualAveregeProfitWeightRation = individual.getTotalProfit() / individual.getTotalWeight();
            }
        }
        double nodeProfitWeightRation =0;
        if(!node.getItems().isEmpty()) {
            Item item = node.getItems().get(0);
            nodeProfitWeightRation = item.getProfitWeightRation();
            if (indywidualAveregeProfitWeightRation < nodeProfitWeightRation) {
                individual.addItemBasedOnProfitWeightRation(item, MAX_BACKPACK_CAPACITY, BACKPACK_ALGORITHM_LOGGING);
            }
        }
    }

    public ArrayList<Individual> sortPopulation(ArrayList<Individual> population){
        Collections.sort(population, new IndyvidualsComparator());
        return population;
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }







}
