package sinius.maze;

import java.util.ArrayList;

@SuppressWarnings("rawtypes")
public class EntityManager {

	private static ArrayList<Class> avalableEntitys;
	
	
	
	public static synchronized void getEntityByClass(String className){
		for(Class c : avalableEntitys){
			if(c.getName().equals(className)){
				
			}
		}
	}
}
