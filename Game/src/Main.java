import java.util.Scanner;

class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Inventory Inventory = new Inventory();
		Gold Gold = new Gold();
		Sword Sword = new Sword();
		Bow Bow = new Bow();
		greet(1,"Sword");
		//Inventory.slot1 = "Name: "+Sword.name+", Damage: "+Sword.damage+", Sell Price: "+Sword.sellPrice+", Nickname: "+Sword.Nickname;
		///System.out.print("Slot 1; "+Inventory.slot1);
		//Inventory.slot2 = "Name: "+Bow.name+", Damage: "+Bow.damage+", Sell Price: "+Bow.sellPrice+", Nickname: "+Bow.Nickname;
	}
	public static void greet(int slot, String item) {
		Inventory.slot1 = slot+item;
		//"Name: "+item.name+", Damage: "+item.damage+", Sell Price: "+item.sellPrice+", Nickname: "+item.Nickname;
	}
}
