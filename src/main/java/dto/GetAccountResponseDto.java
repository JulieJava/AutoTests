
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
public class GetAccountResponseDto {

    @JsonProperty("data")
    private AccountData data;
    @JsonProperty("success")
    private Boolean success;
    @JsonProperty("status")
    private Integer status;

    @JsonProperty("data")
    public AccountData getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(AccountData data) {
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
