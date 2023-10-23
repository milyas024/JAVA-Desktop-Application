

import java.util.Iterator;
import java.util.NoSuchElementException;

class CircularQueue<T> implements Iterable<T>{ 
    private T queue[]; 
    
    private int head, tail, size;  
    
    public CircularQueue(){ 
        queue = (T[])new Object[20]; 
       head = 0;tail = 0; size = 0;  }
    
    
     public  CircularQueue(int n){ //assume n >=0 
         
         
     queue = (T[])new Object[n];     size = 0; head = 0; tail = 0; 
     
     } 

      public synchronized boolean join(T x){ 
          
          if(size < queue.length){
              queue[tail] = x;   tail = (tail+1)%queue.length;   size++;  
              return true;     
          } 
      else
              return false; 
      
      }
      
      
       public  synchronized T top(){     
           if(size > 0)   
               return queue[head];   
           else   return null;  
       
       } 
       
       
       
       public synchronized boolean leave(){    
           if(size == 0) return false;     
           else{  
               head = (head+1)%queue.length;   size--;   return true;     
           }  
       
       }
       
      
         public  synchronized boolean full()
         {
             
             return (size == queue.length);
         
         } 
         
         public synchronized  boolean empty(){
             
             return (size == 0);
             
         
         } 
      public  synchronized Iterator<T> iterator()
              
      {      return new QIterator<T>(queue, head, size);   
      
      }

   private static class QIterator<T> implements Iterator<T>{
       
       private T[] d; private int index;         
       private int size; private int returned = 0; 
       
       
       QIterator(T[] dd, int head, int s){  
           d = dd; index = head; size = s;      
       }   
       
       
       
       public synchronized boolean hasNext(){
           
           
           return returned < size;
       
       
       }  
       public T next(){ if(returned == size) throw new NoSuchElementException();
       
       T item = (T)d[index];      index = (index+1) % d.length;      returned++;  
       return item;       
       
       }       
       public void remove(){
       } 
   }
} 