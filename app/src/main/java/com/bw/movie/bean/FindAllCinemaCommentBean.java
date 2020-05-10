package com.bw.movie.bean;

import java.util.List;

public class FindAllCinemaCommentBean {
    /**
     * result : [{"commentContent":"�ܺ�","commentHeadPic":"http://mobile.bwstudent.com/images/movie/head_pic/2020-03-16/20200316212037.png","commentId":583,"commentTime":1588314616000,"commentUserId":13816,"commentUserName":"刘晓辉","greatHeadPic":[],"greatNum":0,"hotComment":0,"isGreat":0},{"commentContent":"哈哈哈","commentHeadPic":"http://mobile.bwstudent.com/images/movie/head_pic/bwjy.jpg","commentId":461,"commentTime":1586090061000,"commentUserId":13761,"commentUserName":"不是我的益达","greatHeadPic":[],"greatNum":3,"hotComment":0,"isGreat":0},{"commentContent":"很好","commentHeadPic":"http://mobile.bwstudent.com/images/movie/head_pic/2020-04-27/20200427210002.microvirt.launcher2.png","commentId":460,"commentTime":1585899093000,"commentUserId":13837,"commentUserName":"花花公子","greatHeadPic":[],"greatNum":0,"hotComment":0,"isGreat":0},{"commentContent":"����","commentHeadPic":"http://mobile.bwstudent.com/images/movie/head_pic/2020-04-27/20200427213048.unknown","commentId":456,"commentTime":1585746977000,"commentUserId":13692,"commentUserName":"���С�ɰ�","greatHeadPic":[],"greatNum":0,"hotComment":0,"isGreat":0},{"commentContent":"sds","commentHeadPic":"http://mobile.bwstudent.com/images/movie/head_pic/bwjy.jpg","commentId":452,"commentTime":1585741916000,"commentUserId":13870,"commentUserName":"我试试","greatHeadPic":[],"greatNum":0,"hotComment":0,"isGreat":0}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<ResultBean> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * commentContent : �ܺ�
         * commentHeadPic : http://mobile.bwstudent.com/images/movie/head_pic/2020-03-16/20200316212037.png
         * commentId : 583
         * commentTime : 1588314616000
         * commentUserId : 13816
         * commentUserName : 刘晓辉
         * greatHeadPic : []
         * greatNum : 0
         * hotComment : 0
         * isGreat : 0
         */

        private String commentContent;
        private String commentHeadPic;
        private int commentId;
        private long commentTime;
        private int commentUserId;
        private String commentUserName;
        private int greatNum;
        private int hotComment;
        private int isGreat;
        private List<?> greatHeadPic;

        public String getCommentContent() {
            return commentContent;
        }

        public void setCommentContent(String commentContent) {
            this.commentContent = commentContent;
        }

        public String getCommentHeadPic() {
            return commentHeadPic;
        }

        public void setCommentHeadPic(String commentHeadPic) {
            this.commentHeadPic = commentHeadPic;
        }

        public int getCommentId() {
            return commentId;
        }

        public void setCommentId(int commentId) {
            this.commentId = commentId;
        }

        public long getCommentTime() {
            return commentTime;
        }

        public void setCommentTime(long commentTime) {
            this.commentTime = commentTime;
        }

        public int getCommentUserId() {
            return commentUserId;
        }

        public void setCommentUserId(int commentUserId) {
            this.commentUserId = commentUserId;
        }

        public String getCommentUserName() {
            return commentUserName;
        }

        public void setCommentUserName(String commentUserName) {
            this.commentUserName = commentUserName;
        }

        public int getGreatNum() {
            return greatNum;
        }

        public void setGreatNum(int greatNum) {
            this.greatNum = greatNum;
        }

        public int getHotComment() {
            return hotComment;
        }

        public void setHotComment(int hotComment) {
            this.hotComment = hotComment;
        }

        public int getIsGreat() {
            return isGreat;
        }

        public void setIsGreat(int isGreat) {
            this.isGreat = isGreat;
        }

        public List<?> getGreatHeadPic() {
            return greatHeadPic;
        }

        public void setGreatHeadPic(List<?> greatHeadPic) {
            this.greatHeadPic = greatHeadPic;
        }
    }
}
