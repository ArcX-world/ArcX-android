package com.daylong.arcx.view.home;

import android.content.Context;
import android.view.View;

import com.daylong.arcx.R;

import net.daylong.baselibrary.utils.ui.layout.ConstraintBuilder;
import net.daylong.baselibrary.view.layout.LinearLayoutView;

public class HomeTabGroupView extends LinearLayoutView implements View.OnClickListener {
    private HomeTabView dollView;


    public HomeTabGroupView(Context context) {
        super(context);
        setOrientation(HORIZONTAL);

        setLayoutParams(new ConstraintBuilder().mm().height(58).leftBottom().buildPayoutParams());
        HomeTabView pusherView = new HomeTabView(getContext(), "推币机", R.drawable.img_main_pusher);
        pusherView.setId(net.daylong.daylongbase.R.id.base_view_1);
        dollView = new HomeTabView(getContext(), "娃娃机", R.drawable.img_main_doll);
        dollView.setId(net.daylong.daylongbase.R.id.base_view_2);

        HomeTabView arcadeView = new HomeTabView(getContext(), "街机", R.drawable.img_main_arcade);
        arcadeView.setId(net.daylong.daylongbase.R.id.base_view_3);

        pusherView.setOnClickListener(this);
        dollView.setOnClickListener(this);
        arcadeView.setOnClickListener(this);

        addView(pusherView);
        addView(dollView);
        addView(arcadeView);
        dollView.setCheck(true);

    }

    @Override
    public void onClick(View view) {
        for (int i = 0; i < getChildCount(); i++) {
            HomeTabView item = (HomeTabView) getChildAt(i);
            if (view == item) {
                if (!item.isCheck()) {
                    item.setCheck(true);
                    if (onSelectItemClickListener != null) {
                        onSelectItemClickListener.selectItem(view);
                    }
                }
            } else {
                if (item.isCheck()) {
                    item.setCheck(false);
                }
            }
        }
    }

    private OnSelectItemClickListener onSelectItemClickListener;

    public void setOnSelectItemClickListener(OnSelectItemClickListener onSelectItemClickListener) {
        if (this.onSelectItemClickListener == null) {
            this.onSelectItemClickListener = onSelectItemClickListener;
            if (onSelectItemClickListener != null) {
                onSelectItemClickListener.selectItem(dollView);
            }
        }
    }

    public interface OnSelectItemClickListener {
        void selectItem(View view);
    }

}
