
package peopleplatformcontrolsimulation;
import java.util.Random;
import java.util.concurrent.locks.*;
public class PlatformSpace
{
    private  Seat []seatsCapacity;
    
    
    private int counter;
    private Lock reentrantLock;
    
    private Condition PlatformNotFull;
    
    
    
    
    public PlatformSpace() {
        this.seatsCapacity = new Seat [100];
         
        this.counter = 0;
        this.reentrantLock = new ReentrantLock();
        
        this.PlatformNotFull = this.reentrantLock.newCondition();
        
        
        for (int i = 0; i < this.seatsCapacity.length; ++i) {
            this.seatsCapacity[i] = new Seat();
        }
    }
    
    

    
    public int findPlatformSeat(final int visitorNumber) {
        Random randomNumber;
        int seatNumber;
        for (randomNumber = new Random(), seatNumber = randomNumber.nextInt(this.seatsCapacity.length); !this.seatsCapacity[seatNumber].bookSeat(visitorNumber); seatNumber = randomNumber.nextInt(this.seatsCapacity.length)) {
            try {
                Thread.sleep(120L);
            }
            catch (InterruptedException ex) {}
        }
        return seatNumber;
    }
    
    
    
    
        public void enterToPlatform(final int personNumber) {
        this.reentrantLock.lock();
        try {
            while (this.counter == this.seatsCapacity.length) {
                try {
                    System.out.println("Person " + personNumber + " waits... the  platform is full...");
                   
                    this.PlatformNotFull.await();
                }
                
                catch (InterruptedException ex) {}
            }
            ++this.counter;
        }
        finally {
            this.reentrantLock.unlock();
        }
    }
    
    public void leavePlatformSeat(final int seatNumber) {
        this.seatsCapacity[seatNumber].leaveSeat();
    }
    
    
    
    public void leavePlatform() {
        this.reentrantLock.lock();
        try {
            
            --this.counter;
            if (this.counter < this.seatsCapacity.length) {
                this.PlatformNotFull.signal();
                
                
            }
        }
        finally {
            this.reentrantLock.unlock();
        }
    }
    
          public static void main( final String [] args){
    
     PlatformSpace platform= new  PlatformSpace();
     for (int i=0;i<170;i++){
        
    new Thread((Runnable)new  PeopleAccessingPlatform1(i,platform)).start();
    
    }
    
    
    }
}

 class Seat
{
    private int peopleNumber;
    private Lock lock;
    
    Seat() {
        this.peopleNumber = -1;
        this.lock = new ReentrantLock();
    }
    
     public boolean bookSeat(final int visittor) {
        boolean result = false;
        this.lock.lock();
        
        
        
        try {
            if (this.peopleNumber == -1) {
                this.peopleNumber = visittor;
                result = true;
            }
        }
        finally {
            this.lock.unlock();
        }
        return result;
    }
    
    
    
    public void leaveSeat() {
        this.lock.lock();
        try {
            this.peopleNumber = -1;
        }
        finally {
            this.lock.unlock();
        }
    }
}
class PeopleAccessingPlatform implements Runnable
{
    private int person_number;
    private PlatformSpace platformObj;
     private Lock reentrantLock;
    
    public PeopleAccessingPlatform(final int number, final PlatformSpace platformSpace) {
        this.person_number = number;
        this.platformObj = platformSpace;
    }

    PeopleAccessingPlatform() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

    @Override
    public void run() {
        
        
        
        this.platformObj.enterToPlatform(this.person_number);
        
      
        System.out.println("Person " + this.person_number + "  entered and is finding a seat ");
        
        
        final int seatNumber = this.platformObj.findPlatformSeat(this.person_number);
        
        System.out.println("Person " + this.person_number + " sat on seat number " + seatNumber + "!");
        try {
            
            Thread.sleep((int)(Math.random() * 11000.0));
        }
        catch (InterruptedException ex) {}
        
        
        
        this.platformObj.leavePlatformSeat(seatNumber);
        
        this.platformObj.leavePlatform();
        System.out.println("Person " + this.person_number + "  has left.");
    }
    
}


  

