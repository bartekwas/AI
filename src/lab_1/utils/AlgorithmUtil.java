package lab_1.utils;

import lab_1.model.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class AlgorithmUtil {

    public final int POPULATION_SIZE;
    private final double MAX_BACKPACK_CAPACITY;
    private final double MAX_VELOCITY;
    private final double MIN_VELOCITY;
    public final CollectingAlgorithm COLLECTING_ALGORITHM;
    public final ParentsChooseAlgorithm PARENTS_CHOOSE_ALGORITHM;
    private final Configuration configuration;
    private final LogWriter logWriter;
    private final int ITERATIONS;
    private final int BEST_RESULTS_ON_DIAGRAM;
    private ArrayList<Double> bestRatios;

    public AlgorithmUtil(Configuration configuration, int POPULATION_SIZE, CollectingAlgorithm collectingAlgorithm, ParentsChooseAlgorithm parentsChooseAlgorithm, int iterations, int best_results, LogWriter logWriter){
        this.POPULATION_SIZE = POPULATION_SIZE;
        this.MAX_BACKPACK_CAPACITY = configuration.getCapacity();
        this.MAX_VELOCITY = configuration.getMaxSpeed();
        this.MIN_VELOCITY = configuration.getMinSpeed();
        this.COLLECTING_ALGORITHM = collectingAlgorithm;
        this.PARENTS_CHOOSE_ALGORITHM = parentsChooseAlgorithm;
        this.configuration = configuration;
        this.logWriter = logWriter;
        this.ITERATIONS = iterations;
        this.BEST_RESULTS_ON_DIAGRAM = best_results;
        bestRatios = new ArrayList<Double>();
    }


    public ArrayList<Double> initialiazeGeneticAlgorithm(){
        ArrayList<Individual> randomChoosenPopulation = createFirstPopulation();
        ArrayList<Individual> nextPopulation = randomChoosenPopulation;
        for(int i = 0; i<ITERATIONS; i++) {
            nextPopulation = createNewPopulation(nextPopulation);
            assessPopulation(nextPopulation);
            OptionalDouble best = nextPopulation.stream().mapToDouble(p -> p.getFinalRatio()).max();
            bestRatios.add(best.getAsDouble());
            //logWriter.makeCSVWholePopulation(nextPopulation);
            //logBestResult(nextPopulation);
        }
        return bestRatios;
    }

    public ArrayList<Individual> createFirstPopulation(){
        Node[] allNodes = new Node[configuration.getNodes().size()];
        configuration.getNodes().toArray(allNodes);
        ArrayList<Individual> population = createPopulation(allNodes);
        assessPopulation(population);

        //logWriter.makeCSVWholePopulation(population);
        logBestResult(population);

        displayPopulation(population);


        return population;
    }

    public void logBestResult(ArrayList<Individual> population){
        population = sortPopulation(population);
        ArrayList<Individual> bestIndyviduals = new ArrayList<>();
        for(int i = 0; i<BEST_RESULTS_ON_DIAGRAM; i++){
            bestIndyviduals.add(population.get(i));
        }

        //logWriter.addBestResult(bestIndyviduals);
    }


    public Node[] createRandomNodesArray(Node[] allNodes){
        Node[] randomNodes = new Node[allNodes.length];
        Node[] allNodesCopy = Arrays.copyOf(allNodes, allNodes.length);
        int range = allNodesCopy.length-1;
        for(int i =0; i<allNodesCopy.length; i++){
            int random = (int)(Math.random()*(range));
            randomNodes[i] = allNodesCopy[random];
            allNodesCopy[random]=allNodesCopy[range];
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
        System.out.println("Assessment results: ");
        for (int p=0; p<population.size(); p++) {
            StringBuilder sb = new StringBuilder();
            StringBuilder sbLogs = new StringBuilder();
            sbLogs.append("\n");
            sbLogs.append("\n");
            sbLogs.append("\n");
            sb.append("indyvidual." +p + " -");
            sb.append("indyvidual." +p + " -");

            sbLogs.append(" - node order: {");
            for (int i = 0; i < (population.get(p).getNodesOrder().length-1); i++) {
                sbLogs.append(population.get(p).getNodesOrder()[i].getId());
                sbLogs.append(" ");
            }
            sbLogs.append("}");
            sbLogs.append("\n");
            sbLogs.append(" items collected: {");
            for (int i =0; i<population.get(p).getItems().size(); i++){
                sbLogs.append("\n");
                sbLogs.append("p:" +population.get(p).getItems().get(i).getProfit());
                sbLogs.append(", w:" +population.get(p).getItems().get(i).getWeight());
            }
            sbLogs.append(" }");
            sbLogs.append("\n");


            sb.append(" total time: " + round(population.get(p).getTotalTime(), 2) + ";");
            sb.append(" total profit: " + population.get(p).getTotalProfit()+ ";");
            sb.append(" total weight: " + population.get(p).getTotalWeight()+ ";");
            sb.append(" final ratio: " + population.get(p).getFinalRatio());
            sbLogs.append(" total time: " + round(population.get(p).getTotalTime(), 2) + ";");
            sbLogs.append(" total profit: " + population.get(p).getTotalProfit()+ ";");
            sbLogs.append(" total weight: " + population.get(p).getTotalWeight()+ ";");
            sbLogs.append(" final ratio: " + population.get(p).getFinalRatio());
            System.out.println(sb.toString());
            //logWriter.writeToLog(sbLogs.toString());
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
        switch (COLLECTING_ALGORITHM){
            case PROFIT_WEIGHT_RATIO:
                makeDecisionBasedOnProfitToWeightRation(individual, node);
                break;
            case GREEDY:
                makeDecisionBasedOnGreedyAlgorithm(individual, node);
                break;

        }
    }


    public void makeDecisionBasedOnGreedyAlgorithm(Individual individual, Node node){
        if((!(node.getItems() == null)) && (!(node.getItems().isEmpty()))) {
            individual.addItemBasedOnGreedyAlgorithm(node.getItems().get(0), MAX_BACKPACK_CAPACITY, logWriter);
        }
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
                individual.addItemBasedOnProfitWeightRation(item, MAX_BACKPACK_CAPACITY, logWriter);
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

    private ArrayList<Individual> createNewPopulation(ArrayList<Individual> previousPopulation){
        ArrayList<Individual> newPopulation = new ArrayList<>();

        for(int i =0; i<previousPopulation.size()/2; i++) {
            ArrayList<Individual> parents = chooseParents(previousPopulation);
            parents = makeCrossOver(parents);
            //parents = mutateParents(parents);
            newPopulation.add(parents.get(0));
            newPopulation.add(parents.get(1));
        }
        return newPopulation;
    }


    private ArrayList<Individual> makeCrossOver(ArrayList<Individual> parents){
        Node [] allNodes = parents.get(0).getNodesOrder();

        int halfSize = parents.get(0).getNodesOrder().length/2;

        Individual parent1 = parents.get(0);
        Individual parent2 = parents.get(1);

        ArrayList<Node> parent1Nodes = new ArrayList<Node>();
        ArrayList<Node> parent2Nodes = new ArrayList<Node>();

        for(int i=0; i<halfSize; i++){
            parent1Nodes.add(parent1.getNodesOrder()[i]);
            parent2Nodes.add(parent2.getNodesOrder()[i]);
        }

        for(int i = halfSize; i<parent1.getNodesOrder().length; i++){
            parent1Nodes.add(parent2.getNodesOrder()[i]);
            parent2Nodes.add(parent1.getNodesOrder()[i]);
        }


        parent1Nodes = fixIndyvidualsAfterCrossOver(parent1Nodes, allNodes);
        parent2Nodes = fixIndyvidualsAfterCrossOver(parent2Nodes, allNodes);

        Individual newParent1 = new Individual(parent1Nodes.toArray(new Node[parent1Nodes.size()]));
        Individual newParent2 = new Individual(parent2Nodes.toArray(new Node[parent2Nodes.size()]));
        parents.clear();
        parents.add(newParent1);
        parents.add(newParent2);
        return parents;
    }

    private ArrayList<Node> fixIndyvidualsAfterCrossOver(ArrayList<Node> nodesOrderToFix, Node [] allNodes){
        ArrayList<Node> missingNodes = findMissingNodes(allNodes, nodesOrderToFix);
        ArrayList<Node> duplicatedNodes = findDuplicatedNodes(nodesOrderToFix);
        ArrayList<Node> correctNodes = new ArrayList<>();
        int lastPosition = missingNodes.size()-1;
        for (Node nodeToFix : nodesOrderToFix){
            boolean duplicationFound = false;
            for(Iterator<Node> iterator = duplicatedNodes.iterator(); iterator.hasNext();){
                Node duplicatedNode = iterator.next();
                if (nodeToFix.getId().equals(duplicatedNode.getId())){
                    correctNodes.add(missingNodes.get(lastPosition--));
                    iterator.remove();
                    duplicationFound = true;
                }
            }
            if(!duplicationFound) {
                correctNodes.add(nodeToFix);
            }
        }
        return correctNodes;
    }

    private ArrayList<Individual> mutateParents(ArrayList<Individual> parents){
        Node[] parent1Nodes = parents.get(0).getNodesOrder();
        Node[] parent2Nodes = parents.get(1).getNodesOrder();

        parent1Nodes = mutate(parent1Nodes);
        parent2Nodes = mutate(parent2Nodes);

        parents.get(0).setNodesOrder(parent1Nodes);
        parents.get(1).setNodesOrder(parent2Nodes);

        return parents;

    }

    private Node[] mutate(Node[] nodes){
        int nodeSize = nodes.length;

        for (int i = 0; i< nodeSize*0.05; i++){
            int random1 = (int)(Math.random()*(nodeSize));
            int random2 = (int)(Math.random()*(nodeSize));

            Node node1 = nodes[random1];
            Node node2 = nodes[random2];

            nodes[random1] = node2;
            nodes[random2] = node1;
        }
        return nodes;
    }


    public ArrayList<Node> findMissingNodes(Node [] allNodes,ArrayList<Node> nodesOrderToFix){
        ArrayList<Node> missingNodes = new ArrayList<Node>();
        for(Node node: allNodes){
            boolean alreadyExist = false;
            for(Node nodeToFix: nodesOrderToFix){
                String n1Id = nodeToFix.getId();
                String n2Id = node.getId();
                if(nodeToFix.getId().equals(node.getId())){
                    alreadyExist = true;
                    break;
                }
            }
            if (!alreadyExist){
                missingNodes.add(node);
            }
        }
        return missingNodes;
    }

    public ArrayList<Node> findDuplicatedNodes(ArrayList<Node> nodesOrderToFix){
        ArrayList<Node> uniqueNodes = new ArrayList<>();
        ArrayList<Node> duplicatedNodes = new ArrayList<>();
        for(Node nodeToFix : nodesOrderToFix){
            for(Node uniqueNode : uniqueNodes){
                if(nodeToFix.getId().equals(uniqueNode.getId())){
                    duplicatedNodes.add(nodeToFix);
                    break;
                }
            }
            uniqueNodes.add(nodeToFix);
        }
        return duplicatedNodes;
    }



    private ArrayList<Individual> chooseParents(ArrayList<Individual> population){
        switch (PARENTS_CHOOSE_ALGORITHM){
            case TOURNAMENT:
                return chooseParentsBasedOnTournament(population);
            case ROULTETTE:
                return chooseParentsBasedOnRoulette(population);
            default:
                return null;
        }
    }

    private ArrayList<Individual> chooseParentsBasedOnTournament(ArrayList<Individual> population){
        ArrayList<Individual> parents = new ArrayList<>();
        for(int i = 0; i<2; i++) {
            ArrayList<Individual> randomFifeIndyviduals = chooseNRandomIndyviduals(5, population);
            sortPopulation(randomFifeIndyviduals);
            parents.add(randomFifeIndyviduals.get(0));
        }
        return parents;
    }

    private ArrayList<Individual> chooseNRandomIndyviduals(int n, ArrayList<Individual> popultaion){

        Individual[] randomIndividuals = new Individual[n];
        Individual[] allIndividualCopy = popultaion.toArray(new Individual[popultaion.size()]);
        int range = allIndividualCopy.length-1;
        for(int i =0; i<n; i++){
            int random = (int)(Math.random()*(range+1));
            randomIndividuals[i] = allIndividualCopy[random];
            allIndividualCopy[random]=allIndividualCopy[range-1];
            range--;
        }
        return new ArrayList<>(Arrays.asList(randomIndividuals));
    }


    private ArrayList<Individual> chooseParentsBasedOnRoulette(ArrayList<Individual> population){
        return null;
    }







}
