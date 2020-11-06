package com.yl.soft.common.live;

import java.util.Objects;
import java.util.function.Consumer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.huaweicloud.sdk.live.v1.model.StreamForbiddenSetting;
import com.huaweicloud.sdk.live.v1.model.UpdateStreamForbiddenRequest;

public class TTT  {


    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value="body")
    
    private CreateRecTplRequest body = null;

    public CreateRecTplRequest withBody(CreateRecTplRequest body) {
    	return this.body = body;
    }


    /**
     * Get body
     * @return body
     */
    public CreateRecTplRequest getBody() {
        return body;
    }

    public void setBody(CreateRecTplRequest body) {
        this.body = body;
    }
}