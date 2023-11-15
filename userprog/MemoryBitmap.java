import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class MemoryBitMap{
    private int[] bitmap;
    private Semaphore semaphore;

    public MemoryBitMap(int numPages){
        bitmap = new int[numPages];
        semaphore = new Semaphore(1);

        for(int i=0;i<bitmap.length;i++){
            bitmap[i] = 1;
        }
    }
    public void allocatePage(int pageIndex){
        semaphore.acquire();
        try{
            if(pageIndex<0 || pageIndex>=bitmap.length){
                throw new IllegalArgumentException("Invalid page index");
            }
            bitmap[pageIndex] = 0;
        }finally{
            semaphore.release();
        }
    }

    public void deAllocatePage(int pageIndex){
        semaphore.acquire();
        try{
            if(pageIndex<0 || pageIndex>=bitmap.length){
                throw new IllegalArgumentException("Invalid page index");
            }
            bitmap[pageIndex] = 1;
        } finally{
            semaphore.release();
        }
    }
}