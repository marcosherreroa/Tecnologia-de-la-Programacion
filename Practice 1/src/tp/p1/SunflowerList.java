//Alumnos: Marcos Herrero Agustin
//         Flavius Abel Ciapsa
package tp.p1;

public class SunflowerList {
     private Sunflower[] v;
     private int count;
     
     public static final int TAM_MAX = 100;
     
     public SunflowerList(){
    	 this.v= new Sunflower[100];
    	 this.count= 0;
     }
     
	public int getCount(){
    	 return this.count;
     }
     
     public void setCount(int count){
    	 this.count= count;
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
     
     public void showInfo(){
    	 System.out.println("[S]unflower: Cost: "+Sunflower.COST+" suncoins  Harm: "+Sunflower.HARM);
     }
     
     public void add(Position pos, Game game){
    	 v[count]= new Sunflower(pos, game);
    	 ++count;
     }
     
 	public boolean attack(int damage, Position pos){
		   Search search = search(pos);
		   
		   if(search.isFound()) v[search.getIndex()].substractLife(damage);
		   
		   return search.isFound();
		}
   
}
