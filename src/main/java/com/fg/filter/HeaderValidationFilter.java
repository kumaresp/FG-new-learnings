package com.fg.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.fg.constants.UserConstants;
import com.fg.errorHandling.HeaderValidationFailedExcpetion;

public class HeaderValidationFilter 

{}
/*extends OncePerRequestFilter {


	private final HandlerExceptionResolver resolver;
	
	public HeaderValidationFilter(HandlerExceptionResolver resolver) {
		this.resolver=resolver;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
	
		
		try {
			validateCommonHeaders(request);
		} catch (Exception ex) {
			resolver.resolveException(request, response, "", ex);
		}
		
	}

	private void validateCommonHeaders(HttpServletRequest request) {
		checkAndFetch(UserConstants.X_WP_COORELATION_ID,true, request,UserConstants.X_WP_COORELATION_REX);
		checkAndFetch(HttpHeaders.AUTHORIZATION,true, request,UserConstants.X_WP_COORELATION_REX);
		checkAndFetch(UserConstants.X_API_KEY,true, request,UserConstants.X_WP_COORELATION_REX);

	}

	private void checkAndFetch(String name, boolean isRequired, HttpServletRequest request,
			String rex) {
	
		if(isRequired && StringUtils.isEmpty(request.getHeader(name))) {
			throw new HeaderValidationFailedExcpetion(name,true);
		}
		
	}

}
*/