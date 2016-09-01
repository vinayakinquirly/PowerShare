package com.inquirly.powershare.fragment;

import java.io.File;
import android.net.Uri;
import java.util.Random;
import android.util.Log;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.os.Environment;
import android.view.ViewGroup;
import android.graphics.Bitmap;
import android.widget.HorizontalScrollView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.ImageView;
import com.inquirly.powershare.R;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.graphics.BitmapFactory;
import android.support.v4.app.Fragment;
import com.inquirly.powershare.utils.AnimUtils;
import com.inquirly.powershare.utils.CommonUtil;
import com.inquirly.powershare.activity.MainActivity;
import com.inquirly.powershare.activity.InpireMeActivity;

public class PowerPostFragment extends Fragment implements View.OnClickListener{

    private View view;
    private File file;
    private String imagePath, fileName;
    private boolean isAddPhotoClicked;
    private int REQUEST_CODE_TAKE_PICTURE = 10;
    private int REQUEST_CODE_LOAD_FROM_GALLERY = 11;
    private HorizontalScrollView post_horizontal_view;
    private static final String TAG = "PowerPostFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_power_post, container, false);

        ImageView addImages = (ImageView) view.findViewById(R.id.add_images);
        ImageView addVideo = (ImageView) view.findViewById(R.id.addVideoPost);
        ImageView moveLeft = (ImageView) view.findViewById(R.id.move_channel_left);
        TableRow table_inpireMe = (TableRow) view.findViewById(R.id.table_inpireMe);
        ImageView moveRight = (ImageView) view.findViewById(R.id.move_channel_right);
        TextView loadPicCancelBtn = (TextView) view.findViewById(R.id.txt_post_load_picture_cancel);
        TextView loadFromGalleryBtn = (TextView) view.findViewById(R.id.txt_post_load_picture_gallery);
        TextView takePicFromGalleryBtn = (TextView) view.findViewById(R.id.txt_post_load_picture_camera);
        post_horizontal_view = (HorizontalScrollView)view.findViewById(R.id.post_horizontal_view);
        addVideo.setOnClickListener(this);
        table_inpireMe.setOnClickListener(this);
        moveLeft.setOnClickListener(this);
        moveRight.setOnClickListener(this);

        addImages.setOnClickListener(new OptionHandler());
        loadFromGalleryBtn.setOnClickListener(new OptionHandler());
        takePicFromGalleryBtn.setOnClickListener(new OptionHandler());
        loadPicCancelBtn.setOnClickListener(new OptionHandler());
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_OK) {
            if (requestCode == REQUEST_CODE_LOAD_FROM_GALLERY) {
                Uri uriRaw = data.getData();
                if (uriRaw != null) {
                    ImageView imageView = (ImageView) view.findViewById(R.id.image_selected);
                    imageView.setVisibility(View.VISIBLE);
                    imagePath = CommonUtil.getPath(getActivity(), uriRaw);
                    if (imagePath != null) {
                        Uri uri = Uri.parse(imagePath);
                        if (uri != null) {
                            Bitmap bitMap2 = decodeSampledBitmapFromFile(uri.toString(), 500, 200);
                            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                            imageView.setImageBitmap(bitMap2);
                        }
                    }
                }
            } else if (requestCode == REQUEST_CODE_TAKE_PICTURE) {
                ImageView imageView = (ImageView) view.findViewById(R.id.image_selected);
                imageView.setVisibility(View.VISIBLE);
                File file = new File(Environment.getExternalStorageDirectory() + File.separator + fileName);
                Log.d(TAG, "filePath: " + Uri.fromFile(file));
                Bitmap bitMap = decodeSampledBitmapFromFile(file.getAbsolutePath(), 800, 300);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setImageBitmap(bitMap);
                imagePath = file.getPath();
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.table_inpireMe:
                Intent intent = new Intent(getActivity(), InpireMeActivity.class);
                startActivity(intent);
                break;

            case R.id.addVideoPost:
                ((MainActivity)getActivity()).navigateTo(new AddVideoFragment(),null,null);
                break;

            case R.id.move_channel_left:
                post_horizontal_view.scrollTo(-500,0);
                break;

            case R.id.move_channel_right:
                post_horizontal_view.scrollTo(500,0);
                break;
        }
    }

    private class OptionHandler implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            int id = view.getId();
            Log.i(TAG,"check options ID ===" + id);
            switch (id) {
                case R.id.add_images:
                    handleAddPhotoClick();
                    break;

                case R.id.txt_post_load_picture_gallery:
                    handleAddPhotoClick();
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto, REQUEST_CODE_LOAD_FROM_GALLERY);
                    break;

                case R.id.txt_post_load_picture_camera:
                    handleAddPhotoClick();
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    fileName = "image" + new Random().nextInt() + ".jpg";
                    file = new File(Environment.getExternalStorageDirectory() + File.separator + fileName);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                    startActivityForResult(intent, REQUEST_CODE_TAKE_PICTURE);
                    break;

                case R.id.txt_post_load_picture_remove:
                    handleAddPhotoClick();
                    break;

                case R.id.txt_post_load_picture_cancel:
                    handleAddPhotoClick();
                    break;
            }
        }
    }

    private void handleAddPhotoClick() {
        Log.i(TAG,"entered in handleAddPhotoClicked");
        LinearLayout loadPhotoOptionsBase = (LinearLayout) view.findViewById(R.id.linear_post_load_photo_options_base);
        if (!isAddPhotoClicked) {
            AnimUtils.slideUpFromBottom(getActivity(), loadPhotoOptionsBase);
            isAddPhotoClicked = true;
        } else {
            AnimUtils.slideDownFromTop(getActivity(), loadPhotoOptionsBase);
            isAddPhotoClicked = false;
        }
    }

    public static Bitmap decodeSampledBitmapFromFile(String path, int reqWidth, int reqHeight) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        final int height = options.outHeight;
        final int width = options.outWidth;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        int inSampleSize = 1;
        if (height > reqHeight) {
            inSampleSize = Math.round((float) height / (float) reqHeight);
        }
        int expectedWidth = width / inSampleSize;
        if (expectedWidth > reqWidth) {
            inSampleSize = Math.round((float) width / (float) reqWidth);
        }
        options.inSampleSize = inSampleSize;
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(path, options);
    }
}
