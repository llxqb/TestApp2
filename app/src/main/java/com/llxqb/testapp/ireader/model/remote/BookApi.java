package com.llxqb.testapp.ireader.model.remote;


import com.llxqb.testapp.ireader.model.bean.BookDetailBean;
import com.llxqb.testapp.ireader.model.bean.packages.BillBookPackage;
import com.llxqb.testapp.ireader.model.bean.packages.BillboardPackage;
import com.llxqb.testapp.ireader.model.bean.packages.BookChapterPackage;
import com.llxqb.testapp.ireader.model.bean.packages.BookCommentPackage;
import com.llxqb.testapp.ireader.model.bean.packages.BookHelpsPackage;
import com.llxqb.testapp.ireader.model.bean.packages.BookListDetailPackage;
import com.llxqb.testapp.ireader.model.bean.packages.BookListPackage;
import com.llxqb.testapp.ireader.model.bean.packages.BookReviewPackage;
import com.llxqb.testapp.ireader.model.bean.packages.BookSortPackage;
import com.llxqb.testapp.ireader.model.bean.packages.BookSubSortPackage;
import com.llxqb.testapp.ireader.model.bean.packages.BookTagPackage;
import com.llxqb.testapp.ireader.model.bean.packages.ChapterInfoPackage;
import com.llxqb.testapp.ireader.model.bean.packages.CommentDetailPackage;
import com.llxqb.testapp.ireader.model.bean.packages.CommentsPackage;
import com.llxqb.testapp.ireader.model.bean.packages.HelpsDetailPackage;
import com.llxqb.testapp.ireader.model.bean.packages.HotCommentPackage;
import com.llxqb.testapp.ireader.model.bean.packages.HotWordPackage;
import com.llxqb.testapp.ireader.model.bean.packages.KeyWordPackage;
import com.llxqb.testapp.ireader.model.bean.packages.RecommendBookListPackage;
import com.llxqb.testapp.ireader.model.bean.packages.RecommendBookPackage;
import com.llxqb.testapp.ireader.model.bean.packages.ReviewDetailPackage;
import com.llxqb.testapp.ireader.model.bean.packages.SearchBookPackage;
import com.llxqb.testapp.ireader.model.bean.packages.SortBookPackage;
import com.llxqb.testapp.ireader.model.bean.packages.TagSearchPackage;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by newbiechen on 17-4-20.
 */

public interface BookApi {

    /**
     * 推荐书籍
     * @param gender
     * @return
     */
    @GET("/book/recommend")
    Single<RecommendBookPackage> getRecommendBookPackage(@Query("gender") String gender);


    /**
     * 获取书籍的章节总列表
     * @param bookId
     * @param view 默认参数为:chapters
     * @return
     */
    @GET("/mix-atoc/{bookId}")
    Single<BookChapterPackage> getBookChapterPackage(@Path("bookId") String bookId, @Query("view") String view);

    /**
     * 章节的内容
     * 这里采用的是同步请求。
     * @return
     */
    @POST("/cartoon/book/catalogueTxt")
    Single<ChapterInfoPackage> getChapterInfoPackage(@Body String request);


}
