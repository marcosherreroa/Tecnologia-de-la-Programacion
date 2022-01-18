//Alumnos: Marcos Herrero Agustin
//         Flavius Abel Ciapsa
package tp.p1;

public class Zombie {
	private Position pos;
    private int life;
    private int cyclesToAdvance;
    private int cyclesToAttack;
    private Game game;
    
    public static final int LIFE = 5;
    public static final int CYCLESADVANCE = 2;
    public static final int SQUARESPERSTEP = 1;
    public static final int CYCLESATTACK = 1;
    public static final int DAMAGEPERHIT = 1;
    
    public Zombie(Position pos, Game game) {
		this.pos = pos;
		this.life= LIFE;
		this.cyclesToAdvance = CYCLESADVANCE-2;
		this.cyclesToAttack = CYCLESATTACK;
		this.game = game;
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
    
	public int getCyclesToAdvance() {
		return cyclesToAdvance;
	}

	public void setCyclesToAdvance(int cyclesToAdvance) {
		this.cyclesToAdvance = cyclesToAdvance;
	}

	public int getCyclesToAttack() {
		return cyclesToAttack;
	}

	public void setCyclesToAttack(int cyclesToAttack) {
		this.cyclesToAttack = cyclesToAttack;
	}

	public String toString(){
		return "Z ["+life+"]";
	}
		
    public void update(){
    	if(life > 0){
    		boolean advance = false;
    		if(cyclesToAdvance == 0){
    			advance = game.checkAdvance(pos);
    			if(advance) {
    			pos.substractY(1);
    			cyclesToAdvance = CYCLESADVANCE;
    			}
    		}
    		
    		if(!advance && cyclesToAttack == 0){
    			game.attackPlants(pos, DAMAGEPERHIT);
    			cyclesToAttack = CYCLESATTACK;
    		}
    		
    		if(cyclesToAdvance > 0) --cyclesToAdvance;
    		if(cyclesToAttack > 0) --cyclesToAttack;
        }
    }
}
