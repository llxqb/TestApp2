package com.llxqb.testapp.ireader.presenter.contract;


import com.llxqb.testapp.ireader.model.bean.BillBookBean;
import com.llxqb.testapp.ireader.ui.base.BaseContract;

import java.util.List;

/**
 * Created by newbiechen on 17-5-3.
 */

public interface BillBookContract {
    interface View extends BaseContract.BaseView{
        void finishRefresh(List<BillBookBean> beans);
    }

    interface Presenter extends BaseContract.BasePresenter<View>{
        void refreshBookBrief(String billId);
    }
}
