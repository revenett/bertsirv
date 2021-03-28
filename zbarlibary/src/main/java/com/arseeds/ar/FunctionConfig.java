package com.arseeds.ar;

import java.io.Serializable;

/**
 * author：luck
 * project：PictureSelector
 * package：com.luck.picture.lib.model
 * email：893855882@qq.com
 * data：17/1/16
 */
public class FunctionConfig implements Serializable {
    public static final int TYPE_IMAGE = 1;
    public static final int TYPE_VIDEO = 2;

    // 裁剪模式
    public static final int CROP_MODEL_DEFAULT = 0;
    public static final int CROP_MODEL_1_1 = 11;
    public static final int CROP_MODEL_3_4 = 34;
    public static final int CROP_MODEL_3_2 = 32;
    public static final int CROP_MODEL_16_9 = 169;

    public final static int MODE_MULTIPLE = 1;// 多选
    public final static int MODE_SINGLE = 2;// 单选

    public static final int ORDINARY = 0;// 普通 低质量
    public static final int HIGH = 1;// 清晰

    public static final int COPY_WIDTH = 0;
    public static final int COPY_HEIGHT = 0;
    public final static int REQUEST_IMAGE = 88;
    public final static int REQUEST_CAMERA = 99;
    public final static int REQUEST_PREVIEW = 100;
    public static final int READ_EXTERNAL_STORAGE = 0x01;
    public static final int CAMERA = 0x02;
    public static final int CAMERA_RESULT = 0x06;
    public static final int CLOSE_FLAG = 2773;// 关闭activity 标识
    public static final int CLOSE_SINGE_CROP_FLAG = 2018;// 关闭activity 标识
    public static final int UPDATE_FLAG = 2774;// 预览界面更新选中数据 标识
    public static final int CROP_FLAG = 2775;// 裁剪数据 标识
    public static final int CLOSE_PREVIEW_FLAG = 2776;// 只关闭预览界面activity 标识
    public static final int CLOSE_SINE_CROP_FLAG = 2777;// 关闭单图裁剪页面 标识

    public static final int SELECT_MAX_NUM = 9;
    public static final int MAX_COMPRESS_SIZE = 102400;
    public static final String FUNCTION_TAKE = "function_take";
    public static final String TAKE_PHOTO_SUCCESS = "takePhotoSuccess";
    public static final String EXTRA_THIS_CONFIG = "function_options";
    public static final String EXTRA_IS_TOP_ACTIVITY = "isTopActivity";
    public static final String EXTRA_BOTTOM_PREVIEW = "bottom_preview";
    public static final String EXTRA_POSITION = "position";
    public static final String EXTRA_PREVIEW_LIST = "previewList";
    public static final String DIRECTORY_PATH = "directory_path";
    public static final String EXTRA_PREVIEW_SELECT_LIST = "previewSelectList";

    public final static String FOLDER_NAME = "folderName";
    public final static String BUNDLE_CAMERA_PATH = "CameraPath";
    public final static String EXTRA_RESULT = "select_result";

}
