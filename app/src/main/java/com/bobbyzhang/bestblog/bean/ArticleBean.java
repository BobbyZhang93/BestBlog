package com.bobbyzhang.bestblog.bean;

import java.util.List;

/**
 * Created by bumiemac001 on 2017/10/25.
 */

public class ArticleBean {
    private List<ArticlesBean> articles;

    public List<ArticlesBean> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticlesBean> articles) {
        this.articles = articles;
    }

    public static class ArticlesBean {
        /**
         * "uuid": "890e8400-e29b-41d4-a716-446655442222",
         * "url": "http://stormzhang.com/2017/07/05/kotlin-and-deep-learning-reference/",
         * "title": "推荐两份学习 KOTLIN 和机器学习的资料"
         */

        private String uuid;
        private String url;
        private String title;

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

}
