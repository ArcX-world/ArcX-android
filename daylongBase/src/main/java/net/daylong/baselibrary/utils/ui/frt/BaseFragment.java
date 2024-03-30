package net.daylong.baselibrary.utils.ui.frt;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.components.support.RxFragment;

import net.daylong.baselibrary.app.BaseApplication;
import net.daylong.baselibrary.http.response.IBaseView;
import net.daylong.baselibrary.utils.sys.AppUtil;
import net.daylong.baselibrary.utils.ui.ToastUtil;
import net.daylong.baselibrary.utils.ui.act.BaseActivity;
import net.daylong.baselibrary.utils.ui.layout.ConstraintBuilder;
import net.daylong.baselibrary.utils.ui.layout.cl.ConstraintLayoutUtils;
import net.daylong.baselibrary.view.img.MyImageView;

import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;

;


public abstract class BaseFragment extends RxFragment implements IBaseView {
    /**
     * 上下文对象
     */
    protected Context mContext;

    protected ViewGroup rootView;


    protected View initContentView() {
        return ConstraintLayoutUtils.createMM(getContext());
    }

    /**
     * 加载框
     */

    @Override
    public BaseActivity getViewActivity() {
        return (BaseActivity) getActivity();
    }

    private Disposable disposable;

    protected int getLayoutId() {
        return 0;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof BaseActivity) {
            BaseActivity activityx = (BaseActivity) activity;
            disposable = activityx.lifecycle().subscribe(activityEvent -> {
                if (activityEvent == ActivityEvent.START) {
                    // 处理Activity发送的START事件
                    onEventStart();
                } else if (activityEvent == ActivityEvent.STOP) {
                    onEventStop();
                }
            });
        }
    }

    ;

    protected void onEventStart() {

    }

    protected void onEventStop() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if (getLayoutId() != 0) {
            View rootView = inflater.inflate(getLayoutId(), container, false);
            ButterKnife.bind(this, rootView);
            return rootView;
        } else {
            rootView = ConstraintLayoutUtils.createMM(getContext());

            return rootView;
        }


    }


    public void bgColor() {
        if (rootView != null) {
            rootView.setBackgroundColor(Color.RED);
        }
    }


    public void onFragmentEnter() {

    }

    public void onFragmentExit() {

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mContext = getContext();

        initView(view, savedInstanceState);
        initData();


    }


    /**
     * 初始化view
     * 子类实现 控件绑定、视图初始化等内容
     *
     * @param savedInstanceState
     */
    protected void initView(View view, Bundle savedInstanceState) {
    }

    /**
     * 初始化数据
     * 子类可以复写此方法初始化子类数据
     */
    protected void initData() {
    }


    @Override
    public void showToast(String message) {
        ToastUtil.show(message);
    }


    @Override
    public void showLoadingDialog() {

        getViewActivity().showLoadingDialog();

    }


    @Override
    public void showLoadingDialog(String message) {
        getViewActivity().showLoadingDialog(message);


    }


    @Override
    public void dismissLoadingDialog() {
        getViewActivity().dismissLoadingDialog();


    }

    @Override
    public void hideKeyBord() {
        //点击空白位置 隐藏软键盘
    }

    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return this.<T>bindToLifecycle();
    }


    @Override
    public void onDetach() {
        super.onDetach();
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }


    @Override
    public void back() {

    }


    public boolean isNotNull(String... str) {
        String[] clone = str.clone();
        for (String s : clone) {
            if (TextUtils.isEmpty(s)) {
                return false;
            }
        }

        return true;

    }

    public boolean isNull(String... str) {
        String[] clone = str.clone();
        for (String s : clone) {
            if (!TextUtils.isEmpty(s)) {
                return false;
            }
        }

        return true;

    }

    public String getText(EditText editText) {
        return editText.getText().toString().trim();
    }


    public void toast(String msg) {
        ToastUtil.show(msg);
    }


    /**
     * 添加Fragment
     *
     * @param fragment  需要添加的Fragment
     * @param container 布局Id
     */
    public void addFragment(BaseFragment fragment, int container) {
        if (fragment == null) {
            return;
        }
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        if (!fragment.isAdded()) {   // 先判断是否被add过
            transaction.add(container, fragment).commit(); // 隐藏当前的fragment，add下一个到Activity中
        } else {
            transaction.show(fragment).commit(); // 隐藏当前的fragment，显示下一个
        }
    }

    /**
     * 添加Fragment
     *
     * @param fragment  需要添加的Fragment
     * @param container 布局Id
     */
    public void addFragment(BaseFragment fragment, int container, String tag) {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        if (!fragment.isAdded() && null == getChildFragmentManager().findFragmentByTag(tag)) {   // 先判断是否被add过
            transaction.add(container, fragment, tag).commitAllowingStateLoss(); // 隐藏当前的fragment，add下一个到Activity中
        } else {
            transaction.show(fragment).commitAllowingStateLoss(); // 隐藏当前的fragment，显示下一个
        }
    }

    /**
     * 删除Fragment
     *
     * @param fragment
     */
    public void removeFragment(BaseFragment fragment) {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        if (fragment != null) {
            if (fragment.isVisible()) {
                transaction.remove(fragment).commit();
            }
        }
    }

    /**
     * 隐藏Fragment
     *
     * @param fragment
     */
    public void hideFragment(BaseFragment fragment) {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();

        if (fragment != null) {
            if (fragment.isVisible()) {
                transaction.hide(fragment).commit();
            }
        }
    }

    /**
     * 隐藏Fragment
     */
    public void hideFragment(String tag) {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();

        Fragment fragment = getChildFragmentManager().findFragmentByTag(tag);


        if (fragment != null && fragment.isAdded() && fragment.isVisible()) {

            transaction.hide(fragment).commit();
        }
    }

    /**
     * 隐藏Fragment
     *
     * @param fragment
     */
    public void showFragment(BaseFragment fragment) {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();

        if (fragment != null) {
            if (!fragment.isVisible()) {
                transaction.show(fragment).commit();
            }
        }
    }

    /**
     * 隐藏Fragment
     */
    public void showFragment(String tag) {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        Fragment fragment = getChildFragmentManager().findFragmentByTag(tag);
        if (null != fragment && !fragment.isVisible()) {
            transaction.show(fragment).commit();

        }


    }

    public String fromString(int str_reg, Object... text) {
        return String.format(getString(str_reg), text);

    }

    @Override
    public void onNetworkError() {
    }

    /**
     * 判断横竖屏
     *
     * @param activity
     * @return 1：竖 | 0：横
     */
    public static int getScreenOrient(Activity activity) {
        int orient = activity.getRequestedOrientation();
        if (orient != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE && orient != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            WindowManager windowManager = activity.getWindowManager();
            Display display = windowManager.getDefaultDisplay();
            int screenWidth = display.getWidth();
            int screenHeight = display.getHeight();
            orient = screenWidth < screenHeight ? ActivityInfo.SCREEN_ORIENTATION_PORTRAIT : ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
        }
        return orient;
    }


    public String getString(int res, String text) {

        return String.format(getString(res), text);
    }


    public void onErrorRefresh() {
    }


    protected void finish() {
        if (getActivity() != null) {
            getActivity().finish();
        }
    }


    protected OnDismissListener onDismissListener;

    public void setOnDismissListener(OnDismissListener onDismissListener) {
        this.onDismissListener = onDismissListener;
    }


    public interface OnDismissListener {
        void onDismiss();
    }


    public Context getAppContext() {
        return BaseApplication.getInstance().getContext();
    }


    public int getSize(float size) {
        return AppUtil.getSize(size);
    }

    /**
     * 添加view 到主view 中
     *
     * @param view
     */
    public void addView(View view) {
        if (rootView != null) {
            rootView.addView(view);
        }
    }

    /**
     * 添加View 到主view
     *
     * @param position 位置
     * @param view     view
     */
    public void addView(int position, View view) {
        if (rootView != null) {
            rootView.addView(view, position);
        }
    }

    protected Integer bgImgRegId() {
        return null;
    }

    /**
     * 初始化背景
     */
    protected void initBg() {
        if (bgImgRegId() != null) {
            MyImageView myImageView = MyImageView.create(rootView, new ConstraintBuilder().mm());
            myImageView.setBackgroundResource(bgImgRegId());
        }
    }

}
