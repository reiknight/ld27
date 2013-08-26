package jam.ld27.tilemap;

import infinitedog.frisky.textures.TextureManager;
import org.newdawn.slick.Image;

public class TileSet {
    private Image image;
    private int tileWidth;
    private int tileHeight;
    private int width;
    private int height;
    private int cols;
    private int rows;
    
    public TileSet(String name, int tileSize) {
        this(name, tileSize, tileSize);
    }
    
    public TileSet(String name, int tileWidth, int tileHeight) {
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
        this.setTexture(name);
    }
    
    public void setTexture(String name) {
        this.image = TextureManager.getInstance().getTexture(name);
        image = TextureManager.getInstance().getTexture(name);
        // Grab image size
        width = image.getWidth();
        height = image.getHeight();
        // Set columns and rows based on tileSize
        cols = width / tileWidth;
        rows = height / tileHeight;
    }
    
    /**
     * Renders tile given an id.     
     * 
     * It will render a tile of 1x1 tileSize on the given
     * location.
     * 
     * @param id
     * @param x
     * @param y
     */
    public void render(int id, int x, int y) {
        // Bypass the function to the dimension version with 1x1
        render(id, x, y, 1, 1);
    }
    
    public void render(int id, float x, float y) {
        // Bypass the function to the dimension version with 1x1
        render(id, x, y, 1, 1);
    }
    
     /**
     * Renders tile given an id.     
     * 
     * It will render a tile of nxm tileSize on the given
     * location.
     * 
     * @param id
     * @param x
     * @param y
     * @param n
     * @param m
     */
    public void render(int id, int x, int y, int n, int m) {
        int i, j;
        Image subImage;
        
        /**
         * TileSet ids are represented like this:
         * 
         * 0 | 1 | 2
         * 3 | 4 | 5
         */
        
        i  = id / cols;
        j = id % cols;
        
        subImage = this.image.getSubImage(j  * tileWidth, i  * tileHeight, tileWidth * n, tileHeight * m);
        subImage.draw(x, y);
    }
    
     public void render(int id, float x, float y, int n, int m) {
        int i, j;
        Image subImage;
        
        /**
         * TileSet ids are represented like this:
         * 
         * 0 | 1 | 2
         * 3 | 4 | 5
         */
        
        i  = id / cols;
        j = id % cols;
        
        subImage = this.image.getSubImage(j  * tileWidth, i  * tileHeight, tileWidth * n, tileHeight * m);
        subImage.draw(x, y);
    }
     
    public int getRows() {
        return rows;
    }
    
    public int getCols() {
        return cols;
    }
    
    public int size() {
        return cols * rows;
    }
}
