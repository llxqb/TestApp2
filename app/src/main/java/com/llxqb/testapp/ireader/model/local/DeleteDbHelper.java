package com.llxqb.testapp.ireader.model.local;


import com.llxqb.testapp.ireader.model.bean.AuthorBean;
import com.llxqb.testapp.ireader.model.bean.BookCommentBean;
import com.llxqb.testapp.ireader.model.bean.BookHelpfulBean;
import com.llxqb.testapp.ireader.model.bean.BookHelpsBean;
import com.llxqb.testapp.ireader.model.bean.BookReviewBean;
import com.llxqb.testapp.ireader.model.bean.ReviewBookBean;

import java.util.List;

/**
 * Created by newbiechen on 17-4-28.
 */

public interface DeleteDbHelper {
    void deleteBookComments(List<BookCommentBean> beans);
    void deleteBookReviews(List<BookReviewBean> beans);
    void deleteBookHelps(List<BookHelpsBean> beans);
    void deleteAuthors(List<AuthorBean> beans);
    void deleteBooks(List<ReviewBookBean> beans);
    void deleteBookHelpful(List<BookHelpfulBean> beans);
    void deleteAll();
}
