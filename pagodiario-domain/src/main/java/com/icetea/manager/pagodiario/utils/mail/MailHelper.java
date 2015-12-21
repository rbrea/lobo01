package com.icetea.manager.pagodiario.utils.mail;

import static org.slf4j.LoggerFactory.getLogger;

import java.util.Properties;

import javax.inject.Inject;
import javax.inject.Named;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;

import com.icetea.manager.pagodiario.exception.ErrorTypedException;
import com.sun.mail.smtp.SMTPTransport;

@Named
public class MailHelper {

	private static final Logger LOGGER = getLogger(MailHelper.class);
	
	private final String username;
	private final String password;
	private final String smtpAuth;
	private final String starttlsEnable;
	private final String host;
	private final String port;
	private final String fromEmail;
	
	@Inject
	public MailHelper(@Value("${mail.username}") String username, 
			@Value("${mail.password}") String password, 
			@Value("${mail.smtp.auth}") String smtpAuth,
			@Value("${mail.smtp.starttls.enable}") String starttlsEnable, 
			@Value("${mail.smtp.host}") String host, 
			@Value("${mail.smtp.port}") String port,
			@Value("${mail.from.email}") String fromEmail) {
		super();
		this.username = username;
		this.password = password;
		this.smtpAuth = smtpAuth;
		this.starttlsEnable = starttlsEnable;
		this.host = host;
		this.port = port;
		this.fromEmail = fromEmail;
	}

	public boolean send(final String emailTo, final String subject, final String text){
		
		Properties props = new Properties();
		props.put("mail.smtp.host", this.host);
		props.put("mail.smtp.port", this.port);
		props.put("mail.smtp.auth", this.smtpAuth);
		props.put("mail.debug", "true"); 
		props.put("mail.smtp.starttls.enable", this.starttlsEnable);
		props.put("mail.smtp.EnableSSL.enable","true");

	    
	    props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");   
	    props.setProperty("mail.smtp.socketFactory.fallback", "false");   
	    props.setProperty("mail.smtp.port", "465");   
	    props.setProperty("mail.smtp.socketFactory.port", "465"); 
	    
		LOGGER.info("Email preparando session para envio de email a: " + emailTo);
		
		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
				
						return new PasswordAuthentication(username, password);
					}
		});

		LOGGER.info("Email session lista para envio de email a: " + emailTo);
		
		try {

			Message message = new MimeMessage(session);
			
			//set message headers
			message.addHeader("Content-type", "text/HTML; charset=UTF-8");
			message.addHeader("format", "flowed");
			message.addHeader("Content-Transfer-Encoding", "8bit");
			
			message.setFrom(new InternetAddress(this.fromEmail));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(emailTo));
			message.setSubject(subject);
			message.setText(text);

			SMTPTransport transport = (SMTPTransport) session.getTransport("smtps");
			transport.connect(this.host, Integer.valueOf(this.port), this.username, this.password);
			transport.sendMessage(message, InternetAddress.parse(emailTo));
			

			LOGGER.info("Email de reset de password enviado a: " + emailTo);
			
		} catch (MessagingException e) {
			LOGGER.error(e.getMessage(), e);
			throw new ErrorTypedException("Ha ocurrido un error al intentar enviar el email de reseteo de password. "
					+ "\nPor favor contactese con el administrador del sistema.");
		}
		
		return true;
	}
	
}
