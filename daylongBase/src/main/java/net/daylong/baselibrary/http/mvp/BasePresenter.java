package net.daylong.baselibrary.http.mvp;
import androidx.annotation.NonNull;

import net.daylong.baselibrary.utils.rx.RxManager;


public abstract class BasePresenter<M, V> {
    protected M mIModel;
    protected V mIView;
    protected RxManager mRxManager = new RxManager();

    /**
     * IView和IModel绑定完成立即执行
     * 实现类实现绑定完成后的逻辑,例如数据初始化等,界面初始化, 更新等
     */
    public abstract void onStart();

    /**
     * 绑定IModel和IView的引用
     * @param mIModel
     * @param mIView
     */
    public void attachMV(@NonNull M mIModel, @NonNull V mIView){
        this.mIModel = mIModel;
        this.mIView = mIView;
        this.onStart();
    }

    /**
     * 返回presenter想持有的Model引用
     * @return
     */
    public abstract M getModel();

    /**
     * 解绑IModel和IView
     */
    public void detachMV(){
        mIModel = null;
        mIView = null;
        mRxManager = null;
    }

}
