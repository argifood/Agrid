package com.horus_vision.agrid.enums;

import com.horus_vision.agrid.R;

public enum ModelObject {

    FRAGMENT1(R.string.fragment1),
    FRAGMENT2(R.string.fragment2),
    FRAGMENT3(R.string.fragment3);
//    FRAGMENTTEST(R.string.fragment_test),
//    OMADARA(R.string.omadara);


    private int mTitleResId;

    ModelObject(int titleResId) {
        mTitleResId = titleResId;
    }


    public int getTitleResId() {
        return mTitleResId;
    }


}