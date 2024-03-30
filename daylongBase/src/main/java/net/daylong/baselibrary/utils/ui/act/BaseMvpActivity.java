package net.daylong.baselibrary.utils.ui.act;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import net.daylong.baselibrary.http.mvp.BasePresenter;
import net.daylong.baselibrary.http.response.IBaseModel;

public abstract class BaseMvpActivity<P extends BasePresenter, M extends IBaseModel>
        extends BaseActivity  {


    /**
     * presenter 具体的presenter由子类确定
     */
    protected P mPresenter;
    /**
     * model 具体的model由子类确定
     */
    protected M mModel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        initMvp();
        super.onCreate(savedInstanceState);
    }

    private void initMvp() {
        mPresenter = initPresenter();
        if (mPresenter != null) {
            mModel = (M) mPresenter.getModel();
            if (mModel != null) {
                mPresenter.attachMV(mModel, this);
            }
        }
    }

    /**
     * 初始化presenter
     *
     * @return
     */
    @NonNull
    protected abstract P initPresenter();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachMV();
            mPresenter = null;
            System.gc();
        }

    }

}
