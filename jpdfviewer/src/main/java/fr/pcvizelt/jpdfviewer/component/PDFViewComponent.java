package fr.pcvizelt.jpdfviewer.component;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.JComponent;
import javax.swing.JScrollPane;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

import fr.pcvizelt.jpdfviewer.page.PDFPage;

public class PDFViewComponent extends JComponent
{
    private static final long serialVersionUID = 1L;
    private static int PREVIEW_DPI = 600;
    private float zoom;
    private static int PREVIEW_SPACE_BETWEEN_PAGES =  10;
    private HashMap<Integer, PDFPage> renderPageMap;
    private File currentFile;
    private JScrollPane rootScrollPane;
 
    
    public PDFViewComponent(File pdfFile) {
        this.zoom = 1.0f;
        this.renderPageMap = new HashMap<Integer, PDFPage>();
        if (!pdfFile.exists()) {
            return;
        }
        this.currentFile = pdfFile;
    }
    
    public void load() {
        try {
            final PDDocument document = Loader.loadPDF(this.currentFile);
            final PDFRenderer pdfRenderer = new PDFRenderer(document);
            for (int page = 0; page < document.getNumberOfPages(); ++page) {
                final BufferedImage bim = pdfRenderer.renderImageWithDPI(page, (float)PDFViewComponent.PREVIEW_DPI, ImageType.RGB);
                final PDFPage currentPage = new PDFPage(bim, page, this);
                this.renderPageMap.put(currentPage.getPageNumber(), currentPage);
            }
            document.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        this.repaint();
    }
    
    float horPer ;
    @Override
    public void paint(Graphics g) {
    	
        int height = 0;
        int width = 0;
        for (final PDFPage page : this.renderPageMap.values()) {
            page.draw(g);
            height += page.getRenderingHeight() + PDFViewComponent.PREVIEW_SPACE_BETWEEN_PAGES;
            if (width < page.getRenderingWidth()) {
                width = page.getRenderingWidth();
            }
        }
        

        
        this.setPreferredSize(new Dimension(width, height));
        getParent().revalidate();
        
        
        
    }
    
    public void setPreviewDotsPerInch(int dpi) {
        PDFViewComponent.PREVIEW_DPI = dpi;
        this.load();
    }
    
    public void setZoom(float zoom) {
        this.zoom = zoom;

        repaint();
        
        rootScrollPane = (JScrollPane) this.getParent().getParent();
        rootScrollPane.getViewport().setViewPosition(new Point(getPreferredSize().width/2 - rootScrollPane.getWidth()/2, rootScrollPane.getViewport().getViewPosition().y));
    }
    
    public float getZoom() {
        return this.zoom;
    }
    
    public int getPreviewDotsPerInch() {
        return PDFViewComponent.PREVIEW_DPI;
    }
    
    public void setSpaceBetweenPages(int space) {
        PDFViewComponent.PREVIEW_SPACE_BETWEEN_PAGES = space;
        this.load();
    }
    
    public int getSpaceBetweenPages() {
        return PDFViewComponent.PREVIEW_SPACE_BETWEEN_PAGES;
    }
    
    public File getCurrentFile() {
        return this.currentFile;
    }
    
    public void setCurrentFile(File currentFile) {
        this.currentFile = currentFile;
    }
    
}
