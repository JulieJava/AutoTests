
package dto;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "data",
    "success",
    "status"
})
@Generated("jsonschema2pojo")
public class GetImageByIdResponseDto {

    @JsonProperty("data")
    private ImageByIdData data;
    @JsonProperty("success")
    private Boolean success;
    @JsonProperty("status")
    private Integer status;

    @JsonProperty("data")
    public ImageByIdData getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(ImageByIdData data) {
        this.data = data;
    }

    @JsonProperty("success")
    public Boolean getSuccess() {
        return success;
    }

    @JsonProperty("success")
    public void setSuccess(Boolean success) {
        this.success = success;
    }

    @JsonProperty("status")
    public Integer getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(Integer status) {
        this.status = status;
    }

}
