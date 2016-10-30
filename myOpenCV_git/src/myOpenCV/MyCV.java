package myOpenCV;
/*
 * import com.googlecode.javacv.*;
import com.googlecode.javacv.cpp.*;
import com.googlecode.javacv.cpp.opencv_core.*;
import com.googlecode.javacv.cpp.opencv_highgui.*;


import static com.googlecode.javacv.cpp.opencv_core.*;
import static com.googlecode.javacv.cpp.opencv_imgproc.*;
import static com.googlecode.javacv.cpp.opencv_calib3d.*;
import static com.googlecode.javacv.cpp.opencv_objdetect.*;
import static com.googlecode.javacv.cpp.opencv_highgui.*;
 */
import com.googlecode.javacpp.Loader;
import com.googlecode.javacv.*;
import com.googlecode.javacv.cpp.*;
import com.googlecode.javacv.cpp.opencv_imgproc.CvMoments;
import com.googlecode.javacv.cpp.opencv_core.*;
import com.googlecode.javacv.cpp.opencv_highgui.*;

import static com.googlecode.javacv.cpp.opencv_core.*;
import static com.googlecode.javacv.cpp.opencv_imgproc.*;
import static com.googlecode.javacv.cpp.opencv_calib3d.*;
import static com.googlecode.javacv.cpp.opencv_objdetect.*;
import static com.googlecode.javacv.cpp.opencv_highgui.*;
// imread, imwrite, etc
public class MyCV {

	public static void main(String[] args) {
		try {
			//cvLoadImage("");
			
//			BufferedImage img = ImageIO.read(new File("vv.jpg"));
//			IplImage origImg = IplImage.createFrom(img);
//			CvCapture cp = opencv_highgui.cvCreateCameraCapture(0);
//			opencv_highgui.cvSetCaptureProperty(cp, opencv_highgui.CV_CAP_PROP_FRAME_HEIGHT, 720);
//			opencv_highgui.cvSetCaptureProperty(cp, opencv_highgui.CV_CAP_PROP_FRAME_WIDTH, 720);
//			IplImage ip = opencv_highgui.cvQueryFrame(cp);
//			
//			CanvasFrame frame = new CanvasFrame("webcam");
//			while(frame.isVisible() && (ip = opencv_highgui.cvQueryFrame(cp)) != null){
//				frame.showImage(ip);
//			}
			
//			ImageIcon icon = new ImageIcon(img);
//			JFrame frame = new JFrame();
//			frame.setLayout(new FlowLayout());
//			frame.setSize(img.getWidth(null) + 50, img.getHeight(null) + 50);
//			JLabel lbl = new JLabel();
//			lbl.setIcon(icon);
//			frame.add(lbl);
//			frame.setVisible(true);
//			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			
			
			
			CvCapture capture  = cvCreateCameraCapture(CV_CAP_ANY);
			IplImage frame;
			//IplImage grayimg = cvCreateImage(cvSize(640,480),IPL_DEPTH_8U,1);
			
			IplImage imghsv,imgbin;
			
		
			cvNamedWindow("Video",CV_WINDOW_AUTOSIZE);
			CvScalar min = cvScalar(95, 150, 75, 0);
			CvScalar max = cvScalar(145, 255, 255, 0);
			/*CvSeq contour1 = new CvSeq(),contour2;
			CvMemStorage storage = CvMemStorage.create();			
			double areaMax =1000,areaC =0;*/
			
			char c ;
			CvMoments moment = new CvMoments(Loader.sizeof(CvMoments.class));
			double m10,m01,m_area;
			int posX = 0 ,posY  = 0 ;
			frame = cvQueryFrame(capture);
			imghsv = cvCreateImage(cvGetSize(frame),8,3);
			imgbin = cvCreateImage(cvGetSize(frame),8,1);
			for(;;){
				frame = cvQueryFrame(capture);
				if(frame ==null){
					System.out.println("error video file");
					break;
				}
				
				
				                                                          
				cvCvtColor(frame, imghsv,CV_BGR2HSV);	
							
				cvInRangeS(imghsv, min, max, imgbin);
				//cvCvtColor(frame, grayimg,CV_BGR2GRAY);	
				/*cvFindContours(imgbin, storage,contour1,Loader.sizeof(CvContour.class),CV_RETR_LIST,CV_LINK_RUNS,cvPoint(0,0));
				contour2 = contour1;		
				while(contour1 !=null && !contour1.isNull()){
					areaC = cvContourArea(contour1,CV_WHOLE_SEQ,1);
					if(areaC >areaMax){
						areaMax = areaC;
					}
					contour1 = contour1.h_next();
				}
				while(contour2 !=null && !contour2.isNull()){
					areaC = cvContourArea(contour2,CV_WHOLE_SEQ,1);
					if(areaC < areaMax){
						cvDrawContours(imgbin, contour2, CV_RGB(0,0,0),CV_RGB(0,0,0), 0, CV_FILLED, 8,cvPoint(0,0));
						areaMax = areaC;
					}
					contour2 = contour2.h_next();
				}*/
				
				cvMoments(imgbin,moment, 1);
				m10=cvGetSpatialMoment(moment, 1,0);
				m01=cvGetSpatialMoment(moment, 0,1);
				m_area = cvGetSpatialMoment(moment,0,0);
				
				posX = (int)(m10/m_area);
				posY = (int)(m01/m_area);
				if(posX > 0 && posY >0){
					System.out.println("posX :"+posX+"  posY :"+posY);
				}
				cvCircle(frame,cvPoint(posX,posY), 5, cvScalar(0,255, 0,0),9, 0,0);
				cvLine(frame, cvPoint(0,0), cvPoint(posX+50,posY+50), cvScalar(0,255, 0,0),1, 0,0);
				cvShowImage("Video", frame);
				cvShowImage("binary", imgbin);
				//cvShowImage("binary", imgbin);
				c  = (char)cvWaitKey(30);
				if(c ==27)break;
			}
			cvReleaseCapture(capture);
			cvDestroyAllWindows();
		} catch (Exception e) {

		}
	}

}
