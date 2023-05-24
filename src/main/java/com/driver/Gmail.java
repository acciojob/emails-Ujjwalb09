package com.driver;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Gmail extends Email {

    public class Mail
    {
        Date date;
        String sender;
        String message;

        public Mail(Date date, String sender, String message){

            this.date = date;
            this.sender = sender;
            this.message = message;
    }

    }

    List<Mail> inbox = new ArrayList<>();
    List<Mail> trash = new ArrayList<>();
    int inboxCapacity; //maximum number of mails inbox can store
    //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)
    public Gmail(String emailId, int inboxCapacity) {
        super(emailId);
        this.inboxCapacity  = inboxCapacity;
    }

    public void receiveMail(Date date, String sender, String message){
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.

        if(inbox.size() >= inboxCapacity){
            trash.add(inbox.get(0)); //moving oldest mail to trash
            inbox.remove(0); //removing oldest mail from inbox
        }

        inbox.add(new Mail(date, sender, message)); //adding new mail to the inbox
    }

    public void deleteMail(String message){
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing

        for(Mail m: inbox){
            if(Objects.equals(m.message, message)){
                trash.add(m);
                inbox.remove(m);
            }
        }

    }

    public String findLatestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the latest mail present in the inbox

        if(inbox.isEmpty()) return null;

        Mail mail = inbox.get(inbox.size()-1);

        return mail.message;

    }

    public String findOldestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the oldest mail present in the inbox

        if(inbox.isEmpty()) return null;

        Mail mail = inbox.get(0);

        return mail.message;
    }

    public int findMailsBetweenDates(Date start, Date end){
        //find number of mails in the inbox which are received between given dates
        //It is guaranteed that start date <= end date

        int count = 0;

        for(Mail m: inbox){
            int mailDate = Integer.parseInt(String.valueOf(m.date));
            int startDate = Integer.parseInt(String.valueOf(start));
            int endDate = Integer.parseInt(String.valueOf(end));

            if(mailDate >= startDate && mailDate <= endDate){
                count++;
            }
        }

        return count;
    }

    public int getInboxSize(){
        // Return number of mails in inbox

        return inbox.size();

    }

    public int getTrashSize(){
        // Return number of mails in Trash

        return trash.size();
    }

    public void emptyTrash(){
        // clear all mails in the trash

        trash.clear();
    }

    public int getInboxCapacity() {
        // Return the maximum number of mails that can be stored in the inbox
        return inboxCapacity;
    }
}
