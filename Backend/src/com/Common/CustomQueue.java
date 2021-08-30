package com.Common;

import java.lang.reflect.Array;

public class CustomQueue<E> {
		
	private E[] QueueArray;
	private int QueueSize;
	private int QueueLength;
	
	private int Front;
	private int Rear;
	
	@SuppressWarnings("unused")
	private int Count;
	
	@SuppressWarnings("unchecked")
	public CustomQueue(Class<E> c,int _size) 
	{
		QueueSize = _size;
		QueueLength = 0;
		Count = 100;
		
		Front = 0;
		Rear = 0;
		
		//
		
		if(QueueSize > 0) {
			
			QueueArray = (E[])Array.newInstance(c,QueueSize);
			}
			else {
				System.out.println("# CustomQueue 크기가 1 이상이어야 합니다.");
			}
		
	}
	
	public boolean Enqueue(E l) {
		
		// 큐占쏙옙 占쏙옙占� 찼占쏙옙 占쏙옙,
		Rear = (++Rear) % QueueSize;
		QueueArray[Rear] = l;
		
		
		
		//System.out.println("#" + l);
		
		
		
		//System.out.println("Rear Index : " + Rear + " | " + l);
		
//		if(Front == (Rear+1) % QueueSize) 
//		{
//			
//		}
//		
//		else 
//		{
//			// 큐 占쏙옙占싱곤옙 1 占싱삼옙占쏙옙 占쏙옙,
////			if(QueueLength > 0) {
////				QueueLength = (QueueLength++ % QueueSize);
////				QueueArray[QueueLength = (QueueLength++ % QueueSize)] = i;
////			}
////			else {
////				QueueArray[1] = i;
////				QueueLength = 2;
////			}
//		}
		//ShowQueueList();
		
		return true;
	}
	
	public E Dequeue() {
		Front = (++Front) % QueueSize;
		int PreFront = Front;
		
		return QueueArray[PreFront];
	}
	
	public int AmountOf() {
		QueueLength = (Front <= Rear) ? Rear - Front : (QueueSize + Rear) - Front;
		
		return QueueLength;
	}
	
	public int SizeOf() {
		return QueueArray.length;
	}
	
	public synchronized boolean isEmpty() {
		
		if(Front == Rear) 	return true;
		else 				return false;
	}
	
	public boolean isFull() {
		if(Front == (Rear+1) % QueueSize) 	return true;
		else 								return false;
	}
	
	public int IndexOfFront() {
		return Front;
	}
	
	public int IndexOfRear() {
		return Rear;
	}
	
	public void ShowQueueList() {
		
		System.out.print("[ ");
		for (E e : QueueArray) {
			System.out.print(e + " ,");
		}
		
		System.out.println(" ]");
		
	}
	
	public Integer[] Copy(int start, int end) {
		Integer[] result = new Integer[start < end ? (end - start):((QueueSize + end) - start)];
		
		int idx = 0;
		
		try {
		while(idx < (start < end ? (end-start):(QueueSize + end) - start)) {
			if(QueueArray[(start + idx) % QueueSize] != null) 
			{
				result[idx] = Integer.valueOf((int)QueueArray[(start + idx) % QueueSize]);
			}
			idx++;
		}
		} catch(Exception e) {
		}
		
		return result;
	}
	
	public E IndexOf(int idx) {
		
		return QueueArray[idx];
	}
	
	public synchronized void QueueFlush(boolean bIsHigh) {
		
		if(bIsHigh) {
			for(int i = (QueueSize / 2); i < QueueSize ;i++) {
				QueueArray[i] = null;
			}
		}
		else {
			for(int i = 0 ; i < (QueueSize / 2) - 1 ; i++) {
				QueueArray[i] = null;
			}
		}
	}
	/*--------------------------------------------------------------------------------------------------------------------------------------*/
}
