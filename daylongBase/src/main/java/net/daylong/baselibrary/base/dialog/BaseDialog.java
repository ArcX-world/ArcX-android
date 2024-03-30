package net.daylong.baselibrary.base.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;

import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.FloatRange;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import net.daylong.baselibrary.app.BaseApplication;
import net.daylong.baselibrary.utils.sys.AppUtil;
import net.daylong.baselibrary.utils.ui.frt.BaseFragment;
import net.daylong.baselibrary.utils.ui.layout.cl.ConstraintLayoutUtils;
import net.daylong.daylongbase.R;

/**
 * Dialog通用样式
 */
public abstract class BaseDialog extends DialogFragment {

    protected float mDimAmount = 0.9f;//背景昏暗度
    protected boolean mShowBottomEnable;//是否底部显示
    private int mMargin = 0;//左右边距

    protected int getAnimStyle() {
        return 0;
    }

    ;//进入退出动画
    private boolean mOutCancel = true;//点击外部取消
    private Context mContext;
    private int mWidth;
    private int mHeight;

    protected float getDimAmount() {
        return mDimAmount;
    }

    protected int gravity() {
        return Gravity.CENTER;
    }

    @StyleRes
    protected int getDialogStyle() {
        return R.style.MaterialSearch;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public Context getmContext() {
        return mContext;
    }


    /**
     * 其他地方是否可以点击关闭
     *Y
     * @return
     */
    protected boolean isWinDismiss() {
        return false;
    }

    private Handler handler = new Handler();

    @Override
    public void onResume() {
        super.onResume();
        if (handler != null) {
            handler.postDelayed(() -> {
                if (getDialog() != null && getDialog().getWindow() != null) {
                    int style = getAnimStyle();
                    if (getAnimStyle() == 0) {
                        style = R.style.FragmentDialogAnimation;
                    }
                    getDialog().getWindow().setWindowAnimations(style);
                }

            }, 500);
        }
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = BaseApplication.getInstance().getContext();

        setStyle(DialogFragment.STYLE_NORMAL, getDialogStyle());


    }

    protected ViewGroup rootView;

    protected int mLayoutResId() {
        return -1;
    }



    protected  ViewGroup getContentLayout(){
        return null;
    };
    protected ViewGroup contentView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (mLayoutResId() > 0) {
            rootView = (ViewGroup) inflater.inflate(mLayoutResId(), container, false);
            initView(rootView, this);
        } else {
            rootView = ConstraintLayoutUtils.createMM(getContext());
            if (getContentLayout() != null) {
                if (contentView == null) {
                    contentView = getContentLayout();
                    contentView.setId(R.id.base_content_id);
                    if (getContentBg() != null) {
                        contentView.setBackgroundResource(getContentBg());
                    }
                    contentView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    });
                    rootView.addView(contentView);
                }
            }

            initView(rootView, contentView, this);

            initView((ConstraintLayout) rootView, this);
        }
        initData();


        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isWinDismiss()) {
                    dismiss();
                }
            }
        });
        initParams();
        return rootView;

    }

    protected Integer getContentBg() {

        return null;
    }
    public View getRootView() {
        return rootView;
    }

    public ConstraintLayout getRootConstraintView() {
        return (ConstraintLayout) rootView;
    }


    @Override
    public void onStop() {
        super.onStop();
        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().setWindowAnimations(0);
        }
    }

    protected boolean isBackDismiss() {
        return false;
    }


    /**
     * 初始化
     */
    private void initParams() {
        Window window = getDialog().getWindow();
        mDimAmount = getDimAmount();
        if (window != null) {
            WindowManager.LayoutParams params = window.getAttributes();
            params.dimAmount = mDimAmount;
            params.softInputMode = WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE;
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
            //设置dialog动画
            if (getAnimStyle() != 0) {
                window.setWindowAnimations(getAnimStyle());

            } else {
                window.setWindowAnimations(R.style.FragmentDialogAnimation);
            }

            window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN); // 隐藏状态栏
            window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON); // 保持屏幕常亮
            window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN); // 布局扩展到屏幕之外
            window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

            window.setAttributes(params);
            getDialog().setOnKeyListener(new DialogInterface.OnKeyListener() {
                @Override
                public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                    if (!isBackDismiss()) {
                        if (keyCode == KeyEvent.KEYCODE_BACK) {
                            return true;
                        }
                        return false;
                    } else {
                        return false;
                    }
                }
            });
        }
        setCancelable(mOutCancel);

    }

    /**
     * 设置背景昏暗度
     *
     * @param dimAmount
     * @return
     */
    public BaseDialog setDimAmount(@FloatRange(from = 0, to = 1) float dimAmount) {
        mDimAmount = dimAmount;
        return this;
    }


    /**
     * 是否显示底部
     *
     * @param showBottom
     * @return
     */
    public BaseDialog setShowBottom(boolean showBottom) {
        mShowBottomEnable = showBottom;
        return this;
    }

    /**
     * 设置宽高
     *
     * @param width
     * @param height
     * @return
     */
    public BaseDialog setSize(int width, int height) {
        mWidth = width;
        mHeight = height;
        return this;
    }

    /**
     * 设置左右margin
     *
     * @param margin
     * @return
     */
    public BaseDialog setMargin(int margin) {
        mMargin = margin;
        return this;
    }


    /**
     * 设置是否点击外部取消
     *
     * @param outCancel
     * @return
     */
    public BaseDialog setOutCancel(boolean outCancel) {
        mOutCancel = outCancel;
        return this;
    }

    public BaseDialog show(FragmentManager manager) {
        super.show(manager, String.valueOf(System.currentTimeMillis()));
        return this;
    }


    /**
     * 操作dialog布局
     *
     * @param rootView
     * @param dialog
     */
    public abstract void initView(ConstraintLayout rootView, BaseDialog dialog);
    /**
     * 操作dialog布局
     *
     * @param rootView
     * @param dialog
     */
    public  void initView(ViewGroup rootView, ViewGroup contentView, BaseDialog dialog){}
    /**
     * 操作dialog布局
     *
     * @param dialog
     */
    protected void initView(View view, BaseDialog dialog) {
    }


    /**
     * 初始化数据
     */
    public abstract void initData();

    /**
     * 获取屏幕宽度
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return displayMetrics.widthPixels;
    }

    public static int dp2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
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


    @Override
    public void dismiss() {

        super.dismiss();
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            handler = null;
        }
        if (OnDismissListener != null) {
            OnDismissListener.onDismiss();
        }
    }

    protected OnDismissListener OnDismissListener;

    public void setOnDismissListener(BaseDialog.OnDismissListener onDismissListener) {
        OnDismissListener = onDismissListener;
    }

    public interface OnDismissListener {

        void onDismiss();
    }


    public int getSize(float size) {
        return AppUtil.getSize(size);
    }

    public void addView(View view) {

        if (rootView instanceof ConstraintLayout) {
            ((ConstraintLayout) rootView).addView(view);
        }
    }
}
