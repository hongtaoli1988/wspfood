package com.mainone.model.sfa;
// Generated by MyEclipse - Hibernate Tools

import java.util.Date;


/**
 * SfaLinkManInfo generated by MyEclipse - Hibernate Tools
 */
public class SfaLinkManInfo extends AbstractSfaLinkManInfo implements java.io.Serializable {

    // Constructors

    /** default constructor */
    public SfaLinkManInfo() {
    }

	/** minimal constructor */
    public SfaLinkManInfo(Integer customerId, Integer linkManCategory, String linkManName, Date addDate, Integer linkManType, Integer linkManSex) {
        super(customerId, linkManCategory, linkManName, addDate, linkManType, linkManSex);        
    }
    
    /** full constructor */
    public SfaLinkManInfo(Integer customerId, Integer linkManCategory, String linkManName, String linkManAdd, String linkManTel, String linkManMobile, String linkManFax, String linkManZip, Date addDate, Integer linkManType, String item1, String item2, String linkManQq, String linkManMsn, Date linkManBirthday, Integer linkManSex, String linkManEmail, Integer mobileSplitInfoId) {
        super(customerId, linkManCategory, linkManName, linkManAdd, linkManTel, linkManMobile, linkManFax, linkManZip, addDate, linkManType, item1, item2, linkManQq, linkManMsn, linkManBirthday, linkManSex, linkManEmail, mobileSplitInfoId);        
    }
   
}
