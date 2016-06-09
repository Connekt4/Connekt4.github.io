import java.awt.Color;
import java.awt.Graphics;

public class Piece {

	/**
	 * x coordinate
	 */
	private int x;

	/**
	 * y coordinate
	 */
	private int y;

	/**
	 * color of piece
	 */
	private Color color;
	

	static final int radius = Connect4.HEIGHT / 14;

	public Piece(int x, int y, boolean isBlue) {
		this.x = x;
		this.y = y;
		if (isBlue)
			color = Colors.LIGHT_BLUE;
		else
			color = Colors.RED;
	}

	public void display(Graphics g) {
		Color current = g.getColor();
		
		if(color.equals(Colors.LIGHT_BLUE)) g.setColor(Colors.DARK_BLUE);
		else g.setColor(Colors.DARK_RED);
		g.fillOval(x - 5, y - 5, radius * 2 + 10, radius * 2 + 10);
		
		g.setColor(color);
		g.fillOval(x, y, radius * 2, radius * 2);
		
		g.setColor(current);
	}
	
	public boolean isBlue(){
		return color.equals(Colors.LIGHT_BLUE);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Piece other = (Piece) obj;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	
	

}
