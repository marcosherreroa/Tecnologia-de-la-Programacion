//Alumnos: Marcos Herrero Agustin
//         Flavius Abel Ciapsa
package tp.p1;

public class Peashooter {
	private Position pos;
    private int life;
    private int cyclesToShoot;
    private Game game;
    
    public static final int LIFE = 3;
    public static final int COST = 50;
    public static final int CYCLESSHOOT = 1;
    public static final int SHOTSPERCYCLE = 1;
    public static final int DAMAGEPERSHOT = 1;
    
    public Peashooter(Position pos, Game game){
    	this.pos= pos;
    	this.life= LIFE;
    	this.cyclesToShoot = CYCLESSHOOT-1;
    	this.game= game;
    }
  
    
    public Position getPos() {
		return pos;
	}

	public void setPos(Position pos) {
		this.pos = pos;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}
	
	public void substractLife(int life) {
		if(this.life > life) this.life -= life;
		else this.life = 0;
	}

	public int getCyclesToShoot() {
		return cyclesToShoot;
	}

	public void setCyclesToShoot(int cyclesToShoot) {
		this.cyclesToShoot = cyclesToShoot;
	}

	public String toString(){
		return "P ["+this.life+"]";
	}
	
	public void update(){
		if (life > 0){
		   if(cyclesToShoot == 0) {
			   for(int i = 0; i < SHOTSPERCYCLE; ++i) game.shoot(pos, DAMAGEPERSHOT);	
    	       cyclesToShoot = CYCLESSHOOT;
		   }
	
	       --cyclesToShoot;
		}
	}
}
