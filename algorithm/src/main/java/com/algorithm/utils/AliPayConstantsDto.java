package com.algorithm.utils;

import java.io.Serializable;

/**
 * @Author: TaoHaiQing
 * @Date: 2018/9/20 15:43
 */
public class AliPayConstantsDto implements Serializable {

	//appid
    public static final String appId = "2018051060108265";
    //密钥
    public static final String merchantPrivateKey = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCHsekbcW1Lf5/dYO0RRh5UVF+y331FOaQdFScEhxpIzkDzLI6vkjSk5R2trhfl+z67ImVp5kTI1mN+tbnqYn4/eX+I1DAFDJKllI4mJu3XBahLw72zntk/aqpeo0UyQ5tBXx4SkE+akszZHl/Pra03c7nY5SxblY8G9Dr5vLpkTE3QeTriXa0tFkcf4dp0aqBYn0XFrieVYG3Yai70jq/wiEiI4xru2hDWhr3cVOD872ZU/kVzMV74yGdU5rOXQt2RxpaHdZFxbYy+wu4jKGMtbfI481B6gEtNb5jyyEYKpa/XgwxWTcfuj/MRsLwAMiV66U6ho34ba+NTiIvwYXWXAgMBAAECggEAaOoukJBXb8tCYFjjkrqDErKVt2hCo/ZYJEBJhZ+ru+T0pM+rlqXVXDTliHaVdbSXIPsMLB/kyN+x17NMBqOWdx9KcxGT+yNocut0KJ1qwRhXshSqpKlJ0VV4WmmkQ6Ov85BGYRYA5kNgbPKmI51l0v0AEWnHrRvqlPbabmL0Pjz27IlWeGF03b258EpViNuX18xMsSQ5BJ8LZh0g8cWXDlusvCwwPbvtTp84iCd7Us2CX7JOrw/UFZIUD3r6brg6jk2nd7oJP4E/J4boKD0yri2GggLtcgBfGr5b8ql9h5OmFaYxeBEFhY/ZI9TjGY4+CQpYzA+WdDnUXzMXVJOXKQKBgQDjfZWSfQ0Ib4idmGR2rr6LpOXXi9mEUYFxEA7QkCgmHXEhhgE1EfD2gVJl991ReGYCeOmYf3UMUS579Zn4L7KF+YGIGYqetPbZHafrz0HQJg/RxkltSYiH8i5lmIdwMD11Or/83KgTantKDE2OpcRXb23PwQovB53QR/57MAIpjQKBgQCYs1ByX79zwNl2RQGsH60tv7ZlkwXHmfCjxGNleIfmH5t78xmH14YpDqXYY2DSxB/fvl7R20Z9W+ITqSXFGesVVR/9UhkKHr3agIieP3uihFUyMEKnUB7gb7Qid85xR1d+I3ej/5lMjIA30B83Hzhwx6Jtvkb+8LiYL3+f+loIswKBgHa1E5WrN1/Ng4Zo/ROJP9jypRUC3vBS766l6HUgpDKvbldF699bi1e2R975pj1bDDACFDMFX3ib7Bu3Hy8JBKkwd00jttf2KlFFTliYgAkzs1GNnMcwddQqpddR7mq2YXuIuqIiKnpnOAp9vQLQsVHGzZB5dOzxloIW704s0RQJAoGAHXQQ407GnewnxeE/llSx0a4131fBeKOfv+bkiTD7fY7BLeJRHVub/BVgNeHNTjoYL88HeREDgrCTZdlteJwsNPwrZHBOdh5B3oCGWYfDMDC4Qir+4Eja+81wAGn9fuoU3Ta4IADwL8y1+o4BR1+VQmOvV92o0gnagThANLpUXXMCgYBdqGRGqo8rX6elbgp/O+7RwYAmcYEqiy7JtDoekXUfO0A1k/lbSFVulZGMoqc7sw6mr5SNhHMBMQZ8GRcPC8//Z7kS4rjddu0XhJhn5ZhAyq2Q9djr47uLK3ULLS6KikzoH7WyPPaaf5xlWVhROCY6QJ88UV2svJMSX/iLKzfWnQ==";
    //公钥
    public static final String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAz86Fddr8YR7/+AWzJMYi4G6sxbz1zRkDMWJ3UrBbEbCNKTIeM6j9YTdFpejuLHk4OCibIfCp37k50rB8obV9rkdo9my61j0yfQDWKTla3cZxjTsSUK3olZwZfhUgqC/BbLTjdUjpkh4nIAaKbVfyargPKaxDMvLXk/T9HncdK7q0Kk1EXFaYNmV4h+iL6pDpt1zFGgjBzQA2VmEjgd921Q09n5r3QPgzUXDXUSd525GnUBUsg71GI7oHFkKBUEYsNWEcu/QlcVNbwgd3vlCE/TMZkBWpoG6GXX8CeGZ81aqFRagZIICsOAjf0tStyGjMZ0aHVczwHo3DHIY/JStF7QIDAQAB";
    //支付成功跳转url
    public static final String returnUrl = "";
    //支付成功通知url
    public static final String notifyUrl = "";
    //签名类型
    public static final String signType = "RSA2";
    //字符集
    public static final String charset = "UTF-8";
    //请求支付宝url
    public static final String gatewayUrl = "https://openapi.alipay.com/gateway.do";
    //具体请求接口方法
    public static final String method = "alipay.trade.precreate";
    
    
}
