package machine;

import javax.crypto.Mac;
import java.util.Scanner;

public class Machine {

    MachineState state;
    int water, milk, coffee, cups, money;

    public Machine(int water, int milk, int coffee, int cups, int money) {
        this.water = water;
        this.milk = milk;
        this.coffee = coffee;
        this.cups = cups;
        this.money = money;

        setMainState();
    }


    boolean isWorking() {
        return state != MachineState.OFF;
    }

    public void setState(String command) {
        switch (command) {
            case "buy":
                System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3- cappuccino, back - to main menu:");
                state = MachineState.BUYING;
                break;
            case "fill":
                System.out.println("Write how many ml of water you want to add: ");
                state = MachineState.FILLING_WATER;
                break;
            case "take":
                System.out.println("I give you " + money);
                money = 0;
                setMainState();
                break;
            case "remaining":
                displ();
                setMainState();
                break;
            case "exit":
                state = MachineState.OFF;
                break;
            default:
                System.out.println("Unexpected action!");
                setMainState();
                break;
        }
    }

    public void execute(String input) {
        switch (state) {
            case MAIN:
                setState(input);
                break;
            case FILLING_WATER:
                water += Integer.parseInt(input);
                state = MachineState.FILLING_MILK;
                System.out.println("Write how many ml of milk do you want to add: ");
                break;
            case FILLING_MILK:
                milk += Integer.parseInt(input);
                state = MachineState.FILLING_COFFE;
                System.out.println("Write how many grams of coffee beans do you want to add: ");
                break;
            case FILLING_COFFE:
                coffee += Integer.parseInt(input);
                state = MachineState.FILLING_CUPS;
                System.out.println("Write how many disposable cups of coffee do you want to add: ");
                break;
            case FILLING_CUPS:
                cups += Integer.parseInt(input);
                setMainState();
                break;
            case BUYING:
                handleBuying(input);
                setMainState();
                break;
            default:
                break;
        }
    }

    public void handleBuying(String input) {
        switch (input) {
            case "1":
                if (water >= CoffeTypes.ESPRESSO.getWater() && coffee >= CoffeTypes.ESPRESSO.getCoffe() && cups > 0) {
                    cups--;
                    water -= CoffeTypes.ESPRESSO.getWater();
                    coffee -= CoffeTypes.ESPRESSO.getCoffe();
                    money += CoffeTypes.ESPRESSO.getPrice();
                    System.out.println("I have enough resources, making you a coffee!");
                } else if (water < CoffeTypes.ESPRESSO.getWater()) {
                    System.out.println("Sorry, not enough water!");
                } else if (coffee < CoffeTypes.ESPRESSO.getCoffe()) {
                    System.out.println("Sorry, not enough coffee beans!");
                } else if (cups == 0) {
                    System.out.println("Sorry, not enough cups!");
                }
                break;
            case "2":
                if (water >= CoffeTypes.LATTE.getWater() && milk >= CoffeTypes.LATTE.getMilk() && coffee >= CoffeTypes.LATTE.getCoffe() && cups > 0) {
                    cups--;
                    water -= CoffeTypes.LATTE.getWater();
                    milk -= CoffeTypes.LATTE.getMilk();
                    coffee -= CoffeTypes.LATTE.getCoffe();
                    money += CoffeTypes.LATTE.getPrice();
                    System.out.println("I have enough resources, making you a coffee!");
                } else if (water < CoffeTypes.LATTE.getWater()) {
                    System.out.println("Sorry, not enough water!");
                } else if (milk < CoffeTypes.LATTE.getMilk()) {
                    System.out.println("Sorry, not enough milk!");
                } else if (coffee < CoffeTypes.LATTE.getCoffe()) {
                    System.out.println("Sorry, not enough coffee beans!");
                } else if (cups == 0) {
                    System.out.println("Sorry, not enough cups!");
                }
                break;
            case "3":
                if (water >= CoffeTypes.CAPPUCINO.getWater() && milk >= CoffeTypes.CAPPUCINO.getMilk() && coffee >= CoffeTypes.CAPPUCINO.getCoffe() && cups > 0) {
                    cups--;
                    water -= CoffeTypes.CAPPUCINO.getWater();
                    milk -= CoffeTypes.CAPPUCINO.getMilk();
                    coffee -= CoffeTypes.CAPPUCINO.getCoffe();
                    money += CoffeTypes.CAPPUCINO.getPrice();
                    System.out.println("I have enough resources, making you a coffee!");
                } else if (water < CoffeTypes.CAPPUCINO.getWater()) {
                    System.out.println("Sorry, not enough water!");
                } else if (milk < CoffeTypes.CAPPUCINO.getMilk()) {
                    System.out.println("Sorry, not enough milk!");
                } else if (coffee < CoffeTypes.CAPPUCINO.getCoffe()) {
                    System.out.println("Sorry, not enough coffee beans!");
                } else if (cups == 0) {
                    System.out.println("Sorry, not enough cups!");
                }
                break;
        }

    }

    public void setMainState() {
        state = MachineState.MAIN;
        System.out.println("Write action (buy, fill, take, remaining, exit): ");
    }

    public void displ() {
        System.out.print("The coffee machine has:\n");
        System.out.printf("%d ml of water\n", water);
        System.out.printf("%d ml of milk\n", milk);
        System.out.printf("%d g of coffee beans\n", coffee);
        System.out.printf("%d disposable cups\n", cups);
        System.out.printf("%d$ of money\n\n", money);
    }
}
