package enums;

public enum NewsRSSFeedEnum { 
    // �Ż�ͷ������ RSS ����
    YAHOO_TOP_STORIES("http://rss.news.yahoo.com/rss/topstories"), 
    
    //CBS ͷ������ RSS ����
    CBS_TOP_STORIES("http://feeds.cbsnews.com/CBSNewsMain?format=xml"), 
    
    // ��ɼ�ʱ��ͷ������ RSS ����
    LATIMES_TOP_STORIES("http://feeds.latimes.com/latimes/news?format=xml"); 
        
    // ö�ٶ���� RSS ��ַ������
    private String rss_url; 
        
    // ö�ٶ����캯��
    private NewsRSSFeedEnum(String rss) { 
        this.rss_url = rss; 
    } 
        
    // ö�ٶ����ȡ RSS ��ַ�ķ���
    public String getRssURL() { 
        return this.rss_url; 
    } 
 }
