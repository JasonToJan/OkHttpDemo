package com.okhttp.demo.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class QuickSaasBean implements Serializable, Parcelable {

    /**
     * id : 139
     * title : 分类
     * order : 1
     * showAllBtn : 0
     * key : 130
     * showMax : 5
     * upperLimit : 30
     * size : 5
     * showTitle : 0
     * games : [{"rpkId":1,"order":1,"content":"新游页","contentName":"新游","type":"webPool","iconSmall":"http://image.res.meizu.com/image/miniapp/1e0146a852b34a3e974bd8a39e9f955dz"}]
     */

    private int id;
    private String title;
    private int order;
    private String key;
    private int showName;
    private boolean regionShowName;
    private int showMax;
    private int source;
    private List<ContentBean> content;
    private List<Integer> gameIds;

    /**
     * 卡片缓存的key值
     */
    private String cardCacheKey = String.valueOf(id);

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getShowMax() {
        return showMax;
    }

    public void setShowMax(int showMax) {
        this.showMax = showMax;
    }

    public int getShowName() {
        return showName;
    }

    public void setShowName(int showName) {
        this.showName = showName;
    }

    public boolean isRegionShowName() {
        return regionShowName;
    }

    public void setRegionShowName(boolean regionShowName) {
        this.regionShowName = regionShowName;
    }

    public String getCardCacheKey() {
        return cardCacheKey;
    }

    public void setCardCacheKey(String cardCacheKey) {
        this.cardCacheKey = cardCacheKey;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public List<ContentBean> getContent() {
        return content;
    }

    public void setContent(List<ContentBean> content) {
        this.content = content;
    }

    public List<Integer> getGameIds() {
        return gameIds;
    }

    public void setGameIds(List<Integer> gameIds) {
        this.gameIds = gameIds;
    }

    public static class ContentBean implements Serializable, Parcelable{
        /**
         * id : 2790
         * rpkId : 281
         * packageName : com.meizu.chuanqilaile
         * name : 决战沙邑
         * iconUrl : http://image.res.meizu.com/image/miniapp/1558fe8071684d3a8fbcf20a0db6f4d2z
         * simpleDesc : 经典传奇重启
         * categoryId : 10
         * categoryName : 角色扮演
         * pickColor : #CDB7AB
         * playCount : 52023
         * type : game
         * contentName : 传奇来了
         * iconSmall : http://image.res.meizu.com/image/miniapp/70e0a74df8e44c62aa53116a6a916875z
         * reviewTime : 1606377997166
         * labels : [{"labelName":"ARPG","labelColor":"orange"},{"labelName":"挂机","labelColor":"purple"},{"labelName":"冒险","labelColor":"green"}]
         */
        private int rpkId;
        private int order;
        private String packageName;
        private String name;
        private String iconUrl;
        private String simpleDesc;
        private String simpleDescShort;
        private int playCount;
        private String avatars;
        private String bannerUrl;
        private String imageColour;
        private String ggUrl;

        /**
         * 是否已经曝光
         */
        private boolean isExposed = false;
        /**
         * 曝光次数
         */
        private int exposedCount;

        /**
         * 最新一次曝光时间
         */
        private long latestExposedTime;

        /**
         * 最近使用更新时间
         */
        private long recentUpdateTime;

        public int getRpkId() {
            return rpkId;
        }

        public void setRpkId(int rpkId) {
            this.rpkId = rpkId;
        }

        public String getPackageName() {
            return packageName;
        }

        public void setPackageName(String packageName) {
            this.packageName = packageName;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIconUrl() {
            return iconUrl;
        }

        public void setIconUrl(String iconUrl) {
            this.iconUrl = iconUrl;
        }

        public String getSimpleDesc() {
            return simpleDesc;
        }

        public void setSimpleDesc(String simpleDesc) {
            this.simpleDesc = simpleDesc;
        }

        public int getPlayCount() {
            return playCount;
        }

        public void setPlayCount(int playCount) {
            this.playCount = playCount;
        }

        public boolean isExposed() {
            return isExposed;
        }

        public void setExposed(boolean exposed) {
            isExposed = exposed;
            if (isExposed) {
                exposedCount++;
                latestExposedTime = System.currentTimeMillis();
            }
        }

        public int getExposedCount() {
            return exposedCount;
        }

        public void setExposedCount(int exposedCount) {
            this.exposedCount = exposedCount;
        }

        public long getLatestExposedTime() {
            return latestExposedTime;
        }

        public void setLatestExposedTime(long latestExposedTime) {
            this.latestExposedTime = latestExposedTime;
        }

        public int getOrder() {
            return order;
        }

        public void setOrder(int order) {
            this.order = order;
        }

        public String getSimpleDescShort() {
            return simpleDescShort;
        }

        public void setSimpleDescShort(String simpleDescShort) {
            this.simpleDescShort = simpleDescShort;
        }

        public String getAvatars() {
            return avatars;
        }

        public void setAvatars(String avatars) {
            this.avatars = avatars;
        }

        public String getBannerUrl() {
            return bannerUrl;
        }

        public void setBannerUrl(String bannerUrl) {
            this.bannerUrl = bannerUrl;
        }

        public String getImageColour() {
            return imageColour;
        }

        public void setImageColour(String imageColour) {
            this.imageColour = imageColour;
        }

        public String getGgUrl() {
            return ggUrl;
        }

        public void setGgUrl(String ggUrl) {
            this.ggUrl = ggUrl;
        }

        public long getRecentUpdateTime() {
            return recentUpdateTime;
        }

        public void setRecentUpdateTime(long recentUpdateTime) {
            this.recentUpdateTime = recentUpdateTime;
        }

        public ContentBean() {
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.rpkId);
            dest.writeInt(this.order);
            dest.writeString(this.packageName);
            dest.writeString(this.name);
            dest.writeString(this.iconUrl);
            dest.writeString(this.simpleDesc);
            dest.writeString(this.simpleDescShort);
            dest.writeInt(this.playCount);
            dest.writeString(this.avatars);
            dest.writeString(this.bannerUrl);
            dest.writeString(this.imageColour);
            dest.writeString(this.ggUrl);
            dest.writeLong(this.recentUpdateTime);
            dest.writeByte(this.isExposed ? (byte) 1 : (byte) 0);
            dest.writeInt(this.exposedCount);
            dest.writeLong(this.latestExposedTime);
        }

        public void readFromParcel(Parcel source) {
            this.rpkId = source.readInt();
            this.order = source.readInt();
            this.packageName = source.readString();
            this.name = source.readString();
            this.iconUrl = source.readString();
            this.simpleDesc = source.readString();
            this.simpleDescShort = source.readString();
            this.playCount = source.readInt();
            this.avatars = source.readString();
            this.bannerUrl = source.readString();
            this.imageColour = source.readString();
            this.ggUrl = source.readString();
            this.isExposed = source.readByte() != 0;
            this.exposedCount = source.readInt();
            this.latestExposedTime = source.readLong();
            this.recentUpdateTime = source.readLong();
        }

        protected ContentBean(Parcel in) {
            this.rpkId = in.readInt();
            this.order = in.readInt();
            this.packageName = in.readString();
            this.name = in.readString();
            this.iconUrl = in.readString();
            this.simpleDesc = in.readString();
            this.simpleDescShort = in.readString();
            this.playCount = in.readInt();
            this.avatars = in.readString();
            this.bannerUrl = in.readString();
            this.imageColour = in.readString();
            this.ggUrl = in.readString();
            this.isExposed = in.readByte() != 0;
            this.exposedCount = in.readInt();
            this.latestExposedTime = in.readLong();
            this.recentUpdateTime = in.readLong();
        }

        public static final Creator<ContentBean> CREATOR = new Creator<ContentBean>() {
            @Override
            public ContentBean createFromParcel(Parcel source) {
                return new ContentBean(source);
            }

            @Override
            public ContentBean[] newArray(int size) {
                return new ContentBean[size];
            }
        };

        @Override
        public String toString() {
            return "ContentBean{" +
                    "rpkId=" + rpkId +
                    ", order=" + order +
                    ", packageName='" + packageName + '\'' +
                    ", name='" + name + '\'' +
                    ", iconUrl='" + iconUrl + '\'' +
                    ", simpleDesc='" + simpleDesc + '\'' +
                    ", simpleDescShort='" + simpleDescShort + '\'' +
                    ", playCount=" + playCount +
                    '}';
        }
    }

    public QuickSaasBean() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeInt(this.order);
        dest.writeString(this.key);
        dest.writeInt(this.showName);
        dest.writeInt(this.showMax);
        dest.writeInt(this.source);
        dest.writeTypedList(this.content);
        dest.writeList(this.gameIds);
        dest.writeString(this.cardCacheKey);
    }

    public void readFromParcel(Parcel source) {
        this.id = source.readInt();
        this.title = source.readString();
        this.order = source.readInt();
        this.key = source.readString();
        this.showName = source.readInt();
        this.showMax = source.readInt();
        this.source = source.readInt();
        this.content = source.createTypedArrayList(ContentBean.CREATOR);
        this.gameIds = new ArrayList<Integer>();
        source.readList(this.gameIds, Integer.class.getClassLoader());
        this.cardCacheKey = source.readString();
        int tmpCardStyleUniqueId = source.readInt();
    }

    protected QuickSaasBean(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.order = in.readInt();
        this.key = in.readString();
        this.showName = in.readInt();
        this.showMax = in.readInt();
        this.source = in.readInt();
        this.content = in.createTypedArrayList(ContentBean.CREATOR);
        this.gameIds = new ArrayList<Integer>();
        in.readList(this.gameIds, Integer.class.getClassLoader());
        this.cardCacheKey = in.readString();
        int tmpCardStyleUniqueId = in.readInt();
    }

    public static final Creator<QuickSaasBean> CREATOR = new Creator<QuickSaasBean>() {
        @Override
        public QuickSaasBean createFromParcel(Parcel source) {
            return new QuickSaasBean(source);
        }

        @Override
        public QuickSaasBean[] newArray(int size) {
            return new QuickSaasBean[size];
        }
    };

    @Override
    public String toString() {
        return "QuickSaasBean{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", order=" + order +
                ", key='" + key + '\'' +
                ", showName=" + showName +
                ", showMax=" + showMax +
                ", content=" + content +
                ", cardCacheKey='" + cardCacheKey + '\'' +
                '}';
    }

}