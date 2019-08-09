package com.oneOsevenForward;

import java.lang.Object;
import java.util.*;
import org.apache.james.core.MailAddress;
import javax.mail.MessagingException;
import org.apache.mailet.Mail;
import org.apache.mailet.base.GenericMailet;
import org.apache.mailet.MailetConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ForwardMail extends GenericMailet{
          private static final Logger logger = LoggerFactory.getLogger(ForwardMail.class);
          private MailetConfig config = null;
	  private Collection<MailAddress> forwardList;
	  private final String[] mailRgstr = {"adnanlutfi92@gmail.com","dewabu1@gmail.com",
	  "yudikarmateknik@gmail.com","meirusfandi100@gmail.com","jason.tjankilisan@gmail.com"};
	  private final String[] userRgstr = {"adnan","fauzan","yudi","mei","jason"};
	  private final String domainRgstr = "107.co.id";

          @Override
          public void service(Mail mail) throws MessagingException {
		forwardList = new LinkedList<MailAddress>();
		String sendedMail="";
		sendedMail = mail.getRecipients().toString();
		try{
			Collection test = mail.getRecipients();
			logger.debug("Recipient to be checked for string : {} ",sendedMail);
			logger.debug("Recipient to be checked for collection : {} ",test);
		}catch(Exception e){
			logger.error(e.getMessage());
			logger.error("No recipient were found or failed copying");
		}
		int sIndex=0;
		while(sIndex != userRgstr.length)
		{
		   logger.debug("Try Detect " + userRgstr[sIndex]+"@"+domainRgstr);
		   if(sendedMail.contains(userRgstr[sIndex]+"@"+domainRgstr))
		   {
			forwardList.add(new MailAddress(mailRgstr[sIndex]));
			logger.info("User : "+ userRgstr[sIndex] + " email has been found, Forwarding to : " + 
			mailRgstr[sIndex]);
			forwardList.add(new MailAddress(userRgstr[sIndex],domainRgstr));
		   }
		   sIndex++;
		}
		   logger.debug("Here is the list : {} ,Updating mail list", forwardList);
		   mail.setRecipients(forwardList);
           }

}