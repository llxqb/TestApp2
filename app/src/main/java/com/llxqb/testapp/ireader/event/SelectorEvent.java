package com.llxqb.testapp.ireader.event;

import com.llxqb.testapp.ireader.model.flag.BookDistillate;
import com.llxqb.testapp.ireader.model.flag.BookSort;
import com.llxqb.testapp.ireader.model.flag.BookType;

/**
 * Created by newbiechen on 17-4-21.
 */

public class SelectorEvent {

    public BookDistillate distillate;

    public BookType type;

    public BookSort sort;

    public SelectorEvent(BookDistillate distillate,
                         BookType type,
                         BookSort sort) {
        this.distillate = distillate;
        this.type = type;
        this.sort = sort;
    }
}
