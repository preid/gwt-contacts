package com.google.gwt.sample.contacts.client;

import com.google.gwt.core.client.EntryPoint;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.grid.CellFormatter;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tree.Tree;
import com.smartgwt.client.widgets.tree.TreeGrid;
import com.smartgwt.client.widgets.tree.TreeGridField;
import com.smartgwt.client.widgets.tree.TreeNode;
import com.smartgwt.client.widgets.tree.events.DataArrivedEvent;
import com.smartgwt.client.widgets.tree.events.DataArrivedHandler;

public class Contacts
   implements EntryPoint
{

   public void onModuleLoad()
   {
      //ContactsServiceAsync rpcService = GWT.create(ContactsService.class);
      //HandlerManager eventBus = new HandlerManager(null);
      //AppController appViewer = new AppController(rpcService, eventBus);
      //appViewer.go( RootLayoutPanel.get());

      final TreeGrid treeGrid = new TreeGrid();
      treeGrid.setLoadDataOnDemand( false );
      treeGrid.setWidth100();
      treeGrid.setHeight100();
      treeGrid.setDataSource( ContactsXmlDS.getInstance() );
      treeGrid.setCanEdit( true );
      treeGrid.setNodeIcon( "person.png" );
      treeGrid.setFolderIcon( "person.png" );
      treeGrid.setAutoFetchData( true );
      treeGrid.setCanFreezeFields( true );
      treeGrid.setCanReparentNodes( true );

      TreeGridField nameField = new TreeGridField( "Name", 150 );
      nameField.setFrozen( true );

      TreeGridField jobField = new TreeGridField( "Job", 150 );
      TreeGridField employeeTypeField = new TreeGridField( "EmployeeType", 150 );
      TreeGridField employeeStatusField = new TreeGridField( "EmployeeStatus", 150 );
      TreeGridField salaryField = new TreeGridField( "Salary" );
      TreeGridField genderField = new TreeGridField( "Gender" );
      TreeGridField maritalStatusField = new TreeGridField( "MaritalStatus" );

      treeGrid.setFields( nameField, jobField, employeeTypeField, employeeStatusField, salaryField, genderField,
                          maritalStatusField );

      //treeGrid.draw();

      HLayout mainLayout = new HLayout();
      mainLayout.setWidth100();
      mainLayout.setHeight100();

      treeGrid.setShowResizeBar( true );
      mainLayout.addMember( treeGrid );

      VLayout vLayout = new VLayout();
      vLayout.setWidth( 300 );

      final TreeGrid employeeTree = new TreeGrid();
      employeeTree.setLoadDataOnDemand( false );
      employeeTree.setWidth100();
      employeeTree.setHeight100();
      employeeTree.setDataSource( ContactsXmlDS.getInstance() );
      employeeTree.setAutoFetchData( true );
      employeeTree.setShowConnectors( true );
      employeeTree.setNodeIcon( "person.png" );
      employeeTree.setFolderIcon( "person.png" );
      //employeeTree.setShowOpenIcons( false );
      //employeeTree.setShowDropIcons( false );
      employeeTree.setClosedIconSuffix( "" );
      //employeeTree.setBaseStyle( "noBorderCell" );

      final TreeGridField treeGridField = new TreeGridField( "Name" );
      treeGridField.setIcon( "crew_template16.png" );

      treeGridField.setCellFormatter( new CellFormatter()
      {
         public String format( Object value, ListGridRecord record, int rowNum, int colNum )
         {
            TreeNode tn = Tree.nodeForRecord(record);
            tn.setIcon( "crew_template16.png" );
            return value.toString();
         }
      } );

      employeeTree.setFields( treeGridField );
      employeeTree.setShowResizeBar( true );

      employeeTree.addDataArrivedHandler(new DataArrivedHandler() {
            public void onDataArrived(DataArrivedEvent event) {
                employeeTree.getData().openAll();
            }
        });

      Label detailsLabel = new Label();
      detailsLabel.setContents( "Details" );
      detailsLabel.setAlign( Alignment.CENTER );
      detailsLabel.setOverflow( Overflow.HIDDEN );
      detailsLabel.setHeight( "70%" );

      vLayout.addMember( employeeTree );
      vLayout.addMember( detailsLabel );

      mainLayout.addMember( vLayout );
      mainLayout.draw();
   }
}
