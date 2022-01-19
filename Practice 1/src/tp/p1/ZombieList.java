//Alumnos:   
//           
package tp.p1;

public class ZombieList {
	private Zombie[] v;
    private int count;
    
    public static final int TAM_MAX = 100;
    
    public ZombieList(){
    	this.v = new Zombie[TAM_MAX];
    	this.count = 0;
    }
	
	public int getCount() {
		return count;
	}
	
	public void setCount(int count) {
		this.count = count;
	} 
	
	public Search search(Position pos){
		Search search = new Search();
		
		for(int i=0; !search.isFound() && i< count; ++i ){
			if(v[i].getLife()>0 && v[i].getPos().equals(pos)){
				search.setFound(true);
				search.setIndex(i);
			}
		}
		
		return search;
	}
	
	 public SearchString toString(Position pos){
	    	Search search = search(pos);
	    	
	    	SearchString searchString = new SearchString();
	    	if(search.isFound()){
	    		searchString.setFound(true);
	    		searchString.setString(v[search.getIndex()].toString());
	    	}
	    	else searchString.setFound(false);
	    	
	    	return searchString;
	    }
	 
	public void update(){
   	 for(int i = 0; i < count; ++i) v[i].update();
    }
	
	public void add(Position pos, Game game){
   	 v[count]= new Zombie(pos, game);
   	 ++count;
    }
	
	public boolean attack(int damage, Position pos){
	   Search search = search(pos);
	   boolean found = search.isFound();
	   
	   if(found) v[search.getIndex()].substractLife(damage);
	   
	   return found;
	}
	
	public boolean someoneAlive(){
		boolean found = false;
		for(int i = 0; !found && i < count; ++i){
			if (v[i].getLife() > 0) found = true;
		}
		return found;
	}
}