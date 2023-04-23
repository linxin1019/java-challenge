package jp.co.axa.apidemo.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@ApiModel(value = "parameters for paging queries")
@AllArgsConstructor
public class PageInfo {
    @ApiModelProperty(value = "page number", required = true, dataType = "int")
    private int page;

    @ApiModelProperty(value = "size of the page", required = true, dataType = "int")
    private int size;
}
