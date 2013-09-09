package sinius.maze;

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
	
	public void doForAll(editAction e){
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
	
	public void add(Object o){
		synchronized (listLock) {
			list.add(o);
		}
	}
	
	public void remove(Object o){
		synchronized (listLock) {
			list.remove(o);
		}
	}
	
	/**
	 * if you want to remove a object inside the doForAll loop
	 */
	public void removeLater(Object o){
		synchronized (removeLock) {
			remove.add(o);
		}
	}
	
	public void addLater(Object o){
		synchronized (removeLock) {
			add.add(o);
		}
	}
	
	public int size(){
		synchronized (addLock) {
			return list.size();
		}
	}
}
