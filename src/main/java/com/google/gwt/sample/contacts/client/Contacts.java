package com.google.gwt.sample.contacts.client;

import com.google.gwt.core.client.EntryPoint;
import com.smartgwt.client.types.DragDataAction;
import com.smartgwt.client.types.TreeModelType;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.grid.CellFormatter;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tree.Tree;
import com.smartgwt.client.widgets.tree.TreeGrid;
import com.smartgwt.client.widgets.tree.TreeGridField;
import com.smartgwt.client.widgets.tree.TreeNode;
import com.smartgwt.client.widgets.tree.events.DataArrivedEvent;
import com.smartgwt.client.widgets.tree.events.DataArrivedHandler;
import com.smartgwt.client.widgets.tree.events.FolderDropEvent;
import com.smartgwt.client.widgets.tree.events.FolderDropHandler;

public class Contacts
   implements EntryPoint
{

   public void onModuleLoad()
   {
      //ContactsServiceAsync rpcService = GWT.create(ContactsService.class);
      //HandlerManager eventBus = new HandlerManager(null);
      //AppController appViewer = new AppController(rpcService, eventBus);
      //appViewer.go( RootLayoutPanel.get());

      final TreeGrid treeGrid = setupMainGrid();

      //treeGrid.draw();

      HLayout mainLayout = new HLayout();
      mainLayout.setWidth100();
      mainLayout.setHeight100();

      treeGrid.setShowResizeBar( true );
      mainLayout.addMember( treeGrid );

      VLayout vLayout = new VLayout();
      vLayout.setWidth( 300 );

      /*final ToolStrip toolStrip = new ToolStrip();
      toolStrip.setLayoutLeftMargin( 10 );
      final Label label = new Label( "Structure" );
      toolStrip.addMember( label );
      toolStrip.setWidth100();
      vLayout.addMember( toolStrip );*/

      SectionStack structureSectionStack = new SectionStack();
      structureSectionStack.setWidth100();
      structureSectionStack.setHeight100();
      structureSectionStack.setShowResizeBar( true );

      String title = Canvas.imgHTML( "structure16.png" ) + " Structure";
      SectionStackSection structureSection = new SectionStackSection( title );

      structureSection.setCanCollapse( false );
      structureSection.setExpanded( true );

      final TreeGrid structureTree = setupStructureTree();

      SectionStack sectionStack = new SectionStack();
      sectionStack.setWidth100();
      sectionStack.setHeight100();

      SectionStackSection resourcesSection = new SectionStackSection( Canvas.imgHTML( "group16.png" ) + " Resources" );
      final ListGrid resourcesGrid = setupResourcesGrid();
      resourcesGrid.setHeight100();
      resourcesSection.addItem( resourcesGrid );

      SectionStackSection crewSection = new SectionStackSection( Canvas.imgHTML( "crew16.png" ) + " Crew Templates" );
      final TreeGrid crewTemplateTree = setupCrewTemplateTree();
      crewTemplateTree.setHeight100();
      crewSection.addItem( crewTemplateTree );

      SectionStackSection taskSection = new SectionStackSection( Canvas.imgHTML( "task16.png" ) + " Task Templates" );
      final TreeGrid taskTemplateTree = setupTaskTemplateTree();
      taskTemplateTree.setHeight100();
      taskSection.addItem( taskTemplateTree );

      structureSection.addItem( structureTree );
      structureSectionStack.addSection( structureSection );
      vLayout.addMember( structureSectionStack );

      sectionStack.addSection( resourcesSection );
      sectionStack.addSection( crewSection );
      sectionStack.addSection( taskSection );
      vLayout.addMember( sectionStack );

      mainLayout.addMember( vLayout );
      mainLayout.draw();
   }

   private TreeGrid setupMainGrid()
   {
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
      return treeGrid;
   }

   private TreeGrid setupStructureTree()
   {
      Tree grid1Tree = new Tree();
      grid1Tree.setModelType( TreeModelType.CHILDREN );
      grid1Tree.setNameProperty( "Name" );
      grid1Tree.setRoot( new TreeNode( "Root" ) );

      final TreeGrid structureTree = new TreeGrid();
      //structureTree.setLoadDataOnDemand( false );
      structureTree.setWidth100();
      structureTree.setHeight100();
      //structureTree.setDataSource( ContactsXmlDS.getInstance() );
      //structureTree.setAutoFetchData( true );
      structureTree.setData( grid1Tree );
      structureTree.setShowConnectors( true );
      structureTree.setClosedIconSuffix( "" );
      structureTree.setBorder( "none" );
      structureTree.setCanReorderRecords( true );
      structureTree.setCanReparentNodes( true );
      structureTree.setCanEdit( true );
      structureTree.setTitle( "Structure" );
      structureTree.setShowHeader( false );
      structureTree.setCanAcceptDroppedRecords( true );
      structureTree.setCanAcceptDrop( true );
      structureTree.setCanDropOnLeaves( true );
      structureTree.setDuplicateDragMessage( "Record already exists in tree" );
      final TreeGridField treeGridField = new TreeGridField( "Name" );
      structureTree.setFields( treeGridField );
      treeGridField.setCellFormatter( new CellFormatter()
      {
         public String format( Object value, ListGridRecord record, int rowNum, int colNum )
         {
            TreeNode tn = Tree.nodeForRecord( record );
            tn.setIcon( "crew_template16.png" );
            return value.toString();
         }
      } );

      structureTree.addDataArrivedHandler( new DataArrivedHandler()
      {
         public void onDataArrived( DataArrivedEvent event )
         {
            structureTree.getData().openAll();
         }
      } );

      structureTree.addFolderDropHandler( new FolderDropHandler()
      {
         @Override
         public void onFolderDrop( final FolderDropEvent dropEvent )
         {
            System.out.println( "111111111111111" );
            structureTree.setCanEdit( true );
         }
      } );

      return structureTree;
   }

   private ListGrid setupResourcesGrid()
   {
      final ListGrid listGrid = new ListGrid();
      listGrid.setWidth100();
      listGrid.setHeight100();
      listGrid.setDataSource( ContactsXmlDS.getInstance() );
      listGrid.setAutoFetchData( true );
      listGrid.setCanFreezeFields( true );
      listGrid.setBorder( "none" );

      TreeGridField nameField = new TreeGridField( "Name", 120 );
      TreeGridField jobField = new TreeGridField( "Job" );

      listGrid.setFields( nameField, jobField );
      return listGrid;
   }

   private TreeGrid setupCrewTemplateTree()
   {
      final TreeGrid crewTree = new TreeGrid();
      crewTree.setLoadDataOnDemand( false );
      crewTree.setWidth100();
      crewTree.setHeight100();
      crewTree.setDataSource( ContactsXmlDS.getInstance() );
      crewTree.setAutoFetchData( true );
      crewTree.setShowConnectors( true );
      crewTree.setClosedIconSuffix( "" );
      crewTree.setBorder( "none" );
      crewTree.setShowHeader( false );
      crewTree.setCanDragRecordsOut( true );
      crewTree.setDragDataAction( DragDataAction.COPY );
      crewTree.setCanEdit( true );

      final TreeGridField treeGridField = new TreeGridField( "Name" );
      crewTree.setFields( treeGridField );
      treeGridField.setCellFormatter( new CellFormatter()
      {
         public String format( Object value, ListGridRecord record, int rowNum, int colNum )
         {
            TreeNode tn = Tree.nodeForRecord( record );
            tn.setIcon( "crew_template16.png" );
            return value.toString();
         }
      } );

      crewTree.addDataArrivedHandler( new DataArrivedHandler()
      {
         public void onDataArrived( DataArrivedEvent event )
         {
            crewTree.getData().openAll();
         }
      } );
      return crewTree;
   }

   private TreeGrid setupTaskTemplateTree()
   {
      final TreeGrid taskTree = new TreeGrid();
      taskTree.setLoadDataOnDemand( false );
      taskTree.setWidth100();
      taskTree.setHeight100();
      taskTree.setDataSource( ContactsXmlDS.getInstance() );
      taskTree.setAutoFetchData( true );
      taskTree.setShowConnectors( true );
      taskTree.setClosedIconSuffix( "" );
      taskTree.setBorder( "none" );
      taskTree.setShowHeader( false );
      taskTree.setCanDragRecordsOut( true );
      taskTree.setDragDataAction( DragDataAction.COPY );

      final TreeGridField treeGridField = new TreeGridField( "Name" );
      taskTree.setFields( treeGridField );
      treeGridField.setCellFormatter( new CellFormatter()
      {
         public String format( Object value, ListGridRecord record, int rowNum, int colNum )
         {
            TreeNode tn = Tree.nodeForRecord( record );
            tn.setIcon( "tasks16.png" );
            return value.toString();
         }
      } );

      taskTree.addDataArrivedHandler( new DataArrivedHandler()
      {
         public void onDataArrived( DataArrivedEvent event )
         {
            taskTree.getData().openAll();
         }
      } );
      return taskTree;
   }
}
