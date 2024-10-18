package fr.pcvizelt.jpdfviewer.component;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JToolBar;

import fr.pcvizelt.jpdfviewer.JPDFViewer;

public class PDFViewToolbar extends JToolBar
{
    private static final long serialVersionUID = 1L;
    private File currentPdfFile;
    
    protected JButton openPdf;
    protected JButton zoomIn;
    protected JButton zoomOut;
    protected JButton resetZoom;
    protected JComboBox<String> currentZoom;
    
    public PDFViewToolbar(final File currentFile) {
        
       
        this.currentPdfFile = currentFile;
        
        openPdf = new JButton("Open PDF");
        openPdf.addActionListener(this.openPdfAL);
        
        zoomIn = new JButton("[+]");
        zoomIn.addActionListener(zoomInAL);
        zoomOut = new JButton("[-]");
        zoomOut.addActionListener(zoomOutAL);
        String[] zooms = new String[] {
        		"50%",
        		"100%",
        		"150%",
        		"200%",
        		"300%",
        		"400%",
        		"600%",
        		"1000%",
        		"2000%",
        };
        
        currentZoom = new JComboBox<String>(zooms);
        currentZoom.setSelectedIndex(1);
        currentZoom.setEditable(true);
        
        add(openPdf);
        add(zoomIn);
        add(currentZoom);
        add(zoomOut);
    }
    
    /**
     * 
     * 	ACTION LISTENERS
     * 
     */
    private ActionListener openPdfAL = new ActionListener() {
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
    
    private ActionListener zoomInAL = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			PDFViewComponent view = ((JPDFViewer) getParent()).getView();
			
			view.setZoom(view.getZoom()*1.4f);
			
		}
	};
	
	 private ActionListener zoomOutAL = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PDFViewComponent view = ((JPDFViewer) getParent()).getView();
				
				view.setZoom(view.getZoom()/1.4f);
			}
		};
    
}
