package bearmaps.proj2c;

import bearmaps.hw4.streetmap.Node;
import bearmaps.hw4.streetmap.StreetMapGraph;
import bearmaps.proj2ab.KDTree;
import bearmaps.proj2ab.Point;
import edu.princeton.cs.algs4.TrieSET;

import java.util.*;

/**
 * An augmented graph that is more powerful that a standard StreetMapGraph.
 * Specifically, it supports the following additional operations:
 */
public class AugmentedStreetMapGraph extends StreetMapGraph {
    private Map<Point, Long> pointToNode;
    private KDTree kdTree;
    private TrieSET nameTrie;
    private Map<String, LinkedList<Node>> nameToNodeList;

    public AugmentedStreetMapGraph(String dbPath) {
        super(dbPath);
        pointToNode = new HashMap<>();
        List<Point> points = new LinkedList<>();
        List<Node> nodes = this.getNodes();
        for (Node node : nodes) {
            if (!this.neighbors(node.id()).isEmpty()) {
                double lon = node.lon();
                double lat = node.lat();
                Point pt = new Point(lon, lat);
                points.add(pt);
                pointToNode.put(pt, node.id());
            }
            if (node.name() != null) {
                String nodeName = cleanString(node.name());
                nameTrie.add(nodeName);
                if (!nameToNodeList.containsKey(nodeName)) {
                    nameToNodeList.put(nodeName, new LinkedList<>());
                }
                LinkedList<Node> nodeList = nameToNodeList.get(nodeName);
                nodeList.add(node);
                nameToNodeList.put(nodeName, nodeList);
            }
        }
        kdTree = new KDTree(points);
    }


    /**
     * For Project Part II
     * Returns the vertex closest to the given longitude and latitude.
     * @param lon The target longitude.
     * @param lat The target latitude.
     * @return The id of the node in the graph closest to the target.
     */
    public long closest(double lon, double lat) {
        Point closestPoint = kdTree.nearest(lon, lat);
        return pointToNode.get(closestPoint);
    }


    /**
     * For Project Part III (gold points)
     * In linear time, collect all the names of OSM locations that prefix-match the query string.
     * @param prefix Prefix string to be searched for. Could be any case, with our without
     *               punctuation.
     * @return A <code>List</code> of the full names of locations whose cleaned name matches the
     * cleaned <code>prefix</code>.
     */
    public List<String> getLocationsByPrefix(String prefix) {
        String cleanPrefix = cleanString(prefix);
        Iterable<String> nameWithPrefix = nameTrie.keysWithPrefix(cleanPrefix);
        Set<String> locations = new HashSet<>();
        for (String name : nameWithPrefix) {
            for (Node node : nameToNodeList.get(name)) {
                locations.add(node.name());
            }
        }
        return new LinkedList<>(locations);
    }

    /**
     * For Project Part III (gold points)
     * Collect all locations that match a cleaned <code>locationName</code>, and return
     * information about each node that matches.
     * @param locationName A full name of a location searched for.
     * @return A list of locations whose cleaned name matches the
     * cleaned <code>locationName</code>, and each location is a map of parameters for the Json
     * response as specified: <br>
     * "lat" -> Number, The latitude of the node. <br>
     * "lon" -> Number, The longitude of the node. <br>
     * "name" -> String, The actual name of the node. <br>
     * "id" -> Number, The id of the node. <br>
     */
    public List<Map<String, Object>> getLocations(String locationName) {
        String cleanName = cleanString(locationName);
        List<Map<String, Object>> locations = new LinkedList<>();
        if (nameToNodeList.containsKey(cleanName)) {
            for (Node node : nameToNodeList.get(cleanName)) {
                Map<String, Object> nodeMap = new HashMap<>();
                nodeMap.put("id", node.id());
                nodeMap.put("lon", node.lon());
                nodeMap.put("lat", node.lat());
                nodeMap.put("name", node.name());
                locations.add(nodeMap);
            }
        }
        return locations;
    }


    /**
     * Helper to process strings into their "cleaned" form, ignoring punctuation and capitalization.
     */
    private static String cleanString(String s) {
        return s.replaceAll("[^a-zA-Z ]", "").toLowerCase();
    }

}
