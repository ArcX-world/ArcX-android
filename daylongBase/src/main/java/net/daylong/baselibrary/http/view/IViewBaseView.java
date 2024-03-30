package net.daylong.baselibrary.http.view;


import com.trello.rxlifecycle2.LifecycleTransformer;
;

public interface IViewBaseView {



    /**
     * 绑定生命周期
     *
     * @param <T>
     * @return
     */
    <T> LifecycleTransformer<T> bindToLife();




}
