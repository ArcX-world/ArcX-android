package net.daylong.baselibrary.view.recycler;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import net.daylong.baselibrary.utils.MyLogUtil;

import java.util.List;


public abstract class BaseRefreshRecycler<T> extends SmartRefreshLayout implements OnRefreshListener, OnLoadMoreListener {

    private BaseRecyclerView recyclerView;
    protected BaseQuickAdapter<T, BaseViewHolder> baseQuickAdapter;

    public BaseQuickAdapter<T, BaseViewHolder> getBaseQuickAdapter() {
        return null;
    }

    /**
     * 是否启动拉下刷新
     *
     * @return
     */
    protected boolean isRefresh() {
        return false;
    }

    /**
     * 是否启动加载更多
     *
     * @return
     */
    protected boolean isLoadMore() {
        return false;
    }

    public BaseRefreshRecycler(Context context) {
        this(context, null);
    }


    public BaseRefreshRecycler(Context context, AttributeSet attrs) {
        super(context, attrs);
        CustomRefreshHeader customRefreshHeader = new CustomRefreshHeader(getContext());
        setRefreshHeader(customRefreshHeader);
        recyclerView = getRecyclerView();
        baseQuickAdapter = getBaseQuickAdapter();
        if (baseQuickAdapter != null) {
            recyclerView.setAdapter(getBaseQuickAdapter());
        }
        addView(recyclerView);
        ClassicsFooter classicsFooter = new ClassicsFooter(getContext());
        //取消加载提示
        classicsFooter.setAccentColor(Color.TRANSPARENT);
        classicsFooter.setPrimaryColor(Color.TRANSPARENT);
        setRefreshFooter(classicsFooter);
        setReboundDuration(300);
        setEnableRefresh(isRefresh());
        setEnableLoadMore(isLoadMore());


        if (isRefresh()) {
            setOnRefreshListener(this);

        }
        if (isLoadMore()) {
            setOnLoadMoreListener(this);
        }
    }


    public void setBaseQuickAdapter(BaseQuickAdapter<T, BaseViewHolder> baseQuickAdapter) {
        recyclerView.setAdapter(baseQuickAdapter);
    }

    public BaseRecyclerView getRecyclerView() {
        VerticalRecyclerView verticalRecyclerView = new VerticalRecyclerView(getContext());
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        verticalRecyclerView.setLayoutParams(layoutParams);
        return verticalRecyclerView;
    }


    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {

        MyLogUtil.e("刷新");

    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        MyLogUtil.e("加载更多");

    }


    /**
     * 没有更多数据
     */
    public void finishNoMoreData() {
        finishLoadMore();
        finishLoadMoreWithNoMoreData();
        setEnableLoadMore(false);
    }

    public void setData(List<T> list) {
        baseQuickAdapter.setList(list);
    }


}
