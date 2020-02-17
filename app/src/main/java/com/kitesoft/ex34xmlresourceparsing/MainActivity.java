package com.kitesoft.ex34xmlresourceparsing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv= findViewById(R.id.tv);
    }

    public void clickBtn(View v){
        //Resource폴더에 있는 xml문서를 읽어서
        //분석(parse)하는 작업 수행

        //res폴더 창고관리자 객체 소환
        Resources res= getResources();

        //창고관리자로부터 파서객체 얻어오기.
        XmlResourceParser xrp= res.getXml(R.xml.movies);

        //파서가 읽어들인 글씨를 누적하여 저장하기 위한 StringBuffer객체 생성
        StringBuffer buffer= new StringBuffer();


        try {

            xrp.next(); // res폴더의 xml문서를 읽어서 분석해주는 파서에게 다음 eventType단위로 커서를 이동하도록
            int eventType= xrp.getEventType();

            String name; //태그이름
            String text; //내용글씨


            while(eventType!=XmlResourceParser.END_DOCUMENT){

                //eventType의 종류 5개
                switch (eventType){
                    case XmlResourceParser.START_DOCUMENT:
                        buffer.append("xml 파싱 시작합니다..\n\n");
                        break;

                    case XmlResourceParser.START_TAG:
                        name= xrp.getName(); //태그문의 이름얻어오기
                        if( name.equals("item")  ){

                        }else if( name.equals("no")){
                            buffer.append("번호:");
                        }else if( name.equals("title")){
                            buffer.append("제목:");
                        }else if( name.equals("genre")){
                            buffer.append("장르:");
                        }

                        break;

                    case XmlResourceParser.TEXT:
                        text= xrp.getText();
                        buffer.append(text);
                        break;

                    case XmlResourceParser.END_TAG:
                        name= xrp.getName(); //태그문의 이름얻어오기
                        if( name.equals("item")  ){
                            buffer.append("\n");
                        }else if( name.equals("no")){
                            buffer.append("\n");
                        }else if( name.equals("title")){
                            buffer.append("\n");
                        }else if( name.equals("genre")){
                            buffer.append("\n");
                        }
                        break;
                }//switch

                eventType= xrp.next();
            }//while....

            buffer.append("파싱 종료....\n");


            tv.setText(buffer.toString());

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }//clickBtn method...

}//MainActivity class...
