package com.peoni.project.dto.board;

import java.net.URLEncoder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardImageDTO {

	private Long imageId;
	private String uuid;
	private String imageName;
	private String path;
	
	public String getImageURL() {
		try {
			String filename = URLEncoder.encode(uuid + "_" + imageName, "UTF-8").replace("+", "%20");
			return "/upload/board/" + path + "/" + filename;
		} catch (Exception e) {
			return "";
		}
	}
	
	public String getThumbnailURL() {
		try {
			String filename = URLEncoder.encode("s_" + uuid + "_" + imageName, "UTF-8").replace("+", "%20");
			return "/upload/board/" + path + "/" + filename;
		} catch (Exception e) {
			return "";
		}
	}
}
