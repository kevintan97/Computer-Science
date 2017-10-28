package ce152;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Exercise1 {

    private static HashMap<String, Double> priceMedium = new HashMap<>();
    private static HashMap<String, Double> priceLarge = new HashMap<>();

    private static double price = 0.00;
    private static String pizzaString = "";

    public static void main(String[] args) {
        initializePizza();
        // pizzaServiceA();
        pizzaServiceB();
    }

    public static void pizzaServiceA() {
        while (true) {
            // reset the price
            price = 0.00;
            pizzaString = "";

            Scanner input = new Scanner(System.in);
            System.out.print("Please input the pizza ordered: ");
            String pizza = input.next();

            if (pizza.length() > 5) {
                System.out.printf("Invalid input : \"%s\"  \r\n\r\n", pizza);
            } else if (pizza.indexOf("quit") != -1) {
                break;
            } else if (pizza.length() <= 5) {
                String orderSize = pizza.substring(0, 1);
                List<String> toppings = (pizza.length() == 1 ? null : Arrays.asList(pizza.substring(1).split("")));

                // size = 1 > medium | 2 > large
                int size = (orderSize.equals("m") ? 1 : orderSize.equals("l") ? 2 : 0);

                if (size == 0) {
                    System.out.println("Invalid pizza size : " + orderSize + "\r\n");
                } else {
                    try {
                        pizzaString += (size == 1 ? "Medium" : "Large") + " pizza with ";
                        price += (size == 1 ? 4.00 : 5.00);

                        if (toppings != null) {
                            if (size == 1) {
                                for (String str : toppings) {
                                    pizzaString += toppingName(str) + ", ";
                                    price += priceMedium.get(str);
                                }
                            } else if (size == 2) {
                                for (String str : toppings) {
                                    pizzaString += toppingName(str) + ", ";
                                    price += priceLarge.get(str);
                                }
                            }
                        } else {
                            pizzaString += "no toppings ";
                        }

                        System.out.printf("%s $%.2f\r\n\r\n", pizzaString, price);
                        // because HashMaps can throw NPE if there are is no such key in the hashmap,
                        // which means the topping does not exist
                    } catch (NullPointerException npe) {
                        System.out.println("You entered an invalid topping!\r\n");
                    }
                }
            }

            pizzaServiceA();
            break;
        }
    }

    public static void pizzaServiceB() {
        while (true) {
            // reset the price
            price = 0.00;
            pizzaString = "";

            Scanner input = new Scanner(System.in);
            System.out.print("Please input the pizza ordered: ");
            String pizza = input.next();
            boolean isOk = true;

            if (pizza.length() > 5) {
                System.out.printf("Invalid input : \"%s\"  \r\n\r\n", pizza);
            } else if (pizza.indexOf("quit") != -1) {
                break;
            } else if (pizza.length() <= 5) {
                String orderSize = pizza.substring(0, 1);
                List<String> toppings = (pizza.length() == 1 ? null : Arrays.asList(pizza.substring(1).split("")));

                // size = 1 > medium | 2 > large
                int size = (orderSize.equals("m") ? 1 : orderSize.equals("l") ? 2 : 0);

                if (size == 0) {
                    System.out.println("Invalid pizza size : " + orderSize + "\r\n");
                } else {
                    try {
                        pizzaString += (size == 1 ? "Medium" : "Large") + " pizza with ";
                        price += (size == 1 ? 4.00 : 5.00);

                        if (toppings != null) {
                            // count the toppings
                            if (countToppings(toppings)) {
                                if (size == 1) {
                                    for (String str : toppings) {
                                        pizzaString += toppingName(str) + ", ";
                                        price += priceMedium.get(str);
                                    }
                                } else if (size == 2) {
                                    for (String str : toppings) {
                                        pizzaString += toppingName(str) + ", ";
                                        price += priceLarge.get(str);
                                    }
                                }
                            } else {
                                System.out.println("A topping cannot occur more than three times in a pizza!");
                                isOk = false;
                            }
                        } else {
                            pizzaString += "no toppings ";
                        }

                        if (isOk) {
                            System.out.printf("%s $%.2f\r\n\r\n", pizzaString, price);
                        }
                        // because HashMaps can throw NPE if there are is no such key in the hashmap,
                        // which means the topping does not exist
                    } catch (NullPointerException npe) {
                        System.out.println("You entered an invalid topping!\r\n");
                    }
                }
            }

            pizzaServiceB();
            break;
        }
    }

    private static boolean countToppings(List<String> topping) {
        HashMap<String, Integer> count = new HashMap<>();

        for (String str : topping) {
            if (count.containsKey(str)) {
                int amt = count.get(str) + 1;

                if (amt >= 3) {
                    return false;
                } else {
                    count.put(str, amt);
                }
            } else {
                count.put(str, 1);
            }
        }
        return true;
    }

    private static String toppingName(String str) {
        switch (str) {
            case "h":
                return "ham";
            case "m":
                return "mozzarella";
            case "o":
                return "olive";
            case "p":
                return "pineapple";
            case "s":
                return "spinach";
        }
        return "";
    }

    private static void initializePizza() {
        priceMedium.put("h", 1.40);
        priceMedium.put("m", 1.00);
        priceMedium.put("o", 0.80);
        priceMedium.put("p", 1.00);
        priceMedium.put("s", 0.80);

        priceLarge.put("h", 2.10);
        priceLarge.put("m", 1.50);
        priceLarge.put("o", 1.20);
        priceLarge.put("p", 1.50);
        priceLarge.put("s", 1.20);
    }
}		