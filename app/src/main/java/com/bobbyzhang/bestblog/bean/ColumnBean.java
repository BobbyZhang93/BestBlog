package com.bobbyzhang.bestblog.bean;

import java.util.List;

/**
 * Created by BobbyZhang on 2017/9/16.
 */

public class ColumnBean {


    private List<ColumnsBean> columns;

    public List<ColumnsBean> getColumns() {
        return columns;
    }

    public void setColumns(List<ColumnsBean> columns) {
        this.columns = columns;
    }

    public static class ColumnsBean {
        /**
         * uuid : 550e8400-e29b-41d4-a716-446655440000
         * url : http://blog.itbobby.top/
         * remark : BobbyZhang的博客
         * isstart : 1
         */

        private String uuid;
        private String url;
        private String remark;
        private String isstart;

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

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getIsstart() {
            return isstart;
        }

        public void setIsstart(String isstart) {
            this.isstart = isstart;
        }
    }
}

