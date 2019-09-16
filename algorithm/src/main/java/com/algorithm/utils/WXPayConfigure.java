package com.algorithm.utils;

import com.github.wxpay.sdk.WXPayConfig;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class WXPayConfigure implements WXPayConfig {

    private byte[] certData;

    @Override
    public String getAppID() {
        return "wxeb7ff192488e819f";
    }

    @Override
    public String getMchID() {
        return "1487694892";
    }

    @Override
    public String getKey() {
        return "LenovoConnect2017goahead12345678";
    }

    @Override
    public InputStream getCertStream() {
        ByteArrayInputStream certBis = new ByteArrayInputStream(this.certData);
        return certBis;
    }

    @Override
    public int getHttpConnectTimeoutMs() {
        return 1000;
    }

    @Override
    public int getHttpReadTimeoutMs() {
        return 1000;
    }
}
