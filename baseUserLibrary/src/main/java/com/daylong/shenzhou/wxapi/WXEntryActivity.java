package com.daylong.arcx.wxapi;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.daylong.userlibrary.mrg.WXMrg;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import net.daylong.baselibrary.app.Constant;
import net.daylong.baselibrary.utils.ui.act.BaseActivity;

/**
 * @author 陈振荣
 * @Company 广州大龙网络科技有限公司
 * @Description 分享
 * @Date 2019/10/10
 * @Version 1.0
 */
public class WXEntryActivity extends BaseActivity implements IWXAPIEventHandler {

    private IWXAPI api;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        api = WXAPIFactory.createWXAPI(this, Constant.WX_APP_ID, true);
        api.registerApp(Constant.WX_APP_ID);
        //不写没有回调
        api.handleIntent(getIntent(), this);
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        //调用handleIntent，接收到数据后，会自动执行onResp方法，我们在onResp内获取需要的code
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }


    @Override
    public void onResp(BaseResp resp) {
        //微信登陆，接收返回的code
        if (resp.getType() == 1) {
            SendAuth.Resp res = (SendAuth.Resp) resp;
            String code = res.code;
            if (!TextUtils.isEmpty(code)) {
                WXMrg.getInstance().wxLoginSuc(code);
            }
        }
        finish();

    }
}
