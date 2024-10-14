package fr.pcvizelt.jpdfviewer;

import java.io.File;

import javax.swing.JFrame;

public class TestApp
{
    public static void main(final String[] args) {
        File pdfFile = new File(args[0]);
        JFrame frame = new JFrame();
        frame.setTitle("JPDFViewer");
        JPDFViewer pdfViewer = new JPDFViewer(pdfFile);
        pdfViewer.getView().setPreviewDotsPerInch(150);
        frame.setSize(1280, 720);
        frame.add(pdfViewer);
        frame.setDefaultCloseOperation(3);
        frame.setVisible(true);
    }
}
