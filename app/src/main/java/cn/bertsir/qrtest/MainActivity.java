package cn.bertsir.qrtest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import cn.bertsir.zbar.Qr.ScanResult;
import cn.bertsir.zbar.QrConfig;
import cn.bertsir.zbar.QrManager;
import cn.bertsir.zbar.utils.QRUtils;
import cn.bertsir.zbar.view.ScanLineView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button bt_scan;
    private static final String TAG = "MainActivity";
    private ImageView iv_qr;
    private Button bt_make;
    private EditText et_qr_content;
    private EditText et_qr_title;
    private EditText et_qr_des;
    private CheckBox cb_show_title;
    private CheckBox cd_show_des;
    private CheckBox cb_show_ding;
    private CheckBox cb_show_custom_ding;
    private CheckBox cb_show_flash;
    private CheckBox cb_show_album;
    private CheckBox cb_only_center;
    private CheckBox cb_create_add_water;
    private CheckBox cb_crop_image;
    private CheckBox cb_show_zoom;
    private CheckBox cb_auto_zoom;
    private CheckBox cb_finger_zoom;
    private CheckBox cb_double_engine;
    private CheckBox cb_loop_scan;
    private CheckBox cb_auto_light;
    private CheckBox cb_have_vibrator;
    private RadioButton rb_qrcode;
    private RadioButton rb_bcode;
    private RadioButton rb_all;
    private RadioButton rb_screen_sx;
    private RadioButton rb_screen_hx;
    private RadioButton rb_screen_auto;
    private EditText et_loop_scan_time;
    private RadioButton rb_scanline_radar;
    private RadioButton rb_scanline_grid;
    private RadioButton rb_scanline_hybrid;
    private RadioButton rb_scanline_line;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        bt_scan = (Button) findViewById(R.id.bt_scan);
        bt_scan.setOnClickListener(this);
        iv_qr = (ImageView) findViewById(R.id.iv_qr);
        iv_qr.setOnClickListener(this);
        bt_make = (Button) findViewById(R.id.bt_make);
        bt_make.setOnClickListener(this);

        iv_qr.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                String s = null;
                try {
                    s = QRUtils.getInstance().decodeQRcode(iv_qr);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(TAG, "onLongClickException: " + e.toString());
                }
                Toast.makeText(getApplicationContext(), "?????????" + s, Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        et_qr_content = (EditText) findViewById(R.id.et_qr_content);
        et_qr_title = (EditText) findViewById(R.id.et_qr_title);
        et_qr_des = (EditText) findViewById(R.id.et_qr_des);
        cb_show_title = (CheckBox) findViewById(R.id.cb_show_title);
        cd_show_des = (CheckBox) findViewById(R.id.cd_show_des);
        cb_show_ding = (CheckBox) findViewById(R.id.cb_show_ding);
        cb_show_custom_ding = (CheckBox) findViewById(R.id.cb_show_custom_ding);
        cb_show_flash = (CheckBox) findViewById(R.id.cb_show_flash);
        cb_show_album = (CheckBox) findViewById(R.id.cb_show_album);
        cb_only_center = (CheckBox) findViewById(R.id.cb_only_center);
        cb_crop_image = (CheckBox) findViewById(R.id.cb_crop_image);
        cb_create_add_water = (CheckBox) findViewById(R.id.cb_create_add_water);
        cb_show_zoom = (CheckBox) findViewById(R.id.cb_show_zoom);
        cb_auto_zoom = (CheckBox) findViewById(R.id.cb_auto_zoom);
        cb_finger_zoom = (CheckBox) findViewById(R.id.cb_finger_zoom);
        cb_double_engine = (CheckBox) findViewById(R.id.cb_double_engine);
        cb_loop_scan = (CheckBox) findViewById(R.id.cb_loop_scan);
        rb_qrcode = (RadioButton) findViewById(R.id.rb_qrcode);
        rb_bcode = (RadioButton) findViewById(R.id.rb_bcode);
        rb_all = (RadioButton) findViewById(R.id.rb_all);
        rb_screen_hx = (RadioButton) findViewById(R.id.rb_screen_hx);
        rb_screen_sx = (RadioButton) findViewById(R.id.rb_screen_sx);
        rb_screen_auto = (RadioButton) findViewById(R.id.rb_screen_auto);
        et_loop_scan_time = (EditText) findViewById(R.id.et_loop_scan_time);

        rb_qrcode.setChecked(true);
        rb_screen_sx.setChecked(true);

        rb_scanline_radar = (RadioButton) findViewById(R.id.rb_scanline_radar);
        rb_scanline_radar.setOnClickListener(this);
        rb_scanline_grid = (RadioButton) findViewById(R.id.rb_scanline_grid);
        rb_scanline_grid.setOnClickListener(this);
        rb_scanline_hybrid = (RadioButton) findViewById(R.id.rb_scanline_hybrid);
        rb_scanline_hybrid.setOnClickListener(this);
        rb_scanline_line = (RadioButton) findViewById(R.id.rb_scanline_line);
        rb_scanline_line.setOnClickListener(this);
        cb_auto_light = (CheckBox) findViewById(R.id.cb_auto_light);
        cb_have_vibrator = (CheckBox) findViewById(R.id.cb_have_vibrator);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_scan:
                start();
                break;
            case R.id.bt_make:
                Bitmap qrCode = null;
                if (cb_create_add_water.isChecked()) {
                    qrCode = QRUtils.getInstance().createQRCodeAddLogo(et_qr_content.getText().toString(),
                            BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
                } else {
                    qrCode = QRUtils.getInstance().createQRCode(et_qr_content.getText().toString());

                }
                iv_qr.setImageBitmap(qrCode);
                Toast.makeText(getApplicationContext(), "???????????????", Toast.LENGTH_LONG).show();
                break;
        }
    }

    private void start() {
        int scan_type = 0;
        int scan_view_type = 0;
        int screen = 1;
        int line_style = ScanLineView.style_radar;
        if (rb_all.isChecked()) {
            scan_type = QrConfig.TYPE_ALL;
            scan_view_type = QrConfig.SCANVIEW_TYPE_QRCODE;
        } else if (rb_qrcode.isChecked()) {
            scan_type = QrConfig.TYPE_QRCODE;
            scan_view_type = QrConfig.SCANVIEW_TYPE_QRCODE;
        } else if (rb_bcode.isChecked()) {
            scan_type = QrConfig.TYPE_BARCODE;
            scan_view_type = QrConfig.SCANVIEW_TYPE_BARCODE;
        }
        if (rb_screen_auto.isChecked()) {
            screen = QrConfig.SCREEN_SENSOR;
        } else if (rb_screen_sx.isChecked()) {
            screen = QrConfig.SCREEN_PORTRAIT;
        } else if (rb_screen_hx.isChecked()) {
            screen = QrConfig.SCREEN_LANDSCAPE;
        }

        if (rb_scanline_radar.isChecked()) {
            line_style = ScanLineView.style_radar;
        } else if (rb_scanline_grid.isChecked()) {
            line_style = ScanLineView.style_gridding;
        } else if (rb_scanline_hybrid.isChecked()) {
            line_style = ScanLineView.style_hybrid;
        } else if (rb_scanline_line.isChecked()) {
            line_style = ScanLineView.style_line;
        }


        QrConfig qrConfig = new QrConfig.Builder()
                .setDesText(et_qr_des.getText().toString())//??????????????????
                .setShowDes(cd_show_des.isChecked())//?????????????????????????????????
                .setShowLight(cb_show_flash.isChecked())//?????????????????????
                .setShowTitle(cb_show_title.isChecked())//??????Title
                .setShowAlbum(cb_show_album.isChecked())//???????????????????????????
                .setNeedCrop(cb_crop_image.isChecked())//????????????????????????????????????
                .setCornerColor(Color.parseColor("#E42E30"))//?????????????????????
                .setLineColor(Color.parseColor("#E42E30"))//?????????????????????
                .setLineSpeed(QrConfig.LINE_MEDIUM)//?????????????????????
                .setScanType(scan_type)//???????????????????????????????????????????????????????????????????????????????????????
                .setScanViewType(scan_view_type)//????????????????????????????????????????????????????????????????????????
                .setCustombarcodeformat(QrConfig.BARCODE_PDF417)//??????????????????????????????TYPE_CUSTOM????????????
                .setPlaySound(cb_show_ding.isChecked())//?????????????????????bi~?????????
                .setDingPath(cb_show_custom_ding.isChecked() ? R.raw.test : R.raw.qrcode)//???????????????(?????????????????????Ding~)
                .setIsOnlyCenter(cb_only_center.isChecked())//???????????????????????????(?????????????????????)
                .setTitleText(et_qr_title.getText().toString())//??????Tilte??????
                .setTitleBackgroudColor(Color.parseColor("#262020"))//?????????????????????
                .setTitleTextColor(Color.WHITE)//??????Title????????????
                .setShowZoom(cb_show_zoom.isChecked())//???????????????????????????
                .setAutoZoom(cb_auto_zoom.isChecked())//????????????????????????(?????????????????????????????????)
                .setFingerZoom(cb_finger_zoom.isChecked())//????????????????????????
                .setDoubleEngine(cb_double_engine.isChecked())//???????????????????????????(???????????????????????????????????????????????????????????????????????????)
                .setScreenOrientation(screen)//??????????????????
                .setOpenAlbumText("????????????????????????")//?????????????????????
                .setLooperScan(cb_loop_scan.isChecked())//???????????????????????????
                .setLooperWaitTime(Integer.parseInt(et_loop_scan_time.getText().toString()) * 1000)//????????????????????????
                .setScanLineStyle(line_style)//???????????????
                .setAutoLight(cb_auto_light.isChecked())//????????????
                .setShowVibrator(cb_have_vibrator.isChecked())//??????????????????
                .create();
        QrManager.getInstance().init(qrConfig).startScan(MainActivity.this, new QrManager.OnScanResultCallback() {
            @Override
            public void onScanSuccess(ScanResult result) {
                Log.e(TAG, "onScanSuccess: " + result);
                Toast.makeText(getApplicationContext(), "?????????" + result.getContent()
                        + "  ?????????" + result.getType(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
