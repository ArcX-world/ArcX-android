package com.daylong.arcx.view.user.wallet;

import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;

import com.daylong.httplibrary.bean.response.wallet.WalletConfigureResponse;

import net.daylong.baselibrary.utils.MyLogUtil;
import net.daylong.baselibrary.utils.StringUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class MyInputFilter implements InputFilter {
    final int MAX_DECIMAL_PLACES = 2;

    private WalletConfigureResponse configureInfo;
    private int id;
    private EditText editText;

    private double price;

    public MyInputFilter(WalletConfigureResponse configureInfo, int id, EditText editText, double price) {
        this.configureInfo = configureInfo;
        this.id = id;
        this.editText = editText;
        this.price = price;
    }

    @Override

    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        if (dest.toString().contains(".") &&
                (source.equals(".") ||
                        dest.subSequence(dend, dest.length()).toString().equals("."))) {
            return "";
        }
        try {
            String endText = source.toString();
            String lastText = dest.toString();

            /**
             * 如果是删除不做处理
             */
            if (endText.length() > lastText.length()) {
                return null;
            }


            String resultingTxt = dest.subSequence(0, dstart).toString() + endText +
                    dest.subSequence(dend, dest.length()).toString();


            //最小的输入内容
            int minInput = configureInfo.getMinInput(id);


//            if (!TextUtils.isEmpty(resultingTxt) && StringUtils.isNumber(resultingTxt)) {
//
//
//                MyLogUtil.e("ag--input-->" + resultingTxt + "<最小>" + minInput);
//                //输入的
//                int i = Integer.parseInt(resultingTxt) * 100;
//                //可以输入最大值
//                int maxInput = configureInfo.getMaxInput(id, price);
//                //余额
//                if (i > maxInput) {
//                    editText.setText(String.valueOf((int) maxInput));
//                    editText.setSelection(editText.getText().length());
//                }
//            }

            BigDecimal parser = new BigDecimal(resultingTxt);
            DecimalFormat formatter = new DecimalFormat("0.##");
            formatter.setMaximumFractionDigits(MAX_DECIMAL_PLACES);

            if (parser.compareTo(new BigDecimal(formatter.format(parser))) != 0) {

//
                return "";
            } else {
                MyLogUtil.e("rag-->" + formatter.format(resultingTxt));
                return null;
            }
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
            return "";
        }
    }


}
