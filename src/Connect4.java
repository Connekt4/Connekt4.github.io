import java.applet.Applet;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


public class Connect4 extends Applet implements MouseMotionListener, MouseListener, KeyListener{
	static final int WIDTH = 700, HEIGHT = (int)(((double)(WIDTH / 7)) * 6);
	static final int BORDER_WIDTH = 1250, BORDER_HEIGHT = 900;
	int mouseCol = -1;
	public void init(){
		
		setBackground(Colors.LIGHT_GREEN);
		setFocusable(true);
		setSize(BORDER_WIDTH, BORDER_HEIGHT);
		addMouseMotionListener(this);
		addMouseListener(this);
		addKeyListener(this);
	}
	public void start(){
		
	}
	
	public void paint(Graphics g){
		//piece.display(g);
		Board.display(g);
		if(!Board.fourInARow()){
			displayMouseCol(g);
		}
		else
			displayWinner(g);
		
	}
	
	public void displayMouseCol(Graphics g){
		String display = "mouseCol = " + mouseCol;
		Font myFont = g.getFont();
		Font newFont = new Font(myFont.getName(), myFont.getStyle(), 25);
		g.setFont(newFont);
		g.drawString(display, (BORDER_WIDTH - WIDTH) / 2, (BORDER_HEIGHT - HEIGHT) / 3);
	}
	
	public void displayWinner(Graphics g){
		String display;
		if(!Board.currentColor)
			display = "BLUE";
		else display = "RED";
		
		display += " IS THE WINNER!!!"; 
		
		Font myFont = g.getFont();
		Font newFont = new Font(myFont.getName(), myFont.getStyle(), 100);
		g.setFont(newFont);
		g.drawString(display, (BORDER_WIDTH - WIDTH) / 6, (BORDER_HEIGHT - HEIGHT) / 3);
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
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_R){
			Board.pieces = new Piece[7][6];
			Board.currentColor = true;
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
