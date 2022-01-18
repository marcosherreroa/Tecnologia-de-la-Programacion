//Flavius Ciapsa y Marcos Herrero

package tp.p3.logic.objects;

public class Position {
    private int x;
    private int y;
    
    public Position(){
    	this.x = 0;
    	this.y = 0;
    }
    
    public Position(int x, int y){
    	this.x = x;
    	this.y = y;
    }

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}
	
	public void addX(int x){
		this.x += x;
	}
    
	public void substractX(int x){
		this.x -= x;
	}
	
	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
    
	public void addY(int y){
		this.y += y;
	}
    
	public void substractY(int y){
		this.y -= y;
	}
	
     public boolean equals(Position pos){
	    	return x == pos.x && y == pos.y;
	    }
     
     public String toString(){
    	 return "("+ x + ","+ y +")";
     }
}
