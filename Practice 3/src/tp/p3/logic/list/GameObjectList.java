//Flavius Ciapsa y Marcos Herrero

package tp.p3.logic.list;

import tp.p3.logic.Game;
import tp.p3.logic.objects.GameObject;
import tp.p3.logic.objects.Position;

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
		int index = 0;
		boolean found = false;
		
		for(int i=0; !found && i< count; ++i ){
			if(list[i].getLife() > 0 && list[i].getPos().equals(pos)){
				index = i;
				found = true;
			}
		}
		
		Search search = new Search(found, index);
		return search;
	}
   
   public SearchString toStringRelease(Position pos){
   	Search search = search(pos);
   	SearchString searchString;
   	
   	if(search.isFound()){
   		searchString = new SearchString(true, list[search.getIndex()].toStringRelease());
   	}
   	
   	else searchString = new SearchString (false, "");
   	return searchString;
   }
   
   public String toStringDebug(int index){
	   	return list[index].toStringDebug();
	   }
   
   public String externalise(){
	   StringBuilder str = new StringBuilder();
	   for(int i = 0; i< count; ++i){
		   str.append(list[i].externalise()).append(",");
	   }
	   
	   return str.toString();
   }
   
   public boolean isAlive(int index){
	   return (list[index].getLife()>0);
   }
   
   public void add(GameObject object){
	   	 list[count] = object;
	   	 ++count;
	    }
   
   public void removeDead(){
	   GameObject[] aux = new GameObject[Game.NROWS*Game.NCOLUMNS];
	   int ind = 0;
	    for(int i = 0; i< count; ++i){
	    	if(list[i].getLife() > 0){
	            aux[ind] =list[i];
	    		++ind;
	    	}
	    }
	    
	    list = aux;
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
