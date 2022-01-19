//  y  

package tp.p1.logic.list;

import tp.p1.logic.Game;
import tp.p1.logic.objects.GameObject;
import tp.p1.logic.objects.Position;

public class GameObjectList {
    private GameObject[] list;
    private int count;
    
    public GameObjectList(){
    	this.list = new GameObject[Game.NROWS*Game.NCOLUMNS];
    	this.count = 0;
    }
    
    public int getCount(){
   	 return this.count;
    }
    
    public Search search(Position pos){
		Search search = new Search();
		boolean found = false;
		
		for(int i=0; !found && i< count; ++i ){
			if(list[i].getLife() > 0 && list[i].getPos().equals(pos)){
				search.setFound(true);
				search.setIndex(i);
				
				found = true;
			}
		}
		
		return search;
	}
   
   public SearchString toStringRelease(Position pos){
   	Search search = search(pos);
   	
   	SearchString searchString = new SearchString();
   	if(search.isFound()){
   		searchString.setFound(true);
   		searchString.setString(list[search.getIndex()].toStringRelease());
   	}
   	else searchString.setFound(false);
   	
   	return searchString;
   }
   
   public String toStringDebug(int index){
	   	return list[index].toStringDebug();
	   }
   
   public boolean isAlive(int index){
	   return (list[index].getLife()>0);
   }
   
   public void add(GameObject object){
	   	 list[count] = object;
	   	 ++count;
	    }
   
   public void removeDead(){
	   int ind = 0;
	    for(int i = 0; i< count; ++i){
	    	if(list[i].getLife() > 0){
	    		if(i!=ind)list[ind]=list[i];
	    		++ind;
	    	}
	    }
	    
	    for(int i= ind; i< count; ++i)list[i]= null;
	    
	    count = ind;
  }
  
   public void update(){
  	 for(int i = 0; i < count; ++i) {
  		 list[i].update();
  	 }
   }
    
    public boolean attack(int damage, Position pos){
		   Search search = search(pos);
		   boolean found = search.isFound();
		   
		   if(found) list[search.getIndex()].substractLife(damage);
		   
		   return found;
		}
}
