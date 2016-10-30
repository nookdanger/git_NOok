package myOpenCV;
import com.googlecode.javacpp.Loader;
import com.googlecode.javacv.*;
import com.googlecode.javacv.cpp.*;
import com.googlecode.javacv.cpp.opencv_core.*;
import com.googlecode.javacv.cpp.opencv_highgui.*;

import static com.googlecode.javacv.cpp.opencv_core.*;
import static com.googlecode.javacv.cpp.opencv_imgproc.*;
import static com.googlecode.javacv.cpp.opencv_calib3d.*;
import static com.googlecode.javacv.cpp.opencv_objdetect.*;
import static com.googlecode.javacv.cpp.opencv_highgui.*;

public class Contours {

	
	
	public static void main(String[] args) {
		
//		JFrame frame = new JFrame("A JFrame");
//		frame.setSize(250, 250);
//		frame.setLocation(300,200);
//		frame.setVisible(true);
		
		
		
		IplImage img1,imghsv,imgbin;
		CvScalar minc = cvScalar(95,150,75,0),maxc = cvScalar(145,255,255,0);
		//CvScalar min = cvScalar(95, 150, 75, 0);
		//CvScalar max = cvScalar(145, 255, 255, 0);
		CvSeq contour1 = new CvSeq(),contour2;
			double areaMax =1000,areaC =0;
		CvMemStorage storage = CvMemStorage.create();			
		img1 = cvLoadImage("Untitled.png");
		imghsv = cvCreateImage(cvGetSize(img1), 8, 3);
		imgbin = cvCreateImage(cvGetSize(img1), 8,1);
		

		cvCvtColor(img1,imghsv, CV_BGR2HSV);
		cvInRangeS(imghsv,minc,maxc, imgbin);

		cvShowImage("imgbin",imgbin);
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
		
		cvShowImage("color",img1);
		cvShowImage("color2",imgbin);
		cvWaitKey();
		cvReleaseImage(img1);
		cvReleaseImage(imghsv);
		cvReleaseImage(imgbin);
		cvReleaseMemStorage(storage);
	}
}
