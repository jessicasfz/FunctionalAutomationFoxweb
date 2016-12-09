package com.travelex.rap.pages;

import com.travelex.framework.utilities.Locator;
import com.travelex.framework.utilities.Utilities;
import com.travelex.framework.utilities.WebdriverWrapper;

public class VoltaSaleModuleLocator extends WebdriverWrapper {
	String environment = Utilities.getConfigValues("Environment");
	
	public static Locator voltaSalesModule=new Locator ("VoltaSalesModule","name","Travelex Currency Services Volta");
	public static Locator exitVoltaSales=new Locator ("ExitVoltaSalesModule","name","Exit");
	public static Locator voltaAutoPRModule=new Locator ("VoltaAutoPRModule","xpath","//*[contains(@ClassName,'FNWND3120')]");

	public static Locator extractOrdersButton=new Locator ("ExtractOrders","name","Extract Orders");
	public static Locator extractAllChkbox=new Locator ("ExtractAll","name","Extract All");
	public static Locator selectButton=new Locator ("SelectOrder","id","1006");
	public static Locator printRecords=new Locator ("PrintRecords","name","Print Reports");
	public static Locator extractOrdersDialog=new Locator ("ExtractOrderDialogBox","name","Extracting Orders");

	public static Locator voltaSaleDialog=new Locator ("VoltaDialogBox","name","Volta");
	public static Locator voltaSaleDialogError1=new Locator ("VoltaDialogBoxErrorMessage","name","Another extract is currently in progress. (E3751)");
	public static Locator voltaSaleDialogSuccess=new Locator ("VoltaDialogBoxSuccessMessage","name","The Extract has completed.");
	public static Locator voltaSaleDialogError2=new Locator ("VoltaDialogBoxErrorMessage","name","There are no orders to schedule for this run. (I3607)");
	public static Locator voltaSaleDialogError3=new Locator ("VoltaDialogBoxErrorMessage","name","No data could be retrieved using the information contained in the search area. (I0001)");
	public static Locator voltaSaleDialogSuccess2=new Locator ("VoltaDialogBoxSuccessMessage","name","Selected Batches have been confirmed as successfully dispensed. (I3603)");
	public static Locator voltaDialogYes=new Locator ("DialogBox","name","Yes");
	public static Locator voltaDialogNo=new Locator ("DialogBox","name","No");
	
	public static Locator voltaDialogOK=new Locator ("DialogBoxOK","name","OK");
	public static Locator cnfschdRunScen=new Locator ("Pane","name","order_currency_run_id_t");

	public static Locator scheduleOrdersButtonTab=new Locator ("ScheduleOrders","name","Schedule Orders");
	public static Locator allOrdersList=new Locator ("AllOrdersRunProfile","name","All Orders");
	public static Locator retrieveButton=new Locator ("RetrieveOrder","name","Retrieve");
	public static Locator scheduleOrdersButton=new Locator ("ScheduleOrder","name","Schedule");
	public static Locator confSchdRunPane=new Locator ("ConfirmSchedule","name","Confirm Schedule Run");
	public static Locator confirmButton=new Locator ("ConfirmScheduleRun","name","Confirm");

	public static Locator ordersButtonTab=new Locator ("Orders","name","Orders");
	public static Locator batchButtonTab=new Locator ("Batch","name","Batch");
	public static Locator cnfManualBatchButtonTab=new Locator ("ConfirmManualBatch","name","Confirm Manual Batch");
	public static Locator retrieveButtonTab=new Locator ("RetrieveBatch","name","Retrieve");
	public static Locator confirmButtonTab=new Locator ("ConfirmBatch","name","Confirm");
	public static Locator closeButtonTab=new Locator ("CloseBatch","name","Close");
	public static Locator selectAllButton=new Locator ("SelectAll","name","Select All");
	public static Locator selectionAreaPane=new Locator ("SelectionAreaPane","name","Selection Area   ");

	public static Locator startButton=new Locator ("StartAutoPR","id","1006");
	public static Locator closeButton=new Locator ("CloseAutoPR","id","1009");
	                
	public static Locator viewOrders=new Locator ("ViewOrderButton","name","View Orders");
	public static Locator orderRefLbl=new Locator ("OrderReferenceLable","name","t_3");
	public static Locator orderRefField=new Locator ("OrderReferenceTextField","name","orderreference");
	public static Locator cancelOrder=new Locator ("CancelOrderButton","name","Cancel Order");
	public static Locator orderRefLbl2=new Locator ("OrderReferenceLable","name","reference_code");
	public static Locator canReasonTxt=new Locator ("CancellationReasonTextField","id","10");
	public static Locator canReasonTxt1=new Locator ("CancellationReasonTextField","name","cancellation_reason_t");
	public static Locator cancelerrmessage = new Locator("CancelBtn","id","65535");
	public static Locator orderCancellationReason = new Locator("OrderCancellationReason","name","Order Cancellation Reason");
	public static Locator cancellationReasonTxtField = new Locator("CancellationReasonTextField","name","cancellation_reason");
    
	
	
	

}
