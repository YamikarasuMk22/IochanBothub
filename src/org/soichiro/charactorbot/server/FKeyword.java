package org.soichiro.charactorbot.server;

import java.util.List;

import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import org.soichiro.charactorbot.client.CKeyword;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

/**
 * Twitter Bot Keyword Data Class (Post by textfile).
 * @author YamikarasuMk22
 *
 */
public class FKeyword {

	@PrimaryKey
	private Key key;

	/** Parent PostType */
	@Persistent
	private PostType postType;

	@Persistent
	private List<Post> listPost;

	@Persistent
	private String keyword;

    @Persistent
    private Integer sequence;

    @Persistent
    private Boolean isRegex;

	@Persistent
	private Boolean isActivated;

	/**
	 * @return the key
	 */
	public Key getKey() {
		return key;
	}

	/**
	 * @return the postType
	 */
	public PostType getPostType() {
		return postType;
	}

	/**
	 * @param postType the postType to set
	 */
	public void setPostType(PostType postType) {
		this.postType = postType;
	}

	/**
	 * @return the listPost
	 */
	public List<Post> getListPost() {
		return listPost;
	}

	/**
	 * @param listPost the listPost to set
	 */
	public void setListPost(List<Post> listPost) {
		this.listPost = listPost;
	}

	/**
	 * @return the keyword
	 */
	public String getKeyword() {
		return keyword;
	}

	/**
	 * @param keyword the keyword to set
	 */
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}


	/**
	 * @return the sequence
	 */
	public Integer getSequence() {
		return sequence;
	}

	/**
	 * @param sequence the sequence to set
	 */
	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	/**
	 * @return the isRegex
	 */
	public Boolean getIsRegex() {
		return isRegex;
	}

	/**
	 * @param isRegex the isRegex to set
	 */
	public void setIsRegex(Boolean isRegex) {
		this.isRegex = isRegex;
	}

	/**
	 * @return the isActivated
	 */
	public Boolean getIsActivated() {
		return isActivated;
	}

	/**
	 * @param isActivated the isActivated to set
	 */
	public void setIsActivated(Boolean isActivated) {
		this.isActivated = isActivated;
	}

	/**
	 * Create client side data.
	 * @param ori
	 * @return client side data.
	 */
	public static CKeyword createClientSideDate(FKeyword ori)
	{
		CKeyword clientData = new CKeyword();
		clientData.setKey(KeyFactory.keyToString(ori.key));
		clientData.setPostType(KeyFactory.keyToString(ori.getPostType().getKey()));
		clientData.setKeyword(ori.getKeyword());
		clientData.setSequence(ori.getSequence());
		clientData.setIsRegex(ori.getIsRegex());
		clientData.setIsActivated(ori.getIsActivated());
		return clientData;
	}
}