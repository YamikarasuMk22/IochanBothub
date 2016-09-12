package org.soichiro.charactorbot.server;

import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import org.soichiro.charactorbot.client.CPost;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

/**
 * Twitter Post Contents Data Class (Post by textfile).
 * @author YamikarasuMk22
 *
 */
public class FPost {

	@PrimaryKey
	private Key key;

	/** Parent Keyword */
	@Persistent
	private Keyword keyword;

    @Persistent
    private Integer sequence;

    @Persistent
    private String message;

    @Persistent
    private Integer count;


    /**
     * Constructor
     */
    public FPost() {
	}

	/**
	 * @return the key
	 */
	public Key getKey() {
		return key;
	}

	/**
	 * @return the keyword
	 */
	public Keyword getKeyword() {
		return keyword;
	}

	/**
	 * @param keyword the keyword to set
	 */
	public void setKeyword(Keyword keyword) {
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
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the count
	 */
	public Integer getCount() {
		return count;
	}

	/**
	 * @param count the count to set
	 */
	public void setCount(Integer count) {
		this.count = count;
	}


	/**
	 * Create client side data.
	 * @param ori
	 * @return client side data.
	 */
	public static CPost createClientSideDate(FPost ori)
	{
		CPost clientData = new CPost();
		clientData.setKey(KeyFactory.keyToString(ori.key));
		clientData.setKeyword(KeyFactory.keyToString(ori.getKeyword().getKey()));
		clientData.setMessage(ori.getMessage());
		clientData.setSequence(ori.getSequence());
		clientData.setCount(ori.getCount());
		return clientData;
	}
}
