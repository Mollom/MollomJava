package com.mollom.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Defines the content entity.
 *
 * Each entity typically represents a POST request of a user.
 *
 * @see http://mollom.com/api#content
 */
@XmlRootElement(name = "content")
@XmlAccessorType(XmlAccessType.FIELD)
public class Content implements Serializable{
  private static final long serialVersionUID = 1L;
  /**
   * Content ID is a unique identifier to Mollom.
   */
  private String id;

  /**
   * Fields injected by Mollom.
   */
  private double spamScore;
  private String spamClassification;
  private double profanityScore;
  private double qualityScore;

  @XmlElementWrapper(name = "languages")
  @XmlElement(name = "language")
  private Language[] languages;

  private String reason;

  /**
   * Fields used for classification; set by the client.
   */
  private String authorIp;
  private String authorId;
  private String[] authorOpenIds;
  private String authorName;
  private String authorMail;
  private String authorUrl;
  private String honeypot;

  private String postTitle;
  private String postBody;
  // Absolute URL to the parent/context page of the checked content.
  private String contextUrl;
  // The title of the parent/context page of the checked content.
  private String contextTitle;

  /**
   * Fields used to control Mollom's behavior.
   */
  private Check[] checks;
  private boolean allowUnsure;
  private Strictness strictness;
  private ContentType type;

  /**
   * Fields to inform Mollom about locally stored content.
   */
  private int stored;
  // Absolute URL to the stored content.
  private String url;

  /**
   * Constructs a new Content to be checked by Mollom.
   */
  public Content() {
    allowUnsure = true;
    strictness = Strictness.NORMAL;
    checks = new Check[] { Check.SPAM };
    spamScore = -1;
    profanityScore = -1;
    qualityScore = -1;
    stored = -1;
  }

  /**
   * @return Unique content ID assigned by Mollom.
   */
  public String getId() {
    return id;
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

  public String getPostTitle() {
    return postTitle;
  }

  public String getPostBody() {
    return postBody;
  }

  public String getContextUrl() {
    return contextUrl;
  }

  public String getContextTitle() {
    return contextTitle;
  }

  public Check[] getChecks() {
    return checks;
  }

  /**
   * @return The determined spam score of the content.
   *   Values are floating point values with a precision of 2, ranging between
   *   0.00 and 1.00:
   *   - 0.0 means ham
   *   - 0.5 means unsure
   *   - 1.0 means spam
   *   Depending on the Mollom subscription plan, the returned scores can be
   *   real values between 0 and 1.
   *
   * @throws MollomException
   *   If a SPAM check was not performed beforehand.
   */
  public double getSpamScore() {
    if (spamScore == -1) {
      throw new MollomIllegalUsageException("Spam score cannot be determined without doing a SPAM check first.");
    }
    return spamScore;
  }

  public String getSpamClassification() {
    return spamClassification;
  }

  /**
   * @return The determined profanity score of the content.
   *   Values are floating point values with a precision of 2, ranging between
   *   0.00 and 1.00:
   *   - 0.0 means not profane
   *   - 1.0 means profane
   *   Depending on the Mollom subscription plan, the returned scores can be
   *   real values between 0 and 1.
   *
   * @throws MollomException
   *   If a PROFANITY check was not performed beforehand.
   */
  public double getProfanityScore() {
    if (profanityScore == -1) {
      throw new MollomIllegalUsageException("Profanity score cannot be determined without doing a PROFANITY check first.");
    }
    return profanityScore;
  }

  /**
   * @return The determined quality of the content.
   *   Values are floating point values with a precision of 2, ranging between
   *   0.00 and 1.00:
   *   - 0.0 means low quality content
   *   - 1.0 means high quality content
   *   Depending on the Mollom subscription plan, the returned scores can be
   *   real values between 0 and 1.
   *
   * @throws MollomException
   *   If a QUALITY check was not performed beforehand.
   */
  public double getQualityScore() {
    if (qualityScore == -1) {
      throw new MollomIllegalUsageException("Quality score cannot be determined without doing a QUALITY check first.");
    }
    return qualityScore;
  }

  /**
   * @return Detected languages for the content.
   * @throws MollomException
   *   If a LANGUAGE check was not performed beforehand.
   */
  public Language[] getLanguages() {
    if (languages == null) {
      throw new MollomIllegalUsageException("Language cannot be determined without doing a LANGUAGES check first.");
    }
    return languages;
  }

  /**
   * @return A single-word string denoting the reason for the content classification.
   */
  public String getReason() {
    return reason;
  }

  /**
   * @return Whether the content is "ham" or desirable.
   * @throws MollomException
   *   If a SPAM check was not performed beforehand.
   */
  public boolean isHam() {
    if (spamClassification == null) {
      throw new MollomIllegalUsageException("Spam classification cannot be determined without doing a SPAM check first.");
    }
    return "ham".equals(spamClassification);
  }

  /**
   * @return Whether the content is "spam" or undesirable.
   * @throws MollomException
   *   If a SPAM check was not performed beforehand.
   */
  public boolean isSpam() {
    if (spamClassification == null) {
      throw new MollomIllegalUsageException("Spam classification cannot be determined without doing a SPAM check first.");
    }
    return "spam".equals(spamClassification);
  }

  /**
   * If the Content is determined to be unsure, it is recommended that a CAPTCHA is presented to the user.
   *
   * @return Whether the content is "unsure" or Mollom isn't sure whether it is desirable or not.
   * @throws MollomException
   *   If a SPAM check was not performed beforehand.
   */
  public boolean isUnsure() {
    if (spamClassification == null) {
      throw new MollomIllegalUsageException("Spam classification cannot be determined without doing a SPAM check first.");
    }
    return "unsure".equals(spamClassification);
  }

  public Strictness getStrictness() {
    return strictness;
  }

  public ContentType getType() {
    return type;
  }

  public int getStored() {
    return stored;
  }

  public String getUrl() {
    return url;
  }

  void setId(String id) {
    this.id = id;
  }

  /**
   * @param authorIp The IP address of the content author.
   */
  public void setAuthorIp(String authorIp) {
    this.authorIp = authorIp;
  }

  /**
   * @param authorId The local user ID on the client site of the content author.
   */
  public void setAuthorId(String authorId) {
    this.authorId = authorId;
  }

  /**
   * @param authorOpenIds Open IDs of the content author
   */
  public void setAuthorOpenIds(String[] authorOpenIds) {
    this.authorOpenIds = authorOpenIds;
  }

  /**
   * @param authorName The name of the content author.
   */
  public void setAuthorName(String authorName) {
    this.authorName = authorName;
  }

  /**
   * @param authorMail The email address of the content author.
   */
  public void setAuthorMail(String authorMail) {
    this.authorMail = authorMail;
  }

  /**
   * @param authorUrl The homepage/website URL of the content author.
   */
  public void setAuthorUrl(String authorUrl) {
    this.authorUrl = authorUrl;
  }

  /**
   * The value of a client-side honeypot form element, if non-empty.
   * The honeypot is a trap to detect automated attackers and will generally result in a
   * SPAM classification if filled out.
   */
  public void setHoneypot(String honeypot) {
    this.honeypot = honeypot;
  }

  /**
   * @param postTitle The title of the content.
   */
  public void setPostTitle(String postTitle) {
    this.postTitle = postTitle;
  }

  /**
   * @param postBody The body of the content.
   */
  public void setPostBody(String postBody) {
    this.postBody = postBody;
  }

  public void setContextUrl(String contextUrl) {
    this.contextUrl = contextUrl;
  }

  public void setContextTitle(String contextTitle) {
    this.contextTitle = contextTitle;
  }

  /**
   * Configure the checks to request Mollom to perform. Defaults to spam only.
   */
  public void setChecks(Check... checks) {
    this.checks = checks;
  }

  /**
   * Whether or not to allow Mollom to return an unsure classification.
   * Defaults to true. (highly recommended)
   */
  public void setAllowUnsure(boolean allowUnsure) {
    this.allowUnsure = allowUnsure;
  }

  public boolean isAllowUnsure() {
    return allowUnsure;
  }

  void setSpamScore(double spamScore) {
    this.spamScore = spamScore;
  }

  void setSpamClassification(String spamClassification) {
    this.spamClassification = spamClassification;
  }

  void setProfanityScore(double profanityScore) {
    this.profanityScore = profanityScore;
  }

  void setQualityScore(double qualityScore) {
    this.qualityScore = qualityScore;
  }

  void setLanguages(Language[] languages) {
    this.languages = languages;
  }

  void setReason(String reason) {
    this.reason = reason;
  }

  /**
   * Configure the strictness of Mollom spaminess checks.
   */
  public void setStrictness(Strictness strictness) {
    this.strictness = strictness;
  }

  /**
   * Set the content type of this piece of content. Defaults to CONTENT, which is the standard
   * behavior. Currently, the only other supported ContentType is USER for user registration forms.
   */
  public void setType(ContentType type) {
    this.type = type;
  }

  /**
   * Informs Mollom whether the content was accepted/stored on the client site.
   *
   * Only set a value when a storage change occurred:
   * - Only set to true after accepting/storing the content.
   * - Only set to false when the locally stored content is deleted.
   *
   * Do not set a value for in-progress form submissions that have not been
   * stored yet.
   */
  public void setStored(boolean stored) {
    this.stored = stored ? 1 : 0;
  }

  public void setUrl(String url) {
    this.url = url;
  }
}
