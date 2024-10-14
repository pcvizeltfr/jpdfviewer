package fr.pcvizelt.jpdfviewer.page;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import fr.pcvizelt.jpdfviewer.component.*;

public class PDFPage
{
    private BufferedImage pageImage;
    private int pageIndex;
    private float aspectRatio;
    private PDFViewComponent parent;
    
    public PDFPage(BufferedImage pageImage, int pageIndex, PDFViewComponent parentView) {
        this.pageIndex = -1;
        this.aspectRatio = 1.0f;
        this.pageImage = pageImage;
        this.pageIndex = pageIndex;
        this.parent = parentView;
        this.aspectRatio = 1.0f * pageImage.getWidth() / (1.0f * pageImage.getHeight());
    }
    
    public void draw(Graphics g) {
        ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_SPEED);
        g.drawImage(this.pageImage, 0, this.pageIndex * (this.getRenderingHeight() + this.getParent().getSpaceBetweenPages()), this.getRenderingWidth(), this.getRenderingHeight(), null);
    }
    
    public int getRenderingWidth() {
        return (int)(this.parent.getParent().getWidth() * this.getParent().getZoom());
    }
    
    public int getRenderingHeight() {
        return (int)(this.getRenderingWidth() / this.aspectRatio);
    }
    
    public BufferedImage getImage() {
        return this.pageImage;
    }
    
    public int getPageNumber() {
        return this.pageIndex;
    }
    
    public float getAspectRatio() {
        return this.aspectRatio;
    }
    
    public PDFViewComponent getParent() {
        return this.parent;
    }
}
