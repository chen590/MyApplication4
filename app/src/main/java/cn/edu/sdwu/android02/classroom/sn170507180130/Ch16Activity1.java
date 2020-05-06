package cn.edu.sdwu.android02.classroom.sn170507180130;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;

import java.util.Arrays;
import java.util.jar.Manifest;

public class Ch16Activity1 extends AppCompatActivity {
private TextureView textureView;
    private SurfaceTexture surfaceTexture;
    private CameraDevice.StateCallback stateCallback;
    private CameraDevice cameraDevice;
    private CaptureRequest.Builder captureRequestBuilder;
    private CaptureRequest captureRequest;
    private CameraCaptureSession cameraCaptureSession;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int result = checkSelfPermission(Manifest.permission.CAMERA);
            if (result == PackageManager.PERMISSION_GRANTED) {
              setCameraLayout();
            } else {
                requestPermissions(new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE}, 104);
            }
        }
        stateCallback=new CameraDevice.StateCallback() {
            @Override
            public void onOpened(@NonNull CameraDevice cameraDevice) {
                Ch16Activity1.this.cameraDevice=cameraDevice;
                Surface surface=new Surface(surfaceTexture);
                //
                try{
                    final CaptureRequest.Builder captureRequestBuilder=cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
                    captureRequestBuilder.addTarget(surface);

                    cameraDevice.createCaptureSession(Arrays.asList(surface), new CameraCaptureSession.StateCallback() {
                        @Override
                        public void onConfigured(@NonNull CameraCaptureSession cameraCaptureSession) {
                        try{
                            previewRequest=captureRequestBuilder.build();
                            cameraCaptureSession.setRepeatingRequest(previewRequest,null,null);
                        }catch(Exception e){
                            Log.e(Ch16Activity1.class.toString(),e.toString());
                        }


                        }

                        @Override
                        public void onConfigureFailed(@NonNull CameraCaptureSession cameraCaptureSession) {

                        }
                    },null);
                }catch(Exception e){

                }

            }

            @Override
            public void onDisconnected(@NonNull CameraDevice cameraDevice) {
                Ch16Activity1.this.cameraDevice=null;


            @Override
            public void onError(@NonNull CameraDevice cameraDevice, @IntDef(value = {CameraDevice.StateCallback.ERROR_CAMERA_IN_USE, CameraDevice.StateCallback.ERROR_MAX_CAMERAS_IN_USE, CameraDevice.StateCallback.ERROR_CAMERA_DISABLED, CameraDevice.StateCallback.ERROR_CAMERA_DEVICE, CameraDevice.StateCallback.ERROR_CAMERA_SERVICE}) int i) {

            }
        }
    }
    private void openCamera(int width,int height){
        CameraManager cameraManager=(CameraManager)getSystemService(CAMERA_SERVICE);
        try{
            cameraManager.openCamera("0",stateCallback,null);
        }catch(Exception e){
            Log.e(Ch16Activity1.class.toString(),e.toString());
        }

    }
        private void setCameraLayout(){
        setContentView(R.layout.layout_ch16_1);
        textureView=(TextureView)findViewById(R.id.ch16_tv);
        textureView.setSurfaceTextureListener(new TextureView.SurfaceTextureListener(){
        }
    });


    public void call(View view){
        //
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
            //
            int result = checkSelfPermission(Manifest.permission.CALL_PHONE);
            if(result== PackageManager.PERMISSION_GRANTED){
                callphone();
            }else{
                requestPermissions(new String[]{Manifest.permission.CALL_PHONE},101);
            }

        }
    }
    public void chgOri(View view){
        //
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); //H
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//S
    }

    public void sms(View view){
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
           int result= checkSelfPermission(Manifest.permission.SEND_SMS);
            if(result==PackageManager.PERMISSION_GRANTED){
                sendSms();
            }else{
                requestPermissions(new String[]{Manifest.permission.SEND_SMS},102);
            }
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==101){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                callphone();
            }
        }
        if(requestCode==102){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                sendSms();
            }
        }
        if(requestCode==104){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                setCameraLayout();
            }
        }
    }
    private void sendSms(){
        //
        SmsManager smsManager=SmsManager.getDefault();
        smsManager.sendTextMessage("13305311234","13111111111","short message test ",null,null);
    }
    private void callphone(){
        Intent intent=new Intent(Intent.ACTION_CALL, Uri.parse("tel://1395678999"));
        startActivity(intent);
    }
}
