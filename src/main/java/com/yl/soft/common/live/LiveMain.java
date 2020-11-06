package com.yl.soft.common.live;

import java.util.Collections;
import java.util.HashMap;

import org.springframework.stereotype.Component;

import com.huaweicloud.sdk.core.ClientBuilder;
import com.huaweicloud.sdk.core.HcClient;
//用户身份认证
import com.huaweicloud.sdk.core.auth.BasicCredentials;
//请求异常类
import com.huaweicloud.sdk.core.http.FieldExistence;
//Http 配置
import com.huaweicloud.sdk.core.http.HttpConfig;
import com.huaweicloud.sdk.core.http.HttpMethod;
import com.huaweicloud.sdk.core.http.HttpRequestDef;
import com.huaweicloud.sdk.core.http.LocationType;
//导入live的客户端
import com.huaweicloud.sdk.live.v1.LiveAPIClient;

import cn.hutool.core.lang.UUID;

@Component
public class LiveMain {

	static class MyClient extends LiveAPIClient {
		public MyClient(HcClient hcClient) {
			super(hcClient);
		}

		public static ClientBuilder<MyClient> newMyBuilder() {
			return new ClientBuilder<>(MyClient::new);
		}

		public CreateRecTplResponse createRecTpl(TTT request, HttpRequestDef<TTT, CreateRecTplResponse> tttt) {
			return hcClient.syncInvokeHttp(request, tttt);
		}
	}

	public CreateRecTplResponse crateSta(String streamName) {
		// 使用默认配置
		HttpConfig config = HttpConfig.getDefaultHttpConfig();
		BasicCredentials credentials = new BasicCredentials().withAk("XWJ5DWSCZOYSSRDW2PBN")
				.withSk("8zQ7CRk4ZC9qCCWofQhkj0GDBpCZkngnOjlQSRMV").withProjectId("09df8255010025212fa9c006d6e47df7");
		MyClient liveClient = MyClient.newMyBuilder().withHttpConfig(config).withCredential(credentials)
				.withEndpoint("https://live.cn-north-4.myhuaweicloud.com").build();
		CreateRecTplRequest c = new CreateRecTplRequest();
		// c.setDomain("rqyz.live.duoka361.com");
		c.setApp_name("rqyz");
		c.setStream(streamName);
		c.setPush_domain("rqyz.tlive.duoka361.com");
		c.setRecord_type("CONTINUOUS_RECORD");
		c.setDefault_record_config(new HashMap() {
			{
				put("mp4_config", new HashMap<String, Object>() {
					{
						put("record_cycle", 10800);
						put("record_max_duration_to_merge_file", 5);
						put("record_prefix",
								"Record/{publish_domain}/{app}/{record_type}/{record_format}/{stream}_{file_start_time}/{stream}_{file_start_time}");
					}
				});

				put("obs_addr", new HashMap<String, Object>() {
					{
						put("bucket", "rqyz");
						put("location", "cn-north-4");
						put("object", "/");
					}
				});

				put("record_format", Collections.singletonList("MP4"));

			}
		});
		c.setPush_domain("rqyz.tlive.duoka361.com");
		TTT ttt = new TTT();
		ttt.setBody(c);
		CreateRecTplResponse CreateRecTplResponse = liveClient.createRecTpl(ttt, test());
		return CreateRecTplResponse;
	}

	public static void main(String[] args) {
		// 使用默认配置
		HttpConfig config = HttpConfig.getDefaultHttpConfig();
		BasicCredentials credentials = new BasicCredentials().withAk("XWJ5DWSCZOYSSRDW2PBN")
				.withSk("8zQ7CRk4ZC9qCCWofQhkj0GDBpCZkngnOjlQSRMV").withProjectId("09df8255010025212fa9c006d6e47df7");
		MyClient liveClient = MyClient.newMyBuilder().withHttpConfig(config).withCredential(credentials)
				.withEndpoint("https://live.cn-north-4.myhuaweicloud.com").build();
		CreateRecTplRequest c = new CreateRecTplRequest();
		// c.setDomain("rqyz.live.duoka361.com");
		c.setApp_name("rqyz");
		c.setStream(UUID.randomUUID().toString());
		c.setPush_domain("rqyz.tlive.duoka361.com");
		c.setRecord_type("CONTINUOUS_RECORD");
		c.setDefault_record_config(new HashMap() {
			{
				put("mp4_config", new HashMap<String, Object>() {
					{
						put("record_cycle", 10800);
						put("record_max_duration_to_merge_file", 60);
						put("record_prefix",
								"Record/{publish_domain}/{app}/{record_type}/{record_format}/{stream}_{file_start_time}/{stream}_{file_start_time}");
					}
				});

				put("obs_addr", new HashMap<String, Object>() {
					{
						put("bucket", "rqyz");
						put("location", "cn-north-4");
						put("object", "/");
					}
				});

				put("record_format", Collections.singletonList("MP4"));

			}
		});
		c.setPush_domain("rqyz.tlive.duoka361.com");
		TTT ttt = new TTT();
		ttt.setBody(c);
		CreateRecTplResponse CreateRecTplResponse = liveClient.createRecTpl(ttt, test());
		System.out.print(CreateRecTplResponse.toString());

	}

	private static HttpRequestDef<TTT, CreateRecTplResponse> test() {
		// basic
		HttpRequestDef.Builder<TTT, CreateRecTplResponse> builder = HttpRequestDef
				.builder(HttpMethod.POST, TTT.class, CreateRecTplResponse.class)
				.withUri("/v1/{project_id}/record/rules").withContentType("application/json; charset=UTF-8");

//		builder.withRequestField("domain", LocationType.Query, FieldExistence.NON_NULL_NON_EMPTY, String.class,
//				f -> f.withMarshaller(CreateRecTplRequest::getDomain, (req, v) -> {
//					req.setDomain(v);
//				}));

		builder.withRequestField("body", LocationType.Body, FieldExistence.NON_NULL_NON_EMPTY,
				CreateRecTplRequest.class, f -> f.withMarshaller(TTT::getBody, (req, v) -> {
					req.setBody(v);
				}));

//		builder.withRequestField("app", LocationType.Body, FieldExistence.NON_NULL_NON_EMPTY, String.class,
//				f -> f.withMarshaller(CreateRecTplRequest::getApp_name, (req, v) -> {
//					req.setApp_name(v);
//				}));
//
//		builder.withRequestField("default_record_config", LocationType.Body, FieldExistence.NON_NULL_NON_EMPTY,
//				Map.class, f -> f.withMarshaller(CreateRecTplRequest::getDefault_record_config, (req, v) -> {
//					req.setDefault_record_config(v);
//				}));
//
//		builder.withRequestField("push_domain", LocationType.Body, FieldExistence.NON_NULL_NON_EMPTY, String.class,
//				f -> f.withMarshaller(CreateRecTplRequest::getPush_domain, (req, v) -> {
//					req.setPush_domain(v);
//				}));
//
//		builder.withRequestField("record_type", LocationType.Body, FieldExistence.NON_NULL_NON_EMPTY, String.class,
//				f -> f.withMarshaller(CreateRecTplRequest::getRecord_type, (req, v) -> {
//					req.setRecord_type(v);
//				}));
//		builder.withRequestField("stream", LocationType.Body, FieldExistence.NON_NULL_NON_EMPTY, String.class,
//				f -> f.withMarshaller(CreateRecTplRequest::getStream, (req, v) -> {
//					req.setStream(v);
//				}));

		return builder.build();
	}

}