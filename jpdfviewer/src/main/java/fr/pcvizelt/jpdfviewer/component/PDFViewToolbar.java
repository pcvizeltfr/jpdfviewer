package fr.pcvizelt.jpdfviewer.component;

import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.event.*;
import fr.pcvizelt.jpdfviewer.*;
import java.awt.*;

public class PDFViewToolbar extends JToolBar
{
    private static final long serialVersionUID = 1L;
    private File currentPdfFile;
    protected JButton openPdf;
    protected JSlider zoomSlider;
    private ActionListener openPdfAL;
    private ChangeListener zoomSliderCL;
    
    public PDFViewToolbar(final File currentFile) {
        this.openPdfAL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Desktop.getDesktop().open(currentPdfFile);
                }
                catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
        };
        this.zoomSliderCL = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                PDFViewComponent pdfViewComp = ((JPDFViewer)PDFViewToolbar.this.getParent()).getView();
                pdfViewComp.setZoom(PDFViewToolbar.this.zoomSlider.getValue() / 100.0f);
            }
        };
        this.currentPdfFile = currentFile;
        (this.openPdf = new JButton("Open PDF")).addActionListener(this.openPdfAL);
        (this.zoomSlider = new JSlider(20, 1000)).setValue(100);
        this.zoomSlider.addChangeListener(this.zoomSliderCL);
        this.add(this.openPdf);
        this.add(this.zoomSlider);
    }
    
}
