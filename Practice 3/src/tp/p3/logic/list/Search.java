//Flavius Ciapsa y Marcos Herrero

package tp.p3.logic.list;

public class Search {
   private final boolean found;
   private final int index;
   
   public Search(boolean found, int index){
	   this.found = found;
	   this.index = index;
   }

   public boolean isFound() {
	return found;
}

public int getIndex() {
	return index;
}

  
}
