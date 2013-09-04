package sinius.maze;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import sinius.maze.drawing.Drawer;
import sinius.maze.drawing.Finish;
import sinius.maze.drawing.StatisticsOverlay;
import sinius.maze.entitys.Exit;
import sinius.maze.entitys.Player;
import sinius.maze.entitys.Spawn;
import sinius.maze.gui.EditorOptionScreen;
import sinius.maze.gui.GameScreen;
import sinius.maze.io.KeyHandler;
import sinius.maze.io.LevelLoader;
import sinius.maze.timing.FPSTimer;
import sinius.maze.timing.TimeTimer;

public class Game {
	
	public static KeyHandler keys = new KeyHandler();
	
	public GameScreen gameScreen;
	public static Level level;
	public String stage = "Menu";
	public EditorOptionScreen options;
	public static TimeTimer timer = new TimeTimer();
	public static FPSTimer fps = new FPSTimer();
	
	private Thread gameLoop;
	private boolean isRunning = false;
	private boolean editMode;
	private boolean showInfoOverlay = false;
	public static boolean isFinihed = false;
	private Player player;
	
	public static int ppb_x, ppb_y;
	
	public Game(Level l, boolean editMode){
		this.editMode = editMode;
		level = l;
		gameScreen = new GameScreen();
		gameScreen.setVisible(true);
		
		ppb_x = 800 / l.getWidth();
		ppb_y = 800 / l.getHeight();
		
		startGame();
	}
	
	public void startGame(){
		isRunning = true;
		int x1 = (level.getSpawn().getX() * ppb_x) + ppb_x/2;
		int y1 = (level.getSpawn().getY() * ppb_y) + ppb_y/2;
		System.out.println(x1 + "  " + y1);
		player = new Player();
		player.Create(x1, y1);

		

		gameLoop = new Thread(gameLoop(), "gameLoop");
		gameLoop.start();
		timer.Start();
		fps.Start();
	}
	
	public void doTick(){
		if(Game.keys.isKeyPressed(KeyEvent.VK_ESCAPE)){
			isRunning = false;
			
		}
		if(editMode){
			if(Game.keys.mousePosX != -1){
				if(options.getBrush().equals("wall")){
					Block b = level.getBlock(Game.keys.mousePosX, Game.keys.mousePosY);
					if(Game.keys.isMousePressed(MouseEvent.BUTTON1)){
						b.setType(Block.WALL);
						b.setColor(options.getColor());
						if(Game.keys.lastMousePosX != -1 && !Game.keys.mouseMovedAfterDrag){
							for(Block block : Util.getCrossedBlocks(Game.keys.mousePosX, Game.keys.mousePosY, Game.keys.lastMousePosX, Game.keys.lastMousePosY, level)){
								block.setType(1);
								block.setColor(options.getColor());
							}
						}
						
					}
					if(Game.keys.isMousePressed(MouseEvent.BUTTON3)){
						b.setType(Block.AIR);
						if(Game.keys.lastMousePosX != -1 && !Game.keys.mouseMovedAfterDrag){
							for(Block block : Util.getCrossedBlocks(Game.keys.mousePosX, Game.keys.mousePosY, Game.keys.lastMousePosX, Game.keys.lastMousePosY, level)){
									block.setType(0);
							}
						}
						
					}
					
				}
				else if(options.getBrush().equals("spawn")){
					if(Game.keys.isMousePressed(MouseEvent.BUTTON1)){
						Spawn s = new Spawn();
						s.Create(Game.keys.mousePosX, Game.keys.mousePosY);
						level.setSpawn(s);
					}
						
				}
				else if(options.getBrush().equals("pencil")){
					Block b = level.getBlock(Game.keys.mousePosX, Game.keys.mousePosY);
					if(Game.keys.isMousePressed(MouseEvent.BUTTON1)){
						b.setType(Block.WALL);
						b.setColor(options.getColor());
					}
					if(Game.keys.isMousePressed(MouseEvent.BUTTON3)){
						b.setType(Block.AIR);
					}
				}
				else if(options.getBrush().equals("exit")){
					if(Game.keys.isMousePressed(MouseEvent.BUTTON1)){
						
						level.editEntitys("isEntityOnCoord", null, null, null, Game.keys.mousePosX, Game.keys.mousePosY, null);
						if(!level.editEntityReturner){
							Exit x = new Exit();
							x.Create(Game.keys.mousePosX, Game.keys.mousePosY);
							level.editEntitys("add", null, x, null, 0, 0, null);
						}
						level.editEntityReturner = false;
						
					}
						
					else if(Game.keys.isMousePressed(MouseEvent.BUTTON3)){
						level.editEntitys("remove", null, null, null, Game.keys.mousePosX, Game.keys.mousePosY, Exit.class);
					}
					
				}
			}
		}
		if(!editMode){
			level.editEntitys("tick", null, null, null, 0, 0, null);
			level.editEntitys("playerTouch", null, player, null, 0, 0, null);
			player.onTick(level);
			
			if(Game.keys.isKeyPressed(KeyEvent.VK_UP))
				player.up(level);
			if(Game.keys.isKeyPressed(KeyEvent.VK_DOWN))
				player.down(level);
			if(Game.keys.isKeyPressed(KeyEvent.VK_LEFT))
				player.left(level);
			if(Game.keys.isKeyPressed(KeyEvent.VK_RIGHT))
				player.right(level);
			if(Game.keys.isKeyPressed(KeyEvent.VK_F1))
				showInfoOverlay = !showInfoOverlay;
		}
		
		

		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		MainProgram.game.gameScreen.repaint();
	}
	
	public void draw(Graphics g){
		Drawer.graphics = (Graphics2D)g;
			
		Drawer.drawMaze(level);
		Drawer.draw(level);
		if(!editMode){
			Drawer.drawTimer();
			Drawer.drawPlayer(level);
			if(showInfoOverlay)
				StatisticsOverlay.Draw((Graphics2D)g);
			
			if(isFinihed)
				Finish.Draw((Graphics2D) g);
			
		}
				
		g = Drawer.graphics;
	}
	
	private Runnable gameLoop(){
		return new Runnable(){ @Override public void run() {
			while(isRunning){
				fps.registerTick();
				doTick();
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
			quitGame();
				
		}};
	}

	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}
		
	public void quitGame(){
		try {
			LevelLoader.SaveLevel(level, MainProgram.SAVEMAP + "\\saves\\" + level.getName() + ".maze" );
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.exit(0);
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
}
