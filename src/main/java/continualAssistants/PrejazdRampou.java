package continualAssistants;

import OSPABA.*;
import simulation.*;
import agents.*;
import OSPABA.Process;

//meta! id="36"
public class PrejazdRampou extends Process
{
	public PrejazdRampou(int id, Simulation mySim, CommonAgent myAgent)
	{
		super(id, mySim, myAgent);
	}

	@Override
	public void prepareReplication()
	{
		super.prepareReplication();
		// Setup component for the next replication
	}

	//meta! sender="AgentServisu", id="37", type="Start"
	public void processStart(MessageForm message)
	{
		message.setCode(Mc.prechodCezRampu);
		hold(7,message);
	}

	//meta! userInfo="Process messages defined in code", id="0"
	public void processDefault(MessageForm message)
	{
		switch (message.code())
		{
		case Mc.prechodCezRampu:
			assistantFinished(message);
		}
	}

	//meta! userInfo="Generated code: do not modify", tag="begin"
	@Override
	public void processMessage(MessageForm message)
	{
		switch (message.code())
		{
		case Mc.start:
			processStart(message);
		break;

		default:
			processDefault(message);
		break;
		}
	}
	//meta! tag="end"

	@Override
	public AgentServisu myAgent()
	{
		return (AgentServisu)super.myAgent();
	}

}
