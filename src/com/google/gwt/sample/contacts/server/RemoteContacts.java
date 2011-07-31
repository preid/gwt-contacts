package com.google.gwt.sample.contacts.server;

import com.google.gwt.sample.contacts.shared.Contact;
import com.google.gwt.sample.contacts.shared.ContactDetails;

import javax.ejb.Remote;
import java.util.ArrayList;

@Remote
public interface RemoteContacts
{
  Contact addContact(Contact contact);

  Boolean deleteContact(String id);

  ArrayList<ContactDetails> deleteContacts(ArrayList<String> ids);

  ArrayList<ContactDetails> getContactDetails();

  Contact getContact(String id);

  Contact updateContact(Contact contact);
}
