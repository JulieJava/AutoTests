
package dto;

import java.util.List;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "deletehash",
    "account_id",
    "account_url",
    "ad_type",
    "ad_url",
    "title",
    "description",
    "name",
    "type",
    "width",
    "height",
    "size",
    "views",
    "section",
    "vote",
    "bandwidth",
    "animated",
    "favorite",
    "in_gallery",
    "in_most_viral",
    "has_sound",
    "is_ad",
    "nsfw",
    "link",
    "tags",
    "datetime",
    "mp4",
    "hls"
})
@Generated("jsonschema2pojo")
public class Data {

    @JsonProperty("id")
    private String id;
    @JsonProperty("deletehash")
    private String deletehash;
    @JsonProperty("account_id")
    private Object accountId;
    @JsonProperty("account_url")
    private Object accountUrl;
    @JsonProperty("ad_type")
    private Object adType;
    @JsonProperty("ad_url")
    private Object adUrl;
    @JsonProperty("title")
    private String title;
    @JsonProperty("description")
    private String description;
    @JsonProperty("name")
    private String name;
    @JsonProperty("type")
    private String type;
    @JsonProperty("width")
    private Integer width;
    @JsonProperty("height")
    private Integer height;
    @JsonProperty("size")
    private Integer size;
    @JsonProperty("views")
    private Integer views;
    @JsonProperty("section")
    private Object section;
    @JsonProperty("vote")
    private Object vote;
    @JsonProperty("bandwidth")
    private Integer bandwidth;
    @JsonProperty("animated")
    private Boolean animated;
    @JsonProperty("favorite")
    private Boolean favorite;
    @JsonProperty("in_gallery")
    private Boolean inGallery;
    @JsonProperty("in_most_viral")
    private Boolean inMostViral;
    @JsonProperty("has_sound")
    private Boolean hasSound;
    @JsonProperty("is_ad")
    private Boolean isAd;
    @JsonProperty("nsfw")
    private Object nsfw;
    @JsonProperty("link")
    private String link;
    @JsonProperty("tags")
    private List<Object> tags = null;
    @JsonProperty("datetime")
    private Integer datetime;
    @JsonProperty("mp4")
    private String mp4;
    @JsonProperty("hls")
    private String hls;

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("deletehash")
    public String getDeletehash() {
        return deletehash;
    }

    @JsonProperty("deletehash")
    public void setDeletehash(String deletehash) {
        this.deletehash = deletehash;
    }

    @JsonProperty("account_id")
    public Object getAccountId() {
        return accountId;
    }

    @JsonProperty("account_id")
    public void setAccountId(Object accountId) {
        this.accountId = accountId;
    }

    @JsonProperty("account_url")
    public Object getAccountUrl() {
        return accountUrl;
    }

    @JsonProperty("account_url")
    public void setAccountUrl(Object accountUrl) {
        this.accountUrl = accountUrl;
    }

    @JsonProperty("ad_type")
    public Object getAdType() {
        return adType;
    }

    @JsonProperty("ad_type")
    public void setAdType(Object adType) {
        this.adType = adType;
    }

    @JsonProperty("ad_url")
    public Object getAdUrl() {
        return adUrl;
    }

    @JsonProperty("ad_url")
    public void setAdUrl(Object adUrl) {
        this.adUrl = adUrl;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("width")
    public Integer getWidth() {
        return width;
    }

    @JsonProperty("width")
    public void setWidth(Integer width) {
        this.width = width;
    }

    @JsonProperty("height")
    public Integer getHeight() {
        return height;
    }

    @JsonProperty("height")
    public void setHeight(Integer height) {
        this.height = height;
    }

    @JsonProperty("size")
    public Integer getSize() {
        return size;
    }

    @JsonProperty("size")
    public void setSize(Integer size) {
        this.size = size;
    }

    @JsonProperty("views")
    public Integer getViews() {
        return views;
    }

    @JsonProperty("views")
    public void setViews(Integer views) {
        this.views = views;
    }

    @JsonProperty("section")
    public Object getSection() {
        return section;
    }

    @JsonProperty("section")
    public void setSection(Object section) {
        this.section = section;
    }

    @JsonProperty("vote")
    public Object getVote() {
        return vote;
    }

    @JsonProperty("vote")
    public void setVote(Object vote) {
        this.vote = vote;
    }

    @JsonProperty("bandwidth")
    public Integer getBandwidth() {
        return bandwidth;
    }

    @JsonProperty("bandwidth")
    public void setBandwidth(Integer bandwidth) {
        this.bandwidth = bandwidth;
    }

    @JsonProperty("animated")
    public Boolean getAnimated() {
        return animated;
    }

    @JsonProperty("animated")
    public void setAnimated(Boolean animated) {
        this.animated = animated;
    }

    @JsonProperty("favorite")
    public Boolean getFavorite() {
        return favorite;
    }

    @JsonProperty("favorite")
    public void setFavorite(Boolean favorite) {
        this.favorite = favorite;
    }

    @JsonProperty("in_gallery")
    public Boolean getInGallery() {
        return inGallery;
    }

    @JsonProperty("in_gallery")
    public void setInGallery(Boolean inGallery) {
        this.inGallery = inGallery;
    }

    @JsonProperty("in_most_viral")
    public Boolean getInMostViral() {
        return inMostViral;
    }

    @JsonProperty("in_most_viral")
    public void setInMostViral(Boolean inMostViral) {
        this.inMostViral = inMostViral;
    }

    @JsonProperty("has_sound")
    public Boolean getHasSound() {
        return hasSound;
    }

    @JsonProperty("has_sound")
    public void setHasSound(Boolean hasSound) {
        this.hasSound = hasSound;
    }

    @JsonProperty("is_ad")
    public Boolean getIsAd() {
        return isAd;
    }

    @JsonProperty("is_ad")
    public void setIsAd(Boolean isAd) {
        this.isAd = isAd;
    }

    @JsonProperty("nsfw")
    public Object getNsfw() {
        return nsfw;
    }

    @JsonProperty("nsfw")
    public void setNsfw(Object nsfw) {
        this.nsfw = nsfw;
    }

    @JsonProperty("link")
    public String getLink() {
        return link;
    }

    @JsonProperty("link")
    public void setLink(String link) {
        this.link = link;
    }

    @JsonProperty("tags")
    public List<Object> getTags() {
        return tags;
    }

    @JsonProperty("tags")
    public void setTags(List<Object> tags) {
        this.tags = tags;
    }

    @JsonProperty("datetime")
    public Integer getDatetime() {
        return datetime;
    }

    @JsonProperty("datetime")
    public void setDatetime(Integer datetime) {
        this.datetime = datetime;
    }

    @JsonProperty("mp4")
    public String getMp4() {
        return mp4;
    }

    @JsonProperty("mp4")
    public void setMp4(String mp4) {
        this.mp4 = mp4;
    }

    @JsonProperty("hls")
    public String getHls() {
        return hls;
    }

    @JsonProperty("hls")
    public void setHls(String hls) {
        this.hls = hls;
    }

}
