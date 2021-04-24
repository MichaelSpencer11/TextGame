public class Door {
    private enum dirs{N, NE, E, SE, S, SW, W, NW, U, D};
    private static int DoorIdTracker;
    private int DoorId;
    private String doorDir1;
	private String doorDir2;
    private boolean isOpened;
    private boolean isLocked;
    private String doorType;

    public Door(String dir1, String dir2){
        this.DoorId = DoorIdTracker++;
        this.setClosed();
        this.doorDir1 = dir1;
        this.doorDir2 = dir2;
        
    }

    public boolean getOpened(){
        return isOpened;
    }

    public boolean getLocked(){
        return isLocked;
    }

    public void setOpened(){
        isOpened = true;
    }

    public void setClosed(){
        isOpened = false;
    }

    public void setLocked(){
        isLocked = true;
    }

    public void setUnlocked(){
        isLocked = false;
    }

    public void printId(){
        System.out.println(DoorId);
    }


    //public void unlock(Key key){
        //if()
    //}
    
    public String getDoorDir1() {
    	return doorDir1;
    }

    public void setDoorDir1(String doorDir1) {
    	this.doorDir1 = doorDir1;
    }

    public String getDoorDir2() {
    	return doorDir2;
    }

    public void setDoorDir2(String doorDir2) {
    	this.doorDir2 = doorDir2;
    }

}

	
