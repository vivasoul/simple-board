package com.odth.common.view;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;

public class FixedUrlBasedView extends AbstractView {
	
	private String url;

	public FixedUrlBasedView () {
		this(null);
	}
	
	public FixedUrlBasedView(String url) {
		this.url = url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getUrl() {
		return this.url;
	}
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		request.getRequestDispatcher(url).forward(request, response);;
	}	
}
