import java.util.ArrayList;
import java.util.Scanner;
public class App {
    public static void main(String[] args) throws Exception {
        var bag = new ArrayList<>();
        bag.add("Great Sword, 7 damg");
        bag.add("Long Bow, 5 damg");
        bag.add("50 Ft of Rope");
        bag.add("20 Arrows");
        bag.add("50 Gold");

        int health = 100;
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("In your bag, you have:" + bag + "Health: \r" + health + ", 1 for forward, 2 for right, 3 for backward, 4 for left. Max is 10");
            int userInput = scanner.nextInt();
            switch (userInput) {
                case 1:
                System.out.println("How many steps forward do you want to move?");
                break;

                case 2:
                System.out.println("How many steps to the right do you want to move?");
                break;

                case 3:
                System.out.println("How many steps backward do you want to move?");

                break;

                case 4:
                System.out.println("How many steps to the left do you want to move?");

            }
            
            double h = Math.random()/Math.nextDown(1.0);
            double s = 1*(1.0 - h) + 10*h;
            int stepsToMatch = (int) Math.round(s);
            System.out.println(stepsToMatch);
            userInput = scanner.nextInt();
            
            if (userInput == stepsToMatch) {
            
            double u = Math.random()/Math.nextDown(1.0);
            double r = 1*(1.0 - u) + 4*u;
            int eventTrigger = (int) Math.round(r);
            switch (eventTrigger) { 
                case 1:
                    var NPC1Shop = new ArrayList<>();
                    NPC1Shop.add("50 Ft of Rope");
                    NPC1Shop.add("20 Arrows");
                    System.out.println("NPC1 Diag: Hello adventurer! Would you like to buy something? 1 = Yes 2 = No");
                     userInput = scanner.nextInt();
                    if (userInput == 1) {
                        System.out.println(NPC1Shop);
                        System.out.println("What would you like to buy");
                        userInput = scanner.nextInt();
                        
                        if (userInput == 1){
                            bag.add(NPC1Shop.get(0));
                        } 
                        if (userInput == 2){
                            bag.add(NPC1Shop.get(1));
                        }
                        
                    } else{
                        System.out.println("Oh, Ok. Come back some other time");
                    }
                    
                    
                break;
                
                case 2:
                     var NPC2Shop = new ArrayList<>();
                    NPC2Shop.add("5 Bombs");
                    NPC2Shop.add("15 Damg. Instant Damg. potion");
                    System.out.println("NPC2 Diag: Hello!  Buy something would'ya! 1 = Yes 2 = No");
                     userInput = scanner.nextInt();
                    if (userInput == 1) {
                        System.out.println(NPC2Shop);
                        System.out.println("What would you like to buy");
                        userInput = scanner.nextInt();
                        
                        if (userInput == 1){
                            bag.add(NPC2Shop.get(0));
                        } 
                        if (userInput == 2){
                            bag.add(NPC2Shop.get(1));
                        }
                        
                    } else{
                        System.out.println("Come back soon would'ya");
                    }
                    
                break;
                
                case 3:
                    var NPC3Shop = new ArrayList<>();
                    NPC3Shop.add("Health Potion");
                    NPC3Shop.add("10 Arrows");
                    System.out.println("NPC3 Diag: Hello friend! Would you like to buy something? 1 = Yes 2 = No");
                     userInput = scanner.nextInt();
                    if (userInput == 1) {
                        System.out.println(NPC3Shop);
                        System.out.println("What would you like to buy");
                        userInput = scanner.nextInt();
                        
                        if (userInput == 1){
                            bag.add(NPC3Shop.get(0));
                        } 
                        if (userInput == 2){
                            bag.add(NPC3Shop.get(1));
                        }
                        
                    } else{
                        System.out.println("Oh, Ok. Come back some other soon");
                    }
                    
                    break;
                    
                    case 4:
                        double t = Math.random()/Math.nextDown(1.0);
                         double b = 5*(1.0 - t) + 15*t;
                         int monsterHealth = (int) Math.round(b);
                        System.out.println("You encounterd a monster with " + monsterHealth + " health, what do you do? 1: " + bag.get(0) + " 2:" + bag.get(1) + "3" );
                        
                         double w = Math.random()/Math.nextDown(1.0); 
                                double m = 1*(1.0 - w) + 4*w;
                                int monsterCritChance = (int) Math.round(m);
                        userInput = scanner.nextInt();
                        switch (userInput) {
                            case 1:
                            
                                if(monsterCritChance == 2){
                                    System.out.println("Crit! 10 dmag.");
                                    System.out.println("Monster is at " + (monsterHealth - 10) + "health");
                                    monsterHealth = monsterHealth - 10;
                                } else {
                                    System.out.println(" 7 dmag.");
                                  System.out.println("Monster is at " + (monsterHealth - 7) + "health" );
                                  monsterHealth = monsterHealth - 7;
                                }
                            
                                break;

                                case 2:
                                if(monsterCritChance == 2){
                                    System.out.println("Crit! 7 dmag.");
                                    System.out.println("Monster is at " + (monsterHealth - 7) + "health");
                                   
                                } else {
                                     System.out.println(" 5 dmag.");
                                  System.out.println("Monster is at " + (monsterHealth - 5) + "health" );
                                }
                                
                        }

                        if (monsterHealth > 0) {
                            System.out.println("Monster did" + (monsterHealth - 2) + "damage to you!");
                            health = monsterHealth + health;

                        } else {
                            System.out.println("You killed the monster!");
                        } 


                    
                    break;    
                     }
                    } else {
                    double y = Math.random()/Math.nextDown(1.0);
                    double g = 0*(1.0 - y) + 4*y;
                    int randItem = (int) Math.round(g);
                    
                    var groundItems = new ArrayList<>();
                    groundItems.add("Dirt");
                    groundItems.add("A Stick");
                    groundItems.add("Nothing");
                    groundItems.add("5 Arrows");
                    groundItems.add("10 Gold");
                    
                    System.out.println("You got: " + groundItems.get(randItem) +"!");
                    if (groundItems.get(randItem) == "Nothing") {} 
                    else {bag.add(groundItems.get(randItem));}
            }  
      }
        }
     }
  }     
