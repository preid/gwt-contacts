package com.google.gwt.sample.contacts.client;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceFloatField;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;

public class ContactsXmlDS extends DataSource
{
   private static ContactsXmlDS instance = null;

   public static  ContactsXmlDS getInstance()
   {
      if( null == instance )
      {
         instance = new ContactsXmlDS( "contactsDS" );
      }
      return instance;
   }

   public ContactsXmlDS(String id)
   {
      setID(id);
        setTitleField("Name");
        setRecordXPath("/List/employee");
        DataSourceTextField nameField = new DataSourceTextField("Name", "Name", 128);
        DataSourceIntegerField employeeIdField = new DataSourceIntegerField("EmployeeId", "Employee ID");
        employeeIdField.setPrimaryKey(true);
        employeeIdField.setRequired(true);

        DataSourceIntegerField reportsToField = new DataSourceIntegerField("ReportsTo", "Manager");
        reportsToField.setRequired(true);
        reportsToField.setForeignKey(id + ".EmployeeId");
        reportsToField.setRootValue("1");

        DataSourceTextField jobField = new DataSourceTextField("Job", "Title", 128);
        DataSourceTextField emailField = new DataSourceTextField("Email", "Email", 128);
        DataSourceTextField statusField = new DataSourceTextField("EmployeeStatus", "Status", 40);
        DataSourceFloatField salaryField = new DataSourceFloatField("Salary", "Salary");
        DataSourceTextField orgField = new DataSourceTextField("OrgUnit", "Org Unit", 128);
        DataSourceTextField genderField = new DataSourceTextField("Gender", "Gender", 7);
        genderField.setValueMap("male", "female");
        DataSourceTextField maritalStatusField = new DataSourceTextField("MaritalStatus", "Marital Status", 10);

        setFields(nameField, employeeIdField, reportsToField, jobField, emailField,
                statusField, salaryField, orgField, genderField, maritalStatusField);

        setDataURL("ds/contacts_data.xml");
        setClientOnly(true);
   }
}
