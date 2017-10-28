 package ce152;

 import java.io.File;
 import java.io.IOException;
 import java.util.ArrayList;
 import java.util.Collections;
 import java.util.Comparator;
 import java.util.Iterator;
 import java.util.List;
 import java.util.Map;
 import java.util.Scanner;
 import java.util.Set;

public class Exercise5 {

    public static void main(String[] args) {
        exercise5e();
    }

    public static void exercise5a() {
        Hill benNevis = new Hill(255, "Ben Nevis", "Highland", 1344.5, 56.796849, -5.003525);
        System.out.println(benNevis);
    }

    public static void exercise5b() {
        List<Hill> hills = readHills();
        for (int i = 0; i < 20; i++) {
            System.out.println(hills.get(i).toString());
        }
    }

    public static void exercise5c() {
        List<Hill> hills = readHills();
        while (true) {
            System.out.print("Please enter a hill name, or \"quit\" to exit: ");
            Scanner scanner = new Scanner(System.in);

            String input = scanner.nextLine();
            if (input.startsWith("quit")) {
                System.out.println("Good-bye");
                break;
            } else {
                for (Hill hill : hills) {
                    if (hill.name.toLowerCase().startsWith(input.toLowerCase())) {
                        System.out.println(hill.toString());
                    }
                }
                System.out.println("");
            }
        }
    }

    public static void exercise5d() {
        List<Hill> hills = readHills();

        System.out.println("Sorting hills by name... First 20 entries:- ");
        Collections.sort(hills);
        for (int i = 0; i < 20; i++) {
            System.out.println(hills.get(i).toString());
        }

        System.out.println("\r\nSorting hills by descending height... First 20 entries:- ");
        Collections.sort(hills, new HeightComparator());
        for (int i = 0; i < 20; i++) {
            System.out.println(hills.get(i).toString());
        }
    }

    public static void exercise5e() {
        List<Hill> hills = readHills();

        Map<String, Set<Hill>> countyHills = Hill.hillsByCounty(hills);
        Iterator<Map.Entry<String, Set<Hill>>> it = countyHills.entrySet().iterator();

        int i = 0, ii = 0;

        while (it.hasNext() && i < 3) {
            ii = 0;

            Map.Entry<String, Set<Hill>> entry = it.next();

            String countyName = entry.getKey();
            Set<Hill> hillSet = entry.getValue();

            Iterator<Hill> setIt = hillSet.iterator();
            
            System.out.printf("### County: %s\r\n", countyName);
            while (setIt.hasNext() && ii < 3) {
                Hill hill = setIt.next();
                System.out.printf("%s %.1f\r\n", hill.name, hill.height);
                ii++;
            }
            i++;
        }
    }

    public static List<Hill> readHills() {
        List<Hill> hillsList = new ArrayList<Hill>();

        try (Scanner file = new Scanner(new File("C:/hills.csv"))) {
            // Skip first line;
            file.nextLine();

            while (file.hasNextLine()) {
                String[] hill = file.nextLine().split(",");

                int number = Integer.parseInt(hill[0]);
                String name = hill[1];
                String county = hill[2];
                double height = Double.parseDouble(hill[3]);
                double latitude = Double.parseDouble(hill[4]);
                double longitude = Double.parseDouble(hill[5]);

                hillsList.add(new Hill(number, name, county, height, latitude, longitude));
            }
        } catch (IOException ex) {
            System.out.println("File not found!");
        }

        return hillsList;
    }
}

class HeightComparator implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        Hill hill1 = (Hill) o1;
        Hill hill2 = (Hill) o2;

        if (hill1.height == hill2.height) {
            return 0;
            // descending order
        } else if (hill2.height > hill1.height) {
            return 1;
        } else {
            return -1;
        }
    }

}
