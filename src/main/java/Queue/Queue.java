/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Queue;

import java.util.LinkedList;

/**
 *
 * @author David
 */
public class Queue<T> {
    private final LinkedList<T> data = new LinkedList<>();
    private final int maxSize;

    public Queue(int maxSize) {
        this.maxSize = maxSize;
    }
    
    public void put(T value)throws FullException{
        if(data.size() == maxSize){
            throw new FullException();
        }
        data.add(value);
    }
    
    public T get()throws EmptyException{
        if(data.isEmpty()){
            throw new EmptyException();
        }
        return data.poll();
    }
}
