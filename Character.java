import java.util.ArrayList;

public class Character {
    protected String name;
    protected String type;
    protected String description;
    protected String pronounString;
    protected enum pronoun{M, F, NB};
    protected String raceString;
    protected enum race{Human, Elf, Orc};
    protected String areaString;
    protected enum area{Land, Sea, Space};
    protected String areaRace;
    protected Familiar familiar;
    protected Character follower;
    
    protected boolean standing;
    protected boolean sitting;
    protected boolean prone;
    protected boolean floating;
    protected boolean following;
    
    protected int str;
    protected int dex;
    protected int end;
    protected int mnd;
    protected int cha;
    protected int luc;
    
    protected Room currentRoom;
    protected boolean asleep;
    protected ArrayList<Item> inventory;
    protected int invLength = 38;
    protected boolean hasName;
    
    protected Item mainHand;
    protected Item offHand;
    protected Item head;
    protected Item hands;
    protected Item body;
    protected Item back;
    protected Item legs;
    protected Item feet;
    protected Item ring1;
    protected Item ring2;
    
    
    public Character() {
    	
    }
    
    //Constructor for characters who do not have predetermined names in the beginning
    //Generally, the player will name these characters later
    public Character(Room firstRoom){
    	this.hasName = false;
    	this.inventory = new ArrayList<Item>();
    	this.asleep = false;
        this.prone = false;
        this.sitting = false;
        this.standing = true;
        firstRoom.people.add(this);
        this.currentRoom = firstRoom;
        
        
    }
    
    //Constructor for named characters
    public Character(String name, String description, Room firstRoom){
    	this.name = name;
    	this.description = description;
    	this.hasName = true;
    	this.inventory = new ArrayList<Item>();
    	this.asleep = false;
        this.prone = false;
        this.sitting = false;
        this.standing = true;
        firstRoom.people.add(this);
        this.currentRoom = firstRoom;
        
    }

    

    public void look(String inputString){
        int count = 0;
        if (inputString.equals("look") || inputString.equals("l")){
        System.out.println("You look around a bit.");
        System.out.println(currentRoom.getDescription());
        System.out.println("The room seems to be of type " + currentRoom.getTerrainType() + ".");
        currentRoom.printItems();
        currentRoom.printChars();
        for (int i = 0; i < currentRoom.getDirs().size(); i++){
            System.out.println("There is a way to the " + currentRoom.getDirs().get(i) + ".");
        }
        return;
        }
        
        for (Item i : currentRoom.getInventory()) {
        	if(inputString.substring(5).equals(i.getItemName())) {
        		System.out.println(i.getDescription());
        		return;
        	}
        }
        
        for (Character c : currentRoom.getPeople()) {
        	if(inputString.substring(5).equals(c.name)) {
        		 System.out.println(c.getDescription());
        		 return;
        	}
        }
        
        if (inputString.substring(0,4).equals("look") && 
        		inputString.substring(5,8).equals("inv")) {
        	for (Item i : this.inventory) {
        		if (inputString.substring(9).equals(i.getItemName())) {
        			System.out.println(i.getDescription() + " " + i.getDesc2());
        			return;
        		}
        	}
        }
        /*
        if(currentRoom.getDoorsNum() == 1 && inputString.equals("look door")){
            System.out.println("There is a door here.");
            if(currentRoom.getDoors()[0].getOpened()){
                System.out.println("The door is open.");
            }
            else {
                System.out.println("The door is closed.");
            }
        }
        else if (currentRoom.getDoorsNum() > 1 && inputString.equals("look door")){
            System.out.println("Which door would you like to look at?");
            return;
        }

        if(inputString.substring(5,9).equals("door") && inputString.substring(10).equals("n")){
            for(Door door : currentRoom.getDoors()){
                if(door.getDoorDir().equals("north") && door.getOpened()){
                    System.out.println("The door to the north is open.");
                    count++;
                }
                else if(door.getDoorDir().equals("north") && !door.getOpened()){
                    System.out.println("The door to the north is closed.");
                    count++;
                }
                
            }
            if(count == 0){
                System.out.println("There is no door in that direction.");
            }
            return;
        }
        else if(inputString.substring(5,9).equals("door") && inputString.substring(10).equals("e") ){
            for(Door door : currentRoom.getDoors()){
                if(door.getDoorDir().equals("east") && door.getOpened()){
                    System.out.println("The door to the east is open.");
                    count++;
                }
                else if(door.getDoorDir().equals("east") && !door.getOpened()){
                    System.out.println("The door to the east is closed.");
                    count++;
                }
                
            }
            if(count == 0){
                System.out.println("There is no door in that direction.");
            }
            return;
        }
        else if(inputString.substring(5,9).equals("door") && inputString.substring(10).equals("s")){
            for(Door door : currentRoom.getDoors()){
                if(door.getDoorDir().equals("south") && door.getOpened()){
                    System.out.println("The door to the south is open.");
                    count++;
                }
                else if(door.getDoorDir().equals("south") && !door.getOpened()){
                    System.out.println("The door to the south is closed.");
                    count++;
                }
                
            }
            if (count == 0){
                System.out.println("There is no door in that direction.");
            }
            
            return;
        }
        else if(inputString.substring(5,9).equals("door") && inputString.substring(10).equals("w") ){
            for(Door door : currentRoom.getDoors()){
                if(door.getDoorDir().equals("west") && door.getOpened()){
                    System.out.println("The door to the west is open.");
                    count++;
                }
                else if(door.getDoorDir().equals("west") && !door.getOpened()){
                    System.out.println("The door to the west is closed.");
                    count++;
                }
                
            }
            if (count == 0){
                System.out.println("There is no door in that direction.");
            }
            return;
        }
        else if(inputString.substring(5,9).equals("door") && inputString.substring(10).equals("ne")){
            for(Door door : currentRoom.getDoors()){
                if(door.getDoorDir().equals("northeast") && door.getOpened()){
                    System.out.println("The door to the northeast is open.");
                    count++;
                }
                else if(door.getDoorDir().equals("northeast") && !door.getOpened()){
                    System.out.println("The door to the northeast is closed.");
                    count++;
                }
                
            }
            if (count == 0){
                System.out.println("There is no door in that direction.");
            }
            return;
        }
        
        else if(inputString.substring(5,9).equals("door") && inputString.substring(10).equals("se") ){
            for(Door door : currentRoom.getDoors()){
                if(door.getDoorDir().equals("southeast") && door.getOpened()){
                    System.out.println("The door to the southeast is open.");
                    count++;
                }
                else if(door.getDoorDir().equals("southeast") && !door.getOpened()){
                    System.out.println("The door to the southeast is closed.");
                    count++;
                }
                
            }
            if (count == 0){
                System.out.println("There is no door in that direction.");
            }
            return;
        }
        
        else if(inputString.substring(5,9).equals("door") && inputString.substring(10).equals("sw") ){
            for(Door door : currentRoom.getDoors()){
                if(door.getDoorDir().equals("southwest") && door.getOpened()){
                    System.out.println("The door to the southwest is open.");
                    count++;
                }
                else if(door.getDoorDir().equals("southwest") && !door.getOpened()){
                    System.out.println("The door to the southwest is closed.");
                    count++;
                }
                
            }
            if (count == 0){
                System.out.println("There is no door in that direction.");
            }
            return;
        }
        
        else if(inputString.substring(5,9).equals("door") && inputString.substring(10).equals("nw") ){
            for(Door door : currentRoom.getDoors()){
                if(door.getDoorDir().equals("northwest") && door.getOpened()){
                    System.out.println("The door to the northwest is open.");
                    count++;
                }
                else if(door.getDoorDir().equals("northwest") && !door.getOpened()){
                    System.out.println("The door to the northwest is closed.");
                    count++;
                }
                
            }
            if (count == 0){
                System.out.println("There is no door in that direction.");
            }
            return;
        }
        else {
            System.out.println("There is no door in that direction.");
        }
        */
    }

    public void open(String inputString) {
        //check if the door is open or closed
        //check if the door is locked
        //open door
        if(inputString.substring(5,9).equals("door") && inputString.substring(10).equals("n")){
            for(Door door : currentRoom.getDoors()){
            if(door.getDoorDir().equals("north") && door.getLocked()){
                System.out.println("The door to the north is locked.");
                return;
            }
            if(door.getDoorDir().equals("north") && door.getOpened()){
                System.out.println("The door to the north is already open.");
                return;
            }
            else if(door.getDoorDir().equals("north") && !door.getOpened()){
                System.out.println("You open the door to the north.");
                door.setOpened();
                return;
            }

            }
        }
        if(inputString.substring(5,9).equals("door") && inputString.substring(10).equals("ne")){
            for(Door door : currentRoom.getDoors()){
            if(door.getDoorDir().equals("northeast") && door.getLocked()){
                System.out.println("The door to the northeast is locked.");
                return;
            }
            if(door.getDoorDir().equals("northeast") && door.getOpened()){
                System.out.println("The door to the northeast is already open.");
                return;
            }
            else if(door.getDoorDir().equals("northeast") && !door.getOpened()){
                System.out.println("You open the door to the northeast.");
                door.setOpened();
                return;
            }

            }
        }
        if(inputString.substring(5,9).equals("door") && inputString.substring(10).equals("e")){
            for(Door door : currentRoom.getDoors()){
            if(door.getDoorDir().equals("east") && door.getLocked()){
                System.out.println("The door to the east is locked.");
                return;
            }
            if(door.getDoorDir().equals("east") && door.getOpened()){
                System.out.println("The door to the east is already open.");
                return;
            }
            else if(door.getDoorDir().equals("east") && !door.getOpened()){
                System.out.println("You open the door to the east.");
                door.setOpened();
                return;
            }

            }
        }
        if(inputString.substring(5,9).equals("door") && inputString.substring(10).equals("se")){
            for(Door door : currentRoom.getDoors()){
            if(door.getDoorDir().equals("southeast") && door.getLocked()){
                System.out.println("The door to the southeast is locked.");
                return;
            }
            if(door.getDoorDir().equals("southeast") && door.getOpened()){
                System.out.println("The door to the southeast is already open.");
                return;
            }
            else if(door.getDoorDir().equals("southeast") && !door.getOpened()){
                System.out.println("You open the door to the southeast.");
                door.setOpened();
                return;
            }

            }
        }
        if(inputString.substring(5,9).equals("door") && inputString.substring(10).equals("s")){
            for(Door door : currentRoom.getDoors()){
            if(door.getDoorDir().equals("south") && door.getLocked()){
                System.out.println("The door to the south is locked.");
                return;
            }
            if(door.getDoorDir().equals("south") && door.getOpened()){
                System.out.println("The door to the south is already open.");
                return;
            }
            else if(door.getDoorDir().equals("south") && !door.getOpened()){
                System.out.println("You open the door to the south.");
                door.setOpened();
                return;
            }

            }
        }
        if(inputString.substring(5,9).equals("door") && inputString.substring(10).equals("sw")){
            for(Door door : currentRoom.getDoors()){
            if(door.getDoorDir().equals("southwest") && door.getLocked()){
                System.out.println("The door to the southwest is locked.");
                return;
            }
            if(door.getDoorDir().equals("southwest") && door.getOpened()){
                System.out.println("The door to the southwest is already open.");
                return;
            }
            else if(door.getDoorDir().equals("southwest") && !door.getOpened()){
                System.out.println("You open the door to the southwest.");
                door.setOpened();
                return;
            }

            }
        }
        if(inputString.substring(5,9).equals("door") && inputString.substring(10).equals("w")){
            for(Door door : currentRoom.getDoors()){
            if(door.getDoorDir().equals("west") && door.getLocked()){
                System.out.println("The door to the west is locked.");
                return;
            }
            if(door.getDoorDir().equals("west") && door.getOpened()){
                System.out.println("The door to the west is already open.");
                return;
            }
            else if(door.getDoorDir().equals("west") && !door.getOpened()){
                System.out.println("You open the door to the west.");
                door.setOpened();
                return;
            }

            }
        }
        if(inputString.substring(5,9).equals("door") && inputString.substring(10).equals("nw")){
            for(Door door : currentRoom.getDoors()){
            if(door.getDoorDir().equals("northwest") && door.getLocked()){
                System.out.println("The door to the northwest is locked.");
                return;
            }
            if(door.getDoorDir().equals("northwest") && door.getOpened()){
                System.out.println("The door to the northwest is already open.");
                return;
            }
            else if(door.getDoorDir().equals("northwest") && !door.getOpened()){
                System.out.println("You open the door to the north.");
                door.setOpened();
                return;
            }

            }
        }

    }

    public void move(String inputString){
        if (inputString.equals("n")){
                if(currentRoom.getHasN()){
                	if(this.follower == null) {
                		currentRoom.people.remove(this);
                		this.setCurrentRoom(currentRoom.getnRoom());
                		currentRoom.people.add(this);
                		System.out.println("You move to the north.");
                	} 
                	else {
                		currentRoom.people.remove(this);
                		currentRoom.people.remove(follower);
                		this.setCurrentRoom(currentRoom.getnRoom());
                		this.follower.setCurrentRoom(currentRoom.getnRoom());
                		currentRoom.people.add(this);
                		currentRoom.people.add(follower);
                		System.out.println("You move to the north and " + follower.getName() + " follows you.");
                	}
                }
        }
        
        if (inputString.equals("ne")){
            if(currentRoom.getHasNE()){
            	if(this.follower == null) {
            		currentRoom.people.remove(this);
            		this.setCurrentRoom(currentRoom.getNeRoom());
            		currentRoom.people.add(this);
            		System.out.println("You move to the northeast.");
            	} 
            	else {
            		currentRoom.people.remove(this);
            		currentRoom.people.remove(follower);
            		this.setCurrentRoom(currentRoom.getNeRoom());
            		follower.setCurrentRoom(currentRoom.getNeRoom());
            		currentRoom.people.add(this);
            		currentRoom.people.add(follower);
            		System.out.println("You move to the northeast and " + follower.getName() + " follows you.");
            	}
            }
        }
        
        if (inputString.equals("e")){
            if(currentRoom.getHasE()){
            	if(this.follower == null) {
            		currentRoom.people.remove(this);
            		this.setCurrentRoom(currentRoom.geteRoom());
            		currentRoom.people.add(this);
            		System.out.println("You move to the east.");
            	} 
            	else {
            		currentRoom.people.remove(this);
            		currentRoom.people.remove(follower);
            		this.setCurrentRoom(currentRoom.geteRoom());
            		follower.setCurrentRoom(currentRoom.geteRoom());
            		currentRoom.people.add(this);
            		currentRoom.people.add(follower);
            		System.out.println("You move to the east and " + follower.getName() + " follows you.");
            	}
            }
        }
        
        if (inputString.equals("se")){
            if(currentRoom.getHasSE()){
            	if(this.follower == null) {
            		currentRoom.people.remove(this);
            		this.setCurrentRoom(currentRoom.getSeRoom());
            		currentRoom.people.add(this);
            		System.out.println("You move to the southeast.");
            	} 
            	else {
            		currentRoom.people.remove(this);
            		currentRoom.people.remove(follower);
            		this.setCurrentRoom(currentRoom.getSeRoom());
            		follower.setCurrentRoom(currentRoom.getSeRoom());
            		currentRoom.people.add(this);
            		currentRoom.people.add(follower);
            		System.out.println("You move to the southeast and " + follower.getName() + " follows you.");
            	}
            }
        }
        
        if (inputString.equals("s")){
            if(currentRoom.getHasS()){
            	if(this.follower == null) {
            		currentRoom.people.remove(this);
            		this.setCurrentRoom(currentRoom.getsRoom());
            		currentRoom.people.add(this);
            		System.out.println("You move to the south.");
            	} 
            	else {
            		currentRoom.people.remove(this);
            		currentRoom.people.remove(follower);
            		this.setCurrentRoom(currentRoom.getsRoom());
            		follower.setCurrentRoom(currentRoom.getsRoom());
            		currentRoom.people.add(this);
            		currentRoom.people.add(follower);
            		System.out.println("You move to the south and " + follower.getName() + " follows you.");
            	}
            }
        }
        
        if (inputString.equals("sw")){
            if(currentRoom.getHasSW()){
            	if(this.follower == null) {
            		currentRoom.people.remove(this);
            		this.setCurrentRoom(currentRoom.getSwRoom());
            		currentRoom.people.add(this);
            		System.out.println("You move to the southwest.");
            	} 
            	else {
            		currentRoom.people.remove(this);
            		currentRoom.people.remove(follower);
            		this.setCurrentRoom(currentRoom.getSwRoom());
            		follower.setCurrentRoom(currentRoom.getSwRoom());
            		currentRoom.people.add(this);
            		currentRoom.people.add(follower);
            		System.out.println("You move to the southwest and " + follower.getName() + " follows you.");
            	}
            }
        }
        
        if (inputString.equals("w")){
            if(currentRoom.getHasW()){
            	if(this.follower == null) {
            		currentRoom.people.remove(this);
            		this.setCurrentRoom(currentRoom.getwRoom());
            		currentRoom.people.add(this);
            		System.out.println("You move to the west.");
            	} 
            	else {
            		currentRoom.people.remove(this);
            		currentRoom.people.remove(follower);
            		this.setCurrentRoom(currentRoom.getwRoom());
            		follower.setCurrentRoom(currentRoom.getwRoom());
            		currentRoom.people.add(this);
            		currentRoom.people.add(follower);
            		System.out.println("You move to the west and " + follower.getName() + " follows you.");
            	}
            }
        }
        
        if (inputString.equals("nw")){
            if(currentRoom.getHasNW()){
            	if(this.follower == null) {
            		currentRoom.people.remove(this);
            		this.setCurrentRoom(currentRoom.getNwRoom());
            		currentRoom.people.add(this);
            		System.out.println("You move to the northwest.");
            	} 
            	else {
            		currentRoom.people.remove(this);
            		currentRoom.people.remove(follower);
            		this.setCurrentRoom(currentRoom.getNwRoom());
            		follower.setCurrentRoom(currentRoom.getNwRoom());
            		currentRoom.people.add(this);
            		currentRoom.people.add(follower);
            		System.out.println("You move to the northwest and " + follower.getName() + " follows you.");
            	}
            }
        }
        
        if (inputString.equals("u")){
            if(currentRoom.getHasU()){
            	if(this.follower == null) {
            		currentRoom.people.remove(this);
            		this.setCurrentRoom(currentRoom.getuRoom());
            		currentRoom.people.add(this);
            		System.out.println("You move up.");
            	} 
            	else {
            		currentRoom.people.remove(this);
            		currentRoom.people.remove(follower);
            		this.setCurrentRoom(currentRoom.getuRoom());
            		follower.setCurrentRoom(currentRoom.getuRoom());
            		currentRoom.people.add(this);
            		currentRoom.people.add(follower);
            		System.out.println("You move up and " + follower.getName() + " follows you.");
            	}
            }
        }
        
        if (inputString.equals("d")){
            if(currentRoom.getHasD()){
            	if(this.follower == null) {
            		currentRoom.people.remove(this);
            		this.setCurrentRoom(currentRoom.getdRoom());
            		currentRoom.people.add(this);
            		System.out.println("You move down.");
            	} 
            	else {
            		currentRoom.people.remove(this);
            		currentRoom.people.remove(follower);
            		this.setCurrentRoom(currentRoom.getdRoom());
            		follower.setCurrentRoom(currentRoom.getdRoom());
            		currentRoom.people.add(this);
            		currentRoom.people.add(follower);
            		System.out.println("You move down and " + follower.getName() + " follows you.");
            	}
            }
        }

    }
    
    public void take(String inputString) {
    	if (currentRoom.getInventory().size() == 0) {
    		System.out.println("There is nothing here to take.");
    	} else {
    	for (Item i : currentRoom.getInventory()) {
    		if(inputString.substring(5).equals(i.getItemName()) && i.typeToString().equals("Chest")) {
    			System.out.println("You cannot pick up this chest.");
    		}
    		else if(inputString.substring(5).equals(i.getItemName())) {
        		this.inventory.add(i);
        		System.out.println("You take the " + i.getItemName() + ".");
        		currentRoom.getInventory().remove(i);
        		return;
        	}
        }
    	}
    }                            
    
    public void printInv() {
    	System.out.println("/`^^~~vv.._,_,..vv~~^^~~vv..,_,_..vv~~^^`\\");
    	System.out.println("|                Inventory               |");
    	System.out.println("|                                        |");
    	for (Item i : this.inventory) {
    		if (i.equipped) {
    			System.out.print("| [e]" + i.getItemName());
    			for (int j = 0; j < (this.invLength - i.getItemName().length()) - 3; j++) {
        			System.out.print(" ");
        		}
    			System.out.println(" |");
    		} else {
    		System.out.print("| " + i.getItemName());
    		for (int j = 0; j < (this.invLength - i.getItemName().length()); j++) {
    			System.out.print(" ");
    		}
    		System.out.println(" |");
    		}
    	}
    	System.out.println("|                                        |");
    	System.out.println("\\v~~^^\"'``\"'``''^^~~vv~~^^'\"``'\"``'\"^^~~v/");
    }
    
    public void equip(String inputString) {
    	for (Item i : this.inventory) {
    		if(inputString.substring(6).equals(i.getItemName())) {
    			if(i.typeToString().equals("Weapon") && this.mainHand == null)  {
    				this.mainHand = i;
    				System.out.println("You equip the " + i.getItemName() + ".");
    				i.equipped = true;
    				return;
    			} else if (i.typeToString().equals("Weapon") && this.mainHand != null){
    				System.out.println("You're already holding something in your main hand.");
    				return;
    				
    			}
    			
    			if(i.typeToString().equals("Head") && this.head == null){
    				this.head = i;
    				System.out.println("You equip the " + i.getItemName() + ".");
    				i.equipped = true;
    				return;
    			} else if(i.typeToString().equals("Head") && this.head != null) {
    				System.out.println("Your head already has something on it.");
    				return;
    			}
    			if(i.typeToString().equals("Hands") && this.hands == null){
    				this.hands = i;
    				System.out.println("You equip the " + i.getItemName() + ".");
    				i.equipped = true;
    				return;
    			} else if (i.typeToString().equals("Hands") && this.hands != null) {
    				System.out.println("Your hands are already adorned.");
    				return;
    			}
    			if(i.typeToString().equals("Body") && this.body == null){
    				this.body = i;
    				System.out.println("You equip the " + i.getItemName() + ".");
    				i.equipped = true;
    				return;
    			} else if (i.typeToString().equals("Body") && this.body != null) {
    				System.out.println("You're already wearing something on your body.");
    				return;
    			}
    			if(i.typeToString().equals("Back") && this.back == null){
    				this.back = i;
    				System.out.println("You equip the " + i.getItemName() + ".");
    				i.equipped = true;
    				return;
    			} else if(i.typeToString().equals("Back") && this.back != null) {
    				System.out.println("Your back is already adorned with something.");
    				return;
    			}
    			if(i.typeToString().equals("Legs") && this.legs == null){
    				this.legs = i;
    				System.out.println("You equip the " + i.getItemName() + ".");
    				i.equipped = true;
    				return;
    			} else if (i.typeToString().equals("Legs") && this.legs != null) {
    				System.out.println("You're already wearing something on your legs.");
    				return;
    			}
    			if(i.typeToString().equals("Feet") && this.feet == null){
    				this.feet = i;
    				System.out.println("You equip the " + i.getItemName() + ".");
    				i.equipped = true;
    				return;
    			} else if (i.typeToString().equals("Feet") && this.feet != null) {
    				System.out.println("Your feet are already shod with something.");
    				return;
    			}
    			if(i.typeToString().equals("Ring") && this.ring1 == null){
    				this.ring1 = i;
    				System.out.println("You equip the " + i.getItemName() + ".");
    				i.equipped = true;
    				return;
    			}
    			if(i.typeToString().equals("Ring") && this.ring1 != null){
    				this.ring2 = i;
    				System.out.println("You equip the " + i.getItemName() + ".");
    				i.equipped = true;
    				return;
    			}
    			if (i.typeToString().equals("Ring") && this.ring1 != null && this.ring2 != null) {
    				System.out.println("You can't wear any more rings.");
    			}
    			
    			
    		}
    	}
    }
    
    public void unequip(String inputString) {
    	for (Item i : this.inventory) {
    		if(inputString.substring(8).equals(i.getItemName()) && i.equipped == true) {
    			if(i.typeToString().equals("Weapon") && this.mainHand == i) {
    				System.out.println("You unequip the " + i.getItemName() + ".");
    				i.equipped = false;
    				this.mainHand = null;
    			}
    			else if(i.typeToString().equals("Head") && this.head == i) {
    				System.out.println("You unequip the " + i.getItemName() + ".");
    				i.equipped = false;
    				this.head = null;
    			}
    			else if(i.typeToString().equals("Hands") && this.hands == i) {
    				System.out.println("You unequip the " + i.getItemName() + ".");
    				i.equipped = false;
    				this.hands = null;
    			}
    			else if(i.typeToString().equals("Body") && this.body == i) {
    				System.out.println("You unequip the " + i.getItemName() + ".");
    				i.equipped = false;
    				this.body = null;
    			}
    			else if(i.typeToString().equals("Back") && this.back == i) {
    				System.out.println("You unequip the " + i.getItemName() + ".");
    				i.equipped = false;
    				this.back = null;
    			}
    			else if(i.typeToString().equals("Legs") && this.legs == i) {
    				System.out.println("You unequip the " + i.getItemName() + ".");
    				i.equipped = false;
    				this.legs = null;
    			}
    			else if(i.typeToString().equals("Feet") && this.feet == i) {
    				System.out.println("You unequip the " + i.getItemName() + ".");
    				i.equipped = false;
    				this.feet = null;
    			}
    			else if(i.typeToString().equals("Ring") && this.ring1 == i) {
    				System.out.println("You unequip the " + i.getItemName() + ".");
    				i.equipped = false;
    				this.ring1 = null;
    			}
    			else if(i.typeToString().equals("Ring") && this.ring2 == i) {
    				System.out.println("You unequip the " + i.getItemName() + ".");
    				i.equipped = false;
    				this.ring2 = null;
    			}
    		}
    	}
    }
    
    public void drop(String inputString) {
    	for(Item i : inventory) {
    		if (inputString.substring(5).equals(i.getItemName())) {
    			currentRoom.getInventory().add(i);
    			this.inventory.remove(i);
    			System.out.println("You drop the " + i.getItemName() + ".");
    		}
    	}
    }
    
    public void give(String inputString) {
    	Item currentItem;
    	for (Item i : this.inventory) {
    		if(i.getItemName().equals(inputString.substring(5,inputString.indexOf("to") - 1 ))) {
    			currentItem = i;
    			this.inventory.remove(currentItem);
    			for (Character c : currentRoom.people) {
    	    		if(c.getName().equals(inputString.substring(inputString.indexOf("to") + 3 ))) {
    	    			c.inventory.add(currentItem);
    	    			System.out.println("You give the " + currentItem.getItemName() + " to " + c.getName() + ".");
    	    		}
    	    	}
    			break;
    		}
    	}
    }
    
    public void talk(String inputString) {
    	for (Character c : currentRoom.getPeople()) {
    		if(inputString.substring(5).toLowerCase().equals(c.getName())){
    			c.talk();
    		}
    	}
    }
    
    public void followMe(String inputString) {
    	for(Character c : currentRoom.people) {
    		if(inputString.substring(10).equals(c.getName()) && c.typeToString().equals("Familiar")) {
    			c.setFollowing(true);
    			follower = c;
    			System.out.println(c.name + ": Ok, I'll go with you.");
    		}
    	}
    }

    
    public void unFollowMe(String inputString) {
    	for(Character c : currentRoom.people) {
    		if(inputString.substring(12).equals(c.getName())) {
    			c.setFollowing(false);
    			follower = null;
    			System.out.println(c.name + ": I'll just be waiting right here then.");
    		}
    	}
    }
    
    public void talk() {
    	
    }
    
    public void stand() {
    	if(sitting || prone) {
    		sitting = false;
    		prone = false;
    		standing = true;
    		System.out.println("You stand up.");
    	}
    }
    
    public void sit() {
    	if(standing) {
    		standing = false;
    		sitting = true;
    		System.out.println("You take a seat.");
    	}
    	
    	if(sitting) {
    		sitting = false;
    		standing = true;
    		System.out.println("You stand up.");
    	}
    	
    	if(prone) {
    		prone = false;
    		sitting = true;
    		System.out.println("You sit up.");
    	}
    	
    }
    
    
    

    public void closeDoor(Door door){
        door.setClosed();
        System.out.println("You close the door.");
    }

    public void setCurrentRoom(Room newRoom) {
    	this.currentRoom = newRoom;
    }
    
    public String getName(){
        return this.name;
    }
    
    public String getDescription() {
    	return description;
    }
    
    public boolean getHasName() {
    	return this.hasName;
    }
    
    public String typeToString(){
		return this.getClass().toString().substring(6);
	}
    
    public boolean getFollowing() {
		return this.following;
	}
	
	public void setFollowing(boolean following) {
		this.following = following;
	}

    //public String getPronoun() {
        
    //}

    public int getStr(){
        return this.str;
    }

    public int getDex(){
        return this.dex;
    }

    public int getCon(){
        return this.end;
    }

    public int getMnd(){
        return this.mnd;
    }

    public int getCha(){
        return this.cha;
    }

    public void setStr(int newStr){
        this.str = newStr;
    }

    public void setDex(int newDex){
        this.str = newDex;
    }

    public void setEnd(int newEnd){
        this.end = newEnd;
    }

    public void setMnd(int newMnd){
        this.mnd = newMnd;
    }

    public void setCha(int newCha){
        this.cha = newCha;
    }
    
    
}
