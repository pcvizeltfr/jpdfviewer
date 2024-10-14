package fr.pcvizelt.jpdfviewer;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import fr.pcvizelt.jpdfviewer.component.*;

public class JPDFViewer extends JPanel
{
    private static final long serialVersionUID = 1L;
    private PDFViewComponent pdfViewComponent;
    private PDFViewToolbar pdfViewToolbar;
    private File currentPdfFile;
    
    public JPDFViewer(final File pdfFile) {
        this.setLayout(new BorderLayout());
        this.currentPdfFile = pdfFile;
        this.pdfViewComponent = new PDFViewComponent(pdfFile);
        this.pdfViewToolbar = new PDFViewToolbar(pdfFile);
        final JScrollPane scrollPane = new JScrollPane(this.pdfViewComponent);
        this.add(this.pdfViewToolbar, "First");
        this.add(scrollPane, "Center");
        this.pdfViewComponent.load();
    }
    
    public File getCurrentFile() {
        return this.currentPdfFile;
    }
    
    public PDFViewToolbar getToolbar() {
        return this.pdfViewToolbar;
    }
    
    public PDFViewComponent getView() {
        return this.pdfViewComponent;
    }
}