package lab_1.utils;


import lab_1.model.Configuration;
import lab_1.model.Item;
import lab_1.model.Node;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ParserUtils {

    public static Configuration parseInputFile(String FILE_PATH, String FILE_NAME){

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(FILE_PATH + FILE_NAME));

            Configuration configuration = readInitialInformation(reader);

            reader.readLine(); //skip line: NODE COORD SECTION

            Map<String, Node> nodes = readNodes(configuration, reader);

            reader.readLine(); //skip line: Items Section (index, profit, weight, node number)

            readIteams(configuration, reader, nodes);

            configuration.setNodes(new ArrayList<>(nodes.values()));

            reader.close();
            return configuration;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            System.out.println("Invalid input file.");
            e.printStackTrace();
            return null;
        }
    }


    public static Configuration readInitialInformation(BufferedReader reader) throws IOException{
        Configuration configuration = new Configuration();
        configuration.setProblemName(reader.readLine().replace("PROBLEM NAME:", "").trim());
        configuration.setDataType(reader.readLine().replace("KNAPSACK DATA TYPE:", "").trim());
        configuration.setDimension(Integer.parseInt(reader.readLine().replace("DIMENSION:", "").trim()));
        configuration.setNumberOfItems(Integer.parseInt(reader.readLine().replace("NUMBER OF ITEMS:", "").trim()));
        configuration.setCapacity(new BigDecimal(reader.readLine().replace("CAPACITY OF KNAPSACK:", "").trim()));
        configuration.setMinSpeed(new BigDecimal(reader.readLine().replace("MIN SPEED:", "").trim()));
        configuration.setMaxSpeed(new BigDecimal(reader.readLine().replace("MAX SPEED:", "").trim()));
        configuration.setRentingRatio(new BigDecimal(reader.readLine().replace("RENTING RATIO:", "").trim()));
        configuration.setEdgeWeightType(reader.readLine().replace("EDGE_WEIGHT_TYPE:", "").trim());

        return configuration;
    }

    public static Map<String, Node> readNodes(Configuration configuration, BufferedReader reader) throws IOException{
        Map<String, Node> nodes = new HashMap<>();

        for (int i = 0; i < configuration.getDimension(); i++) {
            Node node = toNode(reader.readLine());
            nodes.put(node.getId(), node);
        }
        return nodes;
    }

    public static void readIteams(Configuration configuration, BufferedReader reader, Map<String, Node> nodes) throws IOException{
        for (int i = 0; i < configuration.getNumberOfItems(); i++) {
            Item item = toItem(reader.readLine());
            nodes.get(item.getNodeId()).getItems().add(item);
        }
    }

    private static Node toNode(String line) {
        String[] values = line.split("\t");

        Node node = new Node();
        node.setId(values[0]);
        node.setX(Double.parseDouble(values[1]));
        node.setY(Double.parseDouble(values[2]));

        return node;
    }

    private static Item toItem(String line) {
        String[] values = line.split("\t");

        Item item = new Item();
        item.setId(values[0]);
        item.setProfit(Double.parseDouble(values[1]));
        item.setWeight(Double.parseDouble(values[2]));
        item.setNodeId(values[3]);

        return item;
    }

}
