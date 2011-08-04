package com.google.gwt.sample.contacts.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.widgets.grid.events.EditorEnterEvent;
import com.smartgwt.client.widgets.grid.events.EditorEnterHandler;
import com.smartgwt.client.widgets.tree.TreeGrid;
import com.smartgwt.client.widgets.tree.TreeGridField;

public class Contacts implements EntryPoint
{

  public void onModuleLoad()
  {
    //ContactsServiceAsync rpcService = GWT.create(ContactsService.class);
    //HandlerManager eventBus = new HandlerManager(null);
    //AppController appViewer = new AppController(rpcService, eventBus);
    //appViewer.go( RootLayoutPanel.get());

     final TreeGrid treeGrid = new TreeGrid();
        treeGrid.setLoadDataOnDemand(false);
        treeGrid.setWidth(1000);
        treeGrid.setHeight(600);
        treeGrid.setDataSource(ContactsXmlDS.getInstance());
        treeGrid.setCanEdit(true);
        treeGrid.setNodeIcon("person.png");
        treeGrid.setFolderIcon("person.png");
        treeGrid.setAutoFetchData(true);
        treeGrid.setCanFreezeFields(true);
        treeGrid.setCanReparentNodes(true);

        TreeGridField nameField = new TreeGridField("Name", 150);
        nameField.setFrozen(true);

        TreeGridField jobField = new TreeGridField("Job", 150);
        TreeGridField employeeTypeField = new TreeGridField("EmployeeType", 150);
        TreeGridField employeeStatusField = new TreeGridField("EmployeeStatus", 150);
        TreeGridField salaryField = new TreeGridField("Salary");
        TreeGridField genderField = new TreeGridField("Gender");
        TreeGridField maritalStatusField = new TreeGridField("MaritalStatus");

        treeGrid.setFields(nameField, jobField, employeeTypeField,employeeStatusField, salaryField, genderField, maritalStatusField);

        treeGrid.draw();

  }
}
