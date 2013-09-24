package sinius.maze.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class SynchroniezedList<E>{
	
	private String listLock = "qjeifaopjdfsal;h";
	private String removeLock = "34ifaiofdncvkafead236";
	private String addLock = "fefahrgjgrjrgairge4u904f9";
	
	private List<E> list;
	
	private Collection<E> remove;
	private Collection<E> add;
	
	public SynchroniezedList(){
		listLock = String.valueOf(new Random().nextInt());
		removeLock = String.valueOf(new Random().nextInt());
		list = new ArrayList<E>();
		remove = new ArrayList<E>();
		add = new ArrayList<E>();
	}
	
	public interface editAction<E>{
		public void action(E o);
	}
	
	public synchronized void doForAll(editAction<E> e){
		synchronized (listLock) {
			for(E o : list){
				e.action(o);
			}
		}
		synchronized (removeLock) {
			list.removeAll(remove);
		}
		synchronized (addLock) {
			list.addAll(add);
		}
		
	}
	
	public synchronized void addRemoveUpdate(){
		synchronized (removeLock) {
			list.removeAll(remove);
		}
		synchronized (addLock) {
			list.addAll(add);
		}
	}
	
	public synchronized boolean contains(E o){
		synchronized (listLock) {
			if(list.contains(o))
				return true;
		}
		return false;
	}
	
	public synchronized void addAll(Collection<E> o){
		synchronized (listLock) {
			list.addAll(o);
		}
	}
	
	public synchronized void add(E o){
		synchronized (listLock) {
			list.add(o);
		}
	}
	
	public synchronized void remove(E o){
		synchronized (listLock) {
			list.remove(o);
		}
	}
	
	/**
	 * if you want to remove a E inside the doForAll loop
	 */
	public synchronized void removeLater(E o){
		synchronized (removeLock) {
			remove.add(o);
		}
	}
	
	public synchronized void addLater(E o){
		synchronized (removeLock) {
			add.add(o);
		}
	}
	
	public synchronized int size(){
		synchronized (addLock) {
			return list.size();
		}
	}
}
