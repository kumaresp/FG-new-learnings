package com.fg.utils;

import com.fg.model.InlineResponse2002;

public class TokensUtils {

	public static InlineResponse2002 getTokenResponse() {

		InlineResponse2002 inlineResponse2002 = new InlineResponse2002();
		inlineResponse2002.setLoginName("DEV_INTUSER_APP");
		inlineResponse2002.setFirmName("poc");
		inlineResponse2002.setExpirysIn("3424234");
		inlineResponse2002.setIssuer("poc");
		inlineResponse2002.setClientId("dashboardint");

		return inlineResponse2002;

	}
}
