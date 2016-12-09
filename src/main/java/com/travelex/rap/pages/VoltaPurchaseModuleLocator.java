package com.travelex.rap.pages;

import com.travelex.framework.utilities.Locator;
import com.travelex.framework.utilities.WebdriverWrapper;

public class VoltaPurchaseModuleLocator extends WebdriverWrapper {
		
	public static Locator voltaPurchaseModule=new Locator ("VoltaPurchaseModule","name","Volta - Purchase Module"); 
	public static Locator importCompletedDialog=new Locator ("ImportCompleted","name","VOLTA");
	public static Locator importPurchasesWindow=new Locator ("ImportPurchaseWindow","name","Import Purchases"); 
	public static Locator importButton=new Locator ("ImportButton","name","Import"); 
	public static Locator yesButton=new Locator ("YesButton","name","Yes"); 
	public static Locator noButton=new Locator ("NoButton","name","No"); 
	public static Locator voltaPurchasesDialog=new Locator ("VoltaPurchasesDialog","name","Volta Purchases");
	public static Locator okButton=new Locator ("OkButton","id","2"); 
	public static Locator supervisorMenu=new Locator ("SupervisorMenu","name","Supervisor"); 
	public static Locator importPurchasesMenu=new Locator ("ImportPurchasesMenu","name","Import Purchases"); 
	public static Locator selectPurchaseBag=new Locator ("SelectPurchaseBagWindow","name","Select Purchase Bag"); 
	public static Locator retrieveButton=new Locator ("RetrieveButton","id","1006"); 
	public static Locator selectButton=new Locator ("SelectButton","id","1020"); 
	public static Locator selectPurchaseOrderWindow=new Locator ("SelectPurchaseOrderWindow","name","Select Purchase Order"); 
	public static Locator remittanceWindow=new Locator ("RemittanceWindow","name","Remittance");
	
	public static Locator purchaseOrderRef=new Locator ("PurchaseOrderRef","name","purchases_purchase_id");
	public static Locator shortQtyTextField=new Locator ("ShortQuantityTextField","name","short_qty");
	public static Locator saveButton=new Locator ("SaveButton","id","1017");
	public static Locator refCodeTextField=new Locator ("RefCodeTextField","name","reference_code"); 
	public static Locator closeBtn=new Locator ("CloseBtn","id","1003"); 
	public static Locator amalgamationWindow=new Locator ("AmalgamationWindow","name","Amalgamation"); 
	public static Locator previewButton=new Locator ("PreviewButton","name","Preview");
	public static Locator amalgamateButton=new Locator ("AmalgamateButton","name","Amalgamate");
		
	public static Locator previewPaneClick=new Locator ("PreviewPaneClick","id","1000");
	public static Locator amalgamationRowClick=new Locator ("AmalgamationRowClick","name","amalgamation_id");
	public static Locator confirmButton=new Locator ("ConfirmButton","name","Confirm");
	public static Locator batchNumberMessage=new Locator ("BatchNumberMessage","id","65535");
	
	public static Locator updatePurchInventoryWindow=new Locator ("updatePurchaseInventoryWindow","name","Update Purchase Inventory");	
	public static Locator updateButton=new Locator ("UpdateButton","name","Update"); 	
	public static Locator close=new Locator ("CloseButton","id","1003");
	public static Locator closeButton=new Locator ("CloseButton","name","Close");	
	public static Locator saleVoltaWindow=new Locator ("saleVoltaWindow","name","Travelex Currency Services Volta"); 
	
	public static Locator stock=new Locator ("stock","name","Stock"); 
	public static Locator importPurchaseInventory=new Locator ("stock","name","Import Purchase Inventory"); 
	public static Locator saleUpdateButton=new Locator ("SaleUpdateButton","name","Update"); 
	public static Locator exitButton=new Locator ("exitButton","id","1005");  
	public static Locator dialogVolta=new Locator ("dialogVolta","name","Volta");
	
	public static Locator errorMessage = new Locator("errmessage","id","65535");	
	public static Locator denomValueDropDown = new Locator("denomValueDropDown","name","denom_value");
	public static Locator fitTextField = new Locator("FitAmountTextField","name","fit");	
	public static Locator cancelBtn = new Locator("CancelBtn","name","Cancel");
	public static Locator cancelerrmessage = new Locator("CancelBtn","id","65535");
	
	public static Locator orderCancellationReason = new Locator("OrderCancellationReason","name","Order Cancellation Reason");
	public static Locator cancellationReasonTxtField = new Locator("CancellationReasonTextField","name","cancellation_reason");
	public static Locator okBtn = new Locator("OK Button","id","1001");	
	public static Locator importToBeginMessage = new Locator("ImportToBeginMessage","name","Click Import Button to begin Download");	
	public static Locator importConfirmationMessage = new Locator("ImportConfirmationMessage","name","Do you wish to proceed with the Purchases Import ? (W3812)");	
	public static Locator importCompleted = new Locator("ImportCompleted","name","The Import has completed.");
	public static Locator noDataerrorMessage = new Locator("NoDataErrorMessage","name","No data could be retrieved using the information contained in the search area. (I0001)");	
	public static Locator remittanceCancelledMsg = new Locator("RemittanceCancelledMsg","name","The remittance will be cancelled from the file.\r\nAre you sure you want to Continue? (W3809)");	
	public static Locator exitPurchaseModule = new Locator("ExitPurchaseModule","name","Do you want to exit Volta - Purchase Module? (W3722)");

	

}
