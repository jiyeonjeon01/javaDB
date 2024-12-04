package publicDataTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import publicDataTest.controller.LandPriceRegisterManager;
import publicDataTest.model.LandPriceVO;
import publicDataTest.view.LANDPRICE_CHOICE;
import publicDataTest.view.MenuViewer;

public class PublicDataAPITest {
	public static Scanner sc = new Scanner(System.in);
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		
		int no;
		boolean exitFlag = false; 
		LandPriceRegisterManager lrm = new LandPriceRegisterManager();
		while (!exitFlag) {
			try {
				MenuViewer.mainMenuView();
				no = Integer.parseInt(sc.nextLine()); 
				switch (no) {
				case LANDPRICE_CHOICE.LOAD:
					ArrayList<LandPriceVO> landPriceList = apiDataLoad();
					printLandPriceList(landPriceList);
					break;
				case LANDPRICE_CHOICE.INSERT:
					lrm.insertManager();
					break;
				case LANDPRICE_CHOICE.SELECT:
					lrm.selectManager();
					break;
				case LANDPRICE_CHOICE.DELETE:
					lrm.deleteManager();
					break;
				case LANDPRICE_CHOICE.UPDATE:
					lrm.updateManager();
					break;	
				case LANDPRICE_CHOICE.EXIT:
					System.out.println("프로그램을 종료합니다.");
					exitFlag = true; 
					break; 
				default:
					System.out.println("해당 메뉴 번호만 입력하세요.");
				}
			} catch (Exception e) {
				System.out.println("\n입력에 오류가 있습니다.\n프로그램을 다시 시작하세요.");
			}
			System.out.println("The End");
		}//end of file
		
    }//end of main
	
	public static void printLandPriceList(ArrayList<LandPriceVO> landPriceList) {
		for (LandPriceVO data : landPriceList) {
			System.out.println(data);
		}
	}
	
	public static ArrayList<LandPriceVO> apiDataLoad() {
		//1. url 주소를 적는다.
        StringBuilder urlBuilder = null;
        // 객체를 가져오는데 디비에서 가져오는 것이 아니라 공공데이터서버에서 네트워크로 가져오는 ㄴ것 
        ArrayList<LandPriceVO> landPriceList = new ArrayList<LandPriceVO>();

        try {
            urlBuilder = new StringBuilder("https://apis.data.go.kr/1613000/BusSttnInfoInqireService/getSttnNoList");
            urlBuilder.append("?"+URLEncoder.encode("serviceKey","UTF-8")+
              "=tYEovcZW7GnVo45u4oZAj%2BforRQyuV5PfzFAqRe%2BM7cDZGCCd90JpryiDWeHt2W6JCNmu79AiT2GKYP7v71nTg%3D%3D");
            urlBuilder.append("&"+URLEncoder.encode("pageNo","UTF-8")+"="+URLEncoder.encode("1","UTF-8"));
            urlBuilder.append("&"+URLEncoder.encode("numOfRows","UTF-8")+"="+URLEncoder.encode("10","UTF-8"));
            urlBuilder.append("&"+URLEncoder.encode("_type","UTF-8")+"="+URLEncoder.encode("xml","UTF-8"));
            urlBuilder.append("&"+URLEncoder.encode("cityCode","UTF-8")+"="+URLEncoder.encode("25","UTF-8"));
            urlBuilder.append("&"+URLEncoder.encode("nodeNm","UTF-8")+"="+URLEncoder.encode("전통시장","UTF-8"));
            urlBuilder.append("&"+URLEncoder.encode("nodeNo","UTF-8")+"="+URLEncoder.encode("44810","UTF-8"));

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } 
        
        // 2. 공공데이터 connection 객체 생성(method 방식, Content-type) 요청성공유무(conn.getResponseCode() 확인)
        URL url = null;
        HttpURLConnection conn = null;
        try {
        	// con = DriverManager.getConnection(url, id, pw);
        	url = new URL(urlBuilder.toString());
        	conn = (HttpURLConnection)url.openConnection();
        	conn.setRequestMethod("GET");
        	conn.setRequestProperty("Content-type", "application/json");
        	
        	int responseCode = conn.getResponseCode();
        	System.out.println("서버에서 요청에 대한 ResponseCode: " + responseCode);
        	
        } catch(MalformedURLException e) {
        	System.out.println(e.toString());
        } catch(IOException e) {
        	System.out.println(e.toString());
        }
        
        // 서버에서 응답을 줄 때는 3가지 방식
        // (start line:상태코드, message header:서버상태정보, message body:html코드,데이터코드)
        // 3. 요청전송, 응답처리(200~300:정산, 403:권한설정, 404:페이지찾기힘듬, 405: 인증안됨, 500:서버에서예외처리)
        BufferedReader br = null;
        try {
        	int responseCode = conn.getResponseCode();
        	// 서버요청 성공코드가 오면 message body 읽기 위해서 bufferedreader를 설정하고
        	// 서버요청 실패코드가 오면 message body 에러메세지를 읽기 위해서 준비한다. 
        	if(responseCode >= 200 && responseCode <= 300) {
        		InputStream is = conn.getInputStream();
        		InputStreamReader isr = new InputStreamReader(is);
        		br = new BufferedReader(isr);
        	} else {
        		InputStream is = conn.getErrorStream();
        		InputStreamReader isr = new InputStreamReader(is);
        		br = new BufferedReader(isr);
        	}
        	// message body 데이터를 가져오자
        	Document doc = parseXML(conn.getInputStream());
        	// doc 객체속에서 item 태그명을 찾는다. (태그객체 = 노드)
        	NodeList parentNode = doc.getElementsByTagName("item");
        	
        	// parentNode 객체에서 자식 객체를 가져오기
        	for(int i=0; i<parentNode.getLength(); i++) {
        		// 자식객체가져오기
        		Node item = parentNode.item(i);
        		LandPriceVO landPriceVO = new LandPriceVO();
        		// 자식의 이름을 검색해서 거기에 해당되는 ㅔ데이터를 가져와 모델 LandPriceVO입력한다
        		// Node에는 <div>속성 context(내용, 자식노드
        		for (Node node = item.getFirstChild(); node != null; node = node.getNextSibling()) {
        			switch(node.getNodeName()) {
        			case "gpslati": 
        				landPriceVO.setGpslati(Double.parseDouble(node.getTextContent()));
        				break;
        			case "gpslong":
        				landPriceVO.setGpslong(Double.parseDouble(node.getTextContent()));
        				break;
        			case "nodeid":
        				landPriceVO.setNodeid(node.getTextContent());
        				break;
        			case "nodenm":
        				landPriceVO.setNodenm(node.getTextContent());
        				break;
        			case "nodeno":
        				landPriceVO.setNodeno(Integer.parseInt(node.getTextContent()));
        				break;
        			}
        		}
        		landPriceList.add(landPriceVO);
        	}
        	for(LandPriceVO data : landPriceList) {
        		System.out.println(data);
        	}
        } catch (IOException e) {
    		System.out.println(e.toString());
    	} finally {
    		if(br != null) {
    			try {
    				br.close();
    			} catch(IOException e) {
    				e.printStackTrace();
    			}
    		}
    		
    		if(conn != null) {
    			conn.disconnect();
    		}
    			
    	}
        return landPriceList;
		
	}

	// 입력에 해당되는 객체를 주면 Document로 변환해서 리턴한다 
    public static Document parseXML(InputStream stream) {
        DocumentBuilderFactory objDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder objDocumentBuilder = null;
        Document doc = null;
        try {
            objDocumentBuilder = objDocumentBuilderFactory.newDocumentBuilder();
            doc = objDocumentBuilder.parse(stream);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) { // Simple API for XML e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doc;
    }
}
