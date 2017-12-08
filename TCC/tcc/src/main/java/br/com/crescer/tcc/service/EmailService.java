/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.tcc.service;


import br.com.crescer.tcc.entity.Usuario;
import java.util.List;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 *
 * @author luan.avila
 */
public class EmailService {
    
    public Session session(){
        Properties props = new Properties();
            /** Parâmetros de conexão com servidor Gmail */
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");

            Session session = Session.getDefaultInstance(props,
                        new javax.mail.Authenticator() {
                             protected PasswordAuthentication getPasswordAuthentication() 
                             {
                                   return new PasswordAuthentication("seuemail@gmail.com", "suasenha123");
                             }
                        });
            return session;
    }
    
    public void conviteGrupo(Session session, List<Usuario> usuarios){
        for(int i = 0; i < usuarios.size(); i++){
            session.setDebug(true);
            try {
                  Message message = new MimeMessage(session);
                  message.setFrom(new InternetAddress("seuemail@gmail.com")); //Remetente

                  Address[] toUser = InternetAddress //Destinatário(s)
                             .parse(usuarios.get(i).getEmail());  
                  message.setRecipients(Message.RecipientType.TO, toUser);
                  message.setSubject("Você Recebeu Um Convite no Bom-de-Bola");//Assunto
                  message.setText("Você foi convidado a participar de um novo grupo no Bom-de-Bola."
                          + "/n Cadastre-se agora ou faça login para aceitar!");
                  /**Método para enviar a mensagem criada*/
                  Transport.send(message);
             } catch (MessagingException e) {
                  throw new RuntimeException(e);
            }
        }
    }   
}