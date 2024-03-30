package net.daylong.baselibrary.http.interceptor;


import net.daylong.baselibrary.utils.MyLogUtil;
import java.io.IOException;
import java.nio.charset.Charset;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;


public class HeadInterceptorUtil {
    /**
     * 头部拦截器
     *
     * @return
     */
    public static Interceptor getHeadInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request.Builder builder = chain.request().newBuilder();
                Request build = builder.build();
                return chain.proceed(build);
            }
        }


                ;
    }


    /**
     * post 请求参数获取
     */
    private static String bodyToString(final Request request) {
        final Request copy = request.newBuilder().build();
        final Buffer buffer = new Buffer();
        try {
            copy.body().writeTo(buffer);
        } catch (IOException e) {
            return "something error,when show requestBody";
        }
        return buffer.readUtf8();
    }

    /**
     * 字符串转unicode
     *
     * @param str
     * @return
     */
    public static String stringToUnicode(String str) {
        StringBuffer sb = new StringBuffer();
        char[] c = str.toCharArray();
        for (int i = 0; i < c.length; i++) {
            sb.append("\\u" + Integer.toHexString(c[i]));
        }
        return sb.toString();
    }

    /**
     * 日志拦截器
     *
     * @return
     */
    public static Interceptor getLogInterceptor() {
        final Charset UTF8 = Charset.forName("UTF-8");
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Response response = chain.proceed(request);
                RequestBody requestBody = request.body();
                ResponseBody responseBody = response.body();
                String responseBodyString = responseBody.string();
                String requestMessage;
                requestMessage = request.method() + ' ' + request.url();
                if (requestBody != null) {
                    Buffer buffer = new Buffer();
                    requestBody.writeTo(buffer);
                    requestMessage += "?\n" + buffer.readString(UTF8);
                }


                MyLogUtil.e("请求信息->"+requestMessage);
                if (!response.isSuccessful()) {
                    // 在这里进行请求错误的处理逻辑
                    // 例如，记录错误日志、重试请求等
                    // 这里只是简单地抛出一个异常作为示例
                    throw new IOException("请求错误：" + request.url().toString());
                }

                return response.newBuilder().body(ResponseBody.create(responseBody.contentType(),
                        responseBodyString.getBytes())).build();
            }
        };
    }


    /**
     * 基础公共参数拦截器
     *
     * @return
     */
    public static Interceptor providerInterceptor() {
        return new CommonParamInterceptor();
    }
}
