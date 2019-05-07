package com.example.administrator.stocktaking;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.StringBuilderPrinter;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.stocktaking.Tool.FileHelper;
import com.example.administrator.stocktaking.adapter.RecyclerViewAdapter_AssetcodeList;
import com.example.administrator.stocktaking.adapter.RecyclerViewAdapter_MarkList;
import com.example.administrator.stocktaking.adapter.RecyclerViewAdapter_PeopleList;
import com.example.administrator.stocktaking.bean.ListBean;
import com.example.administrator.stocktaking.bean.ListBean_Assetcode;
import com.google.zxing.ResultPoint;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

import java.util.List;

public class ScanQRCodeActivity extends AppCompatActivity  {


    private CaptureManager capture;
    private DecoratedBarcodeView bv_barcode;
    private RecyclerView  recyclerView1;
    private RecyclerView  recyclerView2;
    private RecyclerViewAdapter_AssetcodeList recyclerViewAdapter_assetcodeList;
    private RecyclerViewAdapter_MarkList recyclerViewAdapter_MarkList;
    private List<ListBean_Assetcode> listAssetcode;
    private List<ListBean> listMarks;

    private Button opencloseButton;
    private TextView qrcodresult;
    private String FilePath;
    private  boolean IsOpen=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qrcode);
      //  qrcodresult= (TextView) findViewById(R.id.qrcodresult);
        bv_barcode = (DecoratedBarcodeView) findViewById(R.id.bv_barcode);
        FilePath = getIntent().getStringExtra("filepath");
        capture = new CaptureManager(this, bv_barcode);
        capture.initializeFromIntent(getIntent(), savedInstanceState);
        opencloseButton= (Button) findViewById(R.id.opencloseButton);

        //bv_barcode.decodeSingle(barcodeCallback);
        bv_barcode.decodeContinuous(barcodeCallback);
       // capture.decode();
        init();
    }

   void init(){
       opencloseButton.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v) {
               IsOpen=!IsOpen;
               bv_barcode.setVisibility(IsOpen?View.VISIBLE: View.GONE);
              // recyclerView1.();
           }
       });
       listAssetcode=new ArrayList<>();
       listMarks=new ArrayList<>();

       listAssetcode = new ArrayList<>();
       if ( new File(FileHelper.MarksListdaddress).exists())
       {
           List<String>  codeList= FileHelper.ReadAlines(FileHelper.Recordaddress+FilePath);
           for (int i = 0; i <codeList.size() ; i++) {
               String[] Cell=codeList.get(i).split("\t");
               listAssetcode.add(new ListBean_Assetcode(Cell[0],Cell[1]));
           }
       }


       recyclerView1=(RecyclerView)findViewById(R.id.recyclerViewCodeList);
       LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(ScanQRCodeActivity.this);
       linearLayoutManager3.setOrientation(LinearLayoutManager.VERTICAL);
       recyclerView1.setLayoutManager(linearLayoutManager3);
       recyclerViewAdapter_assetcodeList = new RecyclerViewAdapter_AssetcodeList(listAssetcode,ScanQRCodeActivity.this);
       recyclerView1.setAdapter(recyclerViewAdapter_assetcodeList);


       listMarks = new ArrayList<>();
       if ( new File(FileHelper.MarksListdaddress).exists())
       {
           List<String>  marksList= FileHelper.ReadAlines(FileHelper.MarksListdaddress);
           for (int i = 0; i <marksList.size() ; i++) {
               listMarks.add(new ListBean(i,marksList.get(i)));
           }
       }

       recyclerView2  =(RecyclerView)findViewById(R.id.recyclerViewMarkList);
       recyclerView2.setLayoutManager(new GridLayoutManager(this, 5));

       recyclerViewAdapter_MarkList = new RecyclerViewAdapter_MarkList(listMarks,ScanQRCodeActivity.this);
       recyclerView2.setAdapter(recyclerViewAdapter_MarkList);

       recyclerViewAdapter_MarkList.setOnItemClickListener(new RecyclerViewAdapter_MarkList.OnItemClickListener() {
           @Override
           public void OnItemClick(View view, RecyclerViewAdapter_MarkList.ViewHolder holder, int position) {
               recyclerViewAdapter_assetcodeList.setMark(listMarks.get(position).getName());
           }
       });

   }


    @Override
    protected void onResume() {
        super.onResume();
        capture.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        capture.onPause();
    }

    @Override
    protected void onDestroy() {

        StringBuilder sb=new StringBuilder();

        for (int i = 0; i < listAssetcode.size() ; i++) {
            sb.append(listAssetcode.get(i).getCode()+"\t"+listAssetcode.get(i).getMark()+"\r\n" );
        }

        FileHelper.writeTxtToFile(sb.toString(),FileHelper.Recordaddress,FilePath,false,false);

        super.onDestroy();
        capture.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        capture.onSaveInstanceState(outState);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        capture.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return bv_barcode.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Log.d(getClass().getName(), "Cancelled");
                Toast.makeText(this, "扫描结果为空", Toast.LENGTH_LONG).show();
            } else {
                Log.d(getClass().getName(), "Scanned: " + result.getContents());
                Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
            }
        }
    }

    private BarcodeCallback barcodeCallback = new BarcodeCallback() {
        @Override
        public void barcodeResult(BarcodeResult result) {
            bv_barcode.pause();
            if (result != null){
                Log.e(getClass().getName(), "获取到的扫描结果是：" + result.getText());

             // qrcodresult.setText(qrcodresult.getText()+result.getText()+"\r\n");

                listAssetcode.add( new ListBean_Assetcode(result.getText()));
                recyclerView1.setAdapter(recyclerViewAdapter_assetcodeList);
                //可以对result进行一些判断，比如说扫描结果不符合就再进行一次扫描
                if (result.getText().contains("符合我的结果")){
                    Toast.makeText(ScanQRCodeActivity.this,"jinlaile",Toast.LENGTH_LONG).show();
                    //符合的可以不在扫描了，当然你想继续扫描也是可以的
                } else {
                    bv_barcode.resume();
                    capture.onResume();

                  //  SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                  //  String str_time = sdf.format( new Date().toLocaleString());
                    //FileHelper.writeTxtToFile(result.getText()+"\r\n",FileHelper.GetDataBasePath(),FileName);
                    FileHelper.writeTxtToFile(result.getText(),FileHelper.Recordaddress,"Record.txt",true,true);
                    Toast.makeText(ScanQRCodeActivity.this,"resume",Toast.LENGTH_LONG).show();

                }
            }
        }

        @Override
        public void possibleResultPoints(List<ResultPoint> resultPoints) {

        }


    };

}
