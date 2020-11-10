package com.fg.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserConstants {

	public static String X_FIS_IDP_TRANSACTION_ID="X_FIS_Idp_Transaction_ID";
	public static final String X_WP_COORELATION_ID = "X-request-claims";
	public static final String X_WP_COORELATION_REX = "X-WP-CorrelationId";
	public static final String X_API_KEY = "X_API_KEY";
	public static final String X_SUN_GARD_IDP_API_KEY="X-SunGard-Idp-API-key";
	
	public static final String VALIDATE_ACCESS_TOKEN="/idptoken/validatetoken";
	
	
	

}
