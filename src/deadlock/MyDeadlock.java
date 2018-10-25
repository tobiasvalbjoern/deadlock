package deadlock;

//https://www.tutorialspoint.com/java/java_thread_deadlock.htm
//Simple example showing a deadlock situation with a solution. 
//Use flag deadlock to enable or disable deadlock


public class MyDeadlock {
	   public static Object Lock1 = new Object();
	   public static Object Lock2 = new Object();
	   
	   public static void main(String args[]) {
	      
		  
		  ThreadDemo1 T1 = new ThreadDemo1();
	      ThreadDemo2 T2 = new ThreadDemo2();
	      T1.start();
	      T2.start();
	   }
	   
	   private static class ThreadDemo1 extends Thread {
	      public void run() {
	         synchronized (Lock1) {
	            System.out.println("Thread 1: Holding lock 1...");
	            
	            try { Thread.sleep(10); }
	            catch (InterruptedException e) {}
	            System.out.println("Thread 1: Waiting for lock 2...");
	            
	            synchronized (Lock2) {
	               System.out.println("Thread 1: Holding lock 1 & 2...");
	            }
	         }
	      }
	   }
	   private static class ThreadDemo2 extends Thread {
	      public void run() {
	         
	    	  Boolean deadlock=false;
	    	  
	    	  if(deadlock) {
	    		  synchronized (Lock2) {
	  	            System.out.println("Thread 2: Holding lock 2...");
	  	            
	  	            try { Thread.sleep(10); }
	  	            catch (InterruptedException e) {}
	  	            System.out.println("Thread 2: Waiting for lock 1...");
	  	            
	  	            synchronized (Lock1) {
	  	               System.out.println("Thread 2: Holding lock 1 & 2...");
	  	            }
	  	         }	 
			  }
	    	  //Avoid deadlock by requesting and releasing locks in the same order
	    	  //java.util.concurrent.locks.ReentrantLock can be used to
	    	  // attempt to acquire the locks of both accounts (trylock() ), 
	    	  //to release the locks if it fails,
	    	  //and to try again later if necessary.
	    	  
	    	  //You can also avoid waiting indefinitely in case of deadlock:
	    	  //The Javadoc states that the join(time) function will wait 
	    	  //at most that many milliseconds for the thread to die. 
	    	  //In effect if the timeout passes your code will stop blocking and continue on. 
	    	  else {
				  synchronized (Lock1) {
			            System.out.println("Thread 2: Holding lock 1...");
			           
			            try {
			               Thread.sleep(10);
			            } catch (InterruptedException e) {}
			            System.out.println("Thread 2: Waiting for lock 2...");
			            
			            synchronized (Lock2) {
			               System.out.println("Thread 2: Holding lock 1 & 2...");
			            }
			         }
			  }
	    	  
	    	  
	      }
	   } 
	}