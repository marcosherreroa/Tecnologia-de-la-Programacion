//  y  

package tp.p1.logic.objects;

import tp.p1.logic.Game;

public abstract class GameObject {
	protected Position pos;
    protected int life;
    protected final int damage;
    protected int cyclesLeftToAction1;
    protected final int cyclesAct1;
    protected int cyclesLeftToAction2;
    protected final int cyclesAct2;
    protected Game game;
    
    protected final String objectText;
    private final String objectShortText;
    
    public GameObject(Position pos, int life, int damage, int cyclesAct1, int cyclesAct2 , 
    		Game game, String objectText, String objectShortText){
    	this.pos = pos;
    	this.life = life;
    	this.damage = damage;
    	this.cyclesLeftToAction1 = cyclesAct1;
    	this.cyclesAct1 = cyclesAct1;
    	this.cyclesLeftToAction2 = cyclesAct2;
    	this.cyclesAct2 = cyclesAct2;
    	this.game = game;
    	
    	this.objectText = objectText;
    	this.objectShortText = objectShortText;
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
	
	public int getDamage(){
		return damage;
	}
		
		public void setGame(Game game){
	    	this.game = game;
	    }
		
	public String toStringRelease(){
		return  objectShortText+"["+life+"]";
		
	}
	
	public String toStringDebug(){
		return objectShortText+"[l:"+life+",x:"+pos.getX()+",y:"+pos.getY()+
				",t:"+cyclesLeftToAction1+"]";
	}
	
	public void update(){
		if(life > 0){
		 boolean act1 = false;
		if(cyclesLeftToAction1 == 0){
			act1 = this.action1();
			cyclesLeftToAction1 = cyclesAct1;
		}
		
		if(!act1 && cyclesLeftToAction2 == 0){
			this.action2();
			cyclesLeftToAction2 = cyclesAct2;
		}
		
		if(cyclesLeftToAction1 > 0)--cyclesLeftToAction1;
		if(cyclesLeftToAction2 > 0)--cyclesLeftToAction2;
		}
	};
	
	public abstract boolean action1();
	public abstract boolean action2();
	public abstract String helpText();
}
