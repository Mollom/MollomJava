package com.mollom.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Defines the CAPTCHA entity.
 *
 * @see http://mollom.com/api#captcha
 */
@XmlRootElement(name = "captcha")
@XmlAccessorType(XmlAccessType.FIELD)
public class Captcha implements Serializable {
  private static final long serialVersionUID = 1L;
  // Fields injected by Mollom upon creation of the CAPTCHA resource.
  private String id; // CAPTCHA ID
  private String url;

  // Fields injected by the client for verification.
  private String solution;

  private String authorIp;
  private String authorId;
  private String[] authorOpenIds;
  private String authorName;
  private String authorMail;
  private String authorUrl;
  private String honeypot;

  /**
   * Seconds that must have passed by for the same author to post again.
   */
  private int rateLimit;

  // Fields injected by Mollom upon CAPTCHA verification
  private int solved;
  private String reason;

  public Captcha() {
    solved = -1;
    rateLimit = -1;
  }

  public String getId() {
    return id;
  }

  public String getUrl() {
    return url;
  }

  public String getSolution() {
    return solution;
  }

  public String getAuthorIp() {
    return authorIp;
  }

  public String getAuthorId() {
    return authorId;
  }

  public String[] getAuthorOpenIds() {
    return authorOpenIds;
  }

  public String getAuthorName() {
    return authorName;
  }

  public String getAuthorMail() {
    return authorMail;
  }

  public String getAuthorUrl() {
    return authorUrl;
  }

  public String getHoneypot() {
    return honeypot;
  }

  public int getRateLimit() {
    return rateLimit;
  }

  public boolean isSolved() {
    if (solved == -1) {
      throw new MollomIllegalUsageException("Cannot check whether or not a CAPTCHA is solved without calling checkCaptcha first.");
    }
    return solved == 1;
  }

  public String getReason() {
    return reason;
  }

  void setId(String id) {
    this.id = id;
  }

  void setUrl(String url) {
    this.url = url;
  }

  public void setSolution(String solution) {
    this.solution = solution;
  }

  public void setAuthorIp(String authorIp) {
    this.authorIp = authorIp;
  }

  public void setAuthorId(String authorId) {
    this.authorId = authorId;
  }

  public void setAuthorOpenIds(String[] authorOpenIds) {
    this.authorOpenIds = authorOpenIds;
  }

  public void setAuthorName(String authorName) {
    this.authorName = authorName;
  }

  public void setAuthorMail(String authorMail) {
    this.authorMail = authorMail;
  }

  public void setAuthorUrl(String authorUrl) {
    this.authorUrl = authorUrl;
  }

  public void setHoneypot(String honeypot) {
    this.honeypot = honeypot;
  }

  public void setRateLimit(int rateLimit) {
    this.rateLimit = rateLimit;
  }

  void setSolved(int solved) {
    this.solved = solved;
  }

  void setReason(String reason) {
    this.reason = reason;
  }
}
