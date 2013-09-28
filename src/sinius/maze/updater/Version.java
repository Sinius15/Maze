package sinius.maze.updater;

public class Version {

	public String state;   //alpha, beta, charlie
	public int release;	//1,2,3,4,5,6
	public String subRelease;//a,b,c,d,e,f
	
	public Version(String in){
		if(in.startsWith("alpha")){
			state = "alpha";
			release = Integer.parseInt(in.substring(5, in.length()-1));
			subRelease = in.substring(in.length()-1);
		}
	}
	
	public Version(String state, int release, String subRrelease){
		this.state = state;
		this.release = release;
		this.subRelease = subRrelease;
	}
	
	public boolean equals(Version v){
		if(v.state.equals(this.state) && v.release == this.release && v.subRelease.equals(this.subRelease))
			return true;
		return false;
	}
	
	public String toString(){
		return state + release + subRelease ;
	}
	
}
