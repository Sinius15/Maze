package sinius.maze;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import sinius.maze.core.Engine;
import sinius.maze.core.SynchroniezedList.editAction;
import sinius.maze.entitys.Player;
import sinius.maze.entitys.Spawn;
import sinius.maze.gameEngine.Display;
import sinius.maze.gameEngine.Display.DrawAction;
import sinius.maze.gui.EditorOptionScreen;
import sinius.maze.io.KeyHandler;
import sinius.maze.io.LevelLoader;
import sinius.maze.timing.FPSTimer;
import sinius.maze.timing.TimeTimer;

public class Game {
	
	public static KeyHandler keys = new KeyHandler();
	
	public static Level level;
	public static EditorOptionScreen options;
	public static TimeTimer timer = new TimeTimer();
	public static FPSTimer fps = new FPSTimer();
	public static Display display;
	
	private static Thread gameLoop;
	public static boolean editMode;
	public static Player player;
	
	public static int ppb_x, ppb_y;
	
	public Game(Level l, boolean editMode){
		Game.editMode = editMode;
		level = l;
		display = new Display(800, 800, "Sinius's Maze");
		display.getPanel().addComponentListener(Game.keys);
		display.getFrame().addKeyListener(Game.keys);
		display.getPanel().addMouseListener(Game.keys);
		display.getPanel().addMouseMotionListener(Game.keys);
		
		display.setDrawAction(new DrawAction() {@Override public void Draw(Graphics2D g) {
				//TODO: draw!!
		}});
		
		ppb_x = 800 / l.getWidth();
		ppb_y = 800 / l.getHeight();
		
		startGame();
		
	}
	
	public static void startGame(){
		
		MainProgram.engine = new Engine();
		
		gameLoop.start();
		timer.Start();
		fps.Start();
	}
	
	public static void doTick(){
		if(Game.keys.isKeyPressed(KeyEvent.VK_ESCAPE)){
			MainProgram.engine.stopGame();
			
		}
		if(Game.keys.latestMouseEvent != null){
			Game.keys.latestMouseEvent = null;
		}
			
		if(editMode){
			if(Game.keys.mousePosX != -1){
				if(options.getBrush().equals("Wall Brush")){
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
					
				}else if(options.getBrush().equals("Spawn")){
					if(Game.keys.isMousePressed(MouseEvent.BUTTON1)){
						Spawn s = new Spawn();
						s.Create(Game.keys.mousePosX, Game.keys.mousePosY, "");
						level.setSpawn(s);
					}
						
				}else if(options.getBrush().equals("Wall pencil")){
					Block b = level.getBlock(Game.keys.mousePosX, Game.keys.mousePosY);
					if(Game.keys.isMousePressed(MouseEvent.BUTTON1)){
						b.setType(Block.WALL);
						b.setColor(options.getColor());
					}
					if(Game.keys.isMousePressed(MouseEvent.BUTTON3)){
						b.setType(Block.AIR);
					}
				}else{
					final Entity x = MainProgram.entityManager.getEntityByName(options.getBrush());
					if(x != null){
						x.Create(Game.keys.mousePosX, Game.keys.mousePosY, "");
						if(Game.keys.isMousePressed(MouseEvent.BUTTON1)){
							
							level.getEntitys().doForAll(new editAction() {@Override public void action(Object o) {
									Entity e = (Entity) o;
									if(e.getX() == x.getX() && e.getY() == x.getY())
										level.getEntitys().addLater(o);
							}});
							
						}else if(Game.keys.isMousePressed(MouseEvent.BUTTON3)){
							level.getEntitys().doForAll(new editAction() {@Override public void action(Object o) {
								Entity e = (Entity) o;
								if(e.getX() == Game.keys.mousePosX && e.getY() == Game.keys.mousePosY && e.getClass().equals(x.getClass()))
									level.getEntitys().removeLater(o);
							}});
						}
					}
					
					
				}
			}
		}
		if(!editMode){
			
			level.getEntitys().doForAll(new editAction() { @Override public void action(Object o) {
					Entity e = (Entity) o;
					e.onTick(Game.level);
					if(e.getCollisionBox().intersects(player.getCollisionBox()))
						e.onPlayerTouch(player);
			}});
			
			player.onTick(level);
			
			if(Game.keys.isKeyPressed(KeyEvent.VK_UP))
				player.up(level);
			if(Game.keys.isKeyPressed(KeyEvent.VK_DOWN))
				player.down(level);
			if(Game.keys.isKeyPressed(KeyEvent.VK_LEFT))
				player.left(level);
			if(Game.keys.isKeyPressed(KeyEvent.VK_RIGHT))
				player.right(level);
		}
		
		

		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
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
		
}
