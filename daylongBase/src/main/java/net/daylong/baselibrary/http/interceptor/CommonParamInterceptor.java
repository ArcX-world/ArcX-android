package net.daylong.baselibrary.http.interceptor;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.http.Body;

/**
 * 参数拦截器
 */
public class CommonParamInterceptor implements Interceptor {
    private static final String REQUEST_METHOD_POST = "POST";

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        if (request.method() == REQUEST_METHOD_POST) {
            request = addPostBaseParams(request);
        } else {
            request = addGetBaseParams(request);

        }


        return chain.proceed(request);


    }

    /**
     * 添加GET方法基础参数
     *
     * @param request
     * @return
     */
    private Request addGetBaseParams(Request request) {


        HttpUrl.Builder builder = request.url().newBuilder();
        //builder.addQueryParameter("channelCode", BaseApplication.getInstance().getChannel());//渠道
//        builder.addQueryParameter("mobilePlatform", "1");//用户ID
        //if (UserCache.getInstance().getAccToken() != null) {
        //    builder.addQueryParameter("accessToken", UserCache.getInstance().getAccToken());
        //
        //}

        //builder.addQueryParameter("versionCode", String.valueOf(SystemUtil.getVersionCode()));
        //builder.addQueryParameter("language", MultiLanguageUtils.getCurrentLanguage());
        //builder.addQueryParameter("versionChannel", BaseApplication.isCN());//1-中国，2-海外
        //builder.addQueryParameter("devId", DeviceIdUtil.getDeviceId());//1-中国，2-海外
        //String productId = AppSharedPreferencesManage.getInstance().getProductId();


        //if (!TextUtils.isEmpty(productId)) {
        //    builder.addQueryParameter("productId", productId);//设备ID
        //
        ////}
        //HttpUrl newUrl =
        //        builder.addQueryParameter("version", SystemUtil.getVersionName())
        //                .build();
        return request.newBuilder()
                //.url(newUrl)
                .build();
    }

    /**
     * 添加POST方法基础参数
     *
     * @param request
     * @return
     */
    private Request addPostBaseParams(Request request) {

        boolean isAddProductId = false;
        /**
         * request.body() instanceof FormBody 为true的条件为：
         * 在ApiService 中将POST请求中设置
         * 1，请求参数注解为@FieldMap
         * 2，方法注解为@FormUrlEncoded
         */
        RequestBody body = request.body();

        if (body instanceof Body) {
            Body formBody = (Body) request.body();
            FormBody.Builder builder = new FormBody.Builder();

            //for (int i = 0; i < formBody.size(); i++) {
            //    //@FieldMap 注解 Map元素中 key 与 value 皆不能为null,否则会出现NullPointerException
            //    if (formBody.value(i) != null) {
            //
            //        String name = formBody.name(i);
            //        builder.add(name, formBody.value(i));
            //
            //        if ("productId".equals(name)) {
            //            isAddProductId = true;
            //        }
            //
            //    }
            //
            //
            //}
//            添加版本号
//            builder.add("version", SystemUtil.getVersionName());
////            平台ID
//            builder.add("language", MultiLanguageUtils.getCurrentLanguage());
//
//            builder.add("channelCode", BaseApplication.getInstance().getChannel());//渠道
//            builder.add("mobilePlatform", "1");
//            builder.add("versionChannel", BaseApplication.isCN());//1-中国，2-海外
//            builder.add("versionCode", String.valueOf(SystemUtil.getVersionCode()));
//            builder.add("devId", DeviceIdUtil.getDeviceId());//1-中国，2-海外
//            //获取游戏设备ID
//
//
//
//
//            String productId = AppSharedPreferencesManage.getInstance().getProductId();
//            if (!TextUtils.isEmpty(productId) && !isAddProductId) {
//                builder.add("productId", productId);//
//
//            }
//            if (UserCache.getInstance().getAccToken() != null) {
//                builder.add("versionCode", UserCache.getInstance().getAccToken());//用户ID
//
//            }
//            formBody = builder.build();
//            request = request.newBuilder().post(formBody).build();
        }
        return request;
    }


}
