package net.daylong.baselibrary.utils.ui.frt;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;

import net.daylong.baselibrary.http.mvp.BasePresenter;
import net.daylong.baselibrary.http.response.IBaseModel;


public abstract class BaseMvpFragment<P extends BasePresenter, M extends IBaseModel>
        extends BaseFragment {

    /**
     * presenter 具体的presenter由子类确定
     */
    protected P mPresenter;
    /**
     * model 具体的model由子类确定
     */
    protected M mModel;
    @Override
    protected void initView(View view, Bundle savedInstanceState) {
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
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachMV();
            mPresenter = null;

        }
        System.gc();
    }


}
