//Alumnos:   
//           
package tp.p1;

public class Sunflower {
	private Position pos;
    private int life;
    public int cyclesToGen;
    private Game game;
    
    public static final int LIFE = 1;
    public static final int COST = 20;
    public static final int CYCLESSUN = 2;
    public static final int SUNSGEN = 10;
    public static final int HARM = 0;
    
    
    public Sunflower(Position pos, Game game) {
  		this.pos = pos;
  		this.life= LIFE;
  		this.cyclesToGen = CYCLESSUN;
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
	
	public int getCyclesToGen() {
		return cyclesToGen;
	}

	public void setCyclesToGen(int cyclesToGen) {
		this.cyclesToGen = cyclesToGen;
	}

	public String toString(){
		return "S ["+life+"]";
	}
	
	public void update(){
		if( life > 0)
		    if(cyclesToGen == 0){
			game.generateSun(SUNSGEN);
			cyclesToGen= CYCLESSUN;
		    }
		    --cyclesToGen;
	}
	
}
