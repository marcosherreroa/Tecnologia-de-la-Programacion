//Flavius Ciapsa y Marcos Herrero

package tp.p1.logic.list;

public class Search {
   private boolean found;
   private int index;
   
   public Search(){
	   this.found = false;
	   this.index = 0;
   }

   public boolean isFound() {
	return found;
}

public void setFound(boolean found) {
	this.found = found;
}

public int getIndex() {
	return index;
}

public void setIndex(int index) {
	this.index = index;
}
   
}
