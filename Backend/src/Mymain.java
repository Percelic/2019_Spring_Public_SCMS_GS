import com.ControlAlgorithm.CAManager;
import com.DataCollector.DataCollectManager;
import com.TrackerController.TrackerControlManager;

public class Mymain {

	private static final int DATA_COLLECT_SERVER_PORT = 5000;
	private static final int TRACKER_CONTROL_SERVER_PORT = 5100;	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub		
		
		DataCollectManager dcm = (args.length >= 5) ?  new DataCollectManager(DATA_COLLECT_SERVER_PORT,args[0], args[1], args[2], args[3], args[4]) : 
													   new DataCollectManager(DATA_COLLECT_SERVER_PORT,"","","","","");		// DB IP , PORT ,  ID , PW, DB NAME
		if(dcm.GetIsRun()) {
		
			TrackerControlManager tcm = (args.length >= 5) ? new TrackerControlManager(TRACKER_CONTROL_SERVER_PORT,args[0], args[1], args[2], args[3], args[4]) :
															new TrackerControlManager(TRACKER_CONTROL_SERVER_PORT, "", "", "", "", "");
			if(tcm.GetIsRun()) {
				CAManager cam = new CAManager();
				
				tcm.TrackerControlManager_SetGroupInfo(dcm.DataCollectManager_GetGroupInfo());
				tcm.TrackerControlManager_SetGroupData(dcm.DataCollectManager_GetGroupData());
				
				cam.CAManager_SetGroupInfo(dcm.DataCollectManager_GetGroupInfo());
				cam.CAManager_SetGroupData(dcm.DataCollectManager_GetGroupData());	
				cam.CAManager_SetTransferQueue(dcm.DataCollectManager_GetTransferQueue());
				cam.CAManager_SetTModeData(dcm.DataCollectManager_GetGroupMode());
			}
			else {
				try {
				dcm.close();
				} catch(Exception e) {
					System.out.println("[ System ] Error occured while closing DataCollectManager :: " + e.toString());
				}
			}
		}
		else {
			try {
				dcm.close();
				} catch(Exception e) {
					System.out.println("[ System ] Error occured while closing DataCollectManager :: " + e.toString());
				}
		}
	}
}
