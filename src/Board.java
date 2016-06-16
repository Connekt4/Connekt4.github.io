import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;


public class Board {

	static final int WIDTH_SIZE = (Connect4.WIDTH) / 7, HEIGHT_SIZE = (Connect4.HEIGHT) / 6;
	public static Piece[][] pieces = new Piece[7][6];
	public static ArrayList<int[]> moves = new ArrayList<>();
	//private static boolean hasWinner = false;
	public static boolean currentColor = true;
	
	
	public static int[] findCoordinate(int col, int row){
		
		int x = (Connect4.BORDER_WIDTH - Connect4.WIDTH) / 2, y = (Connect4.BORDER_HEIGHT - Connect4.HEIGHT) / 2;
		
		x += (col * WIDTH_SIZE);
		y += (row * HEIGHT_SIZE);
		
		return new int[]{x, y};
	}
	
	public static int[] positionOfEmpty(int col){
		if(col > pieces.length - 1)
			return null;
		for(int i = pieces[0].length - 1; i >= 0; i--){
			if(pieces[col][i] == null)
				return new int[]{findCoordinate(col, i)[0] + 9, findCoordinate(col, i)[1] + 9, col, i};
		}
		return null;
	}
	
	public static boolean fourInARow(){
		for(int a = 0; a < pieces[0].length; a++){
			for(int b = 0; b < pieces.length; b++){
				Piece p = pieces[b][a];
				if(p == null){
					continue;
				}
				else if(p != null){
					if(p.isBlue()){
						//Top-Left
						try{
							if(pieces[b - 1][a - 1].isBlue())
								if(pieces[b - 2][a - 2].isBlue())
									if(pieces[b - 3][a - 3].isBlue())
										return true;
						} catch(Exception e){
							//System.out.println("Index is out of bounds. No problemo, senior ;)");
						}
						//Left
						try{
							if(pieces[b - 1][a].isBlue())
								if(pieces[b - 2][a].isBlue())
									if(pieces[b - 3][a].isBlue())
										return true;
						} catch(Exception e){
							//System.out.println("Index is out of bounds. No problemo, senior ;)");
						}
						//Bot-Left
						try{
							if(pieces[b - 1][a + 1].isBlue())
								if(pieces[b - 2][a + 2].isBlue())
									if(pieces[b - 3][a + 3].isBlue())
										return true;
						} catch(Exception e){
							//System.out.println("Index is out of bounds. No problemo, senior ;)");
						}
						//Down
						try{
							if(pieces[b][a + 1].isBlue())
								if(pieces[b][a + 2].isBlue())
									if(pieces[b][a + 3].isBlue())
										return true;
						} catch(Exception e){
							//System.out.println("Index is out of bounds. No problemo, senior ;)");
						}
						//Up
						try{
							if(pieces[b][a - 1].isBlue())
								if(pieces[b][a - 2].isBlue())
									if(pieces[b][a - 3].isBlue())
										return true;
						} catch(Exception e){
							//System.out.println("Index is out of bounds. No problemo, senior ;)");
						}
						//Top-Right
						try{
							if(pieces[b + 1][a - 1].isBlue())
								if(pieces[b + 2][a - 2].isBlue())
									if(pieces[b + 3][a - 3].isBlue())
										return true;
						} catch(Exception e){
							//System.out.println("Index is out of bounds. No problemo, senior ;)");
						}
						//Right
						try{
							if(pieces[b + 1][a].isBlue())
								if(pieces[b + 2][a].isBlue())
									if(pieces[b + 3][a].isBlue())
										return true;
						} catch(Exception e){
							//System.out.println("Index is out of bounds. No problemo, senior ;)");
						}
						//Bot-Right
						try{
							if(pieces[b + 1][a + 1].isBlue())
								if(pieces[b + 2][a + 2].isBlue())
									if(pieces[b + 3][a + 3].isBlue())
										return true;
						} catch(Exception e){
							//System.out.println("Index is out of bounds. No problemo, senior ;)");
						}		
					}
					else if(!p.isBlue()){
						//Top-Left
						try{
							if(!pieces[b - 1][a - 1].isBlue())
								if(!pieces[b - 2][a - 2].isBlue())
									if(!pieces[b - 3][a - 3].isBlue())
										return true;
						} catch(Exception e){
							//System.out.println("Index is out of bounds. No problemo, senior ;)");
						}
						//Left
						try{
							if(!pieces[b - 1][a].isBlue())
								if(!pieces[b - 2][a].isBlue())
									if(!pieces[b - 3][a].isBlue())
										return true;
						} catch(Exception e){
							//System.out.println("Index is out of bounds. No problemo, senior ;)");
						}
						//Bot-Left
						try{
							if(!pieces[b - 1][a + 1].isBlue())
								if(!pieces[b - 2][a + 2].isBlue())
									if(!pieces[b - 3][a + 3].isBlue())
										return true;
						} catch(Exception e){
							//System.out.println("Index is out of bounds. No problemo, senior ;)");
						}
						//Down
						try{
							if(!pieces[b][a + 1].isBlue())
								if(!pieces[b][a + 2].isBlue())
									if(!pieces[b][a + 3].isBlue())
										return true;
						} catch(Exception e){
							//System.out.println("Index is out of bounds. No problemo, senior ;)");
						}
						//Up
						try{
							if(!pieces[b][a - 1].isBlue())
								if(!pieces[b][a - 2].isBlue())
									if(!pieces[b][a - 3].isBlue())
										return true;
						} catch(Exception e){
							//System.out.println("Index is out of bounds. No problemo, senior ;)");
						}
						//Top-Right
						try{
							if(!pieces[b + 1][a - 1].isBlue())
								if(!pieces[b + 2][a - 2].isBlue())
									if(!pieces[b + 3][a - 3].isBlue())
										return true;
						} catch(Exception e){
							//System.out.println("Index is out of bounds. No problemo, senior ;)");
						}
						//Right
						try{
							if(!pieces[b + 1][a].isBlue())
								if(!pieces[b + 2][a].isBlue())
									if(!pieces[b + 3][a].isBlue())
										return true;
						} catch(Exception e){
							//System.out.println("Index is out of bounds. No problemo, senior ;)");
						}
						//Bot-Right
						try{
							if(!pieces[b + 1][a + 1].isBlue())
								if(!pieces[b + 2][a + 2].isBlue())
									if(!pieces[b + 3][a + 3].isBlue())
										return true;
						} catch(Exception e){
							//System.out.println("Index is out of bounds. No problemo, senior ;)");
						}		
					}
				}
			}
		}
		return false;
	}
	
	
	public static void display(Graphics g){
		Color current = g.getColor();
		Font currentFont = g.getFont();
		
		g.setColor(Colors.BLACK);
		
		Font newFont = new Font(currentFont.getName(), currentFont.getStyle(), 25);
		g.setFont(newFont);
		
		//Draw vertical lines
		for(int i = 0; i < pieces.length + 1; i++){
			g.drawLine(findCoordinate(i, 0)[0], findCoordinate(i, 0)[1], findCoordinate(i, 6)[0], findCoordinate(i, 6)[1]);
			if(i != pieces.length) g.drawString("" + i, findCoordinate(i, 6)[0] + Connect4.WIDTH / pieces.length / 2, findCoordinate(i, 6)[1] + 50);
		}
		
		//Draw horizontal lines
		for(int i = 0; i < pieces[0].length + 1; i++){
			g.drawLine(findCoordinate(0, i)[0], findCoordinate(0, i)[1], findCoordinate(7, i)[0], findCoordinate(7, i)[1]);
		}
		
		for(Piece[] a : pieces){
			for(Piece b : a){
				if(b != null)
					b.display(g);
			}
		}
		
		g.setColor(current);
		g.setFont(currentFont);
	}
}
