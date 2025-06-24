package com.erp.Common.Elasticsearch;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

@Configuration
public class ElasticsearchConfig
//		extends AbstractElasticsearchConfiguration
{

//	@Override
//	public RestHighLevelClient elasticsearchClient() {
//		String ipAddress = "15.235.12.19";
//		int port = 9201;  // Replace with the desired Elasticsearch port
//
//		ClientConfiguration clientConfiguration = ClientConfiguration.builder()
//				.connectedTo(ipAddress + ":" + port)
//				.build();
//
//		return RestClients.create(clientConfiguration).rest();
//	}
}
