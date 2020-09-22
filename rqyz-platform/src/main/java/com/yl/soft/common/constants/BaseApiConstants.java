package com.yl.soft.common.constants;

public interface BaseApiConstants extends Constants{
    /**
     * 服务结果码
     */
    enum ServiceResultCode {
        SYS_ERROR(-1,"系统繁忙"),
        ERROR(500,"fail"),
        SUCCESS(200,"succcess"),
        ;

        private Integer code;
        private String value;

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        private ServiceResultCode(Integer code, String value){
            this.code = code;
            this.value = value;
        }
    }
}
