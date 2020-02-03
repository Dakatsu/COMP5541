import java.util.*;

public class DietApp {

    // This would eventually be replaced by a database or hashtable.
    static LinkedList<Food> foodDatabase;
    //static LinkedList<Meal> meals;
    // Table that lists which food groups a food is in.
    static Hashtable<Integer, LinkedList<Integer>> foodGroupsTable;
    // List of food groups.
    static LinkedList<String> foodGroups;

    public static void main(String[] args) {

        // Init food database.
        foodDatabase = new LinkedList<Food>();

        // Init food groups table
        foodGroupsTable = new Hashtable<Integer, LinkedList<Integer>>();

        // Init food groups list.
        foodGroups = new LinkedList<String>();
        foodGroups.add("Protein");
        foodGroups.add("Vegetables");
        foodGroups.add("Grains");
        foodGroups.add("Fruit");
        foodGroups.add("Dairy");

        PromptForNewFoodEntry();

        for (int i = 0; i < foodDatabase.size(); i++) {
            Food fItem = foodDatabase.get(i);
            LinkedList<String> fGroups = GetFoodGroupNames(fItem);
            System.out.println("Food " + fItem.name + " has " + fItem.calories + " calories and belongs to the following food groups:\n  " + fGroups.toString());
        }
        System.out.println(foodGroupsTable.toString());
        System.out.println(foodGroups.toString());
    }

    static void PromptForNewFoodEntry() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the name of your new food:\n  ");
        String name = scanner.next();
        System.out.println("Enter the calories:/n  ");
        int cals = scanner.nextInt();
        LinkedList<Integer> groups = new LinkedList<Integer>();
        for (int i = 0; i < foodGroups.size(); i++) {
            System.out.println("Is this food a " + foodGroups.get(i) + "? Y/N \n  ");
            String response = scanner.next();
            if (response.toUpperCase().startsWith("Y")) {
                groups.add(i);
            }
        }

        Food food = new Food(name, cals);
        AddFoodToDatabase(food, groups);
    }

    // Adds food to database and maps its key to the appropriate food group key
    static void AddFoodToDatabase(Food inFood, LinkedList<Integer> inGroups) {
        if (foodDatabase.add(inFood)) {
            int foodKey = foodDatabase.indexOf(inFood);

            foodGroupsTable.put(foodKey, inGroups);
        }
    }

    // Returns food groups as integers.
    static LinkedList<Integer> GetFoodGroupKeys(Food inFood) {
        LinkedList<Integer> groupKeys = new LinkedList<Integer>();
        int foodKey = foodDatabase.indexOf(inFood);
        if (foodKey != -1 && foodGroupsTable.get(foodKey) != null) {
            groupKeys = foodGroupsTable.get(foodKey);
        }
        return groupKeys;
    }

    // Returns food groups as strings.
    static LinkedList<String> GetFoodGroupNames(Food inFood) {
        LinkedList<String> groupNames = new LinkedList<String>();
        LinkedList<Integer> keys = GetFoodGroupKeys(inFood);
        Iterator<Integer> It = keys.iterator();
        while (It.hasNext()) {
            groupNames.add(foodGroups.get(It.next()));
        }
        return groupNames;
    }
}
