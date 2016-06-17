import java.applet.Applet;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

/*
 * Made by @IshanArya
 */

public class Connect4 extends Applet implements MouseMotionListener, MouseListener, KeyListener{
	static final int WIDTH = 700, HEIGHT = (int)(((double)(WIDTH / 7)) * 6);
	static final int BORDER_WIDTH = 1250, BORDER_HEIGHT = 900;
	int mouseCol = -1;
	Graphics bg;
	Image offscreen;
	public void init(){
		
		setBackground(Colors.LIGHT_GREEN);
		setFocusable(true);
		setSize(BORDER_WIDTH, BORDER_HEIGHT);
		addMouseMotionListener(this);
		addMouseListener(this);
		addKeyListener(this);
		offscreen = createImage(BORDER_WIDTH, BORDER_HEIGHT);
		bg = offscreen.getGraphics();
	}
	public void start(){
		
	}
	
	public void paint(Graphics g){
		//piece.display(g);
		bg.clearRect(0, 0, BORDER_WIDTH, BORDER_HEIGHT);
		Board.display(bg);
		if(!Board.fourInARow()){
			displayMouseCol(bg);
			displayOptions(bg);
		}
		else
			displayWinner(bg);
		
		g.drawImage(offscreen, 0, 0, this);
		
	}
	
	public void update(Graphics g){
		paint(g);
	}
	
	public void displayMouseCol(Graphics g){
		String display = "mouseCol = " + mouseCol;
		Font myFont = g.getFont();
		Font newFont = new Font(myFont.getName(), myFont.getStyle(), 25);
		g.setFont(newFont);
		g.drawString(display, (BORDER_WIDTH - WIDTH) / 2, (BORDER_HEIGHT - HEIGHT) / 3);
	}
	
	public void displayOptions(Graphics g){
		String display = "Press \"U\" to undo";
		Font myFont = g.getFont();
		Font newFont = new Font(myFont.getName(), myFont.getStyle(), 25);
		g.setFont(newFont);
		g.drawString(display, (BORDER_WIDTH - WIDTH) + 2 * (WIDTH / 6), (BORDER_HEIGHT - HEIGHT) / 3);
	}
	
	public void displayWinner(Graphics g){
		String display;
		if(!Board.currentColor)
			display = "BLUE";
		else display = "RED";
		
		display += " IS THE WINNER!!!"; 
		
		Font myFont = g.getFont();
		Font newFont = new Font(myFont.getName(), myFont.getStyle(), 100);
		bg.setFont(newFont);
		bg.drawString(display, (BORDER_WIDTH - WIDTH) / 6, (BORDER_HEIGHT - HEIGHT) / 3);
		
		String reset = "Press \"R\" to restart";
		newFont = new Font(myFont.getName(), myFont.getStyle(), 25);
		g.setFont(newFont);
		g.drawString(reset, (BORDER_WIDTH - WIDTH) + 2 * (WIDTH / 6), (BORDER_HEIGHT - HEIGHT) / 2 - 10);
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		if(e.getX() < (BORDER_WIDTH - WIDTH)/2 || e.getX() > (BORDER_WIDTH - WIDTH)/2 + WIDTH)
			mouseCol = -1;
		else
			mouseCol = (e.getX() - (BORDER_WIDTH - WIDTH)/2)/Board.WIDTH_SIZE;
		repaint();
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if(!Board.fourInARow()){
			if(mouseCol == -1)
				return; //System.out.println("Not a block");
			else{
				try{
					int x = Board.positionOfEmpty(mouseCol)[3];
					Board.pieces[mouseCol][x] = new Piece(Board.positionOfEmpty(mouseCol)[0], Board.positionOfEmpty(mouseCol)[1], Board.currentColor);
					Board.currentColor = !Board.currentColor;
					Board.moves.add(new int[]{mouseCol, x});
				}catch(Exception e1){
					System.out.println("Column is full!");
				}
			//System.out.println("Place!");
			}
			repaint();
		}
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		char keyChar = e.getKeyChar();
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_R){
			Board.pieces = new Piece[7][6];
			Board.currentColor = true;
			Board.moves = new ArrayList<>();
			repaint();
		}
		if(key == KeyEvent.VK_U){
			try{
				Board.pieces[Board.moves.get(Board.moves.size() - 1)[0]][Board.moves.remove(Board.moves.size() - 1)[1]] = null;
				Board.currentColor = !Board.currentColor;
				repaint();
			} catch(Exception e1){
				System.out.println("All undos are used up.");
			}
		}
		if(keyChar >= 48 && keyChar <= 54 && !Board.fourInARow()){
			try{
				int t = Character.getNumericValue(keyChar);
				int x = Board.positionOfEmpty(t)[3];
				Board.pieces[t][x] = new Piece(Board.positionOfEmpty(t)[0], Board.positionOfEmpty(t)[1], Board.currentColor);
				Board.currentColor = !Board.currentColor;
				Board.moves.add(new int[]{t, x});
				repaint();
			} catch(Exception e1){
				System.out.println("Column is full!");
			}
		}
		
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
