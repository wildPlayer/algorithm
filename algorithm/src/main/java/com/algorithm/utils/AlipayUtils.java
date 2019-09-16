package com.algorithm.utils;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.response.AlipayTradePrecreateResponse;

import java.util.HashMap;
import java.util.Map;

public class AlipayUtils {

    /**
     * 生成支付宝付款二维码URL
     * @param total_amount	支付总金额，单位为：元，二位小数，如：20.55
     * @param subject		产品描述
     * @param tradeId		交易流水号
     * @return
     * 		rescode			结果信息：000表示成功；999表示失败
     * 		resinfo			如果成功，则返回付款的URL；如果失败，返回失败原因
     */
    public static Map<String, String> preCreate(String total_amount, String subject, String tradeId) {
        System.out.println("生成支付宝付款二维码交易开始...");
        System.out.println("params: totalAmount=" + total_amount + ", subject=" + subject + ", tradeId=" + tradeId);
//      AliPayConstantsDto aliPayConstantsDto = orderPayService.queryAliConfig();
        //获得初始化的AlipayClient,请勿更改参数顺序
//      AlipayClient alipayClient = new DefaultAlipayClient(aliPayConstantsDto.getGatewayUrl(), aliPayConstantsDto.getAppId(), aliPayConstantsDto.getMerchantPrivateKey(), "json", aliPayConstantsDto.getCharset(), aliPayConstantsDto.getPublicKey(), aliPayConstantsDto.getSignType());
        AlipayClient alipayClient = new DefaultAlipayClient(AliPayConstantsDto.gatewayUrl, AliPayConstantsDto.appId, AliPayConstantsDto.merchantPrivateKey, "json", AliPayConstantsDto.charset, AliPayConstantsDto.publicKey, AliPayConstantsDto.signType);
        //设置请求参数
        AlipayTradePrecreateRequest alipayRequest = new AlipayTradePrecreateRequest();
        //设置支付宝同步通知地址
        alipayRequest.setReturnUrl(AliPayConstantsDto.returnUrl);
        //设置支付宝异步通知地址
        alipayRequest.setNotifyUrl(AliPayConstantsDto.notifyUrl);
        //以下为用户请求参数，此处为接收来自前台的表单提交的参数
        //商户订单号，商户网站订单系统中唯一订单号，必填
        //付款金额，total_amount 必填
        //交易标题，subject 必填
        //交易描述，body 可空
        //业务请求参数的集合，最大长度不限，除公共参数外所有请求参数都必须放在这个参数中传递
        alipayRequest.setBizContent("{" +
                "\"out_trade_no\":\""+tradeId+"\"," +
                "\"total_amount\":"+total_amount+"," +
                "\"subject\":\""+subject+"\"," +
//      		"\"body\":\"Iphone6 16G\"," +
//      		"\"operator_id\":\"yx_001\"," +
//      		"\"store_id\":\"NJ_001\"," +
//      		"\"disable_pay_channels\":\"pcredit,moneyFund,debitCardExpress\"," +
//      		"\"enable_pay_channels\":\"pcredit,moneyFund,debitCardExpress\"," +
//      		"\"terminal_id\":\"NJ_T_001\"," +
//      		"\"extend_params\":{" +
//      		"\"sys_service_provider_id\":\"2088511833207846\"," +
//      		"\"industry_reflux_info\":\"{\\\\\\\"scene_code\\\\\\\":\\\\\\\"metro_tradeorder\\\\\\\",\\\\\\\"channel\\\\\\\":\\\\\\\"xxxx\\\\\\\",\\\\\\\"scene_data\\\\\\\":{\\\\\\\"asset_name\\\\\\\":\\\\\\\"ALIPAY\\\\\\\"}}\"," +
//      		"\"card_type\":\"S0JP0000\"" +
//      		"    }," +
                "\"timeout_express\":\"30m\"," +
                "\"qr_code_timeout_express\":\"30m\"" +
                "  }");
        //发送请求，支付宝将返回一个支付请求的表单数据串
        Map<String, String> result = null;
        try {
            AlipayTradePrecreateResponse response = alipayClient.execute(alipayRequest);
            if(response.isSuccess()){
                result = new HashMap<String, String>();
                result.put("rescode", "000");
                result.put("resinfo", response.getQrCode());
//       	   	AlipaySignature.rsaCheckV1(params, publicKey, charset, signType)
                System.out.println("支付宝生成二维码预下单完成,状态码{" + response.getCode() + "}，状态信息{" + response.getMsg() + "}");
            }else {
                result = new HashMap<String, String>();
                result.put("rescode", "999");
                result.put("resinfo", "异常码{" + response.getCode() + "}，异常信息{" + response.getMsg() + "}");
                System.out.println("支付宝生成二维码预下单异常,异常码{" + response.getCode() + "}，异常信息{" + response.getMsg() + "}");
            }
        } catch (Exception e) {
            result.put("rescode", "999");
            result.put("resinfo", "支付宝生成二维码预下单异常");
            System.out.println("支付宝生成二维码预下单异常：" + e);
            return result;
        }
        if(result.get("rescode").equals("000")){
            try{
                QRCodeUtils.encode(result.get("resinfo"), "D:\\Template\\20190625\\xxx.jpg", "D:\\Template\\20190625\\alipay.jpg", true, 500);
            } catch(Exception e){
                e.printStackTrace();
            }
        }
        //将返回结果输出至页面，将再次向支付宝发起请求，此次请求将直接跳转至支付宝支付页面
        return result;
    }

    public static void main(String[] args) {
        Map<String, String> test = AlipayUtils.preCreate("0.01", "test", "10220190801110000231548676");
        System.out.println(test);
    }
}
