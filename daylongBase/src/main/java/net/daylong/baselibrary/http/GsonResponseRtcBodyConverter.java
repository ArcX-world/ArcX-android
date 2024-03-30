package net.daylong.baselibrary.http;

import com.google.gson.Gson;

import net.daylong.baselibrary.http.base.BaseHttpRtcStatus;
import net.daylong.baselibrary.http.base.BaseHttpStatus;
import net.daylong.baselibrary.utils.MyLogUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * 请求返回
 *
 * @param <T>
 */
public class GsonResponseRtcBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final Type type;

    public GsonResponseRtcBodyConverter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }
    @Override
    public T convert(ResponseBody value) throws IOException {

        String response = value.string();
        //BaseHttpStatus 只解析 code和message
        MyLogUtil.e(response);
        BaseHttpRtcStatus httpStatus = gson.fromJson(response, BaseHttpRtcStatus.class);
        if (!httpStatus.isOk()) { //失败

            value.close();
            try {
                JSONObject jsonObject = new JSONObject(response);
                JSONObject dataJson = jsonObject.optJSONObject("data");
                throw new ApiException(httpStatus.getErrcode(), httpStatus.getErrmsg(), dataJson);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            throw new ApiException(httpStatus.getErrcode(), httpStatus.getErrmsg(), null);
        } else {//成功
            return gson.fromJson(response, type);
        }

    }
}
