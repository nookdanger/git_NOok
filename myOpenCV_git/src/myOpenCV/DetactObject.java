package myOpenCV;
import static com.googlecode.javacv.cpp.opencv_core.CV_FILLED;
import static com.googlecode.javacv.cpp.opencv_core.CV_RGB;
import static com.googlecode.javacv.cpp.opencv_core.CV_WHOLE_SEQ;
import static com.googlecode.javacv.cpp.opencv_core.cvCreateImage;
import static com.googlecode.javacv.cpp.opencv_core.cvDrawContours;
import static com.googlecode.javacv.cpp.opencv_core.cvInRangeS;
import static com.googlecode.javacv.cpp.opencv_core.cvPoint;
import static com.googlecode.javacv.cpp.opencv_core.cvReleaseMemStorage;
import static com.googlecode.javacv.cpp.opencv_core.cvScalar;
import static com.googlecode.javacv.cpp.opencv_core.cvSize;
import static com.googlecode.javacv.cpp.opencv_highgui.CV_CAP_ANY;
import static com.googlecode.javacv.cpp.opencv_highgui.cvCreateCameraCapture;
import static com.googlecode.javacv.cpp.opencv_highgui.cvDestroyAllWindows;
import static com.googlecode.javacv.cpp.opencv_highgui.cvQueryFrame;
import static com.googlecode.javacv.cpp.opencv_highgui.cvReleaseCapture;
import static com.googlecode.javacv.cpp.opencv_highgui.cvShowImage;
import static com.googlecode.javacv.cpp.opencv_highgui.cvWaitKey;
import static com.googlecode.javacv.cpp.opencv_imgproc.CV_BGR2HSV;
import static com.googlecode.javacv.cpp.opencv_imgproc.CV_LINK_RUNS;
import static com.googlecode.javacv.cpp.opencv_imgproc.CV_RETR_LIST;
import static com.googlecode.javacv.cpp.opencv_imgproc.cvContourArea;
import static com.googlecode.javacv.cpp.opencv_imgproc.cvCvtColor;
import static com.googlecode.javacv.cpp.opencv_imgproc.cvFindContours;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.googlecode.javacpp.Loader;
import com.googlecode.javacv.cpp.opencv_core.CvContour;
import com.googlecode.javacv.cpp.opencv_core.CvMemStorage;
import com.googlecode.javacv.cpp.opencv_core.CvScalar;
import com.googlecode.javacv.cpp.opencv_core.CvSeq;
import com.googlecode.javacv.cpp.opencv_core.IplImage;
import com.googlecode.javacv.cpp.opencv_highgui.CvCapture;
import com.googlecode.javacv.cpp.opencv_imgproc.IplConvKernel;
public class DetactObject extends JFrame{
	
	
	JFrame frame = new JFrame();
	JLabel label = new JLabel();
	JSlider slider = new JSlider(JSlider.HORIZONTAL,0,255,0);
	JSlider slider2 = new JSlider(JSlider.HORIZONTAL,0,255,0);
	JSlider slider3 = new JSlider(JSlider.HORIZONTAL,0,255,0);
	
	JSlider slider4 = new JSlider(JSlider.HORIZONTAL,0,255,0);
	JSlider slider5 = new JSlider(JSlider.HORIZONTAL,0,255,0);
	JSlider slider6 = new JSlider(JSlider.HORIZONTAL,0,255,0);
	JPanel panel  = new JPanel(new GridLayout(0,4));
	 Option  p = new Option();
	
	 class Option implements Serializable{

		private static final long serialVersionUID = 2103340720324505162L;
		int rMin;
		 int gMin;
		 int bMin;
		 int rMax;
		 int gMax;
		 int bMax;
	}
	
	
	
	public DetactObject(){
		label.setOpaque(true);
		slider.setMajorTickSpacing(10);
		slider.setPaintLabels(true);
		slider.setPaintTicks(true);
		 slider.setMajorTickSpacing(10);
		slider.addChangeListener(new ChangeListener() {		
			@Override
			public void stateChanged(ChangeEvent e) {
				p.rMin = ((JSlider)e.getSource()).getValue();				
			}
		});
		
		
		
		slider2.setMajorTickSpacing(10);
		slider2.setPaintLabels(true);
		slider2.setPaintTicks(true);
		slider2.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				p.gMin = ((JSlider)e.getSource()).getValue();
				
			}
		});
		
		
		
		slider3.setMajorTickSpacing(10);
		slider3.setPaintLabels(true);
		slider3.setPaintTicks(true);
		slider3.addChangeListener(new ChangeListener() {		
			@Override
			public void stateChanged(ChangeEvent e) {
				p.bMin = ((JSlider)e.getSource()).getValue();
				
			}
		});
		
		
		
		slider4.setMajorTickSpacing(10);
		slider4.setPaintLabels(true);
		slider4.setPaintTicks(true);
		 slider4.setMajorTickSpacing(10);
		slider4.addChangeListener(new ChangeListener() {		
			@Override
			public void stateChanged(ChangeEvent e) {
				p.rMax= ((JSlider)e.getSource()).getValue();				
			}
		});
		
		
		
		slider5.setMajorTickSpacing(10);
		slider5.setPaintLabels(true);
		slider5.setPaintTicks(true);
		slider5.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				p.gMax = ((JSlider)e.getSource()).getValue();
				
			}
		});
		
		
		
		slider6.setMajorTickSpacing(10);
		slider6.setPaintLabels(true);
		slider6.setPaintTicks(true);
		slider6.addChangeListener(new ChangeListener() {		
			@Override
			public void stateChanged(ChangeEvent e) {
				p.bMax = ((JSlider)e.getSource()).getValue();
				
			}
		});
		panel.add(new Label("R-min : "));
		panel.add(slider);
		panel.add(new Label("R-max : "));
		panel.add(slider4);
		panel.add(new Label("G-min : "));
		panel.add(slider2);
		panel.add(new Label("G-max : "));
		panel.add(slider5);
		panel.add(new Label("B-min : "));
		panel.add(slider3);
		panel.add(new Label("B-max : "));
		panel.add(slider6);
		frame.add(label,BorderLayout.CENTER);
		frame.add(panel);

		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}


	public static void main(String[] args) {
		 try{
			 
			 DetactObject  d = new DetactObject();
			 try{
		FileInputStream fin = new FileInputStream("save.dat");
		 ObjectInputStream ois = new ObjectInputStream(fin);
		 d.p = (Option) ois.readObject();
		 ois.close();
			 }catch(Exception e){
			
			 }
		d.slider.setValue(d.p.rMin);
		d.slider4.setValue(d.p.rMax);
		d.slider2.setValue(d.p.gMin);
		d.slider5.setValue(d.p.gMax);
		d.slider3.setValue(d.p.bMin);
		d.slider6.setValue(d.p.bMax);

		CvCapture capture  = cvCreateCameraCapture(CV_CAP_ANY);
		IplImage img ;
		IplImage imghsv;
		IplImage imgbin;
		//cvNamedWindow("Video",CV_WINDOW_AUTOSIZE);
		char c ;
		
		CvScalar min = cvScalar(95, 150, 75, 0);
		CvScalar max = cvScalar(145, 255, 255, 0);
		//img = cvQueryFrame(capture);
//		imghsv = cvCreateImage(cvGetSize(img),8,3);
//		imgbin = cvCreateImage(cvGetSize(img),8,1);
		imghsv = cvCreateImage(cvSize(640,480),8,3);
		imgbin = cvCreateImage(cvSize(640,480),8,1);
//		
		
		
		
		CvSeq contour1 ,contour2;
		CvMemStorage storage = CvMemStorage.create();			
		double areaMax,areaC =0;
		IplConvKernel mat;
		
		for(;;){
			min = cvScalar(d.p.rMin, d.p.gMin, d.p.bMin, 0);
			max = cvScalar(d.p.rMax, d.p.gMax, d.p.bMax, 0);
			img = cvQueryFrame(capture);
			//imghsv = cvCreateImage(cvGetSize(img),8,3);
			if(img==null)break;
			cvCvtColor(img, imghsv,CV_BGR2HSV);	
			cvInRangeS(imghsv, min, max, imgbin);
			contour1 = new CvSeq();
			 areaMax=1000;
			// mat=cvCreateStructuringElementEx(7, 7, 3, 3, CV_SHAPE_RECT,    null);
			
			//cvShowImage("bin1", imgbin);
			//cvDilate(imgbin, imgbin, mat, CV_C);
			//cvErode(imgbin, imgbin, mat, CV_C);
			cvFindContours(imgbin, storage,contour1,Loader.sizeof(CvContour.class),CV_RETR_LIST,CV_LINK_RUNS,cvPoint(0,0));
			contour2 = contour1;		
			 
			while(contour1 !=null && !contour1.isNull()){
				areaC = cvContourArea(contour1,CV_WHOLE_SEQ,1);
				if(areaC >areaMax)
					areaMax = areaC;
				
				contour1 = contour1.h_next();
			}
			while(contour2 !=null && !contour2.isNull()){
				areaC = cvContourArea(contour2,CV_WHOLE_SEQ,1);
				if(areaC < areaMax){
					cvDrawContours(imgbin, contour2, CV_RGB(0,0,0),CV_RGB(0,0,0),
							0, CV_FILLED, 8,cvPoint(0,0));
				}
				contour2 = contour2.h_next();
			}
			
			cvShowImage("Video", img);
			//cvShowImage("hsv", imghsv);
			cvShowImage("bin", imgbin);
			
			c  = (char)cvWaitKey(30);
			if(c ==27)break;
		}
		cvReleaseCapture(capture);
		cvReleaseMemStorage(storage);
		cvDestroyAllWindows();
		
		
		FileOutputStream fout = new FileOutputStream("save.dat");
		ObjectOutputStream oos = new ObjectOutputStream(fout);
		oos.writeObject(d.p);
	}catch(Exception e){
		
	}
	}
}
