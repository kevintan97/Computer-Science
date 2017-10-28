package ce152;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Hill implements Comparable<Hill> {

    int number;
    String name;
    String county;
    double height;
    double latitude;
    double longitude;

    public Hill(int number, String name, String county, double height, double latitude, double longitude) {
        this.number = number;
        this.name = name;
        this.county = county;
        this.height = height;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(number);
        builder.append(",");
        builder.append(name);
        builder.append(",");
        builder.append(county);
        builder.append(",");
        builder.append(height);
        builder.append(",");
        builder.append(latitude);
        builder.append(",");
        builder.append(longitude);
        return builder.toString();
    }

    @Override
    public int compareTo(Hill o) {
        return this.name.toLowerCase().compareTo(o.name.toLowerCase());
    }

    public static Map<String, Set<Hill>> hillsByCounty(List<Hill> hills) {
        // usage of TreeMap and TreeSet will automatically sort alphabetically
        Map<String, Set<Hill>> countyHills = new TreeMap<>();

        for (Hill hill : hills) {
            String county = hill.county;

            if (countyHills.containsKey(county)) {
                Set<Hill> hillSet = (Set<Hill>) countyHills.get(county);
                hillSet.add(hill);
            } else {
                Set<Hill> hillSet = new TreeSet<Hill>();
                hillSet.add(hill);

                countyHills.put(county, hillSet);
            }
        }

        return countyHills;
    }

}