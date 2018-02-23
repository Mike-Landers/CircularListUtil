import java.util.ArrayList;
import java.util.List;

/**
 * ListUtil
 * @author Mike Landers
 */
public class CircularListUtil<T extends Comparable<T>> {

   /**
    * getSmallestValue performs a modified binary search on a circular list of
    * offset/sorted order comparable elements
    * 
    * ex. input:  [2, 3, 4, 1]
    * ex. output: 1
    * 
    * general case end condition = find a value < previous value
    * 
    * edge cases:
    * list has 0 elements, return null
    * list has 1 element, return the 1 element
    * element at index 0 is lowest
    * element at max index is lowest
    * 
    * we can use the fact that the list is mostly sorted to find the end
    * condition within log n iterations by performing a binary search
    * 
    * @return smallest value
    */
   public T getSmallestValue(List<T> circularList) {
      int size = circularList.size();
      if(size == 0) {
         return null;
      }
      else if(size == 1) {
         return circularList.get(0);
      }
      // else list has at least 2 elements
      
      int highIndex = size-1;
      int midIndex = size/2;
      int lowIndex = 0;
      
      // while general case end condition is not met (value < previous value)
      while(!(circularList.get(midIndex).compareTo((T) circularList.get(midIndex-1)) < 0)) {
         // if element at midIndex is less than element at highIndex, check lower half
         if((circularList.get(midIndex).compareTo((T) circularList.get(highIndex)) < 0)) {
            highIndex = midIndex;
         }
         // else check upper half
         else {
            lowIndex = midIndex;
         }
         
         // set midIndex between new high and low indices
         midIndex = highIndex - (highIndex-lowIndex)/2;
          
         if(lowIndex == highIndex) {
            // if first element is lowest
            if(midIndex == 1) {
               return circularList.get(0);
            }
            // else if last element is lowest
            else if(midIndex == size) {
               return circularList.get(size-1);
            }
         }
      }
      
      return circularList.get(midIndex);
   }
      
   public static void main(String[] args) {
      CircularListUtil<Integer> lu = new CircularListUtil<>();
      
      List<Integer> list = new ArrayList<>();
      //list.add(500);
      list.add(501);
      list.add(7);
      list.add(21);
      list.add(22);
      list.add(23);
      list.add(24);
      list.add(25);
      list.add(26);
      list.add(27);
      list.add(28);
      list.add(29);
      list.add(30);
      list.add(31);
      list.add(32);
      list.add(33);
      //list.add(0);
      //list.add(1);
      
      System.out.println("list: " + list + ", size: " + list.size());
      
      Integer smallest = lu.getSmallestValue(list);
      
      System.out.println("smallest value returned: " + smallest);
   }
}
