package com.baway.jdproject.util;

import android.app.Activity;
import android.widget.Toast;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

/**
 * Created by 郑文杰 on 2017/11/10.
 */

public class ShareUtil {
    private Activity ac;

    public ShareUtil(Activity ac) {
        this.ac = ac;
    }

    public void ShareWeb(int thumb_img,String url,String str,boolean flag){
        UMImage thumb = new UMImage(ac,thumb_img);
        UMWeb web = new UMWeb(url);
        web.setThumb(thumb);
        web.setDescription(str);
        web.setTitle("京东分享");
        new ShareAction(ac).withMedia(web).setPlatform(flag?SHARE_MEDIA.QZONE:SHARE_MEDIA.QQ).setCallback(shareListener).share();
    }

    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {
            Toast.makeText(ac,"分享开始",Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(ac,"成功了",Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {

            Toast.makeText(ac,"失败"+t.getMessage(),Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(ac,"取消了",Toast.LENGTH_LONG).show();

        }
    };
}
