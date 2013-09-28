package sinius.maze.updater;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Updater {

	private UpdateScreen updateScreen;
	private URL versionURL;
	
	private String newVersion = null;
	
	public Updater(){
		try {
			versionURL = new URL("http://sinius15.com/maze/version/index.html");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isLatestVersion(Version cur) throws Exception{
		
        URLConnection yc = versionURL.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) 
            break;
        Version latest = (new Version(inputLine));
        
        in.close();
		
		if(cur.release == latest.release && cur.subRelease.equals(latest.subRelease))
			return true;
		if(cur.release < latest.release){
			newVersion = latest.toString();
			return false;
		}
		if(cur.release == latest.release && cur.subRelease.compareTo(latest.subRelease)<0){
			newVersion = latest.toString();
			return false;
		}
		return true;
	}

	public void showUpdateScreen() {
		if(newVersion == null)
			return;
		updateScreen = new UpdateScreen(newVersion);
		updateScreen.setVisible(true);
	}
	
}
