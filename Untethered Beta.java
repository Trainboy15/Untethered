import java.util.*;

class Item {
    String name;
    int quantity;
    int damage;

    Item(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
        this.damage = parseDamage(name);
    }

    private int parseDamage(String name) {
        if (name.contains("damg")) {
            try {
                return Integer.parseInt(name.replaceAll("[^0-9]", ""));
            } catch (Exception e) {
                return 0;
            }
        }
        return 0;
    }

    boolean isSameItem(Item other) {
        return this.name.equalsIgnoreCase(other.name);
    }

    public String toString() {
        return name + (quantity > 1 ? " x" + quantity : "");
    }
}

class Player {
    List<Item> bag = new ArrayList<>();
    int health = 100;
    Item equippedWeapon;

    void addItem(Item newItem) {
        for (Item item : bag) {
            if (item.isSameItem(newItem)) {
                item.quantity += newItem.quantity;
                return;
            }
        }
        bag.add(newItem);
    }

    void printBag() {
        System.out.println("\nInventory:");
        for (Item item : bag) {
            System.out.println("- " + item);
        }
        if (equippedWeapon != null) {
            System.out.println("Equipped: " + equippedWeapon.name + " (" + equippedWeapon.damage + " dmg)");
        }
        System.out.println("Health: " + health);
    }

    void useItem(String itemName) {
        for (Iterator<Item> it = bag.iterator(); it.hasNext();) {
            Item item = it.next();
            if (item.name.equalsIgnoreCase(itemName) && item.quantity > 0) {
                if (item.name.toLowerCase().contains("potion")) {
                    int healAmount = 15;
                    health = Math.min(health + healAmount, 100);
                    System.out.println("Used " + item.name + "! Healed " + healAmount + " HP.");
                    item.quantity--;
                    if (item.quantity == 0) it.remove();
                    return;
                }
            }
        }
        System.out.println("No usable " + itemName + " found.");
    }

    void equipWeapon(Scanner scanner) {
        List<Item> weapons = getWeapons();
        if (weapons.isEmpty()) {
            System.out.println("You have no weapons to equip.");
            return;
        }

        System.out.println("Choose a weapon to equip:");
        for (int i = 0; i < weapons.size(); i++) {
            System.out.println((i + 1) + ": " + weapons.get(i));
        }

        int choice = scanner.nextInt();
        if (choice >= 1 && choice <= weapons.size()) {
            equippedWeapon = weapons.get(choice - 1);
            System.out.println("Equipped: " + equippedWeapon.name);
        }
    }

    List<Item> getWeapons() {
        List<Item> weapons = new ArrayList<>();
        for (Item item : bag) {
            if (item.damage > 0) {
                weapons.add(item);
            }
        }
        return weapons;
    }

    void dealDamage(Monster monster) {
        if (equippedWeapon != null) {
            System.out.println("You attack with " + equippedWeapon.name + "!");
            monster.takeDamage(equippedWeapon.damage);
        } else {
            System.out.println("You have no weapon equipped! You punch the monster.");
            monster.takeDamage(5); // Basic punch damage
        }
    }
}

class Monster {
    int health;

    Monster(int health) {
        this.health = health;
    }

    void takeDamage(int damage) {
        health -= damage;
        System.out.println("Monster took " + damage + " damage! Remaining health: " + health);
    }

    boolean isDead() {
        return health <= 0;
    }
}

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Player player = new Player();

        // Add initial items
        player.addItem(new Item("Great Sword, 20 damg", 1));
        player.addItem(new Item("Long Bow, 15 damg", 1));
        player.addItem(new Item("50 Ft of Rope", 1));
        player.addItem(new Item("Arrows", 50));
        player.addItem(new Item("Gold", 50));
        player.addItem(new Item("Health Potion", 1));

        // Main game loop
        while (true) {
            player.printBag();

            System.out.println("\nChoose an action: 1 = Move, 2 = Use Item, 3 = Equip Weapon, 4 = Explore");
            int action = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (action) {
                case 1:
                    // Movement code (simplified)
                    System.out.println("Move: 1 = forward, 2 = right, 3 = backward, 4 = left");
                    int move = scanner.nextInt();
                    System.out.println("How many steps?");
                    int steps = scanner.nextInt();
                    System.out.println("You moved " + steps + " steps.");
                    break;
                case 2:
                    // Use item
                    System.out.println("Enter item name to use:");
                    String useItem = scanner.nextLine();
                    player.useItem(useItem);
                    break;
                case 3:
                    // Equip weapon
                    player.equipWeapon(scanner);
                    break;
                case 4:
                    // Explore
                    handleEvent(scanner, player);
                    break;
            }
        }
    }

    static void handleEvent(Scanner scanner, Player player) {
        // Random event generation
        double eventChance = Math.random();
        if (eventChance < 0.3) {
            System.out.println("You found some items!");
            String[] items = {"Arrows", "Gold"};
            String foundItem = items[(int)(Math.random() * items.length)];
            player.addItem(new Item(foundItem, 10));
            System.out.println("You found a " + foundItem + "!");
        } else if (eventChance < 0.6) {
            System.out.println("You encounter an NPC!");
            handleNPC(scanner, player);
        } else {
            System.out.println("A monster appears!");
            Monster monster = new Monster(50);
            while (!monster.isDead() && player.health > 0) {
                System.out.println("Monster health: " + monster.health);
                player.dealDamage(monster);
                if (!monster.isDead()) {
                    System.out.println("The monster attacks!");
                    player.health -= 10;
                    System.out.println("You took 10 damage! Current health: " + player.health);
                }
            }
            if (monster.isDead()) {
                System.out.println("You defeated the monster!");
            } else {
                System.out.println("You were defeated by the monster!");
            }
        }
    }

    static void handleNPC(Scanner scanner, Player player) {
        String[] npcItems = {"Health Potion", "10 Arrows", "50 Gold"};
        System.out.println("An NPC offers you items! Choose what to take:");
        for (int i = 0; i < npcItems.length; i++) {
            System.out.println((i + 1) + ": " + npcItems[i]);
        }
        int choice = scanner.nextInt();
        if (choice >= 1 && choice <= npcItems.length) {
            if (npcItems[choice - 1] == "10 Arrows") {
                player.addItem(new Item("Arrows", 10));
            }
            else if (npcItems[choice - 1] == "50 Gold") {
                player.addItem(new Item("Gold", 10)); 
            }
            else {
                player.addItem(new Item("Health Potion",1));
            }
            
            System.out.println("You received " + npcItems[choice - 1]);
        }
    }
}
