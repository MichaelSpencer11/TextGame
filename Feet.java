
public class Feet extends Item {

	public Feet(String name, String description, String desc2, Room room) {
		this.itemName = name;
		this.description = description;
		this.desc2 = desc2;
		this.itemId = itemTracker++;
		this.roomIn = room;
		
		room.getInventory().add(this);
	}
}