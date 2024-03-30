package net.daylong.baselibrary.http.factory;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

import net.daylong.baselibrary.http.GsonResponseBodyConverter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

public class GetResponseConverterFactory extends Converter.Factory {
    public static GetResponseConverterFactory create() {
        return create(new Gson());
    }

    public static GetResponseConverterFactory create(Gson gson) {
        return new GetResponseConverterFactory(gson);
    }

    private final Gson gson;

    private GetResponseConverterFactory(Gson gson) {
        if (gson == null) throw new NullPointerException("gson == null");
        this.gson = gson;
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        //返回我们自定义的Gson响应体变换器
        return new GsonResponseBodyConverter<>(gson, type);
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        //返回我们自定义的Gson响应体变换器

        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));

        return new GsonResponseBodyConverter<>(gson,type);
    }
}
