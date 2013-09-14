package sinius.maze.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class SynchroniezedList{
	
	private String listLock = "qjeifaopjdfsal;h";
	private String removeLock = "34ifaiofdncvkafead236";
	private String addLock = "fefahrgjgrjrgairge4u904f9";
	
	private List<Object> list;
	
	private Collection<Object> remove;
	private Collection<Object> add;
	
	public SynchroniezedList(){
		listLock = String.valueOf(new Random().nextInt());
		removeLock = String.valueOf(new Random().nextInt());
		list = new ArrayList<Object>();
		remove = new ArrayList<Object>();
		add = new ArrayList<Object>();
	}
	
	public interface editAction{
		public void action(Object o);
	}
	
	public synchronized void doForAll(editAction e){
		synchronized (listLock) {
			for(Object o : list){
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
	
	public synchronized boolean contains(Object o){
		synchronized (listLock) {
			if(list.contains(o))
				return true;
		}
		return false;
	}
	
	public synchronized void add(Object o){
		synchronized (listLock) {
			list.add(o);
		}
	}
	
	public synchronized void remove(Object o){
		synchronized (listLock) {
			list.remove(o);
		}
	}
	
	/**
	 * if you want to remove a object inside the doForAll loop
	 */
	public synchronized void removeLater(Object o){
		synchronized (removeLock) {
			remove.add(o);
		}
	}
	
	public synchronized void addLater(Object o){
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
